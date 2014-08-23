/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreAjusteDao;
import dao.EquacaoDao;
import dao.LocalDao;
import dao.TermoDao;
import dao.VariavelDao;
import java.util.ArrayList;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.nfunk.jep.JEP;

/**
 *
 * @author jaime
 */
public class Equacao extends Model  {
    
    
    public int id;
    public String expressaoEquacao;
    public String expressaoEquacaoFormatada;    
    public String expressaoModelo;
    public int idVariavelInteresse;
    public int idAutorModelo;
    public int idtTrabalhoCientifico;
    
    public ArrayList<Termo> termos;
    public ArrayList<Variavel> variaveisEquacao;
    
    public Equacao()
    {
        this.expressaoEquacao = "";
        this.expressaoEquacaoFormatada = "";        
        this.expressaoModelo = "";
        this.idVariavelInteresse = 0;
        this.idAutorModelo = 0;
        this.idtTrabalhoCientifico = 0;
    }
    
    public String getIdString()
    {
        return String.valueOf(this.getId());
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpressaoEquacao() {
        return expressaoEquacao;
    }

    public void setExpressaoEquacao(String expressaoEquacao) {
        this.expressaoEquacao = expressaoEquacao;
    }

    public String getExpressaoEquacaoFormatada() {
        return expressaoEquacaoFormatada;
    }

    public void setExpressaoEquacaoFormatada(String expressaoEquacaoFormatada) {
        this.expressaoEquacaoFormatada = expressaoEquacaoFormatada;
    }

    public String getExpressaoModelo() {
        return expressaoModelo;
    }

    public void setExpressaoModelo(String expressaoModelo) {
        this.expressaoModelo = expressaoModelo;
    }

    public int getIdVariavelInteresse() {
        return idVariavelInteresse;
    }

    public void setIdVariavelInteresse(int idVariavelInteresse) {
        this.idVariavelInteresse = idVariavelInteresse;
    }

    public int getIdAutorModelo() {
        return idAutorModelo;
    }

    public void setIdAutorModelo(int idAutorModelo) {
        this.idAutorModelo = idAutorModelo;
    }

    public int getIdtTrabalhoCientifico() {
        return idtTrabalhoCientifico;
    }

    public void setIdtTrabalhoCientifico(int idtTrabalhoCientifico) {
        this.idtTrabalhoCientifico = idtTrabalhoCientifico;
    }

     public ArrayList<Termo> getTermos() throws Exception {
        
        ArrayList<Termo> termos = new ArrayList<Termo>();
        TermoDao termoDao = new TermoDao();
        termos = termoDao.listarTermos(this.id);

        return termos;
    }   
    public ArrayList<Variavel> getVariaveis() throws Exception {
        
        ArrayList<Variavel> variaveis = new ArrayList<Variavel>();
        VariavelDao variavelDao = new VariavelDao();
        variaveis = variavelDao.listarVariaveis(this.id);
        
        return variaveis;
    }   
    public boolean eh_valido()
    {
        if(this.getExpressaoEquacao() == null || this.getExpressaoEquacao().isEmpty())
        {
            this.setErro("Equação ", "não pode ficar em branco");
        }
        return (this.erros.isEmpty());
        
    }
    
    public ArrayList<Error> getErrors()
    {
        return this.erros;
    }

    public void ajustarModelo(Local local) throws Exception {
        
        JEP myParser = new JEP(); //http://www.singularsys.com/jep/doc/html/index.html
        
        myParser.addStandardFunctions();
        myParser.addStandardConstants();
        
        double resultadoTermo = 0.0;
        int qtdeVariaveis = 0;
        int idMetodoCalculo = 1; //Equação
                
        ArrayList<Termo> termos = getTermos();              
        
        ArrayList<ArvoreAjuste> arvoresAjuste = new ArrayList<ArvoreAjuste>();
        arvoresAjuste = local.getArvoresAjuste(idVariavelInteresse,idMetodoCalculo);

        double[] qtdeObs = new double[arvoresAjuste.size()];
        double[] qtdeEst = new double[arvoresAjuste.size()];
        int iArvoreAjuste = 0;
        
        double[][] valorEntrada= new double[arvoresAjuste.size()][termos.size()];
        int iTermo = 0;
        
        for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {

            for (Termo termo: termos) {
               
               for (VariavelArvoreAjuste variavelArvoreAjuste: arvoreAjuste.variaveisArvoreAjuste) {
                   String sigla = variavelArvoreAjuste.getVariavel().getSigla();
                   Double valor = variavelArvoreAjuste.getValor();
                   myParser.addVariable(sigla, valor);
               }
               myParser.parseExpression(termo.getExpressao());
               resultadoTermo = myParser.getValue();
               valorEntrada[iArvoreAjuste][iTermo] = resultadoTermo;
               
               iTermo++;
               
            }
            
            qtdeObs[iArvoreAjuste] = arvoreAjuste.getQtdeObs(idVariavelInteresse);
            
            iArvoreAjuste++;
            iTermo = 0;
        }

        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();        
        regression.newSampleData(qtdeObs, valorEntrada);
        double[] valorCoeficiente = regression.estimateRegressionParameters();
        
//Monta equacação a partir dos coeficientes calculados
        expressaoEquacao = Double.toString(valorCoeficiente[0]);
        iTermo = 1;
        for (Termo termo: termos) {
            expressaoEquacao = expressaoEquacao + "+("+valorCoeficiente[iTermo]+")*"+ termo.getExpressao();
            iTermo++;
        }

//Aplica equacaoModelo em todas as ArvoresAjuste para calcular valorEstimado        
        myParser.parseExpression(expressaoEquacao);
        
        iArvoreAjuste = 0;
        for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {

            arvoreAjuste.variaveisArvoreAjuste = arvoreAjuste.getVariaveisArvoreAjuste();
            qtdeVariaveis = arvoreAjuste.variaveisArvoreAjuste.size();
            
            for (VariavelArvoreAjuste variavelArvoreAjuste: arvoreAjuste.variaveisArvoreAjuste) {
                String sigla = variavelArvoreAjuste.getVariavel().getSigla();
                Double valor = variavelArvoreAjuste.getValor();
                myParser.addVariable(sigla, valor);
            }

            ArvoreAjusteDao arvoreAjusteDao = new ArvoreAjusteDao();            
            arvoreAjuste.setQtdeEst(myParser.getValue(),idVariavelInteresse,1); //Equacao
            arvoreAjusteDao.updateQtdeEst(arvoreAjuste,idVariavelInteresse,1); //Equacao
            
            qtdeEst[iArvoreAjuste] = arvoreAjuste.getQtdeEst(idVariavelInteresse,1); //Equacao);
            iArvoreAjuste++;
        }

        EstatisticaAjuste estatisticaAjuste = new EstatisticaAjuste();
        estatisticaAjuste.setIdLocal(local.getId());
        estatisticaAjuste.setIdVariavelInteresse(idVariavelInteresse);
        estatisticaAjuste.setIdMetodoCalculo(1); //Equação
        
        estatisticaAjuste.calcularEstatisticasAjuste(qtdeObs,qtdeEst,qtdeVariaveis);
    }


}

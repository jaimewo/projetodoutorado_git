/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.LocalDao;
import dao.TermoDao;
import java.util.ArrayList;
import org.nfunk.jep.JEP;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/**
 *
 * @author jaime
 */
public class Equacao extends Model  {
    
    
    public int id;
    public String expressaoEquacao;
    public String expressaoModelo;
    public int idVariavelInteresse;
    public int idAutorModelo;
    public double r2;
    public double r2Ajust;
    public double syx;
    public int idtTrabalhoCientifico;
    
    public ArrayList<Termo> termos;
    
    public Equacao()
    {
        this.expressaoEquacao = "";
        this.expressaoModelo = "";
        this.idVariavelInteresse = 0;
        this.idAutorModelo = 0;
        this.r2 = 0.0;
        this.r2Ajust = 0.0;
        this.syx = 0.0;
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

    public double getR2() {
        return r2;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }

    public double getR2Ajust() {
        return r2Ajust;
    }

    public void setR2Ajust(double r2Ajust) {
        this.r2Ajust = r2Ajust;
    }

    public double getSyx() {
        return syx;
    }

    public void setSyx(double syx) {
        this.syx = syx;
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

    public void ajustarModelo(int idLocal) throws Exception {
        
        JEP myParser = new JEP(); //http://www.singularsys.com/jep/doc/html/index.html
        
        myParser.addStandardFunctions();
        myParser.addStandardConstants();
        
        double resultadoTermo = 0.0;
        
        ArrayList<Termo> termos = getTermos();                
        
        Local local = new Local();
        
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);
        
        ArrayList<ArvoreAjuste> arvoresAjuste = new ArrayList<ArvoreAjuste>();
        arvoresAjuste = local.getArvoresAjuste();

        double[] valorObservado= new double[arvoresAjuste.size()];
        int iArvoreAjuste = 0;
        
        int qtdeTermos = termos.size();
        
        double[][] valorEntrada= new double[arvoresAjuste.size()][qtdeTermos];
        int iTermo = 0;
        
        for (Termo termo: termos) {
            for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {
                
               valorObservado[iArvoreAjuste] = arvoreAjuste.getQtdeBiomassaObs();
               iArvoreAjuste++;
               
               ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();
               variaveisArvoreAjuste = arvoreAjuste.getVariaveisArvoreAjuste();
               
               for (VariavelArvoreAjuste variavelArvoreAjuste: variaveisArvoreAjuste) {
                   String sigla = variavelArvoreAjuste.getVariavel().getSigla();
                   Double valor = variavelArvoreAjuste.getValor();
                   myParser.addVariable(sigla, valor);
               }
               myParser.parseExpression(termo.getExpressao());
               resultadoTermo = myParser.getValue();
               valorEntrada[iArvoreAjuste][iTermo] = resultadoTermo;
               //update TermoArvoreAjuste.valor = resultado
            }
        }
        int qtdeArvoresAjuste = iArvoreAjuste--;
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();        
        regression.newSampleData(valorObservado, valorEntrada);
        double[] valorCoeficiente = regression.estimateRegressionParameters();
        
        
    }
}

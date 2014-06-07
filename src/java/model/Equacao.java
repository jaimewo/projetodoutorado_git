/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    public String expressaoModelo;
    public int idVariavelInteresse;
    public int idAutorModelo;
    public double r2;
    public double r2Ajust;
    public double syx;
    public int idtTrabalhoCientifico;
    
    public ArrayList<Termo> termos;
    public ArrayList<Variavel> variaveisEquacao;
    
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
                
        ArrayList<Termo> termos = getTermos();              
        
        ArrayList<ArvoreAjuste> arvoresAjuste = new ArrayList<ArvoreAjuste>();
        arvoresAjuste = local.getArvoresAjuste();

//JJJ        ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();

        double[] valorObservado = new double[arvoresAjuste.size()];
        int iArvoreAjuste = 0;
        
        double[][] valorEntrada= new double[arvoresAjuste.size()][termos.size()];
        int iTermo = 0;

        for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {

            for (Termo termo: termos) {
//JJJ               variaveisArvoreAjuste = arvoreAjuste.getVariaveisArvoreAjuste();
               
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
            valorObservado[iArvoreAjuste] = arvoreAjuste.getQtdeBiomassaObs();
            iArvoreAjuste++;
            iTermo = 0;
        }
        int qtdeArvoresAjuste = iArvoreAjuste;
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();        
        regression.newSampleData(valorObservado, valorEntrada);
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
        for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {

            arvoreAjuste.variaveisArvoreAjuste = arvoreAjuste.getVariaveisArvoreAjuste();
               
            for (VariavelArvoreAjuste variavelArvoreAjuste: arvoreAjuste.variaveisArvoreAjuste) {
                String sigla = variavelArvoreAjuste.getVariavel().getSigla();
                Double valor = variavelArvoreAjuste.getValor();
                myParser.addVariable(sigla, valor);
            }
                
            switch (this.idVariavelInteresse) {
            case 1:
                arvoreAjuste.setQtdeBiomassaEst(myParser.getValue());
            //Update arvoreAjuste
                break;
            case 2:
                arvoreAjuste.setQtdeCarbonoEst(myParser.getValue());
                break;
            case 3:
                arvoreAjuste.setQtdeVolumeEst(myParser.getValue());                
                break;
            }
        }

        
//Calcula índices estatísticos (r2, r2Ajust, Syx)
        ArrayList<Double> quadradoResiduo = new ArrayList<>();
        ArrayList<Double> quadradoRegressao = new ArrayList<>();
        ArrayList<Double> quadradoTotais = new ArrayList<>();
        double valorObs =  0.0;
        double valorEst =  0.0;
        double somaObs = 0.0;
        double mediaObs = 0.0;
        double somaQuadradoResiduo = 0.0;
        double somaQuadradoRegressao = 0.0;
        double somaQuadradoTotais = 0.0;
        
        for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {
            switch (this.idVariavelInteresse) {
            case 1:
                 valorObs = arvoreAjuste.getQtdeBiomassaObs();
                 valorEst = arvoreAjuste.getQtdeBiomassaEst();
                 break;
            case 2:
                 valorObs = arvoreAjuste.getQtdeCarbonoObs();
                 valorEst = arvoreAjuste.getQtdeCarbonoEst();
                 break;
            case 3:
                 valorObs = arvoreAjuste.getQtdeVolumeObs();
                 valorEst = arvoreAjuste.getQtdeVolumeEst();
                 break;
            }    
            somaObs += valorObs;
            //quadradoResiduo.add(Math.pow((valorObs - valorEst), 2));
            somaQuadradoResiduo += Math.pow((valorObs - valorEst), 2);
        }        
        
        mediaObs = somaObs / arvoresAjuste.size();
        for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {
            switch (this.idVariavelInteresse) {
            case 1:
                 valorObs = arvoreAjuste.getQtdeBiomassaObs();
                 valorEst = arvoreAjuste.getQtdeBiomassaEst();
                 break;
            case 2:
                 valorObs = arvoreAjuste.getQtdeCarbonoObs();
                 valorEst = arvoreAjuste.getQtdeCarbonoEst();
                 break;
            case 3:
                 valorObs = arvoreAjuste.getQtdeVolumeObs();
                 valorEst = arvoreAjuste.getQtdeVolumeEst();
                 break;
            }    
            //quadradoRegressao.add(Math.pow((valorEst - mediaObs), 2));
            somaQuadradoRegressao += Math.pow((valorEst - mediaObs), 2);
            
            quadradoTotais.add(Math.pow((valorObs - mediaObs), 2));
            somaQuadradoTotais += Math.pow((valorObs - mediaObs), 2);
        }
        
       
        r2 = somaQuadradoRegressao / somaQuadradoTotais;
        
        //R2Ajust = (1 - r2) * ((n-1)/(n-p-1))   onde n=qtde de arvores na simulacao e p=qtde de termos da equação
        //PEND
        //PEND r2Ajust = 1 - (1-r2) * ((arvoresAjuste.size() - 1) / (arvoresAjuste.size() - 2 - 1));
               
        //PEND ia = 1 - (somaQuadradoResiduo/somaQuadradoTotais);

        
        //Syx = RAIZ (somaQuadradoResiduos/(n-p))
        syx = Math.sqrt(somaQuadradoResiduo/(arvoresAjuste.size() - 2));
        
        //Syx% = (Syx/volumeObsMedio)*100
        //PEND syxPerc = (syx / mediaObs) * 100;
        
        EquacaoDao equacaoDao = new EquacaoDao();
        equacaoDao.update(this);


        
        
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreDao;
import dao.VariavelArvoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.nfunk.jep.JEP;

/**
 *
 * @author jaime
 */
public class Arvore extends Model  {
    
    
    private int id;
    private int idParcela;
    private int numArvore;
    private double qtdeBiomassaObs;
    private double qtdeBiomassaEst;
    private double qtdeCarbonoObs;
    private double qtdeCarbonoEst;
    private double qtdeVolumeObs;
    private double qtdeVolumeEst;
    
    public ArrayList<VariavelArvore> variaveisArvore;
    
    public Arvore()
    {
        this.idParcela = 0;
        this.numArvore = 0;
        this.qtdeBiomassaObs = 0.0;
        this.qtdeBiomassaEst = 0.0;
        this.qtdeCarbonoObs = 0.0;
        this.qtdeCarbonoEst = 0.0;
        this.qtdeVolumeObs = 0.0;
        this.qtdeVolumeEst = 0.0;
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

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public int getNumArvore() {
        return numArvore;
    }

    public void setNumArvore(int numArvore) {
        this.numArvore = numArvore;
    }

    public double getQtdeBiomassaObs() {
        return qtdeBiomassaObs;
    }

    public void setQtdeBiomassaObs(double qtdeBiomassaObs) {
        this.qtdeBiomassaObs = qtdeBiomassaObs;
    }

    public double getQtdeBiomassaEst() {
        return qtdeBiomassaEst;
    }

    public void setQtdeBiomassaEst(double qtdeBiomassaEst) {
        this.qtdeBiomassaEst = qtdeBiomassaEst;
    }

    public double getQtdeCarbonoObs() {
        return qtdeCarbonoObs;
    }

    public void setQtdeCarbonoObs(double qtdeCarbonoObs) {
        this.qtdeCarbonoObs = qtdeCarbonoObs;
    }

    public double getQtdeCarbonoEst() {
        return qtdeCarbonoEst;
    }

    public void setQtdeCarbonoEst(double qtdeCarbonoEst) {
        this.qtdeCarbonoEst = qtdeCarbonoEst;
    }

    public double getQtdeVolumeObs() {
        return qtdeVolumeObs;
    }

    public void setQtdeVolumeObs(double qtdeVolumeObs) {
        this.qtdeVolumeObs = qtdeVolumeObs;
    }

    public double getQtdeVolumeEst() {
        return qtdeVolumeEst;
    }

    public void setQtdeVolumeEst(double qtdeVolumeEst) {
        this.qtdeVolumeEst = qtdeVolumeEst;
    }

    public ArrayList<VariavelArvore> getVariaveisArvore() throws Exception {
        ArrayList<VariavelArvore> variaveisArvore = new ArrayList<VariavelArvore>();
        VariavelArvoreDao variaveisArvoreDao = new VariavelArvoreDao();
        variaveisArvore = variaveisArvoreDao.listarVariaveisArvore(this.id);        
        
        return variaveisArvore;
    }

    public Double calculaBiomassaEst(Local local) throws SQLException, Exception {
        int idVarivelInteresse = 1; //Biomassa
        qtdeBiomassaEst = calculaQuantidade(local,idVarivelInteresse);
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.updateBiomassa(this);
        return qtdeBiomassaEst;
    }

    public Double calculaCarbonoEst(Local local) throws SQLException, Exception {
        int idVarivelInteresse = 2; //Carbono
        qtdeCarbonoEst = calculaQuantidade(local,idVarivelInteresse);
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.updateCarbono(this);
        return qtdeCarbonoEst;
    }
    
    public Double calculaVolumeEst(Local local) throws SQLException, Exception {
        int idVarivelInteresse = 3; //Volume
        qtdeVolumeEst = calculaQuantidade(local,idVarivelInteresse);
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.updateVolume(this);
        return qtdeVolumeEst;
    }
    
    
    private Double calculaQuantidade(Local local, int idVariavelInteresse) throws SQLException, Exception {

        Double resultado = 0.0;
        this.variaveisArvore = getVariaveisArvore();

        ArrayList<Equacao> equacoesTrabalho = new ArrayList<Equacao>();
        equacoesTrabalho = local.getTrabalhoCientifico().getEquacoesTrabalho();
        
        for (Equacao equacao : equacoesTrabalho) {
            if (equacao.getIdVariavelInteresse()==idVariavelInteresse) {
                resultado = aplicaParser(variaveisArvore,equacao);
            }
        }
        
        return resultado;
    
    }
    private Double aplicaParser(ArrayList<VariavelArvore> variaveisArvore, Equacao equacao) throws SQLException {
        //http://www.singularsys.com/jep/doc/html/index.html
        JEP myParser = new JEP();
        myParser.addStandardFunctions();
        myParser.addStandardConstants();

        double resultado = 0.0;
        
        for (VariavelArvore variavelArvore : variaveisArvore) {
            String sigla = variavelArvore.getVariavel().getSigla();
            Double valor = variavelArvore.getValor();
        
            myParser.addVariable(sigla, valor);
        }  
            
        myParser.parseExpression(equacao.getExpressaoEquacao());
        
        resultado = myParser.getValue();
        return resultado;
    
    }

}

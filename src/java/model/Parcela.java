/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreDao;
import dao.ParcelaDao;
import java.util.ArrayList;

/**
 *
 * @author jaime
 */
public class Parcela extends Model  {
    
    
    private int id;
    private int idLocal;
    private int numParcela;
    private double areaParcela;
    private double qtdeBiomassa;
    private double qtdeCarbono;
    private double qtdeVolume;
    
    private ArrayList<Arvore> arvores;
    
    public Parcela()
    {
        this.idLocal = 0;
        this.numParcela = 0;
        this.areaParcela = 0.0;
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

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(int numParcela) {
        this.numParcela = numParcela;
    }

    public double getAreaParcela() {
        return areaParcela;
    }

    public void setAreaParcela(double areaParcela) {
        this.areaParcela = areaParcela;
    }

    public double getQtdeBiomassa() {
        return qtdeBiomassa;
    }

    public void setQtdeBiomassa(double qtdeBiomassa) {
        this.qtdeBiomassa = qtdeBiomassa;
    }

    public double getQtdeCarbono() {
        return qtdeCarbono;
    }

    public void setQtdeCarbono(double qtdeCarbono) {
        this.qtdeCarbono = qtdeCarbono;
    }

    public double getQtdeVolume() {
        return qtdeVolume;
    }

    public void setQtdeVolume(double qtdeVolume) {
        this.qtdeVolume = qtdeVolume;
    }

    public ArrayList<Arvore> getArvores() throws Exception {
        ArrayList<Arvore> arvores = new ArrayList<Arvore>();
        ArvoreDao arvoreDao = new ArvoreDao();
        
        arvores = arvoreDao.listarArvores(this.id);
        
        return arvores;
    }

    public void setArvores(ArrayList<Arvore> arvores) {
        this.arvores = arvores;
    }

    public void calculaBiomassa(Local local) throws Exception {
        ArrayList<Arvore> arvores = new ArrayList();
        arvores = getArvores();
        for (Arvore arvore: arvores) {
            this.qtdeBiomassa += arvore.calculaBiomassaEst(local);
        }
        ParcelaDao parcelaDao = new ParcelaDao();
        parcelaDao.updateBiomassa(this);
    }

    public void calculaCarbono(Local local) throws Exception {
        ArrayList<Arvore> arvores = new ArrayList();
        arvores = getArvores();
        for (Arvore arvore: arvores) {
            this.qtdeCarbono += arvore.calculaCarbonoEst(local);
        }
        ParcelaDao parcelaDao = new ParcelaDao();
        parcelaDao.updateCarbono(this);
        
    }
    public void calculaVolume(Local local) throws Exception {
        ArrayList<Arvore> arvores = new ArrayList();
        arvores = getArvores();
        for (Arvore arvore: arvores) {
            this.qtdeVolume += arvore.calculaVolumeEst(local);
        }
        ParcelaDao parcelaDao = new ParcelaDao();
        parcelaDao.updateVolume(this);
        
    }
}

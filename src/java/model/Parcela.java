/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreDao;
import dao.ParcelaDao;
import dao.ParcelaQuantidadeDao;
import java.util.ArrayList;

/**
 *
 * @author jaime
 */
public class Parcela extends Model  {
    
    
    public int id;
    public int idLocal;
    public int numParcela;
    public double areaParcela;
    
    public ArrayList<Arvore> arvores;
    
    public ArrayList<ParcelaQuantidade> parcelasQuantidade;
    
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

    public ArrayList<Arvore> getArvores() throws Exception {
        ArrayList<Arvore> arvores = new ArrayList<Arvore>();
        ArvoreDao arvoreDao = new ArvoreDao();
        
        arvores = arvoreDao.listarArvores(this.id);
        
        return arvores;
    }

    public void setArvores(ArrayList<Arvore> arvores) {
        this.arvores = arvores;
    }

    public ArrayList<ParcelaQuantidade> getParcelasQuantidade() throws Exception {
        ArrayList<ParcelaQuantidade> parcelasQuantidade = new ArrayList<ParcelaQuantidade>();
        ParcelaQuantidadeDao parcelaQuantidadeDao = new ParcelaQuantidadeDao();
        
        parcelasQuantidade = parcelaQuantidadeDao.listarParcelasQuantidade(this.id);
        
        return parcelasQuantidade;
    }

    public void setParcelasQuantidade(ArrayList<ParcelaQuantidade> arvores) {
        this.parcelasQuantidade = parcelasQuantidade;
    }

}

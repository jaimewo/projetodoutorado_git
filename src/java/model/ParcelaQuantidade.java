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
public class ParcelaQuantidade extends Model  {
    
    
    public int id;
    public int idParcela;
    public int idVariavelInteresse;
    public double qtdeVariavelInteresse;

    public ParcelaQuantidade()
    {
        this.idParcela = 0;
        this.idVariavelInteresse = 0;
        this.qtdeVariavelInteresse = 0.0;
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

    public int getIdVariavelInteresse() {
        return idVariavelInteresse;
    }

    public void setIdVariavelInteresse(int idVariavelInteresse) {
        this.idVariavelInteresse = idVariavelInteresse;
    }

    public double getQtdeVariavelInteresse() {
        return qtdeVariavelInteresse;
    }

    public void setQtdeVariavelInteresse(double qtdeVariavelInteresse) {
        this.qtdeVariavelInteresse = qtdeVariavelInteresse;
    }

}

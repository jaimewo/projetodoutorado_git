/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jaime
 */
public class ArvoreVariavel extends Model  {
    
    
    public int id;
    public int idArvore;
    public int idVariavel;
    public double valor;
    
    public ArvoreVariavel()
    {
        this.idArvore = 0;
        this.idVariavel = 0;
        this.valor = 0.0;
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

    public int getIdArvore() {
        return idArvore;
    }

    public void setIdArvore(int idArvore) {
        this.idArvore = idArvore;
    }

    public int getIdVariavel() {
        return idVariavel;
    }

    public void setIdVariavel(int idVariavel) {
        this.idVariavel = idVariavel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}

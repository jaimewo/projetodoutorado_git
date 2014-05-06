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
public class VariavelArvoreAjuste extends Model  {
    
    
    public int id;
    public int idArvoreAjuste;
    public int idVariavel;
    public double valor;
    
    public Variavel variavel;
    
    public VariavelArvoreAjuste()
    {
        this.idArvoreAjuste = 0;
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

    public int getIdArvoreAjuste() {
        return idArvoreAjuste;
    }

    public void setIdArvoreAjuste(int idArvoreAjuste) {
        this.idArvoreAjuste = idArvoreAjuste;
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
 
    public Variavel getVariavel() {
        return variavel;
    }

    public void setVariavel(Variavel variavel) {
        this.variavel = variavel;
    }
       
}

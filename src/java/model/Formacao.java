/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author paulozeferino
 */
public class Formacao extends Model  {
    
    
    public int id;
    public String descricao;
    public int idBioma;

    
    public Formacao()
    {
        this.descricao = "";
        this.idBioma = 0;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getIdBioma() {
        return idBioma;
    }

    public void setIdBioma(int idBioma) {
        this.idBioma = idBioma;
    }
    public boolean eh_valido()
    {
        if(this.getDescricao() == null || this.getDescricao().isEmpty())
        {
            this.setErro("Descrição ", "não pode ficar em branco");
        }
        if(this.getIdBioma() == 0)
        {
            this.setErro("Bioma ", "deve ser informado");
        }        
        return (this.erros.isEmpty());
        
    }

      public ArrayList<Error> getErrors()
    {
        return this.erros;
    } 
    
}

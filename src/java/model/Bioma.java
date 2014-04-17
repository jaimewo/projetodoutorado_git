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
public class Bioma extends Model  {
    
    
    public int id;
    public String descricao;
    
    public Bioma()
    {
        this.descricao = "";
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
    
    public boolean eh_valido()
    {
        if(this.getDescricao() == null || this.getDescricao().isEmpty())
        {
            this.setErro("Descrição ", "não pode ficar em branco");
        }
        return (this.erros.isEmpty());
        
    }
    
     public ArrayList<Error> getErrors()
    {
        return this.erros;
    }   
    
}

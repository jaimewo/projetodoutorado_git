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
public class Municipio extends Model  {
    
    
    public int id;
    public String nome;
    public int idEstado;
    
    public Municipio()
    {
        this.nome = "";
        this.idEstado = 0;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    
    public boolean eh_valido()
    {
        if(this.getNome() == null || this.getNome().isEmpty())
        {
            this.setErro("Nome ", "não pode ficar em branco");
        }
        return (this.erros.isEmpty());
        
    }
    
    public ArrayList<Error> getErrors()
    {
        return this.erros;
    }

    
}

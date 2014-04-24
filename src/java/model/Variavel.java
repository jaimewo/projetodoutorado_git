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
public class Variavel extends Model  {
    
    
    public int id;
    public String sigla;
    public String nome;
    
    public Variavel()
    {
        this.nome = "";
        this.sigla = "";
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.nome = sigla;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public boolean eh_valido()
    {
        if(this.getSigla() == null || this.getSigla().isEmpty())
        {
            this.setErro("Sigla ", "deve ser informada");
        }
        
        if(this.getNome() == null || this.getNome().isEmpty())
        {
            this.setErro("Nome ", "deve ser informado");
        }
        return (this.erros.isEmpty());
        
    }
    
    public ArrayList<Error> getErrors()
    {
        return this.erros;
    } 
    
}

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
public class Equacao extends Model  {
    
    
    public int id;
    public String expressaoEquacao;
    public String expressaoModelo;
    public int idVariavelInteresse;
    public int idAutorModelo;
    
    public Equacao()
    {
        this.expressaoEquacao = "";
        this.expressaoModelo = "";
        this.idVariavelInteresse = 0;
        this.idAutorModelo = 0;
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

    public String getExpressaoEquacao() {
        return expressaoEquacao;
    }

    public void setExpressaoEquacao(String expressaoEquacao) {
        this.expressaoEquacao = expressaoEquacao;
    }

    public String getExpressaoModelo() {
        return expressaoModelo;
    }

    public void setExpressaoModelo(String expressaoModelo) {
        this.expressaoModelo = expressaoModelo;
    }

    public int getIdVariavelInteresse() {
        return idVariavelInteresse;
    }

    public void setIdVariavelInteresse(int idVariavelInteresse) {
        this.idVariavelInteresse = idVariavelInteresse;
    }

    public int getIdAutorModelo() {
        return idAutorModelo;
    }

    public void setIdAutorModelo(int idAutorModelo) {
        this.idAutorModelo = idAutorModelo;
    }

    
    public boolean eh_valido()
    {
        if(this.getExpressaoEquacao() == null || this.getExpressaoEquacao().isEmpty())
        {
            this.setErro("Equação ", "não pode ficar em branco");
        }
        return (this.erros.isEmpty());
        
    }
    
    public ArrayList<Error> getErrors()
    {
        return this.erros;
    }

    
}

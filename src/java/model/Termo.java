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
public class Termo extends Model  {
    
    
    public int id;
    public int idEquacao;
    public String expressao;
    public int ordem;
    
    public Termo()
    {
        this.expressao = "";
        this.idEquacao = 0;
        this.ordem = 0;
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

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public int getIdEquacao() {
        return idEquacao;
    }

    public void setIdEquacao(int idEquacao) {
        this.idEquacao = idEquacao;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    
}

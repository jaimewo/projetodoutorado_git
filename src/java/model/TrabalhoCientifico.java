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
public class TrabalhoCientifico extends Model  {
    
    
    public int id;
    public String titulo;
    public int ano;
    public int idTipoDisponibilidade;
    public int idMetodoQuantificacaoBiomassa;
    public int idMetodoQuantificacaoCarbono;
    public int idAutor;

    
    public TrabalhoCientifico()
    {
        this.titulo = "";
        
        this.idTipoDisponibilidade = 0;
        this.idMetodoQuantificacaoBiomassa = 0;
        this.idMetodoQuantificacaoCarbono = 0;
        this.idAutor = 0;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.id = ano;
    }
    
    public int getIdTipoDisponibilidade() {
        return idTipoDisponibilidade;
    }

    public void setIdTipoDisponibilidade(int idTipoDisponibilidade) {
        this.idTipoDisponibilidade = idTipoDisponibilidade;
    }
    
    public int getIdMetodoQuantificacaoBiomassa() {
        return idMetodoQuantificacaoBiomassa;
    }

    public void setIdMetodoQuantificacaoBiomassa(int idMetodoQuantificacaoBiomassa) {
        this.idMetodoQuantificacaoBiomassa = idMetodoQuantificacaoBiomassa;
    }
    public int getIdMetodoQuantificacaoCarbono() {
        return idMetodoQuantificacaoCarbono;
    }

    public void setIdMetodoQuantificacaoCarbono(int idMetodoQuantificacaoCarbono) {
        this.idMetodoQuantificacaoCarbono = idMetodoQuantificacaoCarbono;
    }
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.id = idAutor;
    }
    
    public boolean eh_valido()
    {
        if(this.getTitulo() == null || this.getTitulo().isEmpty())
        {
            this.setErro("Título ", "não pode ficar em branco");
        }
        if(this.getAno() == 0)
        {
            this.setErro("Ano ", "não pode ficar em branco");
        }
        if(this.getIdTipoDisponibilidade() == 0)
        {
            this.setErro("Tipo de Disponibilidade ", "deve ser informado");
        }
        if(this.getIdMetodoQuantificacaoBiomassa() == 0)
        {
            this.setErro("Metodo de Quantificação de Biomassa ", "deve ser selecionado");
        }
        if(this.getIdMetodoQuantificacaoCarbono() == 0)
        {
            this.setErro("Metodo de Quantificação de Carbono ", "deve ser selecionado");
        }        
        if(this.getIdAutor() == 0)
        {
            this.setErro("Autor ", "não pode ficar em branco");
        }        
        return (this.erros.isEmpty());
        
    }

   
    public ArrayList<Error> getErrors()
    {
        return this.erros;
    }
    
    
}

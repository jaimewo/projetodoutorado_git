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
public class Local extends Model  {
    
    
    public int id;
    public String descricao;
    public double area;
    public double areaParcela;
    public double qtdeBiomassa;
    public double qtdeCarbono;
    public double qtdeVolume;
    public int idTipoEstimativa;
    public int idFormacao;
    public int idEspacamento;
    public int idTrabalhoCientifico;
    
    public Local()
    {
        this.descricao = "";
        this.area = 0.0;
        this.areaParcela = 0.0;
        this.idEspacamento = 0;
        this.idFormacao = 0;
        this.idTrabalhoCientifico = 0;
    }
    
    public String getIdString()
    {
        return String.valueOf(this.getId());
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAreaParcela() {
        return areaParcela;
    }

    public void setAreaParcela(double areaParcela) {
        this.areaParcela = areaParcela;
    }

    public double getQtdeBiomassa() {
        return qtdeBiomassa;
    }

    public void setQtdeBiomassa(double qtdeBiomassa) {
        this.qtdeBiomassa = qtdeBiomassa;
    }

    public double getQtdeCarbono() {
        return qtdeCarbono;
    }

    public void setQtdeCarbono(double qtdeCarbono) {
        this.qtdeCarbono = qtdeCarbono;
    }

    public double getQtdeVolume() {
        return qtdeVolume;
    }

    public void setQtdeVolume(double qtdeVolume) {
        this.qtdeVolume = qtdeVolume;
    }

    public int getIdTipoEstimativa() {
        return idTipoEstimativa;
    }

    public void setIdTipoEstimativa(int idTipoEstimativa) {
        this.idTipoEstimativa = idTipoEstimativa;
    }

    public int getIdFormacao() {
        return idFormacao;
    }

    public void setIdFormacao(int idFormacao) {
        this.idFormacao = idFormacao;
    }

    public int getIdEspacamento() {
        return idEspacamento;
    }

    public void setIdEspacamento(int idEspacamento) {
        this.idEspacamento = idEspacamento;
    }

    public int getIdTrabalhoCientifico() {
        return idTrabalhoCientifico;
    }

    public void setIdTrabalhoCientifico(int idTrabalhoCientifico) {
        this.idTrabalhoCientifico = idTrabalhoCientifico;
    }

    public boolean eh_valido()
    {
        if(this.getDescricao() == null || this.getDescricao().isEmpty())
        {
            this.setErro("Descrição ", "não pode ficar em branco");
        }
        if(this.getArea() == 0)
        {
            this.setErro("Área ", "deve ser informada");
        }        
        if(this.getAreaParcela() == 0)
        {
            this.setErro("Área da Parcela ", "deve ser informada");
        }                
        if(this.getIdFormacao() == 0)
        {
            this.setErro("Formação ", "deve ser informada");
        }                
        if(this.getIdEspacamento() == 0)
        {
            this.setErro("Espaçamento ", "deve ser informado");
        }                
        if(this.getIdTrabalhoCientifico() == 0)
        {
            this.setErro("Trabalho Científico ", "deve ser informado");
        }                        
        return (this.erros.isEmpty());
        
    }
    
    public ArrayList<Error> getErrors()
    {
        return this.erros;
    }

    
}

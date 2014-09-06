/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author jaime
 */
public class ValorPadrao extends Model  {
    
    
    private int id;
    private int idTipoFloresta;
    private int idFormacao;
    private int idEspecie;
    private int idEspacamento;
    private int idadeInicial;
    private int idadeFinal;
    private int idEquacaoPadrao;
    private double qtdeVolumePadrao;
    private double qtdeBiomassaPadrao;
    private double qtdeCarbonoPadrao;
    
    public ValorPadrao()
    {
        this.idTipoFloresta = 0;
        this.idFormacao = 0;
        this.idEspecie = 0;        
        this.idEspacamento = 0;        
        this.idadeInicial = 0;        
        this.idadeFinal = 0;        
        this.idEquacaoPadrao = 0;        
        this.qtdeVolumePadrao = 0.0;        
        this.qtdeBiomassaPadrao = 0.0;        
        this.qtdeCarbonoPadrao = 0.0;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipoFloresta() {
        return idTipoFloresta;
    }

    public void setIdTipoFloresta(int idTipoFloresta) {
        this.idTipoFloresta = idTipoFloresta;
    }

    public int getIdFormacao() {
        return idFormacao;
    }

    public void setIdFormacao(int idFormacao) {
        this.idFormacao = idFormacao;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdEspacamento() {
        return idEspacamento;
    }

    public void setIdEspacamento(int idEspacamento) {
        this.idEspacamento = idEspacamento;
    }

    public int getIdadeInicial() {
        return idadeInicial;
    }

    public void setIdadeInicial(int idadeInicial) {
        this.idadeInicial = idadeInicial;
    }

    public int getIdadeFinal() {
        return idadeFinal;
    }

    public void setIdadeFinal(int idadeFinal) {
        this.idadeFinal = idadeFinal;
    }

    public int getIdEquacaoPadrao() {
        return idEquacaoPadrao;
    }

    public void setIdEquacaoPadrao(int idEquacaoPadrao) {
        this.idEquacaoPadrao = idEquacaoPadrao;
    }

    public double getQtdeVolumePadrao() {
        return qtdeVolumePadrao;
    }

    public void setQtdeVolumePadrao(double qtdeVolumePadrao) {
        this.qtdeVolumePadrao = qtdeVolumePadrao;
    }

    public double getQtdeBiomassaPadrao() {
        return qtdeBiomassaPadrao;
    }

    public void setQtdeBiomassaPadrao(double qtdeBiomassaPadrao) {
        this.qtdeBiomassaPadrao = qtdeBiomassaPadrao;
    }

    public double getQtdeCarbonoPadrao() {
        return qtdeCarbonoPadrao;
    }

    public void setQtdeCarbonoPadrao(double qtdeCarbonoPadrao) {
        this.qtdeCarbonoPadrao = qtdeCarbonoPadrao;
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreAjusteDao;
import dao.MunicipioLocalDao;
import dao.ParcelaDao;
import dao.TrabalhoCientificoDao;
import java.sql.SQLException;
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
    
    public TrabalhoCientifico trabalhoCientifico;
    public ArrayList<MunicipioLocal> municipiosLocal;
    public ArrayList<Parcela> parcelas;
    public ArrayList<ArvoreAjuste> arvoresAjuste;
    
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
    
    public TrabalhoCientifico getTrabalhoCientifico() throws SQLException {
        
        TrabalhoCientificoDao trabalhoCientificoDao = new TrabalhoCientificoDao();
        trabalhoCientifico = trabalhoCientificoDao.getTrabalhoCientifico(this.idTrabalhoCientifico);
        
        return trabalhoCientifico;
    }

    public ArrayList<MunicipioLocal> getMunicipiosLocal() throws Exception {
        ArrayList<MunicipioLocal> municipiosLocal = new ArrayList<MunicipioLocal>();
        MunicipioLocalDao municipioLocalDao = new MunicipioLocalDao();
        municipiosLocal = municipioLocalDao.listarMunicipioLocal(this.id);
        
        return municipiosLocal;
    }

    public void setMunicipiosLocal(ArrayList<MunicipioLocal> municipiosLocal) {
        this.municipiosLocal = municipiosLocal;
    }

    public ArrayList<Parcela> getParcelas() throws Exception {
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
        ParcelaDao parcelaDao = new ParcelaDao();
        
        parcelas = parcelaDao.listarParcelas(this.id);
        
        return parcelas;
    }

    public void setParcelas(ArrayList<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public ArrayList<ArvoreAjuste> getArvoresAjuste() throws Exception {
        ArrayList<ArvoreAjuste> arvoresAjuste = new ArrayList<ArvoreAjuste>();
        ArvoreAjusteDao arvoreAjusteDao = new ArvoreAjusteDao();
        
        arvoresAjuste = arvoreAjusteDao.listarArvoresAjuste(this.id);
        
        return arvoresAjuste;
    }

    public void setArvoreAjuste(ArrayList<ArvoreAjuste> arvoresAjuste) {
        this.arvoresAjuste = arvoresAjuste;
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

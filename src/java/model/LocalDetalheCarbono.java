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
public class LocalDetalheCarbono extends Model  {
    
    
    public int id;
    public int idLocal;
    public double qtdeCarbonoMin;
    public double qtdeCarbonoMed;
    public double qtdeCarbonoMax;
    public double mediaParcela;
    public double variancia;
    public double desvioPadrao;
    public double varianciaMedia;
    public double erroPadrao;
    public double coeficienteVariacao;
    public double erroAbsoluto;
    public double erroRelativo;
    public double intervaloConfiancaMinParcela;
    public double intervaloConfiancaMaxParcela;
    
    public LocalDetalheCarbono()
    {
        this.idLocal = 0;
        this.qtdeCarbonoMin = 0.0;
        this.qtdeCarbonoMed = 0.0;
        this.qtdeCarbonoMax = 0.0;
        this.mediaParcela = 0.0;
        this.variancia = 0.0;
        this.varianciaMedia = 0.0;
        this.erroPadrao = 0.0;
        this.coeficienteVariacao = 0.0;
        this.erroAbsoluto = 0.0;
        this.erroRelativo = 0.0;
        this.intervaloConfiancaMinParcela = 0.0;
        this.intervaloConfiancaMaxParcela = 0.0;
        
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

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public double getQtdeCarbonoMin() {
        return qtdeCarbonoMin;
    }

    public void setQtdeCarbonoMin(double qtdeCarbonoMin) {
        this.qtdeCarbonoMin = qtdeCarbonoMin;
    }

    public double getQtdeCarbonoMed() {
        return qtdeCarbonoMed;
    }

    public void setQtdeCarbonoMed(double qtdeCarbonoMed) {
        this.qtdeCarbonoMed = qtdeCarbonoMed;
    }

    public double getQtdeCarbonoMax() {
        return qtdeCarbonoMax;
    }

    public void setQtdeCarbonoMax(double qtdeCarbonoMax) {
        this.qtdeCarbonoMax = qtdeCarbonoMax;
    }

    public double getMediaParcela() {
        return mediaParcela;
    }

    public void setMediaParcela(double mediaParcela) {
        this.mediaParcela = mediaParcela;
    }

    public double getVariancia() {
        return variancia;
    }

    public void setVariancia(double variancia) {
        this.variancia = variancia;
    }

    public double getDesvioPadrao() {
        return desvioPadrao;
    }

    public void setDesvioPadrao(double desvioPadrao) {
        this.desvioPadrao = desvioPadrao;
    }

    public double getVarianciaMedia() {
        return varianciaMedia;
    }

    public void setVarianciaMedia(double varianciaMedia) {
        this.varianciaMedia = varianciaMedia;
    }

    public double getErroPadrao() {
        return erroPadrao;
    }

    public void setErroPadrao(double erroPadrao) {
        this.erroPadrao = erroPadrao;
    }

    public double getCoeficienteVariacao() {
        return coeficienteVariacao;
    }

    public void setCoeficienteVariacao(double coeficienteVariacao) {
        this.coeficienteVariacao = coeficienteVariacao;
    }

    public double getErroAbsoluto() {
        return erroAbsoluto;
    }

    public void setErroAbsoluto(double erroAbsoluto) {
        this.erroAbsoluto = erroAbsoluto;
    }

    public double getErroRelativo() {
        return erroRelativo;
    }

    public void setErroRelativo(double erroRelativo) {
        this.erroRelativo = erroRelativo;
    }

    public double getIntervaloConfiancaMinParcela() {
        return intervaloConfiancaMinParcela;
    }

    public void setIntervaloConfiancaMinParcela(double intervaloConfiancaMinParcela) {
        this.intervaloConfiancaMinParcela = intervaloConfiancaMinParcela;
    }

    public double getIntervaloConfiancaMaxParcela() {
        return intervaloConfiancaMaxParcela;
    }

    public void setIntervaloConfiancaMaxParcela(double intervaloConfiancaMaxParcela) {
        this.intervaloConfiancaMaxParcela = intervaloConfiancaMaxParcela;
    }
    
}

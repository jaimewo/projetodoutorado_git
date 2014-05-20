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
public class Estatistica extends Model  {
    
    
    public int id;
    public int idLocal;
    public int idVariavelInteresse;
    public double qtdeMinima;
    public double qtdeMedia;
    public double qtdeMaxima;
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

    
    public Estatistica()
    {
        this.idLocal = 0;
        this.idVariavelInteresse = 0;
        this.qtdeMinima = 0.0;
        this.qtdeMedia = 0.0;
        this.qtdeMaxima = 0.0;
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

    public double getQtdeMinima() {
        return qtdeMinima;
    }

    public void setQtdeMinima(double qtdeMinima) {
        this.qtdeMinima = qtdeMinima;
    }

    public double getQtdeMedia() {
        return qtdeMedia;
    }

    public void setQtdeMedia(double qtdeMedia) {
        this.qtdeMedia = qtdeMedia;
    }

    public double getQtdeMaxima() {
        return qtdeMaxima;
    }

    public void setQtdeMaxima(double qtdeMaxima) {
        this.qtdeMaxima = qtdeMaxima;
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

    public int getIdVariavelInteresse() {
        return idVariavelInteresse;
    }

    public void setIdVariavelInteresse(int idVariavelInteresse) {
        this.idVariavelInteresse = idVariavelInteresse;
    }


    
}

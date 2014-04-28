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
public class Parcela extends Model  {
    
    
    public int id;
    public int idLocal;
    public int numParcela;
    public double areaParcela;
    public double qtdeBiomassa;
    public double qtdeBiomassaMin;
    public double qtdeBiomassaMed;
    public double qtdeBiomassaMax;
    public double qtdeCarbono;
    public double qtdeCarbonoMin;
    public double qtdeCarbonoMed;
    public double qtdeCarbonoMax;
    public double qtdeVolume;
    public double qtdeVolumeMin;
    public double qtdeVolumeMed;
    public double qtdeVolumeMax;

    public Parcela()
    {
        this.idLocal = 0;
        this.numParcela = 0;
        this.areaParcela = 0.0;
        this.qtdeBiomassa = 0.0;
        this.qtdeBiomassaMin = 0.0;
        this.qtdeBiomassaMed = 0.0;
        this.qtdeBiomassaMax = 0.0;
        this.qtdeCarbono = 0.0;
        this.qtdeCarbonoMin = 0.0;
        this.qtdeCarbonoMed = 0.0;
        this.qtdeCarbonoMax = 0.0;
        this.qtdeVolume = 0.0;
        this.qtdeVolumeMin = 0.0;
        this.qtdeVolumeMed = 0.0;
        this.qtdeVolumeMax = 0.0;
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

    public int getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(int numParcela) {
        this.numParcela = numParcela;
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

    public double getQtdeBiomassaMin() {
        return qtdeBiomassaMin;
    }

    public void setQtdeBiomassaMin(double qtdeBiomassaMin) {
        this.qtdeBiomassaMin = qtdeBiomassaMin;
    }

    public double getQtdeBiomassaMed() {
        return qtdeBiomassaMed;
    }

    public void setQtdeBiomassaMed(double qtdeBiomassaMed) {
        this.qtdeBiomassaMed = qtdeBiomassaMed;
    }

    public double getQtdeBiomassaMax() {
        return qtdeBiomassaMax;
    }

    public void setQtdeBiomassaMax(double qtdeBiomassaMax) {
        this.qtdeBiomassaMax = qtdeBiomassaMax;
    }

    public double getQtdeCarbono() {
        return qtdeCarbono;
    }

    public void setQtdeCarbono(double qtdeCarbono) {
        this.qtdeCarbono = qtdeCarbono;
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

    public double getQtdeVolume() {
        return qtdeVolume;
    }

    public void setQtdeVolume(double qtdeVolume) {
        this.qtdeVolume = qtdeVolume;
    }

    public double getQtdeVolumeMin() {
        return qtdeVolumeMin;
    }

    public void setQtdeVolumeMin(double qtdeVolumeMin) {
        this.qtdeVolumeMin = qtdeVolumeMin;
    }

    public double getQtdeVolumeMed() {
        return qtdeVolumeMed;
    }

    public void setQtdeVolumeMed(double qtdeVolumeMed) {
        this.qtdeVolumeMed = qtdeVolumeMed;
    }

    public double getQtdeVolumeMax() {
        return qtdeVolumeMax;
    }

    public void setQtdeVolumeMax(double qtdeVolumeMax) {
        this.qtdeVolumeMax = qtdeVolumeMax;
    }

}

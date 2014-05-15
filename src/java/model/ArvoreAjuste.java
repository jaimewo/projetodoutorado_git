/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.VariavelArvoreAjusteDao;
import java.util.ArrayList;

/**
 *
 * @author jaime
 */
public class ArvoreAjuste extends Model  {
    
    
    public int id;
    public int idLocal;
    public int numArvoreAjuste;
    public double qtdeBiomassaObs;
    public double qtdeBiomassaEst;
    public double qtdeCarbonoObs;
    public double qtdeCarbonoEst;
    public double qtdeVolumeObs;
    public double qtdeVolumeEst;
        
    public ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste;
    
    public ArvoreAjuste()
    {
        this.idLocal = 0;
        this.numArvoreAjuste = 0;
        this.qtdeBiomassaObs = 0.0;
        this.qtdeBiomassaEst = 0.0;
        this.qtdeCarbonoObs = 0.0;
        this.qtdeCarbonoEst = 0.0;
        this.qtdeVolumeObs = 0.0;
        this.qtdeVolumeEst = 0.0;
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

    public int getNumArvoreAjuste() {
        return numArvoreAjuste;
    }

    public void setNumArvoreAjuste(int numArvoreAjuste) {
        this.numArvoreAjuste = numArvoreAjuste;
    }

    public double getQtdeBiomassaObs() {
        return qtdeBiomassaObs;
    }

    public void setQtdeBiomassaObs(double qtdeBiomassaObs) {
        this.qtdeBiomassaObs = qtdeBiomassaObs;
    }

    public double getQtdeBiomassaEst() {
        return qtdeBiomassaEst;
    }

    public void setQtdeBiomassaEst(double qtdeBiomassaEst) {
        this.qtdeBiomassaEst = qtdeBiomassaEst;
    }

    public double getQtdeCarbonoObs() {
        return qtdeCarbonoObs;
    }

    public void setQtdeCarbonoObs(double qtdeCarbonoObs) {
        this.qtdeCarbonoObs = qtdeCarbonoObs;
    }

    public double getQtdeCarbonoEst() {
        return qtdeCarbonoEst;
    }

    public void setQtdeCarbonoEst(double qtdeCarbonoEst) {
        this.qtdeCarbonoEst = qtdeCarbonoEst;
    }

    public double getQtdeVolumeObs() {
        return qtdeVolumeObs;
    }

    public void setQtdeVolumeObs(double qtdeVolumeObs) {
        this.qtdeVolumeObs = qtdeVolumeObs;
    }

    public double getQtdeVolumeEst() {
        return qtdeVolumeEst;
    }

    public void setQtdeVolumeEst(double qtdeVolumeEst) {
        this.qtdeVolumeEst = qtdeVolumeEst;
    }
 
    public ArrayList<VariavelArvoreAjuste> getVariaveisArvoreAjuste() throws Exception {
        ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();
        VariavelArvoreAjusteDao variaveisArvoreAjusteDao = new VariavelArvoreAjusteDao();
        variaveisArvoreAjuste = variaveisArvoreAjusteDao.listarVariaveisArvoreAjuste(this.id);     
        
        return variaveisArvoreAjuste;
    }   
}

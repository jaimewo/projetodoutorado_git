/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author jaime
 */
public class ArvoreAjusteQuantidade extends Model  {
    
    
    private int id;
    private int idVariavelInteresse;
    private int idMetodoCalculo;
    private double qtdeObs;
    private double qtdeEst;
    private int idArvoreAjuste;
    
    public ArvoreAjusteQuantidade()
    {
        this.idVariavelInteresse = 0;
        this.idMetodoCalculo = 0;
        this.qtdeObs = 0.0;
        this.qtdeEst = 0.0;
        this.idArvoreAjuste = 0;        
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

    public int getIdVariavelInteresse() {
        return idVariavelInteresse;
    }

    public void setIdVariavelInteresse(int idVariavelInteresse) {
        this.idVariavelInteresse = idVariavelInteresse;
    }

    public int getIdMetodoCalculo() {
        return idMetodoCalculo;
    }

    public void setIdMetodoCalculo(int idMetodoCalculo) {
        this.idMetodoCalculo = idMetodoCalculo;
    }

    public double getQtdeObs() {
        return qtdeObs;
    }

    public void setQtdeObs(double qtdeObs) {
        this.qtdeObs = qtdeObs;
    }

    public double getQtdeEst() {
        return qtdeEst;
    }

    public void setQtdeEst(double qtdeEst) {
        this.qtdeEst = qtdeEst;
    }

    public int getIdArvoreAjuste() {
        return idArvoreAjuste;
    }

    public void setIdArvoreAjuste(int idArvoreAjuste) {
        this.idArvoreAjuste = idArvoreAjuste;
    }

}

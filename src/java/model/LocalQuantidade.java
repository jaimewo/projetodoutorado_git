/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jaime
 */
public class LocalQuantidade extends Model  {
    
    
    private int id;
    private int idVariavelInteresse;
    private int idMetodoCalculo;
    private double qtde;
    private int idLocal;
    
    public LocalQuantidade()
    {
        this.idVariavelInteresse = 0;
        this.idMetodoCalculo = 0;
        this.qtde = 0.0;
        this.idLocal = 0;        

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

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }


    
}

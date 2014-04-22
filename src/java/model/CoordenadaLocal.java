/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author paulozeferino
 */
public class CoordenadaLocal extends Model  {
    
    
    public int id;
    public int idLocal;
    public double coordenada;
    
    public CoordenadaLocal()
    {
        this.idLocal = 0;
        this.coordenada = 0;
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

    public double getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(double coordenada) {
        this.coordenada = coordenada;
    }

}

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
public class MunicipioLocal extends Model  {
    
    
    public int id;
    public int idLocal;
    public int idMunicipio;
    public boolean indPrincipal;
    
    public MunicipioLocal()
    {
        this.idLocal = 0;
        this.idMunicipio = 0;
        this.indPrincipal = false;
        
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

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public boolean isIndPrincipal() {
        return indPrincipal;
    }

    public void setIndPrincipal(boolean indPrincipal) {
        this.indPrincipal = indPrincipal;
    }


}

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
public class Vizinho extends Model  {
    
    
    private int id;
    private int idArvore;
    private int numVizinho;
    private double menorDistancia;
    private int numArvoreMenorDistancia;
    private double qtdeObsMenorDistancia;
    
    
    public Vizinho()
    {
        this.idArvore = 0;
        this.numVizinho = 0;
        this.menorDistancia = 0.0;
        this.numArvoreMenorDistancia = 0;
        this.qtdeObsMenorDistancia = 0.0;
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

    public int getIdArvore() {
        return idArvore;
    }

    public void setIdArvore(int idArvore) {
        this.idArvore = idArvore;
    }

    public int getNumVizinho() {
        return numVizinho;
    }

    public void setNumVizinho(int numVizinho) {
        this.numVizinho = numVizinho;
    }

    public double getMenorDistancia() {
        return menorDistancia;
    }

    public void setMenorDistancia(double menorDistancia) {
        this.menorDistancia = menorDistancia;
    }

    public int getNumArvoreMenorDistancia() {
        return numArvoreMenorDistancia;
    }

    public void setNumArvoreMenorDistancia(int numArvoreMenorDistancia) {
        this.numArvoreMenorDistancia = numArvoreMenorDistancia;
    }

    public double getQtdeObsMenorDistancia() {
        return qtdeObsMenorDistancia;
    }

    public void setQtdeObsMenorDistancia(double qtdeObsMenorDistancia) {
        this.qtdeObsMenorDistancia = qtdeObsMenorDistancia;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.EstatisticaAjusteDao;
import dao.EstatisticaInventarioDao;
import dao.LocalDao;
import dao.LocalQuantidadeDao;
import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author jaime
 */
public class EstatisticaAjuste extends Model  {
    
    
    public int id;
    public int idLocal;
    public int idVariavelInteresse;
    public int idMetodoCalculo;
    public double r2;
    public double r2Ajust;
    public double syx;
    public double syxPerc;
    public double aic;
    public double bic;
    public double willmott;
    public double ia;    
    public double somaQuadradoResiduo = 0.0;
    public double somaQuadradoRegressao = 0.0;
    public double somaQuadradoTotais = 0.0;    

    
    public EstatisticaAjuste()
    {
        this.idLocal = 0;
        this.idVariavelInteresse = 0;
        this.idMetodoCalculo = 0;        
        this.r2 = 0.0;
        this.r2Ajust = 0.0;
        this.syx = 0.0;
        this.syxPerc = 0.0;
        this.aic = 0.0;
        this.bic = 0.0;
        this.willmott = 0.0;
        this.ia = 0.0;        
        this.somaQuadradoResiduo = 0.0;
        this.somaQuadradoRegressao = 0.0;
        this.somaQuadradoTotais = 0.0;    
        
    }
    
    public void calcularEstatisticasAjuste(double[] qtdeObs,
                                           double[] qtdeEst, 
                                           int      qtdeVariaveis) throws Exception {
        
//Calcula índices estatísticos (r2, r2Ajust, Syx)
        ArrayList<Double> quadradoResiduo = new ArrayList<>();
        ArrayList<Double> quadradoRegressao = new ArrayList<>();
        ArrayList<Double> quadradoTotais = new ArrayList<>();
        
        double somaNumeradorWillmott = 0.0;
        double somaDenominadorWillmott = 0.0; 
        double valorObs =  0.0;
        double valorEst =  0.0;
        double somaObs = 0.0;
        double mediaObs = 0.0;
        
        for(int i=0;i<qtdeObs.length;i++) {
            somaObs += qtdeObs[i];
            somaQuadradoResiduo += Math.pow((qtdeObs[i] - qtdeEst[i]), 2);
        }        
        
        mediaObs = somaObs / qtdeObs.length;
        
        for(int i=0;i<qtdeObs.length;i++) {
            somaQuadradoRegressao += Math.pow((qtdeEst[i] - mediaObs), 2);
            quadradoTotais.add(Math.pow((qtdeObs[i] - mediaObs), 2));
            somaQuadradoTotais += Math.pow((qtdeObs[i] - mediaObs), 2);
            somaNumeradorWillmott   += Math.pow((qtdeObs[i] - qtdeEst[i]), (double) 2);
            somaDenominadorWillmott += Math.pow(Math.abs(qtdeEst[i] - mediaObs) + Math.abs(qtdeObs[i] - mediaObs), (double) 2);            
            
        }

        r2 = 1-(somaQuadradoResiduo / somaQuadradoTotais);
        r2Ajust = 1 - (1-r2) * ((qtdeObs.length - 1) / (qtdeObs.length - qtdeVariaveis - 1));        

        //Syx = RAIZ (somaQuadradoResiduos/(n-p))
        syx = Math.sqrt(somaQuadradoResiduo/(qtdeObs.length - 2));
        
        //Syx% = (Syx/volumeObsMedio)*100
        syxPerc = (syx / mediaObs) * 100;

        //Calcula aic
        double nSobreP = qtdeObs.length / qtdeVariaveis;
        if (nSobreP<40) {
            aic = -2*((-qtdeObs.length/2)*log( (1/qtdeObs.length)*somaQuadradoResiduo))+2*(qtdeVariaveis+1);
        } else {
            aic = -2*((-qtdeObs.length/2)*log( (1/qtdeObs.length)*somaQuadradoResiduo))+(2*(qtdeVariaveis+1)*qtdeObs.length/(qtdeObs.length-(qtdeVariaveis-1)));
        }
        
        //Calcula bic
        bic =-2*((-qtdeObs.length/2)*log(somaQuadradoResiduo))+log(qtdeObs.length)*qtdeVariaveis;

        //Calcula willmott        
        willmott = 1 - (somaNumeradorWillmott / somaDenominadorWillmott);
        
        //Calcula ia        
        ia = 1 - (somaQuadradoResiduo / somaQuadradoTotais);
        
        EstatisticaAjusteDao estatisticaAjusteDao = new EstatisticaAjusteDao();
        estatisticaAjusteDao.deletarEstatisticaAjusteLocal(this);
        estatisticaAjusteDao.cadastrar(this);

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

    public double getR2() {
        return r2;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }

    public double getR2Ajust() {
        return r2Ajust;
    }

    public void setR2Ajust(double r2Ajust) {
        this.r2Ajust = r2Ajust;
    }

    public double getSyx() {
        return syx;
    }

    public void setSyx(double syx) {
        this.syx = syx;
    }

    public double getSyxPerc() {
        return syxPerc;
    }

    public void setSyxPerc(double syxPerc) {
        this.syxPerc = syxPerc;
    }

    public double getIa() {
        return ia;
    }

    public void setIa(double ia) {
        this.ia = ia;
    }

    public double getAic() {
        return aic;
    }

    public void setAic(double aic) {
        this.aic = aic;
    }

    public double getBic() {
        return bic;
    }

    public void setBic(double bic) {
        this.bic = bic;
    }

    public double getWillmott() {
        return willmott;
    }

    public void setWillmott(double willmott) {
        this.willmott = willmott;
    }

    public double getSomaQuadradoResiduo() {
        return somaQuadradoResiduo;
    }

    public void setSomaQuadradoResiduo(double somaQuadradoResiduo) {
        this.somaQuadradoResiduo = somaQuadradoResiduo;
    }

    public double getSomaQuadradoRegressao() {
        return somaQuadradoRegressao;
    }

    public void setSomaQuadradoRegressao(double somaQuadradoRegressao) {
        this.somaQuadradoRegressao = somaQuadradoRegressao;
    }

    public double getSomaQuadradoTotais() {
        return somaQuadradoTotais;
    }

    public void setSomaQuadradoTotais(double somaQuadradoTotais) {
        this.somaQuadradoTotais = somaQuadradoTotais;
    }


    
}

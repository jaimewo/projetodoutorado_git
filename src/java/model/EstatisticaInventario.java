/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.EstatisticaInventarioDao;
import dao.LocalDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author jaime
 */
public class EstatisticaInventario extends Model  {
    
    
    public int id;
    public int idLocal;
    public int idVariavelInteresse;
    public int idMetodoCalculo;
    public double qtdeMedia;
    public double mediaParcela;
    public double variancia;
    public double desvioPadrao;
    public double varianciaMedia;
    public double erroPadrao;
    public double coeficienteVariacao;
    public double erroAbsoluto;
    public double erroRelativo;
    public double intervaloConfiancaMinMedia;
    public double intervaloConfiancaMaxMedia;
    public double intervaloConfiancaMinTotal;
    public double intervaloConfiancaMaxTotal;

    
    public EstatisticaInventario()
    {
        this.idLocal = 0;
        this.idVariavelInteresse = 0;
        this.idMetodoCalculo = 0;        
        this.qtdeMedia = 0.0;
        this.mediaParcela = 0.0;
        this.variancia = 0.0;
        this.varianciaMedia = 0.0;
        this.erroPadrao = 0.0;
        this.coeficienteVariacao = 0.0;
        this.erroAbsoluto = 0.0;
        this.erroRelativo = 0.0;
        this.intervaloConfiancaMinMedia = 0.0;
        this.intervaloConfiancaMaxMedia = 0.0;
        this.intervaloConfiancaMinTotal = 0.0;
        this.intervaloConfiancaMaxTotal = 0.0;
        
        
    }
    
    public void calcularEstatisticas(Local local, ArrayList<Parcela> parcelasLocal) throws Exception {
    
        int tamanhoAmostra;    
        double qtdeParcelasLocal =  0.0;
        double umMenosF = 0.0;
        double erro = 0.0;        
        double t = 0.0;
        double qtde = 0.0;

        tamanhoAmostra = parcelasLocal.size();
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();        
        
        for(int i=0;i<tamanhoAmostra;i++) {
            qtde = parcelasLocal.get(i).getQtde(idVariavelInteresse, idMetodoCalculo);
            descriptiveStatistics.addValue(qtde);                            
        }
        
        
        //Calcula média, desvio padrão e variância
        mediaParcela = descriptiveStatistics.getMean();
        variancia = descriptiveStatistics.getVariance();
        desvioPadrao = descriptiveStatistics.getStandardDeviation();
        
        //Calcula 1-f=1-N/n  onde N=qtdeParcelasArea n=tamanhoAmostra
        qtdeParcelasLocal =  local.getArea() / local.getAreaParcela();
        umMenosF = 1 - (tamanhoAmostra / qtdeParcelasLocal);
        
        //Calcula erroPadrao = (desvioPadrao / RAIZ(tamanhoAmostra)) * RAIZ(umMenosF)
        erroPadrao = (desvioPadrao / Math.sqrt(tamanhoAmostra) * Math.sqrt(umMenosF));
        
        //Calcula varianciaMedia
        varianciaMedia = (variancia/tamanhoAmostra)*((qtdeParcelasLocal-tamanhoAmostra)/qtdeParcelasLocal);
        
        //Calcula coeficienteVariacao
        coeficienteVariacao = (desvioPadrao / mediaParcela);
        
        //Calcula erro = LE * media  **LE=LimiteErro = 10%
        erro = 0.1 * mediaParcela;
        
        //Constante t. Com 5% (foi usado 0,025 para ser bicaudal) e n=tamanhoAmostra fica:
        int grauLiberdade = tamanhoAmostra-1;
        if (grauLiberdade<=0) {
            grauLiberdade=1;
        }
        t = getT(0.025,grauLiberdade);
 
        erroAbsoluto = erroPadrao * t;
        erroRelativo = (erroAbsoluto / mediaParcela) * 100;
        
        qtdeMedia = qtdeParcelasLocal * mediaParcela;

        intervaloConfiancaMinMedia = mediaParcela - erroAbsoluto;
        intervaloConfiancaMaxMedia = mediaParcela + erroAbsoluto;

        intervaloConfiancaMinTotal = qtdeMedia - qtdeParcelasLocal * erroAbsoluto;
        intervaloConfiancaMaxTotal = qtdeMedia + qtdeParcelasLocal * erroAbsoluto;

        local.setQtde(qtdeMedia, idVariavelInteresse, idMetodoCalculo);
        
        LocalDao localDao = new LocalDao();  
        localDao.updateQtde(local, idVariavelInteresse, idMetodoCalculo);

        EstatisticaInventarioDao estatisticaDao = new EstatisticaInventarioDao();
        estatisticaDao.deletarEstatisticaLocal(this);
        estatisticaDao.cadastrar(this);
    
    }
        
    public static double getT(double nivelDeProbabilidade, double grausDeLiberdade){
       TDistribution tDist = new TDistribution(grausDeLiberdade);
       return Math.abs(tDist.inverseCumulativeProbability(nivelDeProbabilidade));
       //return tDist.inverseCumulativeProbability(nivelDeProbabilidade);
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

    public double getQtdeMedia() {
        return qtdeMedia;
    }

    public void setQtdeMedia(double qtdeMedia) {
        this.qtdeMedia = qtdeMedia;
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

    public double getIntervaloConfiancaMinMedia() {
        return intervaloConfiancaMinMedia;
    }

    public void setIntervaloConfiancaMinMedia(double intervaloConfiancaMinMedia) {
        this.intervaloConfiancaMinMedia = intervaloConfiancaMinMedia;
    }

    public double getIntervaloConfiancaMaxMedia() {
        return intervaloConfiancaMaxMedia;
    }

    public void setIntervaloConfiancaMaxMedia(double intervaloConfiancaMaxMedia) {
        this.intervaloConfiancaMaxMedia = intervaloConfiancaMaxMedia;
    }

    public double getIntervaloConfiancaMinTotal() {
        return intervaloConfiancaMinTotal;
    }

    public void setIntervaloConfiancaMinTotal(double intervaloConfiancaMinTotal) {
        this.intervaloConfiancaMinTotal = intervaloConfiancaMinTotal;
    }

    public double getIntervaloConfiancaMaxTotal() {
        return intervaloConfiancaMaxTotal;
    }

    public void setIntervaloConfiancaMaxTotal(double intervaloConfiancaMaxTotal) {
        this.intervaloConfiancaMaxTotal = intervaloConfiancaMaxTotal;
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


    
}

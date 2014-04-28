/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.LocalDao;
import dao.LocalDetalheBiomassaDao;
import dao.LocalDetalheCarbonoDao;
import dao.LocalDetalheVolumeDao;
import dao.ParcelaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Local;
import model.LocalDetalheBiomassa;
import model.LocalDetalheCarbono;
import model.LocalDetalheVolume;
import model.Parcela;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author jaime
 */
public class calcularComParcela extends HttpServlet {

    double mediaParcela = 0.0;
    double variancia = 0.0;
    double desvioPadrao = 0.0;
    double qtdeParcelasLocal =  0.0;
    double umMenosF = 0.0;
    double erroPadrao = 0.0;
    double coeficienteVariacao = 0.0;
    double erro = 0.0;
    double t = 0.0;
    double erroAbsoluto = 0.0;
    double erroRelativo = 0.0;
    double intervaloConfiancaMinMedia = 0.0;
    double intervaloConfiancaMaxMedia = 0.0;
    double mediaLocal = 0.0;
    double intervaloConfiancaMinTotal = 0.0;
    double intervaloConfiancaMaxTotal = 0.0;
    int tamanhoAmostra;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
            String idLocalStr = request.getParameter("id");
            LocalDao controller = new LocalDao();
            Local local = controller.getLocal(idLocalStr);
            request.setAttribute("local", local );
            
            List<Parcela> parcelas = new ArrayList<Parcela>();
            ParcelaDao parcelaDao = new ParcelaDao();
            parcelas = parcelaDao.listarParcelas(local);
            
            LocalDetalheBiomassa localDetalheBiomassa = calculaBiomassaComParcela(local,parcelas);
            request.setAttribute("localDetalheBiomassa", localDetalheBiomassa);
            

            request.getRequestDispatcher("??????.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar Local";
    }

    public LocalDetalheBiomassa calculaBiomassaComParcela(Local local,List<Parcela> parcelas) throws Exception {
    
        LocalDetalheBiomassa localDetalheBiomassa = new LocalDetalheBiomassa();
    
        tamanhoAmostra = parcelas.size();
        DescriptiveStatistics estatistica = new DescriptiveStatistics();        
        
        for(int i=0;i<tamanhoAmostra;i++) {
            estatistica.addValue(parcelas.get(i).getQtdeBiomassa());            
        }
        
        calculaEstatisticas(estatistica,local.getArea(),local.getAreaParcela());
    
        local.setQtdeBiomassa(mediaLocal);
    
        LocalDao localDao = new LocalDao();
        localDao.update(local);
    
        LocalDetalheBiomassaDao localDetalheBiomassaDao = new LocalDetalheBiomassaDao();
        localDetalheBiomassa = localDetalheBiomassaDao.getLocalDetalheBiomassa(local.getId());

        localDetalheBiomassa.setCoeficienteVariacao(coeficienteVariacao);
        localDetalheBiomassa.setDesvioPadrao(desvioPadrao);
        localDetalheBiomassa.setErroAbsoluto(erroAbsoluto);
        localDetalheBiomassa.setErroRelativo(erroRelativo);
        localDetalheBiomassa.setIntervaloConfiancaMaxParcela(intervaloConfiancaMaxTotal);
        localDetalheBiomassa.setIntervaloConfiancaMinParcela(intervaloConfiancaMinTotal);
        localDetalheBiomassa.setMediaParcela(mediaLocal);
        localDetalheBiomassa.setQtdeBiomassaMax(intervaloConfiancaMinTotal);
        localDetalheBiomassa.setQtdeBiomassaMed(mediaLocal);
        localDetalheBiomassa.setQtdeBiomassaMin(intervaloConfiancaMinTotal);
        localDetalheBiomassa.setVariancia(variancia);
        localDetalheBiomassa.setVarianciaMedia(variancia);

        localDetalheBiomassaDao.update(localDetalheBiomassa);
    
        return localDetalheBiomassa;
    }


    public LocalDetalheCarbono calculaCarbonoComParcela(Local local,List<Parcela> parcelas) throws Exception {
    
        LocalDetalheCarbono localDetalheCarbono = new LocalDetalheCarbono();
    
        tamanhoAmostra = parcelas.size();
        DescriptiveStatistics estatistica = new DescriptiveStatistics();        
        
        for(int i=0;i<tamanhoAmostra;i++) {
            estatistica.addValue(parcelas.get(i).getQtdeCarbono());            
        }
        
        calculaEstatisticas(estatistica,local.getArea(),local.getAreaParcela());
    
        local.setQtdeCarbono(mediaLocal);
    
        LocalDao localDao = new LocalDao();
        localDao.update(local);
    
        LocalDetalheCarbonoDao localDetalheCarbonoDao = new LocalDetalheCarbonoDao();
        localDetalheCarbono = localDetalheCarbonoDao.getLocalDetalheCarbono(local.getId());

        localDetalheCarbono.setCoeficienteVariacao(coeficienteVariacao);
        localDetalheCarbono.setDesvioPadrao(desvioPadrao);
        localDetalheCarbono.setErroAbsoluto(erroAbsoluto);
        localDetalheCarbono.setErroRelativo(erroRelativo);
        localDetalheCarbono.setIntervaloConfiancaMaxParcela(intervaloConfiancaMaxMedia);
        localDetalheCarbono.setIntervaloConfiancaMinParcela(intervaloConfiancaMinMedia);
        localDetalheCarbono.setMediaParcela(mediaLocal);
        localDetalheCarbono.setQtdeCarbonoMax(intervaloConfiancaMinTotal);
        localDetalheCarbono.setQtdeCarbonoMed(mediaLocal);
        localDetalheCarbono.setQtdeCarbonoMin(intervaloConfiancaMinTotal);
        localDetalheCarbono.setVariancia(variancia);
        localDetalheCarbono.setVarianciaMedia(variancia);

        localDetalheCarbonoDao.update(localDetalheCarbono);
    
        return localDetalheCarbono;
    }

    public LocalDetalheVolume calculaVolumeComParcela(Local local,List<Parcela> parcelas) throws Exception {
    
        LocalDetalheVolume localDetalheVolume = new LocalDetalheVolume();
    
        tamanhoAmostra = parcelas.size();
        DescriptiveStatistics estatistica = new DescriptiveStatistics();        
        
        for(int i=0;i<tamanhoAmostra;i++) {
            estatistica.addValue(parcelas.get(i).getQtdeVolume());            
        }
        
        calculaEstatisticas(estatistica,local.getArea(),local.getAreaParcela());
    
        local.setQtdeVolume(mediaLocal);
    
        LocalDao localDao = new LocalDao();
        localDao.update(local);
    
        LocalDetalheVolumeDao localDetalheVolumeDao = new LocalDetalheVolumeDao();
        localDetalheVolume = localDetalheVolumeDao.getLocalDetalheVolume(local.getId());

        localDetalheVolume.setCoeficienteVariacao(coeficienteVariacao);
        localDetalheVolume.setDesvioPadrao(desvioPadrao);
        localDetalheVolume.setErroAbsoluto(erroAbsoluto);
        localDetalheVolume.setErroRelativo(erroRelativo);
        localDetalheVolume.setIntervaloConfiancaMaxParcela(intervaloConfiancaMaxTotal);
        localDetalheVolume.setIntervaloConfiancaMinParcela(intervaloConfiancaMinTotal);
        localDetalheVolume.setMediaParcela(mediaLocal);
        localDetalheVolume.setQtdeVolumeMax(intervaloConfiancaMinTotal);
        localDetalheVolume.setQtdeVolumeMed(mediaLocal);
        localDetalheVolume.setQtdeVolumeMin(intervaloConfiancaMinTotal);
        localDetalheVolume.setVariancia(variancia);
        localDetalheVolume.setVarianciaMedia(variancia);

        localDetalheVolumeDao.update(localDetalheVolume);
    
        return localDetalheVolume;
    }
    
    public void calculaEstatisticas(DescriptiveStatistics estatistica,double area, double areaParcela) throws Exception {
        
        //Calcula média, desvio padrão e variância
        mediaParcela = estatistica.getMean();
        variancia = estatistica.getVariance();
        desvioPadrao = estatistica.getStandardDeviation();
        
        //Calcula 1-f=1-N/n  onde N=qtdeParcelasArea n=tamanhoAmostra
        qtdeParcelasLocal =  area / areaParcela;
        umMenosF = 1 - (tamanhoAmostra / qtdeParcelasLocal);
        
        //Calcula erroPadrao = (desvioPadrao / RAIZ(tamanhoAmostra)) * RAIZ(umMenosF)
        erroPadrao = (desvioPadrao / Math.sqrt(tamanhoAmostra) * Math.sqrt(umMenosF));
        
        //Calcula coeficienteVariacao
        coeficienteVariacao = (desvioPadrao / mediaParcela);
        
        //Calcula erro = LE * media  **LE=LimiteErro = 10%
        erro = 0.1 * mediaParcela;
        
        //Constante t. Com 5% (foi usado 0,025 para ser bicaudal) e n=21 fica:
        t = getT(0.025,tamanhoAmostra);
 
        erroAbsoluto = erroPadrao * t;
        erroRelativo = (erroAbsoluto / mediaParcela) * 100;
        
        intervaloConfiancaMinMedia = mediaParcela - erroAbsoluto;
        intervaloConfiancaMaxMedia = mediaParcela + erroAbsoluto;
        
        mediaLocal = qtdeParcelasLocal * mediaParcela;

        intervaloConfiancaMinTotal = mediaLocal - qtdeParcelasLocal * erroAbsoluto;
        intervaloConfiancaMaxTotal = mediaLocal + qtdeParcelasLocal * erroAbsoluto;

    }
        
    public static double getT(double nivelDeProbabilidade, double grausDeLiberdade){
       TDistribution tDist = new TDistribution(grausDeLiberdade);
       //return Math.abs(tDist.inverseCumulativeProbability(nivelDeProbabilidade));
       return tDist.inverseCumulativeProbability(nivelDeProbabilidade);
    }
 
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.LocalDao;
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
import model.Parcela;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.distribution.TDistribution;

/**
 *
 * @author jaime
 */
public class calcularComParcela extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
             String idLocalStr = request.getParameter("id");
             LocalDao controller = new LocalDao();
             Local local = controller.getLocal(idLocalStr);
             request.setAttribute("local", local );
             
            LocalDetalheBiomassa localDetalheBiomassa = calculaBiomassaComParcela(local);
            
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

public LocalDetalheBiomassa calculaBiomassaComParcela(Local local) throws Exception {
    
    LocalDetalheBiomassa localDetalheBiomassa = new LocalDetalheBiomassa();
    
    List<Parcela> parcelas = new ArrayList<Parcela>();
    ParcelaDao parcelaDao = new ParcelaDao();
    parcelas = parcelaDao.listarParcelas(local);
    
    int tamanhoAmostra = parcelas.size();
    DescriptiveStatistics estatistica = new DescriptiveStatistics();        
        
    for(int i=0;i<tamanhoAmostra;i++) {
        estatistica.addValue(parcelas.get(i).getQtdeBiomassa());            
    }
        
    //Calcula média, desvio padrão e variância
    double media = estatistica.getMean();
    double variancia = estatistica.getVariance();
    double desvioPadrao = estatistica.getStandardDeviation();
        
    //Calcula 1-f=1-N/n  onde N=qtdeParcelasArea n=tamanhoAmostra
    double qtdeParcelasTalhao =  local.getArea() / local.getAreaParcela();
    double umMenosF = 1 - (tamanhoAmostra / qtdeParcelasTalhao);
        
    //Calcula erroPadrao = (desvioPadrao / RAIZ(tamanhoAmostra)) * RAIZ(umMenosF)
    double erroPadrao = (desvioPadrao / Math.sqrt(tamanhoAmostra) * Math.sqrt(umMenosF));
        
    //Calcula coeficienteVariacao
    double coeficienteVariacao = (desvioPadrao / media);
        
    //Calcula erro = LE * media  **LE=LimiteErro = 10%
    double erro = 0.1 * media;
        
    //Constante t. Com 5% (foi usado 0,025 para ser bicaudal) e n=21 fica:
    double t = getT(0.025,tamanhoAmostra);
 
    //System.out.println("T="+t);
        
        
    double erroAbsoluto = erroPadrao * t;
    double erroRelativo = (erroAbsoluto / media) * 100;
        
    double intervaloConfiancaMinMedia = media - erroAbsoluto;
    double intervaloConfiancaMaxMedia = media + erroAbsoluto;
        
    double mediaTalhao = qtdeParcelasTalhao * media;
        
    double intervaloConfiancaMinTotal = mediaTalhao - qtdeParcelasTalhao * erroAbsoluto;
    double intervaloConfiancaMaxTotal = mediaTalhao + qtdeParcelasTalhao * erroAbsoluto;
        
        volumeLocal.setVolumeTotal(mediaTalhao);
        volumeLocal.setVolumeMinimo(intervaloConfiancaMinTotal);
        volumeLocal.setVolumeMaximo(intervaloConfiancaMaxTotal);
    
    localDetalheBiomassa.set
    
    
    
    return localDetalheBiomassa;
}
    public static double getT(double nivelDeProbabilidade, double grausDeLiberdade){
       TDistribution tDist = new TDistribution(grausDeLiberdade);
       //return Math.abs(tDist.inverseCumulativeProbability(nivelDeProbabilidade));
       return tDist.inverseCumulativeProbability(nivelDeProbabilidade);
    }
 
}

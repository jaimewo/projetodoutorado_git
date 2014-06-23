/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;




import dao.ArvoreDao;
import dao.LocalDao;
import dao.EstatisticaDao;
import dao.ParcelaDao;
import dao.TrabalhoCientificoDao;
import dao.VariavelInteresseDao;
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
import model.Arvore;
import model.Equacao;
import model.Local;
import model.Estatistica;
import model.Parcela;
import model.TrabalhoCientifico;
import model.VariavelArvore;
import model.VariavelInteresse;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.nfunk.jep.*;


/**
 *
 * @author jaime
 */
public class calcularLocal extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
            String idLocalStr = request.getParameter("id");
                
          //idLocalStr = "1";
          //int idTipoEstimativa = 2; //Informados valores das Parcelas
          //int idTipoEstimativa = 3; //Informados valores das Árvores
            
            LocalDao localDao = new LocalDao();
            Local local = localDao.getLocal(Integer.parseInt(idLocalStr));
          //local.setIdTipoEstimativa(idTipoEstimativa);
            
            if (local.getIdTipoEstimativa()==3) {
                ArrayList<Parcela> parcelasLocal = new ArrayList<Parcela>();
                parcelasLocal = local.getParcelas();
            
                for (Parcela parcela : parcelasLocal) {
                    parcela.calculaBiomassa(local);
                    parcela.calculaCarbono(local);
                    parcela.calculaVolume(local);
                }
            }
            
//            local.calculaBiomassaEstatisticas();
//            local.calculaCarbonoEstatisticas();
//            local.calculaVolumeEstatisticas();

            request.setAttribute("local", local );          

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
            Logger.getLogger(calcularLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(calcularLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "CalcularComArvores";
    }

}

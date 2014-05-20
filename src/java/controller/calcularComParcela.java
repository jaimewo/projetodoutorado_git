/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.LocalDao;
import dao.EstatisticaDao;
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
import model.Estatistica;
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


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
            String idLocalStr = request.getParameter("id");
            LocalDao controller = new LocalDao();
            Local local = controller.getLocal(Integer.parseInt(idLocalStr));
            request.setAttribute("local", local );
            
            List<Parcela> parcelas = new ArrayList<Parcela>();
            ParcelaDao parcelaDao = new ParcelaDao();
            parcelas = parcelaDao.listarParcelas(local.getId());
            
            EstatisticaDao estatisticaDao = new EstatisticaDao();
            estatisticaDao.deletarEstatisticaLocal(local.getId());

            Estatistica estatisticaBiomassa = new Estatistica();
            estatisticaBiomassa.setIdLocal(local.getId());
            estatisticaBiomassa.setIdVariavelInteresse(1); //Biomassa
            estatisticaBiomassa.calcularEstatisticas(local, parcelas);
            request.setAttribute("estatisticaBiomassa", estatisticaBiomassa);

            Estatistica estatisticaCarbono = new Estatistica();
            estatisticaCarbono.setIdLocal(local.getId());
            estatisticaCarbono.setIdVariavelInteresse(2); //Carbono
            estatisticaCarbono.calcularEstatisticas(local, parcelas);
            request.setAttribute("estatisticaCarbono", estatisticaCarbono);

            Estatistica estatisticaVolume = new Estatistica();
            estatisticaVolume.setIdLocal(local.getId());
            estatisticaVolume.setIdVariavelInteresse(3); //Volume
            estatisticaVolume.calcularEstatisticas(local, parcelas);
            request.setAttribute("estatisticaVolume", estatisticaVolume);

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
        return "CalcularComParcela";
    }

}

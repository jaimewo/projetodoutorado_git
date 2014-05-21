/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.EstatisticaDao;
import dao.LocalDao;
import dao.ParcelaDao;
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
import model.Estatistica;
import model.Local;
import model.Parcela;
import model.VariavelInteresse;
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
            
            List<Parcela> parcelas = new ArrayList<Parcela>();
            ParcelaDao parcelaDao = new ParcelaDao();
            parcelas = parcelaDao.listarParcelas(local.getId());
            
            EstatisticaDao estatisticaDao = new EstatisticaDao();
            estatisticaDao.deletarEstatisticaLocal(local.getId());

            List<VariavelInteresse> variaveisInteresse = new ArrayList<VariavelInteresse>();
            VariavelInteresseDao variavelInteresseDao = new VariavelInteresseDao();
            variaveisInteresse = variavelInteresseDao.listarVariaveisInteresse();
            
            for (VariavelInteresse variavelInteresse: variaveisInteresse) {

                Estatistica estatistica = new Estatistica();
                estatistica.setIdLocal(local.getId());
                estatistica.setIdVariavelInteresse(variavelInteresse.getId());
                estatistica.calcularEstatisticas(local, parcelas);
                
            }

            local = controller.getLocal(Integer.parseInt(idLocalStr));
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

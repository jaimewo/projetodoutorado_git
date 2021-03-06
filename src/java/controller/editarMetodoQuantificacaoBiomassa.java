/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MetodoQuantificacaoBiomassaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MetodoQuantificacaoBiomassa;

/**
 *
 * @author paulozeferino
 */
public class editarMetodoQuantificacaoBiomassa extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String idMetodoQuantificacaoBiomassa = request.getParameter("id");
            MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa = (MetodoQuantificacaoBiomassa) request.getAttribute("metodoQuantificacaoBiomassa");
            if(metodoQuantificacaoBiomassa == null){             
               MetodoQuantificacaoBiomassaDao controller = new MetodoQuantificacaoBiomassaDao();
               MetodoQuantificacaoBiomassa objeto_metodoQuantificacaoBiomassa = controller.getMetodoQuantificacaoBiomassa(idMetodoQuantificacaoBiomassa);
               request.setAttribute("metodoQuantificacaoBiomassa", objeto_metodoQuantificacaoBiomassa );
            }else {
               request.setAttribute("metodoQuantificacaoBiomassa", metodoQuantificacaoBiomassa );
            }            
            request.getRequestDispatcher("editarMetodoQuantificacaoBiomassa.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar MetodoQuantificacaoBiomassa";
    }
}

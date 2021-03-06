/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MetodoQuantificacaoBiomassaDao;
import dao.BiomaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MetodoQuantificacaoBiomassa;
import model.Bioma;

/**
 *
 * @author paulozeferino
 */
public class novoMetodoQuantificacaoBiomassa extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
        } finally { 
            MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa = (MetodoQuantificacaoBiomassa) request.getAttribute("metodoQuantificacaoBiomassa");
            if(metodoQuantificacaoBiomassa == null) {
               request.setAttribute("metodoQuantificacaoBiomassa", new MetodoQuantificacaoBiomassa());
            } else {
                request.setAttribute("metodoQuantificacaoBiomassa", metodoQuantificacaoBiomassa);
            }
            request.getRequestDispatcher("novoMetodoQuantificacaoBiomassa.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Novo MetodoQuantificacaoBiomassa";
    }
}

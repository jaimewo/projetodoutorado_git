/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MetodoQuantificacaoCarbonoDao;
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
import model.MetodoQuantificacaoCarbono;
import model.Bioma;

/**
 *
 * @author paulozeferino
 */
public class novoMetodoQuantificacaoCarbono extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
        } finally { 
            MetodoQuantificacaoCarbono metodoQuantificacaoCarbono = (MetodoQuantificacaoCarbono) request.getAttribute("metodoQuantificacaoCarbono");
            if(metodoQuantificacaoCarbono == null) {
               request.setAttribute("metodoQuantificacaoCarbono", new MetodoQuantificacaoCarbono());
            } else {
                request.setAttribute("metodoQuantificacaoCarbono", metodoQuantificacaoCarbono);
            }
            request.getRequestDispatcher("novoMetodoQuantificacaoCarbono.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Novo MetodoQuantificacaoCarbono";
    }
}

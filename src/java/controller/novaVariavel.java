/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VariavelDao;
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
import model.Variavel;
import model.Bioma;

/**
 *
 * @author paulozeferino
 */
public class novaVariavel extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
        } finally { 
             Variavel variavel = (Variavel) request.getAttribute("variavel");
             if(variavel == null)
                request.setAttribute("variavel", new Variavel());
             else
                 request.setAttribute("variavel",variavel);
             
             request.getRequestDispatcher("novaVariavel.jsp").forward(request, response);
             
       
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novaVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novaVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Nova Variavel";
    }
}

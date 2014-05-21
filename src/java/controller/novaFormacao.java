/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FormacaoDao;
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
import model.Formacao;
import model.Bioma;

/**
 *
 * @author paulozeferino
 */
public class novaFormacao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            BiomaDao objeto_bioma_dao = new BiomaDao();
            List<Bioma> biomas = objeto_bioma_dao.listarBiomas();
            request.setAttribute("biomas", biomas);
            
        } finally { 
            
            Formacao f = (Formacao) request.getAttribute("formacao");
            if(f == null) {
               request.setAttribute("formacao", new Formacao());
            } else {
                request.setAttribute("formacao", f);
            }
            request.getRequestDispatcher("novaFormacao.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novaFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novaFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Nova Formacao";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiomaDao;
import dao.VariavelDao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autor;
import model.Bioma;
import model.Equacao;
import model.Variavel;

/**
 *
 * @author jaime
 */
public class novaEquacao2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
                VariavelDao variavel_dao = new VariavelDao();
            List<Variavel> variaveis = variavel_dao.listarVariaveis();
            request.setAttribute("variaveis", variaveis);
            
            
        } finally { 
            Equacao equacao = (Equacao) request.getAttribute("equacao");
            if(equacao == null) {
               request.setAttribute("equacao", new Autor());
            } else {
               request.setAttribute("equacao", equacao);
            }
            request.getRequestDispatcher("novaEquacao2.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Novo Autor";
    }
}

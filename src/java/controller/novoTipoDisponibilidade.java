/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoDisponibilidadeDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoDisponibilidade;

/**
 *
 * @author paulozeferino
 */
public class novoTipoDisponibilidade extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            
        } finally { 
            TipoDisponibilidade tipoDisponibilidade = (TipoDisponibilidade) request.getAttribute("tipoDisponibilidade");
            if(tipoDisponibilidade == null) {
               request.setAttribute("tipoDisponibilidade", new TipoDisponibilidade());
            } else {
                request.setAttribute("tipoDisponibilidade", tipoDisponibilidade);
            }
            request.getRequestDispatcher("novoTipoDisponibilidade.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Novo TipoDisponibilidade";
    }
}

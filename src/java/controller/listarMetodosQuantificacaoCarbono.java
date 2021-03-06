/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MetodoQuantificacaoCarbonoDao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MetodoQuantificacaoCarbono;

/**
 *
 * @author paulozeferino
 */
public class listarMetodosQuantificacaoCarbono extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            MetodoQuantificacaoCarbonoDao controller = new MetodoQuantificacaoCarbonoDao();
            List<MetodoQuantificacaoCarbono> metodosQuantificacaoCarbono =  controller.listarMetodosQuantificacaoCarbono();
            request.setAttribute("metodosQuantificacaoCarbono", metodosQuantificacaoCarbono);
        } finally { 
            request.getRequestDispatcher("listarMetodosQuantificacaoCarbono.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarMetodosQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarMetodosQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Action Listar MetodosQuantificacaoCarbono";
    }// </editor-fold>
}

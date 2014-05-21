/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EspacamentoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Espacamento;

/**
 *
 * @author paulozeferino
 */
public class editarEspacamento extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idEspacamento = request.getParameter("id");
             Espacamento espacamento = (Espacamento) request.getAttribute("espacamento");
             if(espacamento == null){             
                EspacamentoDao controller = new EspacamentoDao();
                Espacamento objeto_espacamento = controller.getEspacamento(idEspacamento);
                request.setAttribute("espacamento", objeto_espacamento );
             }else {
                 request.setAttribute("espacamento", espacamento );
             }             
             request.getRequestDispatcher("editarEspacamento.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar Espacamento";
    }
}

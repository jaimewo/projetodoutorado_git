/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MetodoQuantificacaoCarbonoDao;
import java.io.IOException;
import java.sql.SQLException;
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
public class editarMetodoQuantificacaoCarbono extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idMetodoQuantificacaoCarbono = request.getParameter("id");
             MetodoQuantificacaoCarbonoDao controller = new MetodoQuantificacaoCarbonoDao();
             MetodoQuantificacaoCarbono objeto_metodoQuantificacaoCarbono = controller.getMetodoQuantificacaoCarbono(idMetodoQuantificacaoCarbono);
             request.setAttribute("metodoQuantificacaoCarbono", objeto_metodoQuantificacaoCarbono );
             request.getRequestDispatcher("editarMetodoQuantificacaoCarbono.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar MetodoQuantificacaoCarbono";
    }
}

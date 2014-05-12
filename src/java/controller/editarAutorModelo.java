/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AutorModeloDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AutorModelo;

/**
 *
 * @author jaime
 */
public class editarAutorModelo extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idAutorModelo = request.getParameter("id");
             AutorModelo autorModelo = (AutorModelo) request.getAttribute("autorModelo");
             if(autorModelo == null){
                AutorModeloDao controller = new AutorModeloDao();
                AutorModelo objeto_autorModelo = controller.getAutorModelo(idAutorModelo);
                request.setAttribute("autorModelo", objeto_autorModelo );
             } else {
                 request.setAttribute("autorModelo", autorModelo );
             }                
             request.getRequestDispatcher("editarAutorModelo.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar AutorModelo";
    }
}

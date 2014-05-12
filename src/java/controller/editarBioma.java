/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiomaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bioma;

/**
 *
 * @author paulozeferino
 */
public class editarBioma extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idBioma = request.getParameter("id");
             Bioma bioma = (Bioma) request.getAttribute("bioma");
             if(bioma == null){
             BiomaDao controller = new BiomaDao();
             Bioma objeto_bioma = controller.getBioma(idBioma);
             request.setAttribute("bioma", objeto_bioma );
             }else
             {
                 request.setAttribute("bioma", bioma );
             }
             request.getRequestDispatcher("editarBioma.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarBioma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarBioma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar Bioma";
    }
}

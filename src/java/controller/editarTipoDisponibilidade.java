/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoDisponibilidadeDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoDisponibilidade;

/**
 *
 * @author paulozeferino
 */
public class editarTipoDisponibilidade extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String idTipoDisponibilidade = request.getParameter("id");
            TipoDisponibilidade tipoDisponibilidade = (TipoDisponibilidade) request.getAttribute("tipoDisponibilidade");
            if(tipoDisponibilidade == null){               
               TipoDisponibilidadeDao controller = new TipoDisponibilidadeDao();
               TipoDisponibilidade objeto_tipoDisponibilidade = controller.getTipoDisponibilidade(idTipoDisponibilidade);
               request.setAttribute("tipoDisponibilidade", objeto_tipoDisponibilidade );
            }else {
               request.setAttribute("tipoDisponibilidade", tipoDisponibilidade );
            }             
            request.getRequestDispatcher("editarTipoDisponibilidade.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar TipoDisponibilidade";
    }
}

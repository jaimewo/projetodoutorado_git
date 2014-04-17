package controller;

import dao.TrabalhoCientificoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TrabalhoCientifico;

/**
 *
 * @author paulozeferino
 */
public class excluirTrabalhoCientifico extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String id = request.getParameter("id");
             TrabalhoCientificoDao controller = new TrabalhoCientificoDao();
             TrabalhoCientifico objeto_trabalhoCientifico = controller.getTrabalhoCientifico(id);
             controller.deletar(objeto_trabalhoCientifico);
              RequestDispatcher r = request.getRequestDispatcher("/listarTrabalhoCientificoes");    
             r.forward( request, response );  
        } finally {            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Excluir";
    }// </editor-fold>
}

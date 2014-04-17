package controller;

import dao.VariavelDao;
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
import model.Variavel;

/**
 *
 * @author paulozeferino
 */
public class excluirVariavel extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idVariavel = request.getParameter("id");
             VariavelDao controller = new VariavelDao();
             Variavel objeto_variavel = controller.getVariavel(idVariavel);
             controller.deletar(objeto_variavel);
              RequestDispatcher r = request.getRequestDispatcher("/listarVariaveis");    
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
            Logger.getLogger(excluirVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Excluir";
    }// </editor-fold>
}

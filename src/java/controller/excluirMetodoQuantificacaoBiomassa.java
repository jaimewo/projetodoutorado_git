package controller;

import dao.MetodoQuantificacaoBiomassaDao;
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
import model.MetodoQuantificacaoBiomassa;

/**
 *
 * @author paulozeferino
 */
public class excluirMetodoQuantificacaoBiomassa extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idMetodoQuantificacaoBiomassa = request.getParameter("id");
             MetodoQuantificacaoBiomassaDao controller = new MetodoQuantificacaoBiomassaDao();
             MetodoQuantificacaoBiomassa objeto_metodoQuantificacaoBiomassa = controller.getMetodoQuantificacaoBiomassa(idMetodoQuantificacaoBiomassa);
             controller.deletar(objeto_metodoQuantificacaoBiomassa);
              RequestDispatcher r = request.getRequestDispatcher("/listarMetodosQuantificacaoBiomassa");    
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
            Logger.getLogger(excluirMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Excluir";
    }// </editor-fold>
}

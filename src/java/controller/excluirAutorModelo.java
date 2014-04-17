package controller;

import dao.AutorModeloDao;
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
import model.AutorModelo;

/**
 *
 * @author paulozeferino
 */
public class excluirAutorModelo extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idAutorModelo = request.getParameter("id");
             AutorModeloDao controller = new AutorModeloDao();
             AutorModelo objeto_autorModelo = controller.getAutorModelo(idAutorModelo);
             controller.deletar(objeto_autorModelo);
              RequestDispatcher r = request.getRequestDispatcher("/listarAutoresModelo");    
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
            Logger.getLogger(excluirAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Excluir";
    }// </editor-fold>
}

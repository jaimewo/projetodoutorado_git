package controller;

import dao.EspacamentoDao;
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
import model.Espacamento;

/**
 *
 * @author paulozeferino
 */
public class excluirEspacamento extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idEspacamento = request.getParameter("id");
             EspacamentoDao controller = new EspacamentoDao();
             Espacamento objeto_espacamento = controller.getEspacamento(idEspacamento);
             controller.deletar(objeto_espacamento);
              RequestDispatcher r = request.getRequestDispatcher("/listarEspacamentos");    
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
            Logger.getLogger(excluirEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Excluir";
    }// </editor-fold>
}

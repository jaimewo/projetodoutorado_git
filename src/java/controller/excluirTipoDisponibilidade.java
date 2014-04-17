package controller;

import dao.TipoDisponibilidadeDao;
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
import model.TipoDisponibilidade;

/**
 *
 * @author paulozeferino
 */
public class excluirTipoDisponibilidade extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idTipoDisponibilidade = request.getParameter("id");
             TipoDisponibilidadeDao controller = new TipoDisponibilidadeDao();
             TipoDisponibilidade objeto_tipoDisponibilidade = controller.getTipoDisponibilidade(idTipoDisponibilidade);
             controller.deletar(objeto_tipoDisponibilidade);
              RequestDispatcher r = request.getRequestDispatcher("/listarTiposDisponibilidade");    
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
            Logger.getLogger(excluirTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Excluir";
    }// </editor-fold>
}

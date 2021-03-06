package controller;

import dao.MetodoQuantificacaoCarbonoDao;
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
import model.MetodoQuantificacaoCarbono;

/**
 *
 * @author paulozeferino
 */
public class excluirMetodoQuantificacaoCarbono extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
             String idMetodoQuantificacaoCarbono = request.getParameter("id");
             MetodoQuantificacaoCarbonoDao controller = new MetodoQuantificacaoCarbonoDao();
             MetodoQuantificacaoCarbono objeto_metodoQuantificacaoCarbono = controller.getMetodoQuantificacaoCarbono(idMetodoQuantificacaoCarbono);
             controller.deletar(objeto_metodoQuantificacaoCarbono);
              RequestDispatcher r = request.getRequestDispatcher("/listarMetodosQuantificacaoCarbono");    
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
            Logger.getLogger(excluirMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(excluirMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Excluir";
    }// </editor-fold>
}

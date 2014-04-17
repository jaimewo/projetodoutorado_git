package controller;

import dao.AutorModeloDao;
import java.io.IOException;
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
public class createAutorModelo extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String nome = request.getParameter("autorModelo[nome]");
                AutorModelo autorModelo = new AutorModelo();
                autorModelo.setNome(nome);
                if(autorModelo.eh_valido())
                {
                    AutorModeloDao objeto_dao_autorModelo = new AutorModeloDao();
                    objeto_dao_autorModelo.cadastrar(autorModelo);
                    RequestDispatcher r = request.getRequestDispatcher("/listarAutoresModelo");   
                    request.setAttribute("mensagem", "Autor adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoAutorModelo");    
                    request.setAttribute("autorModelo", autorModelo);
                    request.setAttribute("erros", autorModelo.getErrors());
                    r.forward( request, response );  
                }
        } finally {            
            
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar AutorModelo";
    }// </editor-fold>
}

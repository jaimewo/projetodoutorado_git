package controller;

import dao.VariavelDao;
import java.io.IOException;
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
public class createVariavel extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String sigla = request.getParameter("variavel[sigla]");
                String nome = request.getParameter("variavel[nome]");
                Variavel variavel = new Variavel();
                variavel.setSigla(sigla);
                variavel.setNome(nome);
                if(variavel.eh_valido())
                {
                    VariavelDao objeto_dao_variavel = new VariavelDao();
                    objeto_dao_variavel.cadastrar(variavel);
                    RequestDispatcher r = request.getRequestDispatcher("/listarVariaveis");   
                    request.setAttribute("mensagem", "Variável adicionada com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novaVariavel");    
                    request.setAttribute("variavel", variavel);
                    request.setAttribute("erros", variavel.getErrors());
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
            Logger.getLogger(createVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Variavel";
    }// </editor-fold>
}

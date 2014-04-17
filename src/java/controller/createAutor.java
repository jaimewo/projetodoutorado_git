package controller;

import dao.AutorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autor;

/**
 *
 * @author jaime
 */
public class createAutor extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String nome = request.getParameter("autor[nome]");
                Autor autor = new Autor();
                autor.setNome(nome);
                if(autor.eh_valido())
                {
                    AutorDao objeto_dao_autor = new AutorDao();
                    objeto_dao_autor.cadastrar(autor);
                    RequestDispatcher r = request.getRequestDispatcher("/listarAutores");    
                    request.setAttribute("mensagem", "Autor adicionado com sucesso!");                    
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoAutor");    
                    request.setAttribute("autor", autor);
                    request.setAttribute("erros", autor.getErrors());
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
            Logger.getLogger(createAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Autor";
    }// </editor-fold>
}

package controller;

import dao.BiomaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bioma;

/**
 *
 * @author paulozeferino
 */
public class createBioma extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String descricao = request.getParameter("bioma[descricao]");
                Bioma bioma = new Bioma();
                bioma.setDescricao(descricao);
                if(bioma.eh_valido())
                {
                    BiomaDao objeto_dao_bioma = new BiomaDao();
                    objeto_dao_bioma.cadastrar(bioma);
                    RequestDispatcher r = request.getRequestDispatcher("/listarBiomas");  
                    request.setAttribute("mensagem", "Bioma adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoBioma");    
                    request.setAttribute("bioma", bioma);
                    request.setAttribute("erros", bioma.getErrors());
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
            Logger.getLogger(createBioma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createBioma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Bioma";
    }// </editor-fold>
}

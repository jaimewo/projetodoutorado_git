package controller;

import dao.EquacaoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equacao;

/**
 *
 * @author jaime
 */
public class createModelo extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String expressaoModelo = request.getParameter("equacao[expressaoModelo]");
                Equacao equacao = new Equacao();
                equacao.setExpressaoModelo(expressaoModelo);
                if(equacao.eh_valido())
                {
                    EquacaoDao objeto_dao_equacao = new EquacaoDao();
                    objeto_dao_equacao.cadastrar(equacao);
                    RequestDispatcher r = request.getRequestDispatcher("/listarModelos");
                    request.setAttribute("mensagem", "Modelo adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoModelo");    
                    request.setAttribute("equacao", equacao);
                    request.setAttribute("erros", equacao.getErrors());
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
            Logger.getLogger(createEquacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createEquacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Modelo";
    }// </editor-fold>
}

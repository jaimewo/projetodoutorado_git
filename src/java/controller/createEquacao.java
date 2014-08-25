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
public class createEquacao extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                System.out.println("Chegueiiii aqui maledeto");
                String expressaoEquacao = request.getParameter("equacao[expressaoEquacao]");
                System.out.println(expressaoEquacao);
                Equacao equacao = new Equacao();
                equacao.setExpressaoEquacao(expressaoEquacao);
                if(equacao.eh_valido())
                {
                    EquacaoDao objeto_dao_equacao = new EquacaoDao();
                    objeto_dao_equacao.cadastrar(equacao);
                    RequestDispatcher r = request.getRequestDispatcher("/listarEquacoes");
                    request.setAttribute("mensagem", "Equação adicionada com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novaEquacao");    
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
        return "Criar Equacao";
    }// </editor-fold>
}

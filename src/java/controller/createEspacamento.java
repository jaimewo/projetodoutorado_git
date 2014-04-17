package controller;

import dao.EspacamentoDao;
import java.io.IOException;
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
public class createEspacamento extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String descricao = request.getParameter("espacamento[descricao]");
                Espacamento espacamento = new Espacamento();
                espacamento.setDescricao(descricao);
                if(espacamento.eh_valido())
                {
                    EspacamentoDao objeto_dao_espacamento = new EspacamentoDao();
                    objeto_dao_espacamento.cadastrar(espacamento);
                    RequestDispatcher r = request.getRequestDispatcher("/listarEspacamentos"); 
                    request.setAttribute("mensagem", "Espaçamento adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoEspacamento");    
                    request.setAttribute("espacamento", espacamento);
                    request.setAttribute("erros", espacamento.getErrors());
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
            Logger.getLogger(createEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Espacamento";
    }// </editor-fold>
}

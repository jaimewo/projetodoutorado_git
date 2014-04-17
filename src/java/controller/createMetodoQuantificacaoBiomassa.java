package controller;

import dao.MetodoQuantificacaoBiomassaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MetodoQuantificacaoBiomassa;

/**
 *
 * @author paulozeferino
 */
public class createMetodoQuantificacaoBiomassa extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String descricao = request.getParameter("metodoQuantificacaoBiomassa[descricao]");
                MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa = new MetodoQuantificacaoBiomassa();
                metodoQuantificacaoBiomassa.setDescricao(descricao);
                if(metodoQuantificacaoBiomassa.eh_valido())
                {
                    MetodoQuantificacaoBiomassaDao objeto_dao_metodoQuantificacaoBiomassa = new MetodoQuantificacaoBiomassaDao();
                    objeto_dao_metodoQuantificacaoBiomassa.cadastrar(metodoQuantificacaoBiomassa);
                    RequestDispatcher r = request.getRequestDispatcher("/listarMetodosQuantificacaoBiomassa"); 
                    request.setAttribute("mensagem", "Método de Quantificação da Biomassa adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoMetodoQuantificacaoBiomassa");    
                    request.setAttribute("metodoQuantificacaoBiomassa", metodoQuantificacaoBiomassa);
                    request.setAttribute("erros", metodoQuantificacaoBiomassa.getErrors());
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
            Logger.getLogger(createMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar MetodoQuantificacaoBiomassa";
    }// </editor-fold>
}

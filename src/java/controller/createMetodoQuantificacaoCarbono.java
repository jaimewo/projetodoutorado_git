package controller;

import dao.MetodoQuantificacaoCarbonoDao;
import java.io.IOException;
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
public class createMetodoQuantificacaoCarbono extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String descricao = request.getParameter("metodoQuantificacaoCarbono[descricao]");
                MetodoQuantificacaoCarbono metodoQuantificacaoCarbono = new MetodoQuantificacaoCarbono();
                metodoQuantificacaoCarbono.setDescricao(descricao);
                if(metodoQuantificacaoCarbono.eh_valido())
                {
                    MetodoQuantificacaoCarbonoDao objeto_dao_metodoQuantificacaoCarbono = new MetodoQuantificacaoCarbonoDao();
                    objeto_dao_metodoQuantificacaoCarbono.cadastrar(metodoQuantificacaoCarbono);
                    RequestDispatcher r = request.getRequestDispatcher("/listarMetodosQuantificacaoCarbono");    
                    request.setAttribute("mensagem", "Método de Quantificação da Carbono adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoMetodoQuantificacaoCarbono");    
                    request.setAttribute("metodoQuantificacaoCarbono", metodoQuantificacaoCarbono);
                    request.setAttribute("erros", metodoQuantificacaoCarbono.getErrors());
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
            Logger.getLogger(createMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar MetodoQuantificacaoCarbono";
    }// </editor-fold>
}

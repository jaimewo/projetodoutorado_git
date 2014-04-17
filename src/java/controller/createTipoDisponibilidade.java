package controller;

import dao.TipoDisponibilidadeDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoDisponibilidade;

/**
 *
 * @author paulozeferino
 */
public class createTipoDisponibilidade extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String descricao = request.getParameter("tipoDisponibilidade[descricao]");
                TipoDisponibilidade tipoDisponibilidade = new TipoDisponibilidade();
                tipoDisponibilidade.setDescricao(descricao);
                if(tipoDisponibilidade.eh_valido())
                {
                    TipoDisponibilidadeDao objeto_dao_tipoDisponibilidade = new TipoDisponibilidadeDao();
                    objeto_dao_tipoDisponibilidade.cadastrar(tipoDisponibilidade);
                    RequestDispatcher r = request.getRequestDispatcher("/listarTiposDisponibilidade");   
                    request.setAttribute("mensagem", "Tipo de Disponibilidade adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoTipoDisponibilidade");    
                    request.setAttribute("tipoDisponibilidade", tipoDisponibilidade);
                    request.setAttribute("erros", tipoDisponibilidade.getErrors());
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
            Logger.getLogger(createTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar TipoDisponibilidade";
    }// </editor-fold>
}

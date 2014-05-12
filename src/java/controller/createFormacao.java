package controller;

import dao.FormacaoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Formacao;

/**
 *
 * @author jaime
 */
public class createFormacao extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String descricao = request.getParameter("formacao[descricao]");
                String idBiomaStr = request.getParameter("formacao[idBioma]");
                int idBioma;
                Formacao formacao = new Formacao();
                formacao.setDescricao(descricao);
                if (idBiomaStr == null || idBiomaStr.isEmpty()) {
                   idBioma = 0;
                } else {
                   idBioma = Integer.parseInt(idBiomaStr);
                }
                formacao.setIdBioma(idBioma);
                if(formacao.eh_valido())
                {
                    FormacaoDao objeto_dao_formacao = new FormacaoDao();
                    objeto_dao_formacao.cadastrar(formacao);
                    RequestDispatcher r = request.getRequestDispatcher("/listarFormacoes");
                    request.setAttribute("mensagem", "Formação adicionada com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novaFormacao");    
                    request.setAttribute("formacao", formacao);
                    request.setAttribute("erros", formacao.getErrors());
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
            Logger.getLogger(createFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Formacao";
    }// </editor-fold>
}

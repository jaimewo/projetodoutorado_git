/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FormacaoDao;
import java.io.IOException;
import java.io.PrintWriter;
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
public class updateFormacao extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
            String descricao = request.getParameter("formacao[descricao]");
            String id = request.getParameter("formacao[id]");
            String idBiomaStr = request.getParameter("formacao[idBioma]");

            Formacao formacao = new Formacao();
            formacao.setDescricao(descricao);
            formacao.setId(Integer.parseInt(id));
             
            int idBioma;
            if (idBiomaStr == null || idBiomaStr.isEmpty()) {
               idBioma = 0;
            } else {
               idBioma = Integer.parseInt(idBiomaStr);
            }
            formacao.setIdBioma(idBioma);             
             
            if(formacao.eh_valido())
             {
                 FormacaoDao objeto_dao = new FormacaoDao();
                 objeto_dao.update(formacao);
                 RequestDispatcher r = request.getRequestDispatcher("/listarFormacoes"); 
                 request.setAttribute("mensagem", "Formação alterada com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarFormacao?id="+id); 
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
        } catch (Exception ex) {
            Logger.getLogger(updateFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar Formacao";
    }
}

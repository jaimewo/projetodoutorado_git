/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EquacaoDao;
import java.io.IOException;
import java.io.PrintWriter;
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
public class updateEquacao extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
            String expressaoEquacao = request.getParameter("equacao[expressaoEquacao]");
            String id = request.getParameter("equacao[id]");

            Equacao equacao = new Equacao();
            equacao.setExpressaoEquacao(expressaoEquacao);
            equacao.setId(Integer.parseInt(id));
             
            if(equacao.eh_valido())
             {
                 EquacaoDao objeto_dao = new EquacaoDao();
                 objeto_dao.update(equacao);
                 RequestDispatcher r = request.getRequestDispatcher("/listarEquacoes"); 
                 request.setAttribute("mensagem", "Equação alterada com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarEquacao?id="+id); 
                 request.setAttribute("erros", equacao.getErrors());
                  request.setAttribute("equacao", equacao);
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
            Logger.getLogger(updateEquacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateEquacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar Equacao";
    }
}

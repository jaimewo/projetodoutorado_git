/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EspacamentoDao;
import java.io.IOException;
import java.io.PrintWriter;
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
public class updateEspacamento extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String descricao = request.getParameter("espacamento[descricao]");
             String id = request.getParameter("espacamento[id]");
             Espacamento objeto_espacamento = new Espacamento();
             objeto_espacamento.setDescricao(descricao);
             objeto_espacamento.setId(Integer.parseInt(id));
             if(objeto_espacamento.eh_valido())
             {
                 EspacamentoDao objeto_dao = new EspacamentoDao();
                 objeto_dao.update(objeto_espacamento);
                 RequestDispatcher r = request.getRequestDispatcher("/listarEspacamentos"); 
                 request.setAttribute("mensagem", "Espaçamento alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarEspacamento?id="+id);  
                 request.setAttribute("erros", objeto_espacamento.getErrors());
                 request.setAttribute("espacamento", objeto_espacamento);
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
            Logger.getLogger(updateEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateEspacamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar Espacamento";
    }
}

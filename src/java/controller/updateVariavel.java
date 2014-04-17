/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VariavelDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Variavel;

/**
 *
 * @author paulozeferino
 */
public class updateVariavel extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String sigla = request.getParameter("variavel[sigla]");
             String nome = request.getParameter("variavel[nome]");
             String id = request.getParameter("variavel[id]");
             Variavel objeto_variavel = new Variavel();
             objeto_variavel.setSigla(sigla);
             objeto_variavel.setNome(nome);
             objeto_variavel.setId(Integer.parseInt(id));
             if(objeto_variavel.eh_valido())
             {
                 VariavelDao objeto_dao = new VariavelDao();
                 objeto_dao.update(objeto_variavel);
                 RequestDispatcher r = request.getRequestDispatcher("/listarVariaveis");   
                 request.setAttribute("mensagem", "Variável alterada com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarVariavel?id="+id);  
                 request.setAttribute("erros", objeto_variavel.getErrors());
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
            Logger.getLogger(updateVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateVariavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar Variavel";
    }
}

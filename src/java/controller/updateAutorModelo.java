/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AutorModeloDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AutorModelo;

/**
 *
 * @author paulozeferino
 */
public class updateAutorModelo extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String nome = request.getParameter("autorModelo[nome]");
             String id = request.getParameter("autorModelo[id]");
             AutorModelo objeto_autorModelo = new AutorModelo();
             objeto_autorModelo.setNome(nome);
             objeto_autorModelo.setId(Integer.parseInt(id));
             if(objeto_autorModelo.eh_valido())
             {
                 AutorModeloDao objeto_dao = new AutorModeloDao();
                 objeto_dao.update(objeto_autorModelo);
                 RequestDispatcher r = request.getRequestDispatcher("/listarAutoresModelo");   
                 request.setAttribute("mensagem", "Autor de Modelo alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarAutorModelo?id="+id); 
                 request.setAttribute("erros", objeto_autorModelo.getErrors());
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
            Logger.getLogger(updateAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateAutorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar AutorModelo";
    }
}

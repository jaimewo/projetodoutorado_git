/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AutorDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autor;

/**
 *
 * @author jaime
 */
public class updateAutor extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String nome = request.getParameter("autor[nome]");
             String id = request.getParameter("autor[id]");
             Autor objeto_autor = new Autor();
             objeto_autor.setNome(nome);
             objeto_autor.setId(Integer.parseInt(id));
             if(objeto_autor.eh_valido())
             {
                 AutorDao objeto_dao = new AutorDao();
                 objeto_dao.update(objeto_autor);
                 RequestDispatcher r = request.getRequestDispatcher("/listarAutores");  
                 request.setAttribute("mensagem", "Autor alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarAutor?id="+id);  
                 request.setAttribute("erros", objeto_autor.getErrors());
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
            Logger.getLogger(updateAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar Autor";
    }
}

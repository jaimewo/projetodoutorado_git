/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiomaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bioma;

/**
 *
 * @author paulozeferino
 */
public class updateBioma extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String descricao = request.getParameter("bioma[descricao]");
             String id = request.getParameter("bioma[id]");
             Bioma objeto_bioma = new Bioma();
             objeto_bioma.setDescricao(descricao);
             objeto_bioma.setId(Integer.parseInt(id));
             if(objeto_bioma.eh_valido())
             {
                 BiomaDao objeto_dao = new BiomaDao();
                 objeto_dao.update(objeto_bioma);
                 RequestDispatcher r = request.getRequestDispatcher("/listarBiomas");   
                 request.setAttribute("mensagem", "Bioma alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarBioma?id="+id);  
                 request.setAttribute("erros", objeto_bioma.getErrors());
                 request.setAttribute("bioma", objeto_bioma);
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
            Logger.getLogger(updateBioma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateBioma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar Bioma";
    }
}

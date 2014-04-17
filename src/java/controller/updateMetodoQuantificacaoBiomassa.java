/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MetodoQuantificacaoBiomassaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MetodoQuantificacaoBiomassa;

/**
 *
 * @author paulozeferino
 */
public class updateMetodoQuantificacaoBiomassa extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String descricao = request.getParameter("metodoQuantificacaoBiomassa[descricao]");
             String id = request.getParameter("metodoQuantificacaoBiomassa[id]");
             MetodoQuantificacaoBiomassa objeto_metodoQuantificacaoBiomassa = new MetodoQuantificacaoBiomassa();
             objeto_metodoQuantificacaoBiomassa.setDescricao(descricao);
             objeto_metodoQuantificacaoBiomassa.setId(Integer.parseInt(id));
             if(objeto_metodoQuantificacaoBiomassa.eh_valido())
             {
                 MetodoQuantificacaoBiomassaDao objeto_dao = new MetodoQuantificacaoBiomassaDao();
                 objeto_dao.update(objeto_metodoQuantificacaoBiomassa);
                 RequestDispatcher r = request.getRequestDispatcher("/listarMetodosQuantificacaoBiomassa"); 
                 request.setAttribute("mensagem", "Método alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarMetodoQuantificacaoBiomassa?id="+id);  
                 request.setAttribute("erros", objeto_metodoQuantificacaoBiomassa.getErrors());
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
            Logger.getLogger(updateMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateMetodoQuantificacaoBiomassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar MetodoQuantificacaoBiomassa";
    }
}

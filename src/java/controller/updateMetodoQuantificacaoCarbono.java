/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MetodoQuantificacaoCarbonoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MetodoQuantificacaoCarbono;

/**
 *
 * @author paulozeferino
 */
public class updateMetodoQuantificacaoCarbono extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String descricao = request.getParameter("metodoQuantificacaoCarbono[descricao]");
             String id = request.getParameter("metodoQuantificacaoCarbono[id]");
             MetodoQuantificacaoCarbono objeto_metodoQuantificacaoCarbono = new MetodoQuantificacaoCarbono();
             objeto_metodoQuantificacaoCarbono.setDescricao(descricao);
             objeto_metodoQuantificacaoCarbono.setId(Integer.parseInt(id));
             if(objeto_metodoQuantificacaoCarbono.eh_valido())
             {
                 MetodoQuantificacaoCarbonoDao objeto_dao = new MetodoQuantificacaoCarbonoDao();
                 objeto_dao.update(objeto_metodoQuantificacaoCarbono);
                 RequestDispatcher r = request.getRequestDispatcher("/listarMetodosQuantificacaoCarbono");  
                 request.setAttribute("mensagem", "Método alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarMetodoQuantificacaoCarbono?id="+id); 
                 request.setAttribute("erros", objeto_metodoQuantificacaoCarbono.getErrors());
                 request.setAttribute("metodoQuantificacaoCarbono", objeto_metodoQuantificacaoCarbono);
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
            Logger.getLogger(updateMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateMetodoQuantificacaoCarbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar MetodoQuantificacaoCarbono";
    }
}

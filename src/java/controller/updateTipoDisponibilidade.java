/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoDisponibilidadeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoDisponibilidade;

/**
 *
 * @author paulozeferino
 */
public class updateTipoDisponibilidade extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
             String descricao = request.getParameter("tipoDisponibilidade[descricao]");
             String id = request.getParameter("tipoDisponibilidade[id]");
             TipoDisponibilidade objeto_tipoDisponibilidade = new TipoDisponibilidade();
             objeto_tipoDisponibilidade.setDescricao(descricao);
             objeto_tipoDisponibilidade.setId(Integer.parseInt(id));
             if(objeto_tipoDisponibilidade.eh_valido())
             {
                 TipoDisponibilidadeDao objeto_dao = new TipoDisponibilidadeDao();
                 objeto_dao.update(objeto_tipoDisponibilidade);
                 RequestDispatcher r = request.getRequestDispatcher("/listarTiposDisponibilidade"); 
                 request.setAttribute("mensagem", "Tipo de Disponibilidade alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarTipoDisponibilidade?id="+id);
                 request.setAttribute("erros", objeto_tipoDisponibilidade.getErrors());
                 request.setAttribute("tipoDisponibilidade", objeto_tipoDisponibilidade);
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
            Logger.getLogger(updateTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateTipoDisponibilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar TipoDisponibilidade";
    }
}

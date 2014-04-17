/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TrabalhoCientificoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TrabalhoCientifico;

/**
 *
 * @author jaime
 */
public class updateTrabalhoCientifico extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
            String id = request.getParameter("trabalhoCientifico[id]");
            String titulo = request.getParameter("trabalhoCientifico[titulo]");
            String ano = request.getParameter("trabalhoCientifico[ano]");
            String idTipoDisponibilidade = request.getParameter("trabalhoCientifico[idTipoDisponibilidade]");
            String idMetodoQuantificacaoBiomassa = request.getParameter("trabalhoCientifico[idMetodoQuantificacaoBiomassa]");
            String idMetodoQuantificacaoCarbono = request.getParameter("trabalhoCientifico[idMetodoQuantificacaoCarbono]");
            String idAutor = request.getParameter("trabalhoCientifico[idAutor]");
            
            TrabalhoCientifico trabalhoCientifico = new TrabalhoCientifico();
            trabalhoCientifico.setTitulo(titulo);
            trabalhoCientifico.setAno(Integer.parseInt(ano));
            trabalhoCientifico.setIdTipoDisponibilidade(Integer.parseInt(idTipoDisponibilidade));
            trabalhoCientifico.setIdMetodoQuantificacaoBiomassa(Integer.parseInt(idMetodoQuantificacaoBiomassa));
            trabalhoCientifico.setIdMetodoQuantificacaoCarbono(Integer.parseInt(idMetodoQuantificacaoCarbono));
            trabalhoCientifico.setIdAutor(Integer.parseInt(idAutor));
            
            if(trabalhoCientifico.eh_valido())
             {
                 TrabalhoCientificoDao objeto_dao = new TrabalhoCientificoDao();
                 objeto_dao.update(trabalhoCientifico);
                 RequestDispatcher r = request.getRequestDispatcher("/listarTrabalhosCientificos");    
                 request.setAttribute("mensagem", "Trabalho Científico alterado com sucesso!"); 
                 r.forward( request, response );  
             }else
             {
                 RequestDispatcher r = request.getRequestDispatcher("/editarTrabalhoCientifico?id="+id); 
                 request.setAttribute("erros", trabalhoCientifico.getErrors());
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
            Logger.getLogger(updateTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar TrabalhoCientifico";
    }
}

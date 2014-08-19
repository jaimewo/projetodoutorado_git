/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.AutorDao;
import dao.TrabalhoCientificoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autor;
import model.Equacao;
import model.TrabalhoCientifico;

/**
 *
 * @author paulozeferino
 */
public class retornaDetalhesTrabalhoCientifico extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            TrabalhoCientificoDao obj_dao = new TrabalhoCientificoDao();
            try {
               TrabalhoCientifico obj_trabalho = obj_dao.getTrabalhoCientifico(request.getParameter("trabalho_id"));
               ArrayList<Equacao> equacoes = obj_trabalho.getEquacoesTrabalho();
               
               String retorno = "";
               retorno += "<div><b>";
               retorno += " Autor: ";
               Autor objeto_autor = new AutorDao().getAutor(obj_trabalho.getIdAutor()+"");
               retorno += objeto_autor.getNome();
               retorno += "Equações do Trabalho: ";
               retorno += "</b>";
               retorno += "</b></div>";               
               retorno += "<div>";
               for(Equacao obj_equacao:equacoes){
                   switch(obj_equacao.idVariavelInteresse)
                   {
                       case 1:
                           retorno += "Biomassa: ";
                           break;
                       case 2:
                           retorno += "Carbono: ";
                           break;
                       case 3:
                           retorno += "Volume: ";
                           break;
                   }
                   retorno += obj_equacao.expressaoEquacao + "<br />";
               }
               retorno += "</div>";
               
               
               out.println(retorno);
            } catch (SQLException ex) {
                Logger.getLogger(retornaDetalhesTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(retornaDetalhesTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(retornaDetalhesTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

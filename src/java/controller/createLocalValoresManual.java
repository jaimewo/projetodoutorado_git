/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.LocalDao;
import model.Local;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jaime
 */
public class createLocalValoresManual extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            try{
            System.out.println("Quantidade Biomassa:"+request.getParameter("local_biomassa"));
            System.out.println("Quantidade Carbono:"+request.getParameter("local_carbono"));
            System.out.println("Quantidade Volume:"+request.getParameter("local_volume"));


            //Receber da da tela
            int idLocal = Integer.parseInt(request.getParameter("local_id")); //Pegar idLocal da tela
            LocalDao localDao = new LocalDao();
            Local local = new Local();
            local = localDao.getLocal(idLocal);
            
            double qtdeBiomassa = Double.parseDouble(request.getParameter("local_biomassa")); //Receber da tela
            double qtdeCarbono = Double.parseDouble(request.getParameter("local_carbono")); //Receber da tela
            double qtdeVolume = Double.parseDouble(request.getParameter("local_volume")); //Receber da tela

            local.setQtdeBiomassaEquacao(qtdeBiomassa);
            local.setQtdeBiomassaDm(qtdeBiomassa);            
            local.setQtdeCarbonoEquacao(qtdeCarbono);
            local.setQtdeCarbonoDm(qtdeCarbono);            
            local.setQtdeVolumeEquacao(qtdeVolume);
            local.setQtdeVolumeDm(qtdeVolume);            
            localDao = new LocalDao();
            localDao.update(local);

            String retorno="";
            retorno += "alert('Valores cadastrados com sucesso!')";
            out.println(retorno);
    
            }catch(Exception e)
            {
                String retorno="";
                retorno += "alert('Não foi possível processar este cálculo!')";
                out.println(retorno);
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
            Logger.getLogger(createLocalValoresManual.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(createLocalValoresManual.class.getName()).log(Level.SEVERE, null, ex);
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

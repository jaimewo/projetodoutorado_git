/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiomaDao;
import dao.FormacaoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bioma;
import model.Formacao;

/**
 *
 * @author paulozeferino
 */
public class editarFormacao extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
            String idFormacao = request.getParameter("id");
            Formacao formacao = (Formacao) request.getAttribute("formacao");
            if(formacao == null){             
               FormacaoDao controller = new FormacaoDao();
               Formacao objeto_formacao = controller.getFormacao(idFormacao);
               request.setAttribute("formacao", objeto_formacao );
            }else {
               request.setAttribute("formacao", formacao );
            }             
            BiomaDao objeto_bioma_dao = new BiomaDao();
            List<Bioma> biomas = objeto_bioma_dao.listarBiomas();
            request.setAttribute("biomas", biomas);

            request.getRequestDispatcher("editarFormacao.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarFormacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editarFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarFormacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editarFormacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar Formacao";
    }
}

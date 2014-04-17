/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FormacaoDao;
import dao.EspacamentoDao;
import dao.TrabalhoCientificoDao;
import dao.LocalDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Formacao;
import model.Espacamento;
import model.TrabalhoCientifico;
import model.Local;

/**
 *
 * @author jaime
 */
public class editarLocal extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
             String idLocal = request.getParameter("id");
             LocalDao controller = new LocalDao();
             Local objeto_local = controller.getLocal(idLocal);
             request.setAttribute("local", objeto_local );
             
            FormacaoDao objeto_formacao_dao = new FormacaoDao();
            List<Formacao> formacoes = objeto_formacao_dao.listarFormacoes();
            request.setAttribute("formacoes", formacoes);
             
            EspacamentoDao objeto_espacamento_dao = new EspacamentoDao();
            List<Espacamento> espacamentos = objeto_espacamento_dao.listarEspacamentos();
            request.setAttribute("espacamentos", espacamentos);
             
            TrabalhoCientificoDao objeto_trabalhoCientifico_dao = new TrabalhoCientificoDao();
            List<TrabalhoCientifico> trabalhosCientificos = objeto_trabalhoCientifico_dao.listarTrabalhosCientificos();
            request.setAttribute("trabalhosCientificos", trabalhosCientificos);

             request.getRequestDispatcher("editarLocal.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editarLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editarLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar Local";
    }
}
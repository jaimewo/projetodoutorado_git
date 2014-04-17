/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoDisponibilidadeDao;
import dao.MetodoQuantificacaoBiomassaDao;
import dao.MetodoQuantificacaoCarbonoDao;
import dao.TrabalhoCientificoDao;
import dao.AutorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoDisponibilidade;
import model.MetodoQuantificacaoBiomassa;
import model.MetodoQuantificacaoCarbono;
import model.TrabalhoCientifico;
import model.Autor;

/**
 *
 * @author jaime
 */
public class editarTrabalhoCientifico extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
            String idTrabalhoCientifico = request.getParameter("id");
            TrabalhoCientificoDao controller = new TrabalhoCientificoDao();
            TrabalhoCientifico objeto_trabalhoCientifico = controller.getTrabalhoCientifico(idTrabalhoCientifico);
            request.setAttribute("trabalhoCientifico", objeto_trabalhoCientifico );
             
            TipoDisponibilidadeDao objeto_tipoDisponibilidade_dao = new TipoDisponibilidadeDao();
            List<TipoDisponibilidade> tiposDisponibilidade = objeto_tipoDisponibilidade_dao.listarTiposDisponibilidade();
            request.setAttribute("tiposDisponibilidade", tiposDisponibilidade);
             
            MetodoQuantificacaoBiomassaDao objeto_metodoQuantificacaoBiomassa_dao = new MetodoQuantificacaoBiomassaDao();
            List<MetodoQuantificacaoBiomassa> metodosQuantificacaoBiomassa = objeto_metodoQuantificacaoBiomassa_dao.listarMetodosQuantificacaoBiomassa();
            request.setAttribute("metodosQuantificacaoBiomassa", metodosQuantificacaoBiomassa);

            MetodoQuantificacaoCarbonoDao objeto_metodoQuantificacaoCarbono_dao = new MetodoQuantificacaoCarbonoDao();
            List<MetodoQuantificacaoCarbono> metodosQuantificacaoCarbono = objeto_metodoQuantificacaoCarbono_dao.listarMetodosQuantificacaoCarbono();
            request.setAttribute("metodosQuantificacaoCarbono", metodosQuantificacaoCarbono);
             
            AutorDao objeto_autor_dao = new AutorDao();
            List<Autor> autores = objeto_autor_dao.listarAutores();
            request.setAttribute("autores", autores);
            
            request.getRequestDispatcher("editarTrabalhoCientifico.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editarTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(editarTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(editarTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar TrabalhoCientifico";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiomaDao;
import dao.CoordenadaLocalDao;
import dao.EspacamentoDao;
import dao.FormacaoDao;
import dao.LocalDao;
import dao.MunicipioDao;
import dao.TrabalhoCientificoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bioma;
import model.Espacamento;
import model.Formacao;
import model.Local;
import model.Municipio;
import model.TrabalhoCientifico;
/**
 *
 * @author jaime
 */
public class novoLocal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
            
                        String idlocal = request.getParameter("id");
            
            FormacaoDao objeto_formacao_dao = new FormacaoDao();
            List<Formacao> formacoes = objeto_formacao_dao.listarFormacoes();
            request.setAttribute("formacoes", formacoes);

            EspacamentoDao objeto_espacamento_dao = new EspacamentoDao();
            List<Espacamento> espacamentos = objeto_espacamento_dao.listarEspacamentos();
            request.setAttribute("espacamentos", espacamentos);            

            TrabalhoCientificoDao objeto_trabalhoCientifico_dao = new TrabalhoCientificoDao();
            List<TrabalhoCientifico> trabalhosCientificos = objeto_trabalhoCientifico_dao.listarTrabalhosCientificos();
            request.setAttribute("trabalhosCientificos", trabalhosCientificos);

            MunicipioDao objeto_municipio_dao = new MunicipioDao();
            List<Municipio> municipios = objeto_municipio_dao.listarMunicipios();
            request.setAttribute("municipios", municipios);  
           
            BiomaDao objeto_bioma_dao = new BiomaDao();
            List<Bioma> biomas = objeto_bioma_dao.listarBiomas();
            request.setAttribute("biomas", biomas);
              
        } finally { 
            Local local = (Local) request.getAttribute("local");
            if(local == null) {
               request.setAttribute("local", new Local());
            } else {
                request.setAttribute("local", local);
            }
            request.getRequestDispatcher("novoLocalDadosBasicos.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Novo Local";
    }
}

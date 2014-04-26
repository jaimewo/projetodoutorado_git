/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LocalDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Local;

/**
 *
 * @author jaime
 */
public class updateLocal extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
                String id = request.getParameter("local[id]");    
                String descricao = request.getParameter("local[descricao]");
                String areaStr = request.getParameter("local[area]");
                String areaParcelaStr = request.getParameter("local[areaParcela]");
                String idFormacaoStr = request.getParameter("local[idFormacao]");
                String idEspacamentoStr = request.getParameter("local[idEspacamento]");
                String idTrabalhoCientificoStr = request.getParameter("local[idTrabalhoCientifico]");
                String idMunicipioStr = request.getParameter("municipio[idmunicipio]");
                String latitudeStr = request.getParameter("coordenadaLocal[latitude]");
                String longitudeStr = request.getParameter("coordenadaLocal[longitude]");
                
                Local local = new Local();
                local.setDescricao(descricao);

                double area;
                if (areaStr == null || areaStr.isEmpty()) {
                   area = 0.0;
                } else {
                   area = Double.parseDouble(areaStr);
                }
                local.setArea(area);

                double areaParcela;
                if (areaParcelaStr == null || areaParcelaStr.isEmpty()) {
                   areaParcela = 0.0;
                } else {
                   areaParcela = Double.parseDouble(areaParcelaStr);
                }
                local.setAreaParcela(areaParcela);
                
                int idFormacao;
                if (idFormacaoStr == null || idFormacaoStr.isEmpty()) {
                   idFormacao = 0;
                } else {
                   idFormacao = Integer.parseInt(idFormacaoStr);
                }
                local.setIdFormacao(idFormacao);
                
                
                int idEspacamento;
                if (idEspacamentoStr == null || idEspacamentoStr.isEmpty()) {
                   idEspacamento = 0;
                } else {
                   idEspacamento = Integer.parseInt(idEspacamentoStr);
                }
                local.setIdEspacamento(idEspacamento);

                int idMunicipio;
                if (idMunicipioStr == null || idMunicipioStr.isEmpty()) {
                   idMunicipio = 0;
                } else {
                   idMunicipio = Integer.parseInt(idMunicipioStr);
                }
                municipio.setIdMunicipio(idMunicipio);
                
                int idTrabalhoCientifico;
                if (idTrabalhoCientificoStr == null || idTrabalhoCientificoStr.isEmpty()) {
                   idTrabalhoCientifico = 0;
                } else {
                   idTrabalhoCientifico = Integer.parseInt(idTrabalhoCientificoStr);
                }
                local.setIdTrabalhoCientifico(idTrabalhoCientifico);
           
             
                if(local.eh_valido())
                {
                   LocalDao objeto_dao = new LocalDao();
                   objeto_dao.update(local);
                   RequestDispatcher r = request.getRequestDispatcher("/listarLocais"); 
                   request.setAttribute("mensagem", "Loacal alterado com sucesso!"); 
                   r.forward( request, response );  
                }else {
                   RequestDispatcher r = request.getRequestDispatcher("/editarLocal?id="+id); 
                   request.setAttribute("erros", local.getErrors());
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
            Logger.getLogger(updateLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Atualizar Local";
    }
}

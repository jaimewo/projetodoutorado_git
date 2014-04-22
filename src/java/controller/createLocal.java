package controller;

import dao.LocalDao;
import dao.MunicipioLocalDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Local;
import model.MunicipioLocal;
import model.CoordenadaLocal;
/**
 *
 * @author jaime
 */
public class createLocal extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String descricao = request.getParameter("local[descricao]");
                String areaStr = request.getParameter("local[area]");
                String areaParcelaStr = request.getParameter("local[areaParcela]");
                String idFormacaoStr = request.getParameter("local[idFormacao]");
                String idEspacamentoStr = request.getParameter("local[idEspacamento]");
                String idTrabalhoCientificoStr = request.getParameter("local[idTrabalhoCientifico]");
                String idMunicipioLocalStr = request.getParameter("local[idMunicipioLocal]");
                String coordenadaStr = request.getParameter("CoordenadaLocal[coordenada]");
                
                Local local = new Local();
                List<MunicipioLocal> municipiosLocal = new ArrayList<MunicipioLocal>();
                List<CoordenadaLocal> coordenadasLocal = new ArrayList<CoordenadaLocal>();
                
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
                
                int idTrabalhoCientifico;
                if (idTrabalhoCientificoStr == null || idTrabalhoCientificoStr.isEmpty()) {
                   idTrabalhoCientifico = 0;
                } else {
                   idTrabalhoCientifico = Integer.parseInt(idTrabalhoCientificoStr);
                }
                local.setIdTrabalhoCientifico(idTrabalhoCientifico);
                
                MunicipioLocal municipioLocal = new MunicipioLocal();
                int idMunicipioLocal;
                if (idMunicipioLocalStr == null || idMunicipioLocalStr.isEmpty()) {
                   idMunicipioLocal = 0;
                } else {
                   idMunicipioLocal = Integer.parseInt(idMunicipioLocalStr);
                }
                municipioLocal.setIdMunicipio(idMunicipioLocal);  
                municipiosLocal.add(municipioLocal);
                
                CoordenadaLocal coordenadaLocal = new CoordenadaLocal();
                double coordenada;
                if (coordenadaStr == null || coordenadaStr.isEmpty()) {
                   coordenada = 0;
                } else {
                   coordenada = Double.parseDouble(coordenadaStr);
                }
                coordenadaLocal.setCoordenada(coordenada);                
                coordenadasLocal.add(coordenadaLocal);
                
                
                if(local.eh_valido())
                {
                    LocalDao objeto_dao_local = new LocalDao();
                    objeto_dao_local.cadastrar(local, municipiosLocal, coordenadasLocal);
                    RequestDispatcher r = request.getRequestDispatcher("/listarLocais");
                    request.setAttribute("mensagem", "Local adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoLocal");    
                    request.setAttribute("local", local);
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
        } catch (SQLException ex) {
            Logger.getLogger(createLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Local";
    }// </editor-fold>
}

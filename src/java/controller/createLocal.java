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
import model.CoordenadaLocal;
import model.Local;
import model.Municipio;
import model.MunicipioLocal;
/**
 *
 * @author jaime
 */
public class createLocal extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
       // try {
                String descricao = request.getParameter("local_descricao");
                String local_bioma = request.getParameter("local_bioma");
                String local_formacao = request.getParameter("local_formacao");
                String local_espacamento = request.getParameter("local_espacamento");
                String area_total = request.getParameter("area_total");
                String local_trabalhoCientifico = request.getParameter("local_trabalhoCientifico");
                
                
                System.out.println("Local Descrição:"+descricao);
              //  String idMunicipioStr = request.getParameter("municipio[idMunicipio]");
              //  String latitudeStr = request.getParameter("CoordenadaLocal[latitude]");
              //  String longitudeStr = request.getParameter("CoordenadaLocal[longitude]");
                
                Local local = new Local();
                local.setDescricao(descricao);
                local.setIdFormacao(Integer.parseInt(local_formacao));
                local.setIdTrabalhoCientifico(Integer.parseInt(local_trabalhoCientifico));
                local.setArea(Double.parseDouble(area_total));
                local.setAreaParcela(Double.parseDouble(area_total));
                local.setIdEspacamento(Integer.parseInt(local_espacamento));
                
//                List<MunicipioLocal> municipiosLocal = new ArrayList<MunicipioLocal>();
//                List<CoordenadaLocal> coordenadasLocal = new ArrayList<CoordenadaLocal>();
//                
//                local.setDescricao(descricao);
//
//                double area;
//                if (areaStr == null || areaStr.isEmpty()) {
//                   area = 0.0;
//                } else {
//                   area = Double.parseDouble(areaStr);
//                }
//                local.setArea(area);
//
//                double areaParcela;
//                if (areaParcelaStr == null || areaParcelaStr.isEmpty()) {
//                   areaParcela = 0.0;
//                } else {
//                   areaParcela = Double.parseDouble(areaParcelaStr);
//                }
//                local.setAreaParcela(areaParcela);
//                
//                int idFormacao;
//                if (idFormacaoStr == null || idFormacaoStr.isEmpty()) {
//                   idFormacao = 0;
//                } else {
//                   idFormacao = Integer.parseInt(idFormacaoStr);
//                }
//                local.setIdFormacao(idFormacao);
//                
//                
//                int idEspacamento;
//                if (idEspacamentoStr == null || idEspacamentoStr.isEmpty()) {
//                   idEspacamento = 0;
//                } else {
//                   idEspacamento = Integer.parseInt(idEspacamentoStr);
//                }
//                local.setIdEspacamento(idEspacamento);
//                
//                int idTrabalhoCientifico;
//                if (idTrabalhoCientificoStr == null || idTrabalhoCientificoStr.isEmpty()) {
//                   idTrabalhoCientifico = 0;
//                } else {
//                   idTrabalhoCientifico = Integer.parseInt(idTrabalhoCientificoStr);
//                }
//                local.setIdTrabalhoCientifico(idTrabalhoCientifico);
//                
//                Municipio municipio = new Municipio();
//                int idMunicipio;
//                if (idMunicipioStr == null || idMunicipioStr.isEmpty()) {
//                   idMunicipio = 0;
//                } else {
//                   idMunicipio = Integer.parseInt(idMunicipioStr);
//                }
//                municipio.setId(idMunicipio);  
//               
//                
//                CoordenadaLocal coordenadaLocal = new CoordenadaLocal();
//                double latitude;
//                if (latitudeStr == null || latitudeStr.isEmpty()) {
//                   latitude = 0;
//                } else {
//                   latitude = Double.parseDouble(latitudeStr);
//                }
//                coordenadaLocal.setLatitude(latitude);
//                double longitude;
//                if (longitudeStr == null || longitudeStr.isEmpty()) {
//                   longitude = 0;
//                } else {
//                   longitude = Double.parseDouble(longitudeStr);
//                }
//                coordenadaLocal.setLatitude(latitude);     
//                coordenadaLocal.setLongitude(longitude);     
//                coordenadasLocal.add(coordenadaLocal);
//                
//                
                if(local.eh_valido())
                {
                    LocalDao objeto_dao_local = new LocalDao();
                    //objeto_dao_local.cadastrar(local, municipiosLocal, coordenadasLocal);
                    objeto_dao_local.cadastrar(local);
//                    RequestDispatcher r = request.getRequestDispatcher("/listarLocais");
//                    request.setAttribute("mensagem", "Local adicionado com sucesso!");
//                    r.forward( request, response );  
                }else
                {
//                    RequestDispatcher r = request.getRequestDispatcher("/novoLocal");    
//                    request.setAttribute("local", local);
//                    request.setAttribute("erros", local.getErrors());
//                    r.forward( request, response );  
                }
      //  } finally {            
            
      //  }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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
        } catch (Exception ex) {
            Logger.getLogger(createLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Local";
    }// </editor-fold>
}

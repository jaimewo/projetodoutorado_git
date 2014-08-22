package controller;

import dao.ComplementoLocalDao;
import dao.LocalDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Local;
/**
 *
 * @author jaime
 */
public class createLocal extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try (PrintWriter out = response.getWriter()) {
            
                String descricao = request.getParameter("local_descricao");
                String local_formacao = request.getParameter("local_formacao");
                String local_espacamento = request.getParameter("local_espacamento");
                String area_total = request.getParameter("area_total");
                String local_trabalhoCientifico = request.getParameter("local_trabalhoCientifico");
                String local_municipio = request.getParameter("local_municipio");                
           
                String local_latitude = request.getParameter("local_latitude");                                
                String local_longitude = request.getParameter("local_longitude");  
                
                if (local_latitude.equalsIgnoreCase("")) {
                    local_latitude = "0";
                }
                
                if (local_longitude.equalsIgnoreCase("")) {
                    local_longitude = "0";
                }
                
                Local local = new Local();
                local.setDescricao(descricao);
                local.setIdFormacao(Integer.parseInt(local_formacao));
                local.setIdTrabalhoCientifico(Integer.parseInt(local_trabalhoCientifico));
                local.setArea(Double.parseDouble(area_total));
                local.setAreaParcela(Double.parseDouble(area_total));
                local.setIdEspacamento(Integer.parseInt(local_espacamento));
                local.setIdMunicipio(Integer.parseInt(local_municipio));                
                local.setLatitude(Double.parseDouble(local_latitude));                
                local.setLongitude(Double.parseDouble(local_longitude));                                
                
                if(local.eh_valido())
                {
                    LocalDao objeto_dao_local = new LocalDao();
                    ComplementoLocalDao objeto_dao_local1 = new ComplementoLocalDao();
                    objeto_dao_local.cadastrar(local);
                    int id_local = objeto_dao_local1.returnLastLocal();
                    String retorno="";
                    retorno +="$('#valor_calculo').show();";
                    retorno += "$('#local_id').val("+id_local+");";
                    retorno += "$('#local_id_parcela').val('"+id_local+"');";
                    //retorno += "alert('Local criado com sucesso!')";
                    //request.setAttribute("mensagem", "Local Incluído com sucesso!");                                        
                    
                    out.println(retorno);
                }else
                {
                    out.println("alert('Local não pode ser criado');");
                }
        }
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

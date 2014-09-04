/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CoordenadaLocalDao;
import dao.LocalDao;
import dao.MunicipioLocalDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CoordenadaLocal;
import model.Local;
import model.MunicipioLocal;

/**
 *
 * @author jaime
 */
public class updateLocal extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
                String id = request.getParameter("local_id");    
                String descricao = request.getParameter("local_descricao");
                String areaStr = request.getParameter("local[area]");
                String areaParcelaStr = request.getParameter("local[areaParcela]");
                String idTipoEstimativaStr = request.getParameter("local_tipo_estimativa");
                String idTipoFlorestaStr = request.getParameter("local[idTipoFloresta]");
                String idMunicipioStr = request.getParameter("local[idMunicipio]");
                String idadeStr = request.getParameter("local[idade]");
                String idFormacaoStr = request.getParameter("local[idFormacao]");
                String idEspecieStr = request.getParameter("local[idEspecie]");
                String idEspacamentoStr = request.getParameter("local[idEspacamento]");
              
                Local local = new Local();
                local.setId(Integer.parseInt(id));
                local.setDescricao(descricao);
                
                LocalDao objeto_dao = new LocalDao();
         
              
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
                
                int idTipoEstimativa;
                if (idTipoEstimativaStr == null || idTipoEstimativaStr.isEmpty()) {
                   idTipoEstimativa = 0;
                } else {
                   idTipoEstimativa = Integer.parseInt(idTipoEstimativaStr);
                }
                local.setIdTipoEstimativa(idTipoEstimativa);
                
                int idTipoFloresta;
                if (idTipoFlorestaStr == null || idTipoFlorestaStr.isEmpty()) {
                   idTipoFloresta = 0;
                } else {
                   idTipoFloresta = Integer.parseInt(idTipoFlorestaStr);
                }
                local.setIdTipoFloresta(idTipoFloresta);
                
                int idade;
                if (idadeStr == null || idadeStr.isEmpty()) {
                   idade = 0;
                } else {
                   idade = Integer.parseInt(idadeStr);
                }
                local.setIdade(idade);
                
                int idFormacao;
                if (idFormacaoStr == null || idFormacaoStr.isEmpty()) {
                   idFormacao = 0;
                } else {
                   idFormacao = Integer.parseInt(idFormacaoStr);
                }
                local.setIdFormacao(idFormacao);
                
                
                int idEspecie;
                if (idEspecieStr == null || idEspecieStr.isEmpty()) {
                   idEspecie = 0;
                } else {
                   idEspecie = Integer.parseInt(idEspecieStr);
                }
                local.setIdEspecie(idEspecie);
                
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
                local.setIdMunicipio(idMunicipio);
                
                        objeto_dao.update(local);
                String retorno="";
                switch(idTipoEstimativa)
                {
                    case 1:
                          retorno += "alert('Local atualizado com sucesso!');";
                         retorno += "window.location.href='/jcarbon/novoLocalPergunta1?id="+local.getIdString()+"'";
                        break;
                }
             out.println(retorno);
                //if(local.eh_valido())
                //{
                
           
                //   RequestDispatcher r = request.getRequestDispatcher("/listarLocais"); 
                  // request.setAttribute("mensagem", "Loacal alterado com sucesso!"); 
                 //  r.forward( request, response );  
               // }else {
                 //  RequestDispatcher r = request.getRequestDispatcher("/editarLocal?id="+id); 
                 //  request.setAttribute("erros", local.getErrors());
                 //  request.setAttribute("local", local);
                  // r.forward( request, response );  
                //}
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

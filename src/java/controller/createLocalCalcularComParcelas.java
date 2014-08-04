/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.LocalDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.read.biff.BiffException;
import model.EstatisticaInventario;
import model.Local;
import model.Parcela;

/**
 *
 * @author jaime
 */
public class createLocalCalcularComParcelas extends HttpServlet {

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
            throws ServletException, IOException, SQLException, BiffException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int idLocal = Integer.parseInt(request.getParameter("local_id")); //Pegar idLocal da tela
            int idVariavelInteresse = Integer.parseInt(request.getParameter("variavel_interesse")); //Pegar o idVariavelInteresse do combo escolhido na tela            
            importarParcelas(idLocal);
            double qtdTela = 0;
            try{
             qtdTela = calcular(idLocal,idVariavelInteresse);
            String retorno = "";
            retorno += "alert('Cálculo efetuado com sucesso!');";
            retorno += "$('#total_calculdo').val('"+qtdTela+"')";
            out.println(retorno);
            }catch(Exception e)
            {
               String retorno = "";
               retorno += "$('#total_calculdo').val('0')";
               out.println(retorno);
            }
        }
    }
    
        public void importarParcelas(int idLocal) throws SQLException, BiffException {

        // SALVAR A PLANILHA QUE O USUÁRIO ESCOLHEU NA PASTA \...\Arquivos do projeto com o nome
        // parcela99999999.xls sendo 99999999 o id do local 
    
        Local local = new Local();
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);
        
        Parcela parcela = new Parcela();
        parcela.importarPlanilha(local);  
        
    }
        
      
    public double calcular(int idLocal,int idVariavelInteresse) throws SQLException, BiffException, Exception {
    
        int idTipoEstimativa = 2;  //Fixo devido a seleção do Cálculo com Parcelas
    
        Local local = new Local();
        LocalDao localDao = new LocalDao();
    
        local = localDao.getLocal(idLocal);
        localDao = new LocalDao();
        local.setIdTipoEstimativa(idTipoEstimativa);
        localDao.update(local);
    
        ArrayList<Parcela> parcelasLocal = new ArrayList<Parcela>();
        parcelasLocal = local.getParcelas();

        int idMetodoCalculo = 1; //Fixo: Equacao

        EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
        estatisticaInventario.setIdLocal(idLocal);
        estatisticaInventario.setIdVariavelInteresse(idVariavelInteresse);    
        estatisticaInventario.setIdMetodoCalculo(idMetodoCalculo);
        estatisticaInventario.calcularEstatisticas(local,parcelasLocal);
    
        //CAMPO PARA SER ENVIADO PARA A TELA para ser exibido no CAMPO Total Calculado (t/ha)
        double qtdeTela = estatisticaInventario.getQtdeMedia();
        
        return qtdeTela;
        
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
        } catch (BiffException ex) {
            Logger.getLogger(createLocalCalcularComParcelas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComParcelas.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (BiffException ex) {
            Logger.getLogger(createLocalCalcularComParcelas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComParcelas.class.getName()).log(Level.SEVERE, null, ex);
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

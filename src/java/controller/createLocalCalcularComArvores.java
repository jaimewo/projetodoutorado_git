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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.read.biff.BiffException;
import model.Arvore;
import model.EstatisticaInventario;
import model.Local;
import model.Parcela;

/**
 *
 * @author paulozeferino
 */
public class createLocalCalcularComArvores extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        Locale locale = new Locale("pt","BR");  
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm':'ss'h'",locale);
        System.out.println("INICIO: "+formatador.format(calendar.getTime()));
    
        
        ArrayList<Parcela> parcelasLocal = new ArrayList<Parcela>();
        
        try (PrintWriter out = response.getWriter()) {
          
            int idLocal = Integer.parseInt(request.getParameter("local_id")); //Pegar idLocal da tela
            int idVariavelInteresse = Integer.parseInt(request.getParameter("variavel_interesse")); //Pegar o idVariavelInteresse do combo escolhido na tela            
            String btn_clicado = request.getParameter("btn_clicado");
            System.out.println("Botao clicado"+btn_clicado);
            try {
                    parcelasLocal = importarArvores(idLocal);
                    int idMetodoCalculo = 1;
            //        if(btn_clicado.equals("1"))
            //        {
            //            System.out.println("Entrei para equação 1111");
                            // idMetodoCalculo = 1; 
            //        }else
            //        {   
            //            System.out.println("Entrei para equação 22222");
            //            idMetodoCalculo = 2; //DM     
            //        }
            //Fim-se
            double qtdeTela  = calcular(idLocal,idVariavelInteresse,idMetodoCalculo,parcelasLocal);
            
            DecimalFormat df2casas = new DecimalFormat("##,###,###,##0.00");            
                
            String qtdeTelaStr = df2casas.format(qtdeTela);            
                
            String retorno = "";
            //retorno += "alert('Cálculo efetuado com sucesso!');";
            retorno += "$('#total_calculado_arvore_equacao').val('"+qtdeTelaStr+"')";
            System.out.println("Valor Tela:"+qtdeTelaStr);
            out.println(retorno);
            
            locale = new Locale("pt","BR");  
            calendar = new GregorianCalendar();
            formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm':'ss'h'",locale);
            System.out.println("FIM: "+formatador.format(calendar.getTime()));
                

            } catch (SQLException ex) {
                Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
    
    public ArrayList<Parcela> importarArvores(int idLocal) throws SQLException, BiffException {
  
        ArrayList<Parcela> parcelasLocal = new ArrayList<Parcela>();
        Local local = new Local();
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);
        Arvore arvore = new Arvore();
        parcelasLocal = arvore.importarPlanilha(local);
        return parcelasLocal;
        
    }
    
    public double calcular(int idLocal,int idVariavelInteresse,int idMetodoCalculo,ArrayList<Parcela> parcelasLocal) throws SQLException, BiffException, Exception {
    
        int idTipoEstimativa = 3;  //Fixo devido a seleção do Cálculo com Árvores
    
        Local local = new Local();
        LocalDao localDao = new LocalDao();
    
        local = localDao.getLocal(idLocal);
        local.setIdTipoEstimativa(idTipoEstimativa);
        if (idMetodoCalculo==2) { //Data Mining   //******VER COM PV COMO PEDIR ESTES PARÂMETROS NA TELA, POIS SÓ SÃO
                                                  //      NECESSÁRIOS NO BOTÃO "CALCULAR COM DATA MINING"
            int idDMTipoDistancia = 1;  //1-Distancia Euclidiana
                                        //2-Distancia Quadrática
                                        //3-Distancia Manhathan
                                        //4-Distancia Chebychev
            int idDMTipoPonderacao = 3; //1-Sem Ponderação
                                        //2-1/d
                                        //3-1/d2
            int dmQtdeVizinhos = 3;     //1,3 ou 5
            boolean dmComLn = false;    //com ou sem aplicar LN nas variáveis
            local.setIdDMTipoDistancia(idDMTipoDistancia);
            local.setIdDMTipoPonderacao(idDMTipoPonderacao);
            local.setDmQtdeVizinhos(dmQtdeVizinhos);
            local.setDmComLn(dmComLn);
        }        
        localDao = new LocalDao();        
        localDao.update(local);
    
        //ArrayList<Parcela> parcelasLocal = new ArrayList<Parcela>();
        //parcelasLocal = local.getParcelas();
    
        for (Parcela parcela : parcelasLocal) {
             parcela.calculaQtdeEstimada(local,idVariavelInteresse,idMetodoCalculo);
        }
    
        EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
        estatisticaInventario.setIdLocal(idLocal);
        estatisticaInventario.setIdVariavelInteresse(idVariavelInteresse);
        estatisticaInventario.setIdMetodoCalculo(idMetodoCalculo);
        estatisticaInventario.calcularEstatisticas(local,parcelasLocal);
    
        //CAMPO PARA SER ENVIADO PARA A TELA para ser colocado no CAMPO Total Calculado (t/ha)
        //Se idMetodoCalculo = 1; //Equacao
            double qtdeTela = estatisticaInventario.getQtdeMedia();
        //Senão
            double qtdeTelaDataMining = estatisticaInventario.getQtdeMedia();        
        //Fim-se
            
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
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
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

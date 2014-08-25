/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AutorDao;
import dao.DMTipoDistanciaDao;
import dao.DMTipoPonderacaoDao;
import dao.EstatisticaAjusteDao;
import dao.EstatisticaInventarioDao;
import dao.LocalDao;
import dao.VariavelInteresseDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autor;
import model.EstatisticaAjuste;
import model.EstatisticaInventario;
import model.Local;
import model.VariavelInteresse;

/**
 *
 * @author jaime
 */
public class listarDetalhesCalculoParcelas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
         try (PrintWriter out = response.getWriter())  {

            DecimalFormat df4casas = new DecimalFormat("##,###,###,##0.0000");
            DecimalFormat df1casa = new DecimalFormat("##,###,###,##0.0");
            DecimalFormat df2casas = new DecimalFormat("##,###,###,##0.00");

            String idLocalStr = request.getParameter("local_id");
            String idVariavelInteresseStr = request.getParameter("variavel_interesse");
            int idVariavelInteresse = Integer.parseInt(idVariavelInteresseStr);
                
            LocalDao localDao = new LocalDao();
            Local local = new Local();
            local = localDao.getLocal(Integer.parseInt(idLocalStr));
                
            VariavelInteresseDao variavelInteresseDao = new VariavelInteresseDao();
            VariavelInteresse variavelInteresse = new VariavelInteresse();
            variavelInteresse = variavelInteresseDao.getVariavelInteresse(idVariavelInteresse);
            
            int idMetodoCalculo = 1; //Equação
            String descricaoMetodoCalculo = "Inventario";            
            
            String retorno1 = "";     
            
            EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
            EstatisticaInventarioDao estatisticaInventarioDao = new EstatisticaInventarioDao();      
            estatisticaInventario = estatisticaInventarioDao.getEstatisticaInventario(local.getId(), idVariavelInteresse, idMetodoCalculo);
            
            String mediaParcelaStr               = df2casas.format(estatisticaInventario.getMediaParcela());
            String coeficienteVariacaoStr        = df4casas.format(estatisticaInventario.getCoeficienteVariacao());
            String desvioPadraoStr               = df4casas.format(estatisticaInventario.getDesvioPadrao());
            String erroAbsolutoStr               = df4casas.format(estatisticaInventario.getErroAbsoluto());
            String erroPadraoStr                 = df4casas.format(estatisticaInventario.getErroPadrao());
            String erroRelativoStr               = df4casas.format(estatisticaInventario.getErroRelativo());
            String intervaloConfiancaMaxMediaStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMaxMedia());
            String intervaloConfiancaMaxTotalStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMaxTotal());
            String intervaloConfiancaMinMediaStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMinMedia());
            String intervaloConfiancaMinTotalStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMinTotal());
            String qtdeMediaStr                  = df2casas.format(estatisticaInventario.getQtdeMedia());
            String varianciaStr                  = df4casas.format(estatisticaInventario.getVariancia());
            String varianciaMediaStr             = df4casas.format(estatisticaInventario.getVarianciaMedia());
            
            retorno1 += "<p><b>Local: </b>"+local.getDescricao()+ "</p>";
            retorno1 += "<p><b>Variavel de Interesse: </b>"+variavelInteresse.getNome()+ "</p>";
            retorno1 += "<p><b>Metodo de Calculo: </b>"+descricaoMetodoCalculo+ "</p>";            
            retorno1 += "<table>";
            retorno1 += "<thead><tr><td>Estatisticas da Estimativa</td></thead>";
            retorno1 += "<tbody>";
            retorno1 += "<tr><td>Media por Parcela:</td><td align=\"right\">"+mediaParcelaStr+"</td></tr>";
            retorno1 += "<tr><td>Variancia:</td><td align=\"right\">"+varianciaStr+"</td></tr>";            
            retorno1 += "<tr><td>Desvio Padrao:</td><td align=\"right\">"+desvioPadraoStr+"</td></tr>";            
            retorno1 += "<tr><td>Variancia Media:</td><td align=\"right\">"+varianciaMediaStr+"</td></tr>";            
            retorno1 += "<tr><td>Erro Padrao:</td><td align=\"right\">"+erroPadraoStr+"</td></tr>";            
            retorno1 += "<tr><td>Coeficiente de Variacao:</td><td align=\"right\">"+coeficienteVariacaoStr+"</td></tr>";            
            retorno1 += "<tr><td>Erro Absoluto:</td><td align=\"right\">"+erroAbsolutoStr+"</td></tr>";            
            retorno1 += "<tr><td>Erro Relativo:</td><td align=\"right\">"+erroRelativoStr+"</td></tr>";                        
            retorno1 += "<tr><td>Intervalo de Confianca Min(Media):</td><td align=\"right\">"+intervaloConfiancaMinMediaStr+"</td></tr>";                                    
            retorno1 += "<tr><td>Intervalo de Confianca Max(Media):</td><td align=\"right\">"+intervaloConfiancaMaxMediaStr+"</td></tr>";                                                
            retorno1 += "<tr><td>Valor Total Minimo:</td><td align=\"right\">"+intervaloConfiancaMinTotalStr+"</td></tr>";                                    
            retorno1 += "<tr><td>Valor Total Maximo:</td><td align=\"right\">"+intervaloConfiancaMaxTotalStr+"</td></tr>";                                                
            retorno1 += "<tr><td> </td><td> </td></tr>";                                                
            retorno1 += "<tr><td>Valor Total Medio:</td><td align=\"right\">"+qtdeMediaStr+"</td></tr>";                                                            
            retorno1 += "</tbody>";
            retorno1 += "</table>";
            
            String retorno2="";
            retorno2 += "$('#dialog_ver_detalhes_calculo_parcela_inside').html('"+retorno1+"');";
            out.println(retorno2);
            
        } finally { 
//            request.getRequestDispatcher("listarDetalhesCalculoParcelas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculoParcelas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculoParcelas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Action Listar Detalhes CalculoParcelas";
    }// </editor-fold>
}

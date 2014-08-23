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
public class listarDetalhesCalculoArvoresEquacao extends HttpServlet {

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
            String descricaoMetodoCalculo = "Equacao";
            
            String retorno1 = "";            

            EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
            EstatisticaInventarioDao estatisticaInventarioDao = new EstatisticaInventarioDao();      
            estatisticaInventario = estatisticaInventarioDao.getEstatisticaInventario(local.getId(), idVariavelInteresse, idMetodoCalculo);
            
            String mediaParcelaStr               = df2casas.format(estatisticaInventario.getMediaParcela());
            String coeficienteVariacaoStr        = df4casas.format(estatisticaInventario.getCoeficienteVariacao());
            String desvioPadraoStr               = df2casas.format(estatisticaInventario.getDesvioPadrao());
            String erroAbsolutoStr               = df4casas.format(estatisticaInventario.getErroAbsoluto());
            String erroPadraoStr                 = df4casas.format(estatisticaInventario.getErroPadrao());
            String erroRelativoStr               = df4casas.format(estatisticaInventario.getErroRelativo());
            String intervaloConfiancaMaxMediaStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMaxMedia());
            String intervaloConfiancaMaxTotalStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMaxTotal());
            String intervaloConfiancaMinMediaStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMinMedia());
            String intervaloConfiancaMinTotalStr = df2casas.format(estatisticaInventario.getIntervaloConfiancaMinTotal());
            String qtdeMediaStr                  = df2casas.format(estatisticaInventario.getQtdeMedia());
            String varianciaStr                  = df2casas.format(estatisticaInventario.getVariancia());
            String varianciaMediaStr             = df2casas.format(estatisticaInventario.getVarianciaMedia());
            
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
                    
            EstatisticaAjuste estatisticaAjuste = new EstatisticaAjuste();
            EstatisticaAjusteDao estatisticaAjusteDao = new EstatisticaAjusteDao();      
            estatisticaAjuste = estatisticaAjusteDao.getEstatisticaAjuste(local.getId(), idVariavelInteresse, idMetodoCalculo);
            
            if (estatisticaAjuste.getId()!=0) {
                String r2Str                    = df4casas.format(estatisticaAjuste.getR2());
                String r2AjustStr               = df4casas.format(estatisticaAjuste.getR2Ajust());
                String syxStr                   = df4casas.format(estatisticaAjuste.getSyx());
                String syxPercStr               = df4casas.format(estatisticaAjuste.getSyxPerc());
                String iaStr                    = df4casas.format(estatisticaAjuste.getIa());
                String aicStr                   = df4casas.format(estatisticaAjuste.getAic());
                String bicStr                   = df4casas.format(estatisticaAjuste.getBic());
                String wilmottStr               = df4casas.format(estatisticaAjuste.getWillmott());
                String somaQuadradoResiduoStr   = df4casas.format(estatisticaAjuste.getSomaQuadradoResiduo());
                String somaQuadradoRegressaoStr = df4casas.format(estatisticaAjuste.getSomaQuadradoRegressao());
                String somaQuadradoTotaisStr    = df4casas.format(estatisticaAjuste.getSomaQuadradoTotais());
                
                retorno1 += "<br></br>";
                retorno1 += "<thead><tr><td>Estatisticas do Ajuste</td></thead>";
                retorno1 += "<tbody>";
                retorno1 += "<tr><td>R2:</td><td align=\"right\">"+r2Str+"</td></tr>";
                retorno1 += "<tr><td>R2 Ajustado:</td><td align=\"right\">"+r2AjustStr+"</td></tr>";            
                retorno1 += "<tr><td>Syx:</td><td align=\"right\">"+syxStr+"</td></tr>";            
                retorno1 += "<tr><td>Syx(%):</td><td align=\"right\">"+syxPercStr+"</td></tr>";            
                retorno1 += "<tr><td>Ia:</td><td align=\"right\">"+iaStr+"</td></tr>";            
                retorno1 += "<tr><td>Aic:</td><td align=\"right\">"+aicStr+"</td></tr>";            
                retorno1 += "<tr><td>Bic:</td><td align=\"right\">"+bicStr+"</td></tr>";            
                retorno1 += "<tr><td>Wilmott:</td><td align=\"right\">"+wilmottStr+"</td></tr>";                        
                retorno1 += "<tr><td>Soma dos Quadrados dos Residuos:</td><td align=\"right\">"+somaQuadradoResiduoStr+"</td></tr>";                                    
                retorno1 += "<tr><td>Soma dos Quadrados de Regressao:</td><td align=\"right\">"+somaQuadradoRegressaoStr+"</td></tr>";                                                    
                retorno1 += "<tr><td>Soma dos Quadrados Totais:</td><td align=\"right\">"+somaQuadradoTotaisStr+"</td></tr>";                                                                    
                retorno1 += "</tbody>";
                retorno1 += "</table>";
            }        
            
            String retorno2="";
            retorno2 += "$('#dialog_ver_detalhes_calculo_arvore_eq_inside').html('"+retorno1+"');";
            out.println(retorno2);
            
            
        } finally { 
           // request.getRequestDispatcher("listarDetalhesCalculoArvores.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculoArvoresEquacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculoArvoresEquacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Action Listar Detalhes Calculo Arvores";
    }// </editor-fold>
}

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
            String descricaoMetodoCalculo = "Equação";

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
                    
            EstatisticaAjuste estatisticaAjuste = new EstatisticaAjuste();
            EstatisticaAjusteDao estatisticaAjusteDao = new EstatisticaAjusteDao();      
            estatisticaAjuste = estatisticaAjusteDao.getEstatisticaAjuste(local.getId(), idVariavelInteresse, idMetodoCalculo);
            
            String r2Str                    = "";
            String r2AjustStr               = "";
            String syxStr                   = "";
            String syxPercStr               = "";
            String iaStr                    = "";
            String aicStr                   = "";
            String bicStr                   = "";
            String wilmottStr               = "";
            String somaQuadradoResiduoStr   = "";
            String somaQuadradoRegressaoStr = "";
            String somaQuadradoTotaisStr    = "";
            
            if (estatisticaAjuste.getId()!=0) {
                r2Str                    = df4casas.format(estatisticaAjuste.getR2());
                r2AjustStr               = df4casas.format(estatisticaAjuste.getR2Ajust());
                syxStr                   = df4casas.format(estatisticaAjuste.getSyx());
                syxPercStr               = df4casas.format(estatisticaAjuste.getSyxPerc());
                iaStr                    = df4casas.format(estatisticaAjuste.getIa());
                aicStr                   = df4casas.format(estatisticaAjuste.getAic());
                bicStr                   = df4casas.format(estatisticaAjuste.getBic());
                wilmottStr               = df4casas.format(estatisticaAjuste.getWillmott());
                somaQuadradoResiduoStr   = df4casas.format(estatisticaAjuste.getSomaQuadradoResiduo());
                somaQuadradoRegressaoStr = df4casas.format(estatisticaAjuste.getSomaQuadradoRegressao());
                somaQuadradoTotaisStr    = df4casas.format(estatisticaAjuste.getSomaQuadradoTotais());
            }        

            request.setAttribute("local", local);
            request.setAttribute("variavelInteresse", variavelInteresse);
            request.setAttribute("descricaoMetodoCalculo", descricaoMetodoCalculo);
            
            request.setAttribute("mediaParcelaStr", mediaParcelaStr);            
            request.setAttribute("coeficienteVariacaoStr", coeficienteVariacaoStr); 
            request.setAttribute("desvioPadraoStr", desvioPadraoStr); 
            request.setAttribute("erroAbsolutoStr", erroAbsolutoStr); 
            request.setAttribute("erroPadraoStr", erroPadraoStr); 
            request.setAttribute("erroRelativoStr", erroRelativoStr); 
            request.setAttribute("intervaloConfiancaMaxMediaStr", intervaloConfiancaMaxMediaStr); 
            request.setAttribute("intervaloConfiancaMinMediaStr", intervaloConfiancaMinMediaStr); 
            request.setAttribute("intervaloConfiancaMaxTotalStr", intervaloConfiancaMaxTotalStr); 
            request.setAttribute("intervaloConfiancaMinTotalStr", intervaloConfiancaMinTotalStr); 
            request.setAttribute("qtdeMediaStr", qtdeMediaStr); 
            request.setAttribute("varianciaStr", varianciaStr); 
            request.setAttribute("varianciaMediaStr", varianciaMediaStr); 

            request.setAttribute("r2Str", r2Str);             
            request.setAttribute("r2AjustStr", r2AjustStr);             
            request.setAttribute("syxStr", syxStr);             
            request.setAttribute("syxPercStr", syxPercStr);             
            request.setAttribute("iaStr", iaStr);             
            request.setAttribute("aicStr", aicStr);             
            request.setAttribute("bicStr", bicStr);             
            request.setAttribute("wilmottStr", wilmottStr);             
            request.setAttribute("somaQuadradoResiduoStr", somaQuadradoResiduoStr);             
            request.setAttribute("somaQuadradoRegressaoStr", somaQuadradoRegressaoStr);             
            request.setAttribute("somaQuadradoTotaisStr", somaQuadradoTotaisStr);
            
            String retorno1 = "";
            retorno1 += "<p><b>Local: </b>"+local.getDescricao()+ "</p>";
            retorno1 += "<p><b>Variavel de Interesse: </b>"+variavelInteresse.getNome()+ "</p>";
            retorno1 += "<table>";
            retorno1 += "<thead><tr><td>Estatisticas do Calculo</td></thead>";
            retorno1 += "<tbody>";
            retorno1 += "<tr><td>Media</td><td>"+mediaParcelaStr+"</td></tr>";
            retorno1 += "</tbody>";
            retorno1 += "</table>";
            
            String retorno2="";
            retorno2 += "$('#dialog_ver_detalhes_calculo_arvore_eq').append('"+retorno1+"');";
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

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
import java.io.IOException;
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

/**
 *
 * @author jaime
 */
public class listarDetalhesCalculoParcelas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {

            DecimalFormat df4casas = new DecimalFormat("##,###,###,##0.0000");
            DecimalFormat df1casa = new DecimalFormat("##,###,###,##0.0");
            DecimalFormat df2casas = new DecimalFormat("##,###,###,##0.00");

            int idLocal = 1;
            int idMetodoCalculo = 0;
            int idVariavelInteresse = 1;
            String variavelInteresse = "Biomassa";
            
            Local local = new Local();
            LocalDao localDao = new LocalDao();
            local = localDao.getLocal(idLocal);
            
            idMetodoCalculo = 1; //Equação
            EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
            EstatisticaInventarioDao estatisticaInventarioDao = new EstatisticaInventarioDao();      
            estatisticaInventario = estatisticaInventarioDao.getEstatisticaInventario(idLocal, idVariavelInteresse, idMetodoCalculo);
            
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

            request.setAttribute("local", local);
            request.setAttribute("variavelInteresse", variavelInteresse);
            
            request.setAttribute("estatisticaInventario", estatisticaInventario);
            
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
            
        } finally { 
            request.getRequestDispatcher("listarDetalhesCalculoParcelas.jsp").forward(request, response);
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

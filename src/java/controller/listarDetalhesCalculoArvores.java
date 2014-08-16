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
public class listarDetalhesCalculoArvores extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {

            DecimalFormat df4casas = new DecimalFormat("##,###,###,##0.0000");
            DecimalFormat df1casa = new DecimalFormat("##,###,###,##0.0");
            DecimalFormat df2casas = new DecimalFormat("##,###,###,##0.00");
        
            int idLocal = 9;
            int idMetodoCalculo = 0;
            int idVariavelInteresse = 1;
            String variavelInteresse = "Biomassa";
            
            Local local = new Local();
            LocalDao localDao = new LocalDao();
            local = localDao.getLocal(idLocal);
            
            DMTipoDistanciaDao dmTipoDistanciaDao = new DMTipoDistanciaDao();
            String descricaoTipoDistancia =dmTipoDistanciaDao.getDescricao(local.getIdDMTipoDistancia());
            DMTipoPonderacaoDao dmTipoPonderacaoDao = new DMTipoPonderacaoDao();
            String descricaoTipoPonderacao =dmTipoPonderacaoDao.getDescricao(local.getIdDMTipoDistancia());
            
            String descricaoComLn = "Não";
            if (local.isDmComLn()) {
                descricaoComLn = "Sim";
            }             
            
            idMetodoCalculo = 1; //Equação
            EstatisticaInventario estatisticaInventarioEquacao = new EstatisticaInventario();
            EstatisticaInventarioDao estatisticaInventarioDao = new EstatisticaInventarioDao();      
            estatisticaInventarioEquacao = estatisticaInventarioDao.getEstatisticaInventario(idLocal, idVariavelInteresse, idMetodoCalculo);
            
            String equacaoMediaParcelaStr               = df2casas.format(estatisticaInventarioEquacao.getMediaParcela());
            String equacaoCoeficienteVariacaoStr        = df4casas.format(estatisticaInventarioEquacao.getCoeficienteVariacao());
            String equacaoDesvioPadraoStr               = df4casas.format(estatisticaInventarioEquacao.getDesvioPadrao());
            String equacaoErroAbsolutoStr               = df4casas.format(estatisticaInventarioEquacao.getErroAbsoluto());
            String equacaoErroPadraoStr                 = df4casas.format(estatisticaInventarioEquacao.getErroPadrao());
            String equacaoErroRelativoStr               = df4casas.format(estatisticaInventarioEquacao.getErroRelativo());
            String equacaoIntervaloConfiancaMaxMediaStr = df2casas.format(estatisticaInventarioEquacao.getIntervaloConfiancaMaxMedia());
            String equacaoIntervaloConfiancaMaxTotalStr = df2casas.format(estatisticaInventarioEquacao.getIntervaloConfiancaMaxTotal());
            String equacaoIntervaloConfiancaMinMediaStr = df2casas.format(estatisticaInventarioEquacao.getIntervaloConfiancaMinMedia());
            String equacaoIntervaloConfiancaMinTotalStr = df2casas.format(estatisticaInventarioEquacao.getIntervaloConfiancaMinTotal());
            String equacaoQtdeMediaStr                  = df2casas.format(estatisticaInventarioEquacao.getQtdeMedia());
            String equacaoVarianciaStr                  = df4casas.format(estatisticaInventarioEquacao.getVariancia());
            String equacaoVarianciaMediaStr             = df4casas.format(estatisticaInventarioEquacao.getVarianciaMedia());
                    
            idMetodoCalculo = 2; //Data Mining
            EstatisticaInventario estatisticaInventarioDm = new EstatisticaInventario();
            estatisticaInventarioDao = new EstatisticaInventarioDao();      
            estatisticaInventarioDm = estatisticaInventarioDao.getEstatisticaInventario(idLocal, idVariavelInteresse, idMetodoCalculo);
            String dmMediaParcelaStr               = df2casas.format(estatisticaInventarioDm.getMediaParcela());
            String dmCoeficienteVariacaoStr        = df4casas.format(estatisticaInventarioDm.getCoeficienteVariacao());
            String dmDesvioPadraoStr               = df4casas.format(estatisticaInventarioDm.getDesvioPadrao());
            String dmErroAbsolutoStr               = df4casas.format(estatisticaInventarioDm.getErroAbsoluto());
            String dmErroPadraoStr                 = df4casas.format(estatisticaInventarioDm.getErroPadrao());
            String dmErroRelativoStr               = df4casas.format(estatisticaInventarioDm.getErroRelativo());
            String dmIntervaloConfiancaMaxMediaStr = df2casas.format(estatisticaInventarioDm.getIntervaloConfiancaMaxMedia());
            String dmIntervaloConfiancaMaxTotalStr = df2casas.format(estatisticaInventarioDm.getIntervaloConfiancaMaxTotal());
            String dmIntervaloConfiancaMinMediaStr = df2casas.format(estatisticaInventarioDm.getIntervaloConfiancaMinMedia());
            String dmIntervaloConfiancaMinTotalStr = df2casas.format(estatisticaInventarioDm.getIntervaloConfiancaMinTotal());
            String dmQtdeMediaStr                  = df2casas.format(estatisticaInventarioDm.getQtdeMedia());
            String dmVarianciaStr                  = df4casas.format(estatisticaInventarioDm.getVariancia());
            String dmVarianciaMediaStr             = df4casas.format(estatisticaInventarioDm.getVarianciaMedia());            


            idMetodoCalculo = 1; //Equação
            EstatisticaAjuste estatisticaAjusteEquacao = new EstatisticaAjuste();
            EstatisticaAjusteDao estatisticaAjusteDao = new EstatisticaAjusteDao();      
            estatisticaAjusteEquacao               = estatisticaAjusteDao.getEstatisticaAjuste(idLocal, idVariavelInteresse, idMetodoCalculo);
            
            String equacaoR2Str                    = "";
            String equacaoR2AjustStr               = "";
            String equacaoSyxStr                   = "";
            String equacaoSyxPercStr               = "";
            String equacaoIaStr                    = "";
            String equacaoAicStr                   = "";
            String equacaoBicStr                   = "";
            String equacaoWilmottStr               = "";
            String equacaoSomaQuadradoResiduoStr   = "";
            String equacaoSomaQuadradoRegressaoStr = "";
            String equacaoSomaQuadradoTotaisStr    = "";
            
            if (estatisticaAjusteEquacao.getId()!=0) {
                equacaoR2Str                    = df4casas.format(estatisticaAjusteEquacao.getR2());
                equacaoR2AjustStr               = df4casas.format(estatisticaAjusteEquacao.getR2Ajust());
                equacaoSyxStr                   = df4casas.format(estatisticaAjusteEquacao.getSyx());
                equacaoSyxPercStr               = df4casas.format(estatisticaAjusteEquacao.getSyxPerc());
                equacaoIaStr                    = df4casas.format(estatisticaAjusteEquacao.getIa());
                equacaoAicStr                   = df4casas.format(estatisticaAjusteEquacao.getAic());
                equacaoBicStr                   = df4casas.format(estatisticaAjusteEquacao.getBic());
                equacaoWilmottStr               = df4casas.format(estatisticaAjusteEquacao.getWillmott());
                equacaoSomaQuadradoResiduoStr   = df4casas.format(estatisticaAjusteEquacao.getSomaQuadradoResiduo());
                equacaoSomaQuadradoRegressaoStr = df4casas.format(estatisticaAjusteEquacao.getSomaQuadradoRegressao());
                equacaoSomaQuadradoTotaisStr    = df4casas.format(estatisticaAjusteEquacao.getSomaQuadradoTotais());
            }        
            idMetodoCalculo = 2; //Data Mining           
            EstatisticaAjuste estatisticaAjusteDm = new EstatisticaAjuste();
            estatisticaAjusteDao = new EstatisticaAjusteDao();      
            estatisticaAjusteDm = estatisticaAjusteDao.getEstatisticaAjuste(idLocal, idVariavelInteresse, idMetodoCalculo);
            
            String dmR2Str                    = "";
            String dmR2AjustStr               = "";
            String dmSyxStr                   = "";
            String dmSyxPercStr               = "";
            String dmIaStr                    = "";
            String dmAicStr                   = "";
            String dmBicStr                   = "";
            String dmWilmottStr               = "";
            String dmSomaQuadradoResiduoStr   = "";
            String dmSomaQuadradoRegressaoStr = "";
            String dmSomaQuadradoTotaisStr    = "";
            
            if (estatisticaAjusteDm.getId()!=0) {
                dmR2Str                    = df4casas.format(estatisticaAjusteDm.getR2());
                dmR2AjustStr               = df4casas.format(estatisticaAjusteDm.getR2Ajust());
                dmSyxStr                   = df4casas.format(estatisticaAjusteDm.getSyx());
                dmSyxPercStr               = df4casas.format(estatisticaAjusteDm.getSyxPerc());
                dmIaStr                    = df4casas.format(estatisticaAjusteDm.getIa());
                dmAicStr                   = df4casas.format(estatisticaAjusteDm.getAic());
                dmBicStr                   = df4casas.format(estatisticaAjusteDm.getBic());
                dmWilmottStr               = df4casas.format(estatisticaAjusteDm.getWillmott());
                dmSomaQuadradoResiduoStr   = df4casas.format(estatisticaAjusteDm.getSomaQuadradoResiduo());
                dmSomaQuadradoRegressaoStr = df4casas.format(estatisticaAjusteDm.getSomaQuadradoRegressao());
                dmSomaQuadradoTotaisStr    = df4casas.format(estatisticaAjusteDm.getSomaQuadradoTotais());            
            }

            request.setAttribute("local", local);
            request.setAttribute("variavelInteresse", variavelInteresse);
            request.setAttribute("descricaoTipoDistancia", descricaoTipoDistancia);
            request.setAttribute("descricaoTipoPonderacao", descricaoTipoPonderacao);
            request.setAttribute("descricaoComLn", descricaoComLn);
            
            request.setAttribute("estatisticaInventarioEquacao", estatisticaInventarioEquacao);
            request.setAttribute("estatisticaInventarioDm", estatisticaInventarioDm);
            request.setAttribute("estatisticaAjusteEquacao", estatisticaAjusteEquacao);
            request.setAttribute("estatisticaAjusteDm", estatisticaAjusteDm);
            
            request.setAttribute("equacaoMediaParcelaStr", equacaoMediaParcelaStr);            
            request.setAttribute("equacaoCoeficienteVariacaoStr", equacaoCoeficienteVariacaoStr); 
            request.setAttribute("equacaoDesvioPadraoStr", equacaoDesvioPadraoStr); 
            request.setAttribute("equacaoErroAbsolutoStr", equacaoErroAbsolutoStr); 
            request.setAttribute("equacaoErroPadraoStr", equacaoErroPadraoStr); 
            request.setAttribute("equacaoErroRelativoStr", equacaoErroRelativoStr); 
            request.setAttribute("equacaoIntervaloConfiancaMaxMediaStr", equacaoIntervaloConfiancaMaxMediaStr); 
            request.setAttribute("equacaoIntervaloConfiancaMinMediaStr", equacaoIntervaloConfiancaMinMediaStr); 
            request.setAttribute("equacaoIntervaloConfiancaMaxTotalStr", equacaoIntervaloConfiancaMaxTotalStr); 
            request.setAttribute("equacaoIntervaloConfiancaMinTotalStr", equacaoIntervaloConfiancaMinTotalStr); 
            request.setAttribute("equacaoQtdeMediaStr", equacaoQtdeMediaStr); 
            request.setAttribute("equacaoVarianciaStr", equacaoVarianciaStr); 
            request.setAttribute("equacaoVarianciaMediaStr", equacaoVarianciaMediaStr); 
            
            request.setAttribute("dmMediaParcelaStr", dmMediaParcelaStr);            
            request.setAttribute("dmCoeficienteVariacaoStr", dmCoeficienteVariacaoStr); 
            request.setAttribute("dmDesvioPadraoStr", dmDesvioPadraoStr); 
            request.setAttribute("dmErroAbsolutoStr", dmErroAbsolutoStr); 
            request.setAttribute("dmErroPadraoStr", dmErroPadraoStr); 
            request.setAttribute("dmErroRelativoStr", dmErroRelativoStr); 
            request.setAttribute("dmIntervaloConfiancaMaxMediaStr", dmIntervaloConfiancaMaxMediaStr); 
            request.setAttribute("dmIntervaloConfiancaMinMediaStr", dmIntervaloConfiancaMinMediaStr); 
            request.setAttribute("dmIntervaloConfiancaMaxTotalStr", dmIntervaloConfiancaMaxTotalStr); 
            request.setAttribute("dmIntervaloConfiancaMinTotalStr", dmIntervaloConfiancaMinTotalStr); 
            request.setAttribute("dmQtdeMediaStr", dmQtdeMediaStr); 
            request.setAttribute("dmVarianciaStr", dmVarianciaStr); 
            request.setAttribute("dmVarianciaMediaStr", dmVarianciaMediaStr); 
            
            request.setAttribute("equacaoR2Str", equacaoR2Str);             
            request.setAttribute("equacaoR2AjustStr", equacaoR2AjustStr);             
            request.setAttribute("equacaoSyxStr", equacaoSyxStr);             
            request.setAttribute("equacaoSyxPercStr", equacaoSyxPercStr);             
            request.setAttribute("equacaoIaStr", equacaoIaStr);             
            request.setAttribute("equacaoAicStr", equacaoAicStr);             
            request.setAttribute("equacaoBicStr", equacaoBicStr);             
            request.setAttribute("equacaoWilmottStr", equacaoWilmottStr);             
            request.setAttribute("equacaoSomaQuadradoResiduoStr", equacaoSomaQuadradoResiduoStr);             
            request.setAttribute("equacaoSomaQuadradoRegressaoStr", equacaoSomaQuadradoRegressaoStr);             
            request.setAttribute("equacaoSomaQuadradoTotaisStr", equacaoSomaQuadradoTotaisStr);             
            
            request.setAttribute("dmR2Str", dmR2Str);             
            request.setAttribute("dmR2AjustStr", dmR2AjustStr);             
            request.setAttribute("dmSyxStr", dmSyxStr);             
            request.setAttribute("dmSyxPercStr", dmSyxPercStr);             
            request.setAttribute("dmIaStr", dmIaStr);             
            request.setAttribute("dmAicStr", dmAicStr);             
            request.setAttribute("dmBicStr", dmBicStr);             
            request.setAttribute("dmWilmottStr", dmWilmottStr);             
            request.setAttribute("dmSomaQuadradoResiduoStr", dmSomaQuadradoResiduoStr);             
            request.setAttribute("dmSomaQuadradoRegressaoStr", dmSomaQuadradoRegressaoStr);             
            request.setAttribute("dmSomaQuadradoTotaisStr", dmSomaQuadradoTotaisStr);             
            
        } finally { 
            request.getRequestDispatcher("listarDetalhesCalculoArvores.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculoArvores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculoArvores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Action Listar Detalhes Calculo Arvores";
    }// </editor-fold>
}

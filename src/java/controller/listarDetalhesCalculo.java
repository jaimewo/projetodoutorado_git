/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AutorDao;
import dao.DMTipoDistanciaDao;
import dao.DMTipoPonderacaoDao;
import dao.EstatisticaInventarioDao;
import dao.LocalDao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autor;
import model.EstatisticaInventario;
import model.Local;

/**
 *
 * @author jaime
 */
public class listarDetalhesCalculo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {

             
            int idLocal = 1;
            int idMetodoCalculo = 0;
            int idVariavelInteresse = 1;
            String variavelInteresse = "Biomassa";
            
            Local local = new Local();
            LocalDao localDao = new LocalDao();
            local = localDao.getLocal(idLocal);
            
            idMetodoCalculo = 1; //Equação
            EstatisticaInventario estatisticaInventarioEquacao = new EstatisticaInventario();
            EstatisticaInventarioDao estatisticaInventarioDao = new EstatisticaInventarioDao();      
            estatisticaInventarioEquacao = estatisticaInventarioDao.getEstatisticaInventario(idLocal, idVariavelInteresse, idMetodoCalculo);
            
            idMetodoCalculo = 2; //Data Mining
            EstatisticaInventario estatisticaInventarioDm = new EstatisticaInventario();
            estatisticaInventarioDao = new EstatisticaInventarioDao();      
            estatisticaInventarioDm = estatisticaInventarioDao.getEstatisticaInventario(idLocal, idVariavelInteresse, idMetodoCalculo);

            
            DMTipoDistanciaDao dmTipoDistanciaDao = new DMTipoDistanciaDao();
            String descricaoTipoDistancia =dmTipoDistanciaDao.getDescricao(local.getIdDMTipoDistancia());
            DMTipoPonderacaoDao dmTipoPonderacaoDao = new DMTipoPonderacaoDao();
            String descricaoTipoPonderacao =dmTipoPonderacaoDao.getDescricao(local.getIdDMTipoDistancia());
            
            String descricaoComLn = "Não";
            if (local.isDmComLn()) {
                descricaoComLn = "Sim";
            } 

            request.setAttribute("local", local);
            request.setAttribute("variavelInteresse", variavelInteresse);
            request.setAttribute("descricaoTipoDistancia", descricaoTipoDistancia);
            request.setAttribute("descricaoTipoPonderacao", descricaoTipoPonderacao);
            request.setAttribute("descricaoComLn", descricaoComLn);
            request.setAttribute("estatisticaInventarioEquacao", estatisticaInventarioEquacao);
            request.setAttribute("estatisticaInventarioDm", estatisticaInventarioDm);
//            request.setAttribute("estatisticaAjusteEquacao", estatisticaAjusteEquacao);
//            request.setAttribute("estatisticaAjusteDm", estatisticaAjusteDm);
            
           
        } finally { 
            request.getRequestDispatcher("listarDetalhesCalculo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(listarDetalhesCalculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Action Listar Detalhes Calculo";
    }// </editor-fold>
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AutorDao;
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
            
        //<% Local objeto_local                                 = (Local) request.getAttribute("local");%> 
        //<% String variavelInteresse                           = (String) request.getAttribute("variavelInteresse");%> 
        
        //<% EstatisticaInventario estatisticaInventarioEquacao = (EstatisticaInventario) request.getAttribute("estatisticaInventarioEquacao");%>        
        //<% EstatisticaInventario estatisticaInventarioDm      = (EstatisticaInventario) request.getAttribute("estatisticaInventarioDm");%>        
        //<% EstatisticaAjuste estatisticaAjusteEquacao         = (EstatisticaAjuste) request.getAttribute("estatisticaAjusteEquacao");%>        
        //<% EstatisticaAjuste estatisticaAjusteDm              = (EstatisticaAjuste) request.getAttribute("estatisticaAjusteDm");%>  
             
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

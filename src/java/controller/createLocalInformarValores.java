package controller;

import dao.LocalDao;
import dao.LocalQuantidadeDao;
import dao.MunicipioLocalDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.read.biff.BiffException;
import model.Arvore;
import model.CoordenadaLocal;
import model.EstatisticaInventario;
import model.Local;
import model.LocalQuantidade;
import model.Municipio;
import model.MunicipioLocal;
import model.Parcela;
/**
 *
 * @author jaime
 */
public class createLocalInformarValores extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {

        
    //Receber da da tela
    int idLocal = Integer.parseInt(request.getParameter("local_id")); //Pegar idLocal da tela
    double qtdeBiomassa = Double.parseDouble(request.getParameter("local_biomassa")); //Receber da tela
    double qtdeCarbono = Double.parseDouble(request.getParameter("local_carbono")); //Receber da tela
    double qtdeVolume = Double.parseDouble(request.getParameter("local_volume"));; //Receber da tela
    
    LocalQuantidade localQuantidade = new LocalQuantidade();
    localQuantidade.setIdLocal(idLocal);
    localQuantidade.setIdVariavelInteresse(1);  //Biomassa
    localQuantidade.setIdMetodoCalculo(1); //Equacao
    localQuantidade.setQtde(qtdeBiomassa);
    
    LocalQuantidadeDao localQuantidadeDao = new LocalQuantidadeDao();
    localQuantidadeDao.cadastrar(localQuantidade);

    
    localQuantidade = new LocalQuantidade();
    localQuantidade.setIdLocal(idLocal);
    localQuantidade.setIdVariavelInteresse(2);  //Carbono
    localQuantidade.setIdMetodoCalculo(1); //Equacao
    localQuantidade.setQtde(qtdeCarbono);
    
    localQuantidadeDao = new LocalQuantidadeDao();
    localQuantidadeDao.cadastrar(localQuantidade);

    
    localQuantidade = new LocalQuantidade();
    localQuantidade.setIdLocal(idLocal);
    localQuantidade.setIdVariavelInteresse(3);  //Volume
    localQuantidade.setIdMetodoCalculo(1); //Equacao
    localQuantidade.setQtde(qtdeVolume);
    
    localQuantidadeDao = new LocalQuantidadeDao();
    localQuantidadeDao.cadastrar(localQuantidade);
    
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocalInformarValores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalInformarValores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocalInformarValores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalInformarValores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Local";
    }// </editor-fold>
    
}

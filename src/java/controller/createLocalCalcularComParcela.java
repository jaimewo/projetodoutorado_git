package controller;

import dao.LocalDao;
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
import model.Municipio;
import model.MunicipioLocal;
import model.Parcela;
/**
 *
 * @author jaime
 */
public class createLocalCalcularComParcela extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {

    //Receber da da tela
    int idLocal = 0; //Pegar idLocal da tela
    int idVariavelInteresse = 0; //Pegar o idVariavelInteresse do combo escolhido na tela            


    //Se pressionado botão "Escolher Arquivo com Parcelas"
         importarParcelas(idLocal);
    
    //Se pressionado botão "Baixa Arquivo Exemplo Parcelas"
         gravarPlanilhaExemplo(idLocal);
         
    //Se pressionado botão "Calcular"
         calcular(idLocal,idVariavelInteresse);
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocalCalcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocalCalcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Local";
    }// </editor-fold>

    
    public void importarParcelas(int idLocal) throws SQLException, BiffException {

        // SALVAR A PLANILHA QUE O USUÁRIO ESCOLHEU NA PASTA \...\Arquivos do projeto com o nome
        // parcela99999999.xls sendo 99999999 o id do local 
    
        Local local = new Local();
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);
        Parcela parcela = new Parcela();
        parcela.importarPlanilha(local);  
        
    }
    
    public void gravarPlanilhaExemplo(int idLocal) throws SQLException, BiffException, Exception {

        Local local = new Local();
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);

        Parcela parcela = new Parcela();
        parcela.gravarPlanilhaExemplo(local);        
    
        
    }
    public void calcular(int idLocal,int idVariavelInteresse) throws SQLException, BiffException, Exception {
    
        int idTipoEstimativa = 2;  //Fixo devido a seleção do Cálculo com Parcelas
    
        Local local = new Local();
        LocalDao localDao = new LocalDao();
    
        local = localDao.getLocal(idLocal);
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
        
    }


}

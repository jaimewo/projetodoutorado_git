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
public class createLocalCalcularComArvores extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {

        
    //Receber da da tela
    int idLocal = 0; //Pegar idLocal da tela
    int idVariavelInteresse = 0; //Pegar o idVariavelInteresse do combo escolhido na tela            
 
    //Se pressionado botão "Escolher Arquivo com Árvores"
         importarArvores(idLocal);
    
    //Se pressionado botão "Baixa Arquivo Exemplo Árvores"
         gravarPlanilhaExemplo(idLocal);
         
    //Se pressionado botão "Calcular Usando Equação"
    //OU pressionado botão "Calcular Usando Data Mining"
         int idMetodoCalculo = 0;         
         //Se pressionado botão "Calcular Usando Equação"
            idMetodoCalculo = 1; //Equação
         //senão
            idMetodoCalculo = 2; //DM            
         //Fim-se
         calcular(idLocal,idVariavelInteresse,idMetodoCalculo);
    //Fim-se
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(createLocalCalcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar Local";
    }// </editor-fold>
    
    public void importarArvores(int idLocal) throws SQLException, BiffException {
        // SALVAR A PLANILHA QUE O USUÁRIO ESCOLHEU NA PASTA \...\Arquivos do projeto com o nome
        // arvore99999999.xls sendo 99999999 o id do local    

        Local local = new Local();
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);
        Arvore arvore = new Arvore();
        arvore.importarPlanilha(local);
        
    }
    
    public void gravarPlanilhaExemplo(int idLocal) throws SQLException, BiffException, Exception {
    
        Local local = new Local();
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);
        
        Arvore arvore = new Arvore();
        arvore.gravarPlanilhaExemplo(local);
        
    }
    public void calcular(int idLocal,int idVariavelInteresse,int idMetodoCalculo) throws SQLException, BiffException, Exception {
    
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
        
        localDao.update(local);
    
        ArrayList<Parcela> parcelasLocal = new ArrayList<Parcela>();
        parcelasLocal = local.getParcelas();
    
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
            double qtdeTelaEquacao = estatisticaInventario.getQtdeMedia();
        //Senão
            double qtdeTelaDataMining = estatisticaInventario.getQtdeMedia();        
        //Fim-se
            
        
    }
    
}

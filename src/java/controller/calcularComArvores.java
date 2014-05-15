/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;




import dao.ArvoreDao;
import dao.LocalDao;
import dao.LocalDetalheBiomassaDao;
import dao.LocalDetalheCarbonoDao;
import dao.LocalDetalheVolumeDao;
import dao.ParcelaDao;
import dao.TrabalhoCientificoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Arvore;
import model.Equacao;
import model.Local;
import model.LocalDetalheBiomassa;
import model.LocalDetalheCarbono;
import model.LocalDetalheVolume;
import model.Parcela;
import model.TrabalhoCientifico;
import model.VariavelArvore;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.nfunk.jep.*;


/**
 *
 * @author jaime
 */
public class calcularComArvores extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
//            String idLocalStr = request.getParameter("id");
//            if (idLocalStr == null) {
//               Local local = new Local();
//               local.setId(1);
//               local.setQtdeBiomassa(0.0);
//               request.setAttribute("local", local );  
//               request.getRequestDispatcher("calcularComArvores.jsp").forward(request, response);
                
            String idLocalStr = "1";
            
            LocalDao localDao = new LocalDao();
            Local local = localDao.getLocal(Integer.parseInt(idLocalStr));
  
            double biomassaEstLocal = 0.0;
              
            ArrayList<Equacao> equacoesTrabalho = new ArrayList<Equacao>();
            equacoesTrabalho = local.getTrabalhoCientifico().getEquacoesTrabalho();
            
            ArrayList<Parcela> parcelasLocal = new ArrayList<Parcela>();
            parcelasLocal = local.getParcelas();
            
            for (Parcela parcela : parcelasLocal) {
                double biomassaEstParcela = 0.0;
                ParcelaDao parcelaDao = new ParcelaDao();
                ArrayList<Arvore> arvoresParcela = new ArrayList<Arvore>();
                arvoresParcela = parcela.getArvores();
                
                for (Arvore arvore : arvoresParcela) {
                    double biomassaEst = 0.0;
                    ArvoreDao arvoreDao = new ArvoreDao();
                    ArrayList<VariavelArvore> variaveisArvore = new ArrayList<>();
                    variaveisArvore = arvore.getVariaveisArvore();
                    
                    for (Equacao e : equacoesTrabalho) {
                        switch (e.getIdVariavelInteresse()) {
                          case 1: 
                               biomassaEst = calculaBiomassaComArvores(variaveisArvore,e);
                               arvore.setQtdeBiomassaEst(biomassaEst);
                               arvoreDao.updateBiomassa(arvore);
                               biomassaEstParcela += biomassaEst;
                          case 2: 
                               calculaCarbonoComArvores(variaveisArvore,e);
                          case 3: 
                               calculaVolumeComArvores(variaveisArvore,e);
                        }
                    }                    
                }
                parcela.setQtdeBiomassa(biomassaEstParcela);
                parcelaDao.updateBiomassa(parcela);
            }
//PEND            Chamar calcularComParcelas para estimar biomassa do Local
            local.setQtdeBiomassa(biomassaEstLocal);
            localDao.updateBiomassa(local);
            
            
            
/*            
            biomassaEstLocal = 10.0;
            local.setQtdeBiomassa(biomassaEstLocal);
            
*/            
            request.setAttribute("local", local );          

            request.getRequestDispatcher("calcularComArvores.jsp").forward(request, response);

        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(calcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(calcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularComArvores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "CalcularComArvores";
    }

    private double calculaBiomassaComArvores(ArrayList<VariavelArvore> variaveisArvore,Equacao equacao) {
        //http://www.singularsys.com/jep/doc/html/index.html
        JEP myParser = new JEP();
        myParser.addStandardFunctions();
        myParser.addStandardConstants();
        
        double resultado = 0.0;
                
        for (VariavelArvore variavelArvore : variaveisArvore) {
            String sigla = variavelArvore.getVariavel().getSigla();
            Double valor = variavelArvore.getValor();
        
            myParser.addVariable(sigla, valor);
        }  
            
        myParser.parseExpression(equacao.getExpressaoEquacao());
        
        resultado = myParser.getValue();
        return resultado;

    }

    private void calculaCarbonoComArvores(ArrayList<VariavelArvore> variaveisArvore,Equacao equacao) {
    }

    private void calculaVolumeComArvores(ArrayList<VariavelArvore> variaveisArvore,Equacao equacao) {
    }
 
}

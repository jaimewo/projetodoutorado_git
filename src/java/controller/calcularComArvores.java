/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;




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
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author jaime
 */
public class calcularComArvores extends HttpServlet {

    double mediaParcela = 0.0;
    double variancia = 0.0;
    double desvioPadrao = 0.0;
    double qtdeParcelasLocal =  0.0;
    double umMenosF = 0.0;
    double erroPadrao = 0.0;
    double coeficienteVariacao = 0.0;
    double erro = 0.0;
    double t = 0.0;
    double erroAbsoluto = 0.0;
    double erroRelativo = 0.0;
    double intervaloConfiancaMinMedia = 0.0;
    double intervaloConfiancaMaxMedia = 0.0;
    double mediaLocal = 0.0;
    double intervaloConfiancaMinTotal = 0.0;
    double intervaloConfiancaMaxTotal = 0.0;
    int tamanhoAmostra;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        try {
            String idLocalStr = request.getParameter("id");
            
            LocalDao controller = new LocalDao();
            Local local = controller.getLocal(idLocalStr);
            request.setAttribute("local", local );
            
            ArrayList<Equacao> equacoesTrabalho = new ArrayList<Equacao>();
            equacoesTrabalho = local.getTrabalhoCientifico().getEquacoesTrabalho();
            
            for (Equacao e : equacoesTrabalho) {
                switch (e.getIdVariavelInteresse()) {
                    case 1: 
                        calculaBiomaComArvores(local);
                    case 2: 
                        calculaCarbonoComArvores(local);
                    case 3: 
                        calculaVolumeComArvores(local);
                }
            }
            request.getRequestDispatcher("??????.jsp").forward(request, response);
        } finally {            
             
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(calcularComParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Editar Local";
    }

    private void calculaBiomaComArvores(Local local) {
        
        Arvore arvore = new Arvore();
        arvore.getVariaveisArvore().get(0).getVariavel().getSigla();
    }

    private void calculaCarbonoComArvores(Local local) {
    }

    private void calculaVolumeComArvores(Local local) {
    }
 
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiomaDao;
import dao.CoordenadaLocalDao;
import dao.EspacamentoDao;
import dao.EspecieDao;
import dao.FormacaoDao;
import dao.LocalDao;
import dao.MunicipioDao;
import dao.TipoEstimativaDao;
import dao.TipoFlorestaDao;
import dao.ValorPadraoDao;
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
import model.Bioma;
import model.Espacamento;
import model.Especie;
import model.Formacao;
import model.Local;
import model.Municipio;
import model.TipoEstimativa;
import model.TipoFloresta;
import model.ValorPadrao;
/**
 *
 * @author jaime
 */
public class novoLocalPergunta1 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            
            
            String idLocal = request.getParameter("id");
            LocalDao localDao = new LocalDao();
            Local local = new Local();
            local = localDao.getLocal(Integer.parseInt(idLocal));
            
            request.setAttribute("local", local);


            MunicipioDao municipioDao = new MunicipioDao();
            Municipio municipio = municipioDao.getMunicipio(local.getIdMunicipio());
            String nomeMunicipio = municipio.getNome();
            request.setAttribute("nomeMunicipio", nomeMunicipio);

            FormacaoDao formacaoDao = new FormacaoDao();
            Formacao formacao = formacaoDao.getFormacao(local.getIdFormacao());
            String descricaoFormacao = formacao.getDescricao();
            request.setAttribute("descricaoFormacao", descricaoFormacao);

            BiomaDao biomaDao = new BiomaDao();
            Bioma bioma = biomaDao.getBioma(formacao.getIdBioma());
            String descricaoBioma = bioma.getDescricao();
            request.setAttribute("descricaoBioma", descricaoBioma);
            
            TipoFlorestaDao tipoFlorestaDao = new TipoFlorestaDao();
            TipoFloresta tipoFloresta = tipoFlorestaDao.getTipoFloresta(local.getIdTipoFloresta());
            String descricaoTipoFloresta = tipoFloresta.getDescricao();
            request.setAttribute("descricaoTipoFloresta", descricaoTipoFloresta);

            EspecieDao especieDao = new EspecieDao();
            Especie especie = especieDao.getEspecie(local.getIdEspecie());
            String descricaoEspecie = especie.getDescricao();
            request.setAttribute("descricaoEspecie", descricaoEspecie);

            EspacamentoDao espacamentoDao = new EspacamentoDao();
            Espacamento espacamento = espacamentoDao.getEspacamento(local.getIdEspacamento());
            String descricaoEspacamento = espacamento.getDescricao();
            request.setAttribute("descricaoEspacamento", descricaoEspacamento);
            
            ValorPadraoDao valorPadraoDao = new ValorPadraoDao();
            ValorPadrao valorPadrao = new ValorPadrao();
            
            valorPadrao = valorPadraoDao.getValorPadrao(local.getIdTipoFloresta(), local.getIdFormacao(), local.getIdEspecie(), local.getIdEspacamento(), local.getIdade());
            
            double qtdeVolumePadraoDouble  = valorPadrao.getQtdeVolumePadrao();
            double qtdeBiomassaPadraoDouble  = valorPadrao.getQtdeBiomassaPadrao();
            double qtdeCarbonoPadraoDouble  = valorPadrao.getQtdeCarbonoPadrao();
            
            DecimalFormat df2casas = new DecimalFormat("##,###,###,##0.00");            
                
            String qtdeVolumePadrao = df2casas.format(qtdeVolumePadraoDouble); 
            String qtdeBiomassaPadrao = df2casas.format(qtdeBiomassaPadraoDouble); 
            String qtdeCarbonoPadrao = df2casas.format(qtdeCarbonoPadraoDouble); 

            request.setAttribute("qtdeVolumePadrao", qtdeVolumePadrao);
            request.setAttribute("qtdeBiomassaPadrao", qtdeBiomassaPadrao);
            request.setAttribute("qtdeCarbonoPadrao", qtdeCarbonoPadrao);
            
        } finally { 
            request.getRequestDispatcher("novoLocalPergunta1.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoLocalPergunta1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(novoLocalPergunta1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Novo Local";
    }
}

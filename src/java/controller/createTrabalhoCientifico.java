package controller;

import dao.TrabalhoCientificoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TrabalhoCientifico;

/**
 *
 * @author paulozeferino
 */
public class createTrabalhoCientifico extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
                String titulo = request.getParameter("trabalhoCientifico[titulo]");
                String ano = request.getParameter("trabalhoCientifico[ano]");
                
                String idTipoDisponibilidadeStr = request.getParameter("trabalhoCientifico[idTipoDisponibilidade]");
                int idTipoDisponibilidade;
                if (idTipoDisponibilidadeStr == null || idTipoDisponibilidadeStr.isEmpty()) {
                   idTipoDisponibilidade = 0;
                } else {
                   idTipoDisponibilidade = Integer.parseInt(idTipoDisponibilidadeStr);
                }

                String idMetodoQuantificacaoBiomassaStr = request.getParameter("trabalhoCientifico[idMetodoQuantificacaoBiomassa]");
                int idMetodoQuantificacaoBiomassa;
                if (idMetodoQuantificacaoBiomassaStr == null || idMetodoQuantificacaoBiomassaStr.isEmpty()) {
                   idMetodoQuantificacaoBiomassa = 0;
                } else {
                   idMetodoQuantificacaoBiomassa = Integer.parseInt(idMetodoQuantificacaoBiomassaStr);
                }

                String idMetodoQuantificacaoCarbonoStr = request.getParameter("trabalhoCientifico[idMetodoQuantificacaoCarbono]");
                int idMetodoQuantificacaoCarbono;
                if (idMetodoQuantificacaoCarbonoStr == null || idMetodoQuantificacaoCarbonoStr.isEmpty()) {
                   idMetodoQuantificacaoCarbono = 0;
                } else {
                   idMetodoQuantificacaoCarbono = Integer.parseInt(idMetodoQuantificacaoCarbonoStr);
                }
                
                String idAutorStr = request.getParameter("trabalhoCientifico[idAutor]");
                int idAutor;
                if (idAutorStr == null || idAutorStr.isEmpty()) {
                   idAutor = 0;
                } else {
                   idAutor = Integer.parseInt(idAutorStr);
                }
                
                TrabalhoCientifico trabalhoCientifico = new TrabalhoCientifico();
                trabalhoCientifico.setTitulo(titulo);
                trabalhoCientifico.setAno(Integer.parseInt(ano));
                trabalhoCientifico.setIdTipoDisponibilidade(idTipoDisponibilidade);
                trabalhoCientifico.setIdMetodoQuantificacaoBiomassa(idMetodoQuantificacaoBiomassa);
                trabalhoCientifico.setIdMetodoQuantificacaoCarbono(idMetodoQuantificacaoCarbono);
                trabalhoCientifico.setIdAutor(idAutor);
                
                if(trabalhoCientifico.eh_valido())
                {
                    TrabalhoCientificoDao objeto_dao_trabalhoCientifico = new TrabalhoCientificoDao();
                    objeto_dao_trabalhoCientifico.cadastrar(trabalhoCientifico);
                    RequestDispatcher r = request.getRequestDispatcher("/listarTrabalhosCientificos");  
                    request.setAttribute("mensagem", "Trabalho Científico adicionado com sucesso!");
                    r.forward( request, response );  
                }else
                {
                    RequestDispatcher r = request.getRequestDispatcher("/novoTrabalhoCientifico");    
                    request.setAttribute("trabalhoCientifico", trabalhoCientifico);
                    request.setAttribute("erros", trabalhoCientifico.getErrors());
                    r.forward( request, response );  
                }
        } finally {            
            
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(createTrabalhoCientifico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Criar TrabalhoCientifico";
    }// </editor-fold>
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Equacao;


/**
 *
 * @author Jaime
 */
public class EquacaoDao extends MainDao{
    
    public EquacaoDao() {
        super();
    }
    public void cadastrar(Equacao equacao) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO equacao (expressaoequacao,"
                    +                                                            "expressaomodelo,"
                    +                                                            "idvariavelinteresse,"
                    +                                                            "idautormodelo"
                    +                                                            ") VALUES (?,?,?,?)");
            p.setString(1, equacao.getExpressaoEquacao());
            p.setString(2, equacao.getExpressaoModelo());
            p.setInt(3, equacao.getIdVariavelInteresse());
            p.setInt(4, equacao.getIdAutorModelo());
            p.executeUpdate();
            p.close();
            super.con.close();
            
    }
    
    
    public void deletar(Equacao equacao) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from equacao where id = ?");
            p.setInt(1, equacao.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
    }
    
   public void update(Equacao equacao) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE equacao SET expressaoequacao = ?, "
                    +                                                      "expressaomodelo = ?,"
                    +                                                      "idvariavelinteresse = ?,"
                    +                                                      "idautormodelo = ? "
                +                                                          "WHERE id = ?");
        p.setString(1, equacao.getExpressaoEquacao());
        p.setString(2, equacao.getExpressaoModelo());
        p.setInt(3, equacao.getIdVariavelInteresse());
        p.setInt(4, equacao.getIdAutorModelo());
        
        p.setInt(9, equacao.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
   
   public Equacao getEquacao(String id) throws SQLException
   {
        ArrayList<Equacao> equacoes = new ArrayList<Equacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM equacao where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Equacao equacao = new Equacao();
           equacao.setId(rs.getInt("id"));
           equacao.setExpressaoEquacao(rs.getString("expressaoequacao"));
           equacao.setExpressaoModelo(rs.getString("expressaomodelo"));
           equacao.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           equacao.setIdAutorModelo(rs.getInt("idautormodelo"));
           
           equacoes.add(equacao);
           
        }
        rs.close();
        p.close();
        super.con.close();
        return equacoes.get(0);
   }
   
   public List<Equacao> listarEquacoes() throws Exception{
       
        ArrayList<Equacao> equacoes = new ArrayList<Equacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM equacao");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Equacao equacao = new Equacao();
           equacao.setId(rs.getInt("id"));
           equacao.setExpressaoEquacao(rs.getString("expressaoequacao"));
           equacao.setExpressaoModelo(rs.getString("expressaomodelo"));
           equacao.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           equacao.setIdAutorModelo(rs.getInt("idautormodelo"));
           equacoes.add(equacao);
        }
        rs.close();
        p.close();
        super.con.close();
        return equacoes;
    }
   public ArrayList<Equacao> listarEquacoesTrabalho(int idTrabalhoCientifico) throws Exception{
       
        ArrayList<Equacao> equacoesTrabalho = new ArrayList<Equacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT e.id,"
                + "                                             e.expressaoequacao, "
                + "                                             e.expressaomodelo, "
                + "                                             e.idvariavelinteresse,"
                + "                                             e.idautormodelo,"
                + "                                             e.idtrabalhocientifico "
                + "                                      FROM trabalhocientifico tc "
                + "                                      INNER JOIN equacaotrabalhocientifico etc ON tc.id = etc.idtrabalhocientifico "
                + "                                      INNER JOIN equacao e ON etc.idequacao = e.id "
                + "                                      WHERE tc.id = ?");
        p.setInt(1, idTrabalhoCientifico);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Equacao equacao = new Equacao();
           equacao.setId(rs.getInt("id"));
           equacao.setExpressaoEquacao(rs.getString("expressaoequacao"));
           equacao.setExpressaoModelo(rs.getString("expressaomodelo"));
           equacao.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           equacao.setIdAutorModelo(rs.getInt("idautormodelo"));
           equacao.setIdtTrabalhoCientifico(rs.getInt("idTrabalhoCientifico"));
           equacoesTrabalho.add(equacao);
        }
        rs.close();
        p.close();
        super.con.close();
        return equacoesTrabalho;
    }
    
}

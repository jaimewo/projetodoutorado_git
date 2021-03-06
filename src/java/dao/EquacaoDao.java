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
                    +                                                            "expressaoequacaoformatada,"
                    +                                                            "expressaomodelo,"                    
                    +                                                            "idvariavelinteresse,"
                    +                                                            "idautormodelo"
                    +                                                            ") VALUES (?,?,?,?,?)");
            p.setString(1, equacao.getExpressaoEquacao());
            p.setString(2, equacao.getExpressaoEquacaoFormatada());
            p.setString(3, equacao.getExpressaoModelo());            
            p.setInt(4, equacao.getIdVariavelInteresse());
            p.setInt(5, equacao.getIdAutorModelo());
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
                    +                                                      "expressaoequacaoformatada = ?,"
                    +                                                      "expressaomodelo = ?,"
                    +                                                      "idvariavelinteresse = ?,"
                    +                                                      "idautormodelo = ? "
                    +                                                      "WHERE id = ?");
        p.setString(1, equacao.getExpressaoEquacao());
        p.setString(2, equacao.getExpressaoEquacaoFormatada());        
        p.setString(3, equacao.getExpressaoModelo());
        p.setInt(4, equacao.getIdVariavelInteresse());
        p.setInt(5, equacao.getIdAutorModelo());
        
        p.setInt(6, equacao.getId());
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
           equacao.setExpressaoEquacaoFormatada(rs.getString("expressaoequacaoformatada"));           
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
           equacao.setExpressaoEquacaoFormatada(rs.getString("expressaoequacaoformatada"));           
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
   public ArrayList<Equacao> getEquacoesLocal(int idLocal) throws Exception{
       
        ArrayList<Equacao> equacoes = new ArrayList<Equacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT e.id,"
                + "                                             e.expressaoequacao, "
                + "                                             e.expressaoequacaoformatada, "
                + "                                             e.expressaomodelo, "                
                + "                                             e.idvariavelinteresse,"
                + "                                             e.idautormodelo"
                + "                                      FROM equacaolocal el  "
                + "                                      INNER JOIN equacao e ON el.idequacao = e.id "
                + "                                      WHERE el.idlocal = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Equacao equacao = new Equacao();
           equacao.setId(rs.getInt("id"));
           equacao.setExpressaoEquacao(rs.getString("expressaoequacao"));
           equacao.setExpressaoEquacaoFormatada(rs.getString("expressaoequacaoformatada"));           
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
    
}

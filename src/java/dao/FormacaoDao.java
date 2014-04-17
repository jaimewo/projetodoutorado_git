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
import model.Formacao;

/**
 *
 * @author Jaime
 */
public class FormacaoDao {
    
    private Connection con = null;
    
    
    public FormacaoDao()
    {
        try {
            Class.forName("org.postgresql.Driver");
//              this.con = DriverManager
//                    .getConnection(
//                    "jdbc:postgresql://localhost:5432/database_doutorado",
//                    "postgres", "qwe123@");

              this.con = DriverManager
                    .getConnection(
                    "jdbc:postgresql://localhost:5432/JCarbon1",
                    "postgres", "root");
              
              
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FormacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrar(Formacao formacao) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO formacao (descricao, idbioma) VALUES (?,?)");
            p.setString(1, formacao.getDescricao());
            p.setInt(2, formacao.getIdBioma());
            
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(Formacao formacao) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from formacao where id = ?");
            p.setInt(1, formacao.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Formacao formacao) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE formacao SET descricao = ?, idbioma = ? where id = ?");
        p.setString(1, formacao.getDescricao());
        p.setInt(2, formacao.getIdBioma());
        p.setInt(3, formacao.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Formacao getFormacao(String id) throws SQLException
   {
        List<Formacao> formacoes = new ArrayList<Formacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao,idbioma FROM formacao where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Formacao formacao = new Formacao();
           formacao.setId(rs.getInt("id"));
           formacao.setDescricao(rs.getString("descricao"));
           formacao.setIdBioma(rs.getInt("idbioma"));
           formacoes.add(formacao);
        }
        rs.close();
        p.close();
        return formacoes.get(0);
   }
   
   public List<Formacao> listarFormacoes() throws Exception{
        List<Formacao> formacoes = new ArrayList<Formacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM formacao");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Formacao formacao = new Formacao();
           formacao.setId(rs.getInt("id"));
           formacao.setDescricao(rs.getString("descricao"));
           formacao.setIdBioma(rs.getInt("idbioma"));
           formacoes.add(formacao);
        }
        rs.close();
        p.close();
        return formacoes;
    }
    
}

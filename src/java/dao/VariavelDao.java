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
import model.Variavel;

/**
 *
 * @author paulozeferino
 */
public class VariavelDao {
    
    private Connection con = null;
    
    
    public VariavelDao()
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
            Logger.getLogger(VariavelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrar(Variavel variavel) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO variavel(sigla,nome) VALUES (?,?)");
            p.setString(1, variavel.getSigla());
            p.setString(2, variavel.getNome());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(Variavel variavel) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from variavel where id = ?");
            p.setInt(1, variavel.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Variavel variavel) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE variavel SET sigla = ?, nome = ? where id = ?");
        p.setString(1, variavel.getSigla());
        p.setString(2, variavel.getNome());
        p.setInt(3, variavel.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Variavel getVariavel(String variavel_id) throws SQLException
   {
        List<Variavel> variaveis = new ArrayList<Variavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,sigla,nome FROM variavel where id = ?");
        p.setInt(1, Integer.parseInt(variavel_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Variavel variavel = new Variavel();
           variavel.setId(rs.getInt("id"));
           variavel.setSigla(rs.getString("sigla"));
           variavel.setNome(rs.getString("nome"));
           variaveis.add(variavel);
        }
        rs.close();
        p.close();
        return variaveis.get(0);
   }
   
   public List<Variavel> listarVariaveis() throws Exception{
        List<Variavel> variaveis = new ArrayList<Variavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM variavel");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Variavel variavel = new Variavel();
           variavel.setId(rs.getInt("id"));
           variavel.setSigla(rs.getString("sigla"));
           variavel.setNome(rs.getString("nome"));
           variaveis.add(variavel);
        }
        rs.close();
        p.close();
        return variaveis;
    }
    
}

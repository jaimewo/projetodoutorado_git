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

import model.VariavelInteresse;

/**
 *
 * @author jaime
 */
public class VariavelInteresseDao extends MainDao {
    
    public VariavelInteresseDao()
    {
       super();
    }
    
    public void cadastrar(VariavelInteresse variavelInteresse) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO variavelinteresse(sigla,nome) VALUES (?,?)");
            p.setString(1, variavelInteresse.getSigla());
            p.setString(2, variavelInteresse.getNome());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(VariavelInteresse variavelInteresse) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from variavelinteresse where id = ?");
            p.setInt(1, variavelInteresse.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(VariavelInteresse variavelInteresse) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE variavelinteresse SET sigla = ?, nome = ? where id = ?");
        p.setString(1, variavelInteresse.getSigla());
        p.setString(2, variavelInteresse.getNome());
        p.setInt(3, variavelInteresse.getId());
        p.executeUpdate();
        p.close();
    }
   
   public VariavelInteresse getVariavelInteresse(String variavelInteresse_id) throws SQLException
   {
        List<VariavelInteresse> variaveisInteresse = new ArrayList<VariavelInteresse>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,sigla,nome FROM variavelinteresse where id = ?");
        p.setInt(1, Integer.parseInt(variavelInteresse_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           VariavelInteresse variavelInteresse = new VariavelInteresse();
           variavelInteresse.setId(rs.getInt("id"));
           variavelInteresse.setSigla(rs.getString("sigla"));
           variavelInteresse.setNome(rs.getString("nome"));
           variaveisInteresse.add(variavelInteresse);
        }
        rs.close();
        p.close();
        return variaveisInteresse.get(0);
   }
   
   public List<VariavelInteresse> listarVariaveisInteresse() throws Exception{
        List<VariavelInteresse> variaveisInteresse = new ArrayList<VariavelInteresse>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM variavelinteresse");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           VariavelInteresse variavelInteresse = new VariavelInteresse();
           variavelInteresse.setId(rs.getInt("id"));
           variavelInteresse.setSigla(rs.getString("sigla"));
           variavelInteresse.setNome(rs.getString("nome"));
           variaveisInteresse.add(variavelInteresse);
        }
        rs.close();
        p.close();
        return variaveisInteresse;
    }
    
}

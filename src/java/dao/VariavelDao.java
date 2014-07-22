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

import model.Variavel;

/**
 *
 * @author jaime
 */
public class VariavelDao extends MainDao {
    
    public VariavelDao()
    {
       super();
    }
    
    public void cadastrar(Variavel variavel) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO variavel(sigla,nome) VALUES (?,?)");
        p.setString(1, variavel.getSigla());
        p.setString(2, variavel.getNome());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    
    public void deletar(Variavel variavel) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from variavel where id = ?");
        p.setInt(1, variavel.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
   public void update(Variavel variavel) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE variavel SET sigla = ?, nome = ? where id = ?");
        p.setString(1, variavel.getSigla());
        p.setString(2, variavel.getNome());
        p.setInt(3, variavel.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Variavel getVariavel(String variavel_id) throws SQLException
   {
        ArrayList<Variavel> variaveis = new ArrayList<Variavel>();
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
        super.con.close();        
        return variaveis.get(0);
   }
   public Variavel getVariavelComSigla(String sigla) throws SQLException
   {
        ArrayList<Variavel> variaveis = new ArrayList<Variavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,sigla,nome FROM variavel where sigla = ?");
        p.setString(1, sigla);
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
        super.con.close();        
        return variaveis.get(0);
   }
   public ArrayList<Variavel> listarVariaveis() throws Exception{
       
        ArrayList<Variavel> variaveis = new ArrayList<Variavel>();
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
        super.con.close();        
        return variaveis;
    }
    public ArrayList<Variavel> listarVariaveis(int idEquacao) throws Exception{
        
        ArrayList<Variavel> variaveis = new ArrayList<Variavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT v.id AS id,v.nome AS nome, v.sigla AS sigla "
                + "                                      FROM variavel v JOIN variavelequacao ve ON v.id = ve.idvariavel"
                + "                                      WHERE ve.idequacao = ?");
        p.setInt(1, idEquacao);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Variavel variavel = new Variavel();
           variavel.setId(rs.getInt("id"));
           variavel.setNome(rs.getString("nome"));
           variavel.setSigla(rs.getString("sigla"));
           variaveis.add(variavel);
        }
        rs.close();
        p.close();
        super.con.close();        
        return variaveis;
    }   
}

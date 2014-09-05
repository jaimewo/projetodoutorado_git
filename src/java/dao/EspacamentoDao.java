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
import model.Espacamento;

/**
 *
 * @author jaime
 */
public class EspacamentoDao extends MainDao {
    
    
    public EspacamentoDao()
    {
        super();
    }
    
    public void cadastrar(Espacamento espacamento) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO espacamento(descricao) VALUES (?)");
        p.setString(1, espacamento.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();            
    }
    
    
    public void deletar(Espacamento espacamento) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from espacamento where id = ?");
        p.setInt(1, espacamento.getId());
        p.executeUpdate();
        p.close();
        super.con.close();            
        
    }
    
   public void update(Espacamento espacamento) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE espacamento SET descricao = ? where id = ?");
        p.setString(1, espacamento.getDescricao());
        p.setInt(2, espacamento.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Espacamento getEspacamento(String espacamento_id) throws SQLException
   {
        ArrayList<Espacamento> espacamentos = new ArrayList<Espacamento>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM espacamento where id = ?");
        p.setInt(1, Integer.parseInt(espacamento_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Espacamento espacamento = new Espacamento();
           espacamento.setId(rs.getInt("id"));
           espacamento.setDescricao(rs.getString("descricao"));
           espacamentos.add(espacamento);
        }
        rs.close();
        p.close();
        super.con.close();        
        return espacamentos.get(0);
   }
   
   public Espacamento getEspacamento(int idEspacamento) throws SQLException
   {
        ArrayList<Espacamento> espacamentos = new ArrayList<Espacamento>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM espacamento where id = ?");
        p.setInt(1, idEspacamento);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Espacamento espacamento = new Espacamento();
           espacamento.setId(rs.getInt("id"));
           espacamento.setDescricao(rs.getString("descricao"));
           espacamentos.add(espacamento);
        }
        rs.close();
        p.close();
        super.con.close();        
        return espacamentos.get(0);
   }
      
   public ArrayList<Espacamento> listarEspacamentos() throws Exception{
       
       ArrayList<Espacamento> espacamentos = new ArrayList<Espacamento>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM espacamento");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Espacamento espacamento = new Espacamento();
           espacamento.setId(rs.getInt("id"));
           espacamento.setDescricao(rs.getString("descricao"));
           espacamentos.add(espacamento);
        }
        rs.close();
        p.close();
        super.con.close();        
        return espacamentos;
    }
    
}

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
import model.Especie;

/**
 *
 * @author jaime
 */
public class EspecieDao extends MainDao {
    
    
    public EspecieDao()
    {
        super();
    }
    
    public void cadastrar(Especie especie) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO especie(descricao) VALUES (?)");
        p.setString(1, especie.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();            
    }
    
    
    public void deletar(Especie especie) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from especie where id = ?");
        p.setInt(1, especie.getId());
        p.executeUpdate();
        p.close();
        super.con.close();            
        
    }
    
   public void update(Especie especie) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE especie SET descricao = ? where id = ?");
        p.setString(1, especie.getDescricao());
        p.setInt(2, especie.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Especie getEspecie(String especie_id) throws SQLException
   {
        ArrayList<Especie> especies = new ArrayList<Especie>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM especie where id = ?");
        p.setInt(1, Integer.parseInt(especie_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Especie especie = new Especie();
           especie.setId(rs.getInt("id"));
           especie.setDescricao(rs.getString("descricao"));
           especies.add(especie);
        }
        rs.close();
        p.close();
        super.con.close();        
        return especies.get(0);
   }
   public Especie getEspecie(int idEspecie) throws SQLException
   {
        ArrayList<Especie> especies = new ArrayList<Especie>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM especie where id = ?");
        p.setInt(1, idEspecie);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Especie especie = new Especie();
           especie.setId(rs.getInt("id"));
           especie.setDescricao(rs.getString("descricao"));
           especies.add(especie);
        }
        rs.close();
        p.close();
        super.con.close();        
        return especies.get(0);
   }   
   public ArrayList<Especie> listarEspecies() throws Exception{
       
       ArrayList<Especie> especies = new ArrayList<Especie>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM especie");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Especie especie = new Especie();
           especie.setId(rs.getInt("id"));
           especie.setDescricao(rs.getString("descricao"));
           especies.add(especie);
        }
        rs.close();
        p.close();
        super.con.close();        
        return especies;
    }
    
}

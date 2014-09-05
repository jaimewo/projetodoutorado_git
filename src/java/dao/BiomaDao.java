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
import model.Bioma;

/**
 *
 * @author jaime
 */
public class BiomaDao extends MainDao{
    
    public BiomaDao()
    {
       super();
    }
    
    public void cadastrar(Bioma bioma) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO bioma(descricao) VALUES (?)");
        p.setString(1, bioma.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();            
    }
    
    
    public void deletar(Bioma bioma) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from bioma where id = ?");
        p.setInt(1, bioma.getId());
        p.executeUpdate();
        p.close();
        super.con.close();            
        
    }
    
   public void update(Bioma bioma) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE bioma SET descricao = ? where id = ?");
        p.setString(1, bioma.getDescricao());
        p.setInt(2, bioma.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Bioma getBioma(String bioma_id) throws SQLException
   {
        ArrayList<Bioma> biomas = new ArrayList<Bioma>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM bioma where id = ?");
        p.setInt(1, Integer.parseInt(bioma_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Bioma bioma = new Bioma();
           bioma.setId(rs.getInt("id"));
           bioma.setDescricao(rs.getString("descricao"));
           biomas.add(bioma);
        }
        rs.close();
        p.close();
        super.con.close();        
        return biomas.get(0);
   }
   public Bioma getBioma(int idBioma) throws SQLException
   {
        ArrayList<Bioma> biomas = new ArrayList<Bioma>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM bioma where id = ?");
        p.setInt(1, idBioma);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Bioma bioma = new Bioma();
           bioma.setId(rs.getInt("id"));
           bioma.setDescricao(rs.getString("descricao"));
           biomas.add(bioma);
        }
        rs.close();
        p.close();
        super.con.close();        
        return biomas.get(0);
   }
      
   public List<Bioma> listarBiomas() throws Exception{
       
        ArrayList<Bioma> biomas = new ArrayList<Bioma>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM bioma");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Bioma bioma = new Bioma();
           bioma.setId(rs.getInt("id"));
           bioma.setDescricao(rs.getString("descricao"));
           biomas.add(bioma);
        }
        rs.close();
        p.close();
        super.con.close();        
        return biomas;
    }
    
}

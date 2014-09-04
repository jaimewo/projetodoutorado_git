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
import model.TipoEstimativa;

/**
 *
 * @author jaime
 */
public class TipoEstimativaDao extends MainDao{
    
    
    public TipoEstimativaDao()
    {
        super();
    }
    
    public void cadastrar(TipoEstimativa tipoEstimativa) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO tipoestimativa(descricao) VALUES (?)");
        p.setString(1, tipoEstimativa.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    
    public void deletar(TipoEstimativa tipoEstimativa) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from tipoestimativa where id = ?");
        p.setInt(1, tipoEstimativa.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
   public void update(TipoEstimativa tipoEstimativa) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE tipoestimativa SET descricao = ? where id = ?");
        p.setString(1, tipoEstimativa.getDescricao());
        p.setInt(2, tipoEstimativa.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public TipoEstimativa getTipoEstimativa(String tipoEstimativa_id) throws SQLException
   {
        ArrayList<TipoEstimativa> tiposEstimativa = new ArrayList<TipoEstimativa>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM tipoestimativa where id = ?");
        p.setInt(1, Integer.parseInt(tipoEstimativa_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TipoEstimativa tipoEstimativa = new TipoEstimativa();
           tipoEstimativa.setId(rs.getInt("id"));
           tipoEstimativa.setDescricao(rs.getString("descricao"));
           tiposEstimativa.add(tipoEstimativa);
        }
        rs.close();
        p.close();
        super.con.close();        
        return tiposEstimativa.get(0);
   }
   
   public ArrayList<TipoEstimativa> listarTiposEstimativa() throws Exception{
       
        ArrayList<TipoEstimativa> tiposEstimativa = new ArrayList<TipoEstimativa>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM tipoestimativa");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TipoEstimativa tipoEstimativa = new TipoEstimativa();
           tipoEstimativa.setId(rs.getInt("id"));
           tipoEstimativa.setDescricao(rs.getString("descricao"));
           tiposEstimativa.add(tipoEstimativa);
        }
        rs.close();
        p.close();
        super.con.close();        
        return tiposEstimativa;
    }
    
}

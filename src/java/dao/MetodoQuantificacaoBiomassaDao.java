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
import model.MetodoQuantificacaoBiomassa;

/**
 *
 * @author jaime
 */
public class MetodoQuantificacaoBiomassaDao extends MainDao {
    
    
    public MetodoQuantificacaoBiomassaDao()
    {
        super();
    }
    
    public void cadastrar(MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO metodoquantificacaobiomassa(descricao) VALUES (?)");
        p.setString(1, metodoQuantificacaoBiomassa.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    
    public void deletar(MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from metodoquantificacaobiomassa where id = ?");
        p.setInt(1, metodoQuantificacaoBiomassa.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
   public void update(MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE metodoquantificacaobiomassa SET descricao = ? where id = ?");
        p.setString(1, metodoQuantificacaoBiomassa.getDescricao());
        p.setInt(2, metodoQuantificacaoBiomassa.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public MetodoQuantificacaoBiomassa getMetodoQuantificacaoBiomassa(String metodoQuantificacaoBiomassa_id) throws SQLException
   {
        ArrayList<MetodoQuantificacaoBiomassa> metodosQuantificacaoBiomassa = new ArrayList<MetodoQuantificacaoBiomassa>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM metodoquantificacaobiomassa where id = ?");
        p.setInt(1, Integer.parseInt(metodoQuantificacaoBiomassa_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa = new MetodoQuantificacaoBiomassa();
           metodoQuantificacaoBiomassa.setId(rs.getInt("id"));
           metodoQuantificacaoBiomassa.setDescricao(rs.getString("descricao"));
           metodosQuantificacaoBiomassa.add(metodoQuantificacaoBiomassa);
        }
        rs.close();
        p.close();
        super.con.close();        
        return metodosQuantificacaoBiomassa.get(0);
   }
   
   public ArrayList<MetodoQuantificacaoBiomassa> listarMetodosQuantificacaoBiomassa() throws Exception{
       
        ArrayList<MetodoQuantificacaoBiomassa> metodosQuantificacaoBiomassa = new ArrayList<MetodoQuantificacaoBiomassa>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM metodoquantificacaobiomassa");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           MetodoQuantificacaoBiomassa metodoQuantificacaoBiomassa = new MetodoQuantificacaoBiomassa();
           metodoQuantificacaoBiomassa.setId(rs.getInt("id"));
           metodoQuantificacaoBiomassa.setDescricao(rs.getString("descricao"));
           metodosQuantificacaoBiomassa.add(metodoQuantificacaoBiomassa);
        }
        rs.close();
        p.close();
        super.con.close();        
        return metodosQuantificacaoBiomassa;
    }
    
}

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
import model.TipoFloresta;

/**
 *
 * @author jaime
 */
public class TipoFlorestaDao extends MainDao{
    
    
    public TipoFlorestaDao()
    {
        super();
    }
    
    public void cadastrar(TipoFloresta tipoFloresta) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO tipofloresta(descricao) VALUES (?)");
        p.setString(1, tipoFloresta.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    
    public void deletar(TipoFloresta tipoFloresta) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from tipofloresta where id = ?");
        p.setInt(1, tipoFloresta.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
   public void update(TipoFloresta tipoFloresta) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE tipofloresta SET descricao = ? where id = ?");
        p.setString(1, tipoFloresta.getDescricao());
        p.setInt(2, tipoFloresta.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public TipoFloresta getTipoFloresta(String tipoFloresta_id) throws SQLException
   {
        ArrayList<TipoFloresta> tiposFloresta = new ArrayList<TipoFloresta>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM tipofloresta where id = ?");
        p.setInt(1, Integer.parseInt(tipoFloresta_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TipoFloresta tipoFloresta = new TipoFloresta();
           tipoFloresta.setId(rs.getInt("id"));
           tipoFloresta.setDescricao(rs.getString("descricao"));
           tiposFloresta.add(tipoFloresta);
        }
        rs.close();
        p.close();
        super.con.close();        
        return tiposFloresta.get(0);
   }
   
   public ArrayList<TipoFloresta> listarTiposFloresta() throws Exception{
       
        ArrayList<TipoFloresta> tiposFloresta = new ArrayList<TipoFloresta>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM tipofloresta");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TipoFloresta tipoFloresta = new TipoFloresta();
           tipoFloresta.setId(rs.getInt("id"));
           tipoFloresta.setDescricao(rs.getString("descricao"));
           tiposFloresta.add(tipoFloresta);
        }
        rs.close();
        p.close();
        super.con.close();        
        return tiposFloresta;
    }
    
}

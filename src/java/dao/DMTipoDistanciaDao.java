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
import model.DMTipoDistancia;

/**
 *
 * @author jaimewo
 */
public class DMTipoDistanciaDao extends MainDao{
    
    
    public DMTipoDistanciaDao()
    {
        super();
    }
    
   
   public DMTipoDistancia getTipoDistancia(String id) throws SQLException
   {
        ArrayList<DMTipoDistancia> dmTiposDistancia = new ArrayList<DMTipoDistancia>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM dmtipodistancia where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           DMTipoDistancia dmTipoDistancia = new DMTipoDistancia();
           dmTipoDistancia.setId(rs.getInt("id"));
           dmTipoDistancia.setDescricao(rs.getString("descricao"));
           dmTiposDistancia.add(dmTipoDistancia);
        }
        rs.close();
        p.close();
        super.con.close();        
        return dmTiposDistancia.get(0);
   }
   
   public String getDescricao(int id) throws SQLException
   {
        String descricao="";

        PreparedStatement p = this.con.prepareStatement("SELECT descricao FROM dmtipodistancia where id = ?");
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           descricao = rs.getString("descricao");
        }
        rs.close();
        p.close();
        super.con.close();        
        return descricao;
   }
   
   public ArrayList<DMTipoDistancia> listarTiposDistancia() throws Exception{
        ArrayList<DMTipoDistancia> dmTiposDistancia = new ArrayList<DMTipoDistancia>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM dmtipodistancia");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           DMTipoDistancia dmTipoDistancia = new DMTipoDistancia();
           dmTipoDistancia.setId(rs.getInt("id"));
           dmTipoDistancia.setDescricao(rs.getString("descricao"));
           dmTiposDistancia.add(dmTipoDistancia);
        }
        rs.close();
        p.close();
        super.con.close();        
        return dmTiposDistancia;
    }
    
}

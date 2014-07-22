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
import model.MetodoQuantificacaoCarbono;

/**
 *
 * @author jaime
 */
public class MetodoQuantificacaoCarbonoDao extends MainDao{
    
    
    public MetodoQuantificacaoCarbonoDao()
    {
        super();
    }
    
    public void cadastrar(MetodoQuantificacaoCarbono metodoQuantificacaoCarbono) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO metodoquantificacaocarbono(descricao) VALUES (?)");
        p.setString(1, metodoQuantificacaoCarbono.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    
    public void deletar(MetodoQuantificacaoCarbono metodoQuantificacaoCarbono) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from metodoquantificacaocarbono where id = ?");
        p.setInt(1, metodoQuantificacaoCarbono.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
   public void update(MetodoQuantificacaoCarbono metodoQuantificacaoCarbono) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE metodoquantificacaocarbono SET descricao = ? where id = ?");
        p.setString(1, metodoQuantificacaoCarbono.getDescricao());
        p.setInt(2, metodoQuantificacaoCarbono.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public MetodoQuantificacaoCarbono getMetodoQuantificacaoCarbono(String metodoQuantificacaoCarbono_id) throws SQLException
   {
        ArrayList<MetodoQuantificacaoCarbono> metodosQuantificacaoCarbono = new ArrayList<MetodoQuantificacaoCarbono>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM metodoquantificacaocarbono where id = ?");
        p.setInt(1, Integer.parseInt(metodoQuantificacaoCarbono_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           MetodoQuantificacaoCarbono metodoQuantificacaoCarbono = new MetodoQuantificacaoCarbono();
           metodoQuantificacaoCarbono.setId(rs.getInt("id"));
           metodoQuantificacaoCarbono.setDescricao(rs.getString("descricao"));
           metodosQuantificacaoCarbono.add(metodoQuantificacaoCarbono);
        }
        rs.close();
        p.close();
        super.con.close();        
        return metodosQuantificacaoCarbono.get(0);
   }
   
   public ArrayList<MetodoQuantificacaoCarbono> listarMetodosQuantificacaoCarbono() throws Exception{
        ArrayList<MetodoQuantificacaoCarbono> metodosQuantificacaoCarbono = new ArrayList<MetodoQuantificacaoCarbono>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM metodoquantificacaocarbono");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           MetodoQuantificacaoCarbono metodoQuantificacaoCarbono = new MetodoQuantificacaoCarbono();
           metodoQuantificacaoCarbono.setId(rs.getInt("id"));
           metodoQuantificacaoCarbono.setDescricao(rs.getString("descricao"));
           metodosQuantificacaoCarbono.add(metodoQuantificacaoCarbono);
        }
        rs.close();
        p.close();
        super.con.close();        
        return metodosQuantificacaoCarbono;
    }
    
}

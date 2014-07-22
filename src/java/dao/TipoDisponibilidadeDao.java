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
import model.TipoDisponibilidade;

/**
 *
 * @author jaime
 */
public class TipoDisponibilidadeDao extends MainDao{
    
    
    public TipoDisponibilidadeDao()
    {
        super();
    }
    
    public void cadastrar(TipoDisponibilidade tipoDisponibilidade) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO tipodisponibilidade(descricao) VALUES (?)");
        p.setString(1, tipoDisponibilidade.getDescricao());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    
    public void deletar(TipoDisponibilidade tipoDisponibilidade) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from tipodisponibilidade where id = ?");
        p.setInt(1, tipoDisponibilidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
   public void update(TipoDisponibilidade tipoDisponibilidade) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE tipodisponibilidade SET descricao = ? where id = ?");
        p.setString(1, tipoDisponibilidade.getDescricao());
        p.setInt(2, tipoDisponibilidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public TipoDisponibilidade getTipoDisponibilidade(String tipoDisponibilidade_id) throws SQLException
   {
        ArrayList<TipoDisponibilidade> tiposDisponibilidade = new ArrayList<TipoDisponibilidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM tipodisponibilidade where id = ?");
        p.setInt(1, Integer.parseInt(tipoDisponibilidade_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TipoDisponibilidade tipoDisponibilidade = new TipoDisponibilidade();
           tipoDisponibilidade.setId(rs.getInt("id"));
           tipoDisponibilidade.setDescricao(rs.getString("descricao"));
           tiposDisponibilidade.add(tipoDisponibilidade);
        }
        rs.close();
        p.close();
        super.con.close();        
        return tiposDisponibilidade.get(0);
   }
   
   public ArrayList<TipoDisponibilidade> listarTiposDisponibilidade() throws Exception{
       
        ArrayList<TipoDisponibilidade> tiposDisponibilidade = new ArrayList<TipoDisponibilidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM tipodisponibilidade");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TipoDisponibilidade tipoDisponibilidade = new TipoDisponibilidade();
           tipoDisponibilidade.setId(rs.getInt("id"));
           tipoDisponibilidade.setDescricao(rs.getString("descricao"));
           tiposDisponibilidade.add(tipoDisponibilidade);
        }
        rs.close();
        p.close();
        super.con.close();        
        return tiposDisponibilidade;
    }
    
}

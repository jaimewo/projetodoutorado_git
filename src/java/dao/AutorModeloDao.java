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
import model.AutorModelo;

/**
 *
 * @author paulozeferino
 */
public class AutorModeloDao {
    
    private Connection con = null;
    
    
    public AutorModeloDao()
    {
        try {
            Class.forName("org.postgresql.Driver");
//              this.con = DriverManager
//                    .getConnection(
//                    "jdbc:postgresql://localhost:5432/database_doutorado",
//                    "postgres", "qwe123@");

              this.con = DriverManager
                    .getConnection(
                    "jdbc:postgresql://localhost:5432/JCarbon1",
                    "postgres", "root");
              
              
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AutorModeloDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrar(AutorModelo autorModelo) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO autormodelo(nome) VALUES (?)");
            p.setString(1, autorModelo.getNome());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(AutorModelo autorModelo) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from autormodelo where id = ?");
            p.setInt(1, autorModelo.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(AutorModelo autorModelo) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE autormodelo SET nome = ? where id = ?");
        p.setString(1, autorModelo.getNome());
        p.setInt(2, autorModelo.getId());
        p.executeUpdate();
        p.close();
    }
   
   public AutorModelo getAutorModelo(String autorModelo_id) throws SQLException
   {
        List<AutorModelo> autoresModelo = new ArrayList<AutorModelo>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,nome FROM autormodelo where id = ?");
        p.setInt(1, Integer.parseInt(autorModelo_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           AutorModelo autorModelo = new AutorModelo();
           autorModelo.setId(rs.getInt("id"));
           autorModelo.setNome(rs.getString("nome"));
           autoresModelo.add(autorModelo);
        }
        rs.close();
        p.close();
        return autoresModelo.get(0);
   }
   
   public List<AutorModelo> listarAutoresModelo() throws Exception{
        List<AutorModelo> autoresModelo = new ArrayList<AutorModelo>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM autormodelo");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           AutorModelo autorModelo = new AutorModelo();
           autorModelo.setId(rs.getInt("id"));
           autorModelo.setNome(rs.getString("nome"));
           autoresModelo.add(autorModelo);
        }
        rs.close();
        p.close();
        return autoresModelo;
    }
    
}

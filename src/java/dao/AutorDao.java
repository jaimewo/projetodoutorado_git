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
import model.Autor;

/**
 *
 * @author jaime
 */
public class AutorDao extends MainDao{
    
 //   private Connection con = null;
    
    
    public AutorDao()
    {
     super();
    }
    
    public void cadastrar(Autor autor) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO autor(nome) VALUES (?)");
            p.setString(1, autor.getNome());
            p.executeUpdate();
            p.close();
            super.con.close();
    }
    
    
    public void deletar(Autor autor) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from autor where id = ?");
            p.setInt(1, autor.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
        
    }
    
   public void update(Autor autor) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE autor SET nome = ? where id = ?");
        p.setString(1, autor.getNome());
        p.setInt(2, autor.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Autor getAutor(String autor_id) throws SQLException
   {
        ArrayList<Autor> autores = new ArrayList<Autor>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,nome FROM autor where id = ?");
        p.setInt(1, Integer.parseInt(autor_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Autor autor = new Autor();
           autor.setId(rs.getInt("id"));
           autor.setNome(rs.getString("nome"));
           autores.add(autor);
        }
        rs.close();
        p.close();
        super.con.close();        
        return autores.get(0);
   }
   
   public List<Autor> listarAutores() throws Exception{
        ArrayList<Autor> autores = new ArrayList<Autor>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM autor");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Autor autor = new Autor();
           autor.setId(rs.getInt("id"));
           autor.setNome(rs.getString("nome"));
           autores.add(autor);
        }
        rs.close();
        p.close();
        super.con.close();        
        return autores;
    }
    
}

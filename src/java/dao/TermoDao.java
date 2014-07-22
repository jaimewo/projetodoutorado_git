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
import model.Termo;

/**
 *
 * @author Jaime
 */
public class TermoDao extends MainDao {
    
    public TermoDao() {
        super();
    }
    
    public void cadastrar(Termo termo) throws SQLException

    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO termo (idequacao, "
                    +                                                      "expressao,"
                    +                                                      "sequencia"
                    +                                                      ") VALUES (?,?,?)");
        p.setInt   (1,  termo.getIdEquacao());
        p.setString(2,  termo.getExpressao());
        p.setInt   (3,  termo.getSequencia());
         
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    
    public void deletar(Termo termo) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from termo where id = ?");
        p.setInt(1, termo.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
   public void update(Termo termo) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE termo SET idequacao = ?, "
                +                                                        "expressao = ?,"
                +                                                        "sequencia = ?"
                +                                                        " WHERE id = ?");
        p.setInt   (1,  termo.getIdEquacao());
        p.setString(2,  termo.getExpressao());
        p.setInt   (3,  termo.getSequencia());
        
        p.setInt(4, termo.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Termo getTermo(String id) throws SQLException
   {
        ArrayList<Termo> termos = new ArrayList<Termo>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM termo where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Termo termo = new Termo();
           termo.setId(rs.getInt("id"));
           termo.setIdEquacao(rs.getInt("idequacao"));
           termo.setExpressao(rs.getString("expressao"));
           termo.setSequencia(rs.getInt("sequencia"));
           termos.add(termo);
        }
        rs.close();
        p.close();
        super.con.close();        
        return termos.get(0);
   }
   
   public ArrayList<Termo> listarTermos(int idEquacao) throws Exception{
        ArrayList<Termo> termos = new ArrayList<Termo>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM termo WHERE idequacao = ?");
        p.setInt(1, idEquacao);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Termo termo = new Termo();
           termo.setId(rs.getInt("id"));
           termo.setIdEquacao(rs.getInt("idequacao"));
           termo.setExpressao(rs.getString("expressao"));
           termo.setSequencia(rs.getInt("sequencia"));
           termos.add(termo);
        }
        rs.close();
        p.close();
        super.con.close();        
        return termos;
    }
    
}

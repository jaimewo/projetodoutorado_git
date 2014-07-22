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
import model.Vizinho;

/**
 *
 * @author Jaime
 */
public class VizinhoDao extends MainDao {
    
    
    
    public VizinhoDao()
    {
        super();
    }
    
    public void cadastrar(Vizinho vizinho) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO vizinho (idarvore,"
                +                                       "                     numvizinho,"
                +                                       "                     menordistancia,"
                +                                       "                     numarvoremenordistancia,"
                +                                       "                     qtdeobsmenordistancia) "
                +                                       "                     VALUES (?,?,?,?,?)");
        p.setInt   (1, vizinho.getIdArvore());
        p.setInt   (2, vizinho.getNumVizinho());
        p.setDouble(3, vizinho.getMenorDistancia());
        p.setInt   (4, vizinho.getNumArvoreMenorDistancia());
        p.setDouble(5, vizinho.getQtdeObsMenorDistancia());
            
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    public void deletar(Vizinho vizinho) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from vizinho where id = ?");
        p.setInt(1, vizinho.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
   public void update(Vizinho vizinho) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE vizinho SET idarvore = ?, "
                +                                       "                   numvizinho = ?, "
                +                                       "                   menordistancia = ?, "                
                +                                       "                   numarvoremenordistancia = ?, "                
                +                                       "                   atdeobsmenordistancia = ? "                                
                +                                       "where id = ?");
        p.setInt   (1, vizinho.getIdArvore());
        p.setInt   (2, vizinho.getNumVizinho());
        p.setDouble(3, vizinho.getMenorDistancia());
        p.setInt   (4, vizinho.getNumArvoreMenorDistancia());
        p.setDouble(5, vizinho.getQtdeObsMenorDistancia());
        
        p.setInt   (6, vizinho.getId());        
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Vizinho getVizinho(String id) throws SQLException
   {
        ArrayList<Vizinho> vizinhos = new ArrayList<Vizinho>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM vizinho where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Vizinho vizinho = new Vizinho();
           vizinho.setId(rs.getInt("id"));
           vizinho.setIdArvore(rs.getInt("idarvore"));
           vizinho.setMenorDistancia(rs.getDouble("menordistancia"));           
           vizinho.setNumArvoreMenorDistancia(rs.getInt("numarvoremenordistancia"));           
           vizinho.setQtdeObsMenorDistancia(rs.getDouble("qtdeobsmenordistancia"));                      
           vizinhos.add(vizinho);
        }
        rs.close();
        p.close();
        super.con.close();        
        return vizinhos.get(0);
   }
   
   public ArrayList<Vizinho> listarVizinhos() throws Exception{
       
        ArrayList<Vizinho> vizinhos = new ArrayList<Vizinho>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM vizinho where id = ?");

        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Vizinho vizinho = new Vizinho();
           vizinho.setId(rs.getInt("id"));
           vizinho.setIdArvore(rs.getInt("idarvore"));
           vizinho.setMenorDistancia(rs.getDouble("menordistancia"));           
           vizinho.setNumArvoreMenorDistancia(rs.getInt("numarvoremenordistancia"));           
           vizinho.setQtdeObsMenorDistancia(rs.getDouble("qtdeobsmenordistancia"));                      
           vizinhos.add(vizinho);
        }
        rs.close();
        p.close();
        super.con.close();        
        return vizinhos;
    }
    
}

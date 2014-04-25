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
import model.ArvoreVariavel;

/**
 *
 * @author jaime
 */
public class ArvoreVariavelDao {
    
    private Connection con = null;
    
    
    public ArvoreVariavelDao()
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
            Logger.getLogger(ArvoreVariavelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void cadastrar(ArvoreVariavel arvoreVariavel) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO arvorevariavel(idarvore,"
                    +                                                          "idvariavel,"
                    +                                                          "valor"
                    +                                                          ") VALUES (?,?,?)");
            p.setInt(1, arvoreVariavel.getIdArvore());
            p.setInt(2, arvoreVariavel.getIdVariavel());
            p.setDouble(3, arvoreVariavel.getValor());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(ArvoreVariavel arvoreVariavel) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvorevariavel where id = ?");
            p.setInt(1, arvoreVariavel.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(ArvoreVariavel arvoreVariavel) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvorevariavel SET idarvore = ?,"
                +                                                                      " idvariavel = ?,"
                +                                                                      " valor = ?"
                +                                                                      " where id = ?");
        p.setInt(1, arvoreVariavel.getIdArvore());
        p.setInt(2, arvoreVariavel.getIdVariavel());
        p.setDouble(3, arvoreVariavel.getValor());

        p.setInt(4, arvoreVariavel.getId());
        p.executeUpdate();
        p.close();
    }
   
   public ArvoreVariavel getArvoreVariavel(String idArvore) throws SQLException
   {
        List<ArvoreVariavel> arvoresVariavel = new ArrayList<ArvoreVariavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvorevariavel where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreVariavel arvoreVariavel = new ArvoreVariavel();
           arvoreVariavel.setId(rs.getInt("id"));
           arvoreVariavel.setIdArvore(rs.getInt("idarvore"));
           arvoreVariavel.setIdVariavel(rs.getInt("idvariavel"));
           arvoreVariavel.setValor(rs.getDouble("valor"));
           arvoresVariavel.add(arvoreVariavel);
        }
        rs.close();
        p.close();
        return arvoresVariavel.get(0);
   }
   
   public List<ArvoreVariavel> listarArvoresVariavel() throws Exception{
        List<ArvoreVariavel> arvoresVariavel = new ArrayList<ArvoreVariavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvorevariavel");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreVariavel arvoreVariavel = new ArvoreVariavel();
           arvoreVariavel.setId(rs.getInt("id"));
           arvoreVariavel.setIdArvore(rs.getInt("idarvore"));
           arvoreVariavel.setIdVariavel(rs.getInt("idvariavel"));
           arvoreVariavel.setValor(rs.getDouble("valor"));
           arvoresVariavel.add(arvoreVariavel);
        }
        rs.close();
        p.close();
        return arvoresVariavel;
    }
    
}

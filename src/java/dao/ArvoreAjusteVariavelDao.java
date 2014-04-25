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
import model.ArvoreAjusteVariavel;

/**
 *
 * @author jaime
 */
public class ArvoreAjusteVariavelDao {
    
    private Connection con = null;
    
    
    public ArvoreAjusteVariavelDao()
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
            Logger.getLogger(ArvoreAjusteVariavelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void cadastrar(ArvoreAjusteVariavel arvoreAjusteVariavel) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO arvoreajustevariavel(idarvoreajuste,"
                    +                                                          "idvariavel,"
                    +                                                          "valor"
                    +                                                          ") VALUES (?,?,?)");
            p.setInt(1, arvoreAjusteVariavel.getIdArvoreAjuste());
            p.setInt(2, arvoreAjusteVariavel.getIdVariavel());
            p.setDouble(3, arvoreAjusteVariavel.getValor());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(ArvoreAjusteVariavel arvoreAjusteVariavel) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvoreajustevariavel where id = ?");
            p.setInt(1, arvoreAjusteVariavel.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(ArvoreAjusteVariavel arvoreAjusteVariavel) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvoreajustevariavel SET idarvoreajuste = ?,"
                +                                                                      " idvariavel = ?,"
                +                                                                      " valor = ?"
                +                                                                      " where id = ?");
        p.setInt(1, arvoreAjusteVariavel.getIdArvoreAjuste());
        p.setInt(2, arvoreAjusteVariavel.getIdVariavel());
        p.setDouble(3, arvoreAjusteVariavel.getValor());

        p.setInt(4, arvoreAjusteVariavel.getId());
        p.executeUpdate();
        p.close();
    }
   
   public ArvoreAjusteVariavel getArvoreAjusteVariavel(String idArvore) throws SQLException
   {
        List<ArvoreAjusteVariavel> arvoresAjusteVariavel = new ArrayList<ArvoreAjusteVariavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajustevariavel where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjusteVariavel arvoreAjusteVariavel = new ArvoreAjusteVariavel();
           arvoreAjusteVariavel.setId(rs.getInt("id"));
           arvoreAjusteVariavel.setIdArvoreAjuste(rs.getInt("idarvoreajuste"));
           arvoreAjusteVariavel.setIdVariavel(rs.getInt("idvariavel"));
           arvoreAjusteVariavel.setValor(rs.getDouble("valor"));
           arvoresAjusteVariavel.add(arvoreAjusteVariavel);
        }
        rs.close();
        p.close();
        return arvoresAjusteVariavel.get(0);
   }
   
   public List<ArvoreAjusteVariavel> listarArvoresAjusteVariavel() throws Exception{
        List<ArvoreAjusteVariavel> arvoresAjusteVariavel = new ArrayList<ArvoreAjusteVariavel>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajustevariavel");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjusteVariavel arvoreAjusteVariavel = new ArvoreAjusteVariavel();
           arvoreAjusteVariavel.setId(rs.getInt("id"));
           arvoreAjusteVariavel.setIdArvoreAjuste(rs.getInt("idarvoreajuste"));
           arvoreAjusteVariavel.setIdVariavel(rs.getInt("idvariavel"));
           arvoreAjusteVariavel.setValor(rs.getDouble("valor"));
           arvoresAjusteVariavel.add(arvoreAjusteVariavel);
        }
        rs.close();
        p.close();
        return arvoresAjusteVariavel;
    }
    
}

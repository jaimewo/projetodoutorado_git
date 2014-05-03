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
import model.Arvore;

/**
 *
 * @author jaime
 */
public class ArvoreDao extends MainDao {
    
    
    public ArvoreDao()
    {
     super();
    }
     
    public void cadastrar(Arvore arvore) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO arvore(idparcela,"
                    +                                                          "numarvore,"
                    +                                                          "qtdebiomassaobs,"
                    +                                                          "qtdebiomassaest,"
                    +                                                          "qtdecarbonoobs,"
                    +                                                          "qtdecarbonoest,"
                    +                                                          "qtdevolumeobs,"
                    +                                                          "qtdevolumeest"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?)");
            p.setInt(1, arvore.getIdParcela());
            p.setInt(2, arvore.getNumArvore());
            p.setDouble(3, arvore.getQtdeBiomassaObs());
            p.setDouble(4, arvore.getQtdeBiomassaEst());
            p.setDouble(5, arvore.getQtdeCarbonoObs());
            p.setDouble(6, arvore.getQtdeCarbonoEst());
            p.setDouble(7, arvore.getQtdeVolumeObs());
            p.setDouble(8, arvore.getQtdeVolumeEst());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(Arvore arvore) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvore where id = ?");
            p.setInt(1, arvore.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Arvore arvore) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvore SET idparcela = ?,"
                +                                                         " numarvore = ?,"
                +                                                         " qtdebiomassaobs = ?,"
                +                                                         " qtdebiomassaest = ?,"
                +                                                         " qtdecarbonoobs = ?,"
                +                                                         " qtdecarbonoest = ?,"
                +                                                         " qtdevolumeobs = ?,"
                +                                                         " qtdevolumeest = ?"
                +                                                         " where id = ?");
        p.setInt(1, arvore.getIdParcela());
        p.setInt(2, arvore.getNumArvore());
        p.setDouble(3, arvore.getQtdeBiomassaObs());
        p.setDouble(4, arvore.getQtdeBiomassaEst());
        p.setDouble(5, arvore.getQtdeCarbonoObs());
        p.setDouble(6, arvore.getQtdeCarbonoEst());
        p.setDouble(7, arvore.getQtdeVolumeObs());
        p.setDouble(8, arvore.getQtdeVolumeEst());

        p.setInt(9, arvore.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Arvore getArvore(String idArvore) throws SQLException
   {
        List<Arvore> arvores = new ArrayList<Arvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvore where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Arvore arvore = new Arvore();
           arvore.setId(rs.getInt("id"));
           arvore.setIdParcela(rs.getInt("idparcela"));
           arvore.setNumArvore(rs.getInt("numarvore"));
           arvore.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvore.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvore.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvore.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvore.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvore.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvores.add(arvore);
        }
        rs.close();
        p.close();
        return arvores.get(0);
   }
   
   public ArrayList<Arvore> listarArvores(int idParcela) throws Exception{
        ArrayList<Arvore> arvores = new ArrayList<Arvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvore WHERE idparcela = ?");
        p.setInt(1, idParcela);
        ResultSet rs = p.executeQuery();
        
        while(rs.next()){
           Arvore arvore = new Arvore();
           arvore.setId(rs.getInt("id"));
           arvore.setId(rs.getInt("idparcela"));
           arvore.setId(rs.getInt("numarvore"));
           arvore.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvore.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvore.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvore.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvore.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvore.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvores.add(arvore);
        }
        rs.close();
        p.close();
        return arvores;
    }
    
}

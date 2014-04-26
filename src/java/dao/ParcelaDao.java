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
import model.Parcela;

/**
 *
 * @author Jaime
 */
public class ParcelaDao {
    
    private Connection con = null;
    
    
    public ParcelaDao()
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
            Logger.getLogger(ParcelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrar(Parcela parcela) throws SQLException
                        
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO parcela (numparcela, "
                    +                                                          "areaparcela,"
                    +                                                          "qtdebiomassa,"
                    +                                                          "qtdebiomassamin,"
                    +                                                          "qtdebiomassamed,"
                    +                                                          "qtdebiomassamax,"
                    +                                                          "qtdecarbono,"
                    +                                                          "qtdecarbonomin,"
                    +                                                          "qtdecarbonomed,"
                    +                                                          "qtdecarbonomax,"
                    +                                                          "qtdevolume,"
                    +                                                          "qtdevolumemin,"
                    +                                                          "qtdevolumemed,"
                    +                                                          "qtdevolumemax"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        p.setInt   (1,  parcela.getNumParcela());
        p.setDouble(2,  parcela.getAreaParcela());
        p.setDouble(3,  parcela.getQtdeBiomassa());
        p.setDouble(4,  parcela.getQtdeBiomassaMin());
        p.setDouble(5,  parcela.getQtdeBiomassaMed());
        p.setDouble(6,  parcela.getQtdeBiomassaMax());
        p.setDouble(7,  parcela.getQtdeCarbono());
        p.setDouble(8,  parcela.getQtdeCarbonoMin());
        p.setDouble(9,  parcela.getQtdeCarbonoMed());
        p.setDouble(10, parcela.getQtdeCarbonoMax());
        p.setDouble(11, parcela.getQtdeVolume());
        p.setDouble(12, parcela.getQtdeVolumeMin());
        p.setDouble(13, parcela.getQtdeVolumeMed());
        p.setDouble(14, parcela.getQtdeVolumeMax());
         
        p.executeUpdate();
        p.close();
    }
    
    
    public void deletar(Parcela parcela) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from parcela where id = ?");
        p.setInt(1, parcela.getId());
        p.executeUpdate();
        p.close();
        
    }
    
   public void update(Parcela parcela) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcela SET numparcela = ?, "
                    +                                                      "areaparcela = ?,"
                    +                                                      "qtdebiomassa = ?,"
                    +                                                      "qtdebiomassamin = ?,"
                    +                                                      "qtdebiomassamed = ?,"
                    +                                                      "qtdebiomassamax = ?,"
                    +                                                      "qtdecarbono = ?,"
                    +                                                      "qtdecarbonomin = ?,"
                    +                                                      "qtdecarbonomed = ?,"
                    +                                                      "qtdecarbonomax = ?,"
                    +                                                      "qtdevolume = ?,"
                    +                                                      "qtdevolumemin = ?,"
                    +                                                      "qtdevolumemed = ?,"
                    +                                                      "qtdevolumemax = ?"
                +                                                          " WHERE id = ?");
        p.setInt   (1,  parcela.getNumParcela());
        p.setDouble(2,  parcela.getAreaParcela());
        p.setDouble(3,  parcela.getQtdeBiomassa());
        p.setDouble(4,  parcela.getQtdeBiomassaMin());
        p.setDouble(5,  parcela.getQtdeBiomassaMed());
        p.setDouble(6,  parcela.getQtdeBiomassaMax());
        p.setDouble(7,  parcela.getQtdeCarbono());
        p.setDouble(8,  parcela.getQtdeCarbonoMin());
        p.setDouble(9,  parcela.getQtdeCarbonoMed());
        p.setDouble(10, parcela.getQtdeCarbonoMax());
        p.setDouble(11, parcela.getQtdeVolume());
        p.setDouble(12, parcela.getQtdeVolumeMin());
        p.setDouble(13, parcela.getQtdeVolumeMed());
        p.setDouble(14, parcela.getQtdeVolumeMax());
        
        p.setInt(15, parcela.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Parcela getParcela(String id) throws SQLException
   {
        List<Parcela> parcelas = new ArrayList<Parcela>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Parcela parcela = new Parcela();
           parcela.setId(rs.getInt("id"));
           parcela.setNumParcela(rs.getInt("numparcela"));
           parcela.setAreaParcela(rs.getDouble("areaparcela"));
           parcela.setQtdeBiomassa(rs.getDouble("qtdebiomassa"));
           parcela.setQtdeBiomassaMin(rs.getDouble("qtdebiomassamin"));
           parcela.setQtdeBiomassaMed(rs.getDouble("qtdebiomassamed"));
           parcela.setQtdeBiomassaMax(rs.getDouble("qtdebiomassamax"));
           parcela.setQtdeCarbono(rs.getDouble("qtdecarbono"));
           parcela.setQtdeCarbonoMin(rs.getDouble("qtdecarbonomin"));
           parcela.setQtdeCarbonoMed(rs.getDouble("qtdecarbonomed"));
           parcela.setQtdeCarbonoMax(rs.getDouble("qtdecarbonomax"));
           parcela.setQtdeVolume(rs.getDouble("qtdevolume"));
           parcela.setQtdeVolumeMin(rs.getDouble("qtdevolumemin"));
           parcela.setQtdeVolumeMed(rs.getDouble("qtdevolumemed"));
           parcela.setQtdeVolumeMax(rs.getDouble("qtdevolumemax"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        return parcelas.get(0);
   }
   
   public List<Parcela> listarParcelas() throws Exception{
        List<Parcela> parcelas = new ArrayList<Parcela>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Parcela parcela = new Parcela();
           parcela.setId(rs.getInt("id"));
           parcela.setNumParcela(rs.getInt("numparcela"));
           parcela.setAreaParcela(rs.getDouble("areaparcela"));
           parcela.setQtdeBiomassa(rs.getDouble("qtdebiomassa"));
           parcela.setQtdeBiomassaMin(rs.getDouble("qtdebiomassamin"));
           parcela.setQtdeBiomassaMed(rs.getDouble("qtdebiomassamed"));
           parcela.setQtdeBiomassaMax(rs.getDouble("qtdebiomassamax"));
           parcela.setQtdeCarbono(rs.getDouble("qtdecarbono"));
           parcela.setQtdeCarbonoMin(rs.getDouble("qtdecarbonomin"));
           parcela.setQtdeCarbonoMed(rs.getDouble("qtdecarbonomed"));
           parcela.setQtdeCarbonoMax(rs.getDouble("qtdecarbonomax"));
           parcela.setQtdeVolume(rs.getDouble("qtdevolume"));
           parcela.setQtdeVolumeMin(rs.getDouble("qtdevolumemin"));
           parcela.setQtdeVolumeMed(rs.getDouble("qtdevolumemed"));
           parcela.setQtdeVolumeMax(rs.getDouble("qtdevolumemax"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        return parcelas;
    }
    
}

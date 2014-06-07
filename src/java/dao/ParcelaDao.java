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
import model.Local;
import model.Parcela;

/**
 *
 * @author Jaime
 */
public class ParcelaDao extends MainDao {
    
    public ParcelaDao() {
        super();
    }
    
    public void cadastrar(Parcela parcela) throws SQLException, Exception
                        
    {
        String sql = "INSERT INTO parcela (numparcela, "
                    +                                                        "areaparcela,"
                    +                                                        "qtdebiomassa,"
                    +                                                        "qtdecarbono,"
                    +                                                        "qtdevolume,"
                    +                                                        "idlocal"
                    +                                                        ") VALUES (?,?,?,?,?,?) RETURNING parcela.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt   (1, parcela.getNumParcela());
        p.setDouble(2, parcela.getAreaParcela());
        p.setDouble(3, parcela.getQtdeBiomassa());
        p.setDouble(4, parcela.getQtdeCarbono());
        p.setDouble(5, parcela.getQtdeVolume());
        p.setInt   (6, parcela.getIdLocal());
        int idParcela = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idParcela = rs.getInt(1);
        }
        
        if (parcela.arvores.size()>0) {
            for (Arvore arvore: parcela.arvores) {
                arvore.setIdParcela(idParcela);
                ArvoreDao arvoreDao = new ArvoreDao();
                arvoreDao.cadastrar(arvore);
            }
        }
        p.close();
    }
    
    
    public void deletar(Parcela parcela) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from parcela where id = ?");
        p.setInt(1, parcela.getId());
        p.executeUpdate();
        p.close();
        
    }
    
    public void deletarParcela(Local local) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from parcela where idlocal = ?");
        p.setInt(1, local.getId());
        p.executeUpdate();
        p.close();
        
    }
    
   public void update(Parcela parcela) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcela SET numparcela = ?, "
                    +                                                      "areaparcela = ?, "
                    +                                                      "qtdebiomassa = ?, "
                    +                                                      "qtdecarbono = ?, "
                    +                                                      "qtdevolume = ? "
                +                                                          " WHERE id = ?");
        p.setInt   (1, parcela.getNumParcela());
        p.setDouble(2, parcela.getAreaParcela());
        p.setDouble(3, parcela.getQtdeBiomassa());
        p.setDouble(4, parcela.getQtdeCarbono());
        p.setDouble(5, parcela.getQtdeVolume());
        
        p.setInt(6, parcela.getId());
        p.executeUpdate();
        p.close();
    }
    
   public void updateBiomassa(Parcela parcela) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcela SET qtdebiomassa = ? "
                +                                                          " WHERE id = ?");
        p.setDouble(1, parcela.getQtdeBiomassa());
        p.setInt(2, parcela.getId());
        p.executeUpdate();
        p.close();
    }
    
   public void updateCarbono(Parcela parcela) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcela SET qtdecarbono = ? "
                +                                                          " WHERE id = ?");
        p.setDouble(1, parcela.getQtdeCarbono());
        p.setInt(2, parcela.getId());
        p.executeUpdate();
        p.close();
    }
    
   public void updateVolume(Parcela parcela) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcela SET qtdevolume = ? "
                +                                                          " WHERE id = ?");
        p.setDouble(1, parcela.getQtdeVolume());
        p.setInt(2, parcela.getId());
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
           parcela.setQtdeCarbono(rs.getDouble("qtdecarbono"));
           parcela.setQtdeVolume(rs.getDouble("qtdevolume"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        return parcelas.get(0);
   }
   
   public Parcela getParcela(int idLocal,int numParcela) throws SQLException
   {
        List<Parcela> parcelas = new ArrayList<Parcela>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela where idlocal = ? AND numparcela = ?");
        p.setInt(1, idLocal);
        p.setInt(2, numParcela);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Parcela parcela = new Parcela();
           parcela.setId(rs.getInt("id"));
           parcela.setNumParcela(rs.getInt("numparcela"));
           parcela.setAreaParcela(rs.getDouble("areaparcela"));
           parcela.setQtdeBiomassa(rs.getDouble("qtdebiomassa"));
           parcela.setQtdeCarbono(rs.getDouble("qtdecarbono"));
           parcela.setQtdeVolume(rs.getDouble("qtdevolume"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        return parcelas.get(0);
   }
   
   public ArrayList<Parcela> listarParcelas(int idLocal) throws Exception{
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela WHERE idLocal = ?");
           
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Parcela parcela = new Parcela();
           parcela.setId(rs.getInt("id"));
           parcela.setNumParcela(rs.getInt("numparcela"));
           parcela.setAreaParcela(rs.getDouble("areaparcela"));
           parcela.setQtdeBiomassa(rs.getDouble("qtdebiomassa"));
           parcela.setQtdeCarbono(rs.getDouble("qtdecarbono"));
           parcela.setQtdeVolume(rs.getDouble("qtdevolume"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        return parcelas;
    }
    
}

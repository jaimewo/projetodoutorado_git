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
public class CoordenadaLocalDao extends MainDao {
    
    
    public CoordenadaLocalDao()
    {
        super();
    }
     
    public void cadastrar(Arvore coordenadaLocal) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO coordenadalocal(idparcela,"
                    +                                                          "numcoordenadaLocal,"
                    +                                                          "qtdebiomassaobs,"
                    +                                                          "qtdebiomassaest,"
                    +                                                          "qtdecarbonoobs,"
                    +                                                          "qtdecarbonoest,"
                    +                                                          "qtdevolumeobs,"
                    +                                                          "qtdevolumeest"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?)");
            p.setInt(1, coordenadaLocal.getIdParcela());
            p.setInt(2, coordenadaLocal.getNumArvore());
            p.setDouble(3, coordenadaLocal.getQtdeBiomassaObs());
            p.setDouble(4, coordenadaLocal.getQtdeBiomassaEst());
            p.setDouble(5, coordenadaLocal.getQtdeCarbonoObs());
            p.setDouble(6, coordenadaLocal.getQtdeCarbonoEst());
            p.setDouble(7, coordenadaLocal.getQtdeVolumeObs());
            p.setDouble(8, coordenadaLocal.getQtdeVolumeEst());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(Arvore coordenadaLocal) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from coordenadalocal where id = ?");
            p.setInt(1, coordenadaLocal.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Arvore coordenadaLocal) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE coordenadalocal SET idparcela = ?,"
                +                                                         " numcoordenadaLocal = ?,"
                +                                                         " qtdebiomassaobs = ?,"
                +                                                         " qtdebiomassaest = ?,"
                +                                                         " qtdecarbonoobs = ?,"
                +                                                         " qtdecarbonoest = ?,"
                +                                                         " qtdevolumeobs = ?,"
                +                                                         " qtdevolumeest = ?"
                +                                                         " where id = ?");
        p.setInt(1, coordenadaLocal.getIdParcela());
        p.setInt(2, coordenadaLocal.getNumArvore());
        p.setDouble(3, coordenadaLocal.getQtdeBiomassaObs());
        p.setDouble(4, coordenadaLocal.getQtdeBiomassaEst());
        p.setDouble(5, coordenadaLocal.getQtdeCarbonoObs());
        p.setDouble(6, coordenadaLocal.getQtdeCarbonoEst());
        p.setDouble(7, coordenadaLocal.getQtdeVolumeObs());
        p.setDouble(8, coordenadaLocal.getQtdeVolumeEst());

        p.setInt(9, coordenadaLocal.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Arvore getArvore(String idArvore) throws SQLException
   {
        List<Arvore> coordenadasLocal = new ArrayList<Arvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM coordenadalocal where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Arvore coordenadaLocal = new Arvore();
           coordenadaLocal.setId(rs.getInt("id"));
           coordenadaLocal.setIdParcela(rs.getInt("idparcela"));
           coordenadaLocal.setNumArvore(rs.getInt("numcoordenadaLocal"));
           coordenadaLocal.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           coordenadaLocal.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           coordenadaLocal.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           coordenadaLocal.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           coordenadaLocal.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           coordenadaLocal.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           coordenadasLocal.add(coordenadaLocal);
        }
        rs.close();
        p.close();
        return coordenadasLocal.get(0);
   }
   
   public List<Arvore> listarArvorees() throws Exception{
        List<Arvore> coordenadasLocal = new ArrayList<Arvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM coordenadalocal");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Arvore coordenadaLocal = new Arvore();
           coordenadaLocal.setId(rs.getInt("id"));
           coordenadaLocal.setId(rs.getInt("idparcela"));
           coordenadaLocal.setId(rs.getInt("numcoordenadaLocal"));
           coordenadaLocal.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           coordenadaLocal.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           coordenadaLocal.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           coordenadaLocal.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           coordenadaLocal.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           coordenadaLocal.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           coordenadasLocal.add(coordenadaLocal);
        }
        rs.close();
        p.close();
        return coordenadasLocal;
    }
    
}

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
import model.CoordenadaLocal;
import model.CoordenadaLocal;

/**
 *
 * @author jaime
 */
public class CoordenadaLocalDao extends MainDao {
    
    
    public CoordenadaLocalDao()
    {
        super();
    }
     
    
    public ArrayList<CoordenadaLocal> getAll() throws SQLException
    {
        ArrayList<CoordenadaLocal> retorno = new ArrayList<CoordenadaLocal>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM coordenadalocal");
     
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           CoordenadaLocal coordenadaLocal = new CoordenadaLocal();
           coordenadaLocal.setId(rs.getInt("id"));
           coordenadaLocal.setIdLocal(rs.getInt("idlocal"));
           coordenadaLocal.setLatitude(rs.getDouble("latitude"));
           coordenadaLocal.setLongitude(rs.getDouble("longitude"));
           retorno.add(coordenadaLocal);
        }
        rs.close();
        p.close();
        super.con.close();
        return retorno;
        
    }
    
    public void cadastrar(CoordenadaLocal coordenadaLocal) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO coordenadalocal(idlocal,"
                    +                                                          "latitude,"
                    +                                                          "longitude"
                    +                                                          ") VALUES (?,?,?)");
        p.setInt(1, coordenadaLocal.getIdLocal());
        p.setDouble(2, coordenadaLocal.getLatitude());
        p.setDouble(3, coordenadaLocal.getLongitude());
        p.executeUpdate();
        p.close();
        super.con.close();            
    }
    
    
    public void deletar(CoordenadaLocal coordenadaLocal) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from coordenadalocal where id = ?");
        p.setInt(1, coordenadaLocal.getId());
        p.executeUpdate();
        p.close();
        super.con.close();            
        
    }
    
   public void update(CoordenadaLocal coordenadaLocal) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("DELETE from coordenadalocal where idlocal = ?");
        p.setInt(1, coordenadaLocal.getIdLocal());
        p.executeUpdate();
   
        p = this.con.prepareStatement("INSERT INTO coordenadalocal(idlocal,"
                +                                                 "latitude,"
                +                                                 "longitude"
                +                                                 ") VALUES (?,?,?)");
        p.setInt(1, coordenadaLocal.getIdLocal());
        p.setDouble(2, coordenadaLocal.getLatitude());
        p.setDouble(3, coordenadaLocal.getLongitude());
        p.executeUpdate();
    
        p.close();
        super.con.close();            
    }
   
   public CoordenadaLocal getCoordenadaLocal(String idCoordenadaLocal) throws SQLException
   {
        ArrayList<CoordenadaLocal> coordenadasLocal = new ArrayList<CoordenadaLocal>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM coordenadalocal where id = ?");
        p.setInt(1, Integer.parseInt(idCoordenadaLocal));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           CoordenadaLocal coordenadaLocal = new CoordenadaLocal();
           coordenadaLocal.setId(rs.getInt("id"));
           coordenadaLocal.setIdLocal(rs.getInt("idlocal"));
           coordenadaLocal.setLatitude(rs.getDouble("latitude"));
           coordenadaLocal.setLongitude(rs.getDouble("longitude"));
           coordenadasLocal.add(coordenadaLocal);
        }
        rs.close();
        p.close();
        super.con.close();
        return coordenadasLocal.get(0);
   }
   
   public ArrayList<CoordenadaLocal> listarCoordenadasLocal(int idLocal) throws Exception{
       
        ArrayList<CoordenadaLocal> coordenadasLocal = new ArrayList<CoordenadaLocal>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM coordenadalocal WHERE idlocal = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           CoordenadaLocal coordenadaLocal = new CoordenadaLocal();
           coordenadaLocal.setId(rs.getInt("id"));
           coordenadaLocal.setId(rs.getInt("idlocal"));
           coordenadaLocal.setLatitude(rs.getDouble("latitude"));
           coordenadaLocal.setLongitude(rs.getDouble("longitude"));
           coordenadasLocal.add(coordenadaLocal);
        }
        rs.close();
        p.close();
        super.con.close();        
        return coordenadasLocal;
    }
    
}

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
import model.Municipio;
import model.MunicipioLocal;

/**
 *
 * @author jaime
 */
public class MunicipioDao {
    
    private Connection con = null;
    
    
    public MunicipioDao()
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
            Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public Municipio getMunicipio(String idMunicipio) throws SQLException
   {
        List<Municipio> municipios = new ArrayList<Municipio>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,nome,idestado FROM municipio where id = ?");
        p.setInt(1, Integer.parseInt(idMunicipio));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Municipio municipio = new Municipio();
           municipio.setId(rs.getInt("id"));
           municipio.setNome(rs.getString("nome"));
           municipio.setIdEstado(rs.getInt("idestado"));
           municipios.add(municipio);
        }
        rs.close();
        p.close();
        return municipios.get(0);
   }
   
   public List<Municipio> listarMunicipios() throws Exception{
        List<Municipio> municipios = new ArrayList<Municipio>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM municipio");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Municipio municipio = new Municipio();
           municipio.setId(rs.getInt("id"));
           municipio.setNome(rs.getString("nome"));
           municipio.setIdEstado(rs.getInt("idestado"));
           municipios.add(municipio);
        }
        rs.close();
        p.close();
        return municipios;
    }
   
   public List<Municipio> listarMunicipioLocal(String idLocal, List<MunicipioLocal> municipiosLocal) throws Exception{
        List<Municipio> municipios = new ArrayList<Municipio>();
        
        PreparedStatement p = this.con.prepareStatement("SELECT m.nome as nome, ml.indprincipal as indprincipal"
                + "                                      FROM local l "
                + "                                      INNER JOIN municipiolocal ml ON l.id = ml.idlocal "
                + "                                      INNER JOIN municipio m ON ml.idmunicipio = m.id "
                + "                                      WHERE l.id = ?");
        p.setInt(1, Integer.parseInt(idLocal));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Municipio municipio = new Municipio();
           MunicipioLocal municipioLocal = new MunicipioLocal();
           municipio.setNome(rs.getString("nome"));
           municipioLocal.setIndPrincipal(rs.getBoolean("indPrincipal"));
           municipios.add(municipio);
           municipiosLocal.add(municipioLocal);
        }
        rs.close();
        p.close();
        return municipios;
    }
    
}

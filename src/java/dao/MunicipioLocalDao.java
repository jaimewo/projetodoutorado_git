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
public class MunicipioLocalDao extends MainDao{
    
    
    public MunicipioLocalDao()
    {
        super();
    }
    
    public void cadastrar(MunicipioLocal municipioLocal) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO municipioLocal(idlocal,idmunicipio,indprincipal) VALUES (?,?,?)");
            p.setInt(1, municipioLocal.getIdLocal());
            p.setInt(2, municipioLocal.getIdMunicipio());
            p.setBoolean(3, municipioLocal.isIndPrincipal());

            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(MunicipioLocal municipioLocal) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from municipioLocal where id = ?");
            p.setInt(1, municipioLocal.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(MunicipioLocal municipioLocal) throws Exception 
   {
       
        PreparedStatement p = this.con.prepareStatement("DELETE from municipioLocal where idlocal = ?");
        p.setInt(1, municipioLocal.getIdLocal());
        p.executeUpdate();
            
        p = this.con.prepareStatement("INSERT INTO municipioLocal(idlocal,idmunicipio,indprincipal) VALUES (?,?,?)");
        p.setInt(1, municipioLocal.getIdLocal());
        p.setInt(2, municipioLocal.getIdMunicipio());
        p.setBoolean(3, municipioLocal.isIndPrincipal());

        p.executeUpdate();
        p.close();
    }
   
   public MunicipioLocal getMunicipioLocal(String idMunicipioLocal, Municipio municipio) throws SQLException
   {
        List<MunicipioLocal> municipiosLocal = new ArrayList<MunicipioLocal>();
        List<Municipio> municipios = new ArrayList<Municipio>();
        
        PreparedStatement p = this.con.prepareStatement("SELECT ml.id AS id,m.nome AS nome, ml.indprincipal AS indprincipal"
                + "                                      FROM municipio m JOIN municipiolocal ml ON m.id = ml.idlocal"
                + "                                      WHERE id = ?");
        p.setInt(1, Integer.parseInt(idMunicipioLocal));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           MunicipioLocal municipioLocal = new MunicipioLocal();
           municipioLocal.setId(rs.getInt("id"));
           municipioLocal.setIndPrincipal(rs.getBoolean("indprincipal"));
           municipio.setNome(rs.getString("nomemunicipio"));
           municipiosLocal.add(municipioLocal);
           municipios.add(municipio);
        }
        rs.close();
        p.close();
        return municipiosLocal.get(0);
   }
   
   public ArrayList<MunicipioLocal> listarMunicipioLocal(int idLocal) throws Exception{
        ArrayList<MunicipioLocal> municipiosLocal = new ArrayList<MunicipioLocal>();
        
        PreparedStatement p = this.con.prepareStatement("SELECT ml.id as id, "
                + "                                             ml.indprincipal as indprincipal, "
                + "                                             m.id as idmunicipio, "
                + "                                             m.idestado as idestado, "
                + "                                             m.nome as nome "
                + "                                      FROM local l "
                + "                                      INNER JOIN municipiolocal ml ON l.id = ml.idlocal "
                + "                                      INNER JOIN municipio m ON ml.idmunicipio = m.id "
                + "                                      WHERE l.id = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Municipio municipio = new Municipio();
           municipio.setId(rs.getInt("idmunicipio"));
           municipio.setIdEstado(rs.getInt("idestado"));
           municipio.setNome(rs.getString("nome"));
           
           MunicipioLocal municipioLocal = new MunicipioLocal();
           municipioLocal.setId(rs.getInt("id"));
           municipioLocal.setIdLocal(idLocal);
           municipioLocal.setIdMunicipio(rs.getInt("idmunicipio"));
           municipioLocal.setIndPrincipal(rs.getBoolean("indprincipal"));
           municipioLocal.setMunicipio(municipio);

           municipiosLocal.add(municipioLocal);
        }
        rs.close();
        p.close();
        return municipiosLocal;
    }
    
}

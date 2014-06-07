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
import model.Local;
import model.MunicipioLocal;

/**
 *
 * @author Jaime
 */
public class LocalDao extends MainDao {
    
    
    public LocalDao()
    {
      super();
    }
    
    public void cadastrar(Local local) throws SQLException, Exception
                        
    {
            String sql = "INSERT INTO local (descricao, "
                    +                                                          "area,"
                    +                                                          "areaparcela,"
                    +                                                          "qtdebiomassa,"
                    +                                                          "qtdecarbono,"
                    +                                                          "qtdevolume,"
                    +                                                          "idtipoestimativa,"
                    +                                                          "idformacao,"
                    +                                                          "idespacamento,"
                    +                                                          "idtrabalhocientifico"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING local.id";
            PreparedStatement p = this.con.prepareStatement(sql);
            p.setString(1, local.getDescricao());
            p.setDouble(2, local.getArea());
            p.setDouble(3, local.getAreaParcela());
            p.setDouble(4, 0.0);
            p.setDouble(5, 0.0);
            p.setDouble(6, 0.0);
            p.setInt(7, local.getIdTipoEstimativa());
            p.setInt(8, local.getIdFormacao());
            p.setInt(9, local.getIdEspacamento());
            p.setInt(10, local.getIdTrabalhoCientifico());
            int idLocal = 0;
            ResultSet rs = p.executeQuery();
            if(rs.next()){
              idLocal = rs.getInt(1);
            }
             p.close();          
            for (MunicipioLocal ml : local.municipiosLocal) {
                p = this.con.prepareStatement("INSERT INTO municipiolocal (idlocal, "
                    +                                                      "idmunicipio,"
                    +                                                      "indprincipal"
                    +                                                      ") VALUES (?,?,?)");
                p.setInt(1, idLocal);
                p.setInt(2, ml.getIdMunicipio());
                p.setBoolean(3,ml.isIndPrincipal());
                p.executeUpdate();
            }
             p.close();           
            for (CoordenadaLocal cl : local.coordenadasLocal) {
                p = this.con.prepareStatement("INSERT INTO coordenadalocal (idlocal, "
                    +                                                      "latitude,"
                    +                                                      "longitude"
                    +                                                      ") VALUES (?,?,?)");
                p.setInt(1, idLocal);
                p.setDouble(2, cl.getLatitude());
                p.setDouble(3, cl.getLongitude());
                p.executeUpdate();
            }
            
            p.close();

    }
    
    
    public void deletar(Local local) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from local where id = ?");
            p.setInt(1, local.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Local local) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE local SET descricao = ?, "
                +                                                        "area = ?,"
                +                                                        "areaparcela = ?,"
                +                                                        "qtdebiomassa = ?,"
                +                                                        "qtdecarbono = ?,"
                +                                                        "qtdevolume = ?,"
                +                                                        "idtipoestimativa = ?,"
                +                                                        "idformacao = ?,"
                +                                                        "idespacamento = ?,"
                +                                                        "idtrabalhocientifico = ?"
                +                                                        " WHERE id = ?");
        p.setString(1, local.getDescricao());
        p.setDouble(2, local.getArea());
        p.setDouble(3, local.getAreaParcela());
        p.setDouble(4, 0.0);
        p.setDouble(5, 0.0);
        p.setDouble(6, 0.0);
        p.setInt(7, local.getIdTipoEstimativa());
        p.setInt(8, local.getIdFormacao());
        p.setInt(9, local.getIdEspacamento());
        p.setInt(10, local.getIdTrabalhoCientifico());
        
        p.setInt(11, local.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Local getLocal(int id) throws SQLException
   {
        List<Local> locais = new ArrayList<Local>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM local where id = ?");
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Local local = new Local();
           local.setId(rs.getInt("id"));
           local.setDescricao(rs.getString("descricao"));
           local.setArea(rs.getDouble("area"));
           local.setAreaParcela(rs.getDouble("areaparcela"));
           local.setQtdeBiomassa(rs.getDouble("qtdebiomassa"));
           local.setQtdeCarbono(rs.getDouble("qtdecarbono"));
           local.setQtdeVolume(rs.getDouble("qtdevolume"));
           local.setIdTipoEstimativa(rs.getInt("idtipoestimativa"));
           local.setIdFormacao(rs.getInt("idformacao"));
           local.setIdEspacamento(rs.getInt("idespacamento"));
           local.setIdTrabalhoCientifico(rs.getInt("idtrabalhocientifico"));
           locais.add(local);
        }
        rs.close();
        p.close();
        return locais.get(0);
   }
   
   public List<Local> listarLocais() throws Exception{
        List<Local> locais = new ArrayList<Local>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM local");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Local local = new Local();
           local.setId(rs.getInt("id"));
           local.setDescricao(rs.getString("descricao"));
           local.setArea(rs.getDouble("area"));
           local.setAreaParcela(rs.getDouble("areaparcela"));
           local.setQtdeBiomassa(rs.getDouble("qtdebiomassa"));
           local.setQtdeCarbono(rs.getDouble("qtdecarbono"));
           local.setQtdeVolume(rs.getDouble("qtdevolume"));
           local.setIdTipoEstimativa(rs.getInt("idtipoestimativa"));
           local.setIdFormacao(rs.getInt("idformacao"));
           local.setIdEspacamento(rs.getInt("idespacamento"));
           local.setIdTrabalhoCientifico(rs.getInt("idtrabalhocientifico"));
           locais.add(local);
        }
        rs.close();
        p.close();
        return locais;
    }

    public void updateBiomassa(Local local) throws SQLException {
        PreparedStatement p = this.con.prepareStatement("UPDATE local SET qtdebiomassa = ? WHERE id = ?");
        p.setDouble(1, local.getQtdeBiomassa());
        
        p.setInt(2, local.getId());
        p.executeUpdate();
        p.close();
    }

    public void updateCarbono(Local local) throws SQLException {
        PreparedStatement p = this.con.prepareStatement("UPDATE local SET qtdecarbono = ? WHERE id = ?");
        p.setDouble(1, local.getQtdeCarbono());
        
        p.setInt(2, local.getId());
        p.executeUpdate();
        p.close();
    }

    public void updateVolume(Local local) throws SQLException {
        PreparedStatement p = this.con.prepareStatement("UPDATE local SET qtdevolume = ? WHERE id = ?");
        p.setDouble(1, local.getQtdeVolume());
        
        p.setInt(2, local.getId());
        p.executeUpdate();
        p.close();
    }
    
}

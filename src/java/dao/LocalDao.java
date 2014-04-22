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
public class LocalDao {
    
    private Connection con = null;
    
    
    public LocalDao()
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
            Logger.getLogger(LocalDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrar(Local local,List<MunicipioLocal> municipiosLocal,List<CoordenadaLocal> coordenadasLocal) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO local (descricao, "
                    +                                                          "area,"
                    +                                                          "areaparcela,"
                    +                                                          "qtdebiomassa,"
                    +                                                          "qtdecarbono,"
                    +                                                          "qtdevolume,"
                    +                                                          "idtipoestimativa,"
                    +                                                          "idformacao,"
                    +                                                          "idespacamento,"
                    +                                                          "idtrabalhocientifico"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?,?,?)");
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
            
            p.executeUpdate();
            p.close();
            
            
            //FALTA CADASTRAR MunicipioLocal e CoordenadaLocal
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
                +                                                        "istrabalhocientifico = ?"
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
   
   public Local getLocal(String id) throws SQLException
   {
        List<Local> locais = new ArrayList<Local>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM local where id = ?");
        p.setInt(1, Integer.parseInt(id));
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
    
}

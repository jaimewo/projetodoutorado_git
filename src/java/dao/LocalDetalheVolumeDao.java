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
import model.LocalDetalheVolume;

/**
 *
 * @author Jaime
 */
public class LocalDetalheVolumeDao {
    
    private Connection con = null;
    
    
    public LocalDetalheVolumeDao()
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
            Logger.getLogger(LocalDetalheVolumeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrar(LocalDetalheVolume localDetalheVolume) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO localdetalhevolume (idlocal, "
                    +                                                          "qtdevolumemin,"
                    +                                                          "qtdevolumemed,"
                    +                                                          "qtdevolumemax,"
                    +                                                          "mediaparcela,"
                    +                                                          "variancia,"
                    +                                                          "desviopadrao,"
                    +                                                          "varianciamedia,"
                    +                                                          "erropadrao,"
                    +                                                          "coeficientevariacao,"
                    +                                                          "erroabsoluto,"
                    +                                                          "errorelativo,"
                    +                                                          "intervaloconfiancaminparcela,"
                    +                                                          "intervaloconfiancamaxparcela,"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            p.setInt   (1,  localDetalheVolume.getIdLocal());
            p.setDouble(2,  localDetalheVolume.getQtdeVolumeMin());
            p.setDouble(3,  localDetalheVolume.getQtdeVolumeMed());
            p.setDouble(4,  localDetalheVolume.getQtdeVolumeMax());
            p.setDouble(5,  localDetalheVolume.getMediaParcela());
            p.setDouble(6,  localDetalheVolume.getVariancia());
            p.setDouble(7,  localDetalheVolume.getDesvioPadrao());
            p.setDouble(8,  localDetalheVolume.getVarianciaMedia());
            p.setDouble(9,  localDetalheVolume.getErroPadrao());
            p.setDouble(10, localDetalheVolume.getCoeficienteVariacao());
            p.setDouble(11, localDetalheVolume.getErroAbsoluto());
            p.setDouble(12, localDetalheVolume.getErroRelativo());
            p.setDouble(13, localDetalheVolume.getIntervaloConfiancaMinParcela());
            p.setDouble(14, localDetalheVolume.getIntervaloConfiancaMaxParcela());
            
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(LocalDetalheVolume localDetalheVolume) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from localdetalhevolume where id = ?");
            p.setInt(1, localDetalheVolume.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(LocalDetalheVolume localDetalheVolume) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE localdetalhevolume SET idlocal = ?, "
                    +                                                          "qtdevolumemin = ?,"
                    +                                                          "qtdevolumemed = ?,"
                    +                                                          "qtdevolumemax = ?,"
                    +                                                          "mediaparcela = ?,"
                    +                                                          "variancia = ?,"
                    +                                                          "desviopadrao = ?,"
                    +                                                          "varianciamedia = ?,"
                    +                                                          "erropadrao = ?,"
                    +                                                          "coeficientevariacao = ?,"
                    +                                                          "erroabsoluto = ?,"
                    +                                                          "errorelativo = ?,"
                    +                                                          "intervaloconfiancaminparcela = ?,"
                    +                                                          "intervaloconfiancamaxparcela = ?"
                +                                                              " WHERE id = ?");
            p.setInt   (1,  localDetalheVolume.getIdLocal());
            p.setDouble(2,  localDetalheVolume.getQtdeVolumeMin());
            p.setDouble(3,  localDetalheVolume.getQtdeVolumeMed());
            p.setDouble(4,  localDetalheVolume.getQtdeVolumeMax());
            p.setDouble(5,  localDetalheVolume.getMediaParcela());
            p.setDouble(6,  localDetalheVolume.getVariancia());
            p.setDouble(7,  localDetalheVolume.getDesvioPadrao());
            p.setDouble(8,  localDetalheVolume.getVarianciaMedia());
            p.setDouble(9,  localDetalheVolume.getErroPadrao());
            p.setDouble(10, localDetalheVolume.getCoeficienteVariacao());
            p.setDouble(11, localDetalheVolume.getErroAbsoluto());
            p.setDouble(12, localDetalheVolume.getErroRelativo());
            p.setDouble(13, localDetalheVolume.getIntervaloConfiancaMinParcela());
            p.setDouble(14, localDetalheVolume.getIntervaloConfiancaMaxParcela());
        
        p.setInt(11, localDetalheVolume.getId());
        p.executeUpdate();
        p.close();
    }
   
   public LocalDetalheVolume getLocalDetalheVolume(String id) throws SQLException
   {
        List<LocalDetalheVolume> locaisDetalheVolume = new ArrayList<LocalDetalheVolume>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localdetalhevolume where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalDetalheVolume localDetalheVolume = new LocalDetalheVolume();
           localDetalheVolume.setId(rs.getInt("id"));
           localDetalheVolume.setIdLocal(rs.getInt("idlocal"));
           localDetalheVolume.setQtdeVolumeMin(rs.getDouble("qtdevolumemin"));
           localDetalheVolume.setQtdeVolumeMed(rs.getDouble("qtdevolumemed"));
           localDetalheVolume.setQtdeVolumeMax(rs.getDouble("qtdevolumemax"));
           localDetalheVolume.setMediaParcela(rs.getDouble("mediaparcela"));
           localDetalheVolume.setVariancia(rs.getDouble("variancia"));
           localDetalheVolume.setDesvioPadrao(rs.getDouble("desviopadrao"));
           localDetalheVolume.setVarianciaMedia(rs.getDouble("varianciamedia"));
           localDetalheVolume.setErroPadrao(rs.getDouble("erropadrao"));
           localDetalheVolume.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           localDetalheVolume.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           localDetalheVolume.setErroRelativo(rs.getDouble("errorelativo"));
           localDetalheVolume.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           localDetalheVolume.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheVolume.add(localDetalheVolume);
        }
        rs.close();
        p.close();
        return locaisDetalheVolume.get(0);
   }
   
   public List<LocalDetalheVolume> listarLocaisDetalheVolume() throws Exception{
        List<LocalDetalheVolume> locaisDetalheVolume = new ArrayList<LocalDetalheVolume>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localdetalhevolume");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalDetalheVolume localDetalheVolume = new LocalDetalheVolume();
           localDetalheVolume.setId(rs.getInt("id"));
           localDetalheVolume.setIdLocal(rs.getInt("idlocal"));
           localDetalheVolume.setQtdeVolumeMin(rs.getDouble("qtdevolumemin"));
           localDetalheVolume.setQtdeVolumeMed(rs.getDouble("qtdevolumemed"));
           localDetalheVolume.setQtdeVolumeMax(rs.getDouble("qtdevolumemax"));
           localDetalheVolume.setMediaParcela(rs.getDouble("mediaparcela"));
           localDetalheVolume.setVariancia(rs.getDouble("variancia"));
           localDetalheVolume.setDesvioPadrao(rs.getDouble("desviopadrao"));
           localDetalheVolume.setVarianciaMedia(rs.getDouble("varianciamedia"));
           localDetalheVolume.setErroPadrao(rs.getDouble("erropadrao"));
           localDetalheVolume.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           localDetalheVolume.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           localDetalheVolume.setErroRelativo(rs.getDouble("errorelativo"));
           localDetalheVolume.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           localDetalheVolume.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheVolume.add(localDetalheVolume);
        }
        rs.close();
        p.close();
        return locaisDetalheVolume;
    }
    
}

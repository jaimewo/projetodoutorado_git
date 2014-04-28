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
import model.LocalDetalheCarbono;

/**
 *
 * @author Jaime
 */
public class LocalDetalheCarbonoDao extends MainDao {
    
    public LocalDetalheCarbonoDao() {
        super();
    }
    public void cadastrar(LocalDetalheCarbono localDetalheCarbono) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO localdetalhecarbono (idlocal, "
                    +                                                          "qtdecarbonomin,"
                    +                                                          "qtdecarbonomed,"
                    +                                                          "qtdecarbonomax,"
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
            p.setInt   (1,  localDetalheCarbono.getIdLocal());
            p.setDouble(2,  localDetalheCarbono.getQtdeCarbonoMin());
            p.setDouble(3,  localDetalheCarbono.getQtdeCarbonoMed());
            p.setDouble(4,  localDetalheCarbono.getQtdeCarbonoMax());
            p.setDouble(5,  localDetalheCarbono.getMediaParcela());
            p.setDouble(6,  localDetalheCarbono.getVariancia());
            p.setDouble(7,  localDetalheCarbono.getDesvioPadrao());
            p.setDouble(8,  localDetalheCarbono.getVarianciaMedia());
            p.setDouble(9,  localDetalheCarbono.getErroPadrao());
            p.setDouble(10, localDetalheCarbono.getCoeficienteVariacao());
            p.setDouble(11, localDetalheCarbono.getErroAbsoluto());
            p.setDouble(12, localDetalheCarbono.getErroRelativo());
            p.setDouble(13, localDetalheCarbono.getIntervaloConfiancaMinParcela());
            p.setDouble(14, localDetalheCarbono.getIntervaloConfiancaMaxParcela());
            
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(LocalDetalheCarbono localDetalheCarbono) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from localdetalhecarbono where id = ?");
            p.setInt(1, localDetalheCarbono.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(LocalDetalheCarbono localDetalheCarbono) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE localdetalhecarbono SET idlocal = ?, "
                    +                                                          "qtdecarbonomin = ?,"
                    +                                                          "qtdecarbonomed = ?,"
                    +                                                          "qtdecarbonomax = ?,"
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
            p.setInt   (1,  localDetalheCarbono.getIdLocal());
            p.setDouble(2,  localDetalheCarbono.getQtdeCarbonoMin());
            p.setDouble(3,  localDetalheCarbono.getQtdeCarbonoMed());
            p.setDouble(4,  localDetalheCarbono.getQtdeCarbonoMax());
            p.setDouble(5,  localDetalheCarbono.getMediaParcela());
            p.setDouble(6,  localDetalheCarbono.getVariancia());
            p.setDouble(7,  localDetalheCarbono.getDesvioPadrao());
            p.setDouble(8,  localDetalheCarbono.getVarianciaMedia());
            p.setDouble(9,  localDetalheCarbono.getErroPadrao());
            p.setDouble(10, localDetalheCarbono.getCoeficienteVariacao());
            p.setDouble(11, localDetalheCarbono.getErroAbsoluto());
            p.setDouble(12, localDetalheCarbono.getErroRelativo());
            p.setDouble(13, localDetalheCarbono.getIntervaloConfiancaMinParcela());
            p.setDouble(14, localDetalheCarbono.getIntervaloConfiancaMaxParcela());
        
        p.setInt(11, localDetalheCarbono.getId());
        p.executeUpdate();
        p.close();
    }
   
   public LocalDetalheCarbono getLocalDetalheCarbono(String id) throws SQLException
   {
        List<LocalDetalheCarbono> locaisDetalheCarbono = new ArrayList<LocalDetalheCarbono>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localdetalhecarbono where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalDetalheCarbono localDetalheCarbono = new LocalDetalheCarbono();
           localDetalheCarbono.setId(rs.getInt("id"));
           localDetalheCarbono.setIdLocal(rs.getInt("idlocal"));
           localDetalheCarbono.setQtdeCarbonoMin(rs.getDouble("qtdecarbonomin"));
           localDetalheCarbono.setQtdeCarbonoMed(rs.getDouble("qtdecarbonomed"));
           localDetalheCarbono.setQtdeCarbonoMax(rs.getDouble("qtdecarbonomax"));
           localDetalheCarbono.setMediaParcela(rs.getDouble("mediaparcela"));
           localDetalheCarbono.setVariancia(rs.getDouble("variancia"));
           localDetalheCarbono.setDesvioPadrao(rs.getDouble("desviopadrao"));
           localDetalheCarbono.setVarianciaMedia(rs.getDouble("varianciamedia"));
           localDetalheCarbono.setErroPadrao(rs.getDouble("erropadrao"));
           localDetalheCarbono.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           localDetalheCarbono.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           localDetalheCarbono.setErroRelativo(rs.getDouble("errorelativo"));
           localDetalheCarbono.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           localDetalheCarbono.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheCarbono.add(localDetalheCarbono);
        }
        rs.close();
        p.close();
        return locaisDetalheCarbono.get(0);
   }
   
   public List<LocalDetalheCarbono> listarLocaisDetalheCarbono() throws Exception{
        List<LocalDetalheCarbono> locaisDetalheCarbono = new ArrayList<LocalDetalheCarbono>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localdetalhecarbono");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalDetalheCarbono localDetalheCarbono = new LocalDetalheCarbono();
           localDetalheCarbono.setId(rs.getInt("id"));
           localDetalheCarbono.setIdLocal(rs.getInt("idlocal"));
           localDetalheCarbono.setQtdeCarbonoMin(rs.getDouble("qtdecarbonomin"));
           localDetalheCarbono.setQtdeCarbonoMed(rs.getDouble("qtdecarbonomed"));
           localDetalheCarbono.setQtdeCarbonoMax(rs.getDouble("qtdecarbonomax"));
           localDetalheCarbono.setMediaParcela(rs.getDouble("mediaparcela"));
           localDetalheCarbono.setVariancia(rs.getDouble("variancia"));
           localDetalheCarbono.setDesvioPadrao(rs.getDouble("desviopadrao"));
           localDetalheCarbono.setVarianciaMedia(rs.getDouble("varianciamedia"));
           localDetalheCarbono.setErroPadrao(rs.getDouble("erropadrao"));
           localDetalheCarbono.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           localDetalheCarbono.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           localDetalheCarbono.setErroRelativo(rs.getDouble("errorelativo"));
           localDetalheCarbono.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           localDetalheCarbono.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheCarbono.add(localDetalheCarbono);
        }
        rs.close();
        p.close();
        return locaisDetalheCarbono;
    }
    
}

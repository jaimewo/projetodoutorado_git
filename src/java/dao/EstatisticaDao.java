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
import model.Estatistica;

/**
 *
 * @author Jaime
 */
public class EstatisticaDao extends MainDao{
        
    public EstatisticaDao() {
        super();
    }

    
    public void cadastrar(Estatistica estatistica) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO estatistica (idlocal, "
                    +                                                          "qtdeminima,"
                    +                                                          "qtdemedia,"
                    +                                                          "qtdemaxima,"
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
            p.setInt   (1,  estatistica.getIdLocal());
            p.setDouble(2,  estatistica.getQtdeMinima());
            p.setDouble(3,  estatistica.getQtdeMedia());
            p.setDouble(4,  estatistica.getQtdeMaxima());
            p.setDouble(5,  estatistica.getMediaParcela());
            p.setDouble(6,  estatistica.getVariancia());
            p.setDouble(7,  estatistica.getDesvioPadrao());
            p.setDouble(8,  estatistica.getVarianciaMedia());
            p.setDouble(9,  estatistica.getErroPadrao());
            p.setDouble(10, estatistica.getCoeficienteVariacao());
            p.setDouble(11, estatistica.getErroAbsoluto());
            p.setDouble(12, estatistica.getErroRelativo());
            p.setDouble(13, estatistica.getIntervaloConfiancaMinParcela());
            p.setDouble(14, estatistica.getIntervaloConfiancaMaxParcela());
            
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(Estatistica estatistica) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from estatistica where id = ?");
            p.setInt(1, estatistica.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Estatistica estatistica) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE estatistica SET idlocal = ?, "
                    +                                                          "idvariavelinteresse = ?,"
                    +                                                          "qtdeminima = ?,"
                    +                                                          "qtdemedia = ?,"
                    +                                                          "qtdemaxima = ?,"
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
            p.setInt   (1,  estatistica.getIdLocal());
            p.setInt   (2,  estatistica.getIdVariavelInteresse());
            p.setDouble(3,  estatistica.getQtdeMinima());
            p.setDouble(4,  estatistica.getQtdeMedia());
            p.setDouble(5,  estatistica.getQtdeMaxima());
            p.setDouble(6,  estatistica.getMediaParcela());
            p.setDouble(7,  estatistica.getVariancia());
            p.setDouble(8,  estatistica.getDesvioPadrao());
            p.setDouble(9,  estatistica.getVarianciaMedia());
            p.setDouble(10,  estatistica.getErroPadrao());
            p.setDouble(11, estatistica.getCoeficienteVariacao());
            p.setDouble(12, estatistica.getErroAbsoluto());
            p.setDouble(13, estatistica.getErroRelativo());
            p.setDouble(14, estatistica.getIntervaloConfiancaMinParcela());
            p.setDouble(15, estatistica.getIntervaloConfiancaMaxParcela());
        
        p.setInt(16, estatistica.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Estatistica getEstatistica(String id) throws SQLException
   {
        List<Estatistica> locaisDetalheBiomassa = new ArrayList<Estatistica>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatistica where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Estatistica estatistica = new Estatistica();
           estatistica.setId(rs.getInt("id"));
           estatistica.setIdLocal(rs.getInt("idlocal"));
           estatistica.setQtdeMinima(rs.getDouble("qtdeminima"));
           estatistica.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatistica.setQtdeMaxima(rs.getDouble("qtdemaxima"));
           estatistica.setMediaParcela(rs.getDouble("mediaparcela"));
           estatistica.setVariancia(rs.getDouble("variancia"));
           estatistica.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatistica.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatistica.setErroPadrao(rs.getDouble("erropadrao"));
           estatistica.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatistica.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatistica.setErroRelativo(rs.getDouble("errorelativo"));
           estatistica.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           estatistica.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheBiomassa.add(estatistica);
        }
        rs.close();
        p.close();
        return locaisDetalheBiomassa.get(0);
   }
   
      public Estatistica getEstatistica(int idLocal) throws SQLException
   {
        List<Estatistica> locaisDetalheBiomassa = new ArrayList<Estatistica>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatistica where id = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Estatistica estatistica = new Estatistica();
           estatistica.setId(rs.getInt("id"));
           estatistica.setIdLocal(rs.getInt("idlocal"));
           estatistica.setQtdeMinima(rs.getDouble("qtdeminima"));
           estatistica.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatistica.setQtdeMaxima(rs.getDouble("qtdemaxima"));
           estatistica.setMediaParcela(rs.getDouble("mediaparcela"));
           estatistica.setVariancia(rs.getDouble("variancia"));
           estatistica.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatistica.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatistica.setErroPadrao(rs.getDouble("erropadrao"));
           estatistica.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatistica.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatistica.setErroRelativo(rs.getDouble("errorelativo"));
           estatistica.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           estatistica.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheBiomassa.add(estatistica);
        }
        rs.close();
        p.close();
        return locaisDetalheBiomassa.get(0);
   }
   public List<Estatistica> listarLocaisDetalheBiomassa() throws Exception{
        List<Estatistica> locaisDetalheBiomassa = new ArrayList<Estatistica>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatistica");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Estatistica estatistica = new Estatistica();
           estatistica.setId(rs.getInt("id"));
           estatistica.setIdLocal(rs.getInt("idlocal"));
           estatistica.setQtdeMinima(rs.getDouble("qtdeminima"));
           estatistica.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatistica.setQtdeMaxima(rs.getDouble("qtdemaxima"));
           estatistica.setMediaParcela(rs.getDouble("mediaparcela"));
           estatistica.setVariancia(rs.getDouble("variancia"));
           estatistica.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatistica.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatistica.setErroPadrao(rs.getDouble("erropadrao"));
           estatistica.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatistica.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatistica.setErroRelativo(rs.getDouble("errorelativo"));
           estatistica.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           estatistica.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheBiomassa.add(estatistica);
        }
        rs.close();
        p.close();
        return locaisDetalheBiomassa;
    }
    
}

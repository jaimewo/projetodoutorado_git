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
                    +                                                          "idvariavelinteresse,"
                    +                                                          "qtdemedia,"
                    +                                                          "mediaparcela,"
                    +                                                          "variancia,"
                    +                                                          "desviopadrao,"
                    +                                                          "varianciamedia,"
                    +                                                          "erropadrao,"
                    +                                                          "coeficientevariacao,"
                    +                                                          "erroabsoluto,"
                    +                                                          "errorelativo,"
                    +                                                          "intervaloconfiancamin,"
                    +                                                          "intervaloconfiancamax"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            p.setInt   (1,  estatistica.getIdLocal());
            p.setInt   (2,  estatistica.getIdVariavelInteresse());            
            p.setDouble(3,  estatistica.getQtdeMedia());
            p.setDouble(4,  estatistica.getMediaParcela());
            p.setDouble(5,  estatistica.getVariancia());
            p.setDouble(6,  estatistica.getDesvioPadrao());
            p.setDouble(7,  estatistica.getVarianciaMedia());
            p.setDouble(8,  estatistica.getErroPadrao());
            p.setDouble(9, estatistica.getCoeficienteVariacao());
            p.setDouble(10, estatistica.getErroAbsoluto());
            p.setDouble(11, estatistica.getErroRelativo());
            p.setDouble(12, estatistica.getIntervaloConfiancaMin());
            p.setDouble(13, estatistica.getIntervaloConfiancaMax());
            
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
        
    
    public void deletarEstatisticaLocal(int idLocal) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from estatistica where idlocal = ?");
            p.setInt(1, idLocal);
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Estatistica estatistica) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE estatistica SET idlocal = ?, "
                    +                                                          "idvariavelinteresse = ?,"
                    +                                                          "qtdemedia = ?,"
                    +                                                          "mediaparcela = ?,"
                    +                                                          "variancia = ?,"
                    +                                                          "desviopadrao = ?,"
                    +                                                          "varianciamedia = ?,"
                    +                                                          "erropadrao = ?,"
                    +                                                          "coeficientevariacao = ?,"
                    +                                                          "erroabsoluto = ?,"
                    +                                                          "errorelativo = ?,"
                    +                                                          "intervaloconfiancamin = ?,"
                    +                                                          "intervaloconfiancamax = ?"
                +                                                              " WHERE id = ?");
            p.setInt   (1,  estatistica.getIdLocal());
            p.setInt   (2,  estatistica.getIdVariavelInteresse());
            p.setDouble(3,  estatistica.getQtdeMedia());
            p.setDouble(4,  estatistica.getMediaParcela());
            p.setDouble(5,  estatistica.getVariancia());
            p.setDouble(6,  estatistica.getDesvioPadrao());
            p.setDouble(7,  estatistica.getVarianciaMedia());
            p.setDouble(8,  estatistica.getErroPadrao());
            p.setDouble(9, estatistica.getCoeficienteVariacao());
            p.setDouble(10, estatistica.getErroAbsoluto());
            p.setDouble(11, estatistica.getErroRelativo());
            p.setDouble(12, estatistica.getIntervaloConfiancaMin());
            p.setDouble(13, estatistica.getIntervaloConfiancaMax());
        
        p.setInt(14, estatistica.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Estatistica getEstatistica(String id) throws SQLException
   {
        List<Estatistica> estatisticas = new ArrayList<Estatistica>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatistica where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Estatistica estatistica = new Estatistica();
           estatistica.setId(rs.getInt("id"));
           estatistica.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatistica.setIdLocal(rs.getInt("idlocal"));
           estatistica.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatistica.setMediaParcela(rs.getDouble("mediaparcela"));
           estatistica.setVariancia(rs.getDouble("variancia"));
           estatistica.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatistica.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatistica.setErroPadrao(rs.getDouble("erropadrao"));
           estatistica.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatistica.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatistica.setErroRelativo(rs.getDouble("errorelativo"));
           estatistica.setIntervaloConfiancaMin(rs.getDouble("intervaloconfiancamin"));
           estatistica.setIntervaloConfiancaMax(rs.getDouble("intervaloconfiancamax"));
           estatisticas.add(estatistica);
        }
        rs.close();
        p.close();
        return estatisticas.get(0);
   }
   
      public Estatistica getEstatistica(int idLocal) throws SQLException
   {
        List<Estatistica> estatisticas = new ArrayList<Estatistica>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatistica where id = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Estatistica estatistica = new Estatistica();
           estatistica.setId(rs.getInt("id"));
           estatistica.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatistica.setIdLocal(rs.getInt("idlocal"));
           estatistica.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatistica.setMediaParcela(rs.getDouble("mediaparcela"));
           estatistica.setVariancia(rs.getDouble("variancia"));
           estatistica.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatistica.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatistica.setErroPadrao(rs.getDouble("erropadrao"));
           estatistica.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatistica.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatistica.setErroRelativo(rs.getDouble("errorelativo"));
           estatistica.setIntervaloConfiancaMin(rs.getDouble("intervaloconfiancamin"));
           estatistica.setIntervaloConfiancaMax(rs.getDouble("intervaloconfiancamax"));
           estatisticas.add(estatistica);
        }
        rs.close();
        p.close();
        return estatisticas.get(0);
   }
   public List<Estatistica> listarEstatisticas() throws Exception{
        List<Estatistica> estatisticas = new ArrayList<Estatistica>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatistica");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Estatistica estatistica = new Estatistica();
           estatistica.setId(rs.getInt("id"));
           estatistica.setIdLocal(rs.getInt("idlocal"));
           estatistica.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatistica.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatistica.setMediaParcela(rs.getDouble("mediaparcela"));
           estatistica.setVariancia(rs.getDouble("variancia"));
           estatistica.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatistica.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatistica.setErroPadrao(rs.getDouble("erropadrao"));
           estatistica.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatistica.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatistica.setErroRelativo(rs.getDouble("errorelativo"));
           estatistica.setIntervaloConfiancaMin(rs.getDouble("intervaloconfiancamin"));
           estatistica.setIntervaloConfiancaMax(rs.getDouble("intervaloconfiancamax"));
           estatisticas.add(estatistica);
        }
        rs.close();
        p.close();
        return estatisticas;
    }
    
}

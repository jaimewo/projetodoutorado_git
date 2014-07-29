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
import model.EstatisticaInventario;

/**
 *
 * @author Jaime
 */
public class EstatisticaInventarioDao extends MainDao{
        
    public EstatisticaInventarioDao() {
        super();
    }

    
    public void cadastrar(EstatisticaInventario estatisticaInventario) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO estatisticainventario (idlocal, "
                    +                                                          "idvariavelinteresse,"
                    +                                                          "idmetodocalculo,"                    
                    +                                                          "qtdemedia,"
                    +                                                          "mediaparcela,"
                    +                                                          "variancia,"
                    +                                                          "desviopadrao,"
                    +                                                          "varianciamedia,"
                    +                                                          "erropadrao,"
                    +                                                          "coeficientevariacao,"
                    +                                                          "erroabsoluto,"
                    +                                                          "errorelativo,"
                    +                                                          "intervaloconfiancaminmedia,"
                    +                                                          "intervaloconfiancamaxmedia,"
                    +                                                          "intervaloconfiancamintotal,"
                    +                                                          "intervaloconfiancamaxtotal"
                    
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            p.setInt   (1,  estatisticaInventario.getIdLocal());
            p.setInt   (2,  estatisticaInventario.getIdVariavelInteresse());            
            p.setInt   (3,  estatisticaInventario.getIdMetodoCalculo());                        
            p.setDouble(4,  estatisticaInventario.getQtdeMedia());
            p.setDouble(5,  estatisticaInventario.getMediaParcela());
            p.setDouble(6,  estatisticaInventario.getVariancia());
            p.setDouble(7,  estatisticaInventario.getDesvioPadrao());
            p.setDouble(8,  estatisticaInventario.getVarianciaMedia());
            p.setDouble(9,  estatisticaInventario.getErroPadrao());
            p.setDouble(10, estatisticaInventario.getCoeficienteVariacao());
            p.setDouble(11, estatisticaInventario.getErroAbsoluto());
            p.setDouble(12, estatisticaInventario.getErroRelativo());
            p.setDouble(13, estatisticaInventario.getIntervaloConfiancaMinMedia());
            p.setDouble(14, estatisticaInventario.getIntervaloConfiancaMaxMedia());
            
            p.executeUpdate();
            p.close();
    }
    
    public void deletar(EstatisticaInventario estatisticaInventario) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from estatisticainventario where id = ?");
            p.setInt(1, estatisticaInventario.getId());
            p.executeUpdate();
            p.close();
        
    }
        
    
    public void deletarEstatisticaLocal(EstatisticaInventario estatisticaInventario) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from estatisticainventario "
                    +                                       "where idlocal = ? " 
                    +                                       "and   idvariavelinteresse = ? "
                    +                                       "and   idmetodocalculo = ?");
            p.setInt(1, estatisticaInventario.idLocal);
            p.setInt(2, estatisticaInventario.idVariavelInteresse);            
            p.setInt(3, estatisticaInventario.idMetodoCalculo);                        
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(EstatisticaInventario estatisticaInventario) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE estatisticainventario SET idlocal = ?, "
                    +                                                          "idvariavelinteresse = ?,"
                    +                                                          "idmetodocalculo = ?,"                
                    +                                                          "qtdemedia = ?,"
                    +                                                          "mediaparcela = ?,"
                    +                                                          "variancia = ?,"
                    +                                                          "desviopadrao = ?,"
                    +                                                          "varianciamedia = ?,"
                    +                                                          "erropadrao = ?,"
                    +                                                          "coeficientevariacao = ?,"
                    +                                                          "erroabsoluto = ?,"
                    +                                                          "errorelativo = ?,"
                    +                                                          "intervaloconfiancaminmedia = ?,"
                    +                                                          "intervaloconfiancamaxmedia = ?,"                
                    +                                                          "intervaloconfiancamintotal = ?,"
                    +                                                          "intervaloconfiancamaxtotal = ?"
                    +                                                          " WHERE id = ?");
        p.setInt   (1,  estatisticaInventario.getIdLocal());
        p.setInt   (2,  estatisticaInventario.getIdVariavelInteresse());
        p.setInt   (3,  estatisticaInventario.getIdMetodoCalculo());            
        p.setDouble(4,  estatisticaInventario.getQtdeMedia());
        p.setDouble(5,  estatisticaInventario.getMediaParcela());
        p.setDouble(6,  estatisticaInventario.getVariancia());
        p.setDouble(7,  estatisticaInventario.getDesvioPadrao());
        p.setDouble(8,  estatisticaInventario.getVarianciaMedia());
        p.setDouble(9,  estatisticaInventario.getErroPadrao());
        p.setDouble(10, estatisticaInventario.getCoeficienteVariacao());
        p.setDouble(11, estatisticaInventario.getErroAbsoluto());
        p.setDouble(12, estatisticaInventario.getErroRelativo());
        p.setDouble(13, estatisticaInventario.getIntervaloConfiancaMinMedia());
        p.setDouble(14, estatisticaInventario.getIntervaloConfiancaMaxMedia());
        p.setDouble(15, estatisticaInventario.getIntervaloConfiancaMinTotal());
        p.setDouble(16, estatisticaInventario.getIntervaloConfiancaMaxTotal());
        
        p.setInt(17, estatisticaInventario.getId());
        p.executeUpdate();
        p.close();
    }
   
   public EstatisticaInventario getEstatisticaInventario(String id) throws SQLException
   {
        ArrayList<EstatisticaInventario> estatisticasInventario = new ArrayList<EstatisticaInventario>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatisticaInventario where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
           estatisticaInventario.setId(rs.getInt("id"));
           estatisticaInventario.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatisticaInventario.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));           
           estatisticaInventario.setIdLocal(rs.getInt("idlocal"));
           estatisticaInventario.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatisticaInventario.setMediaParcela(rs.getDouble("mediaparcela"));
           estatisticaInventario.setVariancia(rs.getDouble("variancia"));
           estatisticaInventario.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatisticaInventario.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatisticaInventario.setErroPadrao(rs.getDouble("erropadrao"));
           estatisticaInventario.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatisticaInventario.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatisticaInventario.setErroRelativo(rs.getDouble("errorelativo"));
           estatisticaInventario.setIntervaloConfiancaMinMedia(rs.getDouble("intervaloconfiancaminmedia"));
           estatisticaInventario.setIntervaloConfiancaMaxMedia(rs.getDouble("intervaloconfiancamaxmedia"));
           estatisticaInventario.setIntervaloConfiancaMinTotal(rs.getDouble("intervaloconfiancamintotal"));
           estatisticaInventario.setIntervaloConfiancaMaxTotal(rs.getDouble("intervaloconfiancamaxtotal"));
           estatisticasInventario.add(estatisticaInventario);
        }
        rs.close();
        p.close();
        return estatisticasInventario.get(0);
   }
   
      public EstatisticaInventario getEstatisticaInventario(int idLocal,int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        ArrayList<EstatisticaInventario> estatisticasInventario = new ArrayList<EstatisticaInventario>();
        PreparedStatement p = this.con.prepareStatement("SELECT * "
                +                                       "FROM estatisticainventario "
                +                                       "WHERE idlocal = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ?");
        p.setInt(1, idLocal);
        p.setInt(1, idVariavelInteresse);
        p.setInt(1, idMetodoCalculo);
        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
           estatisticaInventario.setId(rs.getInt("id"));
           estatisticaInventario.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatisticaInventario.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));           
           estatisticaInventario.setIdLocal(rs.getInt("idlocal"));
           estatisticaInventario.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatisticaInventario.setMediaParcela(rs.getDouble("mediaparcela"));
           estatisticaInventario.setVariancia(rs.getDouble("variancia"));
           estatisticaInventario.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatisticaInventario.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatisticaInventario.setErroPadrao(rs.getDouble("erropadrao"));
           estatisticaInventario.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatisticaInventario.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatisticaInventario.setErroRelativo(rs.getDouble("errorelativo"));
           estatisticaInventario.setIntervaloConfiancaMinMedia(rs.getDouble("intervaloconfiancaminmedia"));
           estatisticaInventario.setIntervaloConfiancaMaxMedia(rs.getDouble("intervaloconfiancamaxmedia"));
           estatisticaInventario.setIntervaloConfiancaMinTotal(rs.getDouble("intervaloconfiancamintotal"));
           estatisticaInventario.setIntervaloConfiancaMaxTotal(rs.getDouble("intervaloconfiancamaxtotal"));
           estatisticasInventario.add(estatisticaInventario);
        }
        rs.close();
        p.close();
        return estatisticasInventario.get(0);
   }
   public ArrayList<EstatisticaInventario> listarEstatisticasInventario() throws Exception{
       
        ArrayList<EstatisticaInventario> estatisticasInventario = new ArrayList<EstatisticaInventario>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatisticainventario");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           EstatisticaInventario estatisticaInventario = new EstatisticaInventario();
           estatisticaInventario.setId(rs.getInt("id"));
           estatisticaInventario.setIdLocal(rs.getInt("idlocal"));
           estatisticaInventario.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatisticaInventario.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));           
           estatisticaInventario.setQtdeMedia(rs.getDouble("qtdemedia"));
           estatisticaInventario.setMediaParcela(rs.getDouble("mediaparcela"));
           estatisticaInventario.setVariancia(rs.getDouble("variancia"));
           estatisticaInventario.setDesvioPadrao(rs.getDouble("desviopadrao"));
           estatisticaInventario.setVarianciaMedia(rs.getDouble("varianciamedia"));
           estatisticaInventario.setErroPadrao(rs.getDouble("erropadrao"));
           estatisticaInventario.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           estatisticaInventario.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           estatisticaInventario.setErroRelativo(rs.getDouble("errorelativo"));
           estatisticaInventario.setIntervaloConfiancaMinMedia(rs.getDouble("intervaloconfiancaminmedia"));
           estatisticaInventario.setIntervaloConfiancaMaxMedia(rs.getDouble("intervaloconfiancamaxmedia"));
           estatisticaInventario.setIntervaloConfiancaMinTotal(rs.getDouble("intervaloconfiancamintotal"));
           estatisticaInventario.setIntervaloConfiancaMaxTotal(rs.getDouble("intervaloconfiancamaxtotal"));
           estatisticasInventario.add(estatisticaInventario);
        }
        rs.close();
        p.close();
        return estatisticasInventario;
    }
    
}

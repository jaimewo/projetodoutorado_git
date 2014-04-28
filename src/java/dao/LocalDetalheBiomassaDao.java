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
import model.LocalDetalheBiomassa;

/**
 *
 * @author Jaime
 */
public class LocalDetalheBiomassaDao extends MainDao{
        
    public LocalDetalheBiomassaDao() {
        super();
    }

    
    public void cadastrar(LocalDetalheBiomassa localDetalheBiomassa) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO localdetalhebiomassa (idlocal, "
                    +                                                          "qtdebiomassamin,"
                    +                                                          "qtdebiomassamed,"
                    +                                                          "qtdebiomassamax,"
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
            p.setInt   (1,  localDetalheBiomassa.getIdLocal());
            p.setDouble(2,  localDetalheBiomassa.getQtdeBiomassaMin());
            p.setDouble(3,  localDetalheBiomassa.getQtdeBiomassaMed());
            p.setDouble(4,  localDetalheBiomassa.getQtdeBiomassaMax());
            p.setDouble(5,  localDetalheBiomassa.getMediaParcela());
            p.setDouble(6,  localDetalheBiomassa.getVariancia());
            p.setDouble(7,  localDetalheBiomassa.getDesvioPadrao());
            p.setDouble(8,  localDetalheBiomassa.getVarianciaMedia());
            p.setDouble(9,  localDetalheBiomassa.getErroPadrao());
            p.setDouble(10, localDetalheBiomassa.getCoeficienteVariacao());
            p.setDouble(11, localDetalheBiomassa.getErroAbsoluto());
            p.setDouble(12, localDetalheBiomassa.getErroRelativo());
            p.setDouble(13, localDetalheBiomassa.getIntervaloConfiancaMinParcela());
            p.setDouble(14, localDetalheBiomassa.getIntervaloConfiancaMaxParcela());
            
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(LocalDetalheBiomassa localDetalheBiomassa) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from localdetalhebiomassa where id = ?");
            p.setInt(1, localDetalheBiomassa.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(LocalDetalheBiomassa localDetalheBiomassa) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE localdetalhebiomassa SET idlocal = ?, "
                    +                                                          "qtdebiomassamin = ?,"
                    +                                                          "qtdebiomassamed = ?,"
                    +                                                          "qtdebiomassamax = ?,"
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
            p.setInt   (1,  localDetalheBiomassa.getIdLocal());
            p.setDouble(2,  localDetalheBiomassa.getQtdeBiomassaMin());
            p.setDouble(3,  localDetalheBiomassa.getQtdeBiomassaMed());
            p.setDouble(4,  localDetalheBiomassa.getQtdeBiomassaMax());
            p.setDouble(5,  localDetalheBiomassa.getMediaParcela());
            p.setDouble(6,  localDetalheBiomassa.getVariancia());
            p.setDouble(7,  localDetalheBiomassa.getDesvioPadrao());
            p.setDouble(8,  localDetalheBiomassa.getVarianciaMedia());
            p.setDouble(9,  localDetalheBiomassa.getErroPadrao());
            p.setDouble(10, localDetalheBiomassa.getCoeficienteVariacao());
            p.setDouble(11, localDetalheBiomassa.getErroAbsoluto());
            p.setDouble(12, localDetalheBiomassa.getErroRelativo());
            p.setDouble(13, localDetalheBiomassa.getIntervaloConfiancaMinParcela());
            p.setDouble(14, localDetalheBiomassa.getIntervaloConfiancaMaxParcela());
        
        p.setInt(11, localDetalheBiomassa.getId());
        p.executeUpdate();
        p.close();
    }
   
   public LocalDetalheBiomassa getLocalDetalheBiomassa(String id) throws SQLException
   {
        List<LocalDetalheBiomassa> locaisDetalheBiomassa = new ArrayList<LocalDetalheBiomassa>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localdetalhebiomassa where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalDetalheBiomassa localDetalheBiomassa = new LocalDetalheBiomassa();
           localDetalheBiomassa.setId(rs.getInt("id"));
           localDetalheBiomassa.setIdLocal(rs.getInt("idlocal"));
           localDetalheBiomassa.setQtdeBiomassaMin(rs.getDouble("qtdebiomassamin"));
           localDetalheBiomassa.setQtdeBiomassaMed(rs.getDouble("qtdebiomassamed"));
           localDetalheBiomassa.setQtdeBiomassaMax(rs.getDouble("qtdebiomassamax"));
           localDetalheBiomassa.setMediaParcela(rs.getDouble("mediaparcela"));
           localDetalheBiomassa.setVariancia(rs.getDouble("variancia"));
           localDetalheBiomassa.setDesvioPadrao(rs.getDouble("desviopadrao"));
           localDetalheBiomassa.setVarianciaMedia(rs.getDouble("varianciamedia"));
           localDetalheBiomassa.setErroPadrao(rs.getDouble("erropadrao"));
           localDetalheBiomassa.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           localDetalheBiomassa.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           localDetalheBiomassa.setErroRelativo(rs.getDouble("errorelativo"));
           localDetalheBiomassa.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           localDetalheBiomassa.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheBiomassa.add(localDetalheBiomassa);
        }
        rs.close();
        p.close();
        return locaisDetalheBiomassa.get(0);
   }
   
      public LocalDetalheBiomassa getLocalDetalheBiomassa(int idLocal) throws SQLException
   {
        List<LocalDetalheBiomassa> locaisDetalheBiomassa = new ArrayList<LocalDetalheBiomassa>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localdetalhebiomassa where id = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalDetalheBiomassa localDetalheBiomassa = new LocalDetalheBiomassa();
           localDetalheBiomassa.setId(rs.getInt("id"));
           localDetalheBiomassa.setIdLocal(rs.getInt("idlocal"));
           localDetalheBiomassa.setQtdeBiomassaMin(rs.getDouble("qtdebiomassamin"));
           localDetalheBiomassa.setQtdeBiomassaMed(rs.getDouble("qtdebiomassamed"));
           localDetalheBiomassa.setQtdeBiomassaMax(rs.getDouble("qtdebiomassamax"));
           localDetalheBiomassa.setMediaParcela(rs.getDouble("mediaparcela"));
           localDetalheBiomassa.setVariancia(rs.getDouble("variancia"));
           localDetalheBiomassa.setDesvioPadrao(rs.getDouble("desviopadrao"));
           localDetalheBiomassa.setVarianciaMedia(rs.getDouble("varianciamedia"));
           localDetalheBiomassa.setErroPadrao(rs.getDouble("erropadrao"));
           localDetalheBiomassa.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           localDetalheBiomassa.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           localDetalheBiomassa.setErroRelativo(rs.getDouble("errorelativo"));
           localDetalheBiomassa.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           localDetalheBiomassa.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheBiomassa.add(localDetalheBiomassa);
        }
        rs.close();
        p.close();
        return locaisDetalheBiomassa.get(0);
   }
   public List<LocalDetalheBiomassa> listarLocaisDetalheBiomassa() throws Exception{
        List<LocalDetalheBiomassa> locaisDetalheBiomassa = new ArrayList<LocalDetalheBiomassa>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localdetalhebiomassa");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalDetalheBiomassa localDetalheBiomassa = new LocalDetalheBiomassa();
           localDetalheBiomassa.setId(rs.getInt("id"));
           localDetalheBiomassa.setIdLocal(rs.getInt("idlocal"));
           localDetalheBiomassa.setQtdeBiomassaMin(rs.getDouble("qtdebiomassamin"));
           localDetalheBiomassa.setQtdeBiomassaMed(rs.getDouble("qtdebiomassamed"));
           localDetalheBiomassa.setQtdeBiomassaMax(rs.getDouble("qtdebiomassamax"));
           localDetalheBiomassa.setMediaParcela(rs.getDouble("mediaparcela"));
           localDetalheBiomassa.setVariancia(rs.getDouble("variancia"));
           localDetalheBiomassa.setDesvioPadrao(rs.getDouble("desviopadrao"));
           localDetalheBiomassa.setVarianciaMedia(rs.getDouble("varianciamedia"));
           localDetalheBiomassa.setErroPadrao(rs.getDouble("erropadrao"));
           localDetalheBiomassa.setCoeficienteVariacao(rs.getDouble("coeficientevariacao"));
           localDetalheBiomassa.setErroAbsoluto(rs.getDouble("erroabsoluto"));
           localDetalheBiomassa.setErroRelativo(rs.getDouble("errorelativo"));
           localDetalheBiomassa.setIntervaloConfiancaMinParcela(rs.getDouble("intervaloconfiancaminparcela"));
           localDetalheBiomassa.setIntervaloConfiancaMaxParcela(rs.getDouble("intervaloconfiancamaxparcela"));
           locaisDetalheBiomassa.add(localDetalheBiomassa);
        }
        rs.close();
        p.close();
        return locaisDetalheBiomassa;
    }
    
}

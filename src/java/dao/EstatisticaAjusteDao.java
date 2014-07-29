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
import model.EstatisticaAjuste;

/**
 *
 * @author Jaime
 */
public class EstatisticaAjusteDao extends MainDao{
        
    public EstatisticaAjusteDao() {
        super();
    }

    
    public void cadastrar(EstatisticaAjuste estatisticaAjuste) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO estatisticaAjuste (idlocal, "
                    +                                                          "idvariavelinteresse,"
                    +                                                          "idmetodocalculo,"                    
                    +                                                          "r2,"
                    +                                                          "r2ajust,"
                    +                                                          "syx,"
                    +                                                          "syxperc,"
                    +                                                          "aic,"
                    +                                                          "bic,"
                    +                                                          "willmott,"
                    +                                                          "ia,"
                    +                                                          "somaquadradoresiduo,"                    
                    +                                                          "somaquadradoregressao,"                    
                    +                                                          "somaquadradototais"                                        
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            p.setInt   (1,  estatisticaAjuste.getIdLocal());
            p.setInt   (2,  estatisticaAjuste.getIdVariavelInteresse());            
            p.setInt   (3,  estatisticaAjuste.getIdMetodoCalculo());                        
            p.setDouble(4,  estatisticaAjuste.getR2());
            p.setDouble(5,  estatisticaAjuste.getR2Ajust());
            p.setDouble(6,  estatisticaAjuste.getSyx());
            p.setDouble(7,  estatisticaAjuste.getSyxPerc());
            p.setDouble(8,  estatisticaAjuste.getAic());
            p.setDouble(9,  estatisticaAjuste.getBic());
            p.setDouble(10, estatisticaAjuste.getWillmott());
            p.setDouble(11, estatisticaAjuste.getIa());            
            p.setDouble(12, estatisticaAjuste.getSomaQuadradoResiduo());                        
            p.setDouble(13, estatisticaAjuste.getSomaQuadradoRegressao());                        
            p.setDouble(14, estatisticaAjuste.getSomaQuadradoTotais());                                    
            
            p.executeUpdate();
            p.close();
    }
    
    public void deletar(EstatisticaAjuste estatisticaAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from estatisticaAjuste where id = ?");
            p.setInt(1, estatisticaAjuste.getId());
            p.executeUpdate();
            p.close();
        
    }
        
    
    public void deletarEstatisticaAjusteLocal(EstatisticaAjuste estatisticaAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from estatisticaAjuste "
                    +                                       "where idlocal = ? " 
                    +                                       "and   idvariavelinteresse = ? "
                    +                                       "and   idmetodocalculo = ?");
            p.setInt(1, estatisticaAjuste.idLocal);
            p.setInt(2, estatisticaAjuste.idVariavelInteresse);            
            p.setInt(3, estatisticaAjuste.idMetodoCalculo);                        
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(EstatisticaAjuste estatisticaAjuste) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE estatisticaAjuste SET idlocal = ?, "
                    +                                                          "idvariavelinteresse = ?,"
                    +                                                          "idmetodocalculo = ?,"                
                    +                                                          "r2 = ?,"
                    +                                                          "r2ajust = ?,"
                    +                                                          "syx = ?,"
                    +                                                          "syxperc = ?,"
                    +                                                          "aic = ?,"
                    +                                                          "bic = ?,"
                    +                                                          "willmott = ?,"
                    +                                                          "ia = ?,"
                    +                                                          "somaquadradoresiduo = ?,"                    
                    +                                                          "somaquadradoregressao = ?,"                    
                    +                                                          "somaquadradototais = ?"                                        
                    +                                                          " WHERE id = ?");
        p.setInt   (1,  estatisticaAjuste.getIdLocal());
        p.setInt   (2,  estatisticaAjuste.getIdVariavelInteresse());
        p.setInt   (3,  estatisticaAjuste.getIdMetodoCalculo());            
        p.setDouble(4,  estatisticaAjuste.getR2());
        p.setDouble(5,  estatisticaAjuste.getR2Ajust());
        p.setDouble(6,  estatisticaAjuste.getSyx());
        p.setDouble(7,  estatisticaAjuste.getSyxPerc());
        p.setDouble(8,  estatisticaAjuste.getAic());
        p.setDouble(9, estatisticaAjuste.getBic());
        p.setDouble(10, estatisticaAjuste.getWillmott());
        p.setDouble(11,  estatisticaAjuste.getIa());        
        p.setDouble(12, estatisticaAjuste.getSomaQuadradoResiduo());                        
        p.setDouble(13, estatisticaAjuste.getSomaQuadradoRegressao());                        
        p.setDouble(14, estatisticaAjuste.getSomaQuadradoTotais());                                    
        
        p.setInt(15, estatisticaAjuste.getId());
        p.executeUpdate();
        p.close();
    }
   
   public EstatisticaAjuste getEstatisticaAjuste(String id) throws SQLException
   {
        ArrayList<EstatisticaAjuste> estatisticaAjustes = new ArrayList<EstatisticaAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatisticaAjuste where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           EstatisticaAjuste estatisticaAjuste = new EstatisticaAjuste();
           estatisticaAjuste.setId(rs.getInt("id"));
           estatisticaAjuste.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatisticaAjuste.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));           
           estatisticaAjuste.setIdLocal(rs.getInt("idlocal"));
           estatisticaAjuste.setR2(rs.getDouble("r2"));
           estatisticaAjuste.setR2Ajust(rs.getDouble("r2ajust"));
           estatisticaAjuste.setSyx(rs.getDouble("syx"));
           estatisticaAjuste.setSyxPerc(rs.getDouble("syxperc"));
           estatisticaAjuste.setAic(rs.getDouble("aic"));
           estatisticaAjuste.setBic(rs.getDouble("bic"));
           estatisticaAjuste.setWillmott(rs.getDouble("willmott"));
           estatisticaAjuste.setIa(rs.getDouble("ia"));
           estatisticaAjuste.setSomaQuadradoResiduo(rs.getDouble("somaquadradoresiduo"));
           estatisticaAjuste.setSomaQuadradoRegressao(rs.getDouble("somaquadradoregressao"));
           estatisticaAjuste.setSomaQuadradoTotais(rs.getDouble("somaquadradototais"));           
           estatisticaAjustes.add(estatisticaAjuste);
        }
        rs.close();
        p.close();
        return estatisticaAjustes.get(0);
    }
   
    public EstatisticaAjuste getEstatisticaAjuste(int idLocal,int idVariavelInteresse, int idMetodoCalculo) throws SQLException
    {
        ArrayList<EstatisticaAjuste> estatisticaAjustes = new ArrayList<EstatisticaAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * "
                +                                       "FROM estatisticaAjuste "
                +                                       "WHERE idlocal = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ? ");
        p.setInt(1, idLocal);
        p.setInt(2, idVariavelInteresse);
        p.setInt(3, idMetodoCalculo);

        ResultSet rs = p.executeQuery();
        while(rs.next()){
           EstatisticaAjuste estatisticaAjuste = new EstatisticaAjuste();
           estatisticaAjuste.setId(rs.getInt("id"));
           estatisticaAjuste.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatisticaAjuste.setIdLocal(rs.getInt("idlocal"));
           estatisticaAjuste.setR2(rs.getDouble("r2"));
           estatisticaAjuste.setR2Ajust(rs.getDouble("r2ajust"));
           estatisticaAjuste.setSyx(rs.getDouble("syx"));
           estatisticaAjuste.setSyxPerc(rs.getDouble("syxperc"));
           estatisticaAjuste.setAic(rs.getDouble("aic"));
           estatisticaAjuste.setBic(rs.getDouble("bic"));
           estatisticaAjuste.setWillmott(rs.getDouble("willmott"));
           estatisticaAjuste.setIa(rs.getDouble("ia"));
           estatisticaAjuste.setSomaQuadradoResiduo(rs.getDouble("somaquadradoresiduo"));
           estatisticaAjuste.setSomaQuadradoRegressao(rs.getDouble("somaquadradoregressao"));
           estatisticaAjuste.setSomaQuadradoTotais(rs.getDouble("somaquadradototais"));           
           estatisticaAjustes.add(estatisticaAjuste);
        }
        rs.close();
        p.close();
        return estatisticaAjustes.get(0);
   }
   public ArrayList<EstatisticaAjuste> listarEstatisticaAjustes() throws Exception{
       
        ArrayList<EstatisticaAjuste> estatisticaAjustes = new ArrayList<EstatisticaAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM estatisticaAjuste");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           EstatisticaAjuste estatisticaAjuste = new EstatisticaAjuste();
           estatisticaAjuste.setId(rs.getInt("id"));
           estatisticaAjuste.setIdLocal(rs.getInt("idlocal"));
           estatisticaAjuste.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           estatisticaAjuste.setR2(rs.getDouble("r2"));
           estatisticaAjuste.setR2Ajust(rs.getDouble("r2ajust"));
           estatisticaAjuste.setSyx(rs.getDouble("syx"));
           estatisticaAjuste.setSyxPerc(rs.getDouble("syxperc"));
           estatisticaAjuste.setAic(rs.getDouble("aic"));
           estatisticaAjuste.setBic(rs.getDouble("bic"));
           estatisticaAjuste.setWillmott(rs.getDouble("willmott"));
           estatisticaAjuste.setIa(rs.getDouble("ia"));
           estatisticaAjuste.setSomaQuadradoResiduo(rs.getDouble("somaquadradoresiduo"));
           estatisticaAjuste.setSomaQuadradoRegressao(rs.getDouble("somaquadradoregressao"));
           estatisticaAjuste.setSomaQuadradoTotais(rs.getDouble("somaquadradototais"));           
           estatisticaAjustes.add(estatisticaAjuste);
        }
        rs.close();
        p.close();
        return estatisticaAjustes;
    }
    
}

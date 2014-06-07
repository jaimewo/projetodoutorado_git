/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.ArvoreAjuste;
import model.Equacao;
import model.Local;
import model.Variavel;
import model.VariavelArvoreAjuste;

/**
 *
 * @author jaime
 */
public class ArvoreAjusteDao extends MainDao {



    
    
    public ArvoreAjusteDao()
    {
      super();
    }
     
    public void cadastrar(ArvoreAjuste arvoreAjuste) throws SQLException
    {

        String sql = "INSERT INTO arvoreajuste(idlocal,"
                    +                                                          "numarvoreajuste,"
                    +                                                          "qtdebiomassaobs,"
                    +                                                          "qtdebiomassaest,"
                    +                                                          "qtdecarbonoobs,"
                    +                                                          "qtdecarbonoest,"
                    +                                                          "qtdevolumeobs,"
                    +                                                          "qtdevolumeest"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?) RETURNING arvoreajuste.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt(1, arvoreAjuste.getIdLocal());
        p.setInt(2, arvoreAjuste.getNumArvoreAjuste());
        p.setDouble(3, arvoreAjuste.getQtdeBiomassaObs());
        p.setDouble(4, arvoreAjuste.getQtdeBiomassaEst());
        p.setDouble(5, arvoreAjuste.getQtdeCarbonoObs());
        p.setDouble(6, arvoreAjuste.getQtdeCarbonoEst());
        p.setDouble(7, arvoreAjuste.getQtdeVolumeObs());
        p.setDouble(8, arvoreAjuste.getQtdeVolumeEst());
        int idArvoreAjuste = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idArvoreAjuste = rs.getInt(1);
        }
            
        if (arvoreAjuste.variaveisArvoreAjuste.size()>0) {
            for (VariavelArvoreAjuste variavelArvoreAjuste: arvoreAjuste.variaveisArvoreAjuste) {
                variavelArvoreAjuste.setIdArvoreAjuste(idArvoreAjuste);
                VariavelArvoreAjusteDao variavelarvoreAjusteDao = new VariavelArvoreAjusteDao();
                variavelarvoreAjusteDao.cadastrar(variavelArvoreAjuste);
            }
        }
            
        p.close();
            
            
            
    }
    
    
    public void deletar(ArvoreAjuste arvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvoreAjuste where id = ?");
            p.setInt(1, arvoreAjuste.getId());
            p.executeUpdate();
            p.close();
        
    }
    public void deletarLocal(Local local) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where idlocal = ?");
        
        p.setInt(1, local.getId());
        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           int idArvoreAjuste = rs.getInt("id");
           
           VariavelArvoreAjusteDao variavelArvoreAjusteDao = new VariavelArvoreAjusteDao();
           variavelArvoreAjusteDao.deletarArvoreAjuste(idArvoreAjuste);
        }
        rs.close();
        
        p = this.con.prepareStatement("DELETE from arvoreAjuste where idlocal = ?");
        p.setInt(1, local.getId());
        p.executeUpdate();
        p.close();
    }
    
   public void update(ArvoreAjuste arvoreAjuste) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvoreAjuste SET idlocal = ?,"
                +                                                         " numarvoreajuste = ?,"
                +                                                         " qtdebiomassaobs = ?,"
                +                                                         " qtdebiomassaest = ?,"
                +                                                         " qtdecarbonoobs = ?,"
                +                                                         " qtdecarbonoest = ?,"
                +                                                         " qtdevolumeobs = ?,"
                +                                                         " qtdevolumeest = ?"
                +                                                         " where id = ?");
        p.setInt(1, arvoreAjuste.getIdLocal());
        p.setInt(2, arvoreAjuste.getNumArvoreAjuste());
        p.setDouble(3, arvoreAjuste.getQtdeBiomassaObs());
        p.setDouble(4, arvoreAjuste.getQtdeBiomassaEst());
        p.setDouble(5, arvoreAjuste.getQtdeCarbonoObs());
        p.setDouble(6, arvoreAjuste.getQtdeCarbonoEst());
        p.setDouble(7, arvoreAjuste.getQtdeVolumeObs());
        p.setDouble(8, arvoreAjuste.getQtdeVolumeEst());

        p.setInt(9, arvoreAjuste.getId());
        p.executeUpdate();
        p.close();
    }
   
   public ArvoreAjuste getArvoreAjuste(String id) throws SQLException
   {
        List<ArvoreAjuste> arvoreAjustes = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvoreAjuste(rs.getInt("numarvoreajuste"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvoreAjuste.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvoreAjuste.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvoreAjustes.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        return arvoreAjustes.get(0);
   }
   public ArvoreAjuste getArvoreAjuste(Local local, int numArvore) throws SQLException
   {
        List<ArvoreAjuste> arvoreAjustes = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where idlocal = ? AND numarvoreajuste = ?");
        
        p.setInt(1, local.getId());
        p.setInt(2, numArvore);
        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvoreAjuste(rs.getInt("numarvoreajuste"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvoreAjuste.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvoreAjuste.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvoreAjustes.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        return arvoreAjustes.get(0);
   }
   
   public ArrayList<ArvoreAjuste> listarArvoresAjuste(int idLocal) throws Exception{
        ArrayList<ArvoreAjuste> arvoresAjuste = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste WHERE idlocal = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvoreAjuste(rs.getInt("numarvoreajuste"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvoreAjuste.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvoreAjuste.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvoreAjuste.variaveisArvoreAjuste = arvoreAjuste.getVariaveisArvoreAjuste();
           arvoresAjuste.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        return arvoresAjuste;
    }
}


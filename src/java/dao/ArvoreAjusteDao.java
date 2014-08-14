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
     
    public void cadastrar(ArvoreAjuste arvoreAjuste) throws SQLException, Exception
    {

        String sql = "INSERT INTO arvoreajuste(idlocal,"
                    +                         "numarvoreajuste, "
                    +                         "qtdebiomassaobs,"
                    +                         "qtdecarbonoobs,"
                    +                         "qtdevolumeobs,"                
                    +                         "qtdebiomassaestequacao,"                
                    +                         "qtdebiomassaestdm,"                                
                    +                         "qtdecarbonoestequacao,"                
                    +                         "qtdecarbonoestdm,"                                
                    +                         "qtdevolumeestequacao,"                
                    +                         "qtdevolumeestdm"                                
                    +                         ") VALUES (?,?,?,?,?,?,?,?,?,?,?) RETURNING arvoreajuste.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt(1, arvoreAjuste.getIdLocal());
        p.setInt(2, arvoreAjuste.getNumArvoreAjuste());
        p.setDouble(3,arvoreAjuste.getQtdeBiomassaObs());
        p.setDouble(4,arvoreAjuste.getQtdeCarbonoObs());        
        p.setDouble(5,arvoreAjuste.getQtdeVolumeObs());        
        p.setDouble(6,arvoreAjuste.getQtdeBiomassaEstEquacao());        
        p.setDouble(7,arvoreAjuste.getQtdeBiomassaEstDm());                
        p.setDouble(8,arvoreAjuste.getQtdeCarbonoEstEquacao());        
        p.setDouble(9,arvoreAjuste.getQtdeCarbonoEstDm());                
        p.setDouble(10,arvoreAjuste.getQtdeVolumeEstEquacao());        
        p.setDouble(11,arvoreAjuste.getQtdeVolumeEstDm());                
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
        super.con.close();
            
    }
    
    public void deletar(ArvoreAjuste arvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvoreAjuste where id = ?");
            p.setInt(1, arvoreAjuste.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
        
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
        super.con.close();
    }
    
   public void updateQtdeEst(ArvoreAjuste arvoreAjuste,int idVariavelInteresse, int idMetodoCalculo) throws Exception 
   {

        String sql = "";
        double qtdeEst = 0.0;
       
        if (idMetodoCalculo==1) { //Equacao
            switch (idVariavelInteresse) {
                case 1: //Biomassa
                    sql = "UPDATE arvoreAjuste SET qtdebiomassaestequacao = ? where id = ?";
                    qtdeEst = arvoreAjuste.getQtdeBiomassaEstEquacao();
                    break;
                case 2: //Carbono
                    sql = "UPDATE arvoreAjuste SET qtdecarbonoestequacao = ? where id = ?";
                    qtdeEst = arvoreAjuste.getQtdeCarbonoEstEquacao();
                    break;
                case 3:
                    sql = "UPDATE arvoreAjuste SET qtdevolumeestequacao = ? where id = ?";
                    qtdeEst = arvoreAjuste.getQtdeVolumeEstEquacao();
                    break;
                }                            
        } else {// DM
            switch (idVariavelInteresse) {
                case 1:
                    sql = "UPDATE arvoreAjuste SET qtdebiomassaestdm = ? where id = ?";
                    qtdeEst = arvoreAjuste.getQtdeBiomassaEstDm();
                    break;
                case 2:
                    sql = "UPDATE arvoreAjuste SET qtdecarbonoestdm = ? where id = ?";
                    qtdeEst = arvoreAjuste.getQtdeCarbonoEstDm();
                    break;
                case 3:
                    sql = "UPDATE arvoreAjuste SET qtdevolumeestdm = ? where id = ?";
                    qtdeEst = arvoreAjuste.getQtdeVolumeEstDm();
                    break;
            }                            
        }       
       
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setDouble(1, qtdeEst);
        p.setInt(2, arvoreAjuste.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
    }

   public ArvoreAjuste getArvoreAjuste(String id) throws SQLException
   {
        ArrayList<ArvoreAjuste> arvoreAjustes = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvoreAjuste(rs.getInt("numarvoreajuste"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));           
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));                      
           arvoreAjuste.setQtdeBiomassaEstEquacao(rs.getDouble("qtdebiomassaestequacao"));           
           arvoreAjuste.setQtdeBiomassaEstDm(rs.getDouble("qtdebiomassaestdm"));                      
           arvoreAjuste.setQtdeCarbonoEstEquacao(rs.getDouble("qtdecarbonoestequacao"));           
           arvoreAjuste.setQtdeCarbonoEstDm(rs.getDouble("qtdecarbonoestdm"));                      
           arvoreAjuste.setQtdeVolumeEstEquacao(rs.getDouble("qtdevolumeestequacao"));           
           arvoreAjuste.setQtdeVolumeEstDm(rs.getDouble("qtdevolumeestdm"));                      
           arvoreAjustes.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        super.con.close();
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
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));           
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));                      
           arvoreAjuste.setQtdeBiomassaEstEquacao(rs.getDouble("qtdebiomassaestequacao"));           
           arvoreAjuste.setQtdeBiomassaEstDm(rs.getDouble("qtdebiomassaestdm"));                      
           arvoreAjuste.setQtdeCarbonoEstEquacao(rs.getDouble("qtdecarbonoestequacao"));           
           arvoreAjuste.setQtdeCarbonoEstDm(rs.getDouble("qtdecarbonoestdm"));                      
           arvoreAjuste.setQtdeVolumeEstEquacao(rs.getDouble("qtdevolumeestequacao"));           
           arvoreAjuste.setQtdeVolumeEstDm(rs.getDouble("qtdevolumeestdm"));                      
           arvoreAjustes.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        super.con.close();
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
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));           
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));                      
           arvoreAjuste.setQtdeBiomassaEstEquacao(rs.getDouble("qtdebiomassaestequacao"));           
           arvoreAjuste.setQtdeBiomassaEstDm(rs.getDouble("qtdebiomassaestdm"));                      
           arvoreAjuste.setQtdeCarbonoEstEquacao(rs.getDouble("qtdecarbonoestequacao"));           
           arvoreAjuste.setQtdeCarbonoEstDm(rs.getDouble("qtdecarbonoestdm"));                      
           arvoreAjuste.setQtdeVolumeEstEquacao(rs.getDouble("qtdevolumeestequacao"));           
           arvoreAjuste.setQtdeVolumeEstDm(rs.getDouble("qtdevolumeestdm"));                      
           arvoreAjuste.variaveisArvoreAjuste = arvoreAjuste.getVariaveisArvoreAjuste();

           arvoresAjuste.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvoresAjuste;
    }
}


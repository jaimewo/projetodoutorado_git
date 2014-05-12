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
import model.ArvoreAjuste;

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
            PreparedStatement p = this.con.prepareStatement("INSERT INTO arvoreAjuste(idlocal,"
                    +                                                          "numarvoreAjuste,"
                    +                                                          "qtdebiomassaobs,"
                    +                                                          "qtdebiomassaest,"
                    +                                                          "qtdecarbonoobs,"
                    +                                                          "qtdecarbonoest,"
                    +                                                          "qtdevolumeobs,"
                    +                                                          "qtdevolumeest"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?)");
            p.setInt(1, arvoreAjuste.getIdLocal());
            p.setInt(2, arvoreAjuste.getNumArvore());
            p.setDouble(3, arvoreAjuste.getQtdeBiomassaObs());
            p.setDouble(4, arvoreAjuste.getQtdeBiomassaEst());
            p.setDouble(5, arvoreAjuste.getQtdeCarbonoObs());
            p.setDouble(6, arvoreAjuste.getQtdeCarbonoEst());
            p.setDouble(7, arvoreAjuste.getQtdeVolumeObs());
            p.setDouble(8, arvoreAjuste.getQtdeVolumeEst());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(ArvoreAjuste arvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvoreAjuste where id = ?");
            p.setInt(1, arvoreAjuste.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(ArvoreAjuste arvoreAjuste) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvoreAjuste SET idlocal = ?,"
                +                                                         " numarvore = ?,"
                +                                                         " qtdebiomassaobs = ?,"
                +                                                         " qtdebiomassaest = ?,"
                +                                                         " qtdecarbonoobs = ?,"
                +                                                         " qtdecarbonoest = ?,"
                +                                                         " qtdevolumeobs = ?,"
                +                                                         " qtdevolumeest = ?"
                +                                                         " where id = ?");
        p.setInt(1, arvoreAjuste.getIdLocal());
        p.setInt(2, arvoreAjuste.getNumArvore());
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
   
   public ArvoreAjuste getArvoreAjuste(String idArvore) throws SQLException
   {
        List<ArvoreAjuste> arvoreAjustes = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvore(rs.getInt("numarvore"));
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
           arvoreAjuste.setNumArvore(rs.getInt("numarvore"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvoreAjuste.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvoreAjuste.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvoresAjuste.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        return arvoresAjuste;
    }

}

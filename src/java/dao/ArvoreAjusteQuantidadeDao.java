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
import model.ArvoreAjusteQuantidade;

/**
 *
 * @author jaime
 */
public class ArvoreAjusteQuantidadeDao extends MainDao {
    
    
    public ArvoreAjusteQuantidadeDao()
    {
     super();
    }
     
    public void cadastrar(ArvoreAjusteQuantidade arvoreAjusteQuantidade) throws SQLException, Exception
    {

        String sql = "INSERT INTO arvoreajustequantidade(idvariavelinteresse,"
                    +                             "idmetodocalculo,"
                    +                             "qtdeobs,"
                    +                             "qtdeest,"
                    +                             "idarvoreajuste"                
                    +                             ") VALUES (?,?,?,?,?) RETURNING arvoreajustequantidade.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt(1, arvoreAjusteQuantidade.getIdVariavelInteresse());
        p.setInt(2, arvoreAjusteQuantidade.getIdMetodoCalculo());
        p.setDouble(3, arvoreAjusteQuantidade.getQtdeObs());
        p.setDouble(4, arvoreAjusteQuantidade.getQtdeEst());
        p.setInt(5, arvoreAjusteQuantidade.getIdArvoreAjuste());        
        int idArvoreAjuste = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idArvoreAjuste = rs.getInt(1);
        }
            
        p.close();
        super.con.close();
    }
    
    
    public void deletar(ArvoreAjusteQuantidade arvoreAjusteQuantidade) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvoreajustequantidade where id = ?");
            p.setInt(1, arvoreAjusteQuantidade.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
        
    }
    public void deletarArvoreAjuste(int idArvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvoreajustequantidade where idarvoreajuste = ?");
            p.setInt(1, idArvoreAjuste);
            p.executeUpdate();
            p.close();
            super.con.close();
        
    }    
    
   public void update(ArvoreAjusteQuantidade arvoreAjusteQuantidade) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvoreajustequantidade SET idvariavelinteresse = ?,"
                +                                                         " idmetodocalculo = ?,"
                +                                                         " qtdeobs = ?,"
                +                                                         " qtdeest = ?,"
                +                                                         " idarvoreajuste = ?"                
                +                                                         " where id = ?");
        p.setInt(1, arvoreAjusteQuantidade.getIdVariavelInteresse());
        p.setInt(2, arvoreAjusteQuantidade.getIdMetodoCalculo());
        p.setDouble(3, arvoreAjusteQuantidade.getQtdeObs());
        p.setDouble(4, arvoreAjusteQuantidade.getQtdeEst());
        p.setInt(5, arvoreAjusteQuantidade.getIdArvoreAjuste());
        
        p.setInt(6, arvoreAjusteQuantidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
   
    public ArvoreAjusteQuantidade getArvoreAjusteQuantidade(String idArvoreAjuste) throws SQLException
    {
        ArrayList<ArvoreAjusteQuantidade> arvoresAjusteQuantidade = new ArrayList<ArvoreAjusteQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajustequantidade where id = ?");
        p.setInt(1, Integer.parseInt(idArvoreAjuste));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjusteQuantidade arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();
           arvoreAjusteQuantidade.setId(rs.getInt("id"));
           arvoreAjusteQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           arvoreAjusteQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           arvoreAjusteQuantidade.setQtdeObs(rs.getDouble("qtdeobs"));
           arvoreAjusteQuantidade.setQtdeEst(rs.getDouble("qtdeest"));
           arvoreAjusteQuantidade.setIdArvoreAjuste(rs.getInt("idarvoreajuste"));           
           arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvoresAjusteQuantidade.get(0);
   }
   
   public ArvoreAjusteQuantidade getArvoreAjusteQuantidade(int idArvoreAjuste, int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        ArrayList<ArvoreAjusteQuantidade> arvoresAjusteQuantidade = new ArrayList<ArvoreAjusteQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajustequantidade "
                +                                       "WHERE idarvoreajuste = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ");
        p.setInt(1, idArvoreAjuste);
        p.setInt(2, idVariavelInteresse);
        p.setInt(3, idMetodoCalculo);        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjusteQuantidade arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();
           arvoreAjusteQuantidade.setId(rs.getInt("id"));
           arvoreAjusteQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           arvoreAjusteQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           arvoreAjusteQuantidade.setQtdeObs(rs.getDouble("qtdeobs"));
           arvoreAjusteQuantidade.setQtdeEst(rs.getDouble("qtdeest"));
           arvoreAjusteQuantidade.setIdArvoreAjuste(rs.getInt("idarvoreajuste"));           
           arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvoresAjusteQuantidade.get(0);
   }
   
   
   public double getQtdeObs(int idArvoreAjuste, int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        double qtdeObs = 0.0;
        
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajustequantidade "
                +                                       "WHERE idarvoreajuste = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ? ");
        p.setInt(1, idArvoreAjuste);
        p.setInt(2, idVariavelInteresse);
        p.setInt(3, idMetodoCalculo);        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           qtdeObs = rs.getDouble("qtdeobs");
        }
        rs.close();
        p.close();
        super.con.close();
        return qtdeObs;
   }
   
   
   public double getQtdeEst(int idArvoreAjuste, int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        double qtdeEst = 0.0;
        
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajustequantidade "
                +                                       "WHERE idarvoreajuste = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ");
        p.setInt(1, idArvoreAjuste);
        p.setInt(2, idVariavelInteresse);
        p.setInt(3, idMetodoCalculo);        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           qtdeEst = rs.getDouble("qtdeest");
        }
        rs.close();
        p.close();
        super.con.close();
        return qtdeEst;
   }

   
   public ArrayList<ArvoreAjusteQuantidade> listarArvoresAjusteQuantidade(int idArvoreAjuste, int idVariavelInteresse, int idMetodoCalculo) throws Exception{
       
        ArrayList<ArvoreAjusteQuantidade> arvoresAjusteQuantidade = new ArrayList<ArvoreAjusteQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajustequantidade "
                +                                       "WHERE idarvoreajuste = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ?");

        p.setInt(1, idArvoreAjuste);
        p.setInt(2, idVariavelInteresse);
        p.setInt(3, idMetodoCalculo);

        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjusteQuantidade arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();
           arvoreAjusteQuantidade.setId(rs.getInt("id"));
           arvoreAjusteQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           arvoreAjusteQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           arvoreAjusteQuantidade.setQtdeObs(rs.getDouble("qtdeobs"));
           arvoreAjusteQuantidade.setQtdeEst(rs.getDouble("qtdeest"));
           arvoreAjusteQuantidade.setIdArvoreAjuste(rs.getInt("idarvoreajuste"));           
           arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvoresAjusteQuantidade;
    }
    
    public void updateQtdeEst(ArvoreAjusteQuantidade arvoreAjusteQuantidade) throws SQLException {
        PreparedStatement p = this.con.prepareStatement(" UPDATE arvoreajustequantidade SET qtdeest = ?"
                +                                       " WHERE idvariavelinteresse = ? "
                +                                       " AND   idmetodocalculo = ?"
                +                                       " AND   idarvoreajuste = ?");        
        p.setDouble(1, arvoreAjusteQuantidade.getQtdeEst());
        p.setInt   (2, arvoreAjusteQuantidade.getIdVariavelInteresse());
        p.setInt   (3, arvoreAjusteQuantidade.getIdMetodoCalculo());
        p.setInt   (4, arvoreAjusteQuantidade.getIdArvoreAjuste());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
    
}
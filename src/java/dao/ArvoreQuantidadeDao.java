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
import model.ArvoreQuantidade;

/**
 *
 * @author jaime
 */
public class ArvoreQuantidadeDao extends MainDao {
    
    
    public ArvoreQuantidadeDao()
    {
     super();
    }
     
    public void cadastrar(ArvoreQuantidade arvoreQuantidade) throws SQLException, Exception
    {

        String sql = "INSERT INTO arvorequantidade(idvariavelinteresse,"
                    +                             "idmetodocalculo,"
                    +                             "qtdeest,"
                    +                             "idarvore"                
                    +                             ") VALUES (?,?,?,?) RETURNING arvorequantidade.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt(1, arvoreQuantidade.getIdVariavelInteresse());
        p.setInt(2, arvoreQuantidade.getIdMetodoCalculo());
        p.setDouble(3, arvoreQuantidade.getQtdeEst());
        p.setInt(4, arvoreQuantidade.getIdArvore());        
        int idArvore = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idArvore = rs.getInt(1);
        }
            
        p.close();
        super.con.close();
    }
    
    
    public void deletar(ArvoreQuantidade arvoreQuantidade) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvorequantidade where id = ?");
            p.setInt(1, arvoreQuantidade.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
        
    }
   public void update(ArvoreQuantidade arvoreQuantidade) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvorequantidade SET idvariavelinteresse = ?,"
                +                                                         " idmetodocalculo = ?,"
                +                                                         " qtdeest = ?,"
                +                                                         " idarvore = ?"                
                +                                                         " where id = ?");
        p.setInt(1, arvoreQuantidade.getIdVariavelInteresse());
        p.setInt(2, arvoreQuantidade.getIdMetodoCalculo());
        p.setDouble(3, arvoreQuantidade.getQtdeEst());
        p.setInt(4, arvoreQuantidade.getIdArvore());
        
        p.setInt(5, arvoreQuantidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
   
   public double getQtdeEst(int idArvore, int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        double qtdeEst = 0.0;
        
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreaquantidade "
                +                                       "WHERE idarvore = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ");
        p.setInt(1, idArvore);
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
   
    public ArvoreQuantidade getArvoreQuantidade(String idArvore) throws SQLException
    {
        ArrayList<ArvoreQuantidade> arvoresQuantidade = new ArrayList<ArvoreQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvorequantidade where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreQuantidade arvoreQuantidade = new ArvoreQuantidade();
           arvoreQuantidade.setId(rs.getInt("id"));
           arvoreQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           arvoreQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           arvoreQuantidade.setQtdeEst(rs.getDouble("qtdeest"));
           arvoreQuantidade.setIdArvore(rs.getInt("idarvore"));           
           arvoresQuantidade.add(arvoreQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvoresQuantidade.get(0);
   }
   
   public ArvoreQuantidade getArvoreQuantidade(int idArvore, int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        ArrayList<ArvoreQuantidade> arvoresQuantidade = new ArrayList<ArvoreQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvorequantidade "
                +                                       "WHERE idarvore = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ");
        p.setInt(1, idArvore);
        p.setInt(2, idVariavelInteresse);
        p.setInt(3, idMetodoCalculo);        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreQuantidade arvoreQuantidade = new ArvoreQuantidade();
           arvoreQuantidade.setId(rs.getInt("id"));
           arvoreQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           arvoreQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           arvoreQuantidade.setQtdeEst(rs.getDouble("qtdeest"));
           arvoreQuantidade.setIdArvore(rs.getInt("idarvore"));           
           arvoresQuantidade.add(arvoreQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvoresQuantidade.get(0);
   }
   

   
   public ArrayList<ArvoreQuantidade> listarArvoresQuantidade(int idArvore) throws Exception{
       
        ArrayList<ArvoreQuantidade> arvoresQuantidade = new ArrayList<ArvoreQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvorequantidade where idarvore = ?");
        p.setInt(1, idArvore);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreQuantidade arvoreQuantidade = new ArvoreQuantidade();
           arvoreQuantidade.setId(rs.getInt("id"));
           arvoreQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           arvoreQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           arvoreQuantidade.setQtdeEst(rs.getDouble("qtdeest"));
           arvoreQuantidade.setIdArvore(rs.getInt("idarvore"));           
           arvoresQuantidade.add(arvoreQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvoresQuantidade;
    }
    
    public void updateQtdeEst(ArvoreQuantidade arvoreQuantidade) throws SQLException {
        PreparedStatement p = this.con.prepareStatement(" UPDATE arvorequantidade SET qtdeest = ?"
                +                                       " WHERE idvariavelinteresse = ? "
                +                                       " AND   idmetodocalculo = ?"
                +                                       " AND   idarvore = ?");        
        p.setDouble(1, arvoreQuantidade.getQtdeEst());
        p.setInt   (2, arvoreQuantidade.getIdVariavelInteresse());
        p.setInt   (3, arvoreQuantidade.getIdMetodoCalculo());
        p.setInt   (4, arvoreQuantidade.getIdArvore());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
    
}
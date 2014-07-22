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
import model.LocalQuantidade;


/**
 *
 * @author Jaime
 */
public class LocalQuantidadeDao extends MainDao {
    
    
    public LocalQuantidadeDao()
    {
      super();
    }
    
    public void cadastrar(LocalQuantidade localQuantidade) throws SQLException, Exception
                        
    {
        String sql = "INSERT INTO localquantidade (idvariavelinteresse, "
                +                                 "idmetodocalculo,"
                +                                 "qtde,"
                +                                 "idlocal"
                +                                 ") VALUES (?,?,?,?) RETURNING localQuantidade.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt   (1, localQuantidade.getIdVariavelInteresse());
        p.setInt   (2, localQuantidade.getIdMetodoCalculo());
        p.setDouble(3, localQuantidade.getQtde());
        p.setInt   (4, localQuantidade.getIdLocal());
        int idLocalQuantidade = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
          idLocalQuantidade = rs.getInt(1);
        }
        p.close();          
        super.con.close();
    }
    
    
    public void deletar(LocalQuantidade localQuantidade) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from localquantidade where id = ?");
        p.setInt(1, localQuantidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
   public void update(LocalQuantidade localQuantidade) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE localquantidade SET idvariavelinteresse = ?, "
                +                                                                  "idmetodocalculo = ?,"
                +                                                                  "qtde = ?,"
                +                                                                  "idlocal = ?"
                +                                                                  " WHERE id = ?");
        p.setInt   (1, localQuantidade.getIdVariavelInteresse());
        p.setInt   (2, localQuantidade.getIdMetodoCalculo());
        p.setDouble(3, localQuantidade.getQtde());
        p.setInt   (4, localQuantidade.getIdLocal());
        
        p.setInt(5, localQuantidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public double getQtde(int idLocal, int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        double qtde = 0.0;
        
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localquantidade "
                +                                       "WHERE idparcela = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ");
        p.setInt(1, idLocal);
        p.setInt(2, idVariavelInteresse);
        p.setInt(3, idMetodoCalculo);        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           qtde = rs.getDouble("qtde");
        }
        rs.close();
        p.close();
        super.con.close();        
        return qtde;
   }
   
   public LocalQuantidade getLocalQuantidade(int id) throws SQLException
   {
        ArrayList<LocalQuantidade> locaisQuantidade = new ArrayList<LocalQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localquantidade where id = ?");
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalQuantidade localQuantidade = new LocalQuantidade();
           localQuantidade.setId(rs.getInt("id"));
           localQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           localQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           localQuantidade.setQtde(rs.getDouble("qtde"));
           localQuantidade.setIdLocal(rs.getInt("idlocal"));
           
           locaisQuantidade.add(localQuantidade);
           
        }
        rs.close();
        p.close();
        super.con.close();        
        return locaisQuantidade.get(0);
   }
   
   public List<LocalQuantidade> listarLocais() throws Exception{
       
        ArrayList<LocalQuantidade> locaisQuantidade = new ArrayList<LocalQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM localquantidade");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           LocalQuantidade localQuantidade = new LocalQuantidade();
           localQuantidade.setId(rs.getInt("id"));
           localQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           localQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           localQuantidade.setQtde(rs.getDouble("qtde"));
           localQuantidade.setIdLocal(rs.getInt("idlocal"));
           
           locaisQuantidade.add(localQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();        
        return locaisQuantidade;
    }

    public void updateQtde(LocalQuantidade localQuantidade) throws SQLException {
        
        PreparedStatement p = this.con.prepareStatement(" UPDATE localquantidade SET qtde = ?"
                +                                       " WHERE idvariavelinteresse = ? "
                +                                       " AND   idmetodocalculo = ?"
                +                                       " AND   idlocal = ?");        
        p.setDouble(1, localQuantidade.getQtde());
        p.setInt   (2, localQuantidade.getIdVariavelInteresse());
        p.setInt   (3, localQuantidade.getIdMetodoCalculo());
        p.setInt   (4, localQuantidade.getIdLocal());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
}

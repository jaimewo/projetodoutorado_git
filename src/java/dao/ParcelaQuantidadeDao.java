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
import model.Arvore;
import model.Local;
import model.ParcelaQuantidade;

/**
 *
 * @author Jaime
 */
public class ParcelaQuantidadeDao extends MainDao {
    
    public ParcelaQuantidadeDao() {
        super();
    }
    
    public void cadastrar(ParcelaQuantidade parcelaQuantidade) throws SQLException, Exception
                        
    {
        String sql = "INSERT INTO parcelaquantidade (idvariavelinteresse, "
                    +                               "idmetodocalculo,"
                    +                               "qtde,"
                    +                               "idparcela"
                    +                               ") VALUES (?,?,?,?) RETURNING parcelaQuantidade.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt   (1, parcelaQuantidade.getIdVariavelInteresse());
        p.setDouble(2, parcelaQuantidade.getIdMetodoCalculo());
        p.setDouble(3, parcelaQuantidade.getQtde());
        p.setInt   (4, parcelaQuantidade.getIdParcela());
        int idParcelaQuantidade = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idParcelaQuantidade = rs.getInt(1);
        }
        p.close();
        super.con.close();        

    }
    
    
    public void deletar(ParcelaQuantidade parcelaQuantidade) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from parcelaquantidade where id = ?");
        p.setInt(1, parcelaQuantidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
    public void deletar(int idParcela) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from parcelaquantidade where idparcela = ?");
        p.setInt(1, idParcela);
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
   public void update(ParcelaQuantidade parcelaQuantidade) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcelaquantidade SET idvariavelinteresse = ?, "
                    +                                                      "idmetodocalculo = ?, "
                    +                                                      "qtde = ?, "
                    +                                                      "idparcela = ? "
                +                                                          " WHERE id = ?");
        p.setInt   (1, parcelaQuantidade.getIdVariavelInteresse());
        p.setDouble(2, parcelaQuantidade.getIdMetodoCalculo());
        p.setDouble(3, parcelaQuantidade.getQtde());
        p.setInt   (4, parcelaQuantidade.getIdParcela());
                
        p.setInt(5, parcelaQuantidade.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
    public void updateQtde(ParcelaQuantidade parcelaQuantidade) throws SQLException {
        PreparedStatement p = this.con.prepareStatement(" UPDATE parcelaquantidade SET qtde = ?"
                +                                       " WHERE idvariavelinteresse = ? "
                +                                       " AND   idmetodocalculo = ?"
                +                                       " AND   idparcela = ?");        
        p.setDouble(1, parcelaQuantidade.getQtde());
        p.setInt   (2, parcelaQuantidade.getIdVariavelInteresse());
        p.setInt   (3, parcelaQuantidade.getIdMetodoCalculo());
        p.setInt   (4, parcelaQuantidade.getIdParcela());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public ParcelaQuantidade getParcelaQuantidade(String id) throws SQLException
   {
        ArrayList<ParcelaQuantidade> parcelaQuantidades = new ArrayList<ParcelaQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcelaquantidade where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ParcelaQuantidade parcelaQuantidade = new ParcelaQuantidade();
           parcelaQuantidade.setId(rs.getInt("id"));
           parcelaQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           parcelaQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           parcelaQuantidade.setQtde(rs.getDouble("qtde"));
           parcelaQuantidade.setIdParcela(rs.getInt("idparcela"));
           parcelaQuantidades.add(parcelaQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return parcelaQuantidades.get(0);
   }
   
   public double getQtde(int idParcela, int idVariavelInteresse, int idMetodoCalculo) throws SQLException
   {
        double qtde = 0.0;
        
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcelaquantidade "
                +                                       "WHERE idparcela = ? "
                +                                       "AND   idvariavelinteresse = ? "
                +                                       "AND   idmetodocalculo = ?");
        p.setInt(1, idParcela);
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
   
   public ParcelaQuantidade getParcelaQuantidade(int idParcela) throws SQLException
   {
        ArrayList<ParcelaQuantidade> parcelasQuantidade = new ArrayList<ParcelaQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcelaquantidade where idparcela = ?");
        p.setInt(1, idParcela);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ParcelaQuantidade parcelaQuantidade = new ParcelaQuantidade();
           parcelaQuantidade.setId(rs.getInt("id"));
           parcelaQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           parcelaQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           parcelaQuantidade.setQtde(rs.getDouble("qtde"));
           parcelaQuantidade.setIdParcela(rs.getInt("idparcela"));
           parcelasQuantidade.add(parcelaQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return parcelasQuantidade.get(0);
   }
   
   public ArrayList<ParcelaQuantidade> listarParcelasQuantidade(int idParcela) throws Exception{
       
        ArrayList<ParcelaQuantidade> parcelasQuantidade = new ArrayList<ParcelaQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcelaQuantidade WHERE idParcela = ?");
           
        p.setInt(1, idParcela);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ParcelaQuantidade parcelaQuantidade = new ParcelaQuantidade();
           parcelaQuantidade.setId(rs.getInt("id"));
           parcelaQuantidade.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           parcelaQuantidade.setIdMetodoCalculo(rs.getInt("idmetodocalculo"));
           parcelaQuantidade.setQtde(rs.getDouble("qtde"));
           parcelaQuantidade.setIdParcela(rs.getInt("idparcela"));
           parcelasQuantidade.add(parcelaQuantidade);
        }
        rs.close();
        p.close();
        super.con.close();
        return parcelasQuantidade;
    }
    
}

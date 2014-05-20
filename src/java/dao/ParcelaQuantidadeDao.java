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
import model.Local;
import model.Parcela;
import model.ParcelaQuantidade;

/**
 *
 * @author Jaime
 */
public class ParcelaQuantidadeDao extends MainDao {
    
    public ParcelaQuantidadeDao() {
        super();
    }
    
    public void cadastrar(ParcelaQuantidade parcelaQuantidade) throws SQLException
                        
    {
        PreparedStatement p = this.con.prepareStatement("INSERT INTO parcelaquantidade (idparcela, "
                    +                                                                  "idvariavelinteresse,"
                    +                                                                  "qtdevariavelinteresse"
                    +                                                                  ") VALUES (?,?,?)");
        p.setInt   (1, parcelaQuantidade.getIdParcela());
        p.setDouble(2, parcelaQuantidade.getIdVariavelInteresse());
        p.setDouble(3, parcelaQuantidade.getQtdeVariavelInteresse());
         
        p.executeUpdate();
        p.close();
    }
    
    
    public void deletar(ParcelaQuantidade parcelaQuantidade) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from parcelaquantidade where id = ?");
        p.setInt(1, parcelaQuantidade.getId());
        p.executeUpdate();
        p.close();
        
    }
    
   public void update(ParcelaQuantidade parcelaQuantidade) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcelaquantidade SET idparcela = ?, "
                    +                                                      "idvariavelinteresse = ?,"
                    +                                                      "qtdevariavelinteresse = ?"
                +                                                          " WHERE id = ?");
        p.setInt   (1, parcelaQuantidade.getIdParcela());
        p.setDouble(2, parcelaQuantidade.getIdVariavelInteresse());
        p.setDouble(3, parcelaQuantidade.getQtdeVariavelInteresse());
        
        p.setInt(4, parcelaQuantidade.getId());
        p.executeUpdate();
        p.close();
    }
   
   public ParcelaQuantidade getParcelaQuantidade(String id) throws SQLException
   {
        List<ParcelaQuantidade> parcelasQuantidade = new ArrayList<ParcelaQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcelaquantidade where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ParcelaQuantidade parcelaQuantidade = new ParcelaQuantidade();
           parcelaQuantidade.setId(rs.getInt("id"));
           parcelaQuantidade.setIdParcela(rs.getInt("idparcela"));
           parcelaQuantidade.setIdVariavelInteresse(rs.getInt("idvariaveiInteresse"));
           parcelaQuantidade.setQtdeVariavelInteresse(rs.getDouble("qtdevariavelinteresse"));
           parcelasQuantidade.add(parcelaQuantidade);
        }
        rs.close();
        p.close();
        return parcelasQuantidade.get(0);
   }
   public ParcelaQuantidade getParcelaQuantidade(int idParcela, int idVariavelInteresse) throws SQLException
   {
        List<ParcelaQuantidade> parcelasQuantidade = new ArrayList<ParcelaQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcelaquantidade where idparcela = ? and idvariavelinteresse = ?");
        p.setInt(1, idParcela);
        p.setInt(2, idVariavelInteresse);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ParcelaQuantidade parcelaQuantidade = new ParcelaQuantidade();
           parcelaQuantidade.setId(rs.getInt("id"));
           parcelaQuantidade.setIdParcela(rs.getInt("idparcela"));
           parcelaQuantidade.setIdVariavelInteresse(rs.getInt("idvariaveiInteresse"));
           parcelaQuantidade.setQtdeVariavelInteresse(rs.getDouble("qtdevariavelinteresse"));
           parcelasQuantidade.add(parcelaQuantidade);
        }
        rs.close();
        p.close();
        return parcelasQuantidade.get(0);
   }
   
   public ArrayList<ParcelaQuantidade> listarParcelasQuantidade(int idParcela) throws Exception{
        ArrayList<ParcelaQuantidade> parcelasQuantidade = new ArrayList<ParcelaQuantidade>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcelaquantidade WHERE idParcela = ?");
           
        p.setInt(1, idParcela);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ParcelaQuantidade parcelaQuantidade = new ParcelaQuantidade();
           parcelaQuantidade.setId(rs.getInt("id"));
           parcelaQuantidade.setIdParcela(rs.getInt("idparcela"));
           parcelaQuantidade.setIdVariavelInteresse(rs.getInt("idvariaveiInteresse"));
           parcelaQuantidade.setQtdeVariavelInteresse(rs.getDouble("qtdevariavelinteresse"));
           parcelasQuantidade.add(parcelaQuantidade);
        }
        rs.close();
        p.close();
        return parcelasQuantidade;
    }

   public void updateQuantidade(ParcelaQuantidade parcelaQuantidade) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcelaquantidade SET qtdevariavelinteresse = ?"
                +                                                          " WHERE idParcela = ?"
                +                                                          " AND idvariavelinteresse = ?");
        p.setDouble(1, parcelaQuantidade.getQtdeVariavelInteresse());
        p.setInt   (2, parcelaQuantidade.getIdParcela());
        p.setDouble(3, parcelaQuantidade.getIdVariavelInteresse());

        p.setInt(4, parcelaQuantidade.getId());
        p.executeUpdate();
        p.close();
    }
    
}

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
import model.Variavel;
import model.VariavelArvore;

/**
 *
 * @author jaime
 */
public class VariavelArvoreDao extends MainDao {
    
    
    public VariavelArvoreDao()
    {
        super();
    }
     
    public void cadastrar(VariavelArvore variavelArvore) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO variavelarvore(idarvore,"
                    +                                                                  "idvariavel,"
                    +                                                                  "valor"
                    +                                                                  ") VALUES (?,?,?)");
            p.setInt(1, variavelArvore.getIdArvore());
            p.setInt(2, variavelArvore.getIdVariavel());
            p.setDouble(3, variavelArvore.getValor());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(VariavelArvore variavelArvore) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from variavelarvore where id = ?");
            p.setInt(1, variavelArvore.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(VariavelArvore variavelArvore) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE variavelarvore SET idarvore = ?,"
                +                                                                " idvariavel = ?,"
                +                                                                " valor = ?"
                +                                                                " where id = ?");
        p.setInt(1, variavelArvore.getIdArvore());
        p.setInt(2, variavelArvore.getIdVariavel());
        p.setDouble(3, variavelArvore.getValor());

        p.setInt(4, variavelArvore.getId());
        p.executeUpdate();
        p.close();
    }
   
   public VariavelArvore getVariavelArvore(String idArvore) throws SQLException
   {
        List<VariavelArvore> variaveisArvore = new ArrayList<VariavelArvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM variavelarvore where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           VariavelArvore variavelArvore = new VariavelArvore();
           variavelArvore.setId(rs.getInt("id"));
           variavelArvore.setIdArvore(rs.getInt("idarvore"));
           variavelArvore.setIdVariavel(rs.getInt("idvariavel"));
           variavelArvore.setValor(rs.getDouble("valor"));
           variaveisArvore.add(variavelArvore);
        }
        rs.close();
        p.close();
        return variaveisArvore.get(0);
   }
   
   public ArrayList<VariavelArvore> listarVariaveisArvore(int idArvore) throws Exception{
        ArrayList<VariavelArvore> variaveisArvore = new ArrayList<VariavelArvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT va.id as id, "
                + "                                             va.valor as valor, "
                + "                                             v.id as idvariavel, "
                + "                                             v.sigla as sigla, "
                + "                                             v.nome as nome "
                + "                                      FROM arvore a "
                + "                                      INNER JOIN variavelarvore va ON a.id = va.idarvore "
                + "                                      INNER JOIN variavel v ON va.idvariavel = v.id "
                + "                                      WHERE a.id = ?");

        
        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Variavel variavel = new Variavel();
           variavel.setId(rs.getInt("idvariavel"));
           variavel.setSigla(rs.getString("sigla"));
           variavel.setNome(rs.getString("nome"));
           
           VariavelArvore variavelArvore = new VariavelArvore();
           variavelArvore.setId(rs.getInt("id"));
           variavelArvore.setIdArvore(idArvore);
           variavelArvore.setIdVariavel(rs.getInt("idvariavel"));
           variavelArvore.setValor(rs.getDouble("valor"));
           variavelArvore.setVariavel(variavel);

           variaveisArvore.add(variavelArvore);

        }
        rs.close();
        p.close();
        return variaveisArvore;
    }
    
}

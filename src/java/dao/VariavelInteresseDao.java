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

import model.VariavelInteresse;

/**
 *
 * @author jaime
 */
public class VariavelInteresseDao extends MainDao {
    
    public VariavelInteresseDao()
    {
       super();
    }
   public VariavelInteresse getVariavelInteresse(int idVariavelInteresse) throws SQLException
   {
        VariavelInteresse variavelInteresse = new VariavelInteresse();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM variavelinteresse where id = ?");
        p.setInt(1, idVariavelInteresse);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           variavelInteresse.setId(rs.getInt("id"));
           variavelInteresse.setSigla(rs.getString("sigla"));
           variavelInteresse.setNome(rs.getString("nome"));
        }
        rs.close();
        p.close();
        super.con.close();        
        return variavelInteresse;
   }
    
}

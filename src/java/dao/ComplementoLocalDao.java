/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Local;

/**
 *
 * @author paulozeferino
 */
public class ComplementoLocalDao extends MainDao {
    
     public ComplementoLocalDao()
    {
      super();
    }
    
     public int returnLastLocal() throws SQLException
   {
       ArrayList<Local> locais = new ArrayList<Local>();
       
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM local ORDER BY id DESC LIMIT 1");
       
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Local local = new Local();
           local.setId(rs.getInt("id"));
           local.setDescricao(rs.getString("descricao"));
           local.setArea(rs.getDouble("area"));
           local.setAreaParcela(rs.getDouble("areaparcela"));
           local.setIdTipoEstimativa(rs.getInt("idtipoestimativa"));
           local.setIdFormacao(rs.getInt("idformacao"));
           local.setIdEspacamento(rs.getInt("idespacamento"));
           local.setIdDmTipoDistancia(rs.getInt("iddmtipodistancia"));
           local.setIdDmTipoPonderacao(rs.getInt("iddmtipoponderacao"));           
           local.setDmQtdeVizinhos(rs.getInt("dmqtdevizinhos"));                      
           local.setDmComLn(rs.getBoolean("dmcomln"));                                 
           
           locais.add(local);
           
        }
        rs.close();
        p.close();
        super.con.close();        
        System.out.println("Olha o di do ultimo local:"+locais.get(0).getId());
        return locais.get(0).getId(); 
   }
    
}

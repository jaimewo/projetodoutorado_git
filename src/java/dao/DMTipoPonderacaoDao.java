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
import model.DMTipoPonderacao;

/**
 *
 * @author jaimewo
 */
public class DMTipoPonderacaoDao extends MainDao{
    
    
    public DMTipoPonderacaoDao()
    {
        super();
    }
    
   
   public DMTipoPonderacao getTipoPonderacao(String id) throws SQLException
   {
        ArrayList<DMTipoPonderacao> dmTiposPonderacao = new ArrayList<DMTipoPonderacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM dmtipoponderacao where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           DMTipoPonderacao dmTipoPonderacao = new DMTipoPonderacao();
           dmTipoPonderacao.setId(rs.getInt("id"));
           dmTipoPonderacao.setDescricao(rs.getString("descricao"));
           dmTiposPonderacao.add(dmTipoPonderacao);
        }
        rs.close();
        p.close();
        super.con.close();        
        return dmTiposPonderacao.get(0);
   }
   
   public ArrayList<DMTipoPonderacao> listarTiposPonderacao() throws Exception{
        ArrayList<DMTipoPonderacao> dmTiposPonderacao = new ArrayList<DMTipoPonderacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM dmtipoponderacao");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           DMTipoPonderacao dmTipoPonderacao = new DMTipoPonderacao();
           dmTipoPonderacao.setId(rs.getInt("id"));
           dmTipoPonderacao.setDescricao(rs.getString("descricao"));
           dmTiposPonderacao.add(dmTipoPonderacao);
        }
        rs.close();
        p.close();
        super.con.close();        
        return dmTiposPonderacao;
    }
    
}

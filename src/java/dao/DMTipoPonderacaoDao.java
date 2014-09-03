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
import model.DmTipoPonderacao;

/**
 *
 * @author jaimewo
 */
public class DMTipoPonderacaoDao extends MainDao{
    
    
    public DMTipoPonderacaoDao()
    {
        super();
    }
    
   
   public DmTipoPonderacao getTipoPonderacao(String id) throws SQLException
   {
        ArrayList<DmTipoPonderacao> dmTiposPonderacao = new ArrayList<DmTipoPonderacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,descricao FROM dmtipoponderacao where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           DmTipoPonderacao dmTipoPonderacao = new DmTipoPonderacao();
           dmTipoPonderacao.setId(rs.getInt("id"));
           dmTipoPonderacao.setDescricao(rs.getString("descricao"));
           dmTiposPonderacao.add(dmTipoPonderacao);
        }
        rs.close();
        p.close();
        super.con.close();        
        return dmTiposPonderacao.get(0);
   }

   
   public String getDescricao(int id) throws SQLException
   {
        String descricao="";

        PreparedStatement p = this.con.prepareStatement("SELECT descricao FROM dmtipoponderacao where id = ?");
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           descricao = rs.getString("descricao");
        }
        rs.close();
        p.close();
        super.con.close();        
        return descricao;
   }   
   public ArrayList<DmTipoPonderacao> listarTiposPonderacao() throws Exception{
        ArrayList<DmTipoPonderacao> dmTiposPonderacao = new ArrayList<DmTipoPonderacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM dmtipoponderacao");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           DmTipoPonderacao dmTipoPonderacao = new DmTipoPonderacao();
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

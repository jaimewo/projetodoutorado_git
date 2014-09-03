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
import model.Padrao;

/**
 *
 * @author jaime
 */
public class PadraoDao extends MainDao{
    
 //   private Connection con = null;
    
    
    public PadraoDao()
    {
     super();
    }
    
    public void cadastrar(Padrao padrao) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO padrao(idtipofloresta,"
                    +                                                          "idformacao,"
                    +                                                          "idespecie,"
                    +                                                          "idespacamento,"
                    +                                                          "idadeinicial,"
                    +                                                          "idadefinal,"
                    +                                                          "idequacaopadrao,"
                    +                                                          "qtdevolumepadrao"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?)");
            p.setInt(1, padrao.getIdTipoFloresta());
            p.setInt(2, padrao.getIdFormacao());
            p.setInt(3, padrao.getIdEspecie());
            p.setInt(4, padrao.getIdEspacamento());
            p.setInt(5, padrao.getIdadeInicial());
            p.setInt(6, padrao.getIdadeFinal());
            p.setInt(7, padrao.getIdEquacaoPadrao());
            p.setDouble(8, padrao.getQtdeVolumePadrao());
            p.executeUpdate();
            p.close();
            super.con.close();
    }
    
    
    public void deletar(Padrao padrao) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from padrao where id = ?");
            p.setInt(1, padrao.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
        
    }
    
   public void update(Padrao padrao) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE padrao SET idtipofloresta = ?,"
                +                                                         "idformacao = ?,"
                +                                                         "idespecie = ?,"
                +                                                         "idespacamento = ?,"
                +                                                         "idadeinicial = ?,"
                +                                                         "idadefinal = ?,"
                +                                                         "idequacaopadrao = ?,"
                +                                                         "qtdevolumepadrao = ?"
                +                                                         " where id = ?");
        p.setInt(1, padrao.getIdTipoFloresta());
        p.setInt(2, padrao.getIdFormacao());
        p.setInt(3, padrao.getIdEspecie());
        p.setInt(4, padrao.getIdEspacamento());
        p.setInt(5, padrao.getIdadeInicial());
        p.setInt(6, padrao.getIdadeFinal());
        p.setInt(7, padrao.getIdEquacaoPadrao());
        p.setDouble(8, padrao.getQtdeVolumePadrao());
        
        p.setInt(9, padrao.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
   public Padrao getPadrao(String padrao_id) throws SQLException
   {
        ArrayList<Padrao> padroes = new ArrayList<Padrao>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,"
                +                                              "idtipofloresta"
                +                                              "idformacao,"
                +                                              "idespecie,"
                +                                              "idespacamento,"
                +                                              "idadeinicial,"
                +                                              "idadefinal,"
                +                                              "idequacaopadrao,"
                +                                              "qtdevolumepadrao"
                +                                              " FROM padrao where id = ?");
        p.setInt(1, Integer.parseInt(padrao_id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Padrao padrao = new Padrao();
           padrao.setId(rs.getInt("id"));
           padrao.setIdTipoFloresta(rs.getInt("idtipofloresta"));
           padrao.setIdFormacao(rs.getInt("idformacao"));
           padrao.setIdEspecie(rs.getInt("idespecie"));
           padrao.setIdEspacamento(rs.getInt("idespacamento"));
           padrao.setIdadeInicial(rs.getInt("idadeinicial"));
           padrao.setIdadeFinal(rs.getInt("idadefinal"));
           padrao.setIdEquacaoPadrao(rs.getInt("idequacaopadrao"));
           padrao.setQtdeVolumePadrao(rs.getDouble("qtdevolumepadrao"));
           padroes.add(padrao);
        }
        rs.close();
        p.close();
        super.con.close();        
        return padroes.get(0);
   }
   
   public List<Padrao> listarPadroes() throws Exception{
        ArrayList<Padrao> padroes = new ArrayList<Padrao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM padrao");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Padrao padrao = new Padrao();
           padrao.setId(rs.getInt("id"));
           padrao.setIdTipoFloresta(rs.getInt("idtipofloresta"));
           padrao.setIdFormacao(rs.getInt("idformacao"));
           padrao.setIdEspecie(rs.getInt("idespecie"));
           padrao.setIdEspacamento(rs.getInt("idespacamento"));
           padrao.setIdadeInicial(rs.getInt("idadeinicial"));
           padrao.setIdadeFinal(rs.getInt("idadefinal"));
           padrao.setIdEquacaoPadrao(rs.getInt("idequacaopadrao"));
           padrao.setQtdeVolumePadrao(rs.getDouble("qtdevolumepadrao"));
           padroes.add(padrao);
        }
        rs.close();
        p.close();
        super.con.close();        
        return padroes;
    }
    
}

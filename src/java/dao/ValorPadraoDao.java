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
import model.ValorPadrao;

/**
 *
 * @author jaime
 */
public class ValorPadraoDao extends MainDao {
    
    
    public ValorPadraoDao()
    {
     super();
    }
     
    public void cadastrar(ValorPadrao valorPadrao) throws SQLException, Exception
    {

        String sql = "INSERT INTO valorpadrao(idtipofloresta,"
                    +                   "idformacao,"
                    +                   "idespecie,"
                    +                   "idespacamento,"
                    +                   "idadeinicial,"
                    +                   "idadefinal,"
                    +                   "idequacaopadrao,"
                    +                   "qtdevolumepadrao,"
                    +                   "qtdebiomassapadrao,"
                    +                   "qtdecarbonopadrao"
                    +                   ") VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING valorpadrao.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt(1, valorPadrao.getIdTipoFloresta());
        p.setInt(2, valorPadrao.getIdFormacao());
        p.setInt(3, valorPadrao.getIdEspecie());
        p.setInt(4, valorPadrao.getIdEspacamento());
        p.setInt(5, valorPadrao.getIdadeInicial());
        p.setInt(6, valorPadrao.getIdadeFinal());
        p.setInt(7, valorPadrao.getIdEquacaoPadrao());
        p.setDouble(8, valorPadrao.getQtdeVolumePadrao());        
        p.setDouble(9, valorPadrao.getQtdeBiomassaPadrao());        
        p.setDouble(10, valorPadrao.getQtdeCarbonoPadrao());        
        int idValorPadrao = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idValorPadrao = rs.getInt(1);
        }
        valorPadrao.setId(idValorPadrao);
        
        p.close();
        super.con.close();
    }
    
    
    public void deletar(ValorPadrao valorPadrao) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from valorpadrao where id = ?");
            p.setInt(1, valorPadrao.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
        
    }

    
   public ValorPadrao getValorPadrao(String idValorPadrao) throws SQLException
   {
        ArrayList<ValorPadrao> valoresPadrao = new ArrayList<ValorPadrao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM valorpadrao where id = ?");
        p.setInt(1, Integer.parseInt(idValorPadrao));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ValorPadrao valorPadrao = new ValorPadrao();
           valorPadrao.setId(rs.getInt("id"));
           valorPadrao.setIdTipoFloresta(rs.getInt("idtipofloresta"));
           valorPadrao.setIdFormacao(rs.getInt("idformacao"));
           valorPadrao.setIdEspecie(rs.getInt("idespecie"));
           valorPadrao.setIdEspacamento(rs.getInt("idespacamento"));
           valorPadrao.setIdadeInicial(rs.getInt("idadeinicial"));
           valorPadrao.setIdadeFinal(rs.getInt("idadefinal"));
           valorPadrao.setIdEquacaoPadrao(rs.getInt("idequacaopadrao"));
           valorPadrao.setQtdeVolumePadrao(rs.getDouble("qtdevolumepadrao"));
           valorPadrao.setQtdeBiomassaPadrao(rs.getDouble("qtdebiomassapadrao"));
           valorPadrao.setQtdeCarbonoPadrao(rs.getDouble("qtdecarbonopadrao"));
           valoresPadrao.add(valorPadrao);
        }
        rs.close();
        p.close();
        super.con.close();
        return valoresPadrao.get(0);
   }
   
   public ValorPadrao getValorPadrao(int idTipoFloresta,
                                     int idFormacao, 
                                     int idEspecie, 
                                     int idEspacamento, 
                                     int idade) throws SQLException
   {
        ArrayList<ValorPadrao> valoresPadrao = new ArrayList<ValorPadrao>();
        PreparedStatement p;
        if (idTipoFloresta==1) {
            p = this.con.prepareStatement("SELECT * FROM valorpadrao where idtipofloresta = ? "
                    +                                               "and   idformacao = ?");
            p.setInt(1, idTipoFloresta);
            p.setInt(2, idFormacao);
        } else {
            p = this.con.prepareStatement("SELECT * FROM valorpadrao where idtipofloresta = ? "
                    +                                               "and   idespecie = ? "
                    +                                               "and   idespacamento = ? "
                    +                                               "and   idadeinicial <= ? "
                    +                                               "and   idadefinal >= ? ");
            p.setInt(1, idTipoFloresta);
            p.setInt(2, idEspecie);
            p.setInt(3, idEspacamento);
            p.setInt(4, idade);
            p.setInt(5, idade);
        }
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ValorPadrao valorPadrao = new ValorPadrao();
           valorPadrao.setId(rs.getInt("id"));
           valorPadrao.setIdTipoFloresta(rs.getInt("idtipofloresta"));
           valorPadrao.setIdFormacao(rs.getInt("idformacao"));
           valorPadrao.setIdEspecie(rs.getInt("idespecie"));
           valorPadrao.setIdEspacamento(rs.getInt("idespacamento"));
           valorPadrao.setIdadeInicial(rs.getInt("idadeinicial"));
           valorPadrao.setIdadeFinal(rs.getInt("idadefinal"));
           valorPadrao.setIdEquacaoPadrao(rs.getInt("idequacaopadrao"));
           valorPadrao.setQtdeVolumePadrao(rs.getDouble("qtdevolumepadrao"));
           valorPadrao.setQtdeBiomassaPadrao(rs.getDouble("qtdebiomassapadrao"));
           valorPadrao.setQtdeCarbonoPadrao(rs.getDouble("qtdecarbonopadrao"));
           valoresPadrao.add(valorPadrao);
        }
        rs.close();
        p.close();
        super.con.close();
        return valoresPadrao.get(0);
   }

   public ArrayList<ValorPadrao> listarValoresPadrao() throws Exception{
       
        ArrayList<ValorPadrao> valoresPadrao = new ArrayList<ValorPadrao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM valorpadrao");
        ResultSet rs = p.executeQuery();
        
        while(rs.next()){
           ValorPadrao valorPadrao = new ValorPadrao();
           valorPadrao.setId(rs.getInt("id"));
           valorPadrao.setIdTipoFloresta(rs.getInt("idtipofloresta"));
           valorPadrao.setIdFormacao(rs.getInt("idformacao"));
           valorPadrao.setIdEspecie(rs.getInt("idespecie"));
           valorPadrao.setIdEspacamento(rs.getInt("idespacamento"));
           valorPadrao.setIdadeInicial(rs.getInt("idadeinicial"));
           valorPadrao.setIdadeFinal(rs.getInt("idadefinal"));
           valorPadrao.setIdEquacaoPadrao(rs.getInt("idequacaopadrao"));
           valorPadrao.setQtdeVolumePadrao(rs.getDouble("qtdevolumepadrao"));
           valorPadrao.setQtdeBiomassaPadrao(rs.getDouble("qtdebiomassapadrao"));
           valorPadrao.setQtdeCarbonoPadrao(rs.getDouble("qtdecarbonopadrao"));
           valoresPadrao.add(valorPadrao);
        }
        rs.close();
        p.close();
        super.con.close();
        return valoresPadrao;
    }

}
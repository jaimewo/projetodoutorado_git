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
import model.Equacao;


/**
 *
 * @author Jaime
 */
public class EquacaoDao extends MainDao{
    
    public EquacaoDao() {
        super();
    }
    public void cadastrar(Equacao equacao) throws SQLException
                        
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO equacao (expressaoequacao,"
                    +                                                            "expressaomodelo,"
                    +                                                            "idvariavelinteresse,"
                    +                                                            "idautormodelo,"
                    +                                                            "r2,"
                    +                                                            "r2ajust,"
                    +                                                            "syx,"
                    +                                                            "syxperc,"
                    +                                                            "ia,"
                    +                                                            "idtrabalhocientifico"
                    +                                                            ") VALUES (?,?,?,?,?,?,?,?,?,?)");
            p.setString(1, equacao.getExpressaoEquacao());
            p.setString(2, equacao.getExpressaoModelo());
            p.setInt(3, equacao.getIdVariavelInteresse());
            p.setInt(4, equacao.getIdAutorModelo());
            p.setDouble(5, equacao.getR2());
            p.setDouble(6, equacao.getR2Ajust());
            p.setDouble(7, equacao.getSyx());
            p.setDouble(8, equacao.getSyxPerc());
            p.setDouble(9, equacao.getIa());
            p.setInt(10, equacao.getIdTrabalhoCientifico());

            
            p.executeUpdate();
            p.close();
            
    }
    
    
    public void deletar(Equacao equacao) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from equacao where id = ?");
            p.setInt(1, equacao.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(Equacao equacao) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE equacao SET expressaoequacao = ?, "
                    +                                                      "expressaomodelo = ?,"
                    +                                                      "idvariavelinteresse = ?,"
                    +                                                      "idautormodelo = ?,"
                    +                                                      "r2 = ?,"
                    +                                                      "r2ajust = ?,"
                    +                                                      "syx = ?, "
                +                                                          "syxperc = ?, "
                +                                                          "ia = ?, "
                +                                                          "idtrabalhocientifico = ? "
                +                                                          "WHERE id = ?");
        p.setString(1, equacao.getExpressaoEquacao());
        p.setString(2, equacao.getExpressaoModelo());
        p.setInt(3, equacao.getIdVariavelInteresse());
        p.setInt(4, equacao.getIdAutorModelo());
        p.setDouble(5, equacao.getR2());
        p.setDouble(6, equacao.getR2Ajust());
        p.setDouble(7, equacao.getSyx());
        p.setDouble(8, equacao.getSyxPerc());
        p.setDouble(9, equacao.getIa());
        p.setInt(10, equacao.getIdTrabalhoCientifico());
        
        p.setInt(11, equacao.getId());
        p.executeUpdate();
        p.close();
    }
   
   public Equacao getEquacao(String id) throws SQLException
   {
        List<Equacao> equacoes = new ArrayList<Equacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM equacao where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Equacao equacao = new Equacao();
           equacao.setId(rs.getInt("id"));
           equacao.setExpressaoEquacao(rs.getString("expressaoequacao"));
           equacao.setExpressaoModelo(rs.getString("expressaomodelo"));
           equacao.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           equacao.setIdAutorModelo(rs.getInt("idautormodelo"));
           equacao.setR2(rs.getInt("r2"));
           equacao.setR2Ajust(rs.getInt("r2ajust"));
           equacao.setSyx(rs.getDouble("syx"));
           equacao.setSyxPerc(rs.getDouble("syxperc"));
           equacao.setIa(rs.getDouble("ia"));
           equacao.setIdTrabalhoCientifico(rs.getInt("idtrabalhocientifico"));
           equacoes.add(equacao);
        }
        rs.close();
        p.close();
        return equacoes.get(0);
   }
   
   public List<Equacao> listarEquacoes() throws Exception{
        List<Equacao> equacoes = new ArrayList<Equacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM equacao");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Equacao equacao = new Equacao();
           equacao.setId(rs.getInt("id"));
           equacao.setExpressaoEquacao(rs.getString("expressaoequacao"));
           equacao.setExpressaoModelo(rs.getString("expressaomodelo"));
           equacao.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           equacao.setIdAutorModelo(rs.getInt("idautormodelo"));
           equacao.setR2(rs.getInt("r2"));
           equacao.setR2Ajust(rs.getInt("r2ajust"));
           equacao.setSyx(rs.getDouble("syx"));
           equacao.setSyxPerc(rs.getDouble("syxperc"));
           equacao.setIa(rs.getDouble("ia"));
           equacao.setIdTrabalhoCientifico(rs.getInt("idtrabalhocientifico"));
           equacoes.add(equacao);
        }
        rs.close();
        p.close();
        return equacoes;
    }
   public ArrayList<Equacao> listarEquacoesTrabalho(int idTrabalhoCientifico) throws Exception{
        ArrayList<Equacao> equacoesTrabalho = new ArrayList<Equacao>();
        PreparedStatement p = this.con.prepareStatement("SELECT e.id,"
                + "                                             e.expressaoequacao, "
                + "                                             e.expressaomodelo, "
                + "                                             e.idvariavelinteresse,"
                + "                                             e.idautormodelo,"
                + "                                             e.r2,"
                + "                                             e.r2ajust,"
                + "                                             e.syx,"
                + "                                             e.syxperc,"
                + "                                             e.ia,"
                + "                                             e.idtrabalhocientifico "
                + "                                      FROM trabalhocientifico tc "
                + "                                      INNER JOIN equacaotrabalhocientifico etc ON tc.id = etc.idtrabalhocientifico "
                + "                                      INNER JOIN equacao e ON etc.idequacao = e.id "
                + "                                      WHERE tc.id = ?");
        p.setInt(1, idTrabalhoCientifico);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Equacao equacao = new Equacao();
           equacao.setId(rs.getInt("id"));
           equacao.setExpressaoEquacao(rs.getString("expressaoequacao"));
           equacao.setExpressaoModelo(rs.getString("expressaomodelo"));
           equacao.setIdVariavelInteresse(rs.getInt("idvariavelinteresse"));
           equacao.setIdAutorModelo(rs.getInt("idautormodelo"));
           equacao.setR2(rs.getDouble("r2"));
           equacao.setR2Ajust(rs.getInt("r2ajust"));
           equacao.setSyx(rs.getDouble("syx"));
           equacao.setSyxPerc(rs.getDouble("syxperc"));
           equacao.setIa(rs.getDouble("ia"));
           equacao.setIdTrabalhoCientifico(rs.getInt("idTrabalhoCientifico"));
           equacoesTrabalho.add(equacao);
        }
        rs.close();
        p.close();
        return equacoesTrabalho;
    }
    
}

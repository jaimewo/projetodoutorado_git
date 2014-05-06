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
import model.VariavelArvoreAjuste;

/**
 *
 * @author jaime
 */
public class VariavelArvoreAjusteDao extends MainDao {
    
    
    
    
    public VariavelArvoreAjusteDao()
    {
      super();
    }
     
    public void cadastrar(VariavelArvoreAjuste variavelArvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO variavelarvoreajuste(idarvoreajuste,"
                    +                                                          "idvariavel,"
                    +                                                          "valor"
                    +                                                          ") VALUES (?,?,?)");
            p.setInt(1, variavelArvoreAjuste.getIdArvoreAjuste());
            p.setInt(2, variavelArvoreAjuste.getIdVariavel());
            p.setDouble(3, variavelArvoreAjuste.getValor());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(VariavelArvoreAjuste variavelArvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from variavelarvoreajuste where id = ?");
            p.setInt(1, variavelArvoreAjuste.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(VariavelArvoreAjuste variavelArvoreAjuste) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE variavelarvoreajuste SET idarvoreajuste = ?,"
                +                                                                      " idvariavel = ?,"
                +                                                                      " valor = ?"
                +                                                                      " where id = ?");
        p.setInt(1, variavelArvoreAjuste.getIdArvoreAjuste());
        p.setInt(2, variavelArvoreAjuste.getIdVariavel());
        p.setDouble(3, variavelArvoreAjuste.getValor());

        p.setInt(4, variavelArvoreAjuste.getId());
        p.executeUpdate();
        p.close();
    }
   
   public VariavelArvoreAjuste getVariavelArvoreAjuste(String idArvore) throws SQLException
   {
        List<VariavelArvoreAjuste> variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM variavelarvoreajuste where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           VariavelArvoreAjuste variavelArvoreAjuste = new VariavelArvoreAjuste();
           variavelArvoreAjuste.setId(rs.getInt("id"));
           variavelArvoreAjuste.setIdArvoreAjuste(rs.getInt("idarvoreajuste"));
           variavelArvoreAjuste.setIdVariavel(rs.getInt("idvariavel"));
           variavelArvoreAjuste.setValor(rs.getDouble("valor"));
           variaveisArvoreAjuste.add(variavelArvoreAjuste);
        }
        rs.close();
        p.close();
        return variaveisArvoreAjuste.get(0);
   }
   
   public ArrayList<VariavelArvoreAjuste> listarVariaveisArvoreAjuste(int idArvoreAjuste) throws Exception{
        ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT vaa.id as id, "
                + "                                             vaa.valor as valor, "
                + "                                             v.id as idvariavel, "
                + "                                             v.sigla as sigla, "
                + "                                             v.nome as nome "
                + "                                      FROM arvore a "
                + "                                      INNER JOIN variavelarvoreajuste vaa ON a.id = vaa.idarvore "
                + "                                      INNER JOIN variavel v ON vaa.idvariavel = v.id "
                + "                                      WHERE a.id = ?");

        
        p.setInt(1, idArvoreAjuste);       
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Variavel variavel = new Variavel();
           variavel.setId(rs.getInt("idvariavel"));
           variavel.setSigla(rs.getString("sigla"));
           variavel.setNome(rs.getString("nome"));
           
           VariavelArvoreAjuste variavelArvoreAjuste = new VariavelArvoreAjuste();
           variavelArvoreAjuste.setId(rs.getInt("id"));
           variavelArvoreAjuste.setIdArvoreAjuste(idArvoreAjuste);
           variavelArvoreAjuste.setIdVariavel(rs.getInt("idvariavel"));
           variavelArvoreAjuste.setValor(rs.getDouble("valor"));
           variavelArvoreAjuste.setVariavel(variavel);

           variaveisArvoreAjuste.add(variavelArvoreAjuste);

        }
        rs.close();
        p.close();
        return variaveisArvoreAjuste;
    }
    
}

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
import model.Arvore;
import model.ArvoreQuantidade;
import model.Local;
import model.Parcela;
import model.VariavelArvore;

/**
 *
 * @author jaime
 */
public class ArvoreDao extends MainDao {
    
    
    public ArvoreDao()
    {
     super();
    }
     
    public void cadastrar(Arvore arvore) throws SQLException, Exception
    {

        String sql = "INSERT INTO arvore(idparcela,"
                    +                   "numarvore"
                    +                   ") VALUES (?,?) RETURNING arvore.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt(1, arvore.getIdParcela());
        p.setInt(2, arvore.getNumArvore());
        int idArvore = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idArvore = rs.getInt(1);
        }
        
        ArvoreQuantidade arvoreQuantidade = new ArvoreQuantidade();
        for(int iVi=1;iVi<4;iVi++) { //Vi=Variavel de Interesse (1-Biomassa,2-Carbono,3-Volume)
            for(int iMc=1;iMc<3;iMc++) { //Mc=Método de Cálculo (1-Equacao,2-Data Mining)
                arvoreQuantidade.setIdArvore(idArvore);
                arvoreQuantidade.setIdVariavelInteresse(iVi);
                arvoreQuantidade.setIdMetodoCalculo(iMc);
                arvoreQuantidade.setQtdeEst(0.0);
                ArvoreQuantidadeDao arvoreQuantidadeDao = new ArvoreQuantidadeDao();                
                arvoreQuantidadeDao.cadastrar(arvoreQuantidade);
            }
        }
        
        if (arvore.variaveisArvore.size()>0) {
            for (VariavelArvore variavelArvore: arvore.variaveisArvore) {
                variavelArvore.setIdArvore(idArvore);
                VariavelArvoreDao variavelarvoreDao = new VariavelArvoreDao();                            
                variavelarvoreDao.cadastrar(variavelArvore);
            }
        }
            
        p.close();
        super.con.close();
    }
    
    
    public void deletar(Arvore arvore) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvore where id = ?");
            p.setInt(1, arvore.getId());
            p.executeUpdate();
            p.close();
            super.con.close();
        
    }
    
    public void deletarLocal(Local local) throws SQLException
    {
    
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela where idlocal = ?");
        p.setInt(1, local.getId());
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           int idParcela = rs.getInt("id");
           PreparedStatement p1 = this.con.prepareStatement("SELECT * FROM arvore where idparcela = ?");
           p1.setInt(1, idParcela);
           ResultSet rs1 = p1.executeQuery();
           while(rs1.next()){
               int idArvore = rs1.getInt("id");
               
               PreparedStatement p2 = this.con.prepareStatement("DELETE from variavelarvore where idarvore = ?"); 
               p2.setInt(1, idArvore);
               p2.executeUpdate();
               p2.close();
               
               PreparedStatement p3 = this.con.prepareStatement("DELETE from arvorequantidade where idarvore = ?"); 
               p3.setInt(1, idArvore);
               p3.executeUpdate();
               p3.close();
               
           }
           PreparedStatement p4 = this.con.prepareStatement("DELETE from arvore where idparcela = ?"); 
           p4.setInt(1, idParcela);
           p4.executeUpdate();
           p4.close();
        }        
        rs.close();
        PreparedStatement p5 = this.con.prepareStatement("DELETE from parcela where idlocal = ?"); 
        p5.setInt(1, local.getId());
        p5.executeUpdate();
        p5.close();
        
        p.close();
        super.con.close();
    
   }    
   public void update(Arvore arvore) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvore SET idparcela = ?,"
                +                                                         " numarvore = ?"
                +                                                         " where id = ?");
        p.setInt(1, arvore.getIdParcela());
        p.setInt(2, arvore.getNumArvore());

        p.setInt(3, arvore.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
   
   public Arvore getArvore(String idArvore) throws SQLException
   {
        ArrayList<Arvore> arvores = new ArrayList<Arvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvore where id = ?");
        p.setInt(1, Integer.parseInt(idArvore));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Arvore arvore = new Arvore();
           arvore.setId(rs.getInt("id"));
           arvore.setIdParcela(rs.getInt("idparcela"));
           arvore.setNumArvore(rs.getInt("numarvore"));
           arvores.add(arvore);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvores.get(0);
   }
   
   public Arvore getArvore(int idParcela, int numArvore) throws SQLException
   {
        ArrayList<Arvore> arvores = new ArrayList<Arvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvore where idparcela = ? AND numarvore = ?");
        p.setInt(1, idParcela);
        p.setInt(2, numArvore);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Arvore arvore = new Arvore();
           arvore.setId(rs.getInt("id"));
           arvore.setIdParcela(rs.getInt("idparcela"));
           arvore.setNumArvore(rs.getInt("numarvore"));
           arvores.add(arvore);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvores.get(0);
   }

   
   public ArrayList<Arvore> listarArvores(int idParcela) throws Exception{
       
        ArrayList<Arvore> arvores = new ArrayList<Arvore>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvore WHERE idparcela = ?");
        p.setInt(1, idParcela);
        ResultSet rs = p.executeQuery();
        
        while(rs.next()){
           Arvore arvore = new Arvore();
           arvore.setId(rs.getInt("id"));
           arvore.setIdParcela(rs.getInt("idparcela"));
           arvore.setNumArvore(rs.getInt("numarvore"));
          
           arvores.add(arvore);
        }
        rs.close();
        p.close();
        super.con.close();
        return arvores;
    }
    
}
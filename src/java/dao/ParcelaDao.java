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
import model.Local;
import model.Parcela;
import model.ParcelaQuantidade;

/**
 *
 * @author Jaime
 */
public class ParcelaDao extends MainDao {
    
    public ParcelaDao() {
        super();
    }
    
    public void cadastrar(Parcela parcela) throws SQLException, Exception
                        
    {
        String sql = "INSERT INTO parcela (numparcela, "
                    +                     "areaparcela,"
                    +                     "idlocal"
                    +                     ") VALUES (?,?,?) RETURNING parcela.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt   (1, parcela.getNumParcela());
        p.setDouble(2, parcela.getAreaParcela());
        p.setInt   (3, parcela.getIdLocal());
        int idParcela = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idParcela = rs.getInt(1);
        }
        
        ParcelaQuantidadeDao parcelaQuantidadeDao = new ParcelaQuantidadeDao();
        ParcelaQuantidade parcelaQuantidade = new ParcelaQuantidade();

        //SE VEIO ARRAY, CADASTRA PARCELAQUANTIDADE DAS PARCELAS
        if (parcela.parcelasQuantidade.size()>0) {        
           for (ParcelaQuantidade parcelasQuantidade: parcela.parcelasQuantidade) {
                parcelaQuantidade.setIdParcela(idParcela);
                parcelaQuantidade.setIdVariavelInteresse(parcelasQuantidade.getIdVariavelInteresse());
                parcelaQuantidade.setIdMetodoCalculo(parcelasQuantidade.getIdMetodoCalculo());
                parcelaQuantidade.setQtde(parcelasQuantidade.getQtde());
                parcelaQuantidadeDao = new ParcelaQuantidadeDao();
                parcelaQuantidadeDao.cadastrar(parcelaQuantidade);
            
            }
        }
        
        //SE VEIO ARRAY, CADASTRA ÁRVORES DAS PARCELAS
        if (parcela.arvores.size()>0) {
            for (Arvore arvore: parcela.arvores) {
                arvore.setIdParcela(idParcela);
                ArvoreDao arvoreDao = new ArvoreDao();                        
                arvoreDao.cadastrar(arvore);
            }
        }
        p.close();
        super.con.close();
        
        //Atualiza o tamanho da parcela na tabela Local
        LocalDao localDao = new LocalDao();
        localDao.updateAreaParcela(parcela.getIdLocal(), parcela.getAreaParcela());
                
    }
    
    
    public void deletar(Parcela parcela) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from parcela where id = ?");
        p.setInt(1, parcela.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
        
    }
    
    public void deletarParcela(Local local) throws SQLException
    {
    
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela where idlocal = ?");
        p.setInt(1, local.getId());
        ResultSet rs = p.executeQuery();
        while(rs.next()){
               int idParcela = rs.getInt("id");
               PreparedStatement p1 = this.con.prepareStatement("DELETE from parcelaQuantidade where idparcela = ?"); 
               p1.setInt(1, idParcela);
               p1.executeUpdate();
               p1.close();
        }
        PreparedStatement p2 = this.con.prepareStatement("DELETE from parcela where idlocal = ?"); 
        p2.setInt(1, local.getId());
        p2.executeUpdate();
        p2.close();
        
        p.close();
        super.con.close();        
        
    }
    
   public void update(Parcela parcela) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE parcela SET numparcela = ?, "
                    +                                                      "areaparcela = ? "
                +                                                          " WHERE id = ?");
        p.setInt   (1, parcela.getNumParcela());
        p.setDouble(2, parcela.getAreaParcela());
        
        p.setInt(3, parcela.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
      
   public Parcela getParcela(String id) throws SQLException
   {
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Parcela parcela = new Parcela();
           parcela.setId(rs.getInt("id"));
           parcela.setNumParcela(rs.getInt("numparcela"));
           parcela.setAreaParcela(rs.getDouble("areaparcela"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        super.con.close();
        return parcelas.get(0);
   }
   
   public Parcela getParcela(int idLocal,int numParcela) throws SQLException
   {
        List<Parcela> parcelas = new ArrayList<Parcela>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela where idlocal = ? AND numparcela = ?");
        p.setInt(1, idLocal);
        p.setInt(2, numParcela);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Parcela parcela = new Parcela();
           parcela.setId(rs.getInt("id"));
           parcela.setNumParcela(rs.getInt("numparcela"));
           parcela.setAreaParcela(rs.getDouble("areaparcela"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        super.con.close();
        return parcelas.get(0);
   }
   
   public ArrayList<Parcela> listarParcelas(int idLocal) throws Exception{
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM parcela WHERE idLocal = ?");
           
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Parcela parcela = new Parcela();
           parcela.setId(rs.getInt("id"));
           parcela.setNumParcela(rs.getInt("numparcela"));
           parcela.setAreaParcela(rs.getDouble("areaparcela"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        super.con.close();
        return parcelas;
    }
    
}

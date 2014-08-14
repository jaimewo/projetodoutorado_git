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
                    +                     "idlocal,"
                    +                     "qtdebiomassaequacao,"
                    +                     "qtdebiomassadm,"
                    +                     "qtdecarbonoequacao,"
                    +                     "qtdecarbonodm,"
                    +                     "qtdevolumeequacao,"
                    +                     "qtdevolumedm"
                    +                     ") VALUES (?,?,?,?,?,?,?,?,?) RETURNING parcela.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setInt   (1, parcela.getNumParcela());
        p.setDouble(2, parcela.getAreaParcela());
        p.setInt   (3, parcela.getIdLocal());
        p.setDouble(4, parcela.getQtdeBiomassaEquacao());
        p.setDouble(5, parcela.getQtdeBiomassaDm());
        p.setDouble(6, parcela.getQtdeCarbonoEquacao());
        p.setDouble(7, parcela.getQtdeCarbonoDm());
        p.setDouble(8, parcela.getQtdeVolumeEquacao());
        p.setDouble(9, parcela.getQtdeVolumeDm());
        int idParcela = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
           idParcela = rs.getInt(1);
        }
        
        //SE VEIO ARRAY, CADASTRA ÁRVORES DAS PARCELAS
        if (parcela.arvores!=null) {
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
    
        PreparedStatement p = this.con.prepareStatement("DELETE from parcela where idlocal = ?"); 
        p.setInt(1, local.getId());
        p.executeUpdate();
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
           parcela.setQtdeBiomassaEquacao(rs.getDouble("qtdebiomassaequacao"));
           parcela.setQtdeBiomassaDm(rs.getDouble("qtdebiomassadm"));
           parcela.setQtdeCarbonoEquacao(rs.getDouble("qtdecarbonoequacao"));
           parcela.setQtdeCarbonoDm(rs.getDouble("qtdecarbonodm"));
           parcela.setQtdeVolumeEquacao(rs.getDouble("qtdevolumeequacao"));
           parcela.setQtdeVolumeDm(rs.getDouble("qtdevolumedm"));
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
           parcela.setQtdeBiomassaEquacao(rs.getDouble("qtdebiomassaequacao"));
           parcela.setQtdeBiomassaDm(rs.getDouble("qtdebiomassadm"));
           parcela.setQtdeCarbonoEquacao(rs.getDouble("qtdecarbonoequacao"));
           parcela.setQtdeCarbonoDm(rs.getDouble("qtdecarbonodm"));
           parcela.setQtdeVolumeEquacao(rs.getDouble("qtdevolumeequacao"));
           parcela.setQtdeVolumeDm(rs.getDouble("qtdevolumedm"));
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
           parcela.setQtdeBiomassaEquacao(rs.getDouble("qtdebiomassaequacao"));
           parcela.setQtdeBiomassaDm(rs.getDouble("qtdebiomassadm"));
           parcela.setQtdeCarbonoEquacao(rs.getDouble("qtdecarbonoequacao"));
           parcela.setQtdeCarbonoDm(rs.getDouble("qtdecarbonodm"));
           parcela.setQtdeVolumeEquacao(rs.getDouble("qtdevolumeequacao"));
           parcela.setQtdeVolumeDm(rs.getDouble("qtdevolumedm"));
           parcelas.add(parcela);
        }
        rs.close();
        p.close();
        super.con.close();
        return parcelas;
    }
    
   public void updateQtde(Parcela parcela,int idVariavelInteresse, int idMetodoCalculo) throws Exception 
   {

        String sql = "";
        double qtde = 0.0;
       
        if (idMetodoCalculo==1) { //Equacao
            switch (idVariavelInteresse) {
                case 1: //Biomassa
                    sql = "UPDATE parcela SET qtdebiomassaequacao = ? where id = ?";
                    qtde = parcela.getQtdeBiomassaEquacao();
                    break;
                case 2: //Carbono
                    sql = "UPDATE parcela SET qtdecarbonoequacao = ? where id = ?";
                    qtde = parcela.getQtdeCarbonoEquacao();
                    break;
                case 3:
                    sql = "UPDATE parcela SET qtdevolumeequacao = ? where id = ?";
                    qtde = parcela.getQtdeVolumeEquacao();
                    break;
                }                            
        } else {// DM
            switch (idVariavelInteresse) {
                case 1:
                    sql = "UPDATE parcela SET qtdebiomassadm = ? where id = ?";
                    qtde = parcela.getQtdeBiomassaDm();
                    break;
                case 2:
                    sql = "UPDATE parcela SET qtdecarbonodm = ? where id = ?";
                    qtde = parcela.getQtdeCarbonoDm();
                    break;
                case 3:
                    sql = "UPDATE parcela SET qtdevolumedm = ? where id = ?";
                    qtde = parcela.getQtdeVolumeDm();
                    break;
            }                            
        }       
       
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setDouble(1, qtde);
        p.setInt(2, parcela.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
   
}

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
import model.CoordenadaLocal;
import model.Local;
import model.MunicipioLocal;

/**
 *
 * @author Jaime
 */
public class LocalDao extends MainDao {
    
    
    public LocalDao()
    {
      super();
    }
    
    public int cadastrar(Local local) throws SQLException, Exception
                        
    {
        String sql = "INSERT INTO local (descricao, "
                   +                    "area,"
                   +                    "areaparcela,"
                   +                    "idtipoestimativa,"
                   +                    "idtipofloresta,"
                   +                    "iddmtipodistancia,"
                   +                    "dmqtdevizinhos,"
                   +                    "iddmtipoponderacao,"
                   +                    "dmcomln,"                    
                   +                    "qtdebiomassaequacao,"
                   +                    "qtdebiomassadm,"
                   +                    "qtdecarbonoequacao,"
                   +                    "qtdecarbonodm,"
                   +                    "qtdevolumeequacao,"
                   +                    "qtdevolumedm,"
                   +                    "idmunicipio,"
                   +                    "latitude,"
                   +                    "longitude,"                
                   +                    "idade,"
                   +                    "idformacao,"
                   +                    "idespecie,"
                   +                    "idespacamento"
                   +                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING local.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setString(1, local.getDescricao());
        p.setDouble(2, local.getArea());
        p.setDouble(3, local.getAreaParcela());
        p.setInt(4, local.getIdTipoEstimativa());
        p.setInt(5, local.getIdTipoFloresta());
        p.setInt(6, local.getIdDmTipoDistancia());            
        p.setInt(7, local.getDmQtdeVizinhos());                                    
        p.setInt(8, local.getIdDmTipoPonderacao());                        
        p.setBoolean(9, local.isDmComLn());  
        p.setDouble(10, local.getQtdeBiomassaEquacao());
        p.setDouble(11, local.getQtdeBiomassaDm());
        p.setDouble(12, local.getQtdeCarbonoEquacao());
        p.setDouble(13, local.getQtdeCarbonoDm());
        p.setDouble(14, local.getQtdeVolumeEquacao());
        p.setDouble(15, local.getQtdeVolumeDm());        
        p.setDouble(16, local.getIdMunicipio());                
        p.setDouble(17, local.getLatitude());                        
        p.setDouble(18, local.getLongitude());                                
        p.setInt(19, local.getIdade());
        p.setInt(20, local.getIdFormacao());
        p.setInt(21, local.getIdEspecie());
        p.setInt(22, local.getIdEspacamento());
        int idLocal = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
          idLocal = rs.getInt(1);
        }
        p.close();
        super.con.close();        

        return idLocal;
    }
    
    
    public void deletar(Local local) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("DELETE from local where id = ?");
        p.setInt(1, local.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
        
    }
    
   public void update(Local local) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE local SET descricao = ?, "
                +                                                        "area = ?,"
                +                                                        "areaparcela = ?,"
                +                                                        "idtipoestimativa = ?,"
                +                                                        "idtipofloresta = ?,"
                +                                                        "iddmtipodistancia = ?,"                
                +                                                        "dmqtdevizinhos = ?,"                                
                +                                                        "iddmtipoponderacao = ?,"                                
                +                                                        "dmcomln = ?,"                                                
                +                                                        "qtdebiomassaequacao = ?,"
                +                                                        "qtdebiomassadm = ?,"
                +                                                        "qtdecarbonoequacao = ?,"
                +                                                        "qtdecarbonodm = ?,"
                +                                                        "qtdevolumeequacao = ?,"
                +                                                        "qtdevolumedm = ?,"
                +                                                        "idmunicipio = ?,"
                +                                                        "latitude = ?,"
                +                                                        "longitude = ?,"
                +                                                        "idade = ?,"
                +                                                        "idformacao = ?,"
                +                                                        "idespecie = ?,"
                +                                                        "idespacamento = ?"
                +                                                        " WHERE id = ?");
        p.setString(1, local.getDescricao());
        p.setDouble(2, local.getArea());
        p.setDouble(3, local.getAreaParcela());
        p.setInt(4, local.getIdTipoEstimativa());
        p.setInt(5, local.getIdTipoFloresta());
        p.setInt(6, local.getIdDmTipoDistancia());            
        p.setInt(7, local.getDmQtdeVizinhos());                                    
        p.setInt(8, local.getIdDmTipoPonderacao());                        
        p.setBoolean(9, local.isDmComLn());                                                
        p.setDouble(10, local.getQtdeBiomassaEquacao());
        p.setDouble(11, local.getQtdeBiomassaDm());
        p.setDouble(12, local.getQtdeCarbonoEquacao());
        p.setDouble(13, local.getQtdeCarbonoDm());
        p.setDouble(14, local.getQtdeVolumeEquacao());
        p.setDouble(15, local.getQtdeVolumeDm());        
        p.setInt(16, local.getIdMunicipio());                
        p.setDouble(17, local.getLatitude());                
        p.setDouble(18, local.getLongitude());                        
        p.setInt(19, local.getIdade());
        p.setInt(20, local.getIdFormacao());
        p.setInt(21, local.getIdEspecie());
        p.setInt(22, local.getIdEspacamento());
        
        p.setInt(23, local.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
   
    
   public void updateAreaParcela(int idLocal, double areaParcela) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE local SET areaparcela = ?"
                +                                                        " WHERE id = ?");
        p.setDouble(1, areaParcela);
        p.setInt(2, idLocal);
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
   public void updateCoordenadas(Local local) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE local SET latitude = ?, longitude = ?"
                +                                                        " WHERE id = ?");
        p.setDouble(1, local.getLatitude());
        p.setDouble(2, local.getLongitude());
        p.setInt(3, local.getId());
        p.executeUpdate();
        p.close();
        super.con.close();        
    }
    
   public void updateQtde(Local local,int idVariavelInteresse, int idMetodoCalculo) throws Exception 
   {

        String sql = "";
        double qtde = 0.0;
       
        if (idMetodoCalculo==1) { //Equacao
            switch (idVariavelInteresse) {
                case 1: //Biomassa
                    sql = "UPDATE local SET qtdebiomassaequacao = ? where id = ?";
                    qtde = local.getQtdeBiomassaEquacao();
                    break;
                case 2: //Carbono
                    sql = "UPDATE local SET qtdecarbonoequacao = ? where id = ?";
                    qtde = local.getQtdeCarbonoEquacao();
                    break;
                case 3:
                    sql = "UPDATE local SET qtdevolumeequacao = ? where id = ?";
                    qtde = local.getQtdeVolumeEquacao();
                    break;
                }                            
        } else {// DM
            switch (idVariavelInteresse) {
                case 1:
                    sql = "UPDATE local SET qtdebiomassadm = ? where id = ?";
                    qtde = local.getQtdeBiomassaDm();
                    break;
                case 2:
                    sql = "UPDATE local SET qtdecarbonodm = ? where id = ?";
                    qtde = local.getQtdeCarbonoDm();
                    break;
                case 3:
                    sql = "UPDATE local SET qtdevolumedm = ? where id = ?";
                    qtde = local.getQtdeVolumeDm();
                    break;
            }                            
        }       
       
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setDouble(1, qtde);
        p.setInt(2, local.getId());
        p.executeUpdate();
        p.close();
        super.con.close();
    }
  
   
   public Local getLocal(int id) throws SQLException
   {
        ArrayList<Local> locais = new ArrayList<Local>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM local where id = ?");
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Local local = new Local();
           local.setId(rs.getInt("id"));
           local.setDescricao(rs.getString("descricao"));
           local.setArea(rs.getDouble("area"));
           local.setAreaParcela(rs.getDouble("areaparcela"));
           local.setIdTipoEstimativa(rs.getInt("idtipoestimativa"));
           local.setIdTipoFloresta(rs.getInt("idtipofloresta"));
           local.setIdEspecie(rs.getInt("idespecie"));
           local.setIdFormacao(rs.getInt("idformacao"));
           local.setIdEspacamento(rs.getInt("idespacamento"));
           local.setIdDmTipoDistancia(rs.getInt("iddmtipodistancia"));
           local.setIdDmTipoPonderacao(rs.getInt("iddmtipoponderacao"));           
           local.setDmQtdeVizinhos(rs.getInt("dmqtdevizinhos"));                      
           local.setDmComLn(rs.getBoolean("dmcomln"));    
           local.setQtdeBiomassaEquacao(rs.getDouble("qtdebiomassaequacao"));
           local.setQtdeBiomassaDm(rs.getDouble("qtdebiomassadm"));
           local.setQtdeCarbonoEquacao(rs.getDouble("qtdecarbonoequacao"));
           local.setQtdeCarbonoDm(rs.getDouble("qtdecarbonodm"));
           local.setQtdeVolumeEquacao(rs.getDouble("qtdevolumeequacao"));
           local.setQtdeVolumeDm(rs.getDouble("qtdevolumedm"));           
           local.setIdMunicipio(rs.getInt("idmunicipio"));                      
           local.setLatitude(rs.getDouble("latitude"));                                 
           local.setLongitude(rs.getDouble("longitude"));                                            
           
           locais.add(local);
           
        }
        rs.close();
        p.close();
        super.con.close();        
        return locais.get(0);
   }
   
   public ArrayList<Local> listarLocais() throws Exception{
       
        ArrayList<Local> locais = new ArrayList<Local>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM local");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           Local local = new Local();
           local.setId(rs.getInt("id"));
           local.setDescricao(rs.getString("descricao"));
           local.setArea(rs.getDouble("area"));
           local.setAreaParcela(rs.getDouble("areaparcela"));
           local.setIdTipoEstimativa(rs.getInt("idtipoestimativa"));
           local.setIdTipoFloresta(rs.getInt("idtipofloresta"));
           local.setIdEspecie(rs.getInt("idespecie"));
           local.setIdFormacao(rs.getInt("idformacao"));
           local.setIdEspacamento(rs.getInt("idespacamento"));
           local.setIdDmTipoDistancia(rs.getInt("iddmtipodistancia"));
           local.setIdDmTipoPonderacao(rs.getInt("iddmtipoponderacao"));           
           local.setDmQtdeVizinhos(rs.getInt("dmqtdevizinhos"));                      
           local.setDmComLn(rs.getBoolean("dmcomln"));    
           local.setQtdeBiomassaEquacao(rs.getDouble("qtdebiomassaequacao"));
           local.setQtdeBiomassaDm(rs.getDouble("qtdebiomassadm"));
           local.setQtdeCarbonoEquacao(rs.getDouble("qtdecarbonoequacao"));
           local.setQtdeCarbonoDm(rs.getDouble("qtdecarbonodm"));
           local.setQtdeVolumeEquacao(rs.getDouble("qtdevolumeequacao"));
           local.setQtdeVolumeDm(rs.getDouble("qtdevolumedm"));           
           local.setIdMunicipio(rs.getInt("idmunicipio"));                      
           local.setLatitude(rs.getDouble("latitude"));                                 
           local.setLongitude(rs.getDouble("longitude"));                                            
           locais.add(local);
        }
        rs.close();
        p.close();
        super.con.close();        
        return locais;
    }
   
   public ArrayList<CoordenadaLocal> listarCoordenadasLocais() throws Exception{
       
        ArrayList<CoordenadaLocal> coordenadasLocais = new ArrayList<CoordenadaLocal>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM local where latitude IS NOT NULL and latitude <> 0");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           CoordenadaLocal coordenadaLocal = new CoordenadaLocal();
           coordenadaLocal.setIdLocal(rs.getInt("id"));           
           coordenadaLocal.setLatitude(rs.getDouble("latitude"));
           coordenadaLocal.setLongitude(rs.getDouble("longitude"));           
           coordenadasLocais.add(coordenadaLocal);
        }
        rs.close();
        p.close();
        super.con.close();        
        return coordenadasLocais;
    }
    
}

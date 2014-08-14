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
    
    public void cadastrar(Local local) throws SQLException, Exception
                        
    {
        String sql = "INSERT INTO local (descricao, "
                   +                    "area,"
                   +                    "areaparcela,"
                   +                    "idtipoestimativa,"
                   +                    "idformacao,"
                   +                    "idespacamento,"
                   +                    "idtrabalhocientifico,"
                   +                    "iddmtipodistancia,"
                   +                    "iddmtipoponderacao,"
                   +                    "dmqtdevizinhos,"
                   +                    "dmcomln,"                    
                   +                    "qtdebiomassaequacao,"
                   +                    "qtdebiomassadm,"
                   +                    "qtdecarbonoequacao,"
                   +                    "qtdecarbonodm,"
                   +                    "qtdevolumeequacao,"
                   +                    "qtdevolumedm"
                   +                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING local.id";
        PreparedStatement p = this.con.prepareStatement(sql);
        p.setString(1, local.getDescricao());
        p.setDouble(2, local.getArea());
        p.setDouble(3, local.getAreaParcela());
        p.setInt(4, local.getIdTipoEstimativa());
        p.setInt(5, local.getIdFormacao());
        p.setInt(6, local.getIdEspacamento());
        p.setInt(7, local.getIdTrabalhoCientifico());
        p.setInt(8, local.getIdDMTipoDistancia());            
        p.setInt(9, local.getIdDMTipoPonderacao());                        
        p.setInt(10, local.getDmQtdeVizinhos());                                    
        p.setBoolean(11, local.isDmComLn());  
        p.setDouble(12, local.getQtdeBiomassaEquacao());
        p.setDouble(13, local.getQtdeBiomassaDm());
        p.setDouble(14, local.getQtdeCarbonoEquacao());
        p.setDouble(15, local.getQtdeCarbonoDm());
        p.setDouble(16, local.getQtdeVolumeEquacao());
        p.setDouble(17, local.getQtdeVolumeDm());        
        int idLocal = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
          idLocal = rs.getInt(1);
        }
        
        //if (local.municipiosLocal.size()>0) {        
        if (local.municipiosLocal!=null) {                    
            for (MunicipioLocal ml : local.municipiosLocal) {
                p = this.con.prepareStatement("INSERT INTO municipiolocal (idlocal, "
                    +                                                      "idmunicipio,"
                    +                                                      "indprincipal"
                    +                                                      ") VALUES (?,?,?)");
                p.setInt(1, idLocal);
                p.setInt(2, ml.getIdMunicipio());
                p.setBoolean(3,ml.isIndPrincipal());
                p.executeUpdate();
            }
        }
        
        //if (local.coordenadasLocal.size()>0) {                
        if (local.coordenadasLocal!=null) {                            
            for (CoordenadaLocal cl : local.coordenadasLocal) {
                p = this.con.prepareStatement("INSERT INTO coordenadalocal (idlocal, "
                    +                                                      "latitude,"
                    +                                                      "longitude"
                    +                                                      ") VALUES (?,?,?)");
                p.setInt(1, idLocal);
                p.setDouble(2, cl.getLatitude());
                p.setDouble(3, cl.getLongitude());
                p.executeUpdate();
            }
            
        }
        p.close();
        super.con.close();        

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
                +                                                        "idformacao = ?,"
                +                                                        "idespacamento = ?,"
                +                                                        "idtrabalhocientifico = ?,"
                +                                                        "iddmtipodistancia = ?,"                
                +                                                        "iddmtipoponderacao = ?,"                                
                +                                                        "dmqtdevizinhos = ?,"                                
                +                                                        "dmcomln = ?,"                                                
                +                                                        "qtdebiomassaequacao = ?,"
                +                                                        "qtdebiomassadm = ?,"
                +                                                        "qtdecarbonoequacao = ?,"
                +                                                        "qtdecarbonodm = ?,"
                +                                                        "qtdevolumeequacao = ?,"
                +                                                        "qtdevolumedm = ?"
                +                                                        " WHERE id = ?");
        p.setString(1, local.getDescricao());
        p.setDouble(2, local.getArea());
        p.setDouble(3, local.getAreaParcela());
        p.setInt(4, local.getIdTipoEstimativa());
        p.setInt(5, local.getIdFormacao());
        p.setInt(6, local.getIdEspacamento());
        p.setInt(7, local.getIdTrabalhoCientifico());
        p.setInt(8, local.getIdDMTipoDistancia());            
        p.setInt(9, local.getIdDMTipoPonderacao());                        
        p.setInt(10, local.getDmQtdeVizinhos());                                    
        p.setBoolean(11, local.isDmComLn());                                                
        p.setDouble(12, local.getQtdeBiomassaEquacao());
        p.setDouble(13, local.getQtdeBiomassaDm());
        p.setDouble(14, local.getQtdeCarbonoEquacao());
        p.setDouble(15, local.getQtdeCarbonoDm());
        p.setDouble(16, local.getQtdeVolumeEquacao());
        p.setDouble(17, local.getQtdeVolumeDm());        
        p.setInt(18, local.getId());
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
           local.setIdFormacao(rs.getInt("idformacao"));
           local.setIdEspacamento(rs.getInt("idespacamento"));
           local.setIdTrabalhoCientifico(rs.getInt("idtrabalhocientifico"));
           local.setIdDMTipoDistancia(rs.getInt("iddmtipodistancia"));
           local.setIdDMTipoPonderacao(rs.getInt("iddmtipoponderacao"));           
           local.setDmQtdeVizinhos(rs.getInt("dmqtdevizinhos"));                      
           local.setDmComLn(rs.getBoolean("dmcomln"));    
           local.setQtdeBiomassaEquacao(rs.getDouble("qtdebiomassaequacao"));
           local.setQtdeBiomassaDm(rs.getDouble("qtdebiomassadm"));
           local.setQtdeCarbonoEquacao(rs.getDouble("qtdecarbonoequacao"));
           local.setQtdeCarbonoDm(rs.getDouble("qtdecarbonodm"));
           local.setQtdeVolumeEquacao(rs.getDouble("qtdevolumeequacao"));
           local.setQtdeVolumeDm(rs.getDouble("qtdevolumedm"));           
           
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
           local.setIdFormacao(rs.getInt("idformacao"));
           local.setIdEspacamento(rs.getInt("idespacamento"));
           local.setIdTrabalhoCientifico(rs.getInt("idtrabalhocientifico"));
           local.setIdDMTipoDistancia(rs.getInt("iddmtipodistancia"));
           local.setIdDMTipoPonderacao(rs.getInt("iddmtipoponderacao"));           
           local.setDmQtdeVizinhos(rs.getInt("dmqtdevizinhos"));                      
           local.setDmComLn(rs.getBoolean("dmcomln"));                                 
           local.setQtdeBiomassaEquacao(rs.getDouble("qtdebiomassaequacao"));
           local.setQtdeBiomassaDm(rs.getDouble("qtdebiomassadm"));
           local.setQtdeCarbonoEquacao(rs.getDouble("qtdecarbonoequacao"));
           local.setQtdeCarbonoDm(rs.getDouble("qtdecarbonodm"));
           local.setQtdeVolumeEquacao(rs.getDouble("qtdevolumeequacao"));
           local.setQtdeVolumeDm(rs.getDouble("qtdevolumedm"));           
           locais.add(local);
        }
        rs.close();
        p.close();
        super.con.close();        
        return locais;
    }
    
}

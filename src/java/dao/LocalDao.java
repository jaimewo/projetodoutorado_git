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
import model.LocalQuantidade;
import model.MunicipioLocal;
import dao.LocalQuantidadeDao;

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
                   +                    "dmcomln"                    
                   +                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?) RETURNING local.id";
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
        int idLocal = 0;
        ResultSet rs = p.executeQuery();
        if(rs.next()){
          idLocal = rs.getInt(1);
        }
        
        //INCLUI QUANTIDADE ZERADA NO LOCAL
        LocalQuantidadeDao localQuantidadeDao = new LocalQuantidadeDao();
        LocalQuantidade localQuantidade = new LocalQuantidade();
        for(int iVi=1;iVi<4;iVi++) { //Vi=Variavel de Interesse (1-Biomassa,2-Carbono,3-Volume)
            for(int iMc=1;iMc<3;iMc++) { //Mc=Método de Cálculo (1-Equacao,2-Data Mining)
                localQuantidade.setIdLocal(idLocal);
                localQuantidade.setIdVariavelInteresse(iVi);
                localQuantidade.setIdMetodoCalculo(iMc);
                localQuantidade.setQtde(0.0);
                localQuantidadeDao = new LocalQuantidadeDao();                
                localQuantidadeDao.cadastrar(localQuantidade);
            }
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
                +                                                        "dmcomln = ?"                                                
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
        p.setInt(12, local.getId());
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
           locais.add(local);
        }
        rs.close();
        p.close();
        super.con.close();        
        return locais;
    }
    
}

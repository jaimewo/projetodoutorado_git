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
import model.TrabalhoCientifico;

/**
 *
 * @author Jaime
 */
public class TrabalhoCientificoDao extends MainDao {
    
    
    public TrabalhoCientificoDao()
    {
        super();
    }
    
    public void cadastrar(TrabalhoCientifico trabalhoCientifico) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO trabalhocientifico (titulo,"
                    +                                                                       "ano,"
                    +                                                                       "idtipodisponibilidade,"
                    +                                                                       "idmetodoquantificacaobiomassa,"
                    +                                                                       "idmetodoquantificacaocarbono,"
                    +                                                                       "idautor )"
                    +                                                                       " VALUES (?,?,?,?,?,?)");
            p.setString(1, trabalhoCientifico.getTitulo());
            p.setInt(2, trabalhoCientifico.getAno());
            p.setInt(3, trabalhoCientifico.getIdTipoDisponibilidade());
            p.setInt(4, trabalhoCientifico.getIdMetodoQuantificacaoBiomassa());
            p.setInt(5, trabalhoCientifico.getIdMetodoQuantificacaoCarbono());
            p.setInt(6, trabalhoCientifico.getIdAutor());
            
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(TrabalhoCientifico trabalhoCientifico) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from trabalhocientifico where id = ?");
            p.setInt(1, trabalhoCientifico.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(TrabalhoCientifico trabalhoCientifico) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE trabalhocientifico SET titulo = ?, "
                +                                                                     "ano = ?, "
                +                                                                     "idtipodisponibilidade = ?, "
                +                                                                     "idmetodoquantificacaobiomassa = ?, "
                +                                                                     "idmetodoquantificacaocarbono = ?,"
                +                                                                     "idautor = ? "
                +                                                                     "where id = ?");
        p.setString(1, trabalhoCientifico.getTitulo());
        p.setInt(2, trabalhoCientifico.getAno());
        p.setInt(3, trabalhoCientifico.getIdTipoDisponibilidade());
        p.setInt(4, trabalhoCientifico.getIdMetodoQuantificacaoBiomassa());
        p.setInt(5, trabalhoCientifico.getIdMetodoQuantificacaoCarbono());
        p.setInt(6, trabalhoCientifico.getIdAutor());
        
        p.setInt(7, trabalhoCientifico.getId());
        p.executeUpdate();
        p.close();
    }
   
   public TrabalhoCientifico getTrabalhoCientifico(String id) throws SQLException
   {
        List<TrabalhoCientifico> trabalhosCientificos = new ArrayList<TrabalhoCientifico>();
        PreparedStatement p = this.con.prepareStatement("SELECT id,"
                +                                              "titulo,"
                +                                              "ano, "
                +                                              "idtipodisponibilidade,"
                +                                              "idmetodoquantificacaobiomassa,"
                +                                              "idmetodoquantificacaocarbono,"
                +                                              "idautor "
                +                                              "FROM trabalhocientifico where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TrabalhoCientifico trabalhoCientifico = new TrabalhoCientifico();
           trabalhoCientifico.setId(rs.getInt("id"));
           trabalhoCientifico.setTitulo(rs.getString("titulo"));
           trabalhoCientifico.setAno(rs.getInt("ano"));
           trabalhoCientifico.setIdTipoDisponibilidade(rs.getInt("idtipodisponibilidade"));
           trabalhoCientifico.setIdMetodoQuantificacaoBiomassa(rs.getInt("idmetodoquantificacaobiomassa"));
           trabalhoCientifico.setIdMetodoQuantificacaoCarbono(rs.getInt("idmetodoquantificacaocarbono"));
           trabalhoCientifico.setIdAutor(rs.getInt("idautor"));
           
           trabalhosCientificos.add(trabalhoCientifico);
        }
        rs.close();
        p.close();
        return trabalhosCientificos.get(0);
   }
   
   public List<TrabalhoCientifico> listarTrabalhosCientificos() throws Exception{
        List<TrabalhoCientifico> trabalhosCientificos = new ArrayList<TrabalhoCientifico>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM trabalhoCientifico");
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           TrabalhoCientifico trabalhoCientifico = new TrabalhoCientifico();
           trabalhoCientifico.setId(rs.getInt("id"));
           trabalhoCientifico.setTitulo(rs.getString("titulo"));
           trabalhoCientifico.setAno(rs.getInt("ano"));
           trabalhoCientifico.setIdTipoDisponibilidade(rs.getInt("idtipodisponibilidade"));
           trabalhoCientifico.setIdMetodoQuantificacaoBiomassa(rs.getInt("idmetodoquantificacaobiomassa"));
           trabalhoCientifico.setIdMetodoQuantificacaoCarbono(rs.getInt("idmetodoquantificacaocarbono"));
           trabalhoCientifico.setIdAutor(rs.getInt("idautor"));
           
           trabalhosCientificos.add(trabalhoCientifico);
        }
        rs.close();
        p.close();
        return trabalhosCientificos;
    }
    
}

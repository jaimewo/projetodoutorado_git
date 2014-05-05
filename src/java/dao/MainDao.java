/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulozeferino
 */
public class MainDao {
    
     protected Connection con = null;
     protected String ADDRESS = "localhost";
     protected String PORTA = "5433";
     protected String DATABASE_NAME = "JCARBON1";
     
     public MainDao()
     {
           try {
               
            Class.forName("org.postgresql.Driver");
//              this.con = DriverManager
//                    .getConnection(
//                    "jdbc:postgresql://"+ADDRESS+":"+PORTA+"/"+DATABASE_NAME,
//                    "postgres", "qwe123@");
              this.con = DriverManager
                    .getConnection(
                    "jdbc:postgresql://localhost:5432/JCarbon1",
                    "postgres", "root");                
              
              
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ArvoreAjusteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}

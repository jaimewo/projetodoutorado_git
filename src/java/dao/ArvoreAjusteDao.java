/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ArvoreAjuste;
import model.Local;
import model.Variavel;
import model.VariavelArvoreAjuste;

/**
 *
 * @author jaime
 */
public class ArvoreAjusteDao extends MainDao {
    

    
    
    public ArvoreAjusteDao()
    {
      super();
    }
     
    public void cadastrar(ArvoreAjuste arvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("INSERT INTO arvoreAjuste(idlocal,"
                    +                                                          "numarvoreAjuste,"
                    +                                                          "qtdebiomassaobs,"
                    +                                                          "qtdebiomassaest,"
                    +                                                          "qtdecarbonoobs,"
                    +                                                          "qtdecarbonoest,"
                    +                                                          "qtdevolumeobs,"
                    +                                                          "qtdevolumeest"
                    +                                                          ") VALUES (?,?,?,?,?,?,?,?)");
            p.setInt(1, arvoreAjuste.getIdLocal());
            p.setInt(2, arvoreAjuste.getNumArvore());
            p.setDouble(3, arvoreAjuste.getQtdeBiomassaObs());
            p.setDouble(4, arvoreAjuste.getQtdeBiomassaEst());
            p.setDouble(5, arvoreAjuste.getQtdeCarbonoObs());
            p.setDouble(6, arvoreAjuste.getQtdeCarbonoEst());
            p.setDouble(7, arvoreAjuste.getQtdeVolumeObs());
            p.setDouble(8, arvoreAjuste.getQtdeVolumeEst());
            p.executeUpdate();
            p.close();
    }
    
    
    public void deletar(ArvoreAjuste arvoreAjuste) throws SQLException
    {
            PreparedStatement p = this.con.prepareStatement("DELETE from arvoreAjuste where id = ?");
            p.setInt(1, arvoreAjuste.getId());
            p.executeUpdate();
            p.close();
        
    }
    
   public void update(ArvoreAjuste arvoreAjuste) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvoreAjuste SET idlocal = ?,"
                +                                                         " numarvore = ?,"
                +                                                         " qtdebiomassaobs = ?,"
                +                                                         " qtdebiomassaest = ?,"
                +                                                         " qtdecarbonoobs = ?,"
                +                                                         " qtdecarbonoest = ?,"
                +                                                         " qtdevolumeobs = ?,"
                +                                                         " qtdevolumeest = ?"
                +                                                         " where id = ?");
        p.setInt(1, arvoreAjuste.getIdLocal());
        p.setInt(2, arvoreAjuste.getNumArvore());
        p.setDouble(3, arvoreAjuste.getQtdeBiomassaObs());
        p.setDouble(4, arvoreAjuste.getQtdeBiomassaEst());
        p.setDouble(5, arvoreAjuste.getQtdeCarbonoObs());
        p.setDouble(6, arvoreAjuste.getQtdeCarbonoEst());
        p.setDouble(7, arvoreAjuste.getQtdeVolumeObs());
        p.setDouble(8, arvoreAjuste.getQtdeVolumeEst());

        p.setInt(9, arvoreAjuste.getId());
        p.executeUpdate();
        p.close();
    }
   
   public ArvoreAjuste getArvoreAjuste(String id) throws SQLException
   {
        List<ArvoreAjuste> arvoreAjustes = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where id = ?");
        p.setInt(1, Integer.parseInt(id));
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvore(rs.getInt("numarvore"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvoreAjuste.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvoreAjuste.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvoreAjustes.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        return arvoreAjustes.get(0);
   }
   public ArvoreAjuste getArvoreAjuste(Local local, int numArvore) throws SQLException
   {
        List<ArvoreAjuste> arvoreAjustes = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where idlocal = ? AND numarvore = ?");
        
        p.setInt(1, local.getId());
        p.setInt(2, numArvore);
        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvore(rs.getInt("numarvore"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvoreAjuste.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvoreAjuste.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvoreAjustes.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        return arvoreAjustes.get(0);
   }
   
   public ArrayList<ArvoreAjuste> listarArvoresAjuste(int idLocal) throws Exception{
        ArrayList<ArvoreAjuste> arvoresAjuste = new ArrayList<ArvoreAjuste>();
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste WHERE idlocal = ?");
        p.setInt(1, idLocal);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvore(rs.getInt("numarvore"));
           arvoreAjuste.setQtdeBiomassaObs(rs.getDouble("qtdebiomassaobs"));
           arvoreAjuste.setQtdeBiomassaEst(rs.getDouble("qtdebiomassaest"));
           arvoreAjuste.setQtdeCarbonoObs(rs.getDouble("qtdecarbonoobs"));
           arvoreAjuste.setQtdeCarbonoEst(rs.getDouble("qtdecarbonoest"));
           arvoreAjuste.setQtdeVolumeObs(rs.getDouble("qtdevolumeobs"));
           arvoreAjuste.setQtdeVolumeEst(rs.getDouble("qtdevolumeest"));
           arvoresAjuste.add(arvoreAjuste);
        }
        rs.close();
        p.close();
        return arvoresAjuste;
    }
    public void importar(Local local) throws SQLException
    {
        
        try {

            //Criação de um buffer para a ler de uma stream
            BufferedReader StrR = new BufferedReader(new FileReader("c:\\tabela.csv"));

            String Str;
            String[] linha;
            int numLinha = 0;
            int numCelula = 0;
            String numArvoreStr = "";
            String qtdeBiomassaObsStr = "";
            String qtdeCarbonoObsStr = "";
            String qtdeVolumeObsStr = "";
            
            ArrayList<Variavel> variaveisLidas = new ArrayList<Variavel>();
            ArrayList<String> valorVariaveis = new ArrayList<String>();
            VariavelDao variavelDao = new VariavelDao();

            while((Str = StrR.readLine())!= null){
                //Aqui usamos o método split que divide a linha lida em um array de String
                //passando como parametro o divisor ";".
                linha = Str.split(";");
                numLinha++;
                
                //O foreach é usadao para imprimir cada célula do array de String.
                for (String celula : linha) {
                    numCelula++;
                    if(numLinha==1) { //cabeçalho
                        switch (numCelula) {
                        case 1: // "Arvore"
                             break;
                        case 2: // "Biomassa"
                             break;
                        case 3: // "Carbono"
                             break;
                        case 4: // "Volume"
                             break;
                        default: // Variáveis
                             Variavel variavel = variavelDao.getVariavelComSigla(celula);
                             variaveisLidas.add(variavel);
                             break;                        
                        }
                    } else { //demais linhas
                        switch (numCelula) {
                        case 1: // Número da Árvore
                             numArvoreStr = celula;
                             break;
                        case 2: // Valor da Biomassa
                             qtdeBiomassaObsStr = celula;
                             break;
                        case 3: // Valor do Carbono"
                             qtdeCarbonoObsStr = celula;
                             break;
                        case 4: // Valor do Volume
                             qtdeVolumeObsStr = celula;
                             break;
                        default: // Valor das Variáveis
                             valorVariaveis.add(celula);
                             break;                        
                        }
                    }

                }
                // Insere VariavelArvoreAjuste
                ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
                arvoreAjuste.setIdLocal(local.getId());
                arvoreAjuste.setNumArvore(Integer.parseInt(numArvoreStr));
                arvoreAjuste.setQtdeBiomassaObs(Double.parseDouble(qtdeBiomassaObsStr));
                arvoreAjuste.setQtdeCarbonoObs(Double.parseDouble(qtdeCarbonoObsStr));
                arvoreAjuste.setQtdeVolumeObs(Double.parseDouble(qtdeVolumeObsStr));
                cadastrar(arvoreAjuste);
                arvoreAjuste = getArvoreAjuste(local,Integer.parseInt(numArvoreStr));
                int i=0;
                for (Variavel variavelLida: variaveisLidas) {
                    VariavelArvoreAjuste variavelArvoreAjuste = new VariavelArvoreAjuste();
                    variavelArvoreAjuste.setIdArvoreAjuste(arvoreAjuste.getId());
                    variavelArvoreAjuste.setIdVariavel(variavelLida.getId());
                    variavelArvoreAjuste.setValor(Double.parseDouble(valorVariaveis.get(i)));
                    variavelArvoreAjuste.setVariavel(variavelLida);
                    VariavelArvoreAjusteDao variavelArvoreAjusteDao = new VariavelArvoreAjusteDao();
                    variavelArvoreAjusteDao.cadastrar(variavelArvoreAjuste);
                    i++;
                }
            }
            
            //Fechamos o buffer
            StrR.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;  
import jxl.write.biff.RowsExceededException;  
import model.ArvoreAjuste;
import model.Local;  
import model.Variavel;  
import model.VariavelArvoreAjuste;

/**
 *
 * @author jaime
 */
public class ArvoreAjusteDao extends MainDao {

private static Workbook planilha; // objeto que receberá um instancia da planilha estudada
private static Sheet aba; // objeto que será a aba
private static File arquivo; // arquivo .xls que será lido
 

    
    
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
            p.setInt(2, arvoreAjuste.getNumArvoreAjuste());
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
    public void deletarLocal(Local local) throws SQLException
    {
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where idlocal = ?");
        
        p.setInt(1, local.getId());
        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           int idArvoreAjuste = rs.getInt("id");
           
           VariavelArvoreAjusteDao variavelArvoreAjusteDao = new VariavelArvoreAjusteDao();
           variavelArvoreAjusteDao.deletarArvoreAjuste(idArvoreAjuste);
        }
        rs.close();
        
        p = this.con.prepareStatement("DELETE from arvoreAjuste where idlocal = ?");
        p.setInt(1, local.getId());
        p.executeUpdate();
        p.close();
    }
    
   public void update(ArvoreAjuste arvoreAjuste) throws Exception 
   {
        PreparedStatement p = this.con.prepareStatement("UPDATE arvoreAjuste SET idlocal = ?,"
                +                                                         " numarvoreajuste = ?,"
                +                                                         " qtdebiomassaobs = ?,"
                +                                                         " qtdebiomassaest = ?,"
                +                                                         " qtdecarbonoobs = ?,"
                +                                                         " qtdecarbonoest = ?,"
                +                                                         " qtdevolumeobs = ?,"
                +                                                         " qtdevolumeest = ?"
                +                                                         " where id = ?");
        p.setInt(1, arvoreAjuste.getIdLocal());
        p.setInt(2, arvoreAjuste.getNumArvoreAjuste());
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
           arvoreAjuste.setNumArvoreAjuste(rs.getInt("numarvoreajuste"));
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
        PreparedStatement p = this.con.prepareStatement("SELECT * FROM arvoreajuste where idlocal = ? AND numarvoreajuste = ?");
        
        p.setInt(1, local.getId());
        p.setInt(2, numArvore);
        
        ResultSet rs = p.executeQuery();
        while(rs.next()){
           ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
           arvoreAjuste.setId(rs.getInt("id"));
           arvoreAjuste.setIdLocal(rs.getInt("idlocal"));
           arvoreAjuste.setNumArvoreAjuste(rs.getInt("numarvoreajuste"));
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
           arvoreAjuste.setNumArvoreAjuste(rs.getInt("numarvoreajuste"));
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
    public void importar(Local local) throws SQLException, BiffException
    {
        deletarLocal(local);

        try {
    
            //arquivo = new File("c:\\teste\\arvoreajuste2003.xls");
            arquivo = new File("C:\\Users\\jaimewo\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\arvoreajuste2003.xls");

            // instancia a planilha
            planilha = Workbook.getWorkbook(arquivo);

            //Obendo as Abas da planilha
            Sheet[] abas = planilha.getSheets();

            aba = planilha.getSheet(0); // pega a primeira aba, ou seja, aba de indice 0.

            String[][] matriz = new String[aba.getRows()][aba.getColumns()];

            //matriz.length -> representa as linhas da matriz
            //matriz[0].length -> pega o tamanho da linha [0], ou seja, pega o número de colunas
            Cell[] cel; // instancia um array de cÃ©lulas que irá auxiliar no povoamento da matriz

            for (int linha = 0; linha < matriz.length; linha++) {
                cel = aba.getRow(linha);
                for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                    // pega os dados da celula cel[j] e adiciona na matriz
                    matriz[linha][coluna] = cel[coluna].getContents();
                }
            }   
            
            VariavelDao variavelDao = new VariavelDao();
            VariavelArvoreAjusteDao variavelArvoreAjusteDao = new VariavelArvoreAjusteDao();
            ArrayList<Variavel> variaveisLidas = new ArrayList<Variavel>();
            int numArvore = 0;
            double qtdeBiomassaObs = 0.0;
            double qtdeCarbonoObs = 0.0;
            double qtdeVolumeObs = 0.0;
            
            for (int linha = 0; linha < matriz.length; linha++) {
                ArrayList<Double> valorVariaveis = new ArrayList<Double>(); 
                for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                    if (linha==0) {
                        switch (coluna) {
                        case 0: // "Arvore"
                             break;
                        case 1: // "Biomassa"
                            break;
                        case 2: // "Carbono"
                            break;
                        case 3: // "Volume"
                            break;
                        default: // Variáveis
                            Variavel variavel = variavelDao.getVariavelComSigla(matriz[linha][coluna]);
                            variaveisLidas.add(variavel);
                            break;                        
                        }
                   } else { //demais linhas
                        switch (coluna) {
                        case 0: // Número da Árvore
                             numArvore = Integer.parseInt(matriz[linha][coluna]);
                             break;
                        case 1: // Valor da Biomassa
                             qtdeBiomassaObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 2: // Valor do Carbono"
                             qtdeCarbonoObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 3: // Valor do Volume
                             qtdeVolumeObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));;
                             break;
                        default: // Valor das Variáveis
                             valorVariaveis.add(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             break;                        
                        }
                    }
                }
                if (linha>0) {
                    // Insere VariavelArvoreAjuste
                    ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
                    arvoreAjuste.setIdLocal(local.getId());
                    arvoreAjuste.setNumArvoreAjuste(numArvore);
                    arvoreAjuste.setQtdeBiomassaObs(qtdeBiomassaObs);
                    arvoreAjuste.setQtdeCarbonoObs(qtdeCarbonoObs);
                    arvoreAjuste.setQtdeVolumeObs(qtdeVolumeObs);
                    cadastrar(arvoreAjuste);
                    arvoreAjuste = getArvoreAjuste(local,numArvore);
                    int i=0;
                    for (Variavel variavelLida: variaveisLidas) {
                        VariavelArvoreAjuste variavelArvoreAjuste = new VariavelArvoreAjuste();
                        variavelArvoreAjuste.setIdArvoreAjuste(arvoreAjuste.getId());
                        variavelArvoreAjuste.setIdVariavel(variavelLida.getId());
                        variavelArvoreAjuste.setValor(valorVariaveis.get(i));
                        variavelArvoreAjuste.setVariavel(variavelLida);
                        
                        variavelArvoreAjusteDao.cadastrar(variavelArvoreAjuste);
                        i++;
                    }
                }
            }
        
        
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    public void gravarPlanilhaExemplo(Local local) throws SQLException, BiffException, IOException
    {
//http://jmmwrite.wordpress.com/2011/02/09/gerar-xls-planilha-excell-com-java/           
    try {
        WritableWorkbook workbook = Workbook.createWorkbook(new File("c:\\teste\\arquivo.xls")); 
        WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
 
        // work with coordinates (from 0,0 to N,k) -> COL, LINE
        Label label = new Label(0, 0, "Celula 0,0");
        sheet.addCell(label);
 
        label = new Label(0, 1, "Celula 0,1");
        sheet.addCell(label);
 
        label = new Label(0, 2, "Celula 0,2");
        sheet.addCell(label);
 
        Number number = new Number(3, 4, 3.1459);
        sheet.addCell(number);
 
        workbook.write();
        workbook.close();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (RowsExceededException e) {
        e.printStackTrace();
    } catch (WriteException e) {
        e.printStackTrace();
    }
    
      
        
        
        
        
/*        
        try {
    
            //arquivo = new File("c:\\teste\\arvoreajuste2003.xls");
            arquivo = new File("C:\\Users\\jaimewo\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\arvoreajuste2003.xls");

            // instancia a planilha
            planilha = Workbook.getWorkbook(arquivo);

            //Obendo as Abas da planilha
            Sheet[] abas = planilha.getSheets();

            aba = planilha.getSheet(0); // pega a primeira aba, ou seja, aba de indice 0.

            String[][] matriz = new String[aba.getRows()][aba.getColumns()];

            //matriz.length -> representa as linhas da matriz
            //matriz[0].length -> pega o tamanho da linha [0], ou seja, pega o número de colunas
            Cell[] cel; // instancia um array de cÃ©lulas que irá auxiliar no povoamento da matriz

            for (int linha = 0; linha < matriz.length; linha++) {
                cel = aba.getRow(linha);
                for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                    // pega os dados da celula cel[j] e adiciona na matriz
                    matriz[linha][coluna] = cel[coluna].getContents();
                }
            }   
            
            VariavelDao variavelDao = new VariavelDao();
            VariavelArvoreAjusteDao variavelArvoreAjusteDao = new VariavelArvoreAjusteDao();
            ArrayList<Variavel> variaveisLidas = new ArrayList<Variavel>();
            int numArvore = 0;
            double qtdeBiomassaObs = 0.0;
            double qtdeCarbonoObs = 0.0;
            double qtdeVolumeObs = 0.0;
            
            for (int linha = 0; linha < matriz.length; linha++) {
                ArrayList<Double> valorVariaveis = new ArrayList<Double>(); 
                for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                    if (linha==0) {
                        switch (coluna) {
                        case 0: // "Arvore"
                             break;
                        case 1: // "Biomassa"
                            break;
                        case 2: // "Carbono"
                            break;
                        case 3: // "Volume"
                            break;
                        default: // Variáveis
                            Variavel variavel = variavelDao.getVariavelComSigla(matriz[linha][coluna]);
                            variaveisLidas.add(variavel);
                            break;                        
                        }
                   } else { //demais linhas
                        switch (coluna) {
                        case 0: // Número da Árvore
                             numArvore = Integer.parseInt(matriz[linha][coluna]);
                             break;
                        case 1: // Valor da Biomassa
                             qtdeBiomassaObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 2: // Valor do Carbono"
                             qtdeCarbonoObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 3: // Valor do Volume
                             qtdeVolumeObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));;
                             break;
                        default: // Valor das Variáveis
                             valorVariaveis.add(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             break;                        
                        }
                    }
                }
                if (linha>0) {
                    // Insere VariavelArvoreAjuste
                    ArvoreAjuste arvoreAjuste = new ArvoreAjuste();
                    arvoreAjuste.setIdLocal(local.getId());
                    arvoreAjuste.setNumArvoreAjuste(numArvore);
                    arvoreAjuste.setQtdeBiomassaObs(qtdeBiomassaObs);
                    arvoreAjuste.setQtdeCarbonoObs(qtdeCarbonoObs);
                    arvoreAjuste.setQtdeVolumeObs(qtdeVolumeObs);
                    cadastrar(arvoreAjuste);
                    arvoreAjuste = getArvoreAjuste(local,numArvore);
                    int i=0;
                    for (Variavel variavelLida: variaveisLidas) {
                        VariavelArvoreAjuste variavelArvoreAjuste = new VariavelArvoreAjuste();
                        variavelArvoreAjuste.setIdArvoreAjuste(arvoreAjuste.getId());
                        variavelArvoreAjuste.setIdVariavel(variavelLida.getId());
                        variavelArvoreAjuste.setValor(valorVariaveis.get(i));
                        variavelArvoreAjuste.setVariavel(variavelLida);
                        
                        variavelArvoreAjusteDao.cadastrar(variavelArvoreAjuste);
                        i++;
                    }
                }
            }
        
        
        } catch (Exception e) {

            e.printStackTrace();

        }
  */
    }

}


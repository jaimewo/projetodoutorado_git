/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreAjusteDao;
import dao.ArvoreDao;
import dao.ParcelaDao;
import dao.VariavelArvoreAjusteDao;
import dao.VariavelDao;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author jaime
 */
public class Parcela extends Model  {
    
    
    private int id;
    private int idLocal;
    private int numParcela;
    private double areaParcela;
    private double qtdeBiomassa;
    private double qtdeCarbono;
    private double qtdeVolume;
    
    public ArrayList<Arvore> arvores;
    
    public Parcela()
    {
        this.idLocal = 0;
        this.numParcela = 0;
        this.areaParcela = 0.0;
    }
    
    public String getIdString()
    {
        return String.valueOf(this.getId());
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(int numParcela) {
        this.numParcela = numParcela;
    }

    public double getAreaParcela() {
        return areaParcela;
    }

    public void setAreaParcela(double areaParcela) {
        this.areaParcela = areaParcela;
    }

    public double getQtdeBiomassa() {
        return qtdeBiomassa;
    }

    public void setQtdeBiomassa(double qtdeBiomassa) {
        this.qtdeBiomassa = qtdeBiomassa;
    }

    public double getQtdeCarbono() {
        return qtdeCarbono;
    }

    public void setQtdeCarbono(double qtdeCarbono) {
        this.qtdeCarbono = qtdeCarbono;
    }

    public double getQtdeVolume() {
        return qtdeVolume;
    }

    public void setQtdeVolume(double qtdeVolume) {
        this.qtdeVolume = qtdeVolume;
    }

    public ArrayList<Arvore> getArvores() throws Exception {
        ArrayList<Arvore> arvores = new ArrayList<Arvore>();
        ArvoreDao arvoreDao = new ArvoreDao();
        
        arvores = arvoreDao.listarArvores(this.id);
        
        for (Arvore arvore: arvores) {
            arvore.variaveisArvore = arvore.getVariaveisArvore();
        }
        
        return arvores;
    }

    public void setArvores(ArrayList<Arvore> arvores) {
        this.arvores = arvores;
    }

    public void calculaQtde(Local local) throws Exception {
        
        Double qtdeEstimada = 0.0;

        ParcelaDao parcelaDao = new ParcelaDao();
        ArvoreDao arvoreDao = new ArvoreDao();
        
        for (int iVi=1;iVi<4;iVi++) {
            int idVariavelInteresse = iVi;
        
            for (Arvore arvore: arvores) {
                switch (idVariavelInteresse) {
                    case 1: //Biomassa
                        qtdeEstimada = arvore.calculaQtdeEstimada(local,idVariavelInteresse);
                        arvore.setQtdeBiomassaEst(qtdeEstimada);
                        arvoreDao.updateBiomassa(arvore);                                
                        this.qtdeBiomassa += qtdeEstimada;
                        break;
                    case 2: //Carbono
                        qtdeEstimada = arvore.calculaQtdeEstimada(local,idVariavelInteresse);
                        arvore.setQtdeCarbonoEst(qtdeEstimada);
                        arvoreDao.updateCarbono(arvore);                                
                        this.qtdeCarbono += qtdeEstimada;
                        break;
                    case 3: //Volume
                        qtdeEstimada = arvore.calculaQtdeEstimada(local,idVariavelInteresse);
                        arvore.setQtdeVolumeEst(qtdeEstimada);
                        arvoreDao.updateVolume(arvore);                                
                        this.qtdeVolume += qtdeEstimada;
                        break;
                }
            }
            switch (idVariavelInteresse) {
                case 1: //Biomassa
                    parcelaDao.updateBiomassa(this);
                    break;
                case 2: //Carbono
                    parcelaDao.updateCarbono(this);
                    break;
                case 3: //Volume
                    parcelaDao.updateVolume(this);
                    break;
            }
        }
    }

    public void importarPlanilha(Local local) throws SQLException, BiffException
    {
        ParcelaDao parcelaDao = new ParcelaDao();
        parcelaDao.deletarParcela(local);

        Workbook planilha; // objeto que receberá um instancia da planilha estudada
        Sheet aba; // objeto que será a aba
        File arquivo; // arquivo .xls que será lido
 
        try {
    
            arquivo = new File("c:\\teste\\parcela.xls");
            //arquivo = new File("C:\\Users\\jaimewo\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\parcela.xls");

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

            if (!consistePlanilhaImportada(local,matriz)) {
                //Montar msg erro para a Controller
                return;
            }
            
            int numArvore = 0;
            double qtdeBiomassaObs = 0.0;
            double qtdeCarbonoObs = 0.0;
            double qtdeVolumeObs = 0.0;
            
            for (int linha = 0; linha < matriz.length; linha++) {
                for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                    if (linha>0) {
                        switch (coluna) {
                        case 0: // Número da Parcela
                             numParcela = Integer.parseInt(matriz[linha][coluna]);
                             break;
                        case 1: // Área da Parcela
                             areaParcela = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 2: // Valor da Biomassa
                             qtdeBiomassa = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 3: // Valor do Carbono"
                             qtdeCarbono = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 4: // Valor do Volume
                             qtdeVolume = Double.parseDouble(matriz[linha][coluna].replace(",","."));;
                             break;
                        }
                    }
                }
                if (linha>0) {
                    // Insere Parcela
                    idLocal = local.getId();
                    ArrayList<Arvore> arvoresParcela = new ArrayList<Arvore>();
                    arvores = arvoresParcela;
                    parcelaDao.cadastrar(this);
                }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean consistePlanilhaImportada(Local local, String[][] matriz) throws SQLException, Exception {
            
        for (int coluna = 0; coluna < matriz[0].length; coluna++) {
            int colunaAux=coluna+1;
            switch (coluna) {
              case 0: // "Parcela"
                   if (!matriz[0][0].equalsIgnoreCase("Parcela")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Parcela");
                       return false;
                   }
                   break;
              case 1: // "Area"
                   if (!matriz[0][1].equalsIgnoreCase("Area")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Area");
                       return false;
                   }
                   break;
              case 2: // "Biomassa"
                   if (!matriz[0][2].equalsIgnoreCase("Biomassa")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Biomassa");
                       return false;
                   }
                   break;
              case 3: // "Carbono"
                   if (!matriz[0][3].equalsIgnoreCase("Carbono")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Carbono");
                       return false;
                   }
                   break;
              case 4: // "Volume"
                   if (!matriz[0][4].equalsIgnoreCase("Volume")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Volume");
                       return false;
                   }
                   break;
            }
        }           
        
        return true;
    }    
    
    
    public void gravarPlanilhaExemplo(Local local) throws SQLException, BiffException, IOException, Exception
    {
//http://jmmwrite.wordpress.com/2011/02/09/gerar-xls-planilha-excell-com-java/        
    try {
        WritableWorkbook workbook = Workbook.createWorkbook(new File("c:\\teste\\parcelaExemplo.xls")); 
        WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
 
        // work with coordinates (from 0,0 to N,k) -> COL, LINE
        Label label = new Label(0, 0, "Parcela");
        sheet.addCell(label);
        jxl.write.Number number = new jxl.write.Number(0, 1, 1);
        sheet.addCell(number);
 
        label = new Label(1, 0, "Área");
        sheet.addCell(label);
        number = new jxl.write.Number(1, 1, 10);
        sheet.addCell(number);

        label = new Label(2, 0, "Biomassa");
        sheet.addCell(label);
        number = new jxl.write.Number(2, 1, 10);
        sheet.addCell(number);
 
        label = new Label(3, 0, "Carbono");
        sheet.addCell(label);
        number = new jxl.write.Number(3, 1, 10);
        sheet.addCell(number);
 
        label = new Label(4, 0, "Volume");
        sheet.addCell(label);
        number = new jxl.write.Number(4, 1, 10);
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
    
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreAjusteDao;
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
public class ArvoreAjuste extends Model  {
    
    
    public int id;
    public int idLocal;
    public int numArvoreAjuste;
    public double qtdeBiomassaObs;
    public double qtdeBiomassaEst;
    public double qtdeCarbonoObs;
    public double qtdeCarbonoEst;
    public double qtdeVolumeObs;
    public double qtdeVolumeEst;
        
    public ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste;
    
    public ArvoreAjuste()
    {
        this.idLocal = 0;
        this.numArvoreAjuste = 0;
        this.qtdeBiomassaObs = 0.0;
        this.qtdeBiomassaEst = 0.0;
        this.qtdeCarbonoObs = 0.0;
        this.qtdeCarbonoEst = 0.0;
        this.qtdeVolumeObs = 0.0;
        this.qtdeVolumeEst = 0.0;
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

    public int getNumArvoreAjuste() {
        return numArvoreAjuste;
    }

    public void setNumArvoreAjuste(int numArvoreAjuste) {
        this.numArvoreAjuste = numArvoreAjuste;
    }

    public double getQtdeBiomassaObs() {
        return qtdeBiomassaObs;
    }

    public void setQtdeBiomassaObs(double qtdeBiomassaObs) {
        this.qtdeBiomassaObs = qtdeBiomassaObs;
    }

    public double getQtdeBiomassaEst() {
        return qtdeBiomassaEst;
    }

    public void setQtdeBiomassaEst(double qtdeBiomassaEst) {
        this.qtdeBiomassaEst = qtdeBiomassaEst;
    }

    public double getQtdeCarbonoObs() {
        return qtdeCarbonoObs;
    }

    public void setQtdeCarbonoObs(double qtdeCarbonoObs) {
        this.qtdeCarbonoObs = qtdeCarbonoObs;
    }

    public double getQtdeCarbonoEst() {
        return qtdeCarbonoEst;
    }

    public void setQtdeCarbonoEst(double qtdeCarbonoEst) {
        this.qtdeCarbonoEst = qtdeCarbonoEst;
    }

    public double getQtdeVolumeObs() {
        return qtdeVolumeObs;
    }

    public void setQtdeVolumeObs(double qtdeVolumeObs) {
        this.qtdeVolumeObs = qtdeVolumeObs;
    }

    public double getQtdeVolumeEst() {
        return qtdeVolumeEst;
    }

    public void setQtdeVolumeEst(double qtdeVolumeEst) {
        this.qtdeVolumeEst = qtdeVolumeEst;
    }
 
    public ArrayList<VariavelArvoreAjuste> getVariaveisArvoreAjuste() throws Exception {
        ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();
        VariavelArvoreAjusteDao variaveisArvoreAjusteDao = new VariavelArvoreAjusteDao();
        variaveisArvoreAjuste = variaveisArvoreAjusteDao.listarVariaveisArvoreAjuste(this.id);     
        
        return variaveisArvoreAjuste;
    }   
    
    public void importarPlanilha(Local local) throws SQLException, BiffException
    {
        ArvoreAjusteDao arvoreAjusteDao = new ArvoreAjusteDao();
        arvoreAjusteDao.deletarLocal(local);

        Workbook planilha; // objeto que receberá um instancia da planilha estudada
        Sheet aba; // objeto que será a aba
        File arquivo; // arquivo .xls que será lido
 
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
                    
                    arvoreAjusteDao.cadastrar(arvoreAjuste);
                    
                    arvoreAjuste = arvoreAjusteDao.getArvoreAjuste(local,numArvore);
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
    
    public void gravarPlanilhaExemplo(Local local) throws SQLException, BiffException, IOException, Exception
    {
//http://jmmwrite.wordpress.com/2011/02/09/gerar-xls-planilha-excell-com-java/        
    try {
        WritableWorkbook workbook = Workbook.createWorkbook(new File("c:\\teste\\arquivo.xls")); 
        WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
 
        // work with coordinates (from 0,0 to N,k) -> COL, LINE
        Label label = new Label(0, 0, "Árvore");
        sheet.addCell(label);
        jxl.write.Number number = new jxl.write.Number(0, 1, 1);
        sheet.addCell(number);
 
        label = new Label(1, 0, "Biomassa");
        sheet.addCell(label);
        number = new jxl.write.Number(1, 1, 10);
        sheet.addCell(number);
 
        label = new Label(2, 0, "Carbono");
        sheet.addCell(label);
        number = new jxl.write.Number(2, 1, 10);
        sheet.addCell(number);
 
        label = new Label(3, 0, "Volume");
        sheet.addCell(label);
        number = new jxl.write.Number(3, 1, 10);
        sheet.addCell(number);
 
        ArrayList<Variavel> variaveis = new ArrayList<Variavel>();
        ArrayList<String> siglasVariavel = new ArrayList<String>();
        
        ArrayList<Equacao> equacoesTrabalho = new ArrayList<Equacao>();
        equacoesTrabalho = local.getTrabalhoCientifico().getEquacoesTrabalho();
        for(Equacao equacao: equacoesTrabalho) {
            variaveis = equacao.getVariaveis();
            for(Variavel variavel: variaveis) {
                boolean achou = false;
                for (String sigla: siglasVariavel){
                    if(sigla.equalsIgnoreCase(variavel.getSigla())) {
                        achou = true;
                    }
                }            
                if (!achou) {
                    siglasVariavel.add(variavel.getSigla());
                }
            }
        }
        
        int i=4;
        
        for(String sigla: siglasVariavel) {
            label = new Label(i, 0, sigla);
            sheet.addCell(label);
            number = new jxl.write.Number(i, 1, 10);
            sheet.addCell(number);
            i++;
        }
 
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreAjusteDao;
import dao.ArvoreAjusteQuantidadeDao;
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
    
    public double qtdeObs;
    public double qtdeEst;    
        
    public ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste;
        
    public ArrayList<ArvoreAjusteQuantidade> arvoresAjusteQuantidade;
    
    public ArvoreAjuste()
    {
        this.idLocal = 0;
        this.numArvoreAjuste = 0;
        this.qtdeObs = 0.0;
        this.qtdeEst = 0.0;        
        
        this.variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();
        this.arvoresAjusteQuantidade = new ArrayList<ArvoreAjusteQuantidade>();        
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
 
    public ArrayList<VariavelArvoreAjuste> getVariaveisArvoreAjuste() throws Exception {
        ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste = new ArrayList<VariavelArvoreAjuste>();
        VariavelArvoreAjusteDao variaveisArvoreAjusteDao = new VariavelArvoreAjusteDao();
        
        variaveisArvoreAjuste = variaveisArvoreAjusteDao.listarVariaveisArvoreAjuste(this.id);     
        
        return variaveisArvoreAjuste;
    }   
 
    public ArrayList<ArvoreAjusteQuantidade> getArvoresAjusteQuantidade(int idVariavelInteresse,int idMetodoCalculo) throws Exception {
        
        ArrayList<ArvoreAjusteQuantidade> arvoresAjusteQuantidade = new ArrayList<ArvoreAjusteQuantidade>();
        ArvoreAjusteQuantidadeDao arvoreAjusteQuantidadeDao = new ArvoreAjusteQuantidadeDao();
        arvoresAjusteQuantidade = arvoreAjusteQuantidadeDao.listarArvoresAjusteQuantidade(this.id,idVariavelInteresse,idMetodoCalculo);     
        
        return arvoresAjusteQuantidade;
    }   

    public double getQtdeObs(int idVariavelInteresse,int idMetodoCalculo) throws SQLException {
        ArvoreAjusteQuantidadeDao arvoreAjusteQuantidadeDao = new ArvoreAjusteQuantidadeDao();
        qtdeObs = arvoreAjusteQuantidadeDao.getQtdeObs(this.id,idVariavelInteresse,idMetodoCalculo);  
        
        return qtdeObs;
    }

    public double getQtdeEst(int idVariavelInteresse,int idMetodoCalculo) throws SQLException {
        ArvoreAjusteQuantidadeDao arvoreAjusteQuantidadeDao = new ArvoreAjusteQuantidadeDao();
        qtdeEst = arvoreAjusteQuantidadeDao.getQtdeEst(this.id,idVariavelInteresse,idMetodoCalculo);  
        
        return qtdeEst;
    }

    public void importarPlanilha(Local local) throws SQLException, BiffException
    {
        ArvoreAjusteDao arvoreAjusteDao = new ArvoreAjusteDao();
        arvoreAjusteDao.deletarLocal(local);

        Workbook planilha; // objeto que receberá um instancia da planilha estudada
        Sheet aba; // objeto que será a aba
        File arquivo; // arquivo .xls que será lido
 
        try {
    
            arquivo = new File("c:\\teste\\arvoreajuste.xls");
            //arquivo = new File("C:\\Users\\jaimewo\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\arvoreajuste2003.xls");

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
                    
            ArrayList<Variavel> variaveisLidas = new ArrayList<Variavel>();
            
            ArvoreAjusteQuantidade arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();                                    
            
            int numArvoreAjuste = 0;
            double qtdeBiomassaObs = 0.0;
            double qtdeCarbonoObs = 0.0;
            double qtdeVolumeObs = 0.0;
            
            for (int linha = 0; linha < matriz.length; linha++) {
                ArrayList<Double> valorVariaveis = new ArrayList<Double>(); 
                ArrayList<ArvoreAjusteQuantidade> arvoresAjusteQuantidade = new ArrayList<ArvoreAjusteQuantidade>();                                    

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
                            VariavelDao variavelDao = new VariavelDao();
                            Variavel variavel = variavelDao.getVariavelComSigla(matriz[linha][coluna]);
                            variaveisLidas.add(variavel);
                            break;                        
                        }
                   } else { //demais linhas
                        switch (coluna) {
                        case 0: // Número da Árvore
                             numArvoreAjuste = Integer.parseInt(matriz[linha][coluna]);
                             break;
                        case 1: // Valor da Biomassa
                             arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();                                    
                             arvoreAjusteQuantidade.setIdVariavelInteresse(1); //Biomassa
                             arvoreAjusteQuantidade.setIdMetodoCalculo(1);     //Equação
                             arvoreAjusteQuantidade.setIdArvoreAjuste(id);
                             arvoreAjusteQuantidade.setQtdeObs(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);

                             arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();                                    
                             arvoreAjusteQuantidade.setIdVariavelInteresse(1); //Biomassa
                             arvoreAjusteQuantidade.setIdMetodoCalculo(2);     //Data Mining
                             arvoreAjusteQuantidade.setIdArvoreAjuste(id);
                             arvoreAjusteQuantidade.setQtdeObs(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);

                             break;
                        case 2: // Valor do Carbono"
                             arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();                                    
                             arvoreAjusteQuantidade.setIdVariavelInteresse(2); //Carbono
                             arvoreAjusteQuantidade.setIdMetodoCalculo(1);     //Equação
                             arvoreAjusteQuantidade.setIdArvoreAjuste(id);
                             arvoreAjusteQuantidade.setQtdeObs(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);
                             
                             arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();                                    
                             arvoreAjusteQuantidade.setIdVariavelInteresse(2); //Carbono
                             arvoreAjusteQuantidade.setIdMetodoCalculo(2);     //Data Mining
                             arvoreAjusteQuantidade.setIdArvoreAjuste(id);
                             arvoreAjusteQuantidade.setQtdeObs(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);
                            break;
                        case 3: // Valor do Volume
                             arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();                                    
                             arvoreAjusteQuantidade.setIdVariavelInteresse(3); //Volume
                             arvoreAjusteQuantidade.setIdMetodoCalculo(1);     //Equação
                             arvoreAjusteQuantidade.setIdArvoreAjuste(id);
                             arvoreAjusteQuantidade.setQtdeObs(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);

                             arvoreAjusteQuantidade = new ArvoreAjusteQuantidade();                                    
                             arvoreAjusteQuantidade.setIdVariavelInteresse(3); //Volume
                             arvoreAjusteQuantidade.setIdMetodoCalculo(2);     //Data Mining
                             arvoreAjusteQuantidade.setIdArvoreAjuste(id);
                             arvoreAjusteQuantidade.setQtdeObs(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             arvoresAjusteQuantidade.add(arvoreAjusteQuantidade);
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
                    arvoreAjuste.setNumArvoreAjuste(numArvoreAjuste);
                    
                    int i=0;
                    for (Variavel variavelLida: variaveisLidas) {
                        VariavelArvoreAjuste variavelArvoreAjuste = new VariavelArvoreAjuste();
                        variavelArvoreAjuste.setIdArvoreAjuste(arvoreAjuste.getId());
                        variavelArvoreAjuste.setIdVariavel(variavelLida.getId());
                        variavelArvoreAjuste.setValor(valorVariaveis.get(i));
                        variavelArvoreAjuste.setVariavel(variavelLida);
                        arvoreAjuste.variaveisArvoreAjuste.add(variavelArvoreAjuste);
                        i++;
                    }
                    idLocal = local.getId();
                    arvoreAjuste.arvoresAjusteQuantidade = arvoresAjusteQuantidade;
                    arvoreAjusteDao = new ArvoreAjusteDao();    
                    arvoreAjusteDao.cadastrar(arvoreAjuste);
                }
            }
        
        
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    public boolean consistePlanilhaImportada(Local local, String[][] matriz) throws SQLException, Exception {
            
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
        for (int coluna = 0; coluna < matriz[0].length; coluna++) {
            int colunaAux=coluna+1;
            switch (coluna) {
              case 0: // "Arvore"
                   if (!matriz[0][0].equalsIgnoreCase("Arvore")
                   &&  !matriz[0][0].equalsIgnoreCase("arvore")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Arvore");
                       return false;
                   }
                   break;
              case 1: // "Biomassa"
                   if (!matriz[0][1].equalsIgnoreCase("Biomassa")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Biomassa");
                       return false;
                   }
                   break;
              case 2: // "Carbono"
                   if (!matriz[0][2].equalsIgnoreCase("Carbono")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Carbono");
                       return false;
                   }
                   break;
              case 3: // "Volume"
                   if (!matriz[0][3].equalsIgnoreCase("Volume")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Volume");
                       return false;
                   }
                   break;
              default: // Variáveis
                  int iVariavel = coluna-4;
                  String xPlanilha = matriz[0][coluna];
                  String xVariavel = siglasVariavel.get(iVariavel);
                   if (!matriz[0][coluna].equalsIgnoreCase(siglasVariavel.get(iVariavel))) {                  
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser "+siglasVariavel.get(iVariavel));                  
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
        WritableWorkbook workbook = Workbook.createWorkbook(new File("c:\\teste\\arvoreAjusteExemplo.xls")); 
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

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
    
    private double qtdeBiomassaEquacao;   
    private double qtdeBiomassaDm;   
    private double qtdeCarbonoEquacao;   
    private double qtdeCarbonoDm;   
    private double qtdeVolumeEquacao;   
    private double qtdeVolumeDm;  
    
    private double qtde;
    
    public ArrayList<Arvore> arvores;
    
    public Parcela()
    {
        this.idLocal = 0;
        this.numParcela = 0;
        this.areaParcela = 0.0;
        this.qtdeBiomassaEquacao = 0.0;        
        this.qtdeBiomassaDm = 0.0;        
        this.qtdeCarbonoEquacao = 0.0;        
        this.qtdeCarbonoDm = 0.0;        
        this.qtdeVolumeEquacao = 0.0;        
        this.qtdeVolumeDm = 0.0;    
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

    public double getQtdeBiomassaEquacao() {
        return qtdeBiomassaEquacao;
    }

    public void setQtdeBiomassaEquacao(double qtdeBiomassaEquacao) {
        this.qtdeBiomassaEquacao = qtdeBiomassaEquacao;
    }

    public double getQtdeBiomassaDm() {
        return qtdeBiomassaDm;
    }

    public void setQtdeBiomassaDm(double qtdeBiomassaDm) {
        this.qtdeBiomassaDm = qtdeBiomassaDm;
    }

    public double getQtdeCarbonoEquacao() {
        return qtdeCarbonoEquacao;
    }

    public void setQtdeCarbonoEquacao(double qtdeCarbonoEquacao) {
        this.qtdeCarbonoEquacao = qtdeCarbonoEquacao;
    }

    public double getQtdeCarbonoDm() {
        return qtdeCarbonoDm;
    }

    public void setQtdeCarbonoDm(double qtdeCarbonoDm) {
        this.qtdeCarbonoDm = qtdeCarbonoDm;
    }

    public double getQtdeVolumeEquacao() {
        return qtdeVolumeEquacao;
    }

    public void setQtdeVolumeEquacao(double qtdeVolumeEquacao) {
        this.qtdeVolumeEquacao = qtdeVolumeEquacao;
    }

    public double getQtdeVolumeDm() {
        return qtdeVolumeDm;
    }

    public void setQtdeVolumeDm(double qtdeVolumeDm) {
        this.qtdeVolumeDm = qtdeVolumeDm;
    }
    
    public double getQtde(int idVariavelInteresse, int idMetodoCalculo) {
        
        if (idMetodoCalculo==1) { //Equacao
            switch (idVariavelInteresse) {
                case 1:
                    qtde = qtdeBiomassaEquacao;
                    break;
                case 2:
                    qtde = qtdeCarbonoEquacao;
                    break;
                case 3:
                    qtde = qtdeVolumeEquacao;
                    break;
            }                            
        } else {// DM
            switch (idVariavelInteresse) {
                case 1:
                    qtde = qtdeBiomassaDm;
                    break;
                case 2:
                    qtde = qtdeCarbonoDm;
                    break;
                case 3:
                    qtde = qtdeVolumeDm;
                    break;
            }                            
        }         
        return qtde;
    }

    public void setQtde(double qtde,int idVariavelInteresse, int idMetodoCalculo) {
        
        if (idMetodoCalculo==1) { //Equacao
            switch (idVariavelInteresse) {
                case 1:
                    this.qtdeBiomassaEquacao = qtde;
                    break;
                case 2:
                    this.qtdeCarbonoEquacao = qtde;
                    break;
                case 3:
                    this.qtdeVolumeEquacao = qtde;
                    break;
            }                            
        } else {// DM
            switch (idVariavelInteresse) {
                case 1:
                    this.qtdeBiomassaDm = qtde;
                    break;
                case 2:
                    this.qtdeCarbonoDm = qtde;
                    break;
                case 3:
                    this.qtdeVolumeDm = qtde;
                    break;
            }                            
        }         
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

    public void calculaQtdeEstimada(Local local,int idVariavelInteresse,int idMetodoCalculo) throws Exception {
        
        Double qtdeEstimadaArvore = 0.0;
        Double qtdeEstimadaParcela = 0.0;      

        qtdeEstimadaParcela = 0.0;
            
        for (Arvore arvore: arvores) {
            qtdeEstimadaArvore = arvore.calculaQtdeEstimada(local,idVariavelInteresse,idMetodoCalculo);   
            arvore.setQtdeEst(qtdeEstimadaArvore,idVariavelInteresse,idMetodoCalculo);  
            ArvoreDao arvoreDao = new ArvoreDao();            
            arvoreDao.updateQtdeEst(arvore,idVariavelInteresse,idMetodoCalculo);            
            qtdeEstimadaParcela += qtdeEstimadaArvore;
        }

        this.setQtde(qtdeEstimadaParcela, idVariavelInteresse, idMetodoCalculo);
        ParcelaDao parcelaDao = new ParcelaDao();        
        parcelaDao.updateQtde(this, idVariavelInteresse, idMetodoCalculo);        
    
    }

    public void importarPlanilha(Local local) throws SQLException, BiffException
    {
        ParcelaDao parcelaDao = new ParcelaDao();
        parcelaDao.deletarParcela(local);

        Workbook planilha; // objeto que receberá um instancia da planilha estudada
        Sheet aba; // objeto que será a aba
        File arquivo; // arquivo .xls que será lido
 
        try {
    
            System.out.println("Entrei aqui para AbsolutePath");
            //String AbsolutePath = new File(".").getAbsolutePath()+"/parceladolocal"+local.getIdString()+".xls";
            //String AbsolutePath = new File(".").getAbsolutePath()+"/parceladolocal.xls";
            //Ultra C:\Users\jaimewo\AppData\Roaming\NetBeans\8.0\config\GF_4.0\domain1\config
            //Casa C:\Users\Jaime\AppData\Roaming\NetBeans\8.0\config\GF_4.0\domain1\config\.\parceladolocal.xls 
            //arquivo = new File(AbsolutePath);
            
            
            arquivo = new File("C:\\teste\\parcela.xls");

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
            ArrayList<Arvore> arvores = new ArrayList<Arvore>();                                    
            Parcela parcela;
            
            for (int linha = 1; linha < matriz.length; linha++) {
                parcela = new Parcela();    
                for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                    switch (coluna) {
                    case 0: // Número da Parcela
                         parcela.numParcela = Integer.parseInt(matriz[linha][coluna]);
                         break;
                    case 1: // Área da Parcela
                         parcela.areaParcela = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                         break;
                    case 2: // Valor da Biomassa
                         parcela.qtdeBiomassaEquacao = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                         parcela.qtdeBiomassaDm      = Double.parseDouble(matriz[linha][coluna].replace(",","."));                                 
                         break;
                    case 3: // Valor do Carbono"
                         parcela.qtdeCarbonoEquacao = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                         parcela.qtdeCarbonoDm      = Double.parseDouble(matriz[linha][coluna].replace(",","."));                                 
                         break;
                    case 4: // Valor do Volume
                         parcela.qtdeVolumeEquacao = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                         parcela.qtdeVolumeDm      = Double.parseDouble(matriz[linha][coluna].replace(",","."));                                 
                         break;
                    }
                }
 
                parcela.idLocal = local.getId();
                parcela.arvores = arvores;
                parcelaDao = new ParcelaDao();
                parcelaDao.cadastrar(parcela);
 
            }
            System.out.println("nada");
        
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
    
    
    public String gravarPlanilhaExemplo(Local local) throws SQLException, BiffException, IOException, Exception
    {
//http://jmmwrite.wordpress.com/2011/02/09/gerar-xls-planilha-excell-com-java/        
    try {
        
        System.out.println("Entrei aqui para AbsolutePath");
        String AbsolutePath = new File(".").getAbsolutePath();
        System.out.println(AbsolutePath);
        String path_completo = AbsolutePath+"/exemploparcelas.xls";
        WritableWorkbook workbook = Workbook.createWorkbook(new File(path_completo)); 
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
        
        return path_completo;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    } catch (RowsExceededException e) {
        e.printStackTrace();
        return null;
    } catch (WriteException e) {
        e.printStackTrace();
        return null;
    }
        
    
    }
    
    public void gravarPlanilhaValoresEstimados(Local local,int idVariavelInteresse, int idMetodoCalculo) throws SQLException, BiffException, IOException, Exception    {
//http://jmmwrite.wordpress.com/2011/02/09/gerar-xls-planilha-excell-com-java/        
    try {
        
        String descricaoVariavelInteresse = "";
        switch (idVariavelInteresse) {
            case 1:
                descricaoVariavelInteresse = "Biomassa";
                break;
            case 2:
                descricaoVariavelInteresse = "Carbono";
                break;            
            case 3:
                descricaoVariavelInteresse = "Volume";
                break;        
        }
        
        String descricaoMetodoCalculo = "";
        switch (idMetodoCalculo) {
            case 1:
                descricaoMetodoCalculo = "Equação";
                break;
            case 2:
                descricaoMetodoCalculo = "Data Mining";
                break;            
        }
        
        String nomePlanilha = "c:\\teste\\ValorEstimado"+descricaoVariavelInteresse+" Local "+local.getIdString()+" - "+local.getDescricao()+".xls";
        
        WritableWorkbook workbook = Workbook.createWorkbook(new File(nomePlanilha)); 
        WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
 
        // work with coordinates (from 0,0 to N,k) -> COL, LINE
        Label label = new Label(0, 0, "Local: "+local.getDescricao());
        sheet.addCell(label);
        label = new Label(0, 1, "Variavel de Interesse: "+descricaoVariavelInteresse);
        sheet.addCell(label);
        label = new Label(0, 2, "Método de Cálculo: "+descricaoMetodoCalculo);
        sheet.addCell(label);

        label = new Label(0, 4, "Parcela");
        sheet.addCell(label);
        label = new Label(1, 4, "Árvore");
        sheet.addCell(label);
        
 
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
        
        int coluna=2;
        
        for(String sigla: siglasVariavel) {
            label = new Label(coluna, 4, sigla);
            sheet.addCell(label);
            coluna++;
        }
        
        label = new Label(coluna, 4, "Valor Estimado");
        sheet.addCell(label);
        
        int linha=5;
        coluna = 0;        
        
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
        ArrayList<Arvore>  arvores  = new ArrayList<Arvore>();
        
        ParcelaDao parcelaDao = new ParcelaDao();
        
        parcelas = parcelaDao.listarParcelas(local.getId());
        
        for(Parcela parcela: parcelas) {
            arvores = parcela.getArvores();
            
            for (Arvore arvore: arvores) {
                jxl.write.Number number = new jxl.write.Number(coluna, linha, parcela.getNumParcela());
                sheet.addCell(number);
                coluna++;
                
                number = new jxl.write.Number(coluna, linha, arvore.getNumArvore());
                sheet.addCell(number);
                coluna++;
                
                for (VariavelArvore variavelArvore: arvore.variaveisArvore) {
                    //System.out.println("Variavel "+variavelArvore.getVariavel().getSigla()+": "+variavelArvore.getValor());
                    number = new jxl.write.Number(coluna, linha, variavelArvore.getValor());
                    sheet.addCell(number);
                    coluna++;
                }
                number = new jxl.write.Number(coluna, linha, arvore.getQtdeEst(idVariavelInteresse, idMetodoCalculo));
                sheet.addCell(number);
                coluna=0;   
                linha++;            
            }
            coluna=0;
            linha++;            
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



import dao.ArvoreDao;
import dao.ParcelaDao;
import dao.VariavelArvoreDao;
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
import org.nfunk.jep.JEP;

/**
 *
 * @author jaime
 */
public class Arvore extends Model  {
    
    
    private int id;
    private int idParcela;
    private int numArvore;
    private double qtdeBiomassaObs;
    private double qtdeBiomassaEst;
    private double qtdeCarbonoObs;
    private double qtdeCarbonoEst;
    private double qtdeVolumeObs;
    private double qtdeVolumeEst;
    
    public ArrayList<VariavelArvore> variaveisArvore;
    
    public Arvore()
    {
        this.idParcela = 0;
        this.numArvore = 0;
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

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public int getNumArvore() {
        return numArvore;
    }

    public void setNumArvore(int numArvore) {
        this.numArvore = numArvore;
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

    public ArrayList<VariavelArvore> getVariaveisArvore() throws Exception {
        ArrayList<VariavelArvore> variaveisArvore = new ArrayList<VariavelArvore>();
        VariavelArvoreDao variaveisArvoreDao = new VariavelArvoreDao();
        variaveisArvore = variaveisArvoreDao.listarVariaveisArvore(this.id);        
        
        return variaveisArvore;
    }

    public Double calculaBiomassaEst(Local local) throws SQLException, Exception {
        int idVarivelInteresse = 1; //Biomassa
        qtdeBiomassaEst = calculaQuantidade(local,idVarivelInteresse);
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.updateBiomassa(this);
        return qtdeBiomassaEst;
    }

    public Double calculaCarbonoEst(Local local) throws SQLException, Exception {
        int idVarivelInteresse = 2; //Carbono
        qtdeCarbonoEst = calculaQuantidade(local,idVarivelInteresse);
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.updateCarbono(this);
        return qtdeCarbonoEst;
    }
    
    public Double calculaVolumeEst(Local local) throws SQLException, Exception {
        int idVarivelInteresse = 3; //Volume
        qtdeVolumeEst = calculaQuantidade(local,idVarivelInteresse);
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.updateVolume(this);
        return qtdeVolumeEst;
    }
    
    
    private Double calculaQuantidade(Local local, int idVariavelInteresse) throws SQLException, Exception {

        Double resultado = 0.0;
        this.variaveisArvore = getVariaveisArvore();

        ArrayList<Equacao> equacoesTrabalho = new ArrayList<Equacao>();
        equacoesTrabalho = local.getTrabalhoCientifico().getEquacoesTrabalho();
        
        for (Equacao equacao : equacoesTrabalho) {
            if (equacao.getIdVariavelInteresse()==idVariavelInteresse) {
                resultado = aplicaParser(variaveisArvore,equacao);
            }
        }
        
        return resultado;
    
    }
    private Double aplicaParser(ArrayList<VariavelArvore> variaveisArvore, Equacao equacao) throws SQLException {
        //http://www.singularsys.com/jep/doc/html/index.html
        JEP myParser = new JEP();
        myParser.addStandardFunctions();
        myParser.addStandardConstants();

        double resultado = 0.0;
        
        for (VariavelArvore variavelArvore : variaveisArvore) {
            String sigla = variavelArvore.getVariavel().getSigla();
            Double valor = variavelArvore.getValor();
        
            myParser.addVariable(sigla, valor);
        }  
            
        myParser.parseExpression(equacao.getExpressaoEquacao());
        
        resultado = myParser.getValue();
        return resultado;
    
    }
    
    
    public void importarPlanilha(Local local) throws SQLException, BiffException
    {
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.deletarLocal(local);

        Workbook planilha; // objeto que receberá um instancia da planilha estudada
        Sheet aba; // objeto que será a aba
        File arquivo; // arquivo .xls que será lido
 
        try {
            
            //Ultra arquivo = new File("C:\\Users\\jaimewo\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\arvore.xls");
            //arquivo = new File("E:\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\arvore.xls");
            arquivo = new File("c:\\teste\\arvore.xls");
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

            ArrayList<Arvore> arvores = new ArrayList<Arvore>();
            ArrayList<Variavel> variaveisLidas = new ArrayList<Variavel>();
            int numArvore = 0;
            double qtdeBiomassaObs = 0.0;
            double qtdeCarbonoObs = 0.0;
            double qtdeVolumeObs = 0.0;
            int numParcela = 0;
            double areaParcela = 0.0;
            
            for (int linha = 0; linha < matriz.length; linha++) {
                ArrayList<Double> valorVariaveis = new ArrayList<Double>(); 
                Arvore arvore = new Arvore();
                for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                    if (linha==0) {
                        switch (coluna) {
                        case 0: // "Parcela"
                             break;
                        case 1: // "Area"
                             break;
                        case 2: // "Arvore"
                             break;
                        case 3: // "Biomassa"
                            break;
                        case 4: // "Carbono"
                            break;
                        case 5: // "Volume"
                            break;
                        default: // Variáveis
                            Variavel variavel = variavelDao.getVariavelComSigla(matriz[linha][coluna]);
                            variaveisLidas.add(variavel);
                            break;                        
                        }
                   } else { //demais linhas
                        switch (coluna) {
                        case 0: // Número da Parcela
                             numParcela = Integer.parseInt(matriz[linha][coluna]);
                             break;
                        case 1: // Area Parcela
                             areaParcela = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 2: // Número da Árvore
                             arvore.numArvore = Integer.parseInt(matriz[linha][coluna]);
                             break;
                        case 3: // Valor da Biomassa
                             arvore.qtdeBiomassaObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 4: // Valor do Carbono"
                             arvore.qtdeCarbonoObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 5: // Valor do Volume
                             arvore.qtdeVolumeObs = Double.parseDouble(matriz[linha][coluna].replace(",","."));;
                             break;
                        default: // Valor das Variáveis
                             valorVariaveis.add(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             break;                        
                        }
                    }
                }
                ArrayList<VariavelArvore> variaveisArvoreAux = new ArrayList<VariavelArvore>();
                
                if (linha>0) {
                    int i=0;
                    for (Variavel variavelLida: variaveisLidas) {
                        VariavelArvore variavelArvore = new VariavelArvore();
                        variavelArvore.setIdArvore(arvore.id);
                        variavelArvore.setIdVariavel(variavelLida.getId());
                        variavelArvore.setValor(valorVariaveis.get(i));
                        variavelArvore.setVariavel(variavelLida);
                        variaveisArvoreAux.add(variavelArvore);
                        i++;
                    }
                    arvore.variaveisArvore = variaveisArvoreAux; 
                    arvores.add(arvore);
                }
            }
            Parcela parcela = new Parcela();
            parcela.setIdLocal(local.getId());
            parcela.setNumParcela(numParcela);
            parcela.setAreaParcela(areaParcela);
            parcela.setArvores(arvores);
            
            ParcelaDao parcelaDao = new ParcelaDao();
            parcelaDao.cadastrar(parcela);
        
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    
    public void gravarPlanilhaExemplo(Local local) throws SQLException, BiffException, IOException, Exception
    {
//http://jmmwrite.wordpress.com/2011/02/09/gerar-xls-planilha-excell-com-java/        
    try {
        WritableWorkbook workbook = Workbook.createWorkbook(new File("c:\\teste\\arvore.xls")); 
        WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
 
        // work with coordinates (from 0,0 to N,k) -> COL, LINE
        Label label = new Label(0, 0, "Parcela");
        sheet.addCell(label);
        jxl.write.Number number = new jxl.write.Number(0, 1, 1);
        sheet.addCell(number);
        
        label = new Label(1, 0, "Área Parcela");
        sheet.addCell(label);
        number = new jxl.write.Number(1, 1, 1);
        sheet.addCell(number);
        
        label = new Label(2, 0, "Árvore");
        sheet.addCell(label);
        number = new jxl.write.Number(2, 1, 1);
        sheet.addCell(number);
        
        label = new Label(3, 0, "Biomassa");
        sheet.addCell(label);
        number = new jxl.write.Number(3, 1, 10);
        sheet.addCell(number);
 
        label = new Label(4, 0, "Carbono");
        sheet.addCell(label);
        number = new jxl.write.Number(4, 1, 10);
        sheet.addCell(number);
 
        label = new Label(5, 0, "Volume");
        sheet.addCell(label);
        number = new jxl.write.Number(5, 1, 10);
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
        
        int i=6;
        
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

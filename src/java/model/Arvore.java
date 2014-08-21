/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ArvoreDao;
import dao.ParcelaDao;
import dao.VariavelArvoreDao;
import dao.VariavelDao;
import dao.VizinhoDao;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    
    private double qtdeBiomassaEstEquacao;   
    private double qtdeBiomassaEstDm;   
    private double qtdeCarbonoEstEquacao;   
    private double qtdeCarbonoEstDm;   
    private double qtdeVolumeEstEquacao;   
    private double qtdeVolumeEstDm;   
    
    private double qtdeEst;
    
    public ArrayList<VariavelArvore> variaveisArvore;
    
    public Arvore()
    {
        this.idParcela = 0;
        this.numArvore = 0;
        this.qtdeBiomassaEstEquacao = 0.0;        
        this.qtdeBiomassaEstDm = 0.0;        
        this.qtdeCarbonoEstEquacao = 0.0;        
        this.qtdeCarbonoEstDm = 0.0;        
        this.qtdeVolumeEstEquacao = 0.0;        
        this.qtdeVolumeEstDm = 0.0;        
        
        this.variaveisArvore = new ArrayList<VariavelArvore>();
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

    public ArrayList<VariavelArvore> getVariaveisArvore() throws Exception {
        ArrayList<VariavelArvore> variaveisArvore = new ArrayList<VariavelArvore>();
        VariavelArvoreDao variaveisArvoreDao = new VariavelArvoreDao();
        variaveisArvore = variaveisArvoreDao.listarVariaveisArvore(this.id);        
        
        return variaveisArvore;
    }

    public double getQtdeBiomassaEstEquacao() {
        return qtdeBiomassaEstEquacao;
    }

    public void setQtdeBiomassaEstEquacao(double qtdeBiomassaEstEquacao) {
        this.qtdeBiomassaEstEquacao = qtdeBiomassaEstEquacao;
    }

    public double getQtdeBiomassaEstDm() {
        return qtdeBiomassaEstDm;
    }

    public void setQtdeBiomassaEstDm(double qtdeBiomassaEstDm) {
        this.qtdeBiomassaEstDm = qtdeBiomassaEstDm;
    }

    public double getQtdeCarbonoEstEquacao() {
        return qtdeCarbonoEstEquacao;
    }

    public void setQtdeCarbonoEstEquacao(double qtdeCarbonoEstEquacao) {
        this.qtdeCarbonoEstEquacao = qtdeCarbonoEstEquacao;
    }

    public double getQtdeCarbonoEstDm() {
        return qtdeCarbonoEstDm;
    }

    public void setQtdeCarbonoEstDm(double qtdeCarbonoEstDm) {
        this.qtdeCarbonoEstDm = qtdeCarbonoEstDm;
    }

    public double getQtdeVolumeEstEquacao() {
        return qtdeVolumeEstEquacao;
    }

    public void setQtdeVolumeEstEquacao(double qtdeVolumeEstEquacao) {
        this.qtdeVolumeEstEquacao = qtdeVolumeEstEquacao;
    }

    public double getQtdeVolumeEstDm() {
        return qtdeVolumeEstDm;
    }

    public void setQtdeVolumeEstDm(double qtdeVolumeEstDm) {
        this.qtdeVolumeEstDm = qtdeVolumeEstDm;
    }
    
    public double getQtdeEst(int idVariavelInteresse, int idMetodoCalculo) {    
        
        double qtdeEst = 0.0;
        
        if (idMetodoCalculo==1) { //Equacao
            switch (idVariavelInteresse) {
                case 1:
                        qtdeEst = this.qtdeBiomassaEstEquacao;
                        break;
                    case 2:
                        qtdeEst = this.qtdeCarbonoEstEquacao;
                        break;
                    case 3:
                        qtdeEst = this.qtdeVolumeEstEquacao;
                        break;
                }                            
            } else {// DM
                switch (idVariavelInteresse) {
                    case 1:
                        qtdeEst = this.qtdeBiomassaEstDm;
                        break;
                    case 2:
                        qtdeEst = this.qtdeCarbonoEstDm;
                        break;
                    case 3:
                        qtdeEst = this.qtdeVolumeEstDm;
                        break;
                }                            
            }        
        
        return qtdeEst;
    }
    
    public void setQtdeEst(double qtdeEst, int idVariavelInteresse, int idMetodoCalculo) {    
        
        if (idMetodoCalculo==1) { //Equacao
            switch (idVariavelInteresse) {
                case 1:
                    this.qtdeBiomassaEstEquacao = qtdeEst;
                    break;
                case 2:
                    this.qtdeCarbonoEstEquacao = qtdeEst;
                    break;
                case 3:
                    this.qtdeVolumeEstEquacao = qtdeEst;
                    break;
            }                            
        } else {// DM
            switch (idVariavelInteresse) {
                case 1:
                    this.qtdeBiomassaEstDm = qtdeEst;
                    break;
                case 2:
                    this.qtdeCarbonoEstDm = qtdeEst;
                    break;
                case 3:
                    this.qtdeVolumeEstDm = qtdeEst;
                    break;
            }                            
        }        
        
    }
    
    public Double calculaQtdeEstimada(Local local, int idVariavelInteresse, int idMetodoCalculo, Equacao equacao) throws SQLException, Exception {

        Double qtdeEstimada = 0.0;

        if (idMetodoCalculo==1) { //Equação
            qtdeEstimada = aplicaParser(variaveisArvore,equacao);
        } else { //2-Data Mining
            qtdeEstimada = calculaUsandoDM(local,idVariavelInteresse);
        }
        return qtdeEstimada;
    
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
    
    public Double calculaUsandoDM(Local local, int idVariavelInteresse) throws SQLException, ClassNotFoundException, Exception 
    {
        int idMetodoCalculo = 2; //DM
        
        DecimalFormat df = new DecimalFormat("0.000000");
        DecimalFormat df1 = new DecimalFormat("0.00");
        DecimalFormat df2 = new DecimalFormat("0");
        
        Double[]  menoresDistancias = new Double[local.getDmQtdeVizinhos()];
        Integer[] numArvoreMenoresDistancias = new Integer[local.getDmQtdeVizinhos()];
        Double[]  qtdeObsMenoresDistancias = new Double[local.getDmQtdeVizinhos()];
        Double[]  ponderacao = new Double[local.getDmQtdeVizinhos()];
        Double[]  medidasDistanciaChebychev = new Double[variaveisArvore.size()];
        
        for (int i=0; i<local.getDmQtdeVizinhos(); i++) {
            menoresDistancias[i] = 99999.9;
            numArvoreMenoresDistancias[i] = 0;
            qtdeObsMenoresDistancias[i] = 0.0;
            ponderacao[i] = 0.0;
        }

        double medidaDistancia = 0.0;
        double qtdeObsArvoreAjuste = 0.0;

        ArrayList<VariavelArvore> variaveisArvore = new ArrayList<VariavelArvore>();
        variaveisArvore = getVariaveisArvore();

        ArrayList<VariavelArvoreAjuste> variaveisArvoreAjuste= new ArrayList<VariavelArvoreAjuste>();
            
        ArrayList<ArvoreAjuste> arvoresAjuste = new ArrayList<ArvoreAjuste>();
        arvoresAjuste = local.getArvoresAjuste(idVariavelInteresse,idMetodoCalculo);
  
        for(ArvoreAjuste arvoreAjuste: arvoresAjuste) {
            
            qtdeObsArvoreAjuste = arvoreAjuste.getQtdeObs(idVariavelInteresse);
                        
            variaveisArvoreAjuste = arvoreAjuste.getVariaveisArvoreAjuste();
            int i = 0;
            switch (local.getIdDMTipoDistancia()) {
            case 1: //Distancia Euclidiana
                for(VariavelArvore variavelArvore: variaveisArvore) {
                    medidaDistancia += Math.pow(variavelArvore.getValor() - variaveisArvoreAjuste.get(i).getValor(), (double) 2);
                    i++;
                }
                medidaDistancia = Math.sqrt(medidaDistancia);
                break;
            case 2: //Distancia Quandrática
                for(VariavelArvore variavelArvore: variaveisArvore) {
                    medidaDistancia += Math.pow(variavelArvore.getValor() - variaveisArvoreAjuste.get(i).getValor(), (double) 2);
                    i++;
                }
                break;
            case 3: //Distancia Manhathan
                for(VariavelArvore variavelArvore: variaveisArvore) {
                    medidaDistancia += Math.abs(variavelArvore.getValor() - variaveisArvoreAjuste.get(i).getValor());
                    i++;
                }
                break;
            case 4: //Distancia Chebychev
                for(VariavelArvore variavelArvore: variaveisArvore) {
                    medidasDistanciaChebychev[i] = Math.abs(variavelArvore.getValor() - variaveisArvoreAjuste.get(i).getValor());
                    i++;
                }
                double maiorDistancia = 0.0;
                for(int j=0;j<i;j++) {
                    if (medidasDistanciaChebychev[j]>maiorDistancia) {
                        maiorDistancia = medidasDistanciaChebychev[j];
                    }
                }
                medidaDistancia = maiorDistancia;
                break;
            }
            
            for (int iVizinho=0;iVizinho<local.getDmQtdeVizinhos();iVizinho++) {
                if (medidaDistancia<menoresDistancias[iVizinho]) {
                    for (int iAux=local.getDmQtdeVizinhos()-1;iAux>iVizinho;iAux--) {
                        menoresDistancias[iAux] = menoresDistancias[iAux-1];
                        numArvoreMenoresDistancias[iAux] = numArvoreMenoresDistancias[iAux-1];
                        qtdeObsMenoresDistancias[iAux] = qtdeObsMenoresDistancias[iAux-1];
                        ponderacao[iAux] = ponderacao[iAux-1];
                    }
                    menoresDistancias[iVizinho] = medidaDistancia;
                    numArvoreMenoresDistancias[iVizinho] = arvoreAjuste.getNumArvoreAjuste();
                    qtdeObsMenoresDistancias[iVizinho] = qtdeObsArvoreAjuste;  
                    if (local.getIdDMTipoPonderacao()==2) { // 2="1/d"
                       if(medidaDistancia>0) {
                          ponderacao[iVizinho] = 1 / medidaDistancia;
                       } else {
                          ponderacao[iVizinho] = 1.0;
                       }                        
                    } else {
                        if (local.getIdDMTipoPonderacao()==3) { // 3 = "1/d2"
                            if(medidaDistancia>0) {
                               ponderacao[iVizinho] = 1 / (Math.pow(medidaDistancia,(double) 2));
                            } else {
                                ponderacao[iVizinho] = 1.0;
                            }
                        } else {
                            ponderacao[iVizinho] = 1.0;
                        }
                    }

                    
                    iVizinho=local.getDmQtdeVizinhos();
                }
            }
        }
        
        double qtdeEstimada = 0.0;
        double somaPonderacao = 0.0;

        
        for (int iVizinho=0;iVizinho < local.getDmQtdeVizinhos();iVizinho++) {
            if (ponderacao[iVizinho]==0) {
                somaPonderacao = 1;
                qtdeEstimada = qtdeObsMenoresDistancias[iVizinho];
                iVizinho=local.getDmQtdeVizinhos();
            } else {
                qtdeEstimada += qtdeObsMenoresDistancias[iVizinho] * ponderacao[iVizinho];
                somaPonderacao += ponderacao[iVizinho];
            }
        }
        qtdeEstimada = qtdeEstimada / somaPonderacao;

        Vizinho vizinho = new Vizinho();
        vizinho.setIdArvore(this.getNumArvore());
        
        VizinhoDao vizinhoDao = new VizinhoDao();
        for (int iVizinho=0;iVizinho < local.getDmQtdeVizinhos();iVizinho++) {
            vizinho.setNumVizinho(iVizinho);
            vizinho.setMenorDistancia(menoresDistancias[iVizinho]);
            vizinho.setNumArvoreMenorDistancia(numArvoreMenoresDistancias[iVizinho]);
            vizinho.setQtdeObsMenorDistancia(qtdeObsMenoresDistancias[iVizinho]);
            vizinhoDao.cadastrar(vizinho);
        }
        
        return qtdeEstimada;
        
     }
    
    
    public ArrayList<Parcela> importarPlanilha(Local local) throws SQLException, BiffException
    {
        ArvoreDao arvoreDao = new ArvoreDao();
        arvoreDao.deletarLocal(local);
        
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();

        Workbook planilha; // objeto que receberá um instancia da planilha estudada
        Sheet aba; // objeto que será a aba
        File arquivo; // arquivo .xls que será lido
 
        try {
            
            //Ultra arquivo = new File("C:\\Users\\jaimewo\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\arvore.xls");
            //arquivo = new File("E:\\Dropbox\\Jaime\\AA-UFPR\\Doutorado\\Tese\\Implementacao Oficial\\JCarbon\\projetodoutorado_git\\Arquivos\\arvore.xls");
            //System.out.println("Entrei aqui para AbsolutePath");
            //String AbsolutePath = new File(".").getAbsolutePath()+"/arvorelocal"+local.getIdString()+".xls";
            //String AbsolutePath = new File(".").getAbsolutePath()+"/arvoresdolocal.xls";
            //arquivo = new File(AbsolutePath);
            
            
            arquivo = new File("C:\\teste\\arvore.xls");            
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
                return parcelas;
            }

            ArrayList<Arvore> arvores        = new ArrayList<Arvore>();                
            ArrayList<Variavel>          variaveisLidas      = new ArrayList<Variavel>();
            
            int numArvore = 0;
            int numParcela = 0;
            int numParcelaAnt = 0;            
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
                        default: // Variáveis
                            VariavelDao variavelDao = new VariavelDao();                            
                            Variavel variavel = variavelDao.getVariavelComSigla(matriz[linha][coluna]);
                            variaveisLidas.add(variavel);
                            break;                        
                        }
                   } else { //demais linhas
                        switch (coluna) {
                        case 0: // Número da Parcela
                            numParcela = Integer.parseInt(matriz[linha][coluna]);
                            if ((numParcela != numParcelaAnt)
                            &&  (numParcelaAnt>0)) {

                                Parcela parcela = new Parcela();
                                parcela.setIdLocal(local.getId());
                                parcela.setNumParcela(numParcelaAnt);
                                parcela.setAreaParcela(areaParcela);
                                parcela.setArvores(arvores);
                                
                                ParcelaDao parcelaDao = new ParcelaDao();
                                parcelaDao.cadastrar(parcela);
                                parcelas.add(parcela);
                    
                                arvores = new ArrayList<Arvore>();
                                valorVariaveis.clear();
                                valorVariaveis.clear();

                            }
                            numParcelaAnt = numParcela;                                                 
                            break;
                        case 1: // Area Parcela
                             areaParcela = Double.parseDouble(matriz[linha][coluna].replace(",","."));
                             break;
                        case 2: // Número da Árvore
                             arvore.numArvore = Integer.parseInt(matriz[linha][coluna]);
                             break;
                        default: // Valor das Variáveis
                             valorVariaveis.add(Double.parseDouble(matriz[linha][coluna].replace(",",".")));
                             break;                        
                        }
                    }
                }

                
                if (linha>0) {
                    int i=0;
                    for (Variavel variavelLida: variaveisLidas) {
                        VariavelArvore variavelArvore = new VariavelArvore();
                        variavelArvore.setIdArvore(arvore.id);
                        variavelArvore.setIdVariavel(variavelLida.getId());
                        variavelArvore.setValor(valorVariaveis.get(i));
                        variavelArvore.setVariavel(variavelLida);
                        arvore.variaveisArvore.add(variavelArvore);
                   
                        i++;
                    }
                    arvores.add(arvore);
                    valorVariaveis.clear();
                }
                
            }
            
            Parcela parcela = new Parcela();
            parcela.setIdLocal(local.getId());
            parcela.setNumParcela(numParcela);
            parcela.setAreaParcela(areaParcela);

            parcela.setArvores(arvores);
            
            ParcelaDao parcelaDao = new ParcelaDao();            
            parcelaDao.cadastrar(parcela);
            parcelas.add(parcela);  
            

        
        } catch (Exception e) {

            e.printStackTrace();

        }
        return parcelas;
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
              case 0: // "Parcela"
                   if (!matriz[0][0].equalsIgnoreCase("Parcela")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Parcela");
                       return false;
                   }
                   break;
              case 1: // "Area da Parcela"
                   if (!matriz[0][1].equalsIgnoreCase("Area da Parcela")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Area da Parcela");
                       return false;
                   }
                   break;
              case 2: // "Arvore"
                   if (!matriz[0][2].equalsIgnoreCase("Arvore")) {
                       System.out.println("Titulo da coluna "+colunaAux+ " deve ser Arvore");
                       return false;
                   }
                   break;
              default: // Variáveis
                  int iVariavel = coluna-3;
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
    public String gravarPlanilhaExemplo(Local local) throws SQLException, BiffException, IOException, Exception
    {
//http://jmmwrite.wordpress.com/2011/02/09/gerar-xls-planilha-excell-com-java/        
    try {
        
        String AbsolutePath = new File(".").getAbsolutePath();
        String path_completo = AbsolutePath+"/exemploarvores.xls";
        WritableWorkbook workbook = Workbook.createWorkbook(new File(path_completo)); 
        WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
 
        // work with coordinates (from 0,0 to N,k) -> COL, LINE
        Label label = new Label(0, 0, "Parcela");
        sheet.addCell(label);
        jxl.write.Number number = new jxl.write.Number(0, 1, 1);
        sheet.addCell(number);
        
        label = new Label(1, 0, "Area da Parcela");
        sheet.addCell(label);
        number = new jxl.write.Number(1, 1, 1);
        sheet.addCell(number);
        
        label = new Label(2, 0, "Arvore");
        sheet.addCell(label);
        number = new jxl.write.Number(2, 1, 1);
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
        
        int i=3;
        
        for(String sigla: siglasVariavel) {
            label = new Label(i, 0, sigla);
            sheet.addCell(label);
            number = new jxl.write.Number(i, 1, 10);
            sheet.addCell(number);
            i++;
        }
 
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


}

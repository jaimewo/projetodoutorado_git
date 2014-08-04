<%-- 
    Document   : listarDetalhesCalculo
    Created on : 31/07/2014
    Author     : jaime
--%>

<%@page import="model.VariavelInteresse"%>
<%@page import="model.EstatisticaAjuste"%>
<%@page import="model.EstatisticaInventario"%>
<%@page import="model.Local"%>
<%@page import="java.util.List"%>
<%@page import="model.Autor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - Listar Detalhes do Cálculo</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% Local local                                        = (Local) request.getAttribute("local");%> 
        <% String variavelInteresse                           = (String) request.getAttribute("variavelInteresse");%> 
        <% String descricaoTipoDistancia                      = (String) request.getAttribute("descricaoTipoDistancia");%> 
        <% String descricaoTipoPonderacao                     = (String) request.getAttribute("descricaoTipoPonderacao");%> 
        <% String descricaoComLn                              = (String) request.getAttribute("descricaoComLn");%> 
        
        <% String equacaoMediaParcelaStr                      = (String) request.getAttribute("equacaoMediaParcelaStr");%>   
        <% String equacaoCoeficienteVariacaoStr               = (String) request.getAttribute("equacaoCoeficienteVariacaoStr");%>   
        <% String equacaoDesvioPadraoStr                      = (String) request.getAttribute("equacaoDesvioPadraoStr");%>   
        <% String equacaoErroAbsolutoStr                      = (String) request.getAttribute("equacaoErroAbsolutoStr");%>   
        <% String equacaoErroPadraoStr                        = (String) request.getAttribute("equacaoErroPadraoStr");%>   
        <% String equacaoErroRelativoStr                      = (String) request.getAttribute("equacaoErroRelativoStr");%>   
        <% String equacaoIntervaloConfiancaMaxMediaStr        = (String) request.getAttribute("equacaoIntervaloConfiancaMaxMediaStr");%>   
        <% String equacaoIntervaloConfiancaMinMediaStr        = (String) request.getAttribute("equacaoIntervaloConfiancaMinMediaStr");%>   
        <% String equacaoIntervaloConfiancaMaxTotalStr        = (String) request.getAttribute("equacaoIntervaloConfiancaMaxTotalStr");%>   
        <% String equacaoIntervaloConfiancaMinTotalStr        = (String) request.getAttribute("equacaoIntervaloConfiancaMinTotalStr");%>   
        <% String equacaoQtdeMediaStr                         = (String) request.getAttribute("equacaoQtdeMediaStr");%>   
        <% String equacaoVarianciaStr                         = (String) request.getAttribute("equacaoVarianciaStr");%>   
        <% String equacaoVarianciaMediaStr                    = (String) request.getAttribute("equacaoVarianciaMediaStr");%>   

        
        <% String dmMediaParcelaStr                      = (String) request.getAttribute("dmMediaParcelaStr");%>   
        <% String dmCoeficienteVariacaoStr               = (String) request.getAttribute("dmCoeficienteVariacaoStr");%>   
        <% String dmDesvioPadraoStr                      = (String) request.getAttribute("dmDesvioPadraoStr");%>   
        <% String dmErroAbsolutoStr                      = (String) request.getAttribute("dmErroAbsolutoStr");%>   
        <% String dmErroPadraoStr                        = (String) request.getAttribute("dmErroPadraoStr");%>   
        <% String dmErroRelativoStr                      = (String) request.getAttribute("dmErroRelativoStr");%>   
        <% String dmIntervaloConfiancaMaxMediaStr        = (String) request.getAttribute("dmIntervaloConfiancaMaxMediaStr");%>   
        <% String dmIntervaloConfiancaMinMediaStr        = (String) request.getAttribute("dmIntervaloConfiancaMinMediaStr");%>   
        <% String dmIntervaloConfiancaMaxTotalStr        = (String) request.getAttribute("dmIntervaloConfiancaMaxTotalStr");%>   
        <% String dmIntervaloConfiancaMinTotalStr        = (String) request.getAttribute("dmIntervaloConfiancaMinTotalStr");%>   
        <% String dmQtdeMediaStr                         = (String) request.getAttribute("dmQtdeMediaStr");%>   
        <% String dmVarianciaStr                         = (String) request.getAttribute("dmVarianciaStr");%>   
        <% String dmVarianciaMediaStr                    = (String) request.getAttribute("dmVarianciaMediaStr");%>   

        <% String equacaoR2Str                           = (String) request.getAttribute("equacaoR2Str");%>   
        <% String equacaoR2AjustStr                      = (String) request.getAttribute("equacaoR2AjustStr");%>   
        <% String equacaoSyxStr                          =   (String) request.getAttribute("equacaoSyxStr");%>   
        <% String equacaoSyxPercStr                      = (String) request.getAttribute("equacaoSyxPercStr");%>   
        <% String equacaoIaStr                           = (String) request.getAttribute("equacaoIaStr");%>   
        <% String equacaoAicStr                          = (String) request.getAttribute("equacaoAicSrtr");%>   
        <% String equacaoBicStr                          = (String) request.getAttribute("equacaoBicStr");%>   
        <% String equacaoWilmottStr                      = (String) request.getAttribute("equacaoWilmottStr");%>   
        <% String equacaoSomaQuadradoResiduoStr          = (String) request.getAttribute("equacaoSomaQuadradoResiduoStr");%>   
        <% String equacaoSomaQuadradoRegressaoStr        = (String) request.getAttribute("equacaoSomaQuadradoRegressaoStr");%>   
        <% String equacaoSomaQuadradoTotaisStr           = (String) request.getAttribute("equacaoSomaQuadradoTotaisStr");%>   

        <% String dmR2Str                                = (String) request.getAttribute("dmR2Str");%>   
        <% String dmR2AjustStr                           = (String) request.getAttribute("dmR2AjustStr");%>   
        <% String dmSyxStr                               = (String) request.getAttribute("dmSyxStr");%>   
        <% String dmSyxPercStr                           = (String) request.getAttribute("dmSyxPercStr");%>   
        <% String dmIaStr                                = (String) request.getAttribute("dmIaStr");%>   
        <% String dmAicStr                               = (String) request.getAttribute("dmAicSrtr");%>   
        <% String dmBicStr                               = (String) request.getAttribute("dmBicStr");%>   
        <% String dmWilmottStr                           = (String) request.getAttribute("dmWilmottStr");%>   
        <% String dmSomaQuadradoResiduoStr               = (String) request.getAttribute("dmSomaQuadradoResiduoStr");%>   
        <% String dmSomaQuadradoRegressaoStr             = (String) request.getAttribute("dmSomaQuadradoRegressaoStr");%>   
        <% String dmSomaQuadradoTotaisStr                = (String) request.getAttribute("dmSomaQuadradoTotaisStr");%>   
        
        <% EstatisticaInventario estatisticaInventarioEquacao = (EstatisticaInventario) request.getAttribute("estatisticaInventarioEquacao");%>        
        <% EstatisticaInventario estatisticaInventarioDm      = (EstatisticaInventario) request.getAttribute("estatisticaInventarioDm");%>        
        <% EstatisticaAjuste estatisticaAjusteEquacao         = (EstatisticaAjuste) request.getAttribute("estatisticaAjusteEquacao");%>        
        <% EstatisticaAjuste estatisticaAjusteDm              = (EstatisticaAjuste) request.getAttribute("estatisticaAjusteDm");%>  
        <div class="container">
            <br />
            <br />
            <h1>Detalhes dos Cálculos</h1>
            <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <strong><%= request.getAttribute("mensagem").toString()%></strong>
                </div>
            <%}%>
             <div class="field control-group">
                <label for="variavelInteresse" class="control-label">Variável de Interesse:</label>
                <div class="controls">
                    <input type="text" name="variavelInteresse" value="<%=variavelInteresse%>" />
                </div>
            </div> 
            <input type="hidden" name="local[id]" value ="<%=local.getIdString()%>" />
             <div class="field control-group">
                <label for="local_descricao" class="control-label">Local</label>
                <div class="controls">
                    <input type="text" name="local[descricao]" value="<%=local.getDescricao()%>" />
                </div>
            </div>   

                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Estatísticas da Estimativa</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <tr>
                            <th></th>
                            <th>por Regressão Linear</th>
                            <th>utilizando Data Mining</th>
                        </tr>                    
                    </thead>
                    <tbody>
                        <tr>
                            <td>Média por Parcela:</td>
                            <td><%=equacaoMediaParcelaStr%></td>
                            <td><%=dmMediaParcelaStr%></td>
                        </tr>
                        <tr>
                            <td>Variância:</td>
                            <td><%=equacaoVarianciaStr%></td>
                            <td><%=dmVarianciaStr%></td>
                        </tr>
                        <tr>
                            <td>Desvio Padrão:</td>
                            <td><%=equacaoDesvioPadraoStr%></td>
                            <td><%=dmDesvioPadraoStr%></td>
                        </tr>
                        <tr>
                            <td>Variância Média:</td>
                            <td><%=equacaoVarianciaMediaStr%></td>
                            <td><%=dmVarianciaMediaStr%></td>
                        </tr>
                        <tr>
                            <td>Erro Padrão:</td>
                            <td><%=equacaoErroPadraoStr%></td>
                            <td><%=dmErroPadraoStr%></td>
                        </tr>
                        <tr>
                            <td>Coeficiente de Variação:</td>
                            <td><%=equacaoCoeficienteVariacaoStr%></td>
                            <td><%=dmCoeficienteVariacaoStr%></td>
                        </tr>
                        <tr>
                            <td>Erro Absoluto:</td>
                            <td><%=equacaoErroAbsolutoStr%></td>
                            <td><%=dmErroAbsolutoStr%></td>
                        </tr>
                        <tr>
                            <td>Erro Relativo(%):</td>
                            <td><%=equacaoErroRelativoStr%></td>
                            <td><%=dmErroRelativoStr%></td>
                        </tr>
                        <tr>
                            <td>Intervalo de Confiança Min(Média):</td>
                            <td><%=equacaoIntervaloConfiancaMinMediaStr%></td>
                            <td><%=dmIntervaloConfiancaMinMediaStr%></td>
                        </tr>
                        <tr>
                            <td>Intervalo de Confiança Max(Média):</td>
                            <td><%=equacaoIntervaloConfiancaMaxMediaStr%></td>
                            <td><%=dmIntervaloConfiancaMaxMediaStr%></td>
                        </tr>                        
                        <tr>
                            <td>Valor Total Mínimo:</td>
                            <td><%=equacaoIntervaloConfiancaMinTotalStr%></td>
                            <td><%=dmIntervaloConfiancaMinTotalStr%></td>
                        </tr>
                        <tr>
                            <td>Valor Total Máximo:</td>
                            <td><%=equacaoIntervaloConfiancaMaxTotalStr%></td>
                            <td><%=dmIntervaloConfiancaMaxTotalStr%></td>
                        </tr>                        
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Valor Total Médio:</td>
                            <td><%=equacaoQtdeMediaStr%></td>
                            <td><%=dmQtdeMediaStr%></td>
                        </tr>
                    </tbody>
                </table>
                
                <br />
                <div class="field control-group">
                    <label for="local_descricao" class="control-label">Baixar Planilha dos Valores:</label>
                    <div class="controls">
                        <a href="#" id="btn_inventatario_equacao" class="btn btn-inverse" >Estimados pela Equação</a>
                        <a href="#" id="btn_inventatario_dm" class="btn btn-inverse" >Estimados por Data Mining</a>
                    </div>
                </div>

                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Estatísticas do Ajuste</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <tr>
                            <th></th>
                            <th>por Regressão Linear</th>
                            <th>utilizando Data Mining</th>
                        </tr>                    
                    </thead>
                    <tbody>
                        <tr>
                            <td>R2(Coeficiente de Determinação Múltipla):</td>
                            <td><%=equacaoR2Str%></td>
                            <td><%=dmR2Str%></td>
                        </tr>
                        <tr>
                            <td>R2 Ajustado:</td>
                            <td><%=equacaoR2AjustStr%></td>
                            <td><%=dmR2AjustStr%></td>
                        </tr>
                        <tr>
                            <td>Syx (Erro Padrão da Estimativa:</td>
                            <td><%=equacaoSyxStr%></td>
                            <td><%=dmSyxStr%></td>
                        </tr>
                        <tr>
                            <td>Syx %:</td>
                            <td><%=equacaoSyxPercStr%></td>
                            <td><%=dmSyxPercStr%></td>
                        </tr>
                        <tr>
                            <td>Aic (Critério de Informação de Akaike):</td>
                            <td><%=equacaoAicStr%></td>
                            <td><%=dmAicStr%></td>
                        </tr>
                        <tr>
                            <td>Bic (Critério de Informação Bayesiano ou de Schawartz):</td>
                            <td><%=equacaoBicStr%></td>
                            <td><%=dmBicStr%></td>
                        </tr>
                        <tr>
                            <td>Critério de Willmott:</td>
                            <td><%=equacaoWilmottStr%></td>
                            <td><%=dmWilmottStr%></td>
                        </tr>
                        <tr>
                            <td>Ia (Índice de Schlaegel):</td>
                            <td><%=equacaoIaStr%></td>
                            <td><%=dmIaStr%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados dos Resíduos:</td>
                            <td><%=equacaoSomaQuadradoResiduoStr%></td>
                            <td><%=dmSomaQuadradoResiduoStr%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados dos Regressão:</td>
                            <td><%=equacaoSomaQuadradoRegressaoStr%></td>
                            <td><%=dmSomaQuadradoRegressaoStr%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados Totais:</td>
                            <td><%=equacaoSomaQuadradoTotaisStr%></td>
                            <td><%=dmSomaQuadradoTotaisStr%></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>Quantidade de Vizinhos:</td>
                            <td><%=local.getDmQtdeVizinhos()%></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>Tipo de Distância:</td>
                            <td><%=descricaoTipoDistancia%></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>Tipo de Ponderação:</td>
                            <td><%=descricaoTipoPonderacao%></td>
                        </tr>                    
                        <tr>
                            <td></td>
                            <td>Com LN nas Variáveis:</td>
                            <td><%=descricaoComLn%></td>
                        </tr>   
                    </tbody>
                </table>
                
                <br />
                <div class="field control-group">
                    <label for="local_descricao" class="control-label">Baixar Planilha do Ajuste:</label>
                    <div class="controls">
                        <a href="#" id="btn_ajuste_equacao" class="btn btn-inverse" >Da Equação</a>
                        <a href="#" id="btn_ajuste_dm" class="btn btn-inverse" >Por Data Mining</a>
                    </div>
                </div>
                                                
        </div>
    </body>
</html>

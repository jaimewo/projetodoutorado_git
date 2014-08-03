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
        
        <% EstatisticaInventario estatisticaInventarioEquacao = (EstatisticaInventario) request.getAttribute("estatisticaInventarioEquacao");%>        
        <% EstatisticaInventario estatisticaInventarioDm      = (EstatisticaInventario) request.getAttribute("estatisticaInventarioDm");%>        
        <% EstatisticaAjuste estatisticaAjusteEquacao         = (EstatisticaAjuste) request.getAttribute("estatisticaAjusteEquacao");%>        
        <% EstatisticaAjuste estatisticaAjusteDm              = (EstatisticaAjuste) request.getAttribute("estatisticaAjusteDm");%>  
        <div class="container">
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
                <label for="local_descricao" class="control-label">Descrição</label>
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
                            <td><%=estatisticaInventarioEquacao.getMediaParcela()%></td>
                            <td><%=estatisticaInventarioDm.getMediaParcela()%></td>
                        </tr>
                        <tr>
                            <td>Variância:</td>
                            <td><%=estatisticaInventarioEquacao.getVariancia()%></td>
                            <td><%=estatisticaInventarioDm.getVariancia()%></td>
                        </tr>
                        <tr>
                            <td>Desvio Padrão:</td>
                            <td><%=estatisticaInventarioEquacao.getDesvioPadrao()%></td>
                            <td><%=estatisticaInventarioDm.getDesvioPadrao()%></td>
                        </tr>
                        <tr>
                            <td>Variância Média:</td>
                            <td><%=estatisticaInventarioEquacao.getVarianciaMedia()%></td>
                            <td><%=estatisticaInventarioDm.getVarianciaMedia()%></td>
                        </tr>
                        <tr>
                            <td>Erro Padrão:</td>
                            <td><%=estatisticaInventarioEquacao.getErroPadrao()%></td>
                            <td><%=estatisticaInventarioDm.getErroPadrao()%></td>
                        </tr>
                        <tr>
                            <td>Coeficiente de Variação:</td>
                            <td><%=estatisticaInventarioEquacao.getCoeficienteVariacao()%></td>
                            <td><%=estatisticaInventarioDm.getCoeficienteVariacao()%></td>
                        </tr>
                        <tr>
                            <td>Erro Absoluto:</td>
                            <td><%=estatisticaInventarioEquacao.getErroAbsoluto()%></td>
                            <td><%=estatisticaInventarioDm.getErroAbsoluto()%></td>
                        </tr>
                        <tr>
                            <td>Erro Relativo(%):</td>
                            <td><%=estatisticaInventarioEquacao.getErroRelativo()%></td>
                            <td><%=estatisticaInventarioDm.getErroRelativo()%></td>
                        </tr>
                        <tr>
                            <td>Intervalo de Confiança Min(Média):</td>
                            <td><%=estatisticaInventarioEquacao.getIntervaloConfiancaMinMedia()%></td>
                            <td><%=estatisticaInventarioDm.getIntervaloConfiancaMinMedia()%></td>
                        </tr>
                        <tr>
                            <td>Intervalo de Confiança Max(Média):</td>
                            <td><%=estatisticaInventarioEquacao.getIntervaloConfiancaMaxMedia()%></td>
                            <td><%=estatisticaInventarioDm.getIntervaloConfiancaMaxMedia()%></td>
                        </tr>                        
                        <tr>
                            <td>Valor Total Mínimo:</td>
                            <td><%=estatisticaInventarioEquacao.getIntervaloConfiancaMinTotal()%></td>
                            <td><%=estatisticaInventarioDm.getIntervaloConfiancaMinTotal()%></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Valor Total Mínimo:</td>
                            <td><%=estatisticaInventarioEquacao.getQtdeMedia()%></td>
                            <td><%=estatisticaInventarioDm.getQtdeMedia()%></td>
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
                            <td><%=estatisticaAjusteEquacao.getR2()%></td>
                            <td><%=estatisticaAjusteDm.getR2()%></td>
                        </tr>
                        <tr>
                            <td>R2 Ajustado:</td>
                            <td><%=estatisticaAjusteEquacao.getR2Ajust()%></td>
                            <td><%=estatisticaAjusteDm.getR2Ajust()%></td>
                        </tr>
                        <tr>
                            <td>Syx (Erro Padrão da Estimativa:</td>
                            <td><%=estatisticaAjusteEquacao.getSyx()%></td>
                            <td><%=estatisticaAjusteDm.getSyx()%></td>
                        </tr>
                        <tr>
                            <td>Syx %:</td>
                            <td><%=estatisticaAjusteEquacao.getSyxPerc()%></td>
                            <td><%=estatisticaAjusteDm.getSyxPerc()%></td>
                        </tr>
                        <tr>
                            <td>Aic (Critério de Informação de Akaike):</td>
                            <td><%=estatisticaAjusteEquacao.getAic()%></td>
                            <td><%=estatisticaAjusteDm.getAic()%></td>
                        </tr>
                        <tr>
                            <td>Bic (Critério de Informação Bayesiano ou de Schawartz):</td>
                            <td><%=estatisticaAjusteEquacao.getBic()%></td>
                            <td><%=estatisticaAjusteDm.getBic()%></td>
                        </tr>
                        <tr>
                            <td>Critério de Willmott:</td>
                            <td><%=estatisticaAjusteEquacao.getWillmott()%></td>
                            <td><%=estatisticaAjusteDm.getWillmott()%></td>
                        </tr>
                        <tr>
                            <td>Ia (Índice de Schlaegel):</td>
                            <td><%=estatisticaAjusteEquacao.getIa()%></td>
                            <td><%=estatisticaAjusteDm.getIa()%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados dos Resíduos:</td>
                            <td><%=estatisticaAjusteEquacao.getSomaQuadradoResiduo()%></td>
                            <td><%=estatisticaAjusteDm.getSomaQuadradoResiduo()%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados dos Regressão:</td>
                            <td><%=estatisticaAjusteEquacao.getSomaQuadradoRegressao()%></td>
                            <td><%=estatisticaAjusteDm.getSomaQuadradoRegressao()%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados Totais:</td>
                            <td><%=estatisticaAjusteEquacao.getSomaQuadradoTotais()%></td>
                            <td><%=estatisticaAjusteDm.getSomaQuadradoTotais()%></td>
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

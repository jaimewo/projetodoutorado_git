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
        <% Local local                          = (Local)  request.getAttribute("local");%> 
        <% VariavelInteresse variavelInteresse  = (VariavelInteresse) request.getAttribute("variavelInteresse");%> 
        <% String descricaoMetodoCalculo        = (String) request.getAttribute("descricaoMetodoCalculo");%> 
        <% String descricaoTipoDistancia        = (String) request.getAttribute("descricaoTipoDistancia");%> 
        <% String descricaoTipoPonderacao       = (String) request.getAttribute("descricaoTipoPonderacao");%> 
        <% String descricaoComLn                = (String) request.getAttribute("descricaoComLn");%> 
        
        <% String mediaParcelaStr               = (String) request.getAttribute("mediaParcelaStr");%>   
        <% String coeficienteVariacaoStr        = (String) request.getAttribute("coeficienteVariacaoStr");%>   
        <% String desvioPadraoStr               = (String) request.getAttribute("desvioPadraoStr");%>   
        <% String erroAbsolutoStr               = (String) request.getAttribute("erroAbsolutoStr");%>   
        <% String erroPadraoStr                 = (String) request.getAttribute("erroPadraoStr");%>   
        <% String erroRelativoStr               = (String) request.getAttribute("erroRelativoStr");%>   
        <% String intervaloConfiancaMaxMediaStr = (String) request.getAttribute("intervaloConfiancaMaxMediaStr");%>   
        <% String intervaloConfiancaMinMediaStr = (String) request.getAttribute("intervaloConfiancaMinMediaStr");%>   
        <% String intervaloConfiancaMaxTotalStr = (String) request.getAttribute("intervaloConfiancaMaxTotalStr");%>   
        <% String intervaloConfiancaMinTotalStr = (String) request.getAttribute("intervaloConfiancaMinTotalStr");%>   
        <% String qtdeMediaStr                  = (String) request.getAttribute("qtdeMediaStr");%>   
        <% String varianciaStr                  = (String) request.getAttribute("varianciaStr");%>   
        <% String varianciaMediaStr             = (String) request.getAttribute("varianciaMediaStr");%>   

        <% String r2Str                         = (String) request.getAttribute("r2Str");%>   
        <% String r2AjustStr                    = (String) request.getAttribute("r2AjustStr");%>   
        <% String syxStr                        = (String) request.getAttribute("syxStr");%>   
        <% String syxPercStr                    = (String) request.getAttribute("syxPercStr");%>   
        <% String iaStr                         = (String) request.getAttribute("iaStr");%>   
        <% String aicStr                        = (String) request.getAttribute("aicStr");%>   
        <% String bicStr                        = (String) request.getAttribute("bicStr");%>   
        <% String wilmottStr                    = (String) request.getAttribute("wilmottStr");%>   
        <% String somaQuadradoResiduoStr        = (String) request.getAttribute("somaQuadradoResiduoStr");%>   
        <% String somaQuadradoRegressaoStr      = (String) request.getAttribute("somaQuadradoRegressaoStr");%>   
        <% String somaQuadradoTotaisStr         = (String) request.getAttribute("somaQuadradoTotaisStr");%>   
        
        <div class="container">
            <br />
            <br />
            <h1>Detalhes dos Cálculos</h1>
            <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <strong><%= request.getAttribute("mensagem").toString()%></strong>
                </div>
            <%}%>
            <input type="hidden" name="local[id]" value ="<%=local.getIdString()%>" />
             <div class="field control-group">
                <label for="local_descricao" class="control-label">Local</label>
                <div class="controls">
                    <input type="text" name="local[descricao]" value="<%=local.getDescricao()%>" />
                </div>
            </div>   
            
             <div class="field control-group">
                <label for="variavelInteresse" class="control-label">Variável de Interesse:</label>
                <div class="controls">
                    <input type="text" name="variavelInteresse" value="<%=variavelInteresse%>" />
                </div>
            </div> 
            
             <div class="field control-group">
                <label for="metidiCalculo" class="control-label">Método de Cálculo:</label>
                <div class="controls">
                    <input type="text" name="metodoCalculo" value="<%=metodoCalculo%>" />
                </div>
            </div> 

                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Estatísticas da Estimativa</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Média por Parcela:</td>
                            <td><%=mediaParcelaStr%></td>
                        </tr>
                        <tr>
                            <td>Variância:</td>
                            <td><%=varianciaStr%></td>
                        </tr>
                        <tr>
                            <td>Desvio Padrão:</td>
                            <td><%=desvioPadraoStr%></td>
                        </tr>
                        <tr>
                            <td>Variância Média:</td>
                            <td><%=varianciaMediaStr%></td>
                        </tr>
                        <tr>
                            <td>Erro Padrão:</td>
                            <td><%=erroPadraoStr%></td>
                        </tr>
                        <tr>
                            <td>Coeficiente de Variação:</td>
                            <td><%=coeficienteVariacaoStr%></td>
                        </tr>
                        <tr>
                            <td>Erro Absoluto:</td>
                            <td><%=erroAbsolutoStr%></td>
                        </tr>
                        <tr>
                            <td>Erro Relativo(%):</td>
                            <td><%=erroRelativoStr%></td>
                        </tr>
                        <tr>
                            <td>Intervalo de Confiança Min(Média):</td>
                            <td><%=intervaloConfiancaMinMediaStr%></td>
                        </tr>
                        <tr>
                            <td>Intervalo de Confiança Max(Média):</td>
                            <td><%=intervaloConfiancaMaxMediaStr%></td>
                        </tr>                        
                        <tr>
                            <td>Valor Total Mínimo:</td>
                            <td><%=intervaloConfiancaMinTotalStr%></td>
                        </tr>
                        <tr>
                            <td>Valor Total Máximo:</td>
                            <td><%=intervaloConfiancaMaxTotalStr%></td>
                        </tr>                        
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Valor Total Médio:</td>
                            <td><%=qtdeMediaStr%></td>
                        </tr>
                    </tbody>
                </table>
                
                <br />
                <div class="field control-group">
                    <div class="controls">
                        <a href="#" id="btn_valoresEstimados" class="btn btn-inverse" >Baixar Planilha dos Valores Estimados</a>
                    </div>
                </div>

                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Estatísticas do Ajuste</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>R2(Coeficiente de Determinação Múltipla):</td>
                            <td><%=r2Str%></td>
                        </tr>
                        <tr>
                            <td>R2 Ajustado:</td>
                            <td><%=r2AjustStr%></td>
                        </tr>
                        <tr>
                            <td>Syx (Erro Padrão da Estimativa:</td>
                            <td><%=syxStr%></td>
                        </tr>
                        <tr>
                            <td>Syx %:</td>
                            <td><%=syxPercStr%></td>
                        </tr>
                        <tr>
                            <td>Aic (Critério de Informação de Akaike):</td>
                            <td><%=aicStr%></td>
                        </tr>
                        <tr>
                            <td>Bic (Critério de Informação Bayesiano ou de Schawartz):</td>
                            <td><%=bicStr%></td>
                        </tr>
                        <tr>
                            <td>Critério de Willmott:</td>
                            <td><%=wilmottStr%></td>
                        </tr>
                        <tr>
                            <td>Ia (Índice de Schlaegel):</td>
                            <td><%=iaStr%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados dos Resíduos:</td>
                            <td><%=somaQuadradoResiduoStr%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados dos Regressão:</td>
                            <td><%=somaQuadradoRegressaoStr%></td>
                        </tr>
                        <tr>
                            <td>Soma dos Quadrados Totais:</td>
                            <td><%=somaQuadradoTotaisStr%></td>
                        </tr>
                        <tr>
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
                    <div class="controls">
                        <a href="#" id="btn_ajuste" class="btn btn-inverse" >Baixar Planilha do Ajuste</a>
                    </div>
                </div>
                                                
        </div>
    </body>
</html>

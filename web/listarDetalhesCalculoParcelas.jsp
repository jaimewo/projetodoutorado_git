<%-- 
    Document   : listarDetalhesCalculoParcelas
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
        
        <% String mediaParcelaStr                      = (String) request.getAttribute("mediaParcelaStr");%>   
        <% String coeficienteVariacaoStr               = (String) request.getAttribute("coeficienteVariacaoStr");%>   
        <% String desvioPadraoStr                      = (String) request.getAttribute("desvioPadraoStr");%>   
        <% String erroAbsolutoStr                      = (String) request.getAttribute("erroAbsolutoStr");%>   
        <% String erroPadraoStr                        = (String) request.getAttribute("erroPadraoStr");%>   
        <% String erroRelativoStr                      = (String) request.getAttribute("erroRelativoStr");%>   
        <% String intervaloConfiancaMaxMediaStr        = (String) request.getAttribute("intervaloConfiancaMaxMediaStr");%>   
        <% String intervaloConfiancaMinMediaStr        = (String) request.getAttribute("intervaloConfiancaMinMediaStr");%>   
        <% String intervaloConfiancaMaxTotalStr        = (String) request.getAttribute("intervaloConfiancaMaxTotalStr");%>   
        <% String intervaloConfiancaMinTotalStr        = (String) request.getAttribute("intervaloConfiancaMinTotalStr");%>   
        <% String qtdeMediaStr                         = (String) request.getAttribute("qtdeMediaStr");%>   
        <% String varianciaStr                         = (String) request.getAttribute("varianciaStr");%>   
        <% String varianciaMediaStr                    = (String) request.getAttribute("varianciaMediaStr");%>   
        
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
                                                
        </div>
    </body>
</html>

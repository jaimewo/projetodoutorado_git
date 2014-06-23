<%-- 
    Document   : novoModelo
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Equacao"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Novo Modelo</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Novo Modelo</h1>
           <% Equacao objeto_equacao = (Equacao) request.getAttribute("equacao");%>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>           
        <form action="createEquacao" method="POST" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">
            
            <div class="field control-group">
                <label for="modelo_expressaomodelo" class="control-label">Equação para cálculo de</label>
                <div class="controls">
                    <select name="modelo[equacao_para_calculo_de]">
                        <option value="-1">Selecione uma opção</option>
                        <option value="1">Biomassa (B)</option>
                        <option value="2"> Carbono (C) </option>
                        <option value="3"> Volume (V)</option>
                        
                    </select>
                </div>
            </div>
            
            <div class="field control-group">
                <label for="modelo_expressaomodelo" class="control-label">Modelo</label>
                <div class="controls">
                    <input type="text" name="modelo[expressaoModelo]" value="<%=objeto_equacao.getExpressaoModelo()%>"  class="input-xxlarge" readonly="true"/>
                    <a href="#">-</a>
                </div>
            </div>
                
            <div class="field control-group">
                <label for="modelo_expressaomodelo" class="control-label"><b>Termo</b></label>
                <br />
                <br />
                <div class="controls">
                    <input type="text" name="modelo[termo]" class="input-small" readonly="true" value="b0"/> *
                    <input type="text" name="modelo[termo]" class="input-large" readonly="true" />
                    <a href="#">+</a>
                </div>
            </div>
                
                
            
            <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarModelos" class="btn" >Voltar</a>
            </div>
        </form>
        </div>
        
    </body>
</html>

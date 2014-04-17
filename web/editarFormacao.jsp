<%-- 
    Document   : novoAutor
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bioma"%>
<%@page import="model.Error"%>
<%@page import="model.Formacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Formação</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% Formacao objeto_formacao = (Formacao) request.getAttribute("formacao");%>
        <div class="container">
            <h1>Editar Formação</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                            <br></br>                            
                        <%}%>
                </div>
            <%}%>            
            <form action="updateFormacao" method="POST" class="form-horizontal">
        
            <input type="hidden" name="formacao[id]" value ="<%=objeto_formacao.getIdString()%>" />
             <div class="field control-group">
                <label for="formacao_descricao" class="control-label">Descrição</label>
                <div class="controls">
                    <input type="text" name="formacao[descricao]" value="<%=objeto_formacao.getDescricao()%>" />
                </div>
            </div>

            <div class="field control-group">
                <label for="formacao_bioma" class="control-label">Bioma</label>
                <div class="controls">
                    <select id="formacao_bioma" name="formacao[idBioma]">
                        <option value="">Selecione um bioma</option>
                       <% List<Bioma> biomas = (List<Bioma>) request.getAttribute("biomas");%>
                        <% for (Bioma b : biomas) {%>
                        <option value="<%=b.getIdString()%>"><%=b.getDescricao()%> <%= b.getId() == objeto_formacao.getIdBioma() ? "selected" : "" %></option>
                        <%}%>
                    </select>
                </div>
            </div>
     
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarFormacoes" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

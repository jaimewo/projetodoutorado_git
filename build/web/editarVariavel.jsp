<%-- 
    Document   : novoVariavel
    Created on : 30/03/2014, 22:51:30
    Author     : Jaime
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Variavel"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Variável</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% Variavel objeto_variavel = (Variavel) request.getAttribute("variavel");%>
        <div class="container">
            <h1>Editar Variável</h1>
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
            <form action="updateVariavel" method="POST" class="form-horizontal">
        
            <input type="hidden" name="variavel[id]" value ="<%=objeto_variavel.getIdString()%>" />
             <div class="field control-group">
                <label for="variavel_sigla" class="control-label">Sigla</label>
                <div class="controls">
                    <input type="text" name="variavel[sigla]" value="<%=objeto_variavel.getSigla()%>" />
                </div>
             </div>
             <div class="field control-group">
                <label for="variavel_nome" class="control-label">Nome</label>
                <div class="controls">
                    <input type="text" name="variavel[nome]" value="<%=objeto_variavel.getNome()%>" />
                </div>
             </div>
                
             <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarVariaveis" class="btn" >Voltar</a>
             </div>
        </form>
            
        </div>
    </body>
</html>

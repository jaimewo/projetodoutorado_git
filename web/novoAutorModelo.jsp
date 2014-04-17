<%-- 
    Document   : novoAutorModelo
    Created on : 30/03/2014, 22:51:30
    Author     : jaime
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bioma"%>
<%@page import="model.AutorModelo"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Novo AutorModelo</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Novo Autor de Modelo</h1>
            <% AutorModelo objeto_autorModelo = (AutorModelo) request.getAttribute("autorModelo");%>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>
        <form action="createAutorModelo" method="POST" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">
            <div class="field control-group">
                <label for="autorModelo_nome" class="control-label">Nome</label>
                <div class="controls">
                    <input type="text" name="autorModelo[nome]" value="<%=objeto_autorModelo.getNome()%>" />
                </div>
            </div>
            
            <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarAutoresModelo" class="btn" >Voltar</a>
            </div>
        </form>
        </div>
        
    </body>
</html>

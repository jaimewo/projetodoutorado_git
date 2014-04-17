<%-- 
    Document   : novoAutorModelo
    Created on : 30/03/2014, 22:51:30
    Author     : Jaime
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.AutorModelo"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Autor de Modelo</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% AutorModelo objeto_autorModelo = (AutorModelo) request.getAttribute("autorModelo");%>
        <div class="container">
            <h1>Editar Autor de Modelo</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>            
            <form action="updateAutorModelo" method="POST" class="form-horizontal">
        
            <input type="hidden" name="autorModelo[id]" value ="<%=objeto_autorModelo.getIdString()%>" />
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

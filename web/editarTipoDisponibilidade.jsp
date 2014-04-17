<%-- 
    Document   : novoTipoDisponibilidade
    Created on : 30/03/2014, 22:51:30
    Author     : paulozeferino
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.TipoDisponibilidade"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar TipoDisponibilidade</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% TipoDisponibilidade objeto_tipoDisponibilidade = (TipoDisponibilidade) request.getAttribute("tipoDisponibilidade");%>
        <div class="container">
            <h1>Editar TipoDisponibilidade</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>            
            <form action="updateTipoDisponibilidade" method="POST" class="form-horizontal">
        
            <input type="hidden" name="tipoDisponibilidade[id]" value ="<%=objeto_tipoDisponibilidade.getIdString()%>" />
             <div class="field control-group">
                <label for="tipoDisponibilidade_descricao" class="control-label">Descrição</label>
                <div class="controls">
                    <input type="text" name="tipoDisponibilidade[descricao]" value="<%=objeto_tipoDisponibilidade.getDescricao()%>" />
                </div>
            </div>
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarTiposDisponibilidade" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

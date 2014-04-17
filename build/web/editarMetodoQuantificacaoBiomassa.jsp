<%-- 
    Document   : novoMetodoQuantificacaoBiomassa
    Created on : 30/03/2014, 22:51:30
    Author     : Jaime
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.MetodoQuantificacaoBiomassa"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Método de Quantificação de Biomassa</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% MetodoQuantificacaoBiomassa objeto_metodoQuantificacaoBiomassa = (MetodoQuantificacaoBiomassa) request.getAttribute("metodoQuantificacaoBiomassa");%>
        <div class="container">
            <h1>Editar Método de Quantificação de Biomassa</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>            
            <form action="updateMetodoQuantificacaoBiomassa" method="POST" class="form-horizontal">
        
            <input type="hidden" name="metodoQuantificacaoBiomassa[id]" value ="<%=objeto_metodoQuantificacaoBiomassa.getIdString()%>" />
             <div class="field control-group">
                <label for="metodoQuantificacaoBiomassa_descricao" class="control-label">Descrição</label>
                <div class="controls">
                    <input type="text" name="metodoQuantificacaoBiomassa[descricao]" value="<%=objeto_metodoQuantificacaoBiomassa.getDescricao()%>" />
                </div>
            </div>
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarMetodosQuantificacaoBiomassa" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

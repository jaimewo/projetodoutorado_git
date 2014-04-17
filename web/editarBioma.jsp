<%-- 
    Document   : novoBioma
    Created on : 30/03/2014, 22:51:30
    Author     : paulozeferino
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Bioma"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Bioma</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% Bioma objeto_bioma = (Bioma) request.getAttribute("bioma");%>
        <div class="container">
            <h1>Editar Bioma</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>            
            <form action="updateBioma" method="POST" class="form-horizontal">
        
            <input type="hidden" name="bioma[id]" value ="<%=objeto_bioma.getIdString()%>" />
             <div class="field control-group">
                <label for="bioma_descricao" class="control-label">Descrição</label>
                <div class="controls">
                    <input type="text" name="bioma[descricao]" value="<%=objeto_bioma.getDescricao()%>" />
                </div>
            </div>
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarBiomas" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

<%-- 
    Document   : novoMetodoQuantificacaoCarbono
    Created on : 30/03/2014, 22:51:30
    Author     : Jaime
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.MetodoQuantificacaoCarbono"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Método de Quantificação de Carbono</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% MetodoQuantificacaoCarbono objeto_metodoQuantificacaoCarbono = (MetodoQuantificacaoCarbono) request.getAttribute("metodoQuantificacaoCarbono");%>
        <div class="container">
            <h1>Editar Método de Quantificação de Carbono</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>            
            <form action="updateMetodoQuantificacaoCarbono" method="POST" class="form-horizontal">
        
            <input type="hidden" name="metodoQuantificacaoCarbono[id]" value ="<%=objeto_metodoQuantificacaoCarbono.getIdString()%>" />
             <div class="field control-group">
                <label for="metodoQuantificacaoCarbono_descricao" class="control-label">Descrição</label>
                <div class="controls">
                    <input type="text" name="metodoQuantificacaoCarbono[descricao]" value="<%=objeto_metodoQuantificacaoCarbono.getDescricao()%>" />
                </div>
            </div>
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarMetodosQuantificacaoCarbono" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

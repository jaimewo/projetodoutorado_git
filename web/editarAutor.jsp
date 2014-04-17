<%-- 
    Document   : novoAutor
    Created on : 30/03/2014, 22:51:30
    Author     : Jaime
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Autor"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Autor</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% Autor objeto_autor = (Autor) request.getAttribute("autor");%>
        <div class="container">
            <h1>Editar Autor</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>

            <form action="updateAutor" method="POST" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">
        
            <input type="hidden" name="autor[id]" value ="<%=objeto_autor.getIdString()%>" />
             <div class="field control-group">
                <label for="autor_nome" class="control-label">Nome</label>
                <div class="controls">
                    <input type="text" name="autor[nome]" value="<%=objeto_autor.getNome()%>" />
                </div>
            </div>
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarAutores" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

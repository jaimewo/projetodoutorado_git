<%-- 
    Document   : listarAutoresModelo
    Created on : 30/03/2014, 22:36:41
    Author     : paulozeferino
--%>

<%@page import="java.util.List"%>
<%@page import="model.AutorModelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - AutoresModelo</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Autores de Modelos </h1>            
                <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <strong><%= request.getAttribute("mensagem").toString()%></strong>
                </div>
                <%}%>

                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<AutorModelo> autoresModelo = (List<AutorModelo>) request.getAttribute("autoresModelo");%>
                        <% for (AutorModelo a : autoresModelo) {%>
                        <tr>
                            <td><%=a.getNome()%></td>
                            <td colspan="2">
                                <a href="editarAutorModelo?id=<%=a.getId()%>" class="btn">Editar</a>
                                <a href="excluirAutorModelo?id=<%=a.getId()%>" class="btn btn-danger" onclick="return confirm('CONFIRMA EXCLUSÃO ?');">Excluir</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br />
                <div class="actions form-actions well">
                <a href="novoAutorModelo" class="btn btn-inverse" >Novo</a>
                </div>
        </div>
    </body>
</html>

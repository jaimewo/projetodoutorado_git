<%-- 
    Document   : listarEspacamentos
    Created on : 30/03/2014, 22:36:41
    Author     : paulozeferino
--%>

<%@page import="java.util.List"%>
<%@page import="model.Espacamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - Espacamentos</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
                <h1>Espaçamentos</h1>
                <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <%= request.getAttribute("mensagem").toString()%>
                </div>
                <%}%>
                
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Descrição</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Espacamento> espacamentos = (List<Espacamento>) request.getAttribute("espacamentos");%>
                        <% for (Espacamento a : espacamentos) {%>
                        <tr>
                            <td><%=a.getDescricao()%></td>
                            <td colspan="2">
                                <a href="editarEspacamento?id=<%=a.getId()%>" class="btn">Editar</a>
                                <a href="excluirEspacamento?id=<%=a.getId()%>" class="btn btn-danger" onclick="return confirm('CONFIRMA EXCLUSÃO ?');">Excluir</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br />
                <div class="actions form-actions well">
                <a href="novoEspacamento" class="btn btn-inverse" >Novo</a>
                </div>
        </div>
    </body>
</html>

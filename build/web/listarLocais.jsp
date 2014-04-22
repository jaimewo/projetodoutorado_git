<%-- 
    Document   : listarLocais
    Created on : 30/03/2014, 22:36:41
    Author     : jaimewo
--%>

<%@page import="java.util.List"%>
<%@page import="model.Local"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - Locais</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Locais</h1>
            <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <%= request.getAttribute("mensagem").toString()%>
                </div>
            <%}%>            
                
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Local</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Local> locais = (List<Local>) request.getAttribute("locais");%>
                        <% for (Local l : locais) {%>
                        <tr>
                            <td><%=l.getDescricao()%></td>
                            <td colspan="2">
                                <a href="editarLocal?id=<%=l.getId()%>" class="btn">Editar</a>
                                <a href="excluirLocal?id=<%=l.getId()%>" class="btn btn-danger" onclick="return confirm('CONFIRMA EXCLUSÃO ?');">Excluir</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br />
                <div class="actions form-actions well">
                <a href="novoLocal" class="btn btn-inverse" >Novo</a>
                </div>
        </div>
    </body>
</html>

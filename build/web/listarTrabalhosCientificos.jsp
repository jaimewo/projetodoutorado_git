<%-- 
    Document   : listarTrabalhosCientificos
    Created on : 30/03/2014, 22:36:41
    Author     : jaimewo
--%>

<%@page import="java.util.List"%>
<%@page import="model.TrabalhoCientifico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - TrabalhosCientificos</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
                <h1>Trabalhos Científicos</h1>
                <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <%= request.getAttribute("mensagem").toString()%>
                </div>
                <%}%>
                
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Título</th>
                            <th>Autor</th>
                            <th>Ano</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<TrabalhoCientifico> trabalhosCientificos = (List<TrabalhoCientifico>) request.getAttribute("trabalhosCientificos");%>
                        <% for (TrabalhoCientifico tc : trabalhosCientificos) {%>
                        <tr>
                            <td><%=tc.getTitulo()%></td>
                            <td colspan="2">
                                    <a href="editarTrabalhoCientifico?id=<%=tc.getId()%>" class="btn">Editar</a>
                                <a href="excluirTrabalhoCientifico?id=<%=tc.getId()%>" class="btn btn-danger" onclick="return confirm('CONFIRMA EXCLUSÃO ?');">Excluir</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br />
                <div class="actions form-actions well">
                <a href="novoTrabalhoCientifico" class="btn btn-inverse" >Novo</a>
                </div>
        </div>
    </body>
</html>

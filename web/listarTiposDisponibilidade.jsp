<%-- 
    Document   : listarTiposDisponibilidade
    Created on : 30/03/2014, 22:36:41
    Author     : jaime
--%>

<%@page import="java.util.List"%>
<%@page import="model.TipoDisponibilidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - TiposDisponibilidade</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Tipos de Disponibilidade do Trabalho Científico</h1>
            <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <%= request.getAttribute("mensagem").toString()%>
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
                        <% List<TipoDisponibilidade> tiposDisponibilidade = (List<TipoDisponibilidade>) request.getAttribute("tiposDisponibilidade");%>
                        <% for (TipoDisponibilidade a : tiposDisponibilidade) {%>
                        <tr>
                            <td><%=a.getDescricao()%></td>
                            <td colspan="2">
                                <a href="editarTipoDisponibilidade?id=<%=a.getId()%>" class="btn">Editar</a>
                                <a href="excluirTipoDisponibilidade?id=<%=a.getId()%>" class="btn btn-danger" onclick="return confirm('CONFIRMA EXCLUSÃO ?');">Excluir</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br />
                <div class="actions form-actions well">
                <a href="novoTipoDisponibilidade" class="btn btn-inverse" >Novo</a>
                </div>
        </div>
    </body>
</html>

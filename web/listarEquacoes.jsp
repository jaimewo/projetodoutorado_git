<%-- 
    Document   : listarEquacoes
    Created on : 30/03/2014, 22:36:41
    Author     : jaime
--%>

<%@page import="java.util.List"%>
<%@page import="model.Equacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - Equaçoes</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Equações</h1>
            <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <%= request.getAttribute("mensagem").toString()%>
                </div>
            <%}%>            
                
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Equação</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Equacao> equacoes = (List<Equacao>) request.getAttribute("equacoes");%>
                        <% for (Equacao e : equacoes) {%>
                        <tr>
                            <td><%=e.getExpressaoEquacao()%></td>
                            <td colspan="2">
                                <a href="editarEquacao?id=<%=e.getId()%>" class="btn">Editar</a>
                                <a href="excluirEquacao?id=<%=e.getId()%>" class="btn btn-danger" onclick="return confirm('CONFIRMA EXCLUSÃO ?');">Excluir</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br />
                <div class="actions form-actions well">
                <a href="novaEquacao" class="btn btn-inverse" >Novo</a>
                </div>
        </div>
    </body>
</html>

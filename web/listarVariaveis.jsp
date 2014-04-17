<%-- 
    Document   : listarVariaveis
    Created on : 30/03/2014, 22:36:41
    Author     : paulozeferino
--%>

<%@page import="java.util.List"%>
<%@page import="model.Variavel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon - Variáveis</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
             <h1>Variáveis</h1>
             <%if(request.getAttribute("mensagem") != null && !request.getAttribute("mensagem").toString().isEmpty()){%>
                <div class="alert alert-success">
                    <%= request.getAttribute("mensagem").toString()%>
                </div>
            <%}%>            
               
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Sigla</th>
                            <th>Nome</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Variavel> variaveis = (List<Variavel>) request.getAttribute("variaveis");%>
                        <% for (Variavel v : variaveis) {%>
                        <tr>
                            <td><%=v.getSigla()%></td>
                            <td><%=v.getNome()%></td>
                            <td colspan="2">
                                <a href="editarVariavel?id=<%=v.getId()%>" class="btn">Editar</a>
                                <a href="excluirVariavel?id=<%=v.getId()%>" class="btn btn-danger" onclick="return confirm('CONFIRMA EXCLUSÃO ?');">Excluir</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br />
                <div class="actions form-actions well">
                <a href="novaVariavel" class="btn btn-inverse" >Novo</a>
                </div>
        </div>
    </body>
</html>


<%@page import="model.Local"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Error"%>
<%@page import="model.Autor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Calcular Com Árvores</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Calcular Com Árvores</h1>
            <% Local local = (Local) request.getAttribute("local");%>
           
        <form action="calcularComArvores" method="POST" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">
            <div class="field control-group">
                <label for="local_id" class="control-label">Id do Local:</label>
                <div class="controls">
                    <input type="text" name="local[id]" value="<%=local.getId()%>" />
                </div>
            </div>
            <div class="field control-group">
                <label for="local_qtdeBiomassa" class="control-label">Biomassa:</label>
                <div class="controls">
                    <input type="text" name="local[qtdeBiomassa]" value="<%=local.getQtdeBiomassa()%>" />
                </div>
            </div>
            
            <div class="actions form-actions">
                <input type="submit" name="submit" value ="Calcular" class="btn btn-inverse"/>
                <a href="menu" class="btn" >Voltar</a>
            </div>
        </form>
        </div>
        
    </body>
</html>

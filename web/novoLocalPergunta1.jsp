<%-- 
    Document   : novoLocalDadosBasicos
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="model.TipoFloresta"%>
<%@page import="model.Especie"%>
<%@page import="model.Municipio"%>
<%@page import="model.CoordenadaLocal"%>
<%@page import="model.Espacamento"%>
<%@page import="model.Formacao"%>
<%@page import="model.Bioma"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.TipoDisponibilidade"%>
<%@page import="model.Local"%>
<%@page import="model.Autor"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

        <title>JCarbon - Novo Local</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Novo Local</h1>
            <h2>Você escolheu a Opção 1</h2>
            <h2>Desejo saber os valores padrões de Volume/Biomassa/Carbono para um local com estas características</h2>

            <% Local local = (Local) request.getAttribute("local");%>
            <% String nomeMunicipio = (String) request.getAttribute("nomeMunicipio");%>            
            <% String descricaoBioma = (String) request.getAttribute("descricaoBioma");%>            
            <% String descricaoTipoFloresta = (String) request.getAttribute("descricaoTipoFloresta");%>            
            <% String descricaoFormacao = (String) request.getAttribute("descricaoFormacao");%>            
            <% String descricaoEspecie = (String) request.getAttribute("descricaoEspecie");%>            
            <% String descricaoEspacamento = (String) request.getAttribute("descricaoEspacamento");%>            
            <% String qtdeVolumePadrao = (String) request.getAttribute("qtdeVolumePadrao");%>                        
            <% String qtdeBiomassaPadrao = (String) request.getAttribute("qtdeBiomassaPadrao");%>                        
            <% String qtdeCarbonoPadrao = (String) request.getAttribute("qtdeCarbonoPadrao");%>                                    

            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if (lista_erros != null && lista_erros.size() > 0) {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <%for (Error erro : lista_erros) {%>
                <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                <br></br>
                <%}%>
            </div>
            <%}%>

            <form action="#" method="POST" id="form_local" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">
                        
                        
                <div class="field control-group">
                    <label for="descricaoLocal" class="control-label">Local:</label>
                    <div class="controls">
                        <input type="text" name="descricaoLocal" id="descricaoLocal" value="<%=local.getDescricao()%>" />
                    </div>
                </div>

                <div class="field control-group">
                    <label for="nomeMunicipio" class="control-label">Municipio:</label>
                    <div class="controls">
                        <input type="text" name="nomeMunicipio" id="nomeMunicipio" value="<%=nomeMunicipio%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="latitude" class="control-label">Latitude:</label>
                    <div class="controls">
                        <input type="text" name="latitude" id="latitude" value="<%=local.getLatitude()%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="longitude" class="control-label">Longitude:</label>
                    <div class="controls">
                        <input type="text" name="longitude" id="longitude" value="<%=local.getLongitude()%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="area" class="control-label">Área:</label>
                    <div class="controls">
                        <input type="text" name="area" id="area" value="<%=local.getArea()%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="descricaoBioma" class="control-label">Bioma:</label>
                    <div class="controls">
                        <input type="text" name="descricaoBioma" id="descricaoBioma" value="<%=descricaoBioma%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="descricaoTipoFloresta" class="control-label">Tipo de Floresta:</label>
                    <div class="controls">
                        <input type="text" name="descricaoTipoFloresta" id="descricaoTipoFloresta" value="<%=descricaoTipoFloresta%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="descricaoEspecie" class="control-label">Espécie:</label>
                    <div class="controls">
                        <input type="text" name="descricaoEspecie" id="descricaoEspecie" value="<%=descricaoEspecie%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="idade" class="control-label">Idade:</label>
                    <div class="controls">
                        <input type="text" name="idade" id="idade" value="<%=local.getIdade()%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="descricaoEspacamento" class="control-label">Espaçamento:</label>
                    <div class="controls">
                        <input type="text" name="descricaoEspacamento" id="descricaoEspacamento" value="<%=descricaoEspacamento%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="qtdeVolumePadrao" class="control-label">Volume(ton):</label>
                    <div class="controls">
                        <input type="text" name="qtdeVolumePadrao" id="qtdeVolumePadrao" value="<%=qtdeVolumePadrao%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="qtdeBiomassaPadrao" class="control-label">Biomassa(ton):</label>
                    <div class="controls">
                        <input type="text" name="qtdeBiomassaPadrao" id="qtdeBiomassaPadrao" value="<%=qtdeBiomassaPadrao%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="qtdeCarbonoPadrao" class="control-label">Carbono(ton):</label>
                    <div class="controls">
                        <input type="text" name="qtdeCarbonoPadrao" id="qtdeCarbonoPadrao" value="<%=qtdeCarbonoPadrao%>" />
                    </div>
                </div>

                <div class="actions form-actions well">
                    <a href="#" id="btn_voltar" class="btn btn-inverse" >Voltar</a>
                </div>
            </form>
        </div>

        <br />

    </body>
</html>

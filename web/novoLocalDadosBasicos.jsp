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

            <% Local objeto_local = (Local) request.getAttribute("local");%>

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
                    <label for="local_descricao" class="control-label">Local</label>
                    <div class="controls">
                        <input type="text" name="local[descricao]" id="local_descricao" value="<%=objeto_local.getDescricao()%>" />
                    </div>
                </div>

                <div class="field control-group">
                    <label for="local_municipio" class="control-label">Município</label>
                    <div id="municipios" class="controls">
                        <select id="local_municipio" name="local[idMunicipio]" class="municipios">
                            <option value="">Selecione um município</option>
                            <% List<Municipio> municipios = (List<Municipio>) request.getAttribute("municipios");%>
                            <% for (Municipio m : municipios) {%>
                            <option value="<%=m.getIdString()%>"><%=m.getNome()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="local_latitide" class="control-label">Coordenadas: Latitude</label>
                    <div class="controls">
                        <input type="text" name="local[latitude]" id="local_latitude" value="<%=objeto_local.getLatitude()%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="local_longitude" class="control-label">             Longitude</label>
                    <div class="controls">
                        <input type="text" name="local[longitude]" id="local_longitude" value="<%=objeto_local.getLongitude()%>" />
                    </div>
                </div>

                <div class="field control-group">
                    <label for="local_area" class="control-label">Área Total(ha)</label>
                    <div class="controls">
                        <input type="text" name="local[area]" id="area_total" value="<%=objeto_local.getArea()%>" />
                    </div>
                </div>                    
                    
                <div class="field control-group">
                    <label for="local_bioma" class="control-label">Bioma</label>
                    <div class="controls">
                        <select id="local_bioma" name="local[idBioma]">
                            <option value="">Selecione um bioma</option>
                            <% List<Bioma> biomas = (List<Bioma>) request.getAttribute("biomas");%>
                            <% for (Bioma b : biomas) {%>
                            <option value="<%=b.getIdString()%>"><%=b.getDescricao()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                    
                <div class="field control-group">
                    <label for="local_tipoFloresta" class="control-label">Tipo de Floresta</label>
                    <div class="controls">
                        <select id="local_tipoFloresta" name="local[idTipoFloresta]">
                            <option value="">Selecione o Tipo de Floresta</option>
                            <% List<TipoFloresta> tiposFloresta = (List<TipoFloresta>) request.getAttribute("tiposFloresta");%>
                            <% for (TipoFloresta tf : tiposFloresta) {%>
                            <option value="<%=tf.getIdString()%>"><%=tf.getDescricao()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>

                <div class="field control-group">
                    <label for="local_formacao" class="control-label">Formação</label>
                    <div class="controls">
                        <select id="local_formacao" name="local[idFormacao]">
                            <option value="">Selecione uma formação</option>
                            <% List<Formacao> formacoes = (List<Formacao>) request.getAttribute("formacoes");%>
                            <% for (Formacao f : formacoes) {%>
                            <option value="<%=f.getIdString()%>"><%=f.getDescricao()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>

                <div class="field control-group">
                    <label for="local_dados_povoamento" class="control-label">Dados do Povoamento</label>
                </div>
                        
                <div class="field control-group">
                    <label for="local_especie" class="control-label">Espécie</label>
                    <div class="controls">
                        <select id="local_especie" name="local[idEspecie]">
                            <option value="">Selecione uma espécie</option>
                            <% List<Especie> especies = (List<Especie>) request.getAttribute("especies");%>
                            <% for (Especie e : especies) {%>
                            <option value="<%=e.getIdString()%>"><%=e.getDescricao()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>

                <div class="field control-group">
                    <label for="local_idade" class="control-label">Idade (anos)</label>
                    <div class="controls">
                        <input type="text" name="local[idade]" id="idade" value="<%=objeto_local.getIdade()%>" />
                    </div>
                </div>                                  
                      
                <div class="field control-group">
                    <label for="local_espacamento" class="control-label">Espaçamento</label>
                    <div class="controls">
                        <select id="local_espacamento" name="local[idEspacamento]">
                            <option value="">Selecione um espaçamento</option>
                            <% List<Espacamento> espacamentos = (List<Espacamento>) request.getAttribute("espacamentos");%>
                            <% for (Espacamento e : espacamentos) {%>
                            <option value="<%=e.getIdString()%>"><%=e.getDescricao()%></option>
                            <%}%>
                        </select>
                     </div>
                </div>
                        
                <div class="field control-group">
                    <label for="label_local_perguntas" class="control-label">O que você deseja fazer agora?</label>
                </div>
                    
                    
                    

                <div class="actions form-actions well">
                    <a href="#" id="btn_avancar" class="btn btn-inverse" >Avançar</a>
                </div>
                <div class="actions form-actions well">
                    <a href="#" id="btn_cancelar" class="btn btn-inverse" >Cancelar</a>
                </div>
            </form>
        </div>

        <br />

    </body>
</html>

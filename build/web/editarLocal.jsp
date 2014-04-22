<%-- 
    Document   : editarLocal
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="model.TrabalhoCientifico"%>
<%@page import="model.CoordenadaLocal"%>
<%@page import="model.MunicipioLocal"%>
<%@page import="model.Espacamento"%>
<%@page import="model.Formacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bioma"%>
<%@page import="model.Error"%>
<%@page import="model.Local"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Local</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% Local objeto_local = (Local) request.getAttribute("local");%>
        <% CoordenadaLocal objeto_coordenadaLocal = (CoordenadaLocal) request.getAttribute("coordenadaLocal");%>
        <div class="container">
            <h1>Editar Local</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                            <br></br>                            
                        <%}%>
                </div>
            <%}%>            
            <form action="updateLocal" method="POST" class="form-horizontal">
        
            <input type="hidden" name="local[id]" value ="<%=objeto_local.getIdString()%>" />
             <div class="field control-group">
                <label for="local_descricao" class="control-label">Descrição</label>
                <div class="controls">
                    <input type="text" name="local[descricao]" value="<%=objeto_local.getDescricao()%>" />
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
                <label for="local_area" class="control-label">Área Total(ha)</label>
                <div class="controls">
                    <input type="text" name="local[area]" value="<%=objeto_local.getArea()%>" />
                </div>
            </div>
                
            <div class="field control-group">
                <label for="local_municipio" class="control-label">Município</label>
                <div class="controls">
                    <select id="local_municipio" name="municipioLocal[idMunicipioLocal]">
                        <option value="">Selecione um município</option>
                       <% List<MunicipioLocal> municipiosLocal = (List<MunicipioLocal>) request.getAttribute("municipiosLocal");%>
                        <% for (MunicipioLocal ml : municipiosLocal) {%>
                        <option value="<%=ml.getIdString()%>"><%=ml.getNomeMunicipio()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
                    
            <div class="field control-group">
                <label for="local_coordenada" class="control-label">Coordenadas</label>
                <div class="controls">
                    <input type="text" name="coordenadaLocal[coordenada]" value="<%=objeto_coordenadaLocal.getCoordenada()%>" />
                </div>
            </div> 
                
            <div class="field control-group">
                <label for="local_trabalhoCientifico" class="control-label">Trabalho Científico</label>
                <div class="controls">
                    <select id="local_trabalhoCientifico" name="local[idTrabalhoCientifico]">
                        <option value="">Selecione um Trabalho Científico</option>
                       <% List<TrabalhoCientifico> trabalhosCientificos = (List<TrabalhoCientifico>) request.getAttribute("trabalhosCientificos");%>
                        <% for (TrabalhoCientifico t : trabalhosCientificos) {%>
                        <option value="<%=t.getIdString()%>"><%=t.getTitulo()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
     
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarLocais" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

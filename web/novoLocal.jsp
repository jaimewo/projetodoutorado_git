<%-- 
    Document   : novoLocal
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="model.TrabalhoCientifico"%>
<%@page import="model.MunicipioLocal"%>
<%@page import="model.CoordenadaLocal"%>
<%@page import="model.Espacamento"%>
<%@page import="model.Formacao"%>
<%@page import="model.Bioma"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.TipoDisponibilidade"%>
<%@page import="model.MetodoQuantificacaoBiomassa"%>
<%@page import="model.MetodoQuantificacaoCarbono"%>
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
           <% CoordenadaLocal objeto_coordenadaLocal = (CoordenadaLocal) request.getAttribute("coordenadaLocal");%>
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
        <form action="createLocal" method="POST" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">
            <div class="field control-group">
                <label for="local_descricao" class="control-label">Descrição do Local</label>
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

            <div class="actions form-actions well">
                <a href="novoLocal" class="btn btn-inverse" >Salvar</a>
            </div>
                    
            <div class="field control-group">
                <label for="local_msg_parcelas" class="control-label">Cadastrar Parcelas para que o programa faça a estimativa para o local?</label>
            </div> 
                    
            <div class="field control-group">
                <label for="local_msg_arvores" class="control-label">Cadastrar dados das Árvores para que o programa faça o cálculo de cada parcela e, em seguida, faça a estimativa para o local?</label>
            </div> 
                
            <table class="table table-striped table-condensed">
                <tbody>
                    <tr>
                        <td colspan="2">
                            <a href="editarLocal" class="btn">Escolher Arquivo</a>
                        </td>
                        <td> </td>
                        <td colspan="2">
                            <a href="editarLocal" class="btn">Baixar arquivo de exemplo</a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br />
            
            <div class="actions form-actions well">
                <a href="novoLocal" class="btn btn-inverse" >Calcular</a>
            </div>
                
            <table class="table table-striped table-condensed">
                <tbody>
                    <% List<Local> locais = (List<Local>) request.getAttribute("locais");%>
                    <% for (Local l : locais) {%>
                    <tr>
                        <td><label for="local_biomassa" class="control-label">Biomassa: </label></td>
                        <td><%=l.getDescricao()%></td>
                        <td colspan="2">
                            <a href="editarLocal?id=<%=l.getId()%>" class="btn">Ver Detalhes</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

            <table class="table table-striped table-condensed">
                <tbody>
                    <% for (Local l : locais) {%>
                    <tr>
                        <td><label for="local_carbono" class="control-label">Carbono: </label></td>
                        <td><%=l.getDescricao()%></td>
                        <td colspan="2">
                            <a href="editarLocal?id=<%=l.getId()%>" class="btn">Ver Detalhes</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

            <table class="table table-striped table-condensed">
                <tbody>
                    <% for (Local l : locais) {%>
                    <tr>
                        <td><label for="local_volume" class="control-label">Volume: </label></td>
                        <td><%=l.getDescricao()%></td>
                        <td colspan="2">
                            <a href="editarLocal?id=<%=l.getId()%>" class="btn">Ver Detalhes</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
                
        </form>
        </div>
        
    </body>
</html>

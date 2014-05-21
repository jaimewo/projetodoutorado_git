<%-- 
    Document   : novoLocal
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="model.Municipio"%>
<%@page import="model.TrabalhoCientifico"%>
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
            <form action="#" method="POST" id="form_local" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">

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
                    <label for="local_municipio" class="control-label">Município <a href="#">+</a></label>
                    <div class="controls">
                        <select id="local_municipio" name="municipio[idMunicipio]">
                            <option value="">Selecione um município</option>
                            <% List<Municipio> municipios = (List<Municipio>) request.getAttribute("municipios");%>
                            <% for (Municipio m : municipios) {%>
                            <option value="<%=m.getIdString()%>"><%=m.getNome()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                        
                                <div class="field control-group">
                                    <label for="local_latitude" class="control-label">Latitude,Longitude <a href="#" id="add_lat_long">+</a></label>
                <div id="lat_long" class="controls">
                    <p>
                        (<input type="text" name="coordenadaLocal[latitude]"  />,<input type="text" name="coordenadaLocal[longitude]"  />)
                    </p>
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
                    <a href="#" id="btn_salvar" class="btn btn-inverse" >Salvar</a>
                </div>
            </form>
        </div>
        
            <br />
                        
            <div class="container" id="valor_calculo" style="display:none;">
                <input type="radio" name="valor_calculo" value="1"> Deseja informar valores do Local?<br />
                <input type="radio" name="valor_calculo" value="2"> Deseja cadastrar Parcelas para que o programa faça a estimativa para o Local?<br />
                <input type="radio" name="valor_calculo" value="3"> Deseja cadastrar Árvores para que o programa faça o cálculo de cada parcela e, em seguida, faça a estimativa para o Local?<br />
                
                <br />
                
                 <div class="field control-group">
                     <label for="local_descricao" class="control-label"><b>Arquivo com dados</b></label>
                    <div class="controls">
                        <input type="file" name="arquivo"  />
                        <a href="#" id="btn_modelo" class="btn btn-inverse" >Baixar Modelo</a>
                    </div>
                     <br />
                     <br />
                     <a href="#" id="btn_calcular" class="btn btn-inverse" >Calcular</a>
                </div>
                
            </div>
            
            <br />
            
            

                        
            <script type="text/javascript">
                $("#add_lat_long").click(
                        function(){
                            $("#lat_long").append("<p>(<input type=text \/>,<input type=text \/>)</p>");
                            return false;
                        });
                 $("#btn_salvar").click(function(){
                     alert('Simulação de submter o formulário!!!');
                     $("#valor_calculo").show();
                     
                 });
                        
                        
                        
                
            </script>

    </body>
</html>

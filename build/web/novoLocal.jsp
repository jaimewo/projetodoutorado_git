<%-- 
    Document   : novoLocalDadosBasicos
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

        <title>JCarbon - Novo Local - Dados Básicos</title>
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
                    <label for="local_descricao" class="control-label">Descrição do Local</label>
                    <div class="controls">
                        <input type="text" name="local[descricao]" id="local_descricao" value="<%=objeto_local.getDescricao()%>" />
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
                        <input type="text" name="local[area]" id="area_total" value="<%=objeto_local.getArea()%>" />
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
                    <label for="local_latitude" class="control-label">Latitude,Longitude (opcional):</label>
                    <div id="lat_long" class="controls">
                        <p>
                            (<input type="text" id="local_latitude" name="local[latitude]"  />,<input type="text" name="local[longitude]" id="local_longitude" />)
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
                    <div id="detalhes_trabalho_cientifico" style="width:800px;padding:40px;">

                    </div>
                </div>

                <div class="actions form-actions well">
                    <a href="#" id="btn_salvar" class="btn btn-inverse" >Salvar</a>
                </div>
            </form>
        </div>

        <br />

        <div class="container" id="valor_calculo" style="display:none;">
            <a href="#" class="informar_local_manual">Deseja informar valores do Local?</a> 
            <br />
            <a href="#" class="informar_parcelas">Deseja cadastrar Parcelas para que o programa faça a estimativa para o Local?</a>
            <br />
            <a href="#" class="informar_arvores">Deseja cadastrar Árvores para que o programa faça o cálculo de cada parcela e, em seguida, faça a estimativa para o Local?</a>

            <div id="form_local_arvores" style="display: none;">
                <form action="#" id="form_action_local_arvores" method="POST">
                    <div class="field control-group">
                        <label for="local_descricao" class="control-label"><b>Arquivo com dados</b></label>
                        <div class="controls">
                            <input type="file" name="arquivo"  />
                            <a href="BaixarExemploModeloArvore" id="btn_modelo_arvore" class="btn btn-inverse" target='_blank' >Baixar Exemplo de Arquivo com Árvores</a>
                        </div>
                        <br />
                        <br />
                        <div class="field control-group">


                            <h3><b>Variável de interesse</b></h3>
                            <select id="id_variavel_interesse_arvores">
                                <option value="1">Biomassa</option>
                                <option value="2">Carbono</option>
                                <option value="3">Volume</option>
                            </select>

                            <h3><b>Método de Cálculo</b></h3>
                            <select id="metodo_de_calculo">
                                <option value="" selected>Selecione um método</option>
                                <option value="equacao">Equação</option>
                                <option value="data_mining">Data Mining</option>
                            </select>


                        </div>
                        <div id="sp_calcular_arvore_equacao" style="display:none">
                            <input type="hidden" id="btn_clicado" name="btn_clicado" value="-1" />
                            <span><input type="submit" value="Calcular Usando a Equação" name="btn_submit" id="btn_calcular_equacao" class="btn btn-inverse" >
                                <image src="images/loading.gif" style="display:none;" class="loading">
                                </span>
                            <br />
                            <div class="field control-group">
                                <label for="local_descricao" class="control-label">Total Calculado(t/ha)</label>
                                <div class="controls">
                                    <input type="text"  id="total_calculado_arvore_equacao" disabled="true"  />

                                    <input name="id_variavel_interesse_arvores" id="id_variavel_interesse_arvores" value="id_variavel_interesse_arvores" disabled="false" type="hidden" />
                                </div>
                                <a href="#" id="ver_detalhe_do_calculo_arvore_equacao">Ver detalhes do Cálculo usando Equação</a>
                                <br />
                                <br />
                                <a href="#" class="btn_google_maps btn btn-inverse" target="_blank">Definir Local no Google Maps</a>
                            </div>

                        </div>
                        <div id="sp_calcular_arvore_data_mining" style="display:none">
                            <br />
                            <br />
                            <div class="field control-group">
                                <label for="local_tipo_de_distancia" class="control-label">Tipo de Distância</label>
                                <select id="tipo_distancia">
                                    <option value="" selected>Selecione um tipo</option>
                                    <option value="1">Euclidiana</option>
                                    <option value="2">Quadrática</option>
                                </select>
                            </div>

                            <div class="field control-group">
                                <label for="local_quantidade_de_vizinhos" class="control-label">Quantidade de Vizinhos</label>
                                <div class="controls">
                                    <input type="text"  id="local_quantidade_de_vizinhos"  />
                                </div>
                            </div>

                            <div class="field control-group">
                                <label for="local_tipo_de_ponderacao" class="control-label">Tipo de Ponderação</label>
                                <select id="tipo_ponderacao">
                                    <option value="" selected>Selecione um tipo de ponderação</option>
                                    <option value="1">Sem Ponderação</option>
                                    <option value="2">1/d</option>
                                    <option value="3">1/d2</option>
                                </select>
                            </div>

                            <div class="field control-group">
                                <label for="local_tipo_de_ponderacao" class="control-label">Logarítimo?</label>
                                <select id="tipo_ponderacao">
                                    <option value="" selected>Selecione um tipo de ponderação</option>
                                    <option value="1">Sim</option>
                                    <option value="2">Não</option>

                                </select>
                            </div>



                            <div class="field control-group">
                                <span><input type="submit" value="Calcular Usando Data Mining" name="btn_submit" id="btn_calcular_data_mining" class="btn btn-inverse" >
                                    <image src="images/loading.gif" style="display:none;" class="loading">
                                </span>
                                
                                <label for="local_descricao" class="control-label">Total Calculado(t/ha)</label>
                                <div class="controls">
                                    <input type="text"  id="total_calculado_arvore_data_mining" disabled="true"  />
                                </div>
                                <a href="#" id="ver_detalhe_do_calculo_dm">Ver detalhes do Cálculo usando Data Mining  </a>
                                <br />
                                <br />
                                <br />
                                <br />
                                <a href="#" class="btn_google_maps btn btn-inverse" target="_blank">Definir Local no Google Maps</a>
                            </div>



                        </div>
                    </div>
                </form>

                <div id="dialog_ver_detalhes_calculo_arvore_eq" style="display:none">
                    <a href="#" class="btn btn-inverse" >Baixar Planilha dos Valores Estimados</a>
                    <div id="dialog_ver_detalhes_calculo_arvore_eq_inside">
                        
                    </div>
                    <br />
                </div>
                <div id="dialog_ver_detalhes_calculo_arvore_dm" style="display:none">
                    <a href="#" class="btn btn-inverse" >Baixar Planilha dos Valores Estimados</a>
                    <div id="dialog_ver_detalhes_calculo_arvore_dm_inside">
                        
                    </div>
                    <br />
                </div>
                <script type="text/javascript">
                    $(".btn_google_maps").click(function() {
                        var url = "DefinirGoogleMaps?idLocal=" + $("#local_id").val();
                        window.location.href = url;
                        return false;
                    });


                </script>

            </div>
            <script type="text/javascript">

                $("#metodo_de_calculo").change(function() {
                    // alert();
                    var metodo = $("#metodo_de_calculo").val();
                    if (metodo == "equacao")
                    {
                        $('#sp_calcular_arvore_data_mining').hide();
                        $('#sp_calcular_arvore_equacao').show();
                    } else
                    {
                        $('#sp_calcular_arvore_data_mining').show();
                        $('#sp_calcular_arvore_equacao').hide();
                    }

                });

                $("#ver_detalhe_do_calculo_arvore_equacao").click(function()
                {

                    $.post('listarDetalhesCalculoArvoresEquacao', {variavel_interesse: $("#id_variavel_interesse_arvores").val(),
                        local_id: $("#local_id").val()

                    }, function(responseText) {
                        eval(responseText);
                        $("#dialog_ver_detalhes_calculo_arvore_eq").dialog({title: 'Detalhes do Calculo Usando a Equacao', width: '700', height: '700'});
                    });

                    return false;
                });


                $("#ver_detalhe_do_calculo_dm").click(function()
                {
                    $.post('listarDetalhesCalculoArvoresDm', {variavel_interesse: $("#id_variavel_interesse_arvores").val(),
                        local_id: $("#local_id").val()

                    }, function(responseText) {
                        eval(responseText);
                        $("#dialog_ver_detalhes_calculo_arvore_dm").dialog({title: 'Detalhes do Calculo Usando Data Mining', width: '700', height: '700'});                        
                    });

                    return false;
                });

                $("#btn_calcular_data_mining").click(function() {
                    $("#btn_clicado").val("2");
                });

                $("#btn_calcular_equacao").click(function() {
                    $("#btn_clicado").val("1");

                });

                $("#form_action_local_arvores").submit(function() {
                    //   var val = $("input[type=submit][clicked=true]").val();
                    // alert(val);

                    $(".loading").show();
                    $.post('createLocalCalcularComArvores', {variavel_interesse: $("#id_variavel_interesse_arvores").val(),
                        local_id: $("#local_id_parcela").val(), btn_clicado: $("#btn_clicado").val()

                    }, function(responseText) {
                        eval(responseText);
                        $(".loading").hide();
                    });

                    return false;

                });

                $("#btn_baixar_modelo_arvore").click(function() {
                    $.post('createLocalBaixarModeloArvore', {local_id: $("#local_id").val()
                    }, function(responseText) {
                        eval(responseText);
                    });
                });
            </script>



            <div id="form_local_parcelas" style="display: none;">
                <form action="#" id="form_action_local_parcelas" method="POST">
                    <div class="field control-group">
                        <label for="local_descricao" class="control-label"><b>Arquivo com dados</b></label>
                        <div class="controls">
                            <input type="file" name="arquivo"  />
                            <a href="#" id="btn_baixar_modelo_parcela" class="btn btn-inverse" >Baixar Exemplo Arquivo de Parcelas</a>
                            <input name="local_id_parcela" id="local_id_parcela" type="hidden" />
                        </div>
                        <br />
                        <br />
                        <div class="field control-group">
                            <h4><b>Variável de interesse</b></h4>
                            <select id="variavel_interesse_parcelas">
                                <option value= "1">Biomassa</option>
                                <option value="2">Carbono</option>
                                <option value="3">Volume</option>
                            </select>
                        </div>
                        <span>
                            <span><input type="submit"  value="Calcular valor do local" id="btn_calcular" class="btn btn-inverse" disable="true" />
                                    <image src="images/loading.gif" style="display:none;" class="loading">                            
                            </span>                                    
                            <br />
                            <div class="field control-group">
                                <label for="local_descricao" class="control-label">Total Calculado(t/ha)</label>
                                <div class="controls">
                                    <input type="text"  id="total_calculado_parcela"/>
                                </div>
                                <a href="#" id="ver_detalhe_do_calculo_parcelas">Ver detalhes do Cálculo com Parcelas</a>
                                <br />
                                <br />
                                <a href="#" class="btn_google_maps btn btn-inverse" target="_blank">Definir Local no Google Maps</a>
                            </div>
                        </span>
                    </div>
                </form>
                <div id="dialog_ver_detalhes_calculo_parcelas" style="display:none">
                    <div id="dialog_ver_detalhes_calculo_parcelas_inside">
                        
                    </div>
                    <br />
                </div>

            </div>

            <script type="text/javascript">
                $("#form_action_local_parcelas").submit(function() {
                    
                    $(".loading").show();
                    $.post('createLocalCalcularComParcelas', {variavel_interesse: $("#id_variavel_interesse_arvores").val(),
                        local_id: $("#local_id_parcela").val()

                    }, function(responseText) {
                        eval(responseText);
                        $(".loading").hide();                        
                    });

                    return false;
                });

                $("#btn_baixar_modelo_parcela").click(function() {
                    $.post('createLocalBaixarModeloParcela1', {local_id: $("#local_id").val()
                    }, function(responseText) {
                        eval(responseText);
                    });
                });

                $("#ver_detalhe_do_calculo_parcelas").click(function()
                {
                    $.post('listarDetalhesCalculoParcelas', {variavel_interesse: $("#id_variavel_interesse_arvores").val(),
                        local_id: $("#local_id").val()

                    }, function(responseText) {
                        eval(responseText);
                        $("#dialog_ver_detalhes_calculo_parcelas").dialog({title: 'Detalhes do Calculo com Parcelas', width: '700', height: '700'});                        
                    });

                    return false;
                });
                
                
            </script>

            <div id="form_local_manual" style="display: none;">

                <form>
                    <div class="field control-group">
                        <label for="local_descricao" class="control-label">Biomassa Total(t/ha)</label>
                        <div class="controls">
                            <input type="text" name="local[qtd_biomassa_total]" id="local_biomassa"  />
                            <input type="hidden" name="local[local_id]" id="local_id"/>
                        </div>
                    </div>

                    <div class="field control-group">
                        <label for="local_descricao" class="control-label">Carbono Total(t/ha)</label>
                        <div class="controls">
                            <input type="text" name="local[qtd_carbono_total]" id="local_carbono"  />
                        </div>
                    </div>

                    <div class="field control-group">
                        <label for="local_descricao" class="control-label">Volume Total(t/ha)</label>
                        <div class="controls">
                            <input type="text" name="local[qtd_volume_total]" id="local_volume"  />
                        </div>
                    </div>

                    <div class="actions form-actions well">
                        <a href="#" id="btn_salvar_local_manual" class="btn btn-inverse" >Salvar Valores</a>
                    </div>  

                </form>
            </div>



        </div>

        <br />




        <script type="text/javascript">

            $(".informar_arvores").click(function() {
                $("#form_local_manual").hide();
                $("#form_local_parcelas").hide();
                $("#form_local_arvores").toggle();
                return false;
            });

            $(".informar_parcelas").click(function() {
                $("#form_local_manual").hide();
                $("#form_local_arvores").hide();
                $("#form_local_parcelas").toggle();
                return false;
            });


            $(".informar_local_manual").click(function() {
                $("#form_local_arvores").hide();
                $("#form_local_parcelas").hide();
                $("#form_local_manual").toggle();
                return false;
            })
            $("#add_lat_long").click(
                    function() {
                        $("#lat_long").append("<p>(<input type=text \/>,<input type=text \/>)</p>");
                        return false;
                    });

            $("#local_bioma").change(function() {
                var local_bioma_id = $('#local_bioma').val();
                $("#local_formacao").html("");
                $.get('retornaFormacao', {bioma_id: local_bioma_id}, function(responseText) {

                    $("#local_formacao").html(responseText);
                });


                return false;
            });

            $('#add_municipio').click(function() {

                $.get('retornaCidades', {}, function(responseText) {
                    $("#municipios").append(responseText);
                });

                return false;
            });

            $("#local_trabalhoCientifico").change(function() {
                var trabalho_cientifico = $('#local_trabalhoCientifico').val();
                $.get('retornaDetalhesTrabalhoCientifico', {trabalho_id: trabalho_cientifico}, function(responseText) {

                    $("#detalhes_trabalho_cientifico").html(responseText);
                });


                return false;
            });


            $("#btn_salvar_local_manual").click(function() {

                $.post('createLocalValoresManual', {local_biomassa: $("#local_biomassa").val(),
                    local_carbono: $("#local_carbono").val(),
                    local_volume: $("#local_volume").val(),
                    local_id: $("#local_id").val()
                }, function(responseText) {
                    eval(responseText);
                });

            });

            $("#btn_salvar").click(function() {
                $.post('createLocal', {local_descricao: $("#local_descricao").val(),
                    local_bioma: $("#local_bioma").val(),
                    local_formacao: $("#local_formacao").val(),
                    local_espacamento: $("#local_espacamento").val(),
                    area_total: $("#area_total").val(),
                    local_trabalhoCientifico: $("#local_trabalhoCientifico").val(),
                    local_municipio: $("#local_municipio").val(),
                    local_latitude: $("#local_latitude").val(),
                    local_longitude: $("#local_longitude").val()
                }, function(responseText) {

                    eval(responseText);
                });
            });








        </script>

    </body>
</html>

<%-- 
    Document   : novoLocalPergunta7
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
            <% String descricaoLocal = (String) request.getAttribute("descricaoLocal");%>            
            <% String descricaoMunicipio = (String) request.getAttribute("descricaoMunicipio");%>            
            <% String latitude = (String) request.getAttribute("latitude");%>            
            <% String longitude = (String) request.getAttribute("longitude");%>            
            <% String area = (String) request.getAttribute("area");%>            
            <% String descricaoBioma = (String) request.getAttribute("descricaoBioma");%>            
            <% String descricaoTipoFloresta = (String) request.getAttribute("descricaoTipoFloresta");%>            
            <% String descricaoFormacao = (String) request.getAttribute("descricaoFormacao");%>            
            <% String descricaoEspecie = (String) request.getAttribute("descricaoEspecie");%>            
            <% String idade = (String) request.getAttribute("idade");%>            
            <% String descricaoEspacamento = (String) request.getAttribute("descricaoEspacamento");%>            
            <% String modeloVolumePadrao = (String) request.getAttribute("modeloVolumePadrao");%>                        
                                  

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
                    <label for="opcaoA" class="control-label">Você escolheu a Opção 7</label>
                    <label for="opcaoB" class="control-label">Desejo utilizar um modelo padrão do sistema para ajustar uma equação estimar Volume</label>
                </div>
                        
                <div class="field control-group">
                    <label for="descricaoLocal" class="control-label">Local:</label>
                    <div class="controls">
                        <input type="text" name="descricaoLocal" id="descricaoLocal" value="<%=descricaoLocal%>" />
                    </div>
                </div>

                <div class="field control-group">
                    <label for="descricaoMunicipio" class="control-label">Municipio:</label>
                    <div class="controls">
                        <input type="text" name="descricaoMunicipio" id="descricaoMunicipio" value="<%=descricaoMunicipio%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="latitude" class="control-label">Latitude:</label>
                    <div class="controls">
                        <input type="text" name="latitude" id="latitude" value="<%=latitude%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="longitude" class="control-label">Longitude:</label>
                    <div class="controls">
                        <input type="text" name="longitude" id="longitude" value="<%=longitude%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="area" class="control-label">Área:</label>
                    <div class="controls">
                        <input type="text" name="area" id="area" value="<%=area%>" />
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
                        <input type="text" name="idade" id="idade" value="<%=idade%>" />
                    </div>
                </div>
                        
                <div class="field control-group">
                    <label for="descricaoEspacamento" class="control-label">Espaçamento:</label>
                    <div class="controls">
                        <input type="text" name="descricaoEspacamento" id="descricaoEspacamento" value="<%=descricaoEspacamento%>" />
                    </div>
                </div>

                        
                <div class="field control-group">
                    <label for="modeloVolumePadrao" class="control-label">Modelo de Volume Padrão para este local:</label>
                    <div class="controls">
                        <input type="text" name="modeloVolumePadrao" id="modeloVolumePadrao" value="<%=modeloVolumePadrao%>" />
                    </div>
                </div>
                    
                    
            <div id="form_local_arvores_ajuste" style="display: none;">
                <form action="#" id="form_action_local_arvores_ajuste" method="POST">
                    <div class="field control-group">
                        <label for="local_descricao" class="control-label"><b>Selecionar Arquivo com Árvores para Ajustar o Modelo</b></label>
                        <div class="controls">
                            <input type="file" name="arquivo"  />
                            <a href="BaixarExemploModeloArvoreAjuste" id="btn_modelo_arvore_ajuste" class="btn btn-inverse" target='_blank' >Baixar Arquivo para Utilizar como Exemplo</a>
                        </div>
                        <br />
                        <br />
                        <div id="sp_ajustar" style="display:none">
                            <input type="hidden" id="btn_clicado" name="btn_clicado" value="-1" />
                            <span><input type="submit" value="Ajustar" name="btn_submit" id="btn_ajustar" class="btn btn-inverse" >
                                <image src="images/loading.gif" style="display:none;" class="loading">
                                </span>
                            <br />
                            <div class="field control-group">
                                <label for="local_descricao" class="control-label">Equação Ajustada</label>
                                <div class="controls">
                                    <input type="text"  id="equacao_ajustada" disabled="true"  />
                                </div>
                                <a href="#" id="ver_estatistica_ajuste">Ver Estatísticas do Ajuste</a>
                                <br />
                                <br />
                            </div>

                        </div>

                    </div>
                </form>

                    
                    
            <div id="form_local_arvores" style="display: none;">
                <form action="#" id="form_action_local_arvores" method="POST">
                    <div class="field control-group">
                        <label for="local_descricao" class="control-label"><b>Selecionar Arquivo com dados das Árvores</b></label>
                        <div class="controls">
                            <input type="file" name="arquivo"  />
                            <a href="BaixarExemploModeloArvore" id="btn_modelo_arvore" class="btn btn-inverse" target='_blank' >Baixar Exemplo de Arquivo com Árvores</a>
                        </div>
                        <br />
                        <br />
                        <div class="field control-group">

                            <h3><b>Método de Cálculo</b></h3>
                            <select id="metodo_de_calculo">
                                <option value="" selected>Selecione um método</option>
                                <option value="equacao">Equação</option>
                                <option value="data_mining">Data Mining</option>
                            </select>


                        </div>
                        <div id="sp_calcular_arvore_equacao" style="display:none">
                            <input type="hidden" id="btn_clicado" name="btn_clicado" value="-1" />
                            <span><input type="submit" value="Calcular o Volume Usando a Equação" name="btn_submit" id="btn_calcular_equacao" class="btn btn-inverse" >
                                <image src="images/loading.gif" style="display:none;" class="loading">
                                </span>
                            <br />
                            <div class="field control-group">
                                <label for="local_descricao" class="control-label">Volume Calculado(ton)</label>
                                <div class="controls">
                                    <input type="text"  id="total_calculado_arvore_equacao" disabled="true"  />

                                    <input name="id_variavel_interesse_arvores" id="id_variavel_interesse_arvores" value="id_variavel_interesse_arvores" disabled="false" type="hidden" />
                                </div>
                                <a href="#" id="ver_detalhe_do_calculo_arvore_equacao">Ver detalhes do Cálculo usando Equação</a>
                                <br />
                                <br />
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
                                <span><input type="submit" value="Calcular Volume Usando Data Mining" name="btn_submit" id="btn_calcular_data_mining" class="btn btn-inverse" >
                                    <image src="images/loading.gif" style="display:none;" class="loading">
                                </span>
                                
                                <label for="local_descricao" class="control-label">Volume Calculado(ton)</label>
                                <div class="controls">
                                    <input type="text"  id="total_calculado_arvore_data_mining" disabled="true"  />
                                </div>
                                <a href="#" id="ver_detalhe_do_calculo_dm">Ver detalhes do Cálculo usando Data Mining  </a>
                                <br />
                            </div>

                        <div class="field control-group">
                            <h4><b>Cálculo de Biomassa/Carbono</b></h4>
                            <select id="tipo_calculo_biomassa_carbono">
                                <option value="1">Pretendo Usar Funções de Conversão a partir do Volume</option>
                                <option value="2">Pretendo Usar Equações de Biomassa/Carbono</option>
                            </select>
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
                    
                    
                    
                <div class="actions form-actions well">
                    <a href="#" id="btn_voltar" class="btn btn-inverse" >Voltar</a>
                </div>
            </form>
        </div>

        <br />

    </body>
</html>

<%-- 
    Document   : novoLocalPergunta5
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="model.Equacao"%>
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
                    <label for="opcaoA" class="control-label">Você escolheu a Opção 5</label>
                    <label for="opcaoB" class="control-label">Desejo selecionar uma equação cadastrada no sistema para estimar o Volume</label>
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
                    <label for="equacoes" class="control-label">Escolha uma Equação</label>
                    <div id="municipios" class="controls">
                        <select id="local_municipio" name="local[idMunicipio]" class="municipios">
                            <option value="">Selecione um município</option>
                            <% ArrayList<Equacao> equacoesVolumePadrao = (ArrayList<Equacao>) request.getAttribute("equacoesVolumePadrao");%>                        
                            <% for (Equacao e : equacoesVolumePadrao) {%>
                            <option value="<%=e.getIdString()%>"><%=e.getExpressaoEquacaoFormatada()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                        
            <div id="form_local_parcelas" style="display: none;">
                <form action="#" id="form_action_local_parcelas" method="POST">
                    <div class="field control-group">
                        <label for="local_arquivo_parcelas" class="control-label"><b>Arquivo com dados</b></label>
                        <div class="controls">
                            <input type="file" name="arquivo"  />
                            <a href="#" id="btn_baixar_modelo_parcela" class="btn btn-inverse" >Baixar Exemplo Arquivo de Parcelas</a>
                            <input name="local_id_parcela" id="local_id_parcela" type="hidden" />
                        </div>
                        <br />
                        <br />
                        <span>
                            <span><input type="submit"  value="Calcular valor do local" id="btn_calcular" class="btn btn-inverse" disable="true" />
                                    <image src="images/loading.gif" style="display:none;" class="loading">                            
                            </span>                                    
                            <br />
                            <div class="field control-group">
                                <label for="local_descricao" class="control-label">Volume Calculado do Local(ton)</label>
                                <div class="controls">
                                    <input type="text"  id="total_calculado_parcela"/>
                                </div>
                                <a href="#" id="ver_detalhe_do_calculo_parcelas">Ver detalhes do Cálculo com Parcelas</a>
                                <br />
                            </div>
                        </span>
                        <div class="field control-group">
                            <h4><b>Cálculo de Biomassa/Carbono</b></h4>
                            <select id="tipo_calculo_biomassa_carbono">
                                <option value="1">Pretendo Usar Funções de Conversão a partir do Volume</option>
                                <option value="2">Pretendo Usar Equações de Biomassa/Carbono</option>
                            </select>
                        </div>
                        
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
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                <div class="actions form-actions well">
                    <a href="#" id="btn_salvar" class="btn btn-inverse" >Salvar</a>
                </div>
            </form>
        </div>

        <br />

    </body>
</html>

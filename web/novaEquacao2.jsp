<%-- 
    Document   : novaEquacao
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="model.Variavel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Equacao"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <title>JCarbon - Nova Equação 2</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Nova Equação</h1>

            <form action="createEquacao" method="POST" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">

                <div class="field control-group">
                    <label for="equacao_constante" class="control-label">Constante:</label>
                    <div class="controls">
                        <input type="text"  id="equacao_constante"  />
                        <span id="inserir_constante_na_equacao">
                            <a href="#" id="inserir_constante" class="btn btn-inverse" >Inserir na equação</a>
                        </span>
                    </div>
                </div>


                <div class="field control-group">
                    <label for="variavel" class="control-label">Variável:</label>
                    <div class="controls">
                        <select id="variavel" name="variavel[idVariavel]">
                             <option value="">Selecione uma variável</option>
                             <% List<Variavel> variaveis = (List<Variavel>) request.getAttribute("variaveis");%>
                        <% for (Variavel v : variaveis) {%>
                        <option value="<%=v.getSigla()%>"><%=v.getSigla()%></option>
                        <%}%>

                        </select>
                    </div>
                </div>


                <div class="field control-group">
                    <label for="operadores" class="control-label ">Operadores</label>
                </div>
                <div style='padding-left: 120px'>     
                    <div id="operador_mais" style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >+</a>
                    </div>
                    <div id="operador_menos" style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >-</a>
                    </div>
                    <div id="operador_vezes" style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >*</a>
                    </div>
                    <div id="operador_dividir" style='float:left;padding:5px'   >
                        <a href="#" class="btn btn-inverse operador" >/</a>
                    </div>

                    <div id="operador_potencia">
                        <a href="#" class="btn btn-inverse operador" style='float:left;margin:5px' >^</a>
                    </div>
                    <br clear='both' />
                    <div id="operador_raiz"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >Sqrt</a>
                    </div>
                    <div id="operador_ln"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >LN</a>
                    </div>
                    <div id="operador_log"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >Log</a>
                    </div>
                    <div id="operador_exponencial"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >EXP</a>
                    </div>
                    <div id="operador_abre_parenteses"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >(</a>
                    </div>
                    <div id="operador_fecha_parenteses"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse operador" >)</a>
                    </div>
                    <br clear='both' />
                    <div id="operador_limpa_ultimo"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse" id='limpa_ultimo' >Limpa Último</a>
                    </div>
                    <div id="operador_limpa_tudo"  style='float:left;padding:5px'>
                        <a href="#" class="btn btn-inverse" id='limpa_tudo' >Limpa Tudo</a>
                    </div>
                </div>
                <br clear='both' />
                <br />

                <div class="field control-group">
                    <label for="equacao_expressaoequacao" class="control-label">Equação</label>
                    <div class="controls">
                        <input type="text" id="expressao_equacao" name="equacao[expressaoEquacao]"  />
                    </div>
                </div>

                <div class="actions form-actions">
                    <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                    <a href="listarEquacoes" class="btn" >Voltar</a>
                </div>
            </form>

        </div>

    </body>

    <script type="text/javascript">
        var equacao = new Array();

        $('.operador').click(function() {
            equacao.push($(this).text());
            juntaEquacao();
            return false;
        });

        $('#variavel').change(function() {

            equacao.push($('#variavel').val());
            juntaEquacao();
            return false;
        });

        $('#inserir_constante').click(function() {

            equacao.push($('#equacao_constante').val());
            juntaEquacao();
            return false;
        });

        $('#limpa_ultimo').click(function() {
            limpa_ultimo();
            juntaEquacao();
            return false;
        });

        function limpa_ultimo()
        {
            equacao.pop();
            juntaEquacao();

        }

        $('#limpa_tudo').click(function() {
            limpaTudo();
            return false;
        });

        function limpaTudo()
        {
            $('#expressao_equacao').val('');
            equacao = new Array();
        }

        function juntaEquacao()
        {
            var retorno = "";
            for (var i = 0; i < equacao.length; i++)
            {
                retorno += equacao[i];
            }


            $('#expressao_equacao').val(retorno);
        }

    </script>
</html>
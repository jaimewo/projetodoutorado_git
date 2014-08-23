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
         <title>JCarbon - Nova Equação</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <div class="container">
            <h1>Nova Equação</h1>
           <% Equacao objeto_equacao = (Equacao) request.getAttribute("equacao");%>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                </div>
            <%}%>           
            
        <form action="createEquacao" method="POST" class="form-horizontal"  accept-charset="iso-8859-1,utf-8">
            
            <div class="field control-group">
                <label for="equacao_constante" class="control-label">Constante:</label>
                <div class="controls">
                    <input type="text"  id="equacao_constante"  />
                </div>
            </div>
            <div id="inserir_constante_na_equacao">
                <a href="#" class="btn btn-inverse" >Inserir na equação</a>
            </div>

            <div class="field control-group">
                <label for="variavel" class="control-label">Variável:</label>
                <div class="controls">
                    <select id="variavel" name="variavel[idVariavel]">
                        <option value="">Selecione uma variável</option>
                        <% List<Variavel> variaveis = (List<Variavel>) request.getAttribute("variaveis");%>
                        <% for (Variavel v : variaveis) {%>
                        <option value="<%=v.getIdString()%>"><%=v.getSigla()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div id="inserir_constante_na_equacao">
                <a href="#" class="btn btn-inverse" >Inserir na equação</a>
            </div>
            
            <div class="field control-group">
                <label for="operadores" class="control-label">Operacores:</label>
            </div>
            <div id="operador_mais">
                <a href="#" class="btn btn-inverse" >+</a>
            </div>
            <div id="operador_menos">
                <a href="#" class="btn btn-inverse" >-</a>
            </div>
            <div id="operador_vezes">
                <a href="#" class="btn btn-inverse" >*</a>
            </div>
            <div id="operador_dividir">
                <a href="#" class="btn btn-inverse" >/</a>
            </div>
            <div id="operador_potencia">
                <a href="#" class="btn btn-inverse" >^</a>
            </div>
            <div id="operador_raiz">
                <a href="#" class="btn btn-inverse" >Sqrt</a>
            </div>
            <div id="operador_ln">
                <a href="#" class="btn btn-inverse" >LN</a>
            </div>
            <div id="operador_log">
                <a href="#" class="btn btn-inverse" >Log</a>
            </div>
            <div id="operador_exponencial">
                <a href="#" class="btn btn-inverse" >EXP</a>
            </div>
            <div id="operador_abre_parenteses">
                <a href="#" class="btn btn-inverse" >(</a>
            </div>
            <div id="operador_fecha_parenteses">
                <a href="#" class="btn btn-inverse" >)</a>
            </div>
            <div id="operador_limpa_ultimo">
                <a href="#" class="btn btn-inverse" >Limpa Último</a>
            </div>
            <div id="operador_limpa_tudo">
                <a href="#" class="btn btn-inverse" >Limpa Tudo</a>
            </div>
            
            
            
            <div class="field control-group">
                <label for="equacao_expressaoequacao" class="control-label">Equação</label>
                <div class="controls">
                    <input type="text" name="equacao[expressaoEquacao]" value="<%=objeto_equacao.getExpressaoEquacao()%>" />
                </div>
            </div>
            
            <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarEquacoes" class="btn" >Voltar</a>
            </div>
        </form>
        </div>
        
    </body>
</html>

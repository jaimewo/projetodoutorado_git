<%-- 
    Document   : novoTrabalhoCientiico
    Created on : 30/03/2014, 22:51:30
    Author     : jaimewo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.TipoDisponibilidade"%>
<%@page import="model.MetodoQuantificacaoBiomassa"%>
<%@page import="model.MetodoQuantificacaoCarbono"%>
<%@page import="model.TrabalhoCientifico"%>
<%@page import="model.Autor"%>
<%@page import="model.Error"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
         <title>JCarbon - Editar Trabalho Cientifico</title>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
        <% TrabalhoCientifico objeto_trabalhoCientifico = (TrabalhoCientifico) request.getAttribute("trabalhoCientifico");%>
        <div class="container">
            <h1>Editar Trabalho Científico</h1>
            <% ArrayList<Error> lista_erros = (ArrayList<Error>) request.getAttribute("erros");%>
            <%if(lista_erros != null && lista_erros.size() >0 ){%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <%for(Error erro:lista_erros){%>
                            <strong><%=erro.getCampo()%></strong><%=erro.getMensagem()%>
                        <%}%>
                        <br></br>
                </div>
            <%}%>            
            <form action="updateTrabalhoCientifico" method="POST" class="form-horizontal">
        
            <input type="hidden" name="trabalhoCientifico[id]" value ="<%=objeto_trabalhoCientifico.getIdString()%>" />
             <div class="field control-group">
                <label for="trabalhoCientifico_titulo" class="control-label">Título</label>
                <div class="controls">
                    <input type="text" name="trabalhoCientifico[titulo]" value="<%=objeto_trabalhoCientifico.getTitulo()%>" />
                </div>
            </div>

            <div class="field control-group">
                <label for="trabalhoCientifico_autor" class="control-label">Autor</label>
                <div class="controls">
                    <select id="trabalhoCientifico_autor" name="trabalhoCientifico[idAutor]">
                        <option value="">Selecione um autor</option>
                       <% List<Autor> autores = (List<Autor>) request.getAttribute("autores");%>
                        <% for (Autor a : autores) {%>
                        <option value="<%=a.getIdString()%>"><%=a.getNome()%></option>
                        <%}%>
                    </select>
                </div>
            </div>

             <div class="field control-group">
                <label for="trabalhoCientifico_ano" class="control-label">Ano</label>
                <div class="controls">
                    <input type="text" name="trabalhoCientifico[ano]" value="<%=objeto_trabalhoCientifico.getAno()%>" />
                </div>
            </div>
                
            <div class="field control-group">
                <label for="trabalhoCientifico_tipoDisponibilidade" class="control-label">Tipo de Disponibilidade</label>
                <div class="controls">
                    <select id="trabalhoCientifico_tipoDisponibilidade" name="trabalhoCientifico[idTipoDisponibilidade]">
                        <option value="">Selecione uma disponibilidade</option>
                       <% List<TipoDisponibilidade> tiposDisponibilidade = (List<TipoDisponibilidade>) request.getAttribute("tiposDisponibilidade");%>
                        <% for (TipoDisponibilidade td : tiposDisponibilidade) {%>
                        <option value="<%=td.getIdString()%>"><%=td.getDescricao()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
                
            <div class="field control-group">
                <label for="trabalhoCientifico_metodoQuantificacaoBiomassa" class="control-label">Método de Quantificação de Biomassa</label>
                <div class="controls">
                    <select id="trabalhoCientifico_metodoQuantificacaoBiomassa" name="trabalhoCientifico[idMetodoQuantificacaoBiomassa]">
                        <option value="">Selecione um método de quantificação da Biomassa</option>
                       <% List<MetodoQuantificacaoBiomassa> metodosQuantificacaoBiomassa = (List<MetodoQuantificacaoBiomassa>) request.getAttribute("metodosQuantificacaoBiomassa");%>
                        <% for (MetodoQuantificacaoBiomassa mqb : metodosQuantificacaoBiomassa) {%>
                        <option value="<%=mqb.getIdString()%>"><%=mqb.getDescricao()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
                
            <div class="field control-group">
                <label for="trabalhoCientifico_metodoQuantificacaoCarbono" class="control-label">Método de Quantificação de Carbono</label>
                <div class="controls">
                    <select id="trabalhoCientifico_metodoQuantificacaoCarbono" name="trabalhoCientifico[idMetodoQuantificacaoCarbono]">
                        <option value="">Selecione um método de quantificação do carbono</option>
                       <% List<MetodoQuantificacaoCarbono> metodosQuantificacaoCarbono = (List<MetodoQuantificacaoCarbono>) request.getAttribute("metodosQuantificacaoCarbono");%>
                        <% for (MetodoQuantificacaoCarbono mqc : metodosQuantificacaoCarbono) {%>
                        <option value="<%=mqc.getIdString()%>"><%=mqc.getDescricao()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
     
            
           <div class="actions form-actions">
                <input type="submit" name="submit" value ="Salvar" class="btn btn-inverse"/>
                <a href="listarTrabalhosCientificos" class="btn" >Voltar</a>
           </div>
        </form>
            
        </div>
    </body>
</html>

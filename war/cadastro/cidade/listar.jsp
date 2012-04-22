<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.com.appestoque.dominio.cadastro.Cidade" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>

<span class="title"><%=bundle.getString("cidade.pesquisar.titulo")%></span>

	<form id="formListar" method="post" action="/cidadeControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
	
		<app:parametrosListar/>
		<app:barraListar acao="/cidadeControle"/>
		
		<script>
			$("#buscar").click(function () {
		      if(document.getElementById('nome').value.length==0){
		    	  alert('<%=bundle.getString("pesquisa.semFiltro")%>');
		    	  document.getElementById('nome').focus();
		      }else{
		    	  document.forms[0].submit();		  
		      }
		    });
	    </script>
		
		<span class="heading"><%=bundle.getString("cidade.nome")%></span><br/>
		<input type="text" id="nome" name="nome" class="text ui-widget-content ui-corner-all" style="width: 300px" value="<%=request.getAttribute("nome")!=null?request.getAttribute("nome"):""%>"/>	
	<%
		List<Cidade> objetos = new ArrayList<Cidade>();
		if(request.getAttribute("objetos")!=null){
			objetos = (List<Cidade>)request.getAttribute("objetos");
		} 
	%>
	<p/>	
	<% if (objetos.size() > 0) { %>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header">
			<td><%=bundle.getString("cidade.nome")%></td>
			<td width="15%"/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Cidade objeto = (Cidade) objetos.get(i); %>
			<tr>
				<td><%=objeto.getNome()%></td>
				<td align="center" >
					<a id="edt" href="/cidadeControle?acao=editar&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.editar")%>" src="img/editar.png" style="border: 0px;"/></a>
					<a href="/cidadeControle?acao=remover&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.remover")%>" src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/cidadeControle"/>
	<% } else { %>
		<span class="heading"><%=bundle.getString("pesquisa.nenhumresultado")%></span>
	<% } %>
	</form>
	
<%@include file="../../modelo/fim.jspf" %>
<%@page import="br.com.appestoque.Constantes"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.com.appestoque.dominio.suprimento.Produto" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>

<span class="title"><%=bundle.getString("produto.pesquisar.titulo")%></span>

	<form id="formListar" method="post" action="/produtoControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
	
		<app:parametrosListar/>
		<app:barraListar acao="/produtoControle"/>
		
		<script>
			$("#buscar").click(function () {
		      if(document.getElementById('numero').value.length==0){
		    	  alert('<%=bundle.getString("pesquisa.semFiltro")%>');
		    	  document.getElementById('numero').focus();
		      }else{
		    	  document.forms[0].submit();		  
		      }
		    });
	    </script>
		
		<span class="heading"><%=bundle.getString("produto.nome")%></span><br/>
		<input type="text" id="numero" name="numero" class="text ui-widget-content ui-corner-all" style="width: 300px" value="<%=request.getAttribute("numero")!=null?request.getAttribute("numero"):""%>"/>	
	<%
		List<Produto> objetos = new ArrayList<Produto>();
		if(request.getAttribute("objetos")!=null){
			objetos = (List<Produto>)request.getAttribute("objetos");
		} 
	%>
	<p/>	
	<% if (objetos.size() > 0) { %>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header ">
			<td><%=bundle.getString("produto.nome")%></td>
			<td><%=bundle.getString("produto.numero")%></td>
			<td align="right"><%=bundle.getString("produto.preco")%></td>
			<td width="15%"/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Produto objeto = (Produto) objetos.get(i); %>
			<tr>
				<td><%=objeto.getNome()%></td>
				<td><%=objeto.getNumero()%></a></td>
				<td align="right"><fmt:formatNumber value="<%=objeto.getPreco()%>" type="currency" pattern="<%=br.com.appestoque.Constantes.MASCARA_PRECO%>"/></td>
				<td align="center" >
					<a id="edt" href="/produtoControle?acao=editar&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.editar")%>" src="img/editar.png" style="border: 0px;"/></a>
					<a href="/produtoControle?acao=remover&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.remover")%>" src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/produtoControle"/>
	<% } else { %>
		<span class="heading"><%=bundle.getString("pesquisa.nenhumresultado")%></span>
	<% } %>
	</form>
	
<%@include file="../../modelo/fim.jspf" %>
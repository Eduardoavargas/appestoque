<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.com.appestoque.dominio.cadastro.Representante" %>
<%@ page import="br.com.appestoque.util.Constantes" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>

<span class="title"><%=bundle.getString("representante.pesquisar.titulo")%></span>

	<form id="formListar" method="post" action="/representanteControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
	
		<app:parametrosListar/>
		<app:barraListar acao="/representanteControle"/>
		
		<script>
			$("#buscar").click(function () {
		    	  document.forms[0].submit();		  
		    });
	    </script>
		
		<span class="heading"><%=bundle.getString("representante.filtro.cpf")%></span><br/>
		<input type="text" id="cnpj" name="cpf" style="width: 300px" value="<%=request.getAttribute("cpf")!=null?request.getAttribute("cpf"):""%>"/>	
	<%
		List<Representante> objetos = new ArrayList<Representante>();
		if(request.getAttribute("objetos")!=null){
			objetos = (List<Representante>)request.getAttribute("objetos");
		} 
	%>
	<p/>	
	<% if (objetos.size() > 0) { %>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header">
			<td><%=bundle.getString("cpf")%></td>
			<td><%=bundle.getString("nome")%></td>
			<td width="15%"/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Representante objeto = (Representante) objetos.get(i); %>
			<tr>
				<td><%=objeto.getCpf()%></td>
				<td><%=objeto.getNome()%></a></td>
				<td align="center" >
					<a id="edt" href="/representanteControle?acao=editar&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.editar")%>" src="img/editar.png" style="border: 0px;"/></a>
					<a href="/representanteControle?acao=remover&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.remover")%>" src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/representanteControle"/>
	<% } else { %>
		<span class="heading"><%=bundle.getString("pesquisa.nenhumresultado")%></span>
	<% } %>
	</form>
	
<%@include file="../../modelo/fim.jspf" %>
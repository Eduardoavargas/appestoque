<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.com.appestoque.dominio.cadastro.Empresa" %>
<%@ page import="br.com.appestoque.util.Constantes" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>

<span class="title"><%=bundle.getString("empresa.pesquisar.titulo")%></span>

	<form id="formListar" method="post" action="/empresaControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
	
		<app:parametrosListar/>
		<app:barraListar acao="/empresaControle"/>
		
		<script>
			$("#buscar").click(function () {
		    	  document.forms[0].submit();		  
		    });
	    </script>
		
		<span class="heading"><%=bundle.getString("empresa.filtro.numero")%></span><br/>
		<input type="text" id="cnpj" name="cnpj" style="width: 300px" value="<%=request.getAttribute("cnpj")!=null?request.getAttribute("cnpj"):""%>"/>	
	<%
		List<Empresa> objetos = new ArrayList<Empresa>();
		if(request.getAttribute("objetos")!=null){
			objetos = (List<Empresa>)request.getAttribute("objetos");
		} 
	%>
	<p/>	
	<% if (objetos.size() > 0) { %>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header">
			<td><%=bundle.getString("cnpj")%></td>
			<td><%=bundle.getString("empresa.nome")%></td>
			<td width="15%"/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Empresa objeto = (Empresa) objetos.get(i); %>
			<tr>
				<td><%=objeto.getCnpj()%></td>
				<td><%=objeto.getNome()%></a></td>
				<td align="center" >
					<a id="edt" href="/empresaControle?acao=editar&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.editar")%>" src="img/editar.png" style="border: 0px;"/></a>
					<a href="/empresaControle?acao=remover&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.remover")%>" src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/empresaControle"/>
	<% } else { %>
		<span class="heading"><%=bundle.getString("pesquisa.nenhumresultado")%></span>
	<% } %>
	</form>
	
<%@include file="../../modelo/fim.jspf" %>
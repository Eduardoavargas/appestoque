<%@ page isELIgnored="false" %>
<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("usuario.pesquisar.titulo")%></span>
	<form id="formListar" method="post" action="/usuarioControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
		<app:parametrosListar/>
		<app:barraListar acao="/usuarioControle"/>
		<script>
			$("#buscar").click(function () {
		      if(document.getElementById('email').value.length==0){
		    	  alert('<%=bundle.getString("pesquisa.semFiltro")%>');
		    	  document.getElementById('email').focus();
		      }else{
		    	  document.forms[0].submit();		  
		      }
		    });
	    </script>
		<span class="heading"><%=bundle.getString("usuario.nome")%></span><br/>
		<input type="text" id="email" name="email" class="text ui-widget-content ui-corner-all" style="width: 300px" value="<%=request.getAttribute("email")!=null?request.getAttribute("email"):""%>"/>	
	<%
		List<Usuario> objetos = new ArrayList<Usuario>();
		if(request.getAttribute("objetos")!=null){
			objetos = (List<Usuario>)request.getAttribute("objetos");
		} 
	%>
	<p/>	
	<% if (objetos.size() > 0) { %>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header ">
			<td><%=bundle.getString("usuario.nome")%></td>
			<td><%=bundle.getString("usuario.email")%></td>
			<td width="15%"/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Usuario objeto = (Usuario) objetos.get(i); %>
			<tr>
				<td><%=objeto.getNome()%></td>
				<td><%=objeto.getEmail()%></a></td>
				<td align="center" >
					<a href="/usuarioControle?acao=editar&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.editar")%>" src="img/editar.png" style="border: 0px;"/></a>
<%-- 					<a href="/usuarioControle?acao=remover&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.remover")%>" src="img/remover.png" style="border: 0px;"/></a> --%>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/usuarioControle"/>
	<% } else { %>
		<span class="heading"><%=bundle.getString("pesquisa.nenhumresultado")%></span>
	<% } %>
	</form>
<%@include file="../../modelo/fim.jspf" %>
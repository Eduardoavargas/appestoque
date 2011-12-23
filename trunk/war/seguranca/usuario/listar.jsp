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
		<span class="heading"><%=bundle.getString("usuario.filtro.nome")%></span><br/>
		<input type="text" name="email" style="width: 300px" value="<%=request.getAttribute("email")!=null?request.getAttribute("email"):""%>"/>	
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
			<td>Nome</td>
			<td>E-mail</td>
			<td>Senha</td>
			<td/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Usuario objeto = (Usuario) objetos.get(i); %>
			<tr>
				<td><%=objeto.getNome()%></td>
				<td><%=objeto.getEmail()%></a></td>
				<td><%=objeto.getSenha()%></td>
				<td><%=objeto.getSerial()%></td>
				<td align="center" >
					<a href="/usuarioControle?acao=editar&id=<%=objeto.getId()%>"><img src="img/editar.png" style="border: 0px;"/></a>
					<a href="/usuarioControle?acao=remover&id=<%=objeto.getId()%>"><img src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/usuarioControle"/>
	<% } else { %>
		<span class="heading">Nenhum informação foi encontrado.</span>
	<% } %>
	</form>
<%@include file="../../modelo/fim.jspf" %>
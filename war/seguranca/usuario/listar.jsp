<%@ page isELIgnored="false" %>
<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("usuario.pesquisar.titulo")%></span>
	<form id="formListar" method="post" action="/usuarioControle?acao=pesquisar">
		<input type="hidden" name="primeiroRegistro" value="<%=request.getAttribute("primeiroRegistro")!=null?request.getAttribute("primeiroRegistro"):0%>"/>
		<p/>
			<a href="#" onclick="formListar.submit();" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-search"></span><%=bundle.getString("link.buscar")%></a>			
			<a href="/usuarioControle?acao=criar" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-document"></span><%=bundle.getString("link.adicionar")%></a>
		<p/>
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
		<span class="heading"><%= objetos.size() %> usuários correspondentes aos seus critérios de buscas:</span>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header ">
			<td>Nome</td>
			<td>E-mail</td>
			<td>Senha</td>
			<td/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Usuario obj = (Usuario) objetos.get(i); %>
			<tr>
				<td><%=obj.getNome()%></td>
				<td><%=obj.getEmail()%></a></td>
				<td><%=obj.getSenha()%></td>
				<td align="center" >
					<a href="/usuarioControle?acao=editar&id=<%=obj.getId()%>"><img src="img/editar.png" style="border: 0px;"/></a>
					<a href="/usuarioControle?acao=remover&id=<%=obj.getId()%>"><img src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<a href="/usuarioControle?acao=pesquisar&paginar=primeiro" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-seek-prev"></span><%=bundle.getString("link.primeiro")%></a>
		<a href="/usuarioControle?acao=pesquisar&paginar=anterior&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")!=null?request.getAttribute("primeiroRegistro"):0%>" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-seek-first"></span><%=bundle.getString("link.anterior")%></a>
		<a href="/usuarioControle?acao=pesquisar&paginar=proximo&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")!=null?request.getAttribute("primeiroRegistro"):0%>" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-seek-end"></span><%=bundle.getString("link.proximo")%></a>
		<a href="/usuarioControle?acao=pesquisar&paginar=ultimo" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-seek-next"></span><%=bundle.getString("link.ultimo")%></a>
	<% } else { %>
		<span class="heading">Nenhum usuário correspondente foi encontrado.</span>
	<% } %>
	</form>
<%@include file="../../modelo/fim.jspf" %>
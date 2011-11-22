<%@ page isELIgnored="false" %>
<%@ page import="br.com.appestoque.dominio.suprimento.Produto" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("usuario.pesquisar.titulo")%></span>
	<form id="formListar" method="post" action="/usuarioControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
		<app:parametrosListar/>
		<app:barraListar acao="/produtoControle"/>
		<span class="heading"><%=bundle.getString("produto.filtro.numero")%></span><br/>
		<input type="text" name="numero" style="width: 300px" value="<%=request.getAttribute("numero")!=null?request.getAttribute("numero"):""%>"/>	
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
			<td>Nome</td>
			<td>Número</td>
			<td>Preço</td>
			<td/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Produto obj = (Produto) objetos.get(i); %>
			<tr>
				<td><%=obj.getNome()%></td>
				<td><%=obj.getNumero()%></a></td>
				<td><%=obj.getPreco()%></td>
				<td align="center" >
					<a href="/produtoControle?acao=editar&id=<%=obj.getId()%>"><img src="img/editar.png" style="border: 0px;"/></a>
					<a href="/produtoControle?acao=remover&id=<%=obj.getId()%>"><img src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/produtoControle"/>
	<% } else { %>
		<span class="heading">Nenhum informação foi encontrado.</span>
	<% } %>
	</form>
<%@include file="../../modelo/fim.jspf" %>
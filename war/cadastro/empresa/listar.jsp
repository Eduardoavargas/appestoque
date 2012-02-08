<%@ page isELIgnored="false" %>
<%@ page import="br.com.appestoque.dominio.cadastro.Empresa" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("empresa.pesquisar.titulo")%></span>
	<form id="formListar" method="post" action="/empresaControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
		<app:parametrosListar/>
		<app:barraListar acao="/empresaControle"/>
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
		<tr class="ui-widget-header ">
			<td>CNPJ</td>
			<td>Nome</td>
			<td>Bairro</td>
			<td>Cidade</td>
			<td width="15%"/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Empresa objeto = (Empresa) objetos.get(i); %>
			<tr>
				<td><a href="/processo?acao=selecionar&id=<%=objeto.getId()%>"><%=objeto.getCnpj()%></a></td>
				<td><%=objeto.getNome()%></td>
				<td><%=objeto.getEndereco().getBairro()%></td>
				<td><%=objeto.getEndereco().getCidade()%></td>
				<td align="center" >
					<a href="/empresaControle?acao=editar&id=<%=objeto.getId()%>"><img src="img/editar.png" style="border: 0px;"/></a>
					<a href="/empresaControle?acao=remover&id=<%=objeto.getId()%>"><img src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/empresaControle"/>
	<% } else { %>
		<span class="heading">Nenhum informação foi encontrado.</span>
	<% } %>
	</form>
<%@include file="../../modelo/fim.jspf" %>
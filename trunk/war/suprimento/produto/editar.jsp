<%@ page import="br.com.appestoque.dominio.suprimento.Produto" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Produto objeto = (Produto)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("produto.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/produtoControle?acao=modificar">
		<app:barraEditar acao="/produtoControle"/>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			Nome:<br/><app:texto nome="nome" valor="<%=objeto.getNome()%>"/></p>
			Número:<br/><app:texto nome="numero" valor="<%=objeto.getNumero()%>"/></p>
			Preço<br/><input type="input" name="preco" value="<%=objeto.getPreco()%>" class="text ui-widget-content ui-corner-all"></input>
<%-- 			Preço:<input type="input" name="preco" valor="<%=objeto.getPreco()%>"/> --%>
<%-- 			Preço:<br/><app:texto nome="preco" valor="<%=objeto.getPreco()%>"/></p>			 --%>
<%-- 			<fmt:formatNumber value="<%=objeto.getPreco()%>"/></input> --%>

		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
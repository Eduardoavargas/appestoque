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
			Número:<br/><app:texto nome="email" valor="<%=objeto.getNumero()%>"/></p>
			Preço:<br/><app:texto nome="senha" valor="<%=objeto.getPreco()%>"/></p>
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
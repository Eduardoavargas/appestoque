<%@ page import="br.com.appestoque.dominio.suprimento.Produto"%>
<%@ page import="br.com.appestoque.util.Constantes" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Produto objeto = (Produto) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("produto.editar.titulo")%></span>

<form id="formEditar" method="post"
	action="/produtoControle?acao=modificar">
	<app:barraEditar acao="/produtoControle" />
	<input type="hidden" name="id"
		value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
	<br /> Nome:<br />
	<app:texto nome="nome" tamanho="50" valor="<%=objeto.getNome()%>" />
	</p>
	Número:<br />
	<app:texto nome="numero" valor="<%=objeto.getNumero()%>" />
	</p>
	Preço:<br />
	<app:dinheiro id="preco" nome="preco" tamanho="10" valor="<%=objeto.getPreco().toString()%>" precisao="<%=Constantes.PRECISAO_PRECO.toString()%>"/>	
	</p>
	Estoque:<br />
	<app:valor id="estoque" nome="estoque" tamanho="10" valor="<%=objeto.getEstoque().toString()%>" precisao="<%=Constantes.PRECISAO_ESTOQUE.toString()%>"/>
	</p>
	Imagem:<br />
	<app:texto nome="imagem" tamanho="50" valor="<%=objeto.getImagem()%>" />
	</p>
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
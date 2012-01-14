<%@ page import="br.com.appestoque.dominio.suprimento.Produto" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Produto objeto = (Produto)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("produto.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/produtoControle?acao=modificar">
		<app:barraEditar acao="/produtoControle"/>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
					Nome:<br/><app:texto nome="nome" tamanho="50" valor="<%=objeto.getNome()%>"/></p>
					Número:<br/><app:texto nome="numero" valor="<%=objeto.getNumero()%>"/></p>
					Preço:<br/><app:texto nome="preco" tamanho="10" valor="<%=objeto.getPreco().toString()%>"/></p>
					Estoque:<br/><app:texto nome="estoque" tamanho="10" valor="<%=objeto.getEstoque().toString()%>"/></p>
					Imagem 1:<br/><app:texto nome="imagem1" tamanho="50" valor="<%=objeto.getImagem1()%>"/></p>
					Imagem 2:<br/><app:texto nome="imagem2" tamanho="50" valor="<%=objeto.getImagem2()%>"/></p>
					Imagem 3:<br/><app:texto nome="imagem3" tamanho="50" valor="<%=objeto.getImagem3()%>"/></p>
					Imagem 4:<br/><app:texto nome="imagem4" tamanho="50" valor="<%=objeto.getImagem4()%>"/></p>
					Imagem 5:<br/><app:texto nome="imagem5" tamanho="50" valor="<%=objeto.getImagem5()%>"/></p>
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
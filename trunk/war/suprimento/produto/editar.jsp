<%@ page import="br.com.appestoque.dominio.suprimento.Produto" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Produto objeto = (Produto)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("produto.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/produtoControle?acao=modificar">
		<app:barraEditar acao="/produtoControle"/>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			<div style="width:100%; height: 250px;">
				<div style="float:left; width: 50%;">
					Nome:<br/><app:texto nome="nome" tamanho="50" valor="<%=objeto.getNome()%>"/></p>
					Número:<br/><app:texto nome="numero" valor="<%=objeto.getNumero()%>"/></p>
					Preço:<br/><app:texto nome="preco" tamanho="10" valor="<%=objeto.getPreco().toString()%>"/></p>
					Estoque:<br/><app:texto nome="estoque" tamanho="10" valor="<%=objeto.getEstoque().toString()%>"/></p>
					Imagem:<br/><app:texto nome="imagem" tamanho="50" valor="<%=objeto.getImagem()%>"/></p>
				</div>
				<%if(objeto.getImagem()!=null){%>
				<div style="float:left; width: 50%;">
					<img src="<%=objeto.getImagem()%>">
				</div>
				<%}%>
			</div>			
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
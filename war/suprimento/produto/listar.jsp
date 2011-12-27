<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.com.appestoque.dominio.suprimento.Produto" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>

<!-- 	<script type="text/javascript"> -->
<!-- 		$(function() { -->
<!-- 			$('#quadro img').tooltip({ -->
<!-- 				delay: 0, -->
<!-- 				showURL: false, -->
<!-- 				bodyHandler: function() { -->
<!-- 					return $("<img/>").attr("src", this.src); -->
<!-- 				} -->
<!-- 			}); -->
<!-- 		}); -->
<!-- 	</script> -->

<span class="title"><%=bundle.getString("produto.pesquisar.titulo")%></span>

<!-- 	<div id="quadro"> -->
<!-- 		<img src="img/image.png" style="border: 0px;"/> -->
<!-- 	</div> -->

	<form id="formListar" method="post" action="/produtoControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
	
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
			<td align="right">Preço</td>
			<td align="right">Estoque</td>
			<td/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Produto objeto = (Produto) objetos.get(i); %>
			<tr>
				<td><%=objeto.getNome()%></td>
				<td><%=objeto.getNumero()%></a></td>
				<td align="right"><fmt:formatNumber value="<%=objeto.getPreco()%>" type="currency" pattern="##,###,##0.000" /></td>
				<td align="right"><fmt:formatNumber value="<%=objeto.getEstoque()%>" type="currency" pattern="##,###,##0.000" /></td>
				<td align="center" >					
					<a id="edt" href="/produtoControle?acao=editar&id=<%=objeto.getId()%>"><img src="img/editar.png" style="border: 0px;"/></a>
					<a href="/produtoControle?acao=remover&id=<%=objeto.getId()%>"><img src="img/remover.png" style="border: 0px;"/></a>
					<%if(objeto.getImagem()!=null){%>
						<img id="<%=objeto.getId()%>" src="img/imagem.png" style="border: 0px;"/>
					<%}else{%>
						<img src="img/semimagem.png" style="border: 0px;"/>
					<%}%>
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
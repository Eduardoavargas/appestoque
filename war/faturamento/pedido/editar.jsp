<%@ page import="br.com.appestoque.dominio.faturamento.Pedido,br.com.appestoque.dominio.faturamento.Item, java.text.NumberFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Pedido objeto = (Pedido) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("pedido.editar.titulo")%></span>

<form id="formEditar" method="post" action="/pedidoControle?acao=modificar">

	<app:barraEditar acao="/pedidoControle"/>
	<input type="hidden" name="id" value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>

	<%=bundle.getString("pedido.numero")%>:<br/>
	<app:texto id="numero" nome="numero" tamanho="20" valor="<%=objeto.getNumero()%>"/></p>
	
	<%=bundle.getString("pedido.cliente")%>:<br/>
	<app:texto id="cliente" nome="cliente" tamanho="50" valor="<%=objeto.getCliente().getNome()%>"/></p>
	
	<%=bundle.getString("pedido.representante")%>:<br/>
	<app:texto id="cliente" nome="cliente" tamanho="50" valor="<%=objeto.getRepresentante().getNome()%>"/></p>
	
	
	<%=bundle.getString("pedido.obs")%>:<br/>
	<app:texto id="obs" nome="obs" tamanho="50" valor="<%=objeto.getObs()%>"/></p>
	
	<%=bundle.getString("produto.numero")%>:<br />
	<app:texto id="numero" nome="numero" valor="<%=objeto.getNumero()%>" />
	
	<%
	NumberFormat numberFormat = NumberFormat.getInstance();
	numberFormat.setMaximumFractionDigits(br.com.appestoque.Constantes.PRECISAO_VALOR);
	numberFormat.setMinimumFractionDigits(br.com.appestoque.Constantes.PRECISAO_VALOR);
	%>
	
	<p/>	
	<% if (objeto.getItens().size() > 0) { %>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header">
			<td><%=bundle.getString("produto.numero")%></td>
			<td><%=bundle.getString("item.produto")%></td>
			<td><%=bundle.getString("item.valor")%></td>
			<td><%=bundle.getString("item.quantidade")%></td>
			<td><%=bundle.getString("item.total")%></td>
		</tr>
		<% for (int i = 0;i<objeto.getItens().size();i++) { %>
			<% Item item = (Item) objeto.getItens().get(i); %>
			<tr>
				<td><%=item.getProduto().getNumero()%></td>
				<td><%=item.getProduto().getNome()%></td>
				<td><%=numberFormat.format(item.getValor())%></td>
				<td><%=numberFormat.format(item.getQuantidade())%></td>
				<td><%=numberFormat.format(item.getQuantidade()*item.getValor())%></td>
			</tr>
		<% } %>
		</table>
		<p/>	
	<% } else { %>
		<span class="heading"><%=bundle.getString("pesquisa.nenhumresultado")%></span>
	<% } %>
	
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>


<%-- 	<%=bundle.getString("pedido.data")%>:<br/> --%>
<%-- 	<fmt:formatDate value="<%=objeto.getData()%>" pattern="dd/MM/yyyy"/></p> --%>
	
	
<%-- 	<%=bundle.getString("pedido.cliente")%>:<br/> --%>
<!-- 	<select name="idCliente" id="idCliente" class="text ui-widget-content ui-corner-all" style="cursor:pointer; width: 300px;"> -->
<%-- 		<c:forEach var="cliente" items="${clientes}" varStatus="id"> --%>
<%-- 			<option value="${cliente.id}" <c:if test="${cliente.id == idCliente}">selected</c:if> >${cliente.nome}</option> --%>
<%-- 		</c:forEach> --%>
<!-- 	</select></p> -->
	
<%-- 	<%=bundle.getString("pedido.representante")%>:<br/> --%>
<!-- 	<select name="idRepresentante" id="idRepresentante"  class="text ui-widget-content ui-corner-all" style="cursor:pointer; width: 300px;"> -->
<%-- 		<c:forEach var="representante" items="${representantes}" varStatus="id"> --%>
<%-- 			<option value="${representante.id}" <c:if test="${representante.id == idRepresentante}">selected</c:if> >${representante.nome}</option> --%>
<%-- 		</c:forEach> --%>
<!-- 	</select></p> -->
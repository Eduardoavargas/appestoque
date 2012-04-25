<%@ page import="br.com.appestoque.dominio.faturamento.Pedido"%>
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
	
	<%=bundle.getString("pedido.data")%>:<br/>
	<fmt:formatDate value="<%=objeto.getData()%>" pattern="dd/MM/yyyy"/>
	
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
	
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
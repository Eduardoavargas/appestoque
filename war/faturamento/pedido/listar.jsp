<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.com.appestoque.dominio.faturamento.Pedido" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>

<span class="title"><%=bundle.getString("pedido.pesquisar.titulo")%></span>

	<form id="formListar" method="post" action="/pedidoControle?acao=pesquisar&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>">
	
		<app:parametrosListar/>
		<app:barraListar acao="/pedidoControle"/>
		
		<script type="text/javascript">
			$("#buscar").click(function () {
		    	  document.forms[0].submit();		  
		    });
			$("#adicionar").click(function () {
				  alert('<%=bundle.getString("pedido.mensagem.adicionar")%>');		
		    	  return false;
		    });
	    </script>
		
		<span class="heading"><%=bundle.getString("pedido.numero")%></span><br/>
		<input type="text" id="numero" name="numero" class="text ui-widget-content ui-corner-all" style="width: 150px" value="<%=request.getAttribute("numero")!=null?request.getAttribute("numero"):""%>"/>	
	<%
		List<Pedido> objetos = new ArrayList<Pedido>();
		if(request.getAttribute("objetos")!=null){
			objetos = (List<Pedido>)request.getAttribute("objetos");
		} 
	%>
	<p/>	
	<% if (objetos.size() > 0) { %>
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" class="ui-widget" width="100%">
		<tr class="ui-widget-header">
			<td><%=bundle.getString("pedido.numero")%></td>
			<td><%=bundle.getString("pedido.cliente")%></td>
			<td><%=bundle.getString("pedido.data")%></td>
			<td width="15%"/>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Pedido objeto = (Pedido) objetos.get(i); %>
			<tr>
				<td><%=objeto.getNumero()%></td>
				<td><%=objeto.getCliente().getNome()%></td>
				<td><fmt:formatDate value="<%=objeto.getData()%>" pattern="dd/MM/yyyy"/></td>
				<td align="center" >
					<a id="edt" href="/pedidoControle?acao=editar&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.editar")%>" src="img/editar.png" style="border: 0px;"/></a>
					<a href="/pedidoControle?acao=remover&id=<%=objeto.getId()%>"><img title="<%=bundle.getString("link.titulo.remover")%>" src="img/remover.png" style="border: 0px;"/></a>
				</td>
			</tr>
		<% } %>
		</table>
		<p/>	
		<app:paginacao acao="/pedidoControle"/>
	<% } else { %>
		<span class="heading"><%=bundle.getString("pesquisa.nenhumresultado")%></span>
	<% } %>
	</form>
	
<%@include file="../../modelo/fim.jspf" %>
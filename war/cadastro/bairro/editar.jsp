<%@ page import="br.com.appestoque.dominio.cadastro.Bairro"%>
<%@ page import="br.com.appestoque.util.Constantes"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Bairro objeto = (Bairro) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("bairro.editar.titulo")%></span>

<form id="formEditar" method="post" action="/bairroControle?acao=modificar">
	<app:barraEditar acao="/bairroControle"/>

	<script>
			$("#salvar").click(function () {
				if(document.getElementById('nome').value.length==0){
					alert('<%=bundle.getString("bairro.mensagem.validar.nome")%>');
					document.getElementById('nome').focus();
				}else{
					document.forms[0].submit();	
				}
			});
	</script>

	<input type="hidden" name="id" value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
	<%=bundle.getString("bairro.nome")%>:<br/>
	<app:texto id="nome" nome="nome" tamanho="50" valor="<%=objeto.getNome()%>"/></p>
	<%=bundle.getString("bairro.cidade")%>:<br/>
	<select name="idCidade" id="idCidade"  class="text ui-widget-content ui-corner-all" style="cursor:pointer;">
		<c:forEach var="cidade" items="${cidades}" varStatus="id">
			<option value="${cidade.id}" <c:if test="${cidade.id == idCidade}">selected</c:if> >${cidade.nome}</option>
		</c:forEach>
	</select></p>
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
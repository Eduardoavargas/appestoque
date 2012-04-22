<%@ page import="br.com.appestoque.dominio.cadastro.Cidade"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Cidade objeto = (Cidade) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("cidade.editar.titulo")%></span>

<form id="formEditar" method="post" action="/cidadeControle?acao=modificar">
	<app:barraEditar acao="/cidadeControle"/>

	<script type="text/javascript">
			$("#salvar").click(function () {
				if(document.getElementById('nome').value.length==0){
					alert('<%=bundle.getString("cidade.mensagem.validar.nome")%>');
					document.getElementById('nome').focus();
				}else{
					document.forms[0].submit();	
				}
			});
	</script>

	<input type="hidden" name="id" value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
	<%=bundle.getString("cidade.nome")%>:<br />
	<app:texto id="nome" nome="nome" tamanho="50" valor="<%=objeto.getNome()%>"/>
	</p>
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
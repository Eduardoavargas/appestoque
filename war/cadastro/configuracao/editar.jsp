<%@ page import="br.com.appestoque.dominio.cadastro.Empresa"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Empresa objeto = (Empresa) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("parametro.editar.titulo")%></span>

<form id="formEditar" method="post" action="/empresaControle?acao=modificar">

	<p>
		<a href="/menu.jsp"
			style="z-index:0;"
			class="ui-state-default ui-corner-all"><span
			class="ui-icon ui-icon-arrowreturnthick-1-w"></span>Retornar</a>
		<a href="#" id="salvar"
			style="z-index:0;"
			class="ui-state-default ui-corner-all"><span
			class="ui-icon ui-icon-disk"></span>Salvar</a>
	</p>

	<script type="text/javascript">
			$("#salvar").click(function () {
				if(document.getElementById('email').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.email")%>');
					document.getElementById('email').focus();
				}else{
					document.forms[0].submit();	
				}
			});
	</script>

	<input type="hidden" name="id"
		value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
		<label><%=bundle.getString("parametro.email")%><span class="obrigatorio">*</span></label><br/>
<%-- 		<app:texto id="email" nome="email" tamanho="50" valor="<%=objeto.getEmailPedido()%>"/></p> --%>
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
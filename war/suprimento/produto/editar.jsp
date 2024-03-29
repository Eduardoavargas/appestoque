<%@ page import="br.com.appestoque.dominio.suprimento.Produto"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Produto objeto = (Produto) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("produto.editar.titulo")%></span>

<%
	NumberFormat numberFormat = NumberFormat.getInstance();
	numberFormat.setMaximumFractionDigits(br.com.appestoque.Constantes.PRECISAO_VALOR);
	numberFormat.setMinimumFractionDigits(br.com.appestoque.Constantes.PRECISAO_VALOR);
%>

<form id="formEditar" method="post" action="/produtoControle?acao=modificar">
	<app:barraEditar acao="/produtoControle"/>

	<script>
			$("#salvar").click(function () {
				if(document.getElementById('nome').value.length==0){
					alert('<%=bundle.getString("produto.mensagem.validar.nome")%>');
					document.getElementById('nome').focus();
				}else if(document.getElementById('numero').value.length==0){
					alert('<%=bundle.getString("produto.mensagem.validar.numero")%>');
					document.getElementById('numero').focus();
				}else if(document.getElementById('preco').value.length==0){
					alert('<%=bundle.getString("produto.mensagem.validar.preco")%>');
					document.getElementById('preco').focus();
				}else{
					document.forms[0].submit();	
				}
			});
	</script>

	<input type="hidden" name="id"
		value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
	<br /> <%=bundle.getString("produto.nome")%>:<br />
	<app:texto id="nome" nome="nome" tamanho="50" valor="<%=objeto.getNome()%>" />
	</p>
	<%=bundle.getString("produto.numero")%>:<br />
	<app:texto id="numero" nome="numero" valor="<%=objeto.getNumero()%>" />
	</p>
	<%=bundle.getString("produto.preco")%>:<br />
	<app:valor id="preco" nome="preco" tamanho="10" valor="<%=numberFormat.format(objeto.getPreco())%>" precisao="<%=br.com.appestoque.Constantes.PRECISAO_VALOR.toString()%>"/>	
	</p>
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
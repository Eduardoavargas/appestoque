<%@ page import="br.com.appestoque.dominio.cadastro.Cliente"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="br.com.appestoque.util.Constantes"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Cliente objeto = (Cliente) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("cliente.editar.titulo")%></span>

<form id="formEditar" method="post" action="/clienteControle?acao=modificar">
	<app:barraEditar acao="/clienteControle"/>

	<script>
			$("#salvar").click(function () {
				if(document.getElementById('nome').value.length==0){
					alert('<%=bundle.getString("produto.mensagem.validar.nome")%>');
					document.getElementById('nome').focus();
				}else if(document.getElementById('bairro').value.length==0){
					alert('<%=bundle.getString("endereco.mensagem.validar.bairro")%>');
					document.getElementById('bairro').focus();
				}else if(document.getElementById('cidade').value.length==0){
					alert('<%=bundle.getString("endereco.mensagem.validar.cidade")%>');
					document.getElementById('cidade').focus();
				}else if(document.getElementById('cep').value.length==0){
					alert('<%=bundle.getString("endereco.mensagem.validar.cep")%>');
					document.getElementById('cep').focus();
				}else{
					document.forms[0].submit();	
				}
			});
	</script>

	<input type="hidden" name="id"
		value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
	<br /> <%=bundle.getString("cliente.nome")%>:<br />
	<app:texto id="nome" nome="nome" tamanho="50" valor="<%=objeto.getNome()%>" />
	</p>	
	<%=bundle.getString("cliente.cnpj")%>:<br />
	<app:texto id="cnpj" nome="cnpj" valor="<%=objeto.getCnpj()%>" />
	</p>
	<%=bundle.getString("endereco.bairro")%>:<br />
	<app:texto id="bairro" nome="bairro" tamanho="50" valor="<%=objeto.getEndereco().getBairro()%>" />
	</p>
	<%=bundle.getString("endereco.cidade")%>:<br />
	<app:texto id="cidade" nome="cidade" tamanho="50" valor="<%=objeto.getEndereco().getCidade()%>" />
	</p>
	<%=bundle.getString("endereco.cep")%>:<br />
	<app:texto id="cep" nome="cep" valor="<%=objeto.getEndereco().getCep()%>" />
	</p>
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
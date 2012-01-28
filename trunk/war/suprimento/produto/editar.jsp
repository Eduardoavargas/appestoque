<%@ page import="br.com.appestoque.dominio.suprimento.Produto"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="br.com.appestoque.util.Constantes"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Produto objeto = (Produto) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("produto.editar.titulo")%></span>

<script type="text/javascript">
	function submeterForm(){
		if(document.getElementById('nome').value.length==0){
			alert('<%=bundle.getString("produto.mensagem.validar.nome")%>');
			document.getElementById('nome').focus();
		}else if(document.getElementById('numero').value.length==0){
			alert('<%=bundle.getString("produto.mensagem.validar.numero")%>');
			document.getElementById('numero').focus();
		}else if(document.getElementById('preco').value.length==0){
			alert('<%=bundle.getString("produto.mensagem.validar.preco")%>');
			document.getElementById('preco').focus();
		}else if(document.getElementById('estoque').value.length==0){
			alert('<%=bundle.getString("produto.mensagem.validar.estoque")%>');
			document.getElementById('estoque').focus();
		}else{
			document.forms[0].submit();	
		}
	}
</script>

<%
    NumberFormat numberFormat = NumberFormat.getInstance();
	numberFormat.setMaximumFractionDigits(Constantes.PRECISAO_VALOR);
	numberFormat.setMinimumFractionDigits(Constantes.PRECISAO_VALOR);
%>

<form id="formEditar" method="post" action="/produtoControle?acao=modificar">
	<app:barraEditar acao="/produtoControle"/>
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
	<app:valor id="preco" nome="preco" tamanho="10" valor="<%=numberFormat.format(objeto.getPreco())%>" precisao="<%=Constantes.PRECISAO_VALOR.toString()%>"/>	
	</p>
	<%=bundle.getString("produto.estoque")%>:<br />
	<app:valor id="estoque" nome="estoque" tamanho="10" valor="<%=numberFormat.format(objeto.getEstoque())%>" precisao="<%=Constantes.PRECISAO_VALOR.toString()%>"/>
	</p>
	<%=bundle.getString("produto.imagem")%>:<br />
	<app:texto nome="imagem" tamanho="50" valor="<%=objeto.getImagem()%>"/>
	</p>
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
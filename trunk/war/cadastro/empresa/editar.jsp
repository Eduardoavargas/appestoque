<%@ page import="br.com.appestoque.dominio.cadastro.Empresa"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Empresa objeto = (Empresa) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("empresa.editar.titulo")%></span>

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
				}if(document.getElementById('numero').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.numero")%>');
					document.getElementById('numero').focus();
				}else if(document.getElementById('endereco').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.endereco")%>');
					document.getElementById('endereco').focus();
				}else if(document.getElementById('cep').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.cep")%>');
					document.getElementById('cep').focus();
				}else if(document.getElementById('bairro').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.bairro")%>');
					document.getElementById('bairro').focus();
				}else if(document.getElementById('cidade').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.cidade")%>');
					document.getElementById('cidade').focus();
				}else{
					document.forms[0].submit();	
				}
			});
			
	</script>

	<input type="hidden" name="id"
		value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<input type="hidden" name="uuid"
		value="<%=objeto.getUuid() != null ? objeto.getUuid() : ""%>" />	
	<hr>
	
			<label><%=bundle.getString("empresa.nome")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="nome" nome="nome" desativar="true" tamanho="50" valor="<%=objeto.getNome()%>"/></p>
			
			<label><%=bundle.getString("empresa.razao")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="razao" nome="razao" desativar="true" tamanho="50" valor="<%=objeto.getRazao()%>"/></p>
			
			<label><%=bundle.getString("email")%><span class="obrigatorio">*</span></label><br/>
			<app:email nome="email" tamanho="50" valor="<%=objeto.getEmail()%>"/></p>
			
			<label><%=bundle.getString("cnpj")%><span class="obrigatorio">*</span></label><br/>
			<app:cnpj nome="cnpj" desativar="true" valor="<%=objeto.getCnpj()%>"></app:cnpj></p>
			
			<label><%=bundle.getString("endereco")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="endereco" nome="endereco" tamanho="50" valor="<%=objeto.getEndereco()%>"></app:texto></p>
			
			<label><%=bundle.getString("numero")%></label><br/>
			<app:numero id="numero" nome="numero" tamanho="5" valor="<%=objeto.getNumero().toString()%>"/></p>
			
			<label><%=bundle.getString("cep")%><span class="obrigatorio">*</span></label><br/>
			<app:cep nome="cep" valor="<%=objeto.getCep()%>"/></p>
			
			<label><%=bundle.getString("complemento")%></label><br/>
			<app:texto id="complemento" nome="complemento" tamanho="50" valor="<%=objeto.getComplemento()%>"/></p>
			
			<label><%=bundle.getString("bairro")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="bairro" nome="bairro" tamanho="50" valor="<%=objeto.getBairro()%>"/></p>
			
			<label><%=bundle.getString("cidade")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="cidade" nome="cidade" tamanho="50" valor="<%=objeto.getCidade()%>"/></p>
			
			<p>
				<label><%=bundle.getString("twitterConsumerKey")%><span class="obrigatorio">*</span></label><br/>
				<app:texto id="consumerKey" nome="consumerKey" tamanho="50" valor="<%=objeto.getTwitterConsumerKey()%>"/>
			</p>
			<p>
				<label><%=bundle.getString("twitterConsumerSecret")%><span class="obrigatorio">*</span></label><br/>
				<app:texto id="consumerSecret" nome="consumerSecret" tamanho="50" valor="<%=objeto.getTwitterConsumerSecret()%>"/>
			</p>
			<p>
				<label><%=bundle.getString("twitterAccessToken")%><span class="obrigatorio">*</span></label><br/>
				<app:texto id="accessToken" nome="accessToken" tamanho="50" valor="<%=objeto.getTwitterAccessToken()%>"/>
			</p>
			<p>
				<label><%=bundle.getString("twitterAccessTokenSecret")%><span class="obrigatorio">*</span></label><br/>
				<app:texto id="accessTokenSecret" nome="accessTokenSecret" tamanho="50" valor="<%=objeto.getTwitterAccessTokenSecret()%>"/>
			</p>
			
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
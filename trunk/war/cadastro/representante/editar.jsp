<%@ page import="br.com.appestoque.dominio.cadastro.Representante"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Representante objeto = (Representante) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("representante.editar.titulo")%></span>

<form id="formEditar" method="post" action="/representanteControle?acao=modificar">
	<app:barraEditar acao="/representanteControle"/>

	<script type="text/javascript">
			$("#salvar").click(function () {
				if(document.getElementById('nome').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.nome")%>');
					document.getElementById('nome').focus();
				}else if(document.getElementById('cpf').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.cpf")%>');
					document.getElementById('cnpj').focus();
				}else if(document.getElementById('endereco').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.endereco")%>');
					document.getElementById('endereco').focus();
				}else if(document.getElementById('cep').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.cep")%>');
					document.getElementById('cep').focus();
				}else if(document.getElementById('idBairro').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.bairro")%>');
					document.getElementById('idBairro').focus();
				}else if(document.getElementById('idCidade').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.cidade")%>');
					document.getElementById('idCidade').focus();
				}else{
					document.forms[0].submit();	
				}
			});
			
	</script>

	<input type="hidden" name="id"
		value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
	
	<%=bundle.getString("nome")%><br />
	<app:texto id="nome" nome="nome" tamanho="50" valor="<%=objeto.getNome()%>"/>
	</p>	
	<%=bundle.getString("cpf")%><br />
	<app:cpf nome="cpf" valor="<%=objeto.getCpf()%>"/>
	</p>
	<%=bundle.getString("endereco")%><br />
	<app:texto id="endereco" nome="endereco" tamanho="50" valor="<%=objeto.getEndereco()%>"/>
	</p>
	<%=bundle.getString("complemento")%><br />
	<app:texto id="complemento" nome="complemento" tamanho="50" valor="<%=objeto.getComplemento()%>"/>
	</p>
	<%=bundle.getString("cep")%><br />
	<app:cep nome="cep" valor="<%=objeto.getCep()%>"/>
	</p>
	<%=bundle.getString("numero")%><br />
	<app:numero id="numero" nome="numero" valor="<%=objeto.getNumero().toString()%>"/>
	</p>
	<%=bundle.getString("cidade")%><br/>
	<select name="idCidade" id="idCidade"  class="text ui-widget-content ui-corner-all" 
	        style="cursor:pointer;" 
	        onchange="ajax('/bairroControle?acao=ajax&id='+this.value,'bairros')">
		<c:forEach var="cidade" items="${cidades}" varStatus="id">
			<option value="${cidade.id}" <c:if test="${cidade.id == idCidade}">selected</c:if> >${cidade.nome}</option>
		</c:forEach>
	</select>
	</p>
	<%=bundle.getString("bairro")%><br/>
	<div id="bairros">
		<select name="idBairro" id="idBairro" class="text ui-widget-content ui-corner-all" 
		        style="cursor: pointer;">
			<c:forEach var="bairro" items="${bairros}" varStatus="id">
				<option value="${bairro.id}" <c:if test="${bairro.id == idBairro}">selected</c:if>>${bairro.nome}</option>
			</c:forEach>
		</select>
	</div>
	</p>
	<%=bundle.getString("representante.uuid")%><br />
	<app:texto id="uuid" nome="uuid" tamanho="50" valor="<%=objeto.getUuid()%>"/>
	</p>
	
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
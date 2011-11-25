<%@ page import="br.com.appestoque.dominio.cadastro.Empresa" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Empresa objeto = (Empresa)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("empresa.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/empresaControle?acao=modificar">
		<app:barraEditar acao="/empresaControle"/>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			Nome:<br/><app:texto nome="nome" estilo="width:300px;" valor="<%=objeto.getNome()%>"/></p>
			CNPJ:<br/><app:cnpj nome="cnpj" valor="<%=empresa.getCnpj()%>"></app:cnpj></p>
			Bairro:<br/><app:texto nome="bairro" estilo="width:300px;" valor="<%=objeto.getEndereco().getBairro()%>"/></p>
			Cidade:<br/><app:texto nome="cidade" estilo="width:300px;" valor="<%=objeto.getEndereco().getCidade()%>"/></p>
			Cep:<br/><app:cep nome="cep" valor="<%=empresa.getEndereco().getCep()%>"/></p>
			Número:<br/><input name="numero" style="width: 70px;" value="<%=objeto.getEndereco().getNumero()!=null?objeto.getEndereco().getNumero():""%>" class="text ui-widget-content ui-corner-all"/></p>
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
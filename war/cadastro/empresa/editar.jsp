<%@ page import="br.com.appestoque.dominio.cadastro.Empresa" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Empresa objeto = (Empresa)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("empresa.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/empresaControle?acao=modificar">
		<app:barraEditar acao="/empresaControle"/>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			Nome:<br/><app:texto nome="nome" valor="<%=objeto.getNome()%>"/></p>
			CNPJ:<br/><app:texto nome="cnpj" valor="<%=objeto.getCnpj()%>"/></p>
			
			<!--
			Bairro:<br/><app:texto nome="bairro" valor="<%=objeto.getEndereco().getBairro()%>"/></p>
			Cidade:<br/><app:texto nome="cidade" valor="<%=objeto.getEndereco().getCidade()%>"/></p>
			Número:<br/><app:texto nome="numero" valor="<%=objeto.getEndereco().getNumero()%>"/></p>
			Cep:<br/><app:texto nome="cep" valor="<%=objeto.getEndereco().getCep()%>"/></p>
			-->
			
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
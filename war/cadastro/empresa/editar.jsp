<%@ page import="br.com.appestoque.dominio.cadastro.Empresa" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Usuario objeto = (Empresa)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("empresa.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/empresaControle?acao=modificar">
		<app:barraEditar acao="/usuarioControle"/>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			Nome:<br/><app:texto nome="nome" valor="<%=objeto.getNome()%>"/></p>
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
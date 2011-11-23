<%@ page import="br.com.appestoque.dominio.cadastro.Empresa" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.io.*,java.net.*,java.util.*"%>
<%ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/padrao.css" />
<link type="text/css" href="/css/le-frog/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
<title><%=bundle.getString("app.titulo")%></title>
</head>
<body>
	<%Empresa objeto = (Empresa)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("empresa.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/empresaControle?acao=modificar">
		<app:barraEditar acao="/empresaControle"/>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			Nome:<br/><app:texto nome="nome" valor="<%=objeto.getNome()%>"/></p>
			CNPJ:<br/><app:texto nome="cnpj" valor="<%=objeto.getNome()%>"/></p>
			
			<!--
			Bairro:<br/><app:texto nome="bairro" valor="<%=objeto.getEndereco().getBairro()%>"/></p>
			Cidade:<br/><app:texto nome="cidade" valor="<%=objeto.getEndereco().getCidade()%>"/></p>
			Número:<br/><app:texto nome="numero" valor="<%=objeto.getEndereco().getNumero()%>"/></p>
			Cep:<br/><app:texto nome="cep" valor="<%=objeto.getEndereco().getCep()%>"/></p>
			-->
			
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
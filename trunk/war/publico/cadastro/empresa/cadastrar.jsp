<%@ page import="br.com.appestoque.dominio.cadastro.Empresa" %>
<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@ page import="java.io.*,java.net.*,java.util.*"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/padrao.css" />
<link type="text/css" href="/css/le-frog/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
<title><%=bundle.getString("app.titulo")%></title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<script src="/js/jquery.maskedinput-1.3.min.js" type="text/javascript"></script>

</head>
<body>
	<%
		Empresa empresa = (Empresa)request.getAttribute("empresa");
		Usuario usuario = (Usuario)request.getAttribute("usuario");
	%>
	<span class="title"><%=bundle.getString("empresa.cadastrar.titulo")%></span>
	
	<form id="formEditar" method="post" action="/processo?acao=adicionar">
		<p>
			<a href="/apresentacao.jsp"
				style="z-index:0;"
				class="ui-state-default ui-corner-all"><span
				class="ui-icon ui-icon-arrowreturnthick-1-w"></span>Retornar</a>
			<a href="#" onclick="document.forms[0].submit();"
				style="z-index:0;"
				class="ui-state-default ui-corner-all"><span
				class="ui-icon ui-icon-disk"></span>Salvar</a>
		</p>
		<hr><br/>
			Nome:<br/><app:texto nome="nome" estilo="width:300px;" valor="<%=empresa.getNome()%>"/></p>
			CNPJ:<br/><app:cnpj valor="<%=empresa.getCnpj()%>"></app:cnpj></p>
			Bairro:<br/><app:texto nome="bairro" estilo="width:300px;" valor="<%=empresa.getEndereco().getBairro()%>"/></p>
			Cidade:<br/><app:texto nome="cidade" estilo="width:300px;" valor="<%=empresa.getEndereco().getCidade()%>"/></p>
			Cep:<br/><app:cep valor="<%=empresa.getEndereco().getCep()%>"/></p>
			Número:<br/><input name="numero" style="width: 70px;" value="<%=empresa.getEndereco().getNumero()!=null?empresa.getEndereco().getNumero():""%>" class="text ui-widget-content ui-corner-all"/></p>
			<br/><br/>
			E-mail:<br/><app:email valor="<%=usuario.getEmail()%>"/></p>
			Nome de Usuário:<br/><app:texto nome="nomeUsuario" estilo="width:200px;" valor="<%=usuario.getNome()%>"/></p>
			Senha:<br/><app:texto nome="senha" estilo="width:70px;"  valor="<%=usuario.getSenha()%>"/></p>
			
		<hr>
	</form>
<div id="rodape">© 2011 appestoque - <a href="" title="Política de Privacidade">Política de Privacidade</a></div>
</body>
</html>
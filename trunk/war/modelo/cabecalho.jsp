<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/css/padrao.css" />
<link type="text/css" href="/css/le-frog/jquery-ui-1.8.16.custom.css" rel="stylesheet" />	
<script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="/js/padrao.js"></script>

</head>
<body>
	<div id="topo">
		<ul>
			<%
				Boolean autorizado = (Boolean)request.getSession().getAttribute("autorizado");
				if(autorizado!=null&&autorizado.equals(new Boolean("true"))){
			%>
				<li><a href="">Cadastro</a></li>
				<li><a href="">Suprimento</a></li>
				<li><a href="">Financeiro</a></li>
				<li><a href="">Faturamento</a></li>
				<li><a href="">Compra</a></li>
			<%}%>
		</ul>
		<%if(autorizado==null||autorizado.equals(new Boolean("false"))){%>
			<a href="" id="btnLogin">Clique aqui para cadastrar sua empresa</a>
		<%}%>
		<img style=" position:relative; top:35px;" src="img/logo.jpg"/>
	</div>
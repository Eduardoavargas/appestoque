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

		<style type="text/css">
/* menu styles */
.clear {
	clear: both;
	overflow: hidden;
	height: 0
}

#jsddm {
	margin: 0;
	padding: 0
}

#jsddm li {
	float: left;
	list-style: none;
	font: 12px Tahoma, Arial;
	text-align: left;
}

#jsddm li a {
	display: block;
/* 	background: #324143; */
	padding: 5px 12px;
	text-decoration: none;
	border-right: 0px solid white;
	width: 100px;
	color: #EAFFED;
	white-space: nowrap
}

/* #jsddm li a:hover { */
/*  	background: #E0FF9A; */
/* } */

#jsddm li ul {
	margin: 0;
	padding: 0;
	position: absolute;
	visibility: hidden;
}

#jsddm li ul li {
	float: none;
	display: inline
}

#jsddm li ul li a {
/* 	width: auto; */
	background: #E0FF9A;
	color: #24313C
}

#jsddm li ul li a:hover {
	background: #8EA344;
}
</style>
	
		<script type="text/javascript">
			var timeout         = 500;
			var closetimer		= 0;
			var ddmenuitem      = 0;
			
			function jsddm_open()
			{	jsddm_canceltimer();
				jsddm_close();
				ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');}
			
			function jsddm_close()
			{	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}
			
			function jsddm_timer()
			{	closetimer = window.setTimeout(jsddm_close, timeout);}
			
			function jsddm_canceltimer()
			{	if(closetimer)
				{	window.clearTimeout(closetimer);
					closetimer = null;}}
			
			$(document).ready(function()
			{	$('#jsddm > li').bind('mouseover', jsddm_open);
				$('#jsddm > li').bind('mouseout',  jsddm_timer);});
			
			document.onclick = jsddm_close;
		</script>
	
		<%
				Boolean autorizado = (Boolean)request.getSession().getAttribute("autorizado");
				if(autorizado!=null&&autorizado.equals(new Boolean("true"))){
			%>
	
		<ul id="jsddm">
			<li><a href="#">Cadastro</a>
				<ul>
					<li><a href="#">Manter</a></li>
					<li><a href="#">Processar</a></li>
					<li><a href="#">Imprimir</a></li>
				</ul>
			</li>
			<li><a href="#">Suprimento</a>
				<ul>
					<li><a href="#">Manter</a></li>
					<li><a href="#">Processar</a></li>
					<li><a href="#">Imprimir</a></li>
				</ul>
			</li>
			<li><a href="#">Compra</a>
				<ul>
					<li><a href="#">Manter</a></li>
					<li><a href="#">Processar</a></li>
					<li><a href="#">Imprimir</a></li>
				</ul>
			</li>
			<li><a href="#">Faturamento</a>
				<ul>
					<li><a href="#">Manter</a></li>
					<li><a href="#">Processar</a></li>
					<li><a href="#">Imprimir</a></li>
				</ul>
			</li>
			<li><a href="#">Financeiro</a>
				<ul>
					<li><a href="#">Manter</a></li>
					<li><a href="#">Processar</a></li>
					<li><a href="#">Imprimir</a></li>
				</ul>
			</li>
			<li><a href="#">Segurança</a>
				<ul>
					<li><a href="#">Manter</a></li>
					<li><a href="#">Processar</a></li>
					<li><a href="#">Imprimir</a></li>
				</ul>
			</li>
		</ul>
		<div class="clear"> </div>
		
		<%}%>
	
		<%if(autorizado==null||autorizado.equals(new Boolean("false"))){%>
			<a href="" id="btnLogin">Clique aqui para cadastrar sua empresa</a>
		<%}%>
		<img style=" position:relative; top:35px;" src="img/logo.jpg"/>
	</div>
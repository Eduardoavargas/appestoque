<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.ResourceBundle"%>
<%ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());%>
<html>
<head>
<title><%=bundle.getString("app.titulo")%></title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="/js/s3Slider.js"></script>

<script type="text/javascript">

	$(document).ready(function() {	
		$('#slider').s3Slider({
			timeOut: 4000
		});
	});
	
	function validar() {
		if (document.getElementById('email').value.length == 0) {
			alert('<%=bundle.getString("app.mensagem.obrigatoriedade.email")%>');
			document.getElementById('email').focus();
			return false;
		} else if (document.getElementById('senha').value.length == 0) {
			alert('<%=bundle.getString("app.mensagem.obrigatoriedade.senha")%>');
			document.getElementById('senha').focus();
			return false;
		} else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.getElementById('email').value))) {
			alert('<%=bundle.getString("app.mensagem.validar.email")%>');
			document.getElementById('email').focus();
			return false;
		} else {
			return true;
		}
	}

	function getXMLObject() {
		var xmlHttp = false;
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")
			} catch (e2) {
				xmlHttp = false 
			}
		}
		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
			xmlHttp = new XMLHttpRequest();
		}
		return xmlHttp;
	}

	var xmlhttp = new getXMLObject();
	
	function ajaxFunction() {
		  if(xmlhttp) { 
		   var txtname = document.getElementById("txtname");
		    xmlhttp.open("POST","login",true);
		    xmlhttp.onreadystatechange  = handleServerResponse;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send("email="+document.getElementById('email').value+"&senha="+document.getElementById('senha').value);
		  }
		  return false;
		}
	
		String.prototype.trim = function () {
		    return this.replace(/^\s*/, "").replace(/\s*$/, "");
		}
	
		function handleServerResponse() {
		   if (xmlhttp.readyState == 4) {
		     if(xmlhttp.status == 200) {
		    	 var autenticado = Boolean(xmlhttp.responseText.trim()=='true');  
		    	 if(autenticado){
		    		 document.forms[0].submit();	 
		    	 }else{
		    		 alert('<%=bundle.getString("app.mensagem.autenticacao.invalida")%>');	 
		    	 }
		     }
		     else {
		        alert('<%=bundle.getString("app.mensagem.requisicao.invalida")%>');
		     }
		   }
		}
	
</script>


<style type="text/css">

#topo {
	width: 950px;
	margin: 0 auto;
}

body {
	background: url(/img/fundo-body.jpg) repeat-x;
	margin: 0px;
	text-align: center;
	font-family: Arial;
	font-size: 12px;
}

#btnCadastrar {
	display: block;
	width: 249px;
	height: 29px;
	line-height: 29px;
	color: #fff;
	text-decoration: none;
	background: url(/img/btCadastrar.jpg) no-repeat;
	float: right;
	margin-top: 15px;
}

#btnCadastrar:hover {
	text-decoration: underline;
}

#conteudo {
	width: 950px;
	margin: 0 auto;
	padding-top: 120px;
	text-align: left;
	overflow: hidden;
}

#texto_explicativo {
	width: 350px;
	overflow: hidden;
	float: left;
}

#android {
	overflow: hidden;
	float: left;
	margin-right: 5px;
}


h2 {
	font: 3em georgia, fantasy, serif bold;
}

.blocoExplicacao {
	color: #676E70;
	line-height: 19px;
	margin-top: 10px;
	margin-bottom: 30px;
	font-size: 13px;
}

#rodape {
	height: 71px;
	line-height: 71px;
	background: url(/img/fundo-rodape.jpg) repeat-x;
	border-top: 1px solid #DEDEDE;
	margin-top: 10px;
	color: #B2B2B2;
}

#rodape a {
	color: #B2D65D;
	text-decoration: none;
}

#login {
	width: 240px;
	height: 260px;
 	overflow: hidden;
	float: right; 
}

#form_login h3 {
	margin: 0;
	background: #57a700 url(/img/form_heading.gif) repeat-x;
	color: #fff;
	font-size: 20px;
	border: 1px solid #57a700;
	border-bottom: none;
}

#form_login h3 span {
	display: block;
	padding: 10px 20px;
	background: url(/img/fechadura.png) no-repeat 93% 50%;
}

#form_login fieldset {
	margin: 0;
	padding: 0;
	border: none;
	border-top: 3px solid #000;
	background: #000 url(/img/form_top.gif) repeat-x;
	padding-bottom: 1em;
}

#form_login legend {
	display: none;
}

#form_login label{
	width: 100%;
	margin: 5px;
	font: 1.2em verdana, helvetica, serif;
	color: #fff;
}

#form_login input{
	margin: 5px 5px 5px 5px;
	border: 1px solid #111;
	background: #282828 url(/img/form_input.gif) repeat-x;
	color: #fff;
	width: 95%;
}

#form_login button  {
	padding: 0 30px;
	margin: 5px 5px 5px 5px;
	height: 32px;
	border: 1px solid #70ad2e;
	background: #5aae00 url(/img/form_button.gif) repeat-x;
	color: #fff;
	cursor: pointer;
	text-align: center;
	width: 95%;
}













		#slider {
			width: 285px; /* important to be same as image width */
			height: 350px; /* important to be same as image height */
			position: relative; /* important */
			overflow: hidden;
			float: left;
/*  			margin-right: 10px; */
 			margin-left: 10px;
		}
		
		#sliderContent {
			width: 285px; /* important to be same as image width or wider */
			position: absolute;
			top: 0;
			margin-left: 0;
		}
		
		.sliderImage {
			float: left;
			position: relative;
			display: none;
		}
		
		.sliderImage span {
			position: absolute;
			font: 10px Arial, Helvetica, sans-serif;
			padding: 10px 13px;
			width: 258px;
			background-color: #000;
			filter: alpha(opacity=70);
			-moz-opacity: 0.7;
			-khtml-opacity: 0.7;
			opacity: 0.7;
			color: #fff;
			display: none;
		}
		
		.clear {
			clear: both;
		}
		
		.sliderImage span strong {
			font-size: 14px;
		}
		
		.top {
			top: 0;
			left: 0;
		}
		
		.bottom {
			bottom: 0;
			left: 0;
		}
		
		ul { 
			list-style-type: none;
		}








</style>


</head>
<body>
		
		<div id="topo">
			<br/><br/><br/>
			<img style="position: relative; top: 50px;" src="img/logo.jpg" />
		</div>
		
		<div id="conteudo">
			
			<div id="texto_explicativo">
				<h2>Appestoque Web</h2>
				<p class="blocoExplicacao">Site na web para controle de Cliente, Produto e Pedido de Venda.</p>
				<h2>Appestoque Mobile</h2>
				<p class="blocoExplicacao">Aplicativo para dispositivos móveis como tablet e smartphone para controle de Cliente, 
				Produto e Pedido de Venda conforme imagens ao lado.</p>
				<a href="http://twitter.com/#!/appestoque" target="_blank" class="link"><img src="img/twitter.gif" border="0"></a>
				<a href="http://www.facebook.com/appestoque/" target="_blank" class="link"><img src="img/facebook.gif" border="0"></a>
			</div>
			
			
			    <div id="slider">
			        <ul id="sliderContent">
			            <li class="sliderImage">
			                <a href=""><img src="/img/1.png" alt="1" /></a>
			                <span class="bottom"><strong>Produto</strong><br />Tela para pesquisar produtos.</span>
			            </li>
			            <li class="sliderImage">
			                <a href=""><img src="/img/2.png" alt="2" /></a>
			                <span class="bottom"><strong>Cliente</strong><br />Tela para pesquisar clientes.</span>
			            </li>
			            <li class="sliderImage">
			                <img src="/img/3.png" alt="3" />
			                <span class="bottom"><strong>Pedido</strong><br />Tela para pesquisar pedidos.</span>
			            </li>
			            <li class="sliderImage">
			                <img src="/img/5.png" alt="4" />
			                <span class="bottom"><strong>Pedido</strong><br />Tela para cadastrar pedido.</span>
			            </li>
			            <li class="sliderImage">
			                <img src="/img/6.png" alt="5" />
			                <span class="bottom"><strong>Pedido</strong><br />Tela para cadastrar item.</span>
			            </li>
			            <div class="clear sliderImage"></div>
			        </ul>
			    </div>
			
			
			
		
			<div id="login">
				<form id="form_login" action="/menu.jsp" method="post">
					<h3><span>Login</span></h3>
					<fieldset>
						<table width="240px">
							<tr>
								<td><label>E-mail</label></td>
							</tr>	
							<tr>	
								<td><input type="text" id="email" name="email"/></td>
							</tr>
							<tr>
								<td><label>Senha</label></td>
							</tr>	
							<tr>
								<td><input type="password" id="senha" name="senha" /></td>						
							</tr>	
							<tr>					
								<td colspan="2" align="center" ><button type="submit" onclick="return ajaxFunction()">Acessar</button></td>
							</tr>
						</table>
						<a style="float: right; color: #ffffff; margin-right: 10px" href="/processo?acao=preparar">Cadastre sua empresa</a>
					</fieldset>
				</form>
			</div>
			
		</div>
		
		<div id="rodape">
			© 2012 appestoque - <a href="" title="Política de Privacidade" onclick="return false;">Política de Privacidade</a>
		</div>
	
</body>
</html>
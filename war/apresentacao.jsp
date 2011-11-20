<html>
<head>
<title></title>
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
	width: 616px;
	overflow: hidden;
	float: left;
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
	height: 240px;
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

</style>
</head>
<body>
		<div id="topo">
			<a href="" id="btnCadastrar">Clique aqui para cadastrar sua empresa</a> 
			<img style="position: relative; top: 50px;" src="img/logo.jpg" />
		</div>
		<div id="conteudo">
			<div id="texto_explicativo">
				<h2>O que é o appestoque ?</h2>
				<p class="blocoExplicacao">O appestoque é um controle de estoque na web.</p>
				<h2>Para que serve o appestoque ?</h2>
				<p class="blocoExplicacao">O appestoque é utilizado para controlar produtos de uma pequena empresa.</p>
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
								<td><input type="text" name="email"/></td>
							</tr>
							<tr>
								<td><label>Senha</label></td>
							</tr>	
							<tr>
								<td><input type="password" name="senha" /></td>						
							</tr>	
							<tr>					
								<td colspan="2" align="center" ><button type="submit">Acessar</button></td>
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
		</div>
	<div id="rodape">© 2011 appestoque - <a href="" title="Política de Privacidade">Política de Privacidade</a></div>
</body>
</html>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/padrao.css" />
	<link type="text/css" href="/css/le-frog/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.maskedinput-1.3.min.js" ></script>
	<title></title>
</head>
<body>
		<div align="center"><img style="top: 50px;" src="../../../img/logo.jpg" /></div>
		<form id="formCadastro">
		
			<label id="titulo">Cadastro</label><br/><hr></hr><br/>
			
			<label>Nome</label><br/>
			<app:texto id="nome" nome="nome" tamanho="50" valor=""/></p>
			
			<label>CNPJ</label><br/>
			<app:cnpj nome="cnpj" valor=""></app:cnpj></p>
			
			<label>Endereço</label><br/>
			<app:texto nome="cnpj" tamanho="50px" valor=""></app:texto></p>
			
			<label>Número</label><br/>
			<app:numero id="numero" nome="numero" valor=""/></p>
			
			<label>Cep</label><br/>
			<app:cep nome="cep" valor=""/></p>
			
			<label>Complemento</label><br/>
			<app:texto id="complemento" nome="complemento" tamanho="50" valor=""/></p>
			
			<label>Bairro</label><br/>
			<app:texto id="bairro" nome="bairro" tamanho="50" valor=""/></p>
			
			<label>Cidade</label><br/>
			<app:texto id="cidade" nome="cidade" tamanho="50" valor=""/></p>
			
		</form>
</body>
</html>

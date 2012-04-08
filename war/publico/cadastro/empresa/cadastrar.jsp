<%@ page import="br.com.appestoque.dominio.cadastro.Empresa"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/padrao.css" />
<link type="text/css" href="/css/le-frog/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.maskedinput-1.3.min.js" ></script>
<script type="text/javascript" src="/js/padrao.js"></script>

<title></title>

</head>
<body>

	<div id="tudo" align="center" >
		<div id="logo"><img style="top: 50px;" src="../../../img/logo.jpg" /></div>
		<div id="formulario" align="left">

			<%
				Empresa objeto = (Empresa) request.getAttribute("objeto");
			%>
		
			<form id="formCadastro" method="post" action="/processo?acao=cadastrar">
			
				<script>
						function validar(){
							if(document.getElementById('nome').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.nome")%>');
								document.getElementById('nome').focus();
							}else if(document.getElementById('razao').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.razao")%>');
								document.getElementById('razao').focus();
							}else if(document.getElementById('email').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.email")%>');
								document.getElementById('email').focus();
							}else if(document.getElementById('cnpj').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.cnpj")%>');
								document.getElementById('cnpj').focus();
							}else if(document.getElementById('endereco').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.endereco")%>');
								document.getElementById('endereco').focus();
							}else if(document.getElementById('cep').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.cep")%>');
								document.getElementById('cep').focus();
							}else if(document.getElementById('bairro').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.bairro")%>');
								document.getElementById('bairro').focus();
							}else if(document.getElementById('cidade').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.cidade")%>');
								document.getElementById('cidade').focus();
							}else if(document.getElementById('senha').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.senha")%>');
								document.getElementById('senha').focus();
							}else if(document.getElementById('senhaConfirmacao').value.length==0){
								alert('<%=bundle.getString("mensagem.validar.senha.confirmacao")%>');
								document.getElementById('senhaConfirmacao').focus();
							}else if(document.getElementById('senha').value!=document.getElementById('senhaConfirmacao').value){
								alert('<%=bundle.getString("mensagem.validar.senha.naoconfere")%>');
								document.getElementById('senha').focus();
							}else{
								var cnpj = validarCNPJ(document.getElementById('cnpj'));
								var email = validarEmail(document.getElementById('email'));
								if(cnpj&&email){
									alert('<%=bundle.getString("app.mensagem.empresa.confirmacao")%>');
									document.forms[0].submit();
								}else if(!cnpj){									
									alert('<%=bundle.getString("mensagem.cnpj.valido")%>');
									document.getElementById('cnpj').focus();
								}else if(!email){
									alert('<%=bundle.getString("mensagem.email.valido")%>');
									document.getElementById('email').focus();
								}
								return cnpj&&email;
							}
						}
				</script>
			
			<label id="titulo">Cadastro</label><br/><hr></hr><br/>
			
			<label><%=bundle.getString("empresa.nome")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="nome" nome="nome" tamanho="50" valor="<%=objeto.getNome()%>"/></p>
			
			<label><%=bundle.getString("empresa.razao")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="razao" nome="razao" tamanho="50" valor="<%=objeto.getRazao()%>"/></p>
			
			<label><%=bundle.getString("email")%><span class="obrigatorio">*</span></label><br/>
			<app:email nome="email" tamanho="50"  valor="<%=objeto.getEmail()%>"/></p>
			
			<label><%=bundle.getString("cnpj")%><span class="obrigatorio">*</span></label><br/>
			<app:cnpj nome="cnpj" valor="<%=objeto.getCnpj()%>"></app:cnpj></p>
			
			<label><%=bundle.getString("endereco")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="endereco" nome="endereco" tamanho="50" valor=""></app:texto></p>
			
			<label><%=bundle.getString("numero")%></label><br/>
			<app:numero id="numero" nome="numero" tamanho="5" valor=""/></p>
			
			<label><%=bundle.getString("cep")%><span class="obrigatorio">*</span></label><br/>
			<app:cep nome="cep" valor=""/></p>
			
			<label><%=bundle.getString("complemento")%></label><br/>
			<app:texto id="complemento" nome="complemento" tamanho="50" valor=""/></p>
			
			<label><%=bundle.getString("bairro")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="bairro" nome="bairro" tamanho="50" valor=""/></p>
			
			<label><%=bundle.getString("cidade")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="cidade" nome="cidade" tamanho="50" valor=""/></p>
			
			<label><%=bundle.getString("senha")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="senha" tipo="password" nome="senha" valor=""/></p>
			
			<label><%=bundle.getString("senhaConfirmacao")%><span class="obrigatorio">*</span></label><br/>
			<app:texto id="senhaConfirmacao" tipo="password" nome="senha" valor=""/></p>
			
			<p>
				<a href="#"
				    onclick="return validar();"
					id="confirmar"
					style="z-index:0;"
					class="ui-state-default ui-corner-all"><span
					class="ui-icon ui-icon-disk"></span>Confirmar</a>
				<a href="/apresentacao.jsp"				    
					style="z-index:0;"
					class="ui-state-default ui-corner-all"><span
					class="ui-icon ui-icon-cancel"></span>Cancelar</a>
			</p>
			
		</form>
		
		</div>
	</div>

</body>
</html>
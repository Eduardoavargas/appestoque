<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Usuario objeto = (Usuario)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("usuario.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/usuarioControle?acao=modificar">
		<app:barraEditar acao="/usuarioControle"/>
		
		<script type="text/javascript">
			$("#salvar").click(function () {
				alert(document.getElementById('nome').value);
				alert(document.getElementById('senha').value);
				if(document.getElementById('nome').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.nome")%>');
					document.getElementById('nome').focus();
				}else if(document.getElementById('senha').value.length==0){
					alert('<%=bundle.getString("mensagem.validar.senha")%>');
					document.getElementById('senha').focus();
				}else{
					document.forms[0].submit();	
				}
			});
		</script>
		
		
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			<%=bundle.getString("usuario.nome")%><br/><app:texto nome="nome" valor="<%=objeto.getNome()%>" tamanho="50"/></p>
			<%=bundle.getString("usuario.email")%><br/><app:texto nome="email" valor="<%=objeto.getEmail()%>" tamanho="50" desativar="true"/></p>
			<%=bundle.getString("usuario.senha")%><br/><app:texto nome="senha" tipo="password" valor="<%=objeto.getSenha()%>"/></p>
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
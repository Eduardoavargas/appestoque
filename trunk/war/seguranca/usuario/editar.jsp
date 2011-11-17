<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@include file="../../modelo/inicio.jspf" %>
	<%Usuario objeto = (Usuario)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("usuario.editar.titulo")%></span>
	<p/>
	<p><a href="/seguranca/usuario/listar.jsp"/><%=bundle.getString("link.retornar")%></a></p>
	<form method="post" action="/usuarioControle?acao=modificar">
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<table border="0" cellspacing="0" cellpadding="5" bgcolor="#CCCCCC" width="100%">
			<tr bgcolor="#5aae00"  >
				<td style="color: #ffffff; font-weight: bold;">Nome</td>
				<td bgcolor="#ffffff"><input type="input" name="nome" value="<%=objeto.getNome()!=null?objeto.getNome():""%>"></td>
			</tr>
			<tr bgcolor="#5aae00">
				<td style="color: #ffffff; font-weight: bold;">E-mail</td>
				<td bgcolor="#ffffff"><input type="input" name="email" value="<%=objeto.getEmail()!=null?objeto.getEmail():""%>"></td>
			</tr>
			<tr bgcolor="#5aae00">
				<td style="color: #ffffff; font-weight: bold;">Senha</td>
				<td bgcolor="#ffffff"><input type="input" name="senha" value="<%=objeto.getSenha()!=null?objeto.getSenha():""%>"></td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#ffffff" align="center"><input type="submit" value="<%=bundle.getString("botao.confirmar")%>"></td>
			</tr>
		</table>
	</form>
<%@include file="../../modelo/fim.jspf" %>
<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@include file="../../modelo/inicio.jspf" %>
	<%Usuario objeto = (Usuario)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("usuario.editar.titulo")%></span>
	<p/>
	<p><a href="/seguranca/usuario/listar.jsp"/><%=bundle.getString("link.retornar")%></a></p>
	<form method="post" action="/usuarioControle?acao=editar">
		<input type="hidden" name="id" value="<%=objeto.getEmail()%>"/>
		<table border="0" cellspacing="1" cellpadding="5" bgcolor="#CCCCCC">
			<tr bgcolor="#407BA8">
				<td style="color: #ffffff; font-weight: bold;">E-mail</td>
				<td bgcolor="#ffffff"><input type="input" name="email" value="<%=objeto.getEmail()!=null?objeto.getEmail():""%>"></td>
			</tr>
			<tr bgcolor="#407BA8">
				<td style="color: #ffffff; font-weight: bold;">Senha</td>
				<td bgcolor="#ffffff"><input type="input" name="senha" value="<%=objeto.getEmail()!=null?objeto.getEmail():""%>"></td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#ffffff" align="center"><input type="submit" value="<%=bundle.getString("botao.confirmar")%>"></td>
			</tr>
		</table>
	</form>
<%@include file="../../modelo/fim.jspf" %>
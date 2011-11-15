<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("usuario.pesquisar.titulo")%></span>
	<p><a href="/usuarioControle?acao=criar"/><%=bundle.getString("link.adicionar")%></a></p>
	<form method="post" action="/usuarioControle?acao=pesquisar">
		<span class="heading"><%=bundle.getString("usuario.filtro.nome")%></span>
		<p/>
		<input type="text" name="email" style="width: 300px"/>
		&nbsp
		<input type="submit" value="<%=bundle.getString("botao.pesquisar")%>"/>
		&nbsp	
	</form>
	
	<%
		List<Usuario> objetos = new ArrayList<Usuario>();
		if(request.getAttribute("objetos")!=null){
			objetos = (List<Usuario>)request.getAttribute("objetos");
		} 
	%>
	
	<p/>	
	<% if (objetos.size() > 0) { %>
		<span class="heading"><%= objetos.size() %> usuários correspondentes aos seus critérios de buscas:</span>
			
		<p/>
		<table border="0" cellspacing="1" cellpadding="5" bgcolor="#CCCCCC" width="50%">
		<tr bgcolor="#407BA8">
			<td style="color: #ffffff; font-weight: bold;">Nome</td>
			<td style="color: #ffffff; font-weight: bold;">E-mail</td>
			<td style="color: #ffffff; font-weight: bold;">Senha</td>
		</tr>
		<% for (int i = 0;i<objetos.size();i++) { %>
			<% Usuario obj = (Usuario) objetos.get(i); %>
			<tr style="background:#ffffff" onMouseOver="this.style.background='#eeeeee';" onMouseOut="this.style.background='#ffffff';">
				<td><a href="/usuarioControle?acao=editar&id=<%=obj.getId()%>"><%=obj.getNome()%></a></td>
				<td><%=obj.getEmail()%></a></td>
				<td><%=obj.getSenha()%></td>
			</tr>
		<% } %>
		</table>
	
	<% } else { %>
		<span class="heading">Nenhum usuário correspondente foi encontrado.</span>
	<% } %>
	<p/>
	
<%@include file="../../modelo/fim.jspf" %>


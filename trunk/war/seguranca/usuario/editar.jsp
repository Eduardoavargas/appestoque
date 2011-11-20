<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf" %>
	<%Usuario objeto = (Usuario)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("usuario.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/usuarioControle?acao=modificar">
		<p>
		<a href="/usuarioControle?acao=iniciar" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-arrowreturnthick-1-w"></span><%=bundle.getString("link.retornar")%></a>
		<a href="#" onclick="document.forms['formEditar'].submit();" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-disk"></span><%=bundle.getString("link.salvar")%></a>
		</p>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			Nome:<br/><app:texto nome="nome" valor="<%=objeto.getNome()%>"/></p>
			E-mail:<br/><app:texto nome="email" valor="<%=objeto.getEmail()%>"/></p>
			Senha:<br/><app:texto nome="senha" valor="<%=objeto.getSenha()%>"/></p>
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
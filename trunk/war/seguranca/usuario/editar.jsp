<%@ page import="br.com.appestoque.dominio.seguranca.Usuario" %>
<%@include file="../../modelo/inicio.jspf" %>
	<%Usuario objeto = (Usuario)request.getAttribute("objeto");%>
	<span class="title"><%=bundle.getString("usuario.editar.titulo")%></span>
	<form id="formEditar" method="post" action="/usuarioControle?acao=modificar">
		<p>
		<a href="/usuarioControle?acao=iniciar" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-arrowreturnthick-1-w"></span><%=bundle.getString("link.retornar")%></a>
		<a href="#" onclick="formEditar.submit();" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-disk"></span><%=bundle.getString("link.salvar")%></a>
		</p>
		<input type="hidden" name="id" value="<%=objeto.getId()!=null?objeto.getId():""%>"/>
		<hr><br/>
			Nome:<br/><input type="input" name="nome" value="<%=objeto.getNome()!=null?objeto.getNome():""%>"></p>
			E-mail:<br/><input type="input" name="email" value="<%=objeto.getEmail()!=null?objeto.getEmail():""%>"></p>
			Senha:<br/><input type="input" name="senha" value="<%=objeto.getSenha()!=null?objeto.getSenha():""%>"></p>
		<hr>
	</form>
<%@include file="../../modelo/fim.jspf" %>
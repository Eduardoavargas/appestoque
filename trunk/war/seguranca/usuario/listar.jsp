<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("usuario.pesquisar.titulo")%></span>
	<p><a href="usuarioControle?acao=criar"/><%=bundle.getString("usuario.link.adicionar")%></a></p>
	<form method="post" action="usuarioControle">
		<input type="hidden" name="acao" value="pesquisar"/>
		<span class="heading"><%=bundle.getString("usuario.filtro.nome")%></span>
		<p/>
		<input type="text" name="accountName" value="" style="width: 300px"/>
		&nbsp
		<input type="submit" value="Search"/>
		&nbsp	
	</form>
<%@include file="../../modelo/fim.jspf" %>


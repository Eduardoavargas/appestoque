<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/modelo/cabecalho.jsp" />

<div id="conteudo">

	<div id="boxLeft">
	
		<h2>O que � o appestoque ?</h2>
		<p class="blocoExplicacao">Seguindo uma tend�ncia que vem
			crescendo nos �ltimos tempos, o Sen�ide baseia-se no que se conhece
			por "Computa��o na Nuvem", que nada mais � que ter um aplicativo
			online onde o usu�rio cria uma conta de acesso no site permitindo o
			uso dos recursos ali dispon�veis, assim, o usu�rio pode realizar todo
			um trabalho, salv�-lo e acess�-lo em qualquer parte do globo
			terrestre. O objetivo do Sen�ide � disponibilizar aos seus usu�rios a
			possibilidade de ouvir suas m�sicas favoritas em qualquer lugar que
			esteja, sem que seja necess�rio carreg�-las em algum dispositivo ou
			m�dia.</p>

		<h2>Para que serve appestoque ?</h2>
		<p class="blocoExplicacao">Seguindo uma tend�ncia que vem
			crescendo nos �ltimos tempos, o Sen�ide baseia-se no que se conhece
			por "Computa��o na Nuvem", que nada mais � que ter um aplicativo
			online onde o usu�rio cria uma conta de acesso no site permitindo o
			uso dos recursos ali dispon�veis, assim, o usu�rio pode realizar todo
			um trabalho, salv�-lo e acess�-lo em qualquer parte do globo
			terrestre. O objetivo do Sen�ide � disponibilizar aos seus usu�rios a
			possibilidade de ouvir suas m�sicas favoritas em qualquer lugar que
			esteja, sem que seja necess�rio carreg�-las em algum dispositivo ou
			m�dia.</p>
		
	</div>

	<div id="boxSideBar">
		<form id="login" action="/menu.jsp" method="post">
			<h3><span>login</span></h3>
			<fieldset>
				<table width="100%">
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

<c:import url="/modelo/rodape.jsp" />

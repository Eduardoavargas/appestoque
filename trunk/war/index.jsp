<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/modelo/cabecalho.jsp" />

<div id="conteudo">

	<div id="boxLeft">
	
		<h2>O que é o appestoque ?</h2>
		<p class="blocoExplicacao">Seguindo uma tendência que vem
			crescendo nos últimos tempos, o Senóide baseia-se no que se conhece
			por "Computação na Nuvem", que nada mais é que ter um aplicativo
			online onde o usuário cria uma conta de acesso no site permitindo o
			uso dos recursos ali disponíveis, assim, o usuário pode realizar todo
			um trabalho, salvá-lo e acessá-lo em qualquer parte do globo
			terrestre. O objetivo do Senóide é disponibilizar aos seus usuários a
			possibilidade de ouvir suas músicas favoritas em qualquer lugar que
			esteja, sem que seja necessário carregá-las em algum dispositivo ou
			mídia.</p>

		<h2>Para que serve appestoque ?</h2>
		<p class="blocoExplicacao">Seguindo uma tendência que vem
			crescendo nos últimos tempos, o Senóide baseia-se no que se conhece
			por "Computação na Nuvem", que nada mais é que ter um aplicativo
			online onde o usuário cria uma conta de acesso no site permitindo o
			uso dos recursos ali disponíveis, assim, o usuário pode realizar todo
			um trabalho, salvá-lo e acessá-lo em qualquer parte do globo
			terrestre. O objetivo do Senóide é disponibilizar aos seus usuários a
			possibilidade de ouvir suas músicas favoritas em qualquer lugar que
			esteja, sem que seja necessário carregá-las em algum dispositivo ou
			mídia.</p>
		
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

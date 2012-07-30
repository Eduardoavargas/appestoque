<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("ferramenta.painel.bordo.titulo")%></span>
	
	<script type="text/javascript">
		iniciar();
	</script>	
	
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1" onclick="iniciar();">Gr�fico de Linha</a></li>
			<li><a href="#tabs-2" onclick="iniciar();">Mapa de Localiza��o</a></li>
		</ul>
		<div id="tabs-1">
			<div id="corpo">
				<div id="grafico"></div>
				<div id="texto" align="justify">
					<p>A demonstra��o de informa��o no gr�fico ao lado � baseada em todos os pedidos de venda feitos no 
					   per�odo referente ao m�s corrente. No sentido vertical temos as faixas de valores das vendas 
					   e no sentido horizontal temos os dias do m�s representados com intervalos alternados.
					   Os dias que n�o existirem movimenta��o de pedidos de venda n�o ser�o exibidos na linha 
					   horizontal das coordenadas.</p>
				</div>   
			</div>
		</div>
		<div id="tabs-2">
		    <div id="corpo">
				<div id="mapa"></div>
				<div id="texto" align="justify">		
					<p>A demonstra��o de informa��o no mapa ao lado � baseada em todos os pedidos de venda feitos no 
					   per�odo referente ao m�s corrente. Os pontos de marca��o s�o baseados na geolocaliza��o referente 
					   ao momento em que o pedido de venda foi sincronizado pelo representante. Ao passar o mouse sobre o 
					   marcador � poss�vel identificar o cliente no pedido. Ao clicar sobre o marcador � poss�vel ver 
					   informa��es detalhadas e efetuar a impress�o do pedido de venda.</p>
				</div>
			</div>
		</div>
	</div>
	
<%@include file="../../modelo/fim.jspf" %>
<%@include file="../../modelo/inicio.jspf" %>
	<span class="title"><%=bundle.getString("ferramenta.painel.bordo.titulo")%></span>
	
	<script type="text/javascript">
		iniciar();
	</script>	
	
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1" onclick="iniciar();">Gráfico de Linha</a></li>
			<li><a href="#tabs-2" onclick="iniciar();">Mapa de Localização</a></li>
		</ul>
		<div id="tabs-1">
			<div id="corpo">
				<div id="grafico"></div>
				<div id="texto" align="justify">
					<p>A demonstração de informação no gráfico ao lado é baseada em todos os pedidos de venda feitos no 
					   período referente ao mês corrente. No sentido vertical temos as faixas de valores das vendas 
					   e no sentido horizontal temos os dias do mês representados com intervalos alternados.
					   Os dias que não existirem movimentação de pedidos de venda não serão exibidos na linha 
					   horizontal das coordenadas.</p>
				</div>   
			</div>
		</div>
		<div id="tabs-2">
		    <div id="corpo">
				<div id="mapa"></div>
				<div id="texto" align="justify">		
					<p>A demonstração de informação no mapa ao lado é baseada em todos os pedidos de venda feitos no 
					   período referente ao mês corrente. Os pontos de marcação são baseados na geolocalização referente 
					   ao momento em que o pedido de venda foi sincronizado pelo representante. Ao passar o mouse sobre o 
					   marcador é possível identificar o cliente no pedido. Ao clicar sobre o marcador é possível ver 
					   informações detalhadas e efetuar a impressão do pedido de venda.</p>
				</div>
			</div>
		</div>
	</div>
	
<%@include file="../../modelo/fim.jspf" %>
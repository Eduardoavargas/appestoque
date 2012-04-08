<%@include file="/modelo/inicio.jspf"%>

<div align="center" style="width: 100%;" >

	<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js">		
	</script>

	<div style="float: left;">
		<script>
			new TWTR.Widget({
				version : 2,
				type : 'profile',
				rpp : 4,
				interval : 30000,
				width : 250,
				height : 300,
				theme : {
					shell : {
						background : '#5aae00',
						color : '#f7fafa'
					},
					tweets : {
						background : '#000000',
						color : '#ffffff',
						links : '#4aed05'
					}
				},
				features : {
					scrollbar : false,
					loop : false,
					live : false,
					behavior : 'all'
				}
			}).render().setUser('g1').start();
		</script>
	</div>

	
	

</div>

<%@include file="/modelo/fim.jspf"%>
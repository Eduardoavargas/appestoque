<%@ attribute name="nome" required="true"%>
<%@ attribute name="valor" required="true"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="estilo" required="false"%>
<%@ attribute name="tamanho" required="false"%>
<input type="input" id="${id}" name="${nome}" size="${tamanho}"
	style="${estilo}" value="${valor}"
	class="text ui-widget-content ui-corner-all"></input>
<script>	
	$(function() {
		$('#${id}').keyup(function(event) {
			var input = this.value;
	        while (isNaN(input)){
	            input = input.substring(0,input.length-1);
	            $('#${id}').val(input);             
	        }
		});
	});
</script>
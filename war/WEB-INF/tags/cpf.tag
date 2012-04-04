<%@ attribute name="nome" required="true"%>
<%@ attribute name="valor" required="true"%>
<input id="${nome}" name="${nome}" type="input" style="width: 100px"
	   value="${valor}" class="text ui-widget-content ui-corner-all"
	   onblur="validarCPF(this);"></input>
<script>
	$(document).ready(function() {
		$("#${nome}").mask("999.999.999-99");
	});
</script>
<%@ attribute name="nome" required="true"%>
<%@ attribute name="valor" required="true"%>
<input id="${nome}" name="${nome}" type="input" style="width: 120px"
	   value="${valor}" class="text ui-widget-content ui-corner-all"></input>
<script>
	$(document).ready(function() {
		$("#${nome}").mask("99.999.999/9999-99");
	});
</script>
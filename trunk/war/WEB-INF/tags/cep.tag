<%@ attribute name="valor" required="true" %>
<input id="cep" name="cep" type="input" style="width:70px" value="${valor}" class="text ui-widget-content ui-corner-all"></input>
<script>
	$(function() {
		$("#cep").mask("99.999-999");  
	})
</script>
<%@ attribute name="valor" required="true" %>
<input id="cnpj" name="cnpj" type="input" size="17px" value="${valor}" class="text ui-widget-content ui-corner-all"></input>
<script>
	$(function() {
		 $("#cnpj").mask("99.999.999/9999-99");  
	})
</script>
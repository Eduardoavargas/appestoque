<%@ attribute name="nome" required="true" %>
<%@ attribute name="valor" required="true" %>
<input id="${nome}" name="${nome}" type="input" style="width:70px" value="${valor}" class="text ui-widget-content ui-corner-all"></input>
<script>
	$(document).ready(function() {
		$("#${nome}").mask("(99)9999-9999");
	});
</script>
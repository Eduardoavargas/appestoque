<%@ attribute name="id" required="true" %>
<%@ attribute name="texto" required="true" %>
<input id="${id}" name="${id}" type="button" value="${texto}"></input>
<script type="text/javascript">
 	$("#${id}").button();
</script>
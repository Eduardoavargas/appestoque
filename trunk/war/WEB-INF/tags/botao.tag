<%@ attribute name="id" required="true" %>
<%@ attribute name="texto" required="true" %>
<input id="${id}" name="${id}" type="submit" value="${texto}"></input>
<script type="text/javascript">
 	$("#${id}").button();
</script>
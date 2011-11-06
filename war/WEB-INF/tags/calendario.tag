<%@ attribute name="id" required="true" %>
<input id="${id}" name="${id}" type="text"></input>
<script type="text/javascript">
 	$("#${id}").datepicker();
</script>
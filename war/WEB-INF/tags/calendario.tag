<%@ attribute name="id" required="true" %>
<input id="${id}" name="${id}" type="text"></input>
<script>
	$(function() {
		$( "#${id}" ).datepicker();
	});
</script>
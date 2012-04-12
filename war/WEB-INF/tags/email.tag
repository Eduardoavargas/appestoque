<%@ attribute name="nome" required="true"%>
<%@ attribute name="valor" required="true"%>
<%@ attribute name="tamanho" required="false"%>
<%@ attribute name="desativar" required="false"%>
<input id="${nome}" name="${nome}" type="input" size="${tamanho}"
    ${desativar!=null?'disabled="disabled"':''}
	value="${valor}" class="text ui-widget-content ui-corner-all"></input>
<script>
	$('#${nome}').blur(function() {
		if(!validarEmail(this)){
			alert('ATEN\u00c7\u00c3O! E-mail inv\u00e1lido')	
		}
	});
	$('#${nome}').change(function() {
		ajaxValidarEmail(this);
	});

</script>	
	
	

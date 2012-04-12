<%@ attribute name="nome" required="true"%>
<%@ attribute name="valor" required="true"%>
<%@ attribute name="desativar" required="false"%>
<input id="${nome}" name="${nome}" type="input" style="width: 120px"
    ${desativar!=null?'disabled="disabled"':''}
	value="${valor}" class="text ui-widget-content ui-corner-all"></input>
<script>
	$(document).ready(function() {
		$("#${nome}").mask("99.999.999/9999-99");
	});
	$('#${nome}').blur(function() {
		if(!validarCNPJ(this)){
			alert('ATEN\u00c7\u00c3O! CNPJ inv\u00e1lido');	
		}
	});
	$('#${nome}').change(function() {
		ajaxValidarCNPJ(this);
	});
</script>
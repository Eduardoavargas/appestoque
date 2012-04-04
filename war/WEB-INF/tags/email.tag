<%@ attribute name="nome" required="true"%>
<%@ attribute name="valor" required="true"%>
<%@ attribute name="tamanho" required="false"%>
<input id="${nome}" name="${nome}" type="input" size="${tamanho}"
	value="${valor}" class="text ui-widget-content ui-corner-all" 
	onblur="validarEmail(this);"></input>

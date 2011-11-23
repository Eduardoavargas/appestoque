<%@ attribute name="nome" required="true" %>
<%@ attribute name="valor" required="true" %>
<%@ attribute name="tamanho" required="false" %>
<input type="input" size="${tamanho}" name="${nome}" value="${valor}" class="text ui-widget-content ui-corner-all"></input>
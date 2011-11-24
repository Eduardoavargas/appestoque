<%@ attribute name="nome" required="true" %>
<%@ attribute name="valor" required="true" %>
<%@ attribute name="estilo" required="false" %>
<input type="input" name="${nome}" style="${estilo}" value="${valor}" class="text ui-widget-content ui-corner-all"></input>
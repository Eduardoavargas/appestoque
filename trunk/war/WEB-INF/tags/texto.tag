<%@ attribute name="nome" required="true" %>
<%@ attribute name="valor" required="true" %>
<%@ attribute name="id" required="false" %>
<%@ attribute name="estilo" required="false" %>
<%@ attribute name="tamanho" required="false"%>
<%@ attribute name="tipo" required="false"%>
<%@ attribute name="desativar" required="false"%>
<input type="${tipo!=null?tipo:'input'}" ${desativar!=null?'disabled="disabled"':''} id="${id}" name="${nome}" size="${tamanho}" style="${estilo}" value="${valor}" class="text ui-widget-content ui-corner-all"></input>
<%@ attribute name="acao" required="true"%>
<p />
<a href="#" onclick="document.forms[0].submit();"
	style="z-index:0;"
	class="ui-state-default ui-corner-all"><span
	class="ui-icon ui-icon-search"></span>Buscar</a>
<a href="${acao}?acao=criar" 
	style="z-index:0;"
	class="ui-state-default ui-corner-all"><span
	class="ui-icon ui-icon-document"></span>Adicionar</a>
<p />
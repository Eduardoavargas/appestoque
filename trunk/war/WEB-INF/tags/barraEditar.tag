<%@ attribute name="acao" required="true"%>
<p>
	<a href="${acao}?acao=iniciar"
		class="ui-state-default ui-corner-all"><span
		class="ui-icon ui-icon-arrowreturnthick-1-w"></span>Retornar</a>
	<a href="#" onclick="document.forms[0].submit();"
		class="ui-state-default ui-corner-all"><span
		class="ui-icon ui-icon-disk"></span>Salvar</a>
</p>
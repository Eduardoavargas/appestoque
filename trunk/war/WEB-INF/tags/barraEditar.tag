<%@ attribute name="acao" required="true"%>
<p>
	<a href="${acao}?acao=iniciar"
		style="z-index:0;"
		class="ui-state-default ui-corner-all"><span
		class="ui-icon ui-icon-arrowreturnthick-1-w"></span>Retornar</a>
	<a href="#" onclick="document.forms[0].submit();"
		style="z-index:0;"
		class="ui-state-default ui-corner-all"><span
		class="ui-icon ui-icon-disk"></span>Salvar</a>
</p>
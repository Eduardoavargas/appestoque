<%@ attribute name="acao" required="true"%>
<a name="primeiro"
	onclick="return paginar(this,document.getElementById('primeiroRegistro').value,document.getElementById('registrosPorPagina').value,document.getElementById('totalRegistros').value);"
	href="${acaoPrimeiro}?acao=pesquisar&paginar=primeiro&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>"
	class="ui-state-default ui-corner-all"> <span
	class="ui-icon ui-icon-seek-prev"></span>Primeiro</a>
<a name="anterior"
	onclick="return paginar(this,document.getElementById('primeiroRegistro').value,document.getElementById('registrosPorPagina').value,document.getElementById('totalRegistros').value);"
	href="${acaoAnterior}?acao=pesquisar&paginar=anterior&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>"
	class="ui-state-default ui-corner-all"><span
	class="ui-icon ui-icon-seek-first"> </span>Anterior</a>
<a name="proximo"
	onclick="return paginar(this,document.getElementById('primeiroRegistro').value,document.getElementById('registrosPorPagina').value,document.getElementById('totalRegistros').value);"
	href="${acaoProximo}?acao=pesquisar&paginar=proximo&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>"
	class="ui-state-default ui-corner-all"><span
	class="ui-icon ui-icon-seek-end"> </span>Próximo</a>
<a name="ultimo"
	onclick="return paginar(this,document.getElementById('primeiroRegistro').value,document.getElementById('registrosPorPagina').value,document.getElementById('totalRegistros').value);"
	href="${acaoUltimo}?acao=pesquisar&paginar=ultimo&primeiroRegistro=<%=request.getAttribute("primeiroRegistro")%>&totalRegistros=<%=request.getAttribute("totalRegistros")%>&registrosPorPagina=<%=request.getAttribute("registrosPorPagina")%>"
	class="ui-state-default ui-corner-all"><span
	class="ui-icon ui-icon-seek-next"></span>Último</a>
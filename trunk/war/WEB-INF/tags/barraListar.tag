<%@ attribute name="acao" required="true"%>
<%@ attribute name="desativarBuscar" required="false"%>
<%@ attribute name="desativarAdicionar" required="false"%>
<p />
<a href="#" 
   id="buscar"
   style="z-index:0;"
   ${desativarBuscar!=null?'disabled="disabled"':''}
   class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-search"></span>Buscar</a>
<a href="${acao}?acao=criar"
   id="adicionar"
   style="z-index:0;"
   ${desativarAdicionar!=null?'disabled="disabled"':''}
   class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-document"></span>Adicionar</a>
<p />
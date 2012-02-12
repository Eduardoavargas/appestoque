<%@ page import="br.com.appestoque.dominio.cadastro.Cliente"%>
<%@ page import="br.com.appestoque.util.Constantes"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="app"%>
<%@include file="../../modelo/inicio.jspf"%>
<%
	Cliente objeto = (Cliente) request.getAttribute("objeto");
%>
<span class="title"><%=bundle.getString("cliente.editar.titulo")%></span>

<form id="formEditar" method="post" action="/clienteControle?acao=modificar">
	<app:barraEditar acao="/clienteControle"/>

	<script type="text/javascript">
			$("#salvar").click(function () {
				if(document.getElementById('nome').value.length==0){
					alert('<%=bundle.getString("produto.mensagem.validar.nome")%>');
					document.getElementById('nome').focus();
				}else if(document.getElementById('bairro').value.length==0){
					alert('<%=bundle.getString("endereco.mensagem.validar.bairro")%>');
					document.getElementById('bairro').focus();
				}else if(document.getElementById('cidade').value.length==0){
					alert('<%=bundle.getString("endereco.mensagem.validar.cidade")%>');
					document.getElementById('cidade').focus();
				}else if(document.getElementById('cep').value.length==0){
					alert('<%=bundle.getString("endereco.mensagem.validar.cep")%>');
					document.getElementById('cep').focus();
				}else{
					document.forms[0].submit();	
				}
			});
			
			function ajax(url){
				
				var ajax;
			    // Procura o componente nativo do Mozilla/Safari para rodar o AJAX
			    if(window.XMLHttpRequest){
			        // Inicializa o Componente XMLHTTP do Mozilla
			        ajax = new XMLHttpRequest();
			    // Caso ele não encontre, procura por uma versão ActiveX do IE
			    }else if(window.ActiveXObject){
			        // Inicializa o Componente ActiveX para o AJAX
			        ajax = new ActiveXObject("Microsoft.XMLHTTP");
			    }else{
			        // Caso não consiga inicializar nenhum dos componentes, exibe um erro
			        alert("Seu navegador não tem suporte a AJAX.");
			    }
				
			 // Carrega a função de execução do AJAX
			    ajax.onreadystatechange = function() {
			        if(ajax.readyState == 1){
			            // Quando estiver "Carregando a página", exibe a mensagem
			            //document.getElementById(div).innerHTML = "<img src='ajax-loader.gif' alt='AJAX' />";
			        }else if(ajax.readyState == 4 && ajax.status == 200){
			            // Quando estiver completado o Carregamento e o status completo
			            // Procura pela DIV com o id="div" e insere as  informações
			            document.getElementById('teste').value = ajax.responseText;
			        }
			    };
			    
				alert('URL: ' + url );
				
				ajax.open("GET",url,true);
			    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=iso-8859-1")
			    ajax.send(null);
				
			}
			
	</script>

	<input type="hidden" name="id"
		value="<%=objeto.getId() != null ? objeto.getId() : ""%>" />
	<hr>
	
	<%=bundle.getString("cliente.nome")%>:<br />
	<app:texto id="nome" nome="nome" tamanho="50" valor="<%=objeto.getNome()%>"/>
	</p>	
	<%=bundle.getString("cliente.cnpj")%>:<br />
	<app:cnpj nome="cnpj" valor="<%=objeto.getCnpj()%>"/>
	</p>
	<%=bundle.getString("complemento")%>:<br />
	<app:texto id="complemento" nome="complemento" tamanho="50" valor="<%=objeto.getComplemento()%>"/>
	</p>
	<%=bundle.getString("cep")%>:<br />
	<app:cep nome="cep" valor="<%=objeto.getCep()%>"/>
	</p>
	<%=bundle.getString("numero")%>:<br />
	<app:numero id="numero" nome="numero" valor="<%=objeto.getNumero().toString()%>"/>
	</p>
	<%=bundle.getString("bairro.cidade")%>:<br/>
	<select name="idCidade" id="idCidade"  class="text ui-widget-content ui-corner-all" style="cursor:pointer;" onchange="ajax('/cidadeControle?acao=ajax&id='+this.value)">
		<c:forEach var="cidade" items="${cidades}" varStatus="id">
			<option value="${cidade.id}" <c:if test="${cidade.id == idCidade}">selected</c:if> >${cidade.nome}</option>
		</c:forEach>
	</select>
	</p>
	<%=bundle.getString("bairro.cidade")%>:<br/>
	<select name="idBairro" id="idBairro"  class="text ui-widget-content ui-corner-all" style="cursor:pointer;">
		<div id="bairros">
			<c:forEach var="bairro" items="${bairros}" varStatus="id">
				<option value="${bairro.id}" <c:if test="${bairro.id == idBairro}">selected</c:if> >${bairro.nome}</option>
			</c:forEach>
		</div>
	</select>
	</p>
	
	<hr>
</form>
<%@include file="../../modelo/fim.jspf"%>
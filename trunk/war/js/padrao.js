
var timeout = 500;
var closetimer = 0;
var ddmenuitem = 0;

// open hidden layer
function mopen(id) {
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if (ddmenuitem)
		ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose() {
	if (ddmenuitem)
		ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime() {
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime() {
	if (closetimer) {
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose;
// -->

function paginar(obj, primeiroRegistro, registrosPorPagina, totalRegistros){
	var msg = 'Procedimento nao permitido';
	if ((obj.name == 'primeiro') && (parseInt(primeiroRegistro) == 0)) {
		alert(msg);
		return false;
	} if ((obj.name == 'anterior') && (parseInt(primeiroRegistro) == 0)) {
		alert(msg);
		return false;
	} if ((obj.name == 'proximo')
			&& ((parseInt(primeiroRegistro) + parseInt(registrosPorPagina)) >= parseInt(totalRegistros))) {
		alert(msg);
		return false;
	} if ((obj.name == 'ultimo')
			&& ((parseInt(primeiroRegistro) + parseInt(registrosPorPagina)) >= parseInt(totalRegistros))) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

function ajax(url,regiao){
	var ajax;
    if(window.XMLHttpRequest){
        ajax = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        ajax = new ActiveXObject("Microsoft.XMLHTTP");
    }else{
        alert("Seu navegador n�o tem suporte a AJAX.");
    }
    ajax.onreadystatechange = function() {
        if(ajax.readyState == 1){
        	document.getElementById(regiao).innerHTML = "<img src='img/ajax-loader.gif' alt='AJAX' />";	
        }else if(ajax.readyState == 4 && ajax.status == 200){
            document.getElementById(regiao).innerHTML = ajax.responseText;
        }
    };
	ajax.open("GET",url,true);
    ajax.setRequestHeader("Content-Type", "text/plain;charset=UTF-8"); 
    ajax.send(null);
}



function validarCPF(obj){
    if((obj.value.replace(/\D/g,'').length>0)&&!isCpf(obj.value)){
        alert('ATEN\u00c7\u00c3O! CPF inv\u00e1lido');
        return false;
    }else{
        return true;
    }
}


function validarCNPJ(obj){
    if((obj.value.replace(/\D/g,'').length>0)&&!isCnpj(obj.value)){
        alert('ATEN\u00c7\u00c3O! CNPJ inv\u00e1lido');
        return false;
    }else{
        return true;
    }
}

/**
 * @author M�rcio d'�vila
 * @version 1.03, 2004-2008
 *
 * Este script foi retirado de:
 * http://www.mhavila.com.br/topicos/web/cpf_cnpj.html
 *
 * Licenciado sob os termos da licen�a Creative Commons,
 * Atribui��o - Compartilhamento pela mesma licen�a 2.5:
 * http://creativecommons.org/licenses/by-sa/2.5/br/
 * Qualquer outra forma de uso requer autoriza��o expressa do autor.
 *
 * PROT�TIPOS:
 * m�todo String.lpad(int pSize, char pCharPad)
 * m�todo String.trim()
 *
 * String unformatNumber(String pNum)
 * String formatCpfCnpj(String pCpfCnpj, boolean pUseSepar, boolean pIsCnpj)
 * String dvCpfCnpj(String pEfetivo, boolean pIsCnpj)
 * boolean isCpf(String pCpf)
 * boolean isCnpj(String pCnpj)
 * boolean isCpfCnpj(String pCpfCnpj)
 */


NUM_DIGITOS_CPF  = 11;
NUM_DIGITOS_CNPJ = 14;
NUM_DGT_CNPJ_BASE = 8;


/**
 * Adiciona m�todo lpad() � classe String.
 * Preenche a String � esquerda com o caractere fornecido,
 * at� que ela atinja o tamanho especificado.
 */
String.prototype.lpad = function(pSize, pCharPad)
{
	var str = this;
	var dif = pSize - str.length;
	var ch = String(pCharPad).charAt(0);
	for (; dif>0; dif--) str = ch + str;
	return (str);
} //String.lpad


/**
 * Adiciona m�todo trim() � classe String.
 * Elimina brancos no in�cio e fim da String.
 */
String.prototype.trim = function()
{
	return this.replace(/^\s*/, "").replace(/\s*$/, "");
} //String.trim


/**
 * Elimina caracteres de formata��o e zeros � esquerda da string
 * de n�mero fornecida.
 * @param String pNum
 * 	String de n�mero fornecida para ser desformatada.
 * @return String de n�mero desformatada.
 */
function unformatNumber(pNum)
{
	return String(pNum).replace(/\D/g, "").replace(/^0+/, "");
} //unformatNumber


/**
 * Formata a string fornecida como CNPJ ou CPF, adicionando zeros
 * � esquerda se necess�rio e caracteres separadores, conforme solicitado.
 * @param String pCpfCnpj
 * 	String fornecida para ser formatada.
 * @param boolean pUseSepar
 * 	Indica se devem ser usados caracteres separadores (. - /).
 * @param boolean pIsCnpj
 * 	Indica se a string fornecida � um CNPJ.
 * 	Caso contr�rio, � CPF. Default = false (CPF).
 * @return String de CPF ou CNPJ devidamente formatada.
 */
function formatCpfCnpj(pCpfCnpj, pUseSepar, pIsCnpj)
{
	if (pIsCnpj==null) pIsCnpj = false;
	if (pUseSepar==null) pUseSepar = true;
	var maxDigitos = pIsCnpj? NUM_DIGITOS_CNPJ: NUM_DIGITOS_CPF;
	var numero = unformatNumber(pCpfCnpj);

	numero = numero.lpad(maxDigitos, '0');

	if (!pUseSepar) return numero;

	if (pIsCnpj)
	{
		reCnpj = /(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/;
		numero = numero.replace(reCnpj, "$1.$2.$3/$4-$5");
	}
	else
	{
		reCpf  = /(\d{3})(\d{3})(\d{3})(\d{2})$/;
		numero = numero.replace(reCpf, "$1.$2.$3-$4");
	}
	return numero;
} //formatCpfCnpj


/**
 * Calcula os 2 d�gitos verificadores para o n�mero-efetivo pEfetivo de
 * CNPJ (12 d�gitos) ou CPF (9 d�gitos) fornecido. pIsCnpj � booleano e
 * informa se o n�mero-efetivo fornecido � CNPJ (default = false).
 * @param String pEfetivo
 * 	String do n�mero-efetivo (SEM d�gitos verificadores) de CNPJ ou CPF.
 * @param boolean pIsCnpj
 * 	Indica se a string fornecida � de um CNPJ.
 * 	Caso contr�rio, � CPF. Default = false (CPF).
 * @return String com os dois d�gitos verificadores.
 */
function dvCpfCnpj(pEfetivo, pIsCnpj)
{
	if (pIsCnpj==null) pIsCnpj = false;
	var i, j, k, soma, dv;
	var cicloPeso = pIsCnpj? NUM_DGT_CNPJ_BASE: NUM_DIGITOS_CPF;
	var maxDigitos = pIsCnpj? NUM_DIGITOS_CNPJ: NUM_DIGITOS_CPF;
	var calculado = formatCpfCnpj(pEfetivo + "00", false, pIsCnpj);
	calculado = calculado.substring(0, maxDigitos-2);
	var result = "";

	for (j = 1; j <= 2; j++)
	{
		k = 2;
		soma = 0;
		for (i = calculado.length-1; i >= 0; i--)
		{
			soma += (calculado.charAt(i) - '0') * k;
			k = (k-1) % cicloPeso + 2;
		}
		dv = 11 - soma % 11;
		if (dv > 9) dv = 0;
		calculado += dv;
		result += dv
	}

	return result;
} //dvCpfCnpj


/**
 * Testa se a String pCpf fornecida � um CPF v�lido.
 * Qualquer formata��o que n�o seja algarismos � desconsiderada.
 * @param String pCpf
 * 	String fornecida para ser testada.
 * @return <code>true</code> se a String fornecida for um CPF v�lido.
 */
function isCpf(pCpf){
	var numero = formatCpfCnpj(pCpf, false, false);
    
	if (numero.length > NUM_DIGITOS_CPF) return false;
    
	var base = numero.substring(0, numero.length - 2);
	var digitos = dvCpfCnpj(base, false);
	var algUnico, i;

	// Valida d�gitos verificadores
	if (numero != "" + base + digitos) return false;

	/* N�o ser�o considerados v�lidos os seguintes CPF:
	 * 000.000.000-00, 111.111.111-11, 222.222.222-22, 333.333.333-33, 444.444.444-44,
	 * 555.555.555-55, 666.666.666-66, 777.777.777-77, 888.888.888-88, 999.999.999-99.
	 */
	algUnico = true;
	for (i=1; algUnico && i<NUM_DIGITOS_CPF; i++){
		algUnico = (numero.charAt(i-1) == numero.charAt(i));
	}
    
	return (!algUnico);
    
} 


/**
 * Testa se a String pCnpj fornecida � um CNPJ v�lido.
 * Qualquer formata��o que n�o seja algarismos � desconsiderada.
 * @param String pCnpj
 * 	String fornecida para ser testada.
 * @return <code>true</code> se a String fornecida for um CNPJ v�lido.
 */
function isCnpj(pCnpj)
{
	var numero = formatCpfCnpj(pCnpj, false, true);
	if (numero.length > NUM_DIGITOS_CNPJ) return false;

	var base = numero.substring(0, NUM_DGT_CNPJ_BASE);
	var ordem = numero.substring(NUM_DGT_CNPJ_BASE, 12);
	var digitos = dvCpfCnpj(base + ordem, true);
	var algUnico;

	// Valida d�gitos verificadores
	if (numero != "" + base + ordem + digitos) return false;

	/* N�o ser�o considerados v�lidos os CNPJ com os seguintes n�meros B�SICOS:
	 * 11.111.111, 22.222.222, 33.333.333, 44.444.444, 55.555.555,
	 * 66.666.666, 77.777.777, 88.888.888, 99.999.999.
	 */
	algUnico = numero.charAt(0) != '0';
	for (i=1; algUnico && i<NUM_DGT_CNPJ_BASE; i++)
	{
		algUnico = (numero.charAt(i-1) == numero.charAt(i));
	}
	if (algUnico) return false;

	/* N�o ser� considerado v�lido CNPJ com n�mero de ORDEM igual a 0000.
	 * N�o ser� considerado v�lido CNPJ com n�mero de ORDEM maior do que 0300
	 * e com as tr�s primeiras posi��es do n�mero B�SICO com 000 (zeros).
	 * Esta cr�tica n�o ser� feita quando o no B�SICO do CNPJ for igual a 00.000.000.
	 */
	if (ordem == "0000") return false;
	return (base == "00000000"
		|| parseInt(ordem, 10) <= 300 || base.substring(0, 3) != "000");
} //isCnpj


/**
 * Testa se a String pCpfCnpj fornecida � um CPF ou CNPJ v�lido.
 * Se a String tiver uma quantidade de d�gitos igual ou inferior
 * a 11, valida como CPF. Se for maior que 11, valida como CNPJ.
 * Qualquer formata��o que n�o seja algarismos � desconsiderada.
 * @param String pCpfCnpj
 * 	String fornecida para ser testada.
 * @return <code>true</code> se a String fornecida for um CPF ou CNPJ v�lido.
 */
function isCpfCnpj(pCpfCnpj)
{
	var numero = pCpfCnpj.replace(/\D/g, "");
	if (numero.length > NUM_DIGITOS_CPF)
		return isCnpj(pCpfCnpj);
	else
		return isCpf(pCpfCnpj);
} //isCpfCnpj



String.prototype.replaceAll = function(de, para){
    var str = this;
    var pos = str.indexOf(de);
    while (pos > -1){
		str = str.replace(de, para);
		pos = str.indexOf(de);
	}
    return (str);
}



function validarEmail(obj) {
	var x = obj.value;
	var atpos = x.indexOf("@");
	var dotpos = x.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {
		alert('ATEN\u00c7\u00c3O! E-mail inv\u00e1lido')
		return false;
	}
}

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
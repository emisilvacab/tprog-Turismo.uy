function validarAltaSalida(){
	
	if (document.getElementById('form-select-dpto').value.length == 0){
		alert('Seleccione un departamento');
    	return false;
    }
	else if (document.getElementById('form-select-act').value.length == 0){
		alert('Seleccione una actividad');
    	return false;
    }
    else if(document.getElementById('nombreSal').value.length == 0 ){
		alert('Ingrese un nombre para la salida');
    	return false;
	}
    else if(document.getElementById('fechaSal').value.length == 0 ){
		alert('Seleccione una Fecha');
    	return false;
	}
    else if(document.getElementById('horaSal').value.length == 0 ){
		alert('Seleccione una hora');
    	return false;
	}
	 else if(document.getElementById('lugarSal').value.length == 0 ){
		alert('Ingrese un lugar');
    	return false;
	}
    else if(document.getElementById('cantTurSal').value.length == 0 ){
		alert('Ingrese una cantidad de turistas');
    	return false;
	}
    else if(document.getElementById('cantTurSal').value <= 0 ){
		alert('Ingrese una cantidad de turistas mayor que 0');
    	return false;
	}
	return true;
}


function limpiar(select){
	if(select == "dpto"){
		if(document.getElementById("form-select-dpto").options[document.getElementById("form-select-dpto").selectedIndex].text == ""){
	    	document.querySelectorAll('#form-select-act option').forEach(o => o.remove());
		}
	}
}

function getURL(cambio) {
	if (cambio == "dpto"){
		var dpto = document.getElementById("form-select-dpto").options[document.getElementById("form-select-dpto").selectedIndex].text;
		return "altaSalida?cambio=dpto&dpto=" + encodeURIComponent(dpto);
	}
	if (cambio == "act") {
		var dpto = document.getElementById("form-select-dpto").options[document.getElementById("form-select-dpto").selectedIndex].text;
    	var act = document.getElementById("form-select-act").options[document.getElementById("form-select-act").selectedIndex].text;
		if(dpto.length != 0){
			return "altaSalida?cambio=act&dpto=" + encodeURIComponent(dpto) + "&act=" + encodeURIComponent(act);
		}
	}
	return "";
}
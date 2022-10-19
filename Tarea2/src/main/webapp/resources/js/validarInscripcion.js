

function validarInscripcion(){
	if (document.getElementById('form-select-dpto').value.length == 0 && document.getElementById('form-select-cat').value.length == 0){
		alert('Seleccione un departamento o una categoría');
    	return false;
    }
    else if (document.getElementById('form-select-act').value.length == 0){
		alert('Seleccione una actividad');
    	return false;
    }
    else if (document.getElementById('form-select-sal').value.length == 0){
		alert('Seleccione una salida');
    	return false;
    }
    else if (document.getElementById('form-cantTuristas').value.length == 0){
		alert('Ingrese una cantidad de turistas');
    	return false;
    }
	else if (document.getElementById('form-cantTuristas').value < 1){
		alert('Ingrese una cantidad de turistas mayor a 0');
    	return false;
    }
    else if (document.getElementById('checkConPaq').checked && document.getElementById('form-select-paq').value.length == 0){
		alert('Seleccione un paquete');
    	return false;
    }
    
	return true;
	
}

function limpiarCat() {
	document.getElementById("form-select-cat").value = "";
}

function limpiarDpto() {
	document.getElementById("form-select-dpto").value = "";
}

function deshabilitarSal(){
	if (document.getElementById("form-cantTuristas").value == "") {
		alert("Ingrese la cantidad de turistas que desea inscribir antes de seleccionar una salida.");
		document.getElementById("form-select-sal").value = "";
		document.getElementById("form-select-sal").setAttribute("disabled",true);
	}
}

function habilitarSal(){
	document.getElementById("form-select-sal").removeAttribute("disabled");
	document.getElementById("form-select-sal").value = "";
}

function habilitarPaq() {
	document.getElementById("form-select-paq").removeAttribute("disabled")
}

function deshabilitarPaq() {
	document.getElementById("form-select-paq").value = "";
	document.getElementById("form-select-paq").setAttribute("disabled",true);
}

function getURL(cambio) {
	if (cambio == "dpto"){
		return "/Tarea2/inscribir?cambio=dpto&dpto=" + encodeURIComponent(document.getElementById("form-select-dpto").value);
	}
	if(cambio == "cat") {
		return "/Tarea2/inscribir?cambio=cat&cat=" + encodeURIComponent(document.getElementById("form-select-cat").value);
	}
	if (cambio == "act") {
		if(document.getElementById("form-select-dpto").value.legth != 0)
			return "/Tarea2/inscribir?cambio=act&dpto=" + encodeURIComponent(document.getElementById("form-select-dpto").value) + "&act=" + encodeURIComponent(document.getElementById("form-select-act").value);
		if(document.getElementById("form-select-cat").value.legth != 0)
			return "/Tarea2/inscribir?cambio=act&cat=" + encodeURIComponent(document.getElementById("form-select-cat").value) + "&act=" + encodeURIComponent(document.getElementById("form-select-act").value); 
	}
	if (cambio == "sal") {
		salida = document.getElementById("form-select-sal").value.split("-")[0].trim();
		if(document.getElementById("form-select-dpto").value.legth != 0)
			return "/Tarea2/inscribir?cambio=sal&dpto=" + encodeURIComponent(document.getElementById("form-select-dpto").value) + "&act=" + encodeURIComponent(document.getElementById("form-select-act").value) + "&cant=" + encodeURIComponent(document.getElementById("form-cantTuristas").value) + "&sal=" + encodeURIComponent(salida);
		if(document.getElementById("form-select-cat").value.legth != 0)
			return "/Tarea2/inscribir?cambio=sal&cat=" + encodeURIComponent(document.getElementById("form-select-cat").value) + "&act=" + encodeURIComponent(document.getElementById("form-select-act").value) + "&cant=" + encodeURIComponent(document.getElementById("form-cantTuristas").value) + "&sal=" + encodeURIComponent(salida); 
	}
}




















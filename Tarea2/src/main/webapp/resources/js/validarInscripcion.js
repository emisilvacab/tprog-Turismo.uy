
/*PASO 1*/

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

function habilitarPaq() {
	document.getElementById("form-select-paq").removeAttribute("disabled")
}

function deshabilitarPaq() {
	document.getElementById("form-select-paq").value = "";
	document.getElementById("form-select-paq").setAttribute("disabled",true);
}

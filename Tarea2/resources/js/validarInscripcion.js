
/*PASO 1*/

function validarPaso1(){
	if (document.getElementById('form-select-dpto').value.length == 0 && document.getElementById('form-select-cat').value.length == 0){
		alert('Seleccione un departamento o una categoría');
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

/*PASO 4*/

function validarPaso4(){
	if (document.getElementById('form-cantTuristas').value.length == 0){
		alert('Ingrese una cantidad de turistas');
    	return false;
    }
	else if (document.getElementById('form-cantTuristas').value < 1){
		alert('Ingrese una cantidad de turistas mayor a 0');
    	return false;
    }
	return true;
}
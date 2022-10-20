
/*PASO 2*/

function validarCompra(){
	if (document.getElementById('form-select-compraPaq').value.length == 0){
		alert('Seleccione un paquete');
    	return false;
    }
	else if (document.getElementById('form-cantTuristasPaq').value.length == 0){
		alert('Ingrese una cantidad de turistas');
    	return false;
    }
	else if (document.getElementById('form-cantTuristasPaq').value < 1){
		alert('Ingrese una cantidad de turistas mayor a 0');
    	return false;
    }else if (!Number.isInteger(Number(document.getElementById("form-cantTuristasPaq").value))){
		alert('Ingrese un valor entero para la cantidad de turistas.');
    	return false;	
	}
	return true;
}
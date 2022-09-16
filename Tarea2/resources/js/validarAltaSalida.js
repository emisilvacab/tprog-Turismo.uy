function validarAltaSalida(){
	
	if (document.getElementById('depSal').value.length == 0){
		alert('Seleccione un departamento');
    	return false;
    }
	else if (document.getElementById('actSal').value.length == 0){
		alert('Seleccione una actividad');
    	return false;
    }
    else if(document.getElementById('nombreSal').value.length == 0 ){
		alert('Ingrese un nombre para la salida');
    	return false;
	}
	/*se va a cambiar cuando sea dinamico*/
    else if(document.getElementById('nombreSal').value == "Degusta Setiembre" || document.getElementById('nombreSal').value == "Degusta Agosto"){
		alert('Ya existe una salida con ese nombre');
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
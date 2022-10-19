function validarAltaActividad(){
	
	if (document.getElementById('depAct').value.length == 0){
		alert('Seleccione un departamento');
    	return false;
    }
    else if(document.getElementById('nombreAct').value.length == 0 ){
		alert('Ingrese un nombre para la actividad');
    	return false;
	}
    else if(document.getElementById('descripcionAct').value.length == 0 ){
		alert('Ingrese una descripción');
    	return false;
	}
    else if(document.getElementById('durAct').value.length == 0 ){
		alert('Ingrese una duración');
    	return false;
	}
    else if(document.getElementById('durAct').value <= 0 ){
		alert('Ingrese una duración mayor que 0');
    	return false;
	}
    else if(document.getElementById('costoAct').value.length == 0 ){
		alert('Ingrese un costo');
    	return false;
	}
    else if(document.getElementById('costoAct').value < 0 ){
		alert('Ingrese un costo mayor o igual a 0');
    	return false;
	}
    else if(document.getElementById('ciudadAct').value.length == 0 ){
		alert('Ingrese una ciudad');
    	return false;
	}
    else if(document.getElementById('catAct').value.length == 0 ){
		alert('Seleccione categoría(s)');
    	return false;
	}
	alert('Actividad dada de alta');
	return true;
}
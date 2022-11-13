fun function validarIniciarSesion(){	
	if (document.getElementById('nickname').value.length == 0){
		alert('Ingrese nickname');
    	return false;
    }
	else if (document.getElementById('password').value.length == 0){
		alert('Ingrese contraseña');
    	return false;
    }
    return true
	
}
function validarIniciarSesion(){
	console.log("validar");	
	if (document.getElementById('nickname').value.length == 0){
		document.getElementById('lblError').innerText = "Por favor, ingrese un nickname"
    	return false;
    }
	else if (document.getElementById('password').value.length == 0){
		document.getElementById('lblError').innerText = "Por favor, ingrese una contraseña"
    	return false;
    }
    return true
	
}
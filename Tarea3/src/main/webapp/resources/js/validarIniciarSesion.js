function validarIniciarSesion(){
	console.log("validar");	
	if (document.getElementById('nickname').value.length == 0){
		document.getElementById('lblError').innerText = "ingrese un nickname"
    	return false;
    }
	else if (document.getElementById('password').value.length == 0){
		document.getElementById('lblError').innerText = "ingrese una contraseña"
    	return false;
    }
    return true
	
}
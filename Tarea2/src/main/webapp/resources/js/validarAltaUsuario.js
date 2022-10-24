function validarAltaUsuario(){
	alert("validó")
	if (document.getElementById('fieldNickname').value.length == 0){
		alert('Ingrese un nickname');
    	return false;
    }
    else if(document.getElementById('nombrePersona').value.length == 0 ){
		alert('Ingrese su nombre');
    	return false;
	}
    else if(document.getElementById('apellidoPersona').value.length == 0){
		alert('Ingrese su apellido');
    	return false;
	}
    else if(document.getElementById('contrasenaPersona').value.length == 0 ){
		alert('Ingrese una contraseña');
    	return false;
	}
    else if(document.getElementById('confirmacionContrasena').value.length == 0 ){
		alert('Confirme la contraseña');
    	return false;
	}
	else if(document.getElementById('confirmacionContrasena').value != document.getElementById('contrasenaPersona').value){
		alert('La contraseña debe coincidir con la confirmación');
		return false;
	}
    else if(!document.getElementById('nacimientoPersona').value){
		alert('Ingrese su fecha de nacimiento');
    	return false;
	}
    else if(document.getElementById('correoPersona').value.length == 0 ){
		alert('Ingrese su correo');
    	return false;
	}
	else if(!algunoApretado){
		alert('El usuario debe ser Turista o Proveedor');
		return false;
	}
	else if((!botonTurista) && (document.getElementById('descripcionProv').value.length == 0)){
		alert('Al ser Proveedor debe tener descripción');
		return false;
	}
	return true;
}
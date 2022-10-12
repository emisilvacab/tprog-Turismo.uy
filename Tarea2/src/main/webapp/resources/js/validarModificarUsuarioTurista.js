function validarModificarUsuarioTurista(){
    if(document.getElementById('nombrePersonaMUT').value.length == 0 ){
		alert('Ingrese su nombre');
    	return false;
	}
    else if(document.getElementById('apellidoPersonaMUT').value.length == 0){
		alert('Ingrese su apellido');
    	return false;
	}
    else if(document.getElementById('contrasenaPersonaMUT').value.length == 0 ){
		alert('Ingrese una contraseña');
    	return false;
	}
    else if(document.getElementById('confirmacionContrasenaMUT').value.length == 0 ){
		alert('Confirme la contraseña');
    	return false;
	}
	else if(document.getElementById('confirmacionContrasenaMUT').value != document.getElementById('contrasenaPersonaMUT').value){
		alert('La contrasena debe coincidir con la confirmación');
		return false;
	}
    else if(!document.getElementById('nacimientoPersonaMUT').value){
		alert('Ingrese su fecha de nacimiento');
    	return false;
	}
	alert("Usuario modificado con éxito!");
	return true;
}
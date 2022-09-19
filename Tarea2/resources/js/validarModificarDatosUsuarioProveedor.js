function validarModificarUsuarioTurista(){
    if(document.getElementById('nombrePersonaMUP').value.length == 0 ){
		alert('Ingrese su nombre');
    	return false;
	}
    else if(document.getElementById('apellidoPersonaMUP').value.length == 0){
		alert('Ingrese su apellido');
    	return false;
	}
    else if(document.getElementById('contrasenaPersonaMUP').value.length == 0 ){
		alert('Ingrese una contraseña');
    	return false;
	}
    else if(document.getElementById('confirmacionContrasenaMUP').value.length == 0 ){
		alert('Confirme la contraseña');
    	return false;
	}
	else if(document.getElementById('confirmacionContrasenaMUP').value != document.getElementById('contrasenaPersonaMUT').value){
		alert('La contrasena debe coincidir con la confirmación');
		return false;
	}
    else if(!document.getElementById('nacimientoPersonaMUP').value){
		alert('Ingrese su fecha de nacimiento');
    	return false;
	}
	else if(!document.getElementById('descripcionProvMUP').value){
		alert('Ingrese una descripción');
    	return false;
	}
	alert("Usuario modificado con éxito!");
	return true;
}
function validarIniciarSesion(){
	
	if (document.getElementById('nickname').value.length == 0){
		alert('Ingrese nickname');
    	return false;
    }
	else if (document.getElementById('nickname').value != "lachiqui" && document.getElementById('nickname').value != "washington" && document.getElementById('nickname').value != "mirtha.legrand.ok@hotmail.com.ar"&& document.getElementById('nickname').value != "washington@turismorocha.gub.uy" ){
		alert('Usuario invalido');
    	return false;
    }
	else if ((document.getElementById('nickname').value == "lachiqui" || document.getElementById('nickname').value == "mirtha.legrand.ok@hotmail.com.ar") && document.getElementById('password').value != "awdrg543"){
		alert('Contraseña invalida');
    	return false;
    }
	else if ((document.getElementById('nickname').value == "washington" || document.getElementById('nickname').value == "washington@turismorocha.gub.uy") && document.getElementById('password').value != "asdfg654"){
		alert('Contraseña invalida');
    	return false;
    }
	else if ((document.getElementById('nickname').value == "lachiqui" || document.getElementById('nickname').value == "mirtha.legrand.ok@hotmail.com.ar") && document.getElementById('password').value == "awdrg543"){
		alert('Sesión iniciada');
		document.getElementById('formInSes').action = "indexTurista.html";
		return true;
    }
	else if ((document.getElementById('nickname').value == "washington" || document.getElementById('nickname').value == "washington@turismorocha.gub.uy") && document.getElementById('password').value == "asdfg654"){
		alert('Sesión iniciada');
		document.getElementById('formInSes').action = "indexProveedor.html";
    	return true;
    }
}
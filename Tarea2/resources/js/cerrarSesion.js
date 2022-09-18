function cerrarSesion(){
	var confirmoCerrarSesion = confirm("Seguro que desea cerrar sesión?");
	if (confirmoCerrarSesion){
		window.location.href = "index.html";
	}
}
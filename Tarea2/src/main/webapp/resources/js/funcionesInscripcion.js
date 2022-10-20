

function validarInscripcion(){
	if (document.getElementById('form-select-dpto').value.length == 0 && document.getElementById('form-select-cat').value.length == 0){
		alert('Seleccione un departamento o una categoría');
    	return false;
    }
    else if (document.getElementById('form-select-act').value.length == 0){
		alert('Seleccione una actividad');
    	return false;
    }
    else if (document.getElementById('form-select-sal').value.length == 0){
		alert('Seleccione una salida');
    	return false;
    }
    else if (document.getElementById('form-cantTuristas').value.length == 0){
		alert('Ingrese una cantidad de turistas');
    	return false;
    }
	else if (document.getElementById('form-cantTuristas').value < 1){
		alert('Ingrese una cantidad de turistas mayor a 0');
    	return false;
    }
    else if (document.getElementById('checkConPaq').checked && document.getElementById('form-select-paq').value.length == 0){
		alert('Seleccione un paquete');
    	return false;
    }
    
	return true;
	
}

function limpiar(select){
	if(select == "dpto"){
		document.getElementById("form-select-cat").value = "";
		if(document.getElementById("form-select-dpto").options[document.getElementById("form-select-dpto").selectedIndex].text == ""){
			//si selecciono el vacio en dpto borro todos los option de todos los select (menos de categoria y departamento)
	    	document.querySelectorAll('#form-select-act option').forEach(o => o.remove());
	    	document.querySelectorAll('#form-select-sal option').forEach(o => o.remove());
	    	document.getElementById("form-cantTuristas").value = "";
	    	document.querySelectorAll('#form-select-paq option').forEach(o => o.remove());
		}
	}
	if (select == 'cat'){
		document.getElementById("form-select-dpto").value = "";
		if(document.getElementById("form-select-cat").options[document.getElementById("form-select-cat").selectedIndex].text == ""){
			//si selecciono el vacio en cat borro todos los option de todos los select (menos de categoria y departamento)
	    	document.querySelectorAll('#form-select-act option').forEach(o => o.remove());
	    	document.querySelectorAll('#form-select-sal option').forEach(o => o.remove());
	    	document.querySelectorAll('#form-select-paq option').forEach(o => o.remove());
		}
	}
	if (select == "act") {
		document.querySelectorAll('#form-select-sal option').forEach(o => o.remove());
    	document.getElementById("form-cantTuristas").value = "";
    	document.querySelectorAll('#form-select-paq option').forEach(o => o.remove());
	}
	if (select == "sal") {
		document.querySelectorAll('#form-select-paq option').forEach(o => o.remove());
	}
}

function deshabilitarSal(){
	if (document.getElementById("form-cantTuristas").value == "") {
		alert("Ingrese la cantidad de turistas que desea inscribir antes de seleccionar una salida.");
		document.getElementById("form-select-sal").value = "";
		document.getElementById("form-select-sal").setAttribute("disabled",true);
	}
	if (document.getElementById("form-cantTuristas").value < 1){
		alert('Ingrese una cantidad de turistas mayor a 0.');
    	document.getElementById("form-select-sal").value = "";
		document.getElementById("form-select-sal").setAttribute("disabled",true);	
	}
	if (!Number.isInteger(Number(document.getElementById("form-cantTuristas").value))){
		alert('Ingrese un valor entero para la cantidad de turistas.');
    	document.getElementById("form-select-sal").value = "";
		document.getElementById("form-select-sal").setAttribute("disabled",true);	
	}
}

function habilitarSal(){
	document.getElementById("form-select-sal").removeAttribute("disabled");
	document.getElementById("form-select-sal").value = "";
}

function habilitarPaq() {
	document.getElementById("form-select-paq").removeAttribute("disabled")
}

function deshabilitarPaq() {
	document.getElementById("form-select-paq").value = "";
	document.getElementById("form-select-paq").setAttribute("disabled",true);
}

function getURL(cambio) {
	if (cambio == "dpto"){
		var dpto = document.getElementById("form-select-dpto").options[document.getElementById("form-select-dpto").selectedIndex].text;
		return "/Tarea2/inscribir?cambio=dpto&dpto=" + encodeURIComponent(dpto);
	}
	if(cambio == "cat") {
		var cat = document.getElementById("form-select-cat").options[document.getElementById("form-select-cat").selectedIndex].text;
		return "/Tarea2/inscribir?cambio=cat&cat=" + encodeURIComponent(cat);
	}
	if (cambio == "act") {
		var dpto = document.getElementById("form-select-dpto").options[document.getElementById("form-select-dpto").selectedIndex].text;
    	var cat = document.getElementById("form-select-cat").options[document.getElementById("form-select-cat").selectedIndex].text;
    	var act = document.getElementById("form-select-act").options[document.getElementById("form-select-act").selectedIndex].text;
		if(dpto.length != 0){
			return "/Tarea2/inscribir?cambio=act&dpto=" + encodeURIComponent(dpto) + "&act=" + encodeURIComponent(act);
		}
		if(cat.legth != 0){
			return "/Tarea2/inscribir?cambio=act&cat=" + encodeURIComponent(cat) + "&act=" + encodeURIComponent(act); 
		}
	}
	if (cambio == "sal") {
		var dpto = document.getElementById("form-select-dpto").options[document.getElementById("form-select-dpto").selectedIndex].text;
    	var cat = document.getElementById("form-select-cat").options[document.getElementById("form-select-cat").selectedIndex].text;
    	var act = document.getElementById("form-select-act").options[document.getElementById("form-select-act").selectedIndex].text;
		var cant = document.getElementById("form-cantTuristas").value;
		var sal = document.getElementById("form-select-sal").options[document.getElementById("form-select-sal").selectedIndex].text;
		sal = sal.split("-")[0].trim();
		
		if(dpto.length != 0){
			return "/Tarea2/inscribir?cambio=sal&dpto=" + encodeURIComponent(dpto) + "&act=" + encodeURIComponent(act) + "&cant=" + encodeURIComponent(cant) + "&sal=" + encodeURIComponent(sal);
		}
		if(cat.legth != 0){
			return "/Tarea2/inscribir?cambio=sal&cat=" + encodeURIComponent(cat) + "&act=" + encodeURIComponent(act) + "&cant=" + encodeURIComponent(cant) + "&sal=" + encodeURIComponent(sal); 
		}
	}
	return "";
}
























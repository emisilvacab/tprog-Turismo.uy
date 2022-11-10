function validarEnEjecucion(){
	var req = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	req.open("POST", "registrar",true);
	var nickname = "";
	req.onreadystatechange = function(){
		if (req.readyState ==4 && req.status==200){
			console.log("ejecutando js");
			nickname = document.getElementById('fieldNickname').value;
		}
	}
	var data = {nicknameDinamico: nickname};
	console.log(document.getElementById('fieldNickname'));
	console.log(nickname);
	data = "nicknameDinamico=pepe";
	console.log(data);
	req.send(data);

}
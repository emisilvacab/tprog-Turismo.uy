
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    
    
<html>
    
<head>
	<meta charset="utf-8">
	<title>Iniciar Sesion</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="styleIniciarSesion.css">
</head>

<body>
	<div class="background container row d-flex">
		<h1 class="tituloIniciarSesion">Turismo.uy</h1>
		<form class="form-floating" action="log" method="post" onsubmit = "return validarIniciarSesion()">
		
			<input type="text" class="form-control" id="floatingInput" placeholder="nickname / correo">
			
			<input type="password" class="form-control" id="floatingPassword" placeholder="contraseña">
			
			<button type="submit" class="btn btn-outline-primary btn-lg btn-block col">Iniciar Sesión</button>
		</form>
	</div>
</body>
</html>
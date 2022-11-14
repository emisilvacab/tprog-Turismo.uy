
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
    <!DOCTYPE html>
    
    
    
<html>
    
<head>	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<meta charset="utf-8">
	<title>Iniciar Sesion</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="resources/css/styleIniciarSesion.css">
</head>

<body>
	<div class="background container row d-flex">
		<h1 class="tituloIniciarSesion">Turismo.uy</h1>
		
		<%
				EstadoSesion estado = (EstadoSesion) session.getAttribute("estado_sesion");
				if (estado != null && estado == EstadoSesion.LOGIN_INCORRECTO) {
					session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
				
			%>
			<p class="lblError" id="lblError">Los datos ingresados son inválidos. Por favor inténtelo de nuevo.</p>
			<%
				}
			%>
			
		<form class="form-floating" action="log" method="post" onsubmit = "return validarIniciarSesion()">
		
			<input type="text" class="form-control" id="nickname" name="id_logging" placeholder="nickname / correo">
			
			<input type="password" class="form-control" id="password" name="password" placeholder="contraseña">
			
			<button type="submit" class="btn btn-outline-primary btn-lg btn-block col">Iniciar Sesión</button>
		</form>
	</div>
	
	<script src=resources/js/validarIniciarSesion.js type="text/javascript"></script>
</body>
</html>
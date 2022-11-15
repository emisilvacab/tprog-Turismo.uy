<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
    <!DOCTYPE html>
    
    
    
<html>
    
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Iniciar sesion</title>
    <link rel="icon" href="img?id=icono.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  	<link rel="stylesheet" href="resources/css/style.css">
  </head>

<body>

	<div class="cuerpo">
      
	<div class="d-flex justify-content-center" style="margin-top: 20vh;">
		<img class="imagenLogo"src="img?id=logo.png" alt="...">
	</div>
	   <div class="d-flex justify-content-center">
		<form class="form-floating mx-3" action="log" method="post" onsubmit = "return validarIniciarSesion()">
			<div class="form-group mb-1">
				<label for="exampleInputEmail1">Nickname/Email</label>
				<input class="form-control" id="nickname" name="id_logging" aria-describedby="emailHelp" placeholder="Ingrese nickname o email">
			</div>
			<div class="form-group mb-1">
				<label for="exampleInputPassword1">Contraseña</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Ingrese contraseña">
		
		        <%
				EstadoSesion estado = (EstadoSesion) session.getAttribute("estado_sesion");
				if (estado != null && estado == EstadoSesion.LOGIN_INCORRECTO) {
					session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
				%>
				<small class="form-text" style="color: #d63619;">Los datos ingresados son inválidos. Por favor inténtelo de nuevo.</small>
				<%
				} else {
				%>
		        <small id="lblError"  class="form-text text-muted">Nunca compartiremos su informacion personal.</small>
				<%
				}
				%>
	        </div>
	        <button type="submit" class="btn btn-primary mt-2">Ingresar</button>
	      </form>
	    </div>
    	
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src=resources/js/validarIniciarSesion.js type="text/javascript"></script>
</body>
</html>
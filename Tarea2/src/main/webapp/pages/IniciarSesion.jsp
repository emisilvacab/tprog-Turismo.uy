<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>

<!DOCTYPE html>
<html>
<head>
	   <jsp:include page="/template/head.jsp"/>
</head>


<body>
	<jsp:include page="/template/header.jsp"/>
	<jsp:include page="/template/aside-bar.jsp"/>
	
	<%
		EstadoSesion estado = (EstadoSesion) session.getAttribute("estado_sesion");
		if (estado != null && estado == EstadoSesion.LOGIN_INCORRECTO) {
			session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
		
	%>
	<script type="text/javascript">
		alert("Datos de usuario inválido");	
	</script>
	<%
		}
	%>
	
	<section id="section-middle" class="section">
		<h1 class="headerAltaActSal" id="section-header-middle">Inicio de Sesión</h1>		
	
		<form class="formAltaActSal" id="formInSes" onsubmit = "return validarIniciarSesion()" action="log" method="post">
			
			<label class="lbltxt">Nickname o correo del Usuario:</label><br>
			<input class="inputbox" type="text" id="nickname" name="id_logging"><br>	
			
			<label class="lbltxt">Contraseña:</label><br>
			<input class="inputbox" id="password" type="password" name="password"><br>
						
			<input class="btn btn-outline-dark" id="btnAceptar" type="submit" value="Aceptar">
			
		</form>
	</section>
	
	<script src=../resources/js/validarIniciarSesion.js type="text/javascript"></script>

</body>
</html>
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
	
	
	
	
	
	<section id="section-middle" class="section">
	
		<h1 class="headerAltaActSal" id="section-header-middle">Alta de Actividad Turística</h1>		
		<form class="formAltaActSal" onsubmit = "return validarAltaActividad()" action="/Tarea2/AltaActividad" method="post">   
			<label class="lbltxt">Seleccionar Departamento:</label><br>
			<select class="inputbox" id="depAct" name="depAct">
				<option selected></option>
				<option value="Dpto1">Rocha</option>
			</select><br>	
			
			<label class="lbltxt">Nombre de Actividad:</label><br>
			<input class="inputbox" type="text" id="nombreAct" name="nombreAct"><br>	
			
			<label class="lbltxt">Descripción:</label><br>
			<textarea class="inputbox" rows = "5" cols = "38" id="descripcionAct" name = "descripcionAct"></textarea><br>			
			
			<label class="lbltxt">Duración (horas):</label><br>
			<input class="inputbox" id="durAct" type="number" name="durAct"><br>
			
			
			   <label class="lbltxt">Costo (pesos uruguayos):</label><br>
			<input class="inputbox" type="number" id="costoAct" name="costoAct"><br>
			
			<label class="lbltxt">Ciudad:</label><br>
			<input class="inputbox" type="text" id="ciudadAct" name="ciudadAct"><br>		
			
			<label class="lbltxt">Imagen:</label><br>
			<input class="inputbox" type="file" id="imgAct" accept = "image/*" name="imgAct"><br>		
			
			
			<label class="lbltxt">Seleccionar Categoría(s):</label><br>
			<select class="inputbox" id="catAct" name="catAct">
				<option selected></option>
				<option value="Cat1">Gastronomía</option>
			</select><br>		
			
			<input class="btn btn-outline-dark" id="btnAceptar" type="submit" value="Aceptar">
		</form>
		
	</section>
	
	<script src=resources/js/validarAltaActividad.js type="text/javascript"></script>
</body>
</html>
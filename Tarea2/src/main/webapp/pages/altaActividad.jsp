<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="java.util.Set"%>
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
		<form class="formAltaActSal" onsubmit = "return validarAltaActividad()" action="altaActividad" method="post">   
			<label class="lbltxt">Seleccionar Departamento:</label><br>
			<select class="inputbox" id="depAct" name="depAct">
				<option selected></option>
		    <%
				Set<String> dptos = (Set<String>) request.getAttribute("dptos");
		    	if(dptos != null)
		    		for(String dpto: dptos){
			%>
						<option value=<%=dpto%>><%=dpto%></option>
			<%		}
			%>
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
			<select class="multiple" multiple="multiple" id="catsAct" name="catsAct">
				<option selected></option>
				<option value="test1">test1</option>
				<option value="test2">test2</option>
			<% 
				Set<String> cats = (Set<String>) request.getAttribute("cats");
		    	if(cats != null)
					for(String cat: cats){
			%>
						<option value=<%=cat%>><%=cat%></option>
			<%		}
		    %>
			</select><br>
			
			<%
				String estado = (String) request.getAttribute("error");
				if (estado != null) {
					request.setAttribute("error", null);
				
			%>
					<p style="color:#FF0000">La actividad ingresada ya existe. Modifique su nombre</p>
			<%
				}
			%>		
			
			<input class="btn btn-outline-dark" id="btnAceptar" type="submit" value="Aceptar">
		</form>

		
	</section>
	
	<script src=../resources/js/validarAltaActividad.js type="text/javascript"></script>
</body>
</html>
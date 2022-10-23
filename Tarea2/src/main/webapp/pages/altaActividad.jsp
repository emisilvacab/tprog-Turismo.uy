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
			
		<form class="formAltaActSal" onsubmit = "return validarAltaActividad()" action="altaActividad" method="post" enctype="multipart/form-data">   
			
			<label class="lbltxt">Seleccionar Departamento*</label><br>
			<select class="inputbox" id="depAct" name="depAct">
				<%
			  		String dptoSelected = (String) request.getAttribute("depAct");
			  		if (dptoSelected != null) {
			  	%>
			  		<option></option>
			  		<option selected><%=dptoSelected%></option>
			  	<%
			  		} else {
			  	%>
			  			<option selected></option>
			  	<% 
		  			} 
					Set<String> dptosInsc = (Set<String>) request.getAttribute("dptos");
		    		if(dptosInsc != null)
					for(String dpto: dptosInsc){
						if(dptoSelected == null || !dpto.equals(dptoSelected)) {
			  	%>
			  				<option><%=dpto%></option>
			  	<%
			  			} 
					}
			  	%>
			</select><br>
			
			<label class="lbltxt">Nombre de Actividad*</label><br>
			<%
				String error = (String) request.getAttribute("error");
				if (error != null) {
					request.setAttribute("error", null);
				
			%>
					<p style="color:#FF0000">Ya existe una actividad con ese nombre.</p>
			<%
				}
			%>		
			<input class="inputbox" type="text" id="nombreAct" name="nombreAct"  <%if (request.getAttribute("nombreAct") != null){ String nombreAct = (String) request.getAttribute("nombreAct"); %> value="<%=nombreAct%>" <%}%>><br>	
			
			<label class="lbltxt">Descripción*</label><br>
			<textarea class="inputbox" rows = "5" cols = "38" id="descripcionAct" name = "descripcionAct"><%if (request.getAttribute("descripcionAct") != null) { String descripcionAct = (String) request.getAttribute("descripcionAct"); %><%=descripcionAct.trim()%><%}%></textarea><br>			
			
			<label class="lbltxt">Duración (horas)*</label><br>
			<input class="inputbox" id="durAct" type="number" name="durAct" <%if (request.getAttribute("durAct") != null){ String durAct = (String) request.getAttribute("durAct"); %> value="<%=durAct%>" <%}%>><br>
			
			
		   	<label class="lbltxt">Costo (pesos uruguayos)*</label><br>
			<input class="inputbox" type="number" id="costoAct" name="costoAct" <%if (request.getAttribute("costoAct") != null){ String costoAct = (String) request.getAttribute("costoAct"); %> value="<%=costoAct%>" <%}%>><br>
			
			<label class="lbltxt">Ciudad*</label><br>
			<input class="inputbox" type="text" id="ciudadAct" name="ciudadAct" <%if (request.getAttribute("ciudadAct") != null){ String ciudadAct = (String) request.getAttribute("ciudadAct"); %> value="<%=ciudadAct%>" <%}%>><br>		
			
			<label class="lbltxt">Imagen</label><br>
			<input class="inputbox" type="file" id="imgAct" accept = "image/*" name="imgAct"><br> 
			
			
			<label class="lbltxt">Seleccionar Categoría(s)*</label><br>
			<select class="multiple" multiple="multiple" id="catsAct" name="catsAct">
				<% 
				Set<String> cats = (Set<String>) request.getAttribute("cats");
				Set<String> catsMarcadas = (Set<String>) request.getAttribute("catsMarcadas");
				%>
				<option <%if (catsMarcadas == null || catsMarcadas.size() == 0){%> selected <%}%>></option>
				<% 
			    	if(cats != null)
						for(String cat: cats){
							 String val = cat.replace(" ","+");
				%>
						<option <%if (catsMarcadas != null && catsMarcadas.contains(cat)) {%> selected <%}%> value=<%=val%>><%=cat%></option>
				<%		
						}
		   		%>
			</select><br>
			
			<input class="btn btn-outline-dark" id="btnAceptar" type="submit" value="Aceptar">
		</form>

	</section>
	
	<script src=resources/js/validarAltaActividad.js type="text/javascript"></script>
</body>
</html>
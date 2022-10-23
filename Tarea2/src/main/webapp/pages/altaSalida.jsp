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
	
		<h1 class="headerAltaActSal" id="section-header-middle">Alta de Salida Turística</h1>		
	
		<form class="formAltaActSal" onsubmit="return validarAltaSalida()" action="altaSalida" method="post" enctype="multipart/form-data">
			   
			<label class="lbltxt">Seleccionar Departamento*</label><br>
			<select class="form-select form-select-sm combobox" name="dpto" onchange="limpiar('dpto'); if (this.value.length > 0) window.location.href = getURL('dpto')" id="form-select-dpto" aria-label=".form-select-sm example">
		  	<%
		  		String dptoSelected = (String) request.getAttribute("dpto");
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
			</select>
			
			<label class="lbltxt">Seleccionar Actividad*</label><br>
			<%
				Set<String> actsSal = (Set<String>) request.getAttribute("actsSal");
		    	if(actsSal != null && actsSal.size() == 0) {
			%>
					<p style="color:#FF0000">El departamento seleccionado no tiene actividades confirmadas.</p>
			<%
				}
		    %>
			<select class="form-select form-select-sm" name="act" onchange="limpiar('act'); if (this.value.length > 0) window.location.href = getURL('act')" id="form-select-act" aria-label=".form-select-sm example">
		  	<%
		  		String actSelected = (String) request.getAttribute("act");
			  	if (actSelected != null) {
		  	%>
			  		<option></option>
				  	<option selected><%=actSelected%></option>
		  	<%
			  	} else {
		  	%>
				  <option selected></option>				 
		  	<% 
			  	}

		    	if(actsSal != null)
					for(String act: actsSal){
						if(actSelected == null || !act.equals(actSelected)) {
		  	%>
			  				<option><%=act%></option>
		  	<%
			  			} 
					}
		  	%>
			</select>
			
			<label class="lbltxt">Nombre de Salida*</label><br>
			<%  //CARTELES DE ERRORES
				String error = (String) request.getAttribute("error");
				if (error != null) {
					request.setAttribute("error", null);
			%>
					<p style="color:#FF0000">Ya existe una salida con ese nombre.</p>
				
			<%	}
			%>
			<input class="inputbox" type="text" <%if (request.getAttribute("nombreSal") != null){ String nombreSal = (String) request.getAttribute("nombreSal"); %> value="<%=nombreSal%>" <%}%> id="nombreSal" name="nombreSal"><br>	
			
			<label class="lbltxt">Fecha*</label><br>
			<input class="inputbox" type="date"<%if (request.getAttribute("fechaSal") != null){ String fechaSal = (String) request.getAttribute("fechaSal"); %> value="<%=fechaSal%>" <%}%> id="fechaSal" name="fechaSal"><br>
			
			<label class="lbltxt">Hora*</label><br>
			<input class="inputbox" type="time" <%if (request.getAttribute("horaSal") != null){ String horaSal = (String) request.getAttribute("horaSal"); %> value="<%=horaSal%>" <%}%> id="horaSal" name="horaSal"><br>
			
			<label class="lbltxt">Lugar*</label><br>
			<input class="inputbox" type="text"  <%if (request.getAttribute("lugarSal") != null){ String lugarSal = (String) request.getAttribute("lugarSal"); %> value="<%=lugarSal%>" <%}%> id="lugarSal" name="lugarSal"><br>
			
			<label class="lbltxt">Cantidad de Turistas*</label><br>
			<input class="inputbox" type="number" <%if (request.getAttribute("cantTurSal") != null){ String cantTurSal = (String) request.getAttribute("cantTurSal"); %> value="<%=cantTurSal%>" <%}%> id="cantTurSal" name="cantTurSal"><br>

			<label class="lbltxt">Imagen</label><br>
			<input class="inputbox" type="file" id="imgSal" accept = "image/*" name="imgSal"><br>	
			
			<input class="btn btn-outline-dark" id="btnAceptar" type="submit" value="Aceptar">
		</form>
		
  	</section>
  
	<script src=resources/js/validarAltaSalida.js type="text/javascript"></script>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@ page import ="java.util.Set"%>
<%@ page import ="logica.datatypes.DTUsuario"%>


<!DOCTYPE html>
<html>
<head>
	   <jsp:include page="/template/head.jsp"/>
</head>


<body>
	<jsp:include page="/template/header.jsp"/>
	<jsp:include page="/template/aside-bar.jsp"/>
	
	<section id="section-middle" class="section">
  
	  	<h1 class="section-header" id="section-header-middle">
			Inscripción a salida
		</h1>
		
		<%  //LA SESION EXPIRO
			DTUsuario usr = (DTUsuario) session.getAttribute("usuario_logueado");
			if (usr == null) {
		%>
		alert("La sesión ha expirado. Inicie sesión nuevamente para inscribirse a una salida.")
		<% //FALTA EL HREF A INDEX SI EXPIRO
			}
		%>
		
		<%  //CARTELES DE ERRORES
			String error = (String) request.getAttribute("error");
			if (error != null && error.equals("existe")) {
		%>
		<p style="color:#FF0000">Usted ya está inscripto a la salida seleccionada. Seleccione otra salida.</p>
		<%
			} else { 
				if (error != null && error.equals("lleno")) {
			
		%>
		<p style="color:#FF0000">La cantidad de gente ingresada supera el límite de la salida. Cambie la cantidad de personas o la salida seleccionada</p>
		<%
				}
			}
		%>
		
		<form class="formInsc" action="inscripcionASalida.html" method="post" onsubmit = "return validarInscripcion()">
				<h5 class="tittle-inst" id="title-inst1">
				  1) Seleccione un departamento o una categoría:
				</h5>
				
				<h6 id="title-form-select-dpto">
				  Departamento:
				</h6>
				
				<select class="form-select form-select-sm combobox" name="dpto" onchange="limpiarCat(); if (this.value.length > 0) window.location.href = getURL('dpto')" id="form-select-dpto" aria-label=".form-select-sm example">
				  <%
				  	String dptoSelected = (String) request.getAttribute("dpto");
				  	if (dptoSelected != null) { //Si me reenviaron los campos que ya habia puesto dejo ese seleccionado
				  %>
				  <option selected><%=dptoSelected%></option>
				  <%
				  	} else { //Sino dejo seleccionado el vacio
				  %>
				  <option selected></option>
				  <% 
				  	} 
					Set<String> dptosInsc = (Set<String>) request.getAttribute("dptos"); //uso los dptos que ya mandaron para el aside-bar
			    	if(dptosInsc != null)
						for(String dpto: dptosInsc){
							if(dptoSelected != null && !dpto.equals(dptoSelected)) { //cargo todos los dptos que me mandaron menos el que ya habia marcado arriba
				  %>
				  <option><%=dpto%></option>
				  <%
				  			} 
						}
				  %>
				  <option>Rocha</option> <%//BORRAAAAAR ESTE Y TODOS LOS HARDCODEADOS %>
				</select>
				
				<h6 id="title-form-select-cat">
				  Categoría:
				</h6>
				
				<select class="form-select form-select-sm" name="cat" onchange="limpiarDpto(); if (this.value.length > 0) window.location.href = getURL('cat')" id="form-select-cat" aria-label=".form-select-sm example">
				  <%
				  	String catSelected = (String) request.getAttribute("cat");
				  	if (catSelected != null) {
				  %>
				  <option selected><%=catSelected%></option>
				  <%
				  	} else {
				  %>
				  <option selected></option>
				  <% 
				  	}
				    Set<String> catsInsc = (Set<String>) request.getAttribute("cats");
			    	if(catsInsc != null)
						for(String cat: catsInsc){
							if(catSelected != null && !cat.equals(catSelected)) {
				  %>
				  <option><%=cat%></option>
				  <%
				  			} 
						}
				  %>
				  <option>Gastronomía</option>
				</select>
				
				<h5 class="tittle-inst" id="title-inst2">
				  2) Seleccione una actividad:
				</h5>
				
				<select class="form-select form-select-sm" name="act" onchange="if (this.value.length > 0) window.location.href = getURL('act')" id="form-select-act" aria-label=".form-select-sm example">
				  <%
				  	String actSelected = (String) request.getAttribute("act");
				  	if (actSelected != null) {
				  %>
				  <option selected><%=actSelected%></option>
				  <%
				  	} else {
				  %>
				  <option selected></option>
				  <% 
				  	}
				  	Set<String> actsInsc = (Set<String>) request.getAttribute("actsInsc");
			    	if(actsInsc != null)
						for(String act: actsInsc){
							if(actSelected != null && !act.equals(actSelected)) {
				  %>
				  <option><%=act%></option>
				  <%
				  			} 
						}
				  %>
				  <option>Degusta</option>
				  <option>Teatro con sabores</option>
				</select>
				
				<h5 class="tittle-inst" >
				  3) Ingrese la cantidad de turistas que desea inscribir:
				</h5>
				<input class="form-control lg" name="cant" <%if (request.getAttribute("cant") != null){%> value='<%=request.getAttribute("cant")%>'<%}%> id="form-cantTuristas" oninput="habilitarSal()" type="number" placeholder="Cantidad de turistas que desea inscribir">
				
				<h5 class="tittle-inst" >
				  4) Seleccione una salida:
				</h5>
				
				<select class="form-select form-select-sm" name="sal" onchange="if (this.value.length > 0) window.location.href = getURL('sal')" onFocus="deshabilitarSal()" id="form-select-sal" aria-label=".form-select-sm example">
				  <%
				  	String salSelected = (String) request.getAttribute("sal");
				  	if (salSelected != null) {
				  %>
				  <option selected><%=salSelected%></option>
				  <%
				  	} else {
				  %>
				  <option selected></option>
				  <% 
				  	}
				    Set<String> salsInsc = (Set<String>) request.getAttribute("salsInsc");
			    	if(salsInsc != null)
						for(String sal: salsInsc){
							if(salSelected != null && !sal.equals(salSelected)) {
				  %>
				  <option><%=sal%></option>
				  <%
				  			} 
						}
				  %>
				  <option>Degusta Setiembre - 3/9/2022</option>
				</select>
			
				
				<h5 class="tittle-inst" >
				  5) Seleccione un tipo de inscripción y un paquete (en caso de que corresponda):
				</h5>
				<div class="eleccion-con-paquete">
					<div class="form-check">
					  <input class="form-check-input" onclick="deshabilitarPaq()" type="radio" name="flexRadioDefault" id="checkSinPaq" checked>
					  <label class="form-check-label" for="flexRadioDefault2">
					    <h6>Inscribirme SIN paquete de actividades</h6>
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" onclick="habilitarPaq()" type="radio" name="flexRadioDefault" id="checkConPaq">
					  <label class="form-check-label" for="flexRadioDefault1">
					    <h6>Inscribirme utilizando paquete de actividades</h6>
					  </label>
					</div>
				</div>
				
				<h6 id="title-form-select-cat">
				  Paquete:
				</h6>
				<select class="form-select form-select-sm" name="paq" onchange="" id="form-select-paq" aria-label=".form-select-sm example" disabled>
				  <%
				  	String paqSelected = (String) request.getAttribute("paq");
				  	if (paqSelected != null) {
				  %>
				  <option selected><%=paqSelected%></option>
				  <%
				  	} else {
				  %>
				  <option selected></option>
				  <% 
				  	}
				    Set<String> paqsInsc = (Set<String>) request.getAttribute("paqsInsc");
			    	if(paqsInsc != null)
						for(String paq: paqsInsc){
							if(paqSelected != null && !paq.equals(paqSelected)) {
				  %>
				  <option><%=paq%></option>
				  <%
				  			} 
						}
				  %>
				  <option>Disfrutar Rocha</option>
				</select>
				
				<button class="btn btn-outline-dark" type="submit" type="button" id="button-continuar">Realizar inscripción</button>
		</form>
	
  </section>
  
  <script src=resources/js/validarInscripcion.js type="text/javascript"></script>
	
</body>
</html>
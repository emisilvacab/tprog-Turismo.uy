<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@ page import ="java.util.Set"%>
<%@ page import ="publicadores.DtUsuario"%>


<!DOCTYPE html>
<html>
<head>
	   <jsp:include page="/template/head.jsp"/>
</head>

<body>
	<jsp:include page="/template/header.jsp"/>
	<jsp:include page="/template/aside-bar.jsp"/>
	
	<%  //LA SESION EXPIRO
		DtUsuario usr = (DtUsuario) session.getAttribute("usuario_logueado");
		if (usr == null) {
	%>
	<script type="text/javascript">
		alert("La sesión ha expirado. Inicie sesión nuevamente para inscribirse a una salida.");
		window.location.href = "Home";
	</script>
	<% 
		}
	%>
	
	<section id="section-middle" class="section">
		
		<%  //CARTEL DE EXITO DE COMPRA
			String exito = (String) request.getAttribute("exito");
			if (exito != null && exito.equals("comprado")) {
		%>
		<div class="alert alert-success alert-dismissible" id="alert-exito" role="alert">
	       <div>Compra realizada con éxito.</div>
	       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	    </div>
		<%
			}
		%>
  
	  	<h1 class="section-header" id="section-header-middle">
			Comprar paquete
		</h1>
		
		<form class="formCompra" action="comprar" method="post" onsubmit = "return validarCompra()">
				
			<%  //CARTEL DE ERROR
				String error = (String) request.getAttribute("error");
				if (error != null && error.equals("existe")) {
			%>
			<h6 style="color:#FF0000">Ya ha comprado este paquete anteriormente. Seleccione otro paquete.</h6>
			<%
				}
			%>
			
			<h5 class="tittle-inst" id="title-inst1">
			  1) Seleccione un paquete:
			</h5>
			
			<%
				Set<String> paqsCompra = (Set<String>) request.getAttribute("paqsCompra");
		    	if(paqsCompra != null && paqsCompra.size() == 0) {
			%>
			<p style="color:#FF0000">No hay paquetes con actividades por el momento.</p>
			<%
				}
		    %>
			<select class="form-select form-select-sm" name="paq" id="form-select-compraPaq" aria-label=".form-select-sm example">
			  <%
			  	String paqSelected = (String) request.getAttribute("paq");
			  	if (paqSelected != null) {
			  %>
			  <option></option>
			  <option selected><%=paqSelected%></option>
			  <%
			  	} else {
			  %>
			  <option selected></option>
			  <% 
			  	}
		    	if(paqsCompra != null)
					for(String paq: paqsCompra){
						if(paqSelected == null || !paq.equals(paqSelected)) {
			  %>
			  <option><%=paq%></option>
			  <%
			  			} 
					}
			  %>
			</select>
			
			<h5 class="tittle-inst" >
			  2) Indique cantidad de turistas para el paquete:
			</h5>
			<input class="form-control lg" name="cant"  <%if (request.getAttribute("cant") != null){ String cant = (String) request.getAttribute("cant");%> value="<%=cant%>" <%}%> id="form-cantTuristasPaq" type="number" placeholder="Cantidad de turistas que desea incluir en el paquete">
			
			<a class="btn btn-outline-dark" href="Home" id="button-continuar">Volver a inicio</a>
			<button class="btn btn-outline-dark" type="submit" type="button" id="button-continuar">Comprar</button>
	
		</form>
		
    </section>
	
	<script src=resources/js/validarCompraPaquete.js type="text/javascript"></script>

</body>

</html>
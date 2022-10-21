<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="logica.datatypes.DTPaquete"%>
<%@page import="java.util.GregorianCalendar"%>

<!DOCTYPE html>
<html>
<head>
	   <jsp:include page="/template/head.jsp"/>
</head>
<body>
	<jsp:include page="/template/header.jsp"/>
	<jsp:include page="/template/aside-bar.jsp"/>
	
	<section id="section-paquete" class="section">
		<h1 class="section-header" id="section-header-middle"> Información del Paquete: </h1>
	
			<%
			DTPaquete paquete = (DTPaquete) request.getAttribute("detallePaquete"); 
			GregorianCalendar fecha = paquete.getFechaAlta();
			%>
			<div class="container" id="container-paquete">
		
				<img id="img-paquete" src="https://sites.google.com/site/areasprotegidasenuruguay/_/rsrc/1411660757953/algunas-de-las-areas-ingresadas-por-el-snap/laguna-de-rocha/Mapa_Rocha_BLOG.jpg?height=280&width=400" class="img-thumbnail" alt="...">
		
				<p class="fw-bold">Nombre:</p>
				<p class="text-break"><%=paquete.getNombre()%></p>
				<p class="fw-bold">Validez:</p>
				<p class="text-break"><%=paquete.getValidez()%></p>
				<p class="fw-bold">Descuento:</p>
				<p class="text-break"><%=paquete.getDescuento()%>%</p>
				<p class="fw-bold">Fecha de alta:</p>
				<p class="text-break"><%=fecha.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=fecha.get(GregorianCalendar.MONTH)%>/<%=fecha.get(GregorianCalendar.YEAR)%> </p>
				<p class="fw-bold">Descripción:</p>
				<p class="text-break"><%=paquete.getDescripcion()%>.</p>
		
		
		<h2>Actividades asociadas:</h2>
		<div class="container" id="container-actividades-paquete">
	
			<div class="d-flex align-items-stretch" id="flex-actividades-paquete">
	
				<div id="paquete-card" class="card" style="width: 18rem;">
  					<img id="card-img-paquete" src="https://s3.amazonaws.com/turismorocha/eventos/2569/cover/degusta-048968300-1659558891.jpg" class="card-img-top" alt="...">
  					<div class="card-body" id="card-body-paquete">
    					<h3 class="card-title">Degusta</h3>
    					<a href="verDatosActividad.html" class="stretched-link"></a>
   		
  					</div>  						
				</div>
					
			</div>
	
					
		</div>
	
	</section>
	
</body>
</html>
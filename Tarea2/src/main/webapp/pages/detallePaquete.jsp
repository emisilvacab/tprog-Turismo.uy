<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="publicadores.DtPaquete"%>
<%@page import="publicadores.DtActividad"%>
<%@page import="publicadores.DtColecciones"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>


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
			DtPaquete paquete = (DtPaquete) request.getAttribute("detallePaquete"); 
			GregorianCalendar fecha = paquete.getFechaAlta().toGregorianCalendar();
			DtColecciones categoriasCol = (DtColecciones) request.getAttribute("detallePaqueteCategorias");
			HashSet<String> categorias = new HashSet<String>(categoriasCol.getSetString());
			%>
			<div class="container" id="container-paquete">
		
				<img id="img-paquete" <%if (paquete.getLinkImagen() != null){%> src="<%=paquete.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultPaquete.png"<%}%> class="img-thumbnail" alt="...">
		
				<p class="fw-bold">Nombre:</p>
				<p class="text-break"><%=paquete.getNombre()%></p>
				<p class="fw-bold">Categorías:</p>
				<%for(String cat : categorias){ %>
					<p class="text-break"><%=cat%></p>
				<%} %>
				<p class="fw-bold">Validez:</p>
				<p class="text-break"><%=paquete.getValidez()%></p>
				<p class="fw-bold">Descuento:</p>
				<p class="text-break"><%=paquete.getDescuento()%>%</p>
				<p class="fw-bold">Fecha de alta:</p>
				<p class="text-break"><%=fecha.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=fecha.get(GregorianCalendar.MONTH)+1%>/<%=fecha.get(GregorianCalendar.YEAR)%> </p>
				<p class="fw-bold">Descripción:</p>
				<p class="text-break"><%=paquete.getDescripcion()%>.</p>
		
		
		<h2>Actividades asociadas:</h2>
		<div class="container" id="container-actividades-paquete">
	
			<div class="d-flex align-items-stretch" id="flex-actividades-paquete">
				<%
				DtColecciones actividadesCol = (DtColecciones) request.getAttribute("detallePaqueteActividades");
				Set<DtActividad> actividades = new HashSet<DtActividad>(actividadesCol.getSetDtActividad());
				for(DtActividad act : actividades){
				%>
				<div id="paquete-card" class="card" style="width: 18rem;">
  					<img id="card-img-paquete" <%if (act.getLinkImagen() != null){%> src="<%=act.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultActividad.png"<%}%> class="card-img-top" alt="...">
  					<div class="card-body" id="card-body-paquete">
    					<h3 class="card-title"><%=act.getNombre()%></h3>
    					<a href="/Tarea2/VerDatosActividad?actSeleccionada=<%=act.getNombre()%>" class="stretched-link"></a>
   		
  					</div>  						
				</div>
				<%
				}
				%>
					
			</div>
	
					
		</div>
	
	</section>
	
</body>
</html>
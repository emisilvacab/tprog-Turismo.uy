<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="logica.datatypes.DTPaquete"%>
<%@page import="model.EstadoSesion"%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.HashSet"%>

<!DOCTYPE html>
<html>
<head>
	   <jsp:include page="/template/head.jsp"/>
</head>


<body>
	<jsp:include page="/template/header.jsp"/>
	<jsp:include page="/template/aside-bar.jsp"/>
	
	<% 
	HashSet<DTPaquete> paquetes = (HashSet<DTPaquete>) request.getAttribute("listarPaquetes");
	%>
	
	<section id="section-paquete-1" class="section">
		<h1 class="section-header" id="section-header-middle"> Seleccione un paquete:</h1>
	
		<div class="container" id="container-cards-paquete">
			
			<div class="d-flex align-items-stretch" id="flex-cards-paquete">
					
				<%for (DTPaquete paq : paquetes){%>
				<div id="paquete-card" class="card" style="width: 18rem;">
 					<img id="card-img-paquete" <%if (paq.getLinkImagen() != null){%> src="<%=paq.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultPaquete.png"<%}%> class="card-img-top" alt="...">
 						<div class="card-body" id="card-body-paquete">
   							<h3 class="card-title"><%=paq.getNombre()%></h3>
   							<p class="card-text"><%=paq.getDescripcion()%></p>	
   			 				<a href="DetallePaquete?detallePaqueteNombre=<%=paq.getNombre()%>" class="stretched-link"></a>
   			
 						</div>  						
				</div>
				<%}%>
			</div>
		</div>
	</section>

</body>
</html>
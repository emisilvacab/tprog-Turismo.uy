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
 					<img id="card-img-paquete" src="https://sites.google.com/site/areasprotegidasenuruguay/_/rsrc/1411660757953/algunas-de-las-areas-ingresadas-por-el-snap/laguna-de-rocha/Mapa_Rocha_BLOG.jpg?height=280&width=400" class="card-img-top" alt="...">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.datatypes.DTActividad"%>
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
		String estado = (String) request.getAttribute("error");
		if (estado != null)
			request.setAttribute("error", null);
	%>
	
	<section id="section-middle" class="section">
	<section id="contenedor-verAct">
		<div class="row">
			<div class="col">
				
				<h1 class="mb-4">
					Actividades turísticas de <%=request.getParameter("nombreTipoActividad")%>
				</h1>
				
				<%
					HashSet<DTActividad> listaActividades = (HashSet<DTActividad>) request.getAttribute("actividades");
					for (DTActividad actividad: listaActividades) {
				%>
				
				<div class="card mb-4 contenedor-actividad" style="max-width: 1000px;">
					<div class="row g-0">
						<div class="col-md-4">
							<a href="/Tarea2/VerDatosActividad?actSeleccionada=<%=actividad.getNombre()%>">
							<img src="https://s3.amazonaws.com/turismorocha/eventos/2569/cover/degusta-048968300-1659558891.jpg" class="img-fluid rounded-start" alt="Actividad">
							</a>
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<h4 class="card-title"><%=actividad.getNombre()%></h4>
								<div class="card-text">
									<p class="max-lines">
									<%=actividad.getDescripcion()%>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>
		</section>
		</section>
	
	
</body>
</html>
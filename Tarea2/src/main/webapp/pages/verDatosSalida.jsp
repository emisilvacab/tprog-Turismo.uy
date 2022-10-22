<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.datatypes.DTSalida"%>
<%@page import="logica.datatypes.DTUsuario"%>
<%@page import="java.util.GregorianCalendar"%>
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
		<%
		
		DTSalida salida = (DTSalida) request.getAttribute("salida");
		if (salida != null) {
		
		// Para tener la hora con el formato hh:mm
		String minS, horaS;
        int min = salida.getHora() % 100;
        int hora = (salida.getHora() - min) / 100;
        if (min < 10)
        	minS = "0" +  min;
        else minS = String.valueOf(min);
        if (hora < 10)
        	horaS = "0" + hora;
        else horaS = String.valueOf(hora);
        String horaConvertida = horaS + ":" + minS;
		
		//Para sacar las partes de la fecha
		GregorianCalendar fechaSalida = salida.getFechaDTSalida();
        Integer diaSalida = fechaSalida.get(fechaSalida.DAY_OF_MONTH);
        Integer mesSalida = fechaSalida.get(fechaSalida.MONTH) + 1;
        Integer anioSalida = fechaSalida.get(fechaSalida.YEAR);
        
        GregorianCalendar fechaAlta = salida.getAlta();
        Integer diaAlta = fechaAlta.get(fechaAlta.DAY_OF_MONTH);
        Integer mesAlta = fechaAlta.get(fechaAlta.MONTH) + 1;
        Integer anioAlta = fechaAlta.get(fechaAlta.YEAR);
        
        String nombreAct = (String) request.getAttribute("nombreActividad");
        String descripcionAct = (String) request.getAttribute("descripcionActividad");
        
		%>
		
   		<div class="row">
   			<div class="card mb-3" style="max-width: 99.5%; min-width: 300px;">
				<div class="card-body" style="font-size: 110%;">
					<h2 class="title"><%=salida.getNombre()%></h2>
				</div>
			</div>
   		</div>
		<div class="row">
		  <div class="col-sm-6" style="min-width: 300px;">
				<div class="card mb-3" style="max-width: 100%;">
					<div class="row">
				      	<div class="col-md-6" style="min-width: 300px;">
				   			<img src="https://s3.amazonaws.com/turismorocha/operadores/1/med/bahia-resto-053888900-1458674720.JPG" class="img-fluid rounded" style="max-width: 100%;" alt="Actividad">
				      	</div>
					     <div class="col-md-6" style="min-width: 300px;">
					     	<div class="card-body pb-0 mb-0" style="max-width: 100%;">
								<ul class="list-group list-group-flush" style="font-size: 135%;">
									<li class="list-group-item"><%=salida.getLugarDTSalida()%></li>
									<li class="list-group-item"><%=diaSalida%>/<%=mesSalida%>/<%=anioSalida%></li>
									<li class="list-group-item">A las <%=horaConvertida%>hs</li>
									<li class="list-group-item">Hasta <%=salida.getMaxTuristas()%> turistas</li>
								</ul>
							</div>
				  		</div>
			     	</div>
			  		<div class="row">
			  			<div class="col-md-12">
			  				<%
			  				DTUsuario usr = (DTUsuario) session.getAttribute("usuario_logueado");
			  				if (usr != null && usr.getClass().getName().equals("logica.datatypes.DTTurista")) {
			  				%>
				  			<div class="card-body mb-0 mt-0 pt-2 pb-1" style="max-width: 100%;">
					  			<div class="row">
				  					<button type="button" class="btn btn-primary btn-block">Inscribirse a salida</button>
				  				</div>
			  				</div>
			  				<%
			  				}
			  				%>
			  				<p class="card-text mb-1" style="margin-left: 4px;"><small class="text-muted">Dada de alta el <%=diaAlta%>/<%=mesAlta%>/<%=anioAlta%></small></p>
		  				</div>
			  		</div>
			     </div>
	    	</div>
		  
		
		  <div class="col-sm-6" style="min-width: 300px;">
			  	<h3 class="mb-3">Asociada a la actividad</h3>
			  	<div class="card mb-3 contenedor-actividad" style="max-width: 98.5%;">
					<div class="row g-0">
						<div class="col-md-4" style="min-width: 200px;">
						<a href="/Tarea2/VerDatosActividad?actSeleccionada=<%=nombreAct%>">
							<img src="https://s3.amazonaws.com/turismorocha/eventos/2569/cover/degusta-048968300-1659558891.jpg" class="img-fluid rounded-start" alt="Actividad">
						</a>
						</div>
						<div class="col-md-8" style="min-width: 300px;">
							<div class="card-body" style="max-width: 100%;">
								<h4 class="card-title"><%=nombreAct%></h4>
								<div class="card-text">
									<%=descripcionAct%>
								</div>
							</div>
						</div>
					</div>
				</div>
		  </div>
		 </div>
		<%
	  	}
	  	%>
		</section>
	</section>
	
</body>
</html>
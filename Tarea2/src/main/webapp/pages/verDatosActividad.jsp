<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.datatypes.DTActividad"%>
<%@page import="logica.datatypes.DTSalida"%>
<%@page import="java.util.HashSet"%>
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
		<div class="row">
		
			<%
				DTActividad actividad = (DTActividad) request.getAttribute("actividad");
				
				if (actividad != null) {
			%>
		
		  <div class="col-sm-6">
				<div class="row">
			      <div class="col-sm-6" style="min-width: 300px;">
			        <div class="card" style="max-width: 100%;">
			   			<img src="https://s3.amazonaws.com/turismorocha/eventos/2569/cover/degusta-048968300-1659558891.jpg" class="img-fluid rounded" alt="Actividad">
			   		</div>
			      </div>
			      <div class="col-sm-6"  style="min-width: 300px;">
			        <div class="card mb-3" style="max-width: 100%;">
						<div class="card-body">
						<ul class="list-group list-group-flush">
							<li class="list-group-item" style="font-size: 150%;"><%=actividad.getCiudad()%></li>
							<li class="list-group-item" style="font-size: 150%;"><%=actividad.getDuracion()%> días</li>
							<li class="list-group-item" style="font-weight: 600; font-size: 150%;">$<%=actividad.getCosto()%></li>
							<li class="list-group-item" style="font-size: 150%;">Proveedor: <em style="color: #2f3131;">washington</em></li>
						</ul>
						<p class="card-text"><small class="text-muted">Dada de alta el <%=(Integer) request.getAttribute("fechaAltaDia")%>/<%=(Integer) request.getAttribute("fechaAltaMes")%>/<%=(Integer) request.getAttribute("fechaAltaAño")%></small></p>
						</div>
			  		</div>
			      </div>
	    		</div>
	    		<div class="row">
	    			<div class="card mb-3" style="max-width: 100%; min-width: 300px;">
						<div class="card-body" style="font-size: 110%;">
							<h2 class="title mb-3 "><%=actividad.getNombre()%></h2>
							<div class="card-text mb-2">
								<%=actividad.getDescripcion()%>
							</div>
							<ul class="list-group list-group-flush">
								<li class="list-group-item" style="font-size: 110%;">Departamento: <%=(String) request.getAttribute("departamento")%></li>
								<li class="list-group-item" style="font-size: 110%;">Categoría: Gastronomía</li>
								<li class="list-group-item" style="font-size: 110%;"><%=actividad.getEstado()%></li>
							</ul>
						</div>
					</div>
	    		</div>
		  </div>
		  	<%
		  	}
		  	%>
		
		  <div class="col-sm-3" style="min-width: 300px;">
		  	<div class="row">
		  	<div class="col-sm-1">
		  	</div>
		  	
		  	<div class="col-sm-11">
		  	<h3>Salidas</h3>
		  	
		  	<%
		  	
		  	HashSet<DTSalida> listaSalidas = (HashSet<DTSalida>) request.getAttribute("salidas");
		  	GregorianCalendar fechaSalida;
			for (DTSalida salida: listaSalidas) {
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
		        fechaSalida = salida.getFechaDTSalida();
		        Integer diaSalida = fechaSalida.get(fechaSalida.DAY_OF_MONTH);
		        Integer mesSalida = fechaSalida.get(fechaSalida.MONTH) + 1;
		        Integer anioSalida = fechaSalida.get(fechaSalida.YEAR);
		  	%>
		  	
		  	<div class="card mb-3 contenedor-verSalPaq" style="max-width: 20rem;">
		  		<a href="verDatosSalida.html">
			  	<img class="card-img-top" src="https://s3.amazonaws.com/turismorocha/operadores/1/med/bahia-resto-053888900-1458674720.JPG" alt="Card image cap">
			  	</a>
			  <div class="card-body">
			    <h5 class="card-title"><%=salida.getNombre()%></h5>
			    <ul class="list-group list-group">
					<li class="list-group-item" style="font-size: 100%;">Fecha: <%=diaSalida%>/<%=mesSalida%>/<%=anioSalida%> a las <%=horaConvertida%>hs</li>
					<li class="list-group-item" style="font-size: 100%;">Lugar: <%=salida.getLugarDTSalida()%></li>
				</ul>
			  </div>
			</div>
			
			<%
			}
			%>
			
		    </div>
		    </div>
		  </div>
		  
		  <div class="col-sm-3" style="min-width: 300px;">
		  	<h3>Paquetes</h3>
		  	<div class="card mb-3 contenedor-verSalPaq" style="max-width: 20rem">
		  		<a href="detalleDePaquete.html">
			  		<img class="card-img-top" src="https://sites.google.com/site/areasprotegidasenuruguay/_/rsrc/1411660757953/algunas-de-las-areas-ingresadas-por-el-snap/laguna-de-rocha/Mapa_Rocha_BLOG.jpg?height=280&width=400" alt="Card image cap">
			  	</a>
			  <div class="card-body">
			    <h5 class="card-title">Disfrutar Rocha</h5>
			    <p class="card-text">Actividades para hacer en familia y disfrutar arte y gastronomía</p>
			  </div>
			</div>
		  </div>
		  
		 </div>
		</section>
	</section>

</body>
</html>
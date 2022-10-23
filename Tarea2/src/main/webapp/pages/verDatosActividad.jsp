<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.datatypes.DTActividad"%>
<%@page import="logica.datatypes.DTSalida"%>
<%@page import="logica.datatypes.DTPaquete"%>
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
			   			<img <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultActividad.png"<%}%> class="img-fluid rounded" alt="Actividad">
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
								<li class="list-group-item" style="font-size: 110%;">
								<%
								HashSet<String> listaCategorias = (HashSet<String>) request.getAttribute("categorias");
								if (!listaCategorias.isEmpty()) {
									String categorias = String.join(", ", listaCategorias);
									
									if (listaCategorias.size() > 1) {
								%>
								Categorías:
								<%
									} else {
								%>
								Categoría:
								<%
									}
								%>
									<%=categorias%>
								<%
								}
								%>
								</li>
								<li class="list-group-item" style="font-size: 110%;"><%=actividad.getEstado().toString()%></li>
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
		  		<a href="/Tarea2/VerDatosSalida?salSeleccionada=<%=salida.getNombre()%>">
			  	<img class="card-img-top"  <%if (salida.getLinkImagen() != null){%>src=<%=salida.getLinkImagen()%><%} else {%>src="resources/img/imgDefaultSalida.png"<%}%> alt="Card image cap">
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
		  	
		  	<%
		  	HashSet<DTPaquete> listaPaquetes = (HashSet<DTPaquete>) request.getAttribute("paquetes");
		  	for (DTPaquete paquete: listaPaquetes) {
		  	%>
		  	
		  	<div class="card mb-3 contenedor-verSalPaq" style="max-width: 20rem">
		  		<a href="/Tarea2/DetallePaquete?detallePaqueteNombre=<%=paquete.getNombre()%>">
			  		<img class="card-img-top" <%if (paquete.getLinkImagen() != null){%> src="<%=paquete.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultPaquete.png"<%}%> alt="Card image cap">
			  	</a>
			  <div class="card-body">
			    <h5 class="card-title"><%=paquete.getNombre()%></h5>
			    <p class="card-text"><%=paquete.getDescripcion()%></p>
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
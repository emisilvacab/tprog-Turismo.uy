<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="publicadores.DtActividad"%>
<%@page import="publicadores.DtSalida"%>
<%@page import="publicadores.DtPaquete"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>

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
				DtActividad actividad = (DtActividad) request.getAttribute("actividad");
				String nombreProveedor = (String) request.getAttribute("proveedor");
				if (actividad != null) {
			%>
		
		  <div class="col-sm-6">
					<div class="row">
					
						<div class="col-sm">
					        <div class="card mx-3 my-3">
								<a href="VerDatosActividad?actSeleccionada=<%=actividad.getNombre()%>">
									<img <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%} else {%>src="img?id=imgDefaultActividad.png"<%}%> class="img-fluid rounded" alt="Actividad">
								</a>
							<div class="card-body">
								<h2 class="title mb-3 "><%=actividad.getNombre()%></h2>
								<div class="card-text mb-2">
									<%=actividad.getDescripcion()%>
								</div>
				          		<ul class="list-group list-group-flush">
									<li class="list-group-item" style="font-size: 150%;"><%=actividad.getDuracion()%> días</li>
									<li class="list-group-item" style="font-weight: 600; font-size: 150%;">$<%=actividad.getCosto()%></li>
									<li class="list-group-item" style="font-size: 150%;">Proveedor: <em style="color: #2f3131;"><%=nombreProveedor%></em></li>
								</ul>
								<p class="card-text"><small class="text-muted">Dada de alta el <%=(Integer) request.getAttribute("fechaAltaDia")%>/<%=(Integer) request.getAttribute("fechaAltaMes")%>/<%=(Integer) request.getAttribute("fechaAltaAño")%></small></p>
							  </div>
							</div>
						</div>
					</div>
	    		<div class="row">
	    			<div class="card mb-3">
						<div class="card-body" style="font-size: 110%;">
							<ul class="list-group list-group-flush">
								<li class="list-group-item" style="font-size: 150%;"><%=actividad.getCiudad()%></li>
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
						<%
							String video = actividad.getLinkVideo();
							if (video.length() > 0) {
						%>
						<div class="card-body pt-0" style="height:46vh;">
						<h5 class="card-title">Video de presentacion:</h5>
						<%
							String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|(?:be-nocookie|be)\\.com\\/(?:watch|[\\w]+\\?(?:feature=[\\w]+.[\\w]+\\&)?v=|v\\/|e\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
							Pattern patron = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
							Matcher matcher = patron.matcher(video);
							if(matcher.find()){
								String idVideo = matcher.group(1);
								video = "https://www.youtube.com/embed/" + idVideo;
							}
						%>
							<iframe class="mb-1" style="min-width:100%; min-height:40vh;" src="<%=video%>" frameborder="0" allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture;" allowfullscreen></iframe>
						</div>
						<%
					  		}
					  	%>
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
		  	
		  	HashSet<DtSalida> listaSalidas = (HashSet<DtSalida>) request.getAttribute("salidas");
		  	GregorianCalendar fechaSalida;
			for (DtSalida salida: listaSalidas) {
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
		        fechaSalida = salida.getFechaDTSalida().toGregorianCalendar();
		        Integer diaSalida = fechaSalida.get(fechaSalida.DAY_OF_MONTH);
		        Integer mesSalida = fechaSalida.get(fechaSalida.MONTH) + 1;
		        Integer anioSalida = fechaSalida.get(fechaSalida.YEAR);
		  	%>
		  	
		  	<div class="card mb-3 contenedor-verSalPaq" style="max-width: 20rem;">
		  		<a href="VerDatosSalida?salSeleccionada=<%=salida.getNombre()%>">
			  	<img class="card-img-top"  <%if (salida.getLinkImagen() != null){%>src="<%=salida.getLinkImagen()%>"<%} else {%>src="img?id=imgDefaultSalida.png"<%}%> alt="Card image cap">
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
		  	HashSet<DtPaquete> listaPaquetes = (HashSet<DtPaquete>) request.getAttribute("paquetes");
		  	for (DtPaquete paquete: listaPaquetes) {
		  	%>
		  	
		  	<div class="card mb-3 contenedor-verSalPaq" style="max-width: 20rem">
		  		<a href="DetallePaquete?detallePaqueteNombre=<%=paquete.getNombre()%>">
			  		<img class="card-img-top" <%if (paquete.getLinkImagen() != null){%> src="<%=paquete.getLinkImagen()%>" <%} else {%>src="img?id=imgDefaultPaquete.png"<%}%> alt="Card image cap">
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
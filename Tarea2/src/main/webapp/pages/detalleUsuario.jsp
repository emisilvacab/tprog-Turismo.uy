<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.datatypes.DTUsuario"%>
<%@page import="logica.datatypes.DTProveedor"%>
<%@page import="logica.datatypes.DTTurista"%>
<%@page import="logica.datatypes.DTActividad"%>
<%@page import="logica.datatypes.DTInscripcion"%>
<%@page import="logica.datatypes.DTCompra"%>
<%@page import="logica.datatypes.DTSalida"%>
<%@page import="logica.Estado"%>

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
	DTUsuario usr = (DTUsuario) request.getAttribute("usuarioDetalle");
	String tipo = (String) request.getAttribute("usuarioDetalleTipo");
	HashSet<DTInscripcion> inscripciones = (HashSet<DTInscripcion>) request.getAttribute("usuarioDetalleInscripciones");
	HashSet<DTCompra> compras = (HashSet<DTCompra>) request.getAttribute("usuarioDetalleCompras");
	
	String nick = null;
	DTUsuario logged = (DTUsuario) session.getAttribute("usuario_logueado");
	if(logged != null){
		nick = logged.getNickname(); 
	}

	%>
	<section id="section-usario-detalle" class="section">
	<h1 class="section-header" id="section-header-middle"> Información del Usuario: </h1>
	<div class="container" id="container-detalle-turista">
	
		<div class="container" id="informacion-usuario">
		
			<div class="card mb-3" style="max-width: 640px;">
  				<div class="row g-0">
    				<div class="col-md-4">
      					<img src="https://pbs.twimg.com/media/EOHAP9zWoAsnkiM?format=jpg&name=small" class="img-fluid rounded-start">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
        					<h5 class="card-title"><%=usr.getNombre()%></h5>
        					
        					<p class="card-text"><%=usr.getNickname()%> / <p1 class="text-muted"><%=usr.getCorreo()%></p1></p>
        					<% GregorianCalendar nacimiento = usr.getNacimiento(); %>
        					<p class="card-text">Fecha de nacimiento: <p1 class="text-muted"><%=nacimiento.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=nacimiento.get(GregorianCalendar.MONTH)%>/<%=nacimiento.get(GregorianCalendar.YEAR)%> </p1></p>
      					</div>
    				</div>
  				</div>
			</div>
			
			<ul class="nav nav-tabs" id="myTab" role="tablist" style="max-width:950px;">
  				<li class="nav-item" role="presentation">
    				<button class="nav-link active" id="perfil-tab" data-bs-toggle="tab" data-bs-target="#perfil-tab-pane" type="button" role="tab" aria-controls="perfil-tab-pane" aria-selected="true" style="color: #2f3131;" >Perfil</button>
  				</li>
  				
  				<%if(tipo == "turista"){ %>
  				
  				<li class="nav-item" role="presentation">
    				<button class="nav-link" id="salidas-tab" data-bs-toggle="tab" data-bs-target="#salidas-tab-pane" type="button" role="tab" aria-controls="salidas-tab-pane" aria-selected="false" style="color: #2f3131;">Salidas</button>
  				</li>
  				<% 		if(usr.getNickname().equals(nick)){%>
  				<li class="nav-item" role="presentation">
    				<button class="nav-link" id="paquetes-tab" data-bs-toggle="tab" data-bs-target="#paquetes-tab-pane" type="button" role="tab" aria-controls="paquetes-tab-pane" aria-selected="false" style="color: #2f3131;">Paquetes comprados</button>
  				</li>
  				<%		} %>
  				<% }else if(tipo == "proveedor"){ %>
  				
  				<li class="nav-item" role="presentation">
    				<button class="nav-link" id="actividades-tab" data-bs-toggle="tab" data-bs-target="#actividades-tab-pane" type="button" role="tab" aria-controls="actividades-tab-pane" aria-selected="false" style="color: #2f3131;">Actividades</button>
  				</li>
  				
  				<li class="nav-item" role="presentation">
    				<button class="nav-link" id="salidass-tab" data-bs-toggle="tab" data-bs-target="#salidas-tab-pane" type="button" role="tab" aria-controls="salidas-tab-pane" aria-selected="false" style="color: #2f3131;">Salidas</button>
  				</li>
  				
  				<%}%>
  				
			</ul>
			<div class="tab-content" id="myTabContent">
  				<div class="tab-pane fade show active" id="perfil-tab-pane" role="tabpanel" aria-labelledby="perfil-tab" tabindex="0">
         			<p1 class="text-muted">Nickname: <%=usr.getNickname()%></p1><br>
         			<p3 class="text">Nombre: <%=usr.getNombre()%></p3><br>
         			<p3 class="text">Apellido: <%=usr.getApellido()%></p3><br>
         			<p1 class="text-muted">E-Mail: <%=usr.getCorreo()%></p1><br>
         			<p3 class="text">Fecha de nacimiento: <%=nacimiento.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=nacimiento.get(GregorianCalendar.MONTH)%>/<%=nacimiento.get(GregorianCalendar.YEAR)%></p3>
         			
  				
  				</div>
  				
  				<%if(tipo == "turista"){ %>
  				
  				<div class="tab-pane fade" id="salidas-tab-pane" role="tabpanel" aria-labelledby="salidas-tab" tabindex="0">

					<div class="container" id="container-cards-salidas-usuario" style="max-width:950px;">
	
						<div class="d-flex align-items-stretch" id="flex-cards-paquete" style="max-width:950px;">
							<%
							
							for(DTInscripcion ins : inscripciones){
								GregorianCalendar insFecha = ins.getFecha();
							%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img id="card-img-paquete" src="https://city.woow.com.uy/media/catalog/product/cache/dcf64a24127a43d9ce9fe76e3e5f8061/n/u/nueva2_3_1.jpg" class="card-img-top" alt="...">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=ins.getSalida()%></h3>
    								<%if(usr.getNickname().equals(nick)){ %>
     									<p class="card-text"><strong>Cantidad de turistas: </strong><%=ins.getCantTuristas()%></p>	
     									<p class="card-text"><strong>Costo:</strong> $<%=ins.getCosto()%></p>
     									<p3 class="card-text"><strong>Fecha de inscripción: </strong><%=insFecha.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=insFecha.get(GregorianCalendar.MONTH)%>/<%=insFecha.get(GregorianCalendar.YEAR)%></p3>
     								<%} %>
 									<a href="/Tarea2/VerDatosSalida?salSeleccionada=<%=ins.getSalida()%>" class="stretched-link"></a>
    			
  								</div>  						
							</div>
							<%} %>
						</div>
					</div>
  				
  				
  				</div>
  				
  				<div class="tab-pane fade" id="paquetes-tab-pane" role="tabpanel" aria-labelledby="paquetes-tab" tabindex="0">
  				
  					<div class="container" id="container-cards-paquete-usuario" style="max-width:950px;">
	
						<div class="d-flex align-items-stretch" id="flex-cards-paquete" style="max-width:950px;">
							<%
							if(usr.getNickname().equals(nick)){
								for(DTCompra comp : compras){
									GregorianCalendar fechaCompra = comp.getFecha();
							%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img id="card-img-paquete" src="https://sites.google.com/site/areasprotegidasenuruguay/_/rsrc/1411660757953/algunas-de-las-areas-ingresadas-por-el-snap/laguna-de-rocha/Mapa_Rocha_BLOG.jpg?height=280&width=400" class="card-img-top" alt="...">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=comp.getPaquete()%></h3>
     								<p class="card-text"><strong>Fecha de Compra:</strong> <%=fechaCompra.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=fechaCompra.get(GregorianCalendar.MONTH)%>/<%=fechaCompra.get(GregorianCalendar.YEAR)%></p>	
      								<p class="card-text"><strong>Cantidad de turistas: </strong><%=comp.getCantTuristas()%></p>
      								<p class="card-text"><strong>Costo:</strong> $<%=comp.getCosto()%></p>	
      								
    			 					<a href="DetallePaquete?detallePaqueteNombre=<%=comp.getPaquete()%>" class="stretched-link"></a>
    			
  								</div>  								
							</div>
							<%	}
							} 
							%>
						</div>
  				
  					</div>
  				</div>
  				
  				<% }else if(tipo == "proveedor"){ %>
  					
  				<div class="tab-pane fade" id="actividades-tab-pane" role="tabpanel" aria-labelledby="actividades-tab" tabindex="0">

					<div class="container" id="container-cards-actividades-usuario" style="max-width:950px;">
	
						<div class="d-flex align-items-stretch" id="flex-cards-paquete" style="max-width:950px;">
						<% 
  						HashSet<DTActividad> actividades = (HashSet<DTActividad>) request.getAttribute("usuarioDetalleActividades");
						
						if(!usr.getNickname().equals(nick)){
							for(DTActividad actividad : actividades){
								if(actividad.getEstado().equals(Estado.CONFIRMADA)){
  									GregorianCalendar alta = actividad.getAlta();
  						%>
  						
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img id="card-img-paquete" <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%}%> class="card-img-top" alt="...">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=actividad.getNombre() %></h3>
    								<p class="card-text"><strong>Duración: </strong><%=actividad.getDuracion()%> días</p>
    								<p class="card-text"><strong>Costo: $</strong><%=actividad.getCosto()%></p>
    								<p class="card-text"><strong>Ciudad:</strong><%=actividad.getCiudad()%></p>	
    								<!--   class="card-text"><strong>Departamento:</strong> Rocha</p>-->
    								<p class="card-text"><strong>Fecha de alta: </strong><%=alta.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=alta.get(GregorianCalendar.MONTH)%>/<%=alta.get(GregorianCalendar.YEAR)%></p>	
    			
    								<a href="/Tarea2/VerDatosActividad?actSeleccionada=<%=actividad.getNombre()%>" class="stretched-link"></a>
   		
  								</div>  						
							</div>
				<%				
								}
  							}
						}else{
							for(DTActividad actividad : actividades){
								GregorianCalendar alta = actividad.getAlta();
								Estado estadoAct = actividad.getEstado();
								String estadoStr;
																
								if(estadoAct.equals(Estado.CONFIRMADA)){
									estadoStr = "Confirmada";
								}else if(estadoAct.equals(Estado.AGREGADA)){
									estadoStr = "Agregada";
								}else{
									estadoStr = "Rechazada";
								}
				%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img id="card-img-paquete" <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%}%> class="card-img-top" alt="...">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=actividad.getNombre() %></h3>
    								<p class="card-text"><strong>Duración: </strong><%=actividad.getDuracion()%></p>
    								<p class="card-text"><strong>Costo: $</strong><%=actividad.getCosto()%></p>
    								<p class="card-text"><strong>Ciudad:</strong><%=actividad.getCiudad()%></p>	
    								<!--   class="card-text"><strong>Departamento:</strong> Rocha</p>-->
    								<p class="card-text"><strong>Fecha de alta: </strong><%=alta.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=alta.get(GregorianCalendar.MONTH)%>/<%=alta.get(GregorianCalendar.YEAR)%></p>	
    								<p class="card-text"><strong>Estado: </strong><%=estadoStr%></p>	
    								
    								<a href="/Tarea2/VerDatosActividad?actSeleccionada=<%=actividad.getNombre()%>" class="stretched-link"></a>
   		
  								</div>  						
							</div>
				<% 
							}
						}
  				
  				%>
  					
						</div>
					</div>
  				</div>
  				
  				<div class="tab-pane fade" id="salidas-tab-pane" role="tabpanel" aria-labelledby="salidas-tab" tabindex="0">

					<div class="container" id="container-cards-actividades-usuario" style="max-width:950px;">
	
						<div class="d-flex align-items-stretch" id="flex-cards-paquete" style="max-width:950px;">
						<% 
						
						if(usr.getNickname().equals(nick)){
							HashSet<DTSalida> salidas = (HashSet<DTSalida>) request.getAttribute("usuarioDetalleSalidas");
							for(DTSalida sal : salidas){							
  						%>
  						
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<!-- <img id="card-img-paquete" ruta ruta ruta class="card-img-top" alt="..."> -->
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=sal.getNombre()%></h3>    			
    								<a href="/Tarea2/VerDatosSalida?salSeleccionada=<%=sal.getNombre()%>" class="stretched-link"></a>
   		
  								</div>  						
							</div>
					<%				
							}
						}else{
							HashSet<DTSalida> salidasConf = (HashSet<DTSalida>) request.getAttribute("usuarioDetalleSalidasConfirmadas");
							for(DTSalida sal : salidasConf){
							%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<!-- <img id="card-img-paquete" ruta ruta ruta class="card-img-top" alt="..."> -->
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=sal.getNombre()%></h3>    			
    								<a href="/Tarea2/VerDatosSalida?salSeleccionada=<%=sal.getNombre()%>" class="stretched-link"></a>
   		
  								</div>  						
							</div>
								
								
							<%
							}
						}
						
  				}
  					%>
  					
						</div>
					</div>
  				</div> 
  				  						
			</div>								
		</div>			
	</div>
</section>	
</body>
</html>
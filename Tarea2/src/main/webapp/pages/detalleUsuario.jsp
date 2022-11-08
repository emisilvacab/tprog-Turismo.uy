<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>

<%@page import = "publicadores.DtUsuario" %>
<%@page import = "publicadores.DtCompra" %>
<%@page import = "publicadores.DtInscripcion" %>
<%@page import = "publicadores.DtProveedor" %>
<%@page import = "publicadores.DtTurista" %>
<%@page import = "publicadores.DtColecciones" %>
<%@page import = "publicadores.DtActividad" %>
<%@page import = "publicadores.DtSalida" %>
<%@page import = "publicadores.Estado" %>

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

	<%
	DtUsuario usr = (DtUsuario) request.getAttribute("usuarioDetalle");
	String tipo = (String) request.getAttribute("usuarioDetalleTipo");
	//HashSet<DtInscripcion> inscripciones = (HashSet<DtInscripcion>) request.getAttribute("usuarioDetalleInscripciones");
	//HashSet<DtCompra> compras = (HashSet<DtCompra>) request.getAttribute("usuarioDetalleCompras");
	
	String nick = null;
	DtUsuario logged = (DtUsuario) session.getAttribute("usuario_logueado");
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
      					<img <%if (usr.getLinkImagen() != null){%> src="<%=usr.getLinkImagen()%>" <%} else {%>src="https://cdn-icons-png.flaticon.com/512/1077/1077063.png"<%}%> class="img-fluid rounded-start">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
        					<h5 class="card-title"><%=usr.getNombre()%></h5>

        					<p class="card-text"><%=usr.getNickname()%> / <p1 class="text-muted"><%=usr.getCorreo()%></p1></p>
        					<% GregorianCalendar nacimiento = usr.getNacimiento().toGregorianCalendar(); %>
        					<p class="card-text">Fecha de nacimiento: <p1 class="text-muted"><%=nacimiento.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=nacimiento.get(GregorianCalendar.MONTH) +1%>/<%=nacimiento.get(GregorianCalendar.YEAR)%> </p1></p>

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
    				<button class="nav-link" id="salidas-tab" data-bs-toggle="tab" data-bs-target="#salidas-tab-pane" type="button" role="tab" aria-controls="salidas-tab-pane" aria-selected="false" style="color: #2f3131;">Inscripciones a Salidas</button>
  				</li>
  				<% 		if(usr.getNickname().equals(nick)){%>
  				<li class="nav-item" role="presentation">
    				<button class="nav-link" id="paquetes-tab" data-bs-toggle="tab" data-bs-target="#paquetes-tab-pane" type="button" role="tab" aria-controls="paquetes-tab-pane" aria-selected="false" style="color: #2f3131;">Paquetes comprados</button>
  				</li>
  				<%		} %>
  				<% }else if(tipo == "proveedor"){ %>
  				
  				<li class="nav-item" role="presentation">
    				<button class="nav-link" id="actividades-tab" data-bs-toggle="tab" data-bs-target="#actividades-tab-pane" type="button" role="tab" aria-controls="actividades-tab-pane" aria-selected="false" style="color: #2f3131;">Actividades ofrecidas</button>
  				</li>
  				
  				<li class="nav-item" role="presentation">
    				<button class="nav-link" id="salidass-tab" data-bs-toggle="tab" data-bs-target="#salidas-tab-pane" type="button" role="tab" aria-controls="salidas-tab-pane" aria-selected="false" style="color: #2f3131;">Salidas ofrecidas</button>
  				</li>
  				
  				<%}%>
  				
			</ul>
			<div class="tab-content" id="myTabContent">
  				<div class="tab-pane fade show active" id="perfil-tab-pane" role="tabpanel" aria-labelledby="perfil-tab" tabindex="0">
         			<p1 class="text-muted">Nickname: <%=usr.getNickname()%></p1><br>
         			<p3 class="text">Nombre: <%=usr.getNombre()%></p3><br>
         			<p3 class="text">Apellido: <%=usr.getApellido()%></p3><br>
         			<p1 class="text-muted">E-Mail: <%=usr.getCorreo()%></p1><br>
         			<p3 class="text">Fecha de nacimiento: <%=nacimiento.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=nacimiento.get(GregorianCalendar.MONTH) +1%>/<%=nacimiento.get(GregorianCalendar.YEAR)%></p3><br>
         			
         			<%if(tipo == "turista"){
         				DtTurista tur = (DtTurista) usr;
         			%>
         				<p1 class="text">Nacionalidad: <%=tur.getNacionalidad()%></p1><br>
         			
         			<%}else if(tipo == "proveedor"){ 
         				DtProveedor prov = (DtProveedor) usr;

         			%>
  					<p1 class="text">Link: <a href="<%=prov.getLink()%>" target="_blank"><%=prov.getLink()%></a></p1><br>
  					<p3 class="text">Descripción: <%=prov.getDescripcion()%></p3><br>
  					<%} %>
  				</div>
  				
  				<%if(tipo == "turista"){ %>
  				
  				<div class="tab-pane fade" id="salidas-tab-pane" role="tabpanel" aria-labelledby="salidas-tab" tabindex="0">

					<div class="container" id="container-cards-salidas-usuario" style="max-width:950px;">
	
						<div class="d-flex align-items-stretch" id="flex-cards-paquete" style="max-width:950px;">
							<%
							DtColecciones insCol = (DtColecciones) request.getAttribute("usuarioDetalleInscripciones");
							Set<DtInscripcion> inscripciones = new HashSet<DtInscripcion>(insCol.getSetDtInscripcion());
							for(DtInscripcion ins : inscripciones){
								GregorianCalendar insFecha = ins.getFecha().toGregorianCalendar();
							%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=ins.getSalida()%></h3>
    								<%if(usr.getNickname().equals(nick)){ %>
     									<p class="card-text"><strong>Cantidad de turistas: </strong><%=ins.getCantTuristas()%></p>	
     									<p class="card-text"><strong>Costo:</strong> $<%=ins.getCosto()%></p>
     									<p3 class="card-text"><strong>Fecha de inscripción: </strong><%=insFecha.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=insFecha.get(GregorianCalendar.MONTH)+1%>/<%=insFecha.get(GregorianCalendar.YEAR)%></p3>
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
							DtColecciones compraCol = (DtColecciones) request.getAttribute("usuarioDetalleCompras");
							Set<DtCompra> compras = new HashSet<DtCompra>(compraCol.getSetDtCompra());
							if(usr.getNickname().equals(nick)){
								for(DtCompra comp : compras){
									GregorianCalendar fechaCompra = comp.getFecha().toGregorianCalendar();
							%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=comp.getPaquete()%></h3>
     								<p class="card-text"><strong>Fecha de Compra:</strong> <%=fechaCompra.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=fechaCompra.get(GregorianCalendar.MONTH)+1%>/<%=fechaCompra.get(GregorianCalendar.YEAR)%></p>	
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
  						//HashSet<DTActividad> actividades = (HashSet<DTActividad>) request.getAttribute("usuarioDetalleActividades");
						Set<DtActividad> actividades = (HashSet<DtActividad>) request.getAttribute("usuarioDetalleActividades");
						if(!usr.getNickname().equals(nick)){
							for(DtActividad actividad : actividades){
								if(actividad.getEstado().equals(Estado.CONFIRMADA)){
  									GregorianCalendar alta = actividad.getAlta().toGregorianCalendar();
  						%>
  						
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img id="card-img-paquete" <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultActividad.png"<%}%> class="card-img-top" alt="...">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=actividad.getNombre() %></h3>
    								<p class="card-text"><strong>Duración: </strong><%=actividad.getDuracion()%> días</p>
    								<p class="card-text"><strong>Costo: $</strong><%=actividad.getCosto()%></p>
    								<p class="card-text"><strong>Ciudad:</strong><%=actividad.getCiudad()%></p>	
    								<!--   class="card-text"><strong>Departamento:</strong> Rocha</p>-->
    								<p class="card-text"><strong>Fecha de alta: </strong><%=alta.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=alta.get(GregorianCalendar.MONTH)+1%>/<%=alta.get(GregorianCalendar.YEAR)%></p>	
    			
    								<a href="/Tarea2/VerDatosActividad?actSeleccionada=<%=actividad.getNombre()%>" class="stretched-link"></a>
   		
  								</div>  						
							</div>
				<%				
								}
  							}
						}else{
							for(DtActividad actividad : actividades){
								GregorianCalendar alta = actividad.getAlta().toGregorianCalendar();
								Estado estadoAct = actividad.getEstado();
								String estadoStr = "";
																
								if(estadoAct.equals(Estado.CONFIRMADA)){
									estadoStr = "Confirmada";
								}else if(estadoAct.equals(Estado.AGREGADA)){
									estadoStr = "Agregada";
								}else if(estadoAct.equals(Estado.RECHAZADA)){
									estadoStr = "Rechazada";
								}//else if(estadoAct.equals(Estado.FINALIZADA)){
								//	estadoStr = "Finalizada";
								//}
				%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img id="card-img-paquete" <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultActividad.png"<%}%> class="card-img-top" alt="...">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=actividad.getNombre() %></h3>
    								<p class="card-text"><strong>Duración: </strong><%=actividad.getDuracion()%></p>
    								<p class="card-text"><strong>Costo: $</strong><%=actividad.getCosto()%></p>
    								<p class="card-text"><strong>Ciudad:</strong><%=actividad.getCiudad()%></p>	
    								<!--   class="card-text"><strong>Departamento:</strong> Rocha</p>-->
    								<p class="card-text"><strong>Fecha de alta: </strong><%=alta.get(GregorianCalendar.DAY_OF_MONTH)%>/<%=alta.get(GregorianCalendar.MONTH)+1%>/<%=alta.get(GregorianCalendar.YEAR)%></p>	
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
							HashSet<DtSalida> salidas = (HashSet<DtSalida>) request.getAttribute("usuarioDetalleSalidas");
							for(DtSalida sal : salidas){							
  						%>
  						
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img <%if (sal.getLinkImagen() != null){%> src="<%=sal.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultSalida.png"<%}%> class="card-img-top" alt="...">
  								<div class="card-body" id="card-body-paquete">
    								<h3 class="card-title"><%=sal.getNombre()%></h3>    			
    								<a href="/Tarea2/VerDatosSalida?salSeleccionada=<%=sal.getNombre()%>" class="stretched-link"></a>
   		
  								</div>  						
							</div>
					<%				
							}
						}else{
							HashSet<DtSalida> salidasConf = (HashSet<DtSalida>) request.getAttribute("usuarioDetalleSalidasConfirmadas");
							for(DtSalida sal : salidasConf){
							%>
							<div id="paquete-card" class="card" style="width: 18rem;">
  								<img <%if (sal.getLinkImagen() != null){%> src="<%=sal.getLinkImagen()%>" <%} else {%>src="resources/img/imgDefaultSalida.png"<%}%> class="card-img-top" alt="...">
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
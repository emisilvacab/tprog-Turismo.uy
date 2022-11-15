<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model.EstadoSesion"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="publicadores.DtActividad"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista Actividades</title>
    <link rel="icon" href="img?id=icono.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
      	<link rel="stylesheet" href="resources/css/style.css">
    
  </head>
  <body>
    <div class="cuerpo">

    	<%
		String estado = (String) request.getAttribute("error");
		if (estado != null)
			request.setAttribute("error", null);
		%>
		
    	<jsp:include page="/template/navbar.jsp"/>
    	
    	
	    <div class="row gx-1">  
	      <div class="col-sm-6">
	        <div class="dropdown">
	          <div class="btn-group" style="width:100%;">
	          <a class="btn btn-outline-primary btn-block dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Departamentos
	          </a>
	          
	          <ul class="dropdown-menu" style="width:100%;">
	        <% 
	        Set<String> dptos = (HashSet<String>) request.getAttribute("dptos");
	    	if(dptos != null) {
	    		boolean primero = true;
	    		for(String dpto: dptos){
	    			if (primero) {
	    				primero = false;
	    		%> <li><a class="dropdown-item" href="ListarActividades?tipoPedidoActividad=departamento&nombreTipoActividad=<%=dpto%>"><%=dpto%></a></li>
			<% 	
				} else {
			%>
            	<li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="ListarActividades?tipoPedidoActividad=departamento&nombreTipoActividad=<%=dpto%>"><%=dpto%></a></li>
	        <%
					}
				}
	    	}
  	  		%>
	          </ul>
	          </div>
	        </div>
	      </div>
	      
	      <div class="col-sm-6">
	        <div class="dropdown">
	          <div class="btn-group" style="width:100%">
	          <a class="btn btn-outline-primary btn-block dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Categoria
	          </a>
	          
	          <ul class="dropdown-menu" style="width:100%">
	   	    <%
	   	    
	   	 	Set<String> cats = (HashSet<String>) request.getAttribute("cats");
	    	if(cats != null) {
	    		boolean primerCat = true;
				for(String cat: cats){
					if (primerCat) {
	    				primerCat = false;
				%> <li><a class="dropdown-item" href="ListarActividades?tipoPedidoActividad=categoria&nombreTipoActividad=<%=cat%>"><%=cat%></a></li>
			<% 	
					} else {
			%>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="ListarActividades?tipoPedidoActividad=categoria&nombreTipoActividad=<%=cat%>"><%=cat%></a></li>
   	        <%
					}
				}
	    	}
  	  		%>
	          </ul>
	          
	          </div>
	        </div>
	      </div>
	    </div>
	    <%
	    	String nombreTipoActividad = request.getParameter("nombreTipoActividad");
	    	if (nombreTipoActividad != null) {
	    %>
		<h1 class="mt-3 mb-2 mx-3">
			Actividades turísticas de <%=request.getParameter("nombreTipoActividad")%>
		</h1>
		    <%
				HashSet<DtActividad> listaActividades = (HashSet<DtActividad>) request.getAttribute("actividades");
		    	if (nombreTipoActividad != null) {
				if (listaActividades.isEmpty()) {
			%>
					<h4 class="mb-2 mx-3 text-muted">
					No se encuentran actividades turísticas disponibles
					</h4>
			<%
				} else {
			%>	
	    <div class="container">
			<div class="row gx-2">
			<%
					for (DtActividad actividad: listaActividades) {
			%>

		      <div class="col-sm">
		        <div class="card mx-3 my-3">
		        	<a href="VerDatosActividad?actSeleccionada=<%=actividad.getNombre()%>">
		        	<img <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%} else {%>src="img?id=imgDefaultActividad.png"<%}%> class="card-img-top card-img-usuario" alt="...">
		        	</a>
		          	<div class="card-body">
			            <h3 class="card-title"><%=actividad.getNombre()%></h3>
			            <p class="card-text card-lines"><%=actividad.getDescripcion()%></p>
				    </div>
				  </div>
				</div>
			<%
					}
			%>
		    </div>
	    </div>
	    <%
		    	}
		    }
	    }
	    %>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

    
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.datatypes.DTUsuario"%>
<%@page import="logica.datatypes.DTTurista"%>
<%@page import="logica.datatypes.DTProveedor"%>
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
	
<section id="section-listdo-usuarios" class="section">
	<h1 class="section-header" id="section-header-usuarios"> Usuarios registrados:</h1>
	
		<div class="container" id="container-cards-usuarios">
	    
	    
		<div class="d-flex align-items-stretch" id="flex-cards-usuarios">
			<%
				HashSet<DTUsuario> listaUsuarios = (HashSet<DTUsuario>) request.getAttribute("usuarios");
				for (DTUsuario usuario: listaUsuarios) {
					String nick = usuario.getNickname();
			%>
	
			<div id="usuario-card" class="card" style="width: 18rem;">
  				<img id="card-img-usuario" <%if (usuario.getLinkImagen() != null){%> src="<%=usuario.getLinkImagen()%>" <%} else {%>src="https://cdn-icons-png.flaticon.com/512/1077/1077063.png"<%}%> class="card-img-top" alt="...">
  				<div class="card-body" id="card-body-paquete">

    				<h3 class="card-title"><%=nick%></h3>
    				<p class="card-text"><%=usuario.getApellido()%>, <%=usuario.getNombre()%></p>
    				<h6 class="card-text"><% if (usuario instanceof DTTurista) {%>Turista<%} else {%>Proveedor<%}%></h6>
        			<p class="card-text"><small class="text-muted"><%=usuario.getCorreo()%></small></p>
    				<a href="DetalleUsuario?usuarioDetalleNickname=<%=nick%>" class="stretched-link"></a>
    			
  				</div>  						
			</div>
			<%
				}
			%>
			
		</div>	
	</div>
</section>

</body>
</html>
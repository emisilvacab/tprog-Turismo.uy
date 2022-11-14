<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="publicadores.DtUsuario"%>


	<!-- ESTA FALTANDO IMAGEN DE USUARIO Y LINKEAR A LOS JSP EN CADA CASO -->
	<nav class="navbar">
     <div class="container-fluid">
    
      <a class="navbar-brand" href="Home">
        <img src="img?id=logo.png" alt="Turismo.uy" class="logopic">
      </a>
      
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Buscar">
        <button class="btn btn-outline-dark" type="Buscar">Buscar</button>
      </form>
      
      <%
		DtUsuario usr = (DtUsuario) session.getAttribute("usuario_logueado");
		if (usr == null) {
	  %>
      <div>
	      <a class="navbar-text" href="registrar">Registrarse</a>
	      <a class="navbar-text" href="log?iniciar=si">Iniciar sesión</a>
  	  </div>
  	  
  	  <%
		} else {
	  %>
		<div>
		 <div class="dropdown">
		 	<button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		  	 <%
				if (usr.getLinkImagen() == null) {
	 		 %>
		  		<img src="https://cdn-icons-png.flaticon.com/512/1077/1077063.png" id="foto_usuario" class="rounded-circle" alt="foto de usuario" class="logopic">
		  	<%
				} else { 
		  	%>
		  		<img src="<%=usr.getLinkImagen()%>" id="foto_usuario" class="rounded-circle" alt="foto de usuario" class="logopic">
		  	<%
		  		} 
		  	%>
		  	<%=usr.getNickname()%>
			</button>
			<ul class="dropdown-menu dropdown-menu-dark">
				<li><a class="dropdown-item" href="DetalleUsuario?usuarioDetalleNickname=<%=usr.getNickname()%>">Ver mi perfil</a></li>
				<li><hr class="dropdown-divider"></li>
			 	<li><a class="dropdown-item" onClick="cerrarSesion()" href="log">Cerrar sesión</a></li>
			</ul>
		 </div>
		</div>
	  <%
		} 
	  %>
     </div>
   </nav>
  
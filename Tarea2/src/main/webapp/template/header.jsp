<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="logica.datatypes.DTUsuario"%>

	<!-- ESTA FALTANDO IMAGEN DE USUARIO Y LINKEAR A LOS JSP EN CADA CASO -->
	<nav class="navbar">
     <div class="container-fluid">
    
      <a class="navbar-brand" href="index.html">
        <img src="resources/img/logo.png" alt="Turismo.uy" class="logopic">
      </a>
      
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Buscar">
        <button class="btn btn-outline-dark" type="Buscar">Buscar</button>
      </form>
      
      <%
		DTUsuario usr = (DTUsuario) session.getAttribute("usuario_logueado");
		if (usr == null) {
	  %>
      <div>
	      <a class="navbar-text" href="altausuario.html">Registrarse</a>
	      <a class="navbar-text" href="/Tarea2/log?iniciar=si">Iniciar sesión</a>
  	  </div>
  	  
  	  <%
		} else {
	  %>
		<div>
		 <div class="dropdown">
		 	<button class="btn btn-outline-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		  	<img src="https://c.wallhere.com/photos/55/39/safe_house_cia_agent_tobin_frost_denzel_washington-584127.jpg!d" id="foto_usuario" class="rounded-circle" alt="foto de usuario" class="logopic">
		  	<%=usr.getNickname()%>
			</button>
			<ul class="dropdown-menu dropdown-menu-dark">
				<li><a class="dropdown-item" href="detalleDeUsuarioProveedor_proveedor.html">Ver mi perfil</a></li>
				<li><hr class="dropdown-divider"></li>
			 	<li><a class="dropdown-item" onClick="cerrarSesion()" href="/Tarea2/log">Cerrar sesión</a></li>
			</ul>
		 </div>
		</div>
	  <%
		} 
	  %>
     </div>
   </nav>
  
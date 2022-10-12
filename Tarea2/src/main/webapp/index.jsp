<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>


<!DOCTYPE html>
<html>
	<head>
	   <jsp:include page="/template/head.jsp"/>
    </head>

<%
	EstadoSesion estado = (EstadoSesion) session.getAttribute("estado_sesion");
	if (estado == null) {
		session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
	}
%>
<body>
	
	<jsp:include page="/template/header.jsp"/>
	<jsp:include page="/template/aside-bar.jsp"/>
	
	<section id="section-middle" class="section">
	    <div class="card" id = "card-presentacion">
		  <div class="card-body">
		    <h4 class="card-title">Solos vamos más rápido, juntos vamos más lejos.</h4>
		      <p class="card-text">Como forma de colaborar con la reactivación del sector turístico en Uruguay, surge Turismo.uy. Brindamos la posibilidad de que proveedores ofrezcan sus actividades turísticas en línea. También permitimos que el  público general pueda acceder a estas actividades tan importantes para la salud y para la reactivación del sector. </p>
	      </div>
	    </div>
		
		<div class="card" id = "card-unitenos">
		  <div class="card-body">
		  	<a class = "ref" href=altausuario.html>
		    <h4 class="card-title">¡Unítenos y forma parte de la experiencia!</h4>
		    </a>
		  </div>
		</div>
			
	    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false">
	      
	      <div class="carousel-indicators">
	        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
	        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
	      </div> 
	      <div class="carousel-inner">
	        <div class="carousel-item active">
	          <img src="resources/img/campo.jpg" class="d-block w-100" alt="...">
	          <div class="carousel-caption d-none d-md-block">
	            <h5>Colonia</h5>
	            <p>Campos que generan paz.</p>
	          </div>
	        </div>  
	        <div class="carousel-item">
	          <img src="resources/img/piria.jpg" class="d-block w-100" alt="...">
	          <div class="carousel-caption d-none d-md-block">
	            <h5>Piriapolis, Maldonado</h5>
	            <p>Atardeceres mágicos.</p>
	          </div>
	        </div>
	        <div class="carousel-item">
	          <img src="resources/img/rodo.jpg" class="d-block w-100" alt="...">
	          <div class="carousel-caption d-none d-md-block">
	            <h5>Parque rodó, Montevideo</h5>
	            <p>Divertidos juegos sobre la rambla.</p>
	          </div>
	        </div>
	      </div>
	      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
	        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	        <span class="visually-hidden">Previous</span>
	      </button>
	      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
	        <span class="carousel-control-next-icon" aria-hidden="true"></span>
	        <span class="visually-hidden">Next</span>
	      </button>
	      
	    </div>
  </section>
</body>
</html>
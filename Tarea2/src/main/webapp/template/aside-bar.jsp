<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="publicadores.DtUsuario"%>
<%@ page import ="publicadores.DtTurista"%>
<%@ page import ="publicadores.DtProveedor"%>

<%@page import="java.util.Set"%>

<!-- FALTA LINKEAR BIEN CON CADA PAGINA -->

<aside id="sidebar" class="sidebar">
    <div class="accordion" id="accordionPanelsStayOpenExample">
       <div class="accordion-item">
	    <h2 class="accordion-header" id="headingOne">
	      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
	        Opciones
	      </button>
	    </h2>
	      	<%
				DtUsuario usr = (DtUsuario) session.getAttribute("usuario_logueado");
				if (usr != null && usr.getClass().getName().equals("publicadores.DtProveedor")) {
	  		%>
	        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		        <a class="ref" href="altaActividad">Alta de actividad</a>
		      </div>
		    </div>    
		    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		        <a class="ref" href="altaSalida?cambio=iniciar">Alta de salida</a>
		      </div>
		    </div>
		    <% 
		    	} else if (usr != null && usr.getClass().getName().equals("publicadores.DtTurista")) {  
		    %>
		    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		        <a class="ref" href="inscribir?cambio=iniciar">Inscripción a salida</a>
		      </div>
		    </div>
		    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		        <a class="ref" href="comprar">Compra de paquete</a>
		      </div>
		    </div>
	      	<%
	      		}
			%>
		  <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
	         <div class="accordion-body">
	        	<a class="ref" href="ListarUsuarios">Usuarios registrados</a>
	         </div>
	      </div>
	      <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
	         <div class="accordion-body">
	        	<a class="ref" href="ListarPaquetes">Paquetes registrados</a>
	         </div>
	      </div>
	  </div>

      <div class="accordion-item">
	    <h2 class="accordion-header" id="headingTwo">
	      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	        Departamentos</button>
	    </h2>
	    <% 
			Set<String> dptos = (Set<String>) request.getAttribute("dptos");
	    	if(dptos != null)
	    		for(String dpto: dptos){
		%>
	    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
	      <div class="accordion-body">
	       <a class="ref" href="ListarActividades?tipoPedidoActividad=departamento&nombreTipoActividad=<%=dpto%>"><%=dpto%></a>
	      </div>
   		</div>
  	  	<% 
			}
  	  	%>
  	  	</div>
  	  
  	  <div class="accordion-item">
	    <h2 class="accordion-header" id="headingThree">
	      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
	        Categorías</button>
	    </h2>
	    <% 
			Set<String> cats = (Set<String>) request.getAttribute("cats");
	    	if(cats != null)
				for(String cat: cats){
		%>
	    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
	      <div class="accordion-body">
	       <a class="ref" href="ListarActividades?tipoPedidoActividad=categoria&nombreTipoActividad=<%=cat%>"><%=cat%></a>
	      </div>
   		</div>
  	  	
  	  	<% 
			}
  	  	%>
      </div>
      
    </div> 
</aside>
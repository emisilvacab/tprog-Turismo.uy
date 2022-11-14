<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="publicadores.DtUsuario"%>

<script src=/Tarea3/src/main/webapp/template/cerrarSesion.js type="text/javascript"></script>


    <nav class="navbar navbar-expand-lg" id="accordionPanelsStayOpenExample" style="background-color: #fbf7f0; box-shadow: 0px 10px 10px rgba(1, 41, 112, 0.1); position: sticky;">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" style="margin-right: 4px;">
            <span class="navbar-toggler-icon"></span>
            </button>
            <%
            DtUsuario usr = (DtUsuario) session.getAttribute("usuario_logueado");
            if (usr != null) {
            %>
            <a class="navbar-brand max-lines" href="#">Bienvenido <%=usr.getNombre()%></a>
            <img src="/Tarea3/img?id=icono.png" alt="Logo" width="30" height="24" class="d-inline-block align-text-end">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mx-2 mb-lg-0">
                    <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Ver actividades turisticas</a>
                    </li>
                    <li class="nav-item">
                    <a class="nav-link" href="#cerrarSesionModal" data-toggle="modal" data-target="#cerrarSesionModal" onClick="cerrarSesionModal()">Cerrar sesion</a>
                 
                    </li>
                </ul>
            </div>
            
					
            <%
            } 
            %>
        </div>
    </nav>
    
    <div class="modal fade" id="cerrarSesionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Cerrar sesión</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					        ...
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					        <button type="button" class="btn btn-primary">Confirmar</button>
					      </div>
					    </div>
					  </div>
					</div>
					
					
					
					
					
					







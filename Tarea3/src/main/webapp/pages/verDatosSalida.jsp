<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="publicadores.DtSalida"%>
<%@page import="publicadores.DtUsuario"%>
<%@page import="publicadores.DtActividad"%>
<%@page import="publicadores.Estado"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salida</title>
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
	
	<%
	
		DtSalida salida = (DtSalida) request.getAttribute("salida");
		if (salida != null) {
		
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
		GregorianCalendar fechaSalida = salida.getFechaDTSalida().toGregorianCalendar();
	       Integer diaSalida = fechaSalida.get(fechaSalida.DAY_OF_MONTH);
	       Integer mesSalida = fechaSalida.get(fechaSalida.MONTH) + 1;
	       Integer anioSalida = fechaSalida.get(fechaSalida.YEAR);
	       
	       GregorianCalendar fechaAlta = salida.getAlta().toGregorianCalendar();
	       Integer diaAlta = fechaAlta.get(fechaAlta.DAY_OF_MONTH);
	       Integer mesAlta = fechaAlta.get(fechaAlta.MONTH) + 1;
	       Integer anioAlta = fechaAlta.get(fechaAlta.YEAR);
	       
	       boolean esVigente = (boolean) request.getAttribute("vigencia");
	       DtActividad actividad = (DtActividad) request.getAttribute("actividad");
	       
		%>
	
	    <div class="container" style="min-width: 97%">
	        <div class="row gx-1" style="min-width: 97%">
	            <div class="col my-3">
	              <div class="card mt-2 mx-3">
	                  <div class="row gx-0">
	                      <div class="col-md-6">
	                          <img <%if (salida.getLinkImagen() != null){%> src="<%=salida.getLinkImagen()%>" <%} else {%>src="img?id=imgDefaultSalida.png"<%}%> class="img-fluid rounded" alt="...">
	                      </div>
	                      <div class="col-md-6">
	                          <div class="card-body mt-2 pb-0" style="font-size: 110%;">
	                              <h2 class="title"><%=salida.getNombre()%></h2>
	                              <div class="card-body pt-1">
									<ul class="list-group list-group-flush" style="font-size: 135%;">
										<li class="list-group-item"><%=salida.getLugarDTSalida()%></li>
										<li class="list-group-item"><%=diaSalida%>/<%=mesSalida%>/<%=anioSalida%></li>
										<li class="list-group-item">A las <%=horaConvertida%>hs</li>
										<li class="list-group-item">Hasta <%=salida.getMaxTuristas()%> turistas</li>
									</ul>
									<p class="card-text mb-1" style="margin-left: 4px;"><small class="text-muted">Dada de alta el <%=diaAlta%>/<%=mesAlta%>/<%=anioAlta%></small></p>
	                              </div>
	                          </div>
	                      </div>
	                  </div>
	                </div>
	            </div>
	        </div>
	
	        <div class="col">
	            <h3 class="mb-3">Asociada a la actividad</h3>
	            <div class="card mx-3 mb-3" style="max-width: 100%;">
	                <div class="row g-0">
	                    <div class="col-md-4" style="min-width: 200px;">
	                    	<a href="VerDatosActividad?actSeleccionada=<%=actividad.getNombre()%>">
	                        	<img <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%} else {%>src="img?id=imgDefaultActividad.png"<%}%> class="img-fluid rounded-start" alt="Actividad">
	                    	</a>
	                    </div>
	                    <div class="col-md-8" style="min-width: 300px;">
	                        <div class="card-body" style="max-width: 100%;">
	                            <h4 class="card-title"><%=actividad.getNombre()%></h4>
	                            <div class="card-text">
	                                <%=actividad.getDescripcion()%>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
		<%
	  	}
	  	%>
	   	</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

    
</body>
</html>
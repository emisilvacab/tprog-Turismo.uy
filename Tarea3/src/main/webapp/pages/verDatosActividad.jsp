<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>
<%@page import="publicadores.DtActividad"%>
<%@page import="publicadores.DtSalida"%>
<%@page import="publicadores.DtPaquete"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Actividad</title>
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
			DtActividad actividad = (DtActividad) request.getAttribute("actividad");
			String nombreProveedor = (String) request.getAttribute("proveedor");
			if (actividad != null) {
		%>
	    <div class="container" style="min-width: 97%">
	        <div class="row gx-1" style="min-width: 97%">
	            <div class="col my-3">
	                <div class="row gx-0 mx-3">
	                    <div class="col-sm-6 mt-1">
	                        <div class="card" style="min-height:100%">
	                            <img <%if (actividad.getLinkImagen() != null){%> src="<%=actividad.getLinkImagen()%>" <%} else {%>src="img?id=imgDefaultActividad.png"<%}%> class="card-img-top card-img-usuario" alt="...">
	                            <div class="card-body" style="font-size: 110%;">
	                                <h2 class="title">Degusta</h2>
	                                    <div class="card-text">
	                                        Festival gastronómico de productos locales en Rocha.
	                                    </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-sm-6 mt-1">
	                        <div class="card d-flex justify-content-center flex-wrap" style="min-height:100%">
	                            <div class="card-body pt-1">
	                                <ul class="list-group list-group-flush">
	                                    <li class="list-group-item" style="font-size: 150%;"><%=actividad.getCiudad()%></li>
	                                    <li class="list-group-item" style="font-size: 150%;"><%=actividad.getDuracion()%> días</li>
	                                    <li class="list-group-item" style="font-weight: 600; font-size: 150%;">$<%=actividad.getCosto()%></li>
	                                    <li class="list-group-item" style="font-size: 130%;">Proveedor: <em style="color: #2f3131;"><%=nombreProveedor%></em></li>
	                                    <li class="list-group-item" style="font-size: 130%;">Departamento: <%=(String) request.getAttribute("departamento")%></li>
	                                    <li class="list-group-item" style="font-size: 130%;">
	                                    <%
										HashSet<String> listaCategorias = (HashSet<String>) request.getAttribute("categorias");
										if (!listaCategorias.isEmpty()) {
											String categorias = String.join(", ", listaCategorias);
											
											if (listaCategorias.size() > 1) {
										%>
										Categorías:
										<%
											} else {
										%>
										Categoría:
										<%
											}
										%>
											<%=categorias%>
										<%
										}
										%>
	                                    </li>
	                                    <li class="list-group-item" style="font-size: 130%;"><%=actividad.getEstado().toString()%></li>
	                                </ul>
	                                <p class="card-text"><small class="text-muted">Dada de alta el <%=(Integer) request.getAttribute("fechaAltaDia")%>/<%=(Integer) request.getAttribute("fechaAltaMes")%>/<%=(Integer) request.getAttribute("fechaAltaAño")%></small></p>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	
	        <div class="col">
	            <div class="row gx-1" style="min-width: 47vw;">
	                <div class="col-sm-6">
	                    <h2 class="mx-3">Salidas</h2>
	                    <%
		  	
					  	HashSet<DtSalida> listaSalidas = (HashSet<DtSalida>) request.getAttribute("salidas");
					  	GregorianCalendar fechaSalida;
						for (DtSalida salida: listaSalidas) {
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
					        fechaSalida = salida.getFechaDTSalida().toGregorianCalendar();
					        Integer diaSalida = fechaSalida.get(fechaSalida.DAY_OF_MONTH);
					        Integer mesSalida = fechaSalida.get(fechaSalida.MONTH) + 1;
					        Integer anioSalida = fechaSalida.get(fechaSalida.YEAR);
					  	%>
	                    <div class="card mx-3 my-3">
	                    	<a href="VerDatosSalida?salSeleccionada=<%=salida.getNombre()%>">
	                        	<img <%if (salida.getLinkImagen() != null){%>src="<%=salida.getLinkImagen()%>"<%} else {%>src="img?id=imgDefaultSalida.png"<%}%> class="card-img-top card-img-usuario" alt="...">
	                        </a>
	                        <div class="card-body">
	                        <h3 class="card-title" style="overflow-wrap:break-word"><%=salida.getNombre()%></h3>
	                        <ul class="list-group list-group">
								<li class="list-group-item" style="font-size: 100%;">Fecha: <%=diaSalida%>/<%=mesSalida%>/<%=anioSalida%> a las <%=horaConvertida%>hs</li>
								<li class="list-group-item" style="font-size: 100%;">Lugar: <%=salida.getLugarDTSalida()%></li>
							</ul>
	                        </div>
	                    </div>
	                    <%
						}
						%>
	                </div>
	                
	                <div class="col-sm-6">
	                    <h2 class="mx-3">Paquetes</h2>
	                    <%
					  	HashSet<DtPaquete> listaPaquetes = (HashSet<DtPaquete>) request.getAttribute("paquetes");
					  	for (DtPaquete paquete: listaPaquetes) {
					  	%>
	                    <div class="card mx-3 my-3">
	                        <img <%if (paquete.getLinkImagen() != null){%> src="<%=paquete.getLinkImagen()%>" <%} else {%>src="img?id=imgDefaultPaquete.png"<%}%> class="card-img-top card-img-usuario" alt="...">
	                        <div class="card-body">
	                            <h3 class="card-title" style="overflow-wrap:break-word; hyphens: auto;"><%=paquete.getNombre()%></h3>
	                            <p class="card-text card-lines"><%=paquete.getDescripcion()%></h6>
	                        </div>
	                    </div>
	                    <%
						}
						%>
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
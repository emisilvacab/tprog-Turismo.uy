<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EstadoSesion"%>

<!DOCTYPE html>
<html>
<head>
	   <jsp:include page="/template/head.jsp"/>
</head>


<body>
	<jsp:include page="/template/header.jsp"/>
	<jsp:include page="/template/aside-bar.jsp"/>
	
	<section id="section-middle" class="section">
	<div class='row'>
		<h1 class="headerRegistrarUsuario" id="header-center">Registrar Usuario</h1>
	</div>
		<%  //CARTELES DE ERRORES
			String mensaje = (String) request.getAttribute("error");
			request.setAttribute("error", null);			
		if (mensaje != null && mensaje.equals("usuario-repetido-nickname")) {
			System.out.println("llego error repetido nickname");
		%>				
		<h6 class="text-center" style="color:#FF0000">Ya existe un usuario con el nickname ingresado.</h6>
		<%
			} else { 
				if (mensaje != null && mensaje.equals("usuario-repetido-correo")) {	
					System.out.println("llego error repetido correo");

		%>
		<h6 style="color:#FF0000">Ya existe un usuario con el correo ingresado.</h6>
		<%
				}
			}
		%>		
			<form class="form" id="form-registrar-usuario" action="/Tarea2/registrar" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-4">
						<label class="lbltxt">Nickname*</label><br>
						<input class="inputbox" name="nickname" id="fieldNickname" <%if (request.getAttribute("nickname") != null){String nickname = (String)request.getAttribute("nickname"); %> value='<%=nickname%>' <%} %>><br>
						<label class="lbltxt">Nombre*</label><br>
						<input class="inputbox" type="text" id="nombrePersona" name="nombrePersona" <%String nombre = (String) request.getAttribute("nombre"); if (nombre == null) nombre = "";  System.out.println(nombre); %> value='<%=nombre%>'><br>	
						
						<label class="lbltxt">Apellido*</label><br>
						<input class="inputbox" id="apellidoPersona" name="apellido" <%if (request.getAttribute("apellido") != null){String apellido = (String)request.getAttribute("apellido"); %> value='<%=apellido%>' <%} %>><br>			
						
						<label class="lbltxt">Contraseña*</label><br>
						<input class="inputbox" type="password" id="contrasenaPersona" name="contrasena"><br>
						
				
						<label class="lbltxt">Confirmación*</label><br>
						<input class="inputbox" type="password" id="confirmacionContrasena" name="confirmacionContrasena"><br>
						
						<label class="lbltxt">Nacimiento*</label><br>
						<input class="inputbox" type="date" id="nacimientoPersona" name="nacimientoPersona" <%if (request.getAttribute("nacimientoPersona") != null){String nacimiento = (String)request.getAttribute("nacimientoPersona"); %> value='<%=nacimiento%>' <%} %>><br><br>	
						
						<label class="lbltxt">Correo*</label><br>
						<input class="inputbox" type="email" id="correoPersona" name="correoPersona" <%if (request.getAttribute("correoPersona") != null){String correo = (String)request.getAttribute("correoPersona"); %> value='<%=correo%>' <%} %>><br><br>		
						
						<label class="lbltxt">Imagen</label><br>
						<input class="inputbox" type="file" id="imgPersona" name="imgPersona" accept = "image/*"><br><br><br>		
					</div>
				
				
			<!-- <div class="column">	 -->	
					<div class="col-md-4">
						<h2 class="lbltxt">Turista</h2>
					    <label class="lbltxt">Nacionalidad*</label><br>
					    <select name="nacionalidad" id="nacionalidadTurista">
					    <option value="Afghanistan">Afghanistan</option>
							<option value="Albania">Albania</option>
							<option value="Algeria">Algeria</option>
							<option value="American Samoa">American Samoa</option>
							<option value="Andorra">Andorra</option>
							<option value="Angola">Angola</option>
							<option value="Anguilla">Anguilla</option>
							<option value="Antartica">Antarctica</option>
							<option value="Antigua and Barbuda">Antigua and Barbuda</option>
							<option value="Argentina">Argentina</option>
							<option value="Armenia">Armenia</option>
							<option value="Aruba">Aruba</option>
							<option value="Australia">Australia</option>
							<option value="Austria">Austria</option>
							<option value="Azerbaijan">Azerbaijan</option>
							<option value="Bahamas">Bahamas</option>
							<option value="Bahrain">Bahrain</option>
							<option value="Bangladesh">Bangladesh</option>
							<option value="Barbados">Barbados</option>
							<option value="Belarus">Belarus</option>
							<option value="Belgium">Belgium</option>
							<option value="Belize">Belize</option>
							<option value="Benin">Benin</option>
							<option value="Bermuda">Bermuda</option>
							<option value="Bhutan">Bhutan</option>
							<option value="Bolivia">Bolivia</option>
							<option value="Bosnia and Herzegowina">Bosnia and Herzegowina</option>
							<option value="Botswana">Botswana</option>
							<option value="Bouvet Island">Bouvet Island</option>
							<option value="Brazil">Brazil</option>
							<option value="British Indian Ocean Territory">British Indian Ocean Territory</option>
							<option value="Brunei Darussalam">Brunei Darussalam</option>
							<option value="Bulgaria">Bulgaria</option>
							<option value="Burkina Faso">Burkina Faso</option>
							<option value="Burundi">Burundi</option>
							<option value="Cambodia">Cambodia</option>
							<option value="Cameroon">Cameroon</option>
							<option value="Canada">Canada</option>
							<option value="Cape Verde">Cape Verde</option>
							<option value="Cayman Islands">Cayman Islands</option>
							<option value="Central African Republic">Central African Republic</option>
							<option value="Chad">Chad</option>
							<option value="Chile">Chile</option>
							<option value="China">China</option>
							<option value="Christmas Island">Christmas Island</option>
							<option value="Cocos Islands">Cocos (Keeling) Islands</option>
							<option value="Colombia">Colombia</option>
							<option value="Comoros">Comoros</option>
							<option value="Congo">Congo</option>
							<option value="Congo">Congo, the Democratic Republic of the</option>
							<option value="Cook Islands">Cook Islands</option>
							<option value="Costa Rica">Costa Rica</option>
							<option value="Cota D'Ivoire">Cote d'Ivoire</option>
							<option value="Croatia">Croatia (Hrvatska)</option>
							<option value="Cuba">Cuba</option>
							<option value="Cyprus">Cyprus</option>
							<option value="Czech Republic">Czech Republic</option>
							<option value="Denmark">Denmark</option>
							<option value="Djibouti">Djibouti</option>
							<option value="Dominica">Dominica</option>
							<option value="Dominican Republic">Dominican Republic</option>
							<option value="East Timor">East Timor</option>
							<option value="Ecuador">Ecuador</option>
							<option value="Egypt">Egypt</option>
							<option value="El Salvador">El Salvador</option>
							<option value="Equatorial Guinea">Equatorial Guinea</option>
							<option value="Eritrea">Eritrea</option>
							<option value="Estonia">Estonia</option>
							<option value="Ethiopia">Ethiopia</option>
							<option value="Falkland Islands">Falkland Islands (Malvinas)</option>
							<option value="Faroe Islands">Faroe Islands</option>
							<option value="Fiji">Fiji</option>
							<option value="Finland">Finland</option>
							<option value="France">France</option>
							<option value="France Metropolitan">France, Metropolitan</option>
							<option value="French Guiana">French Guiana</option>
							<option value="French Polynesia">French Polynesia</option>
							<option value="French Southern Territories">French Southern Territories</option>
							<option value="Gabon">Gabon</option>
							<option value="Gambia">Gambia</option>
							<option value="Georgia">Georgia</option>
							<option value="Germany">Germany</option>
							<option value="Ghana">Ghana</option>
							<option value="Gibraltar">Gibraltar</option>
							<option value="Greece">Greece</option>
							<option value="Greenland">Greenland</option>
							<option value="Grenada">Grenada</option>
							<option value="Guadeloupe">Guadeloupe</option>
							<option value="Guam">Guam</option>
							<option value="Guatemala">Guatemala</option>
							<option value="Guinea">Guinea</option>
							<option value="Guinea-Bissau">Guinea-Bissau</option>
							<option value="Guyana">Guyana</option>
							<option value="Haiti">Haiti</option>
							<option value="Heard and McDonald Islands">Heard and Mc Donald Islands</option>
							<option value="Holy See">Holy See (Vatican City State)</option>
							<option value="Honduras">Honduras</option>
							<option value="Hong Kong">Hong Kong</option>
							<option value="Hungary">Hungary</option>
							<option value="Iceland">Iceland</option>
							<option value="India">India</option>
							<option value="Indonesia">Indonesia</option>
							<option value="Iran">Iran (Islamic Republic of)</option>
							<option value="Iraq">Iraq</option>
							<option value="Ireland">Ireland</option>
							<option value="Israel">Israel</option>
							<option value="Italy">Italy</option>
							<option value="Jamaica">Jamaica</option>
							<option value="Japan">Japan</option>
							<option value="Jordan">Jordan</option>
							<option value="Kazakhstan">Kazakhstan</option>
							<option value="Kenya">Kenya</option>
							<option value="Kiribati">Kiribati</option>
							<option value="Democratic People's Republic of Korea">Korea, Democratic People's Republic of</option>
							<option value="Korea">Korea, Republic of</option>
							<option value="Kuwait">Kuwait</option>
							<option value="Kyrgyzstan">Kyrgyzstan</option>
							<option value="Lao">Lao People's Democratic Republic</option>
							<option value="Latvia">Latvia</option>
							<option value="Lebanon" selected>Lebanon</option>
							<option value="Lesotho">Lesotho</option>
							<option value="Liberia">Liberia</option>
							<option value="Libyan Arab Jamahiriya">Libyan Arab Jamahiriya</option>
							<option value="Liechtenstein">Liechtenstein</option>
							<option value="Lithuania">Lithuania</option>
							<option value="Luxembourg">Luxembourg</option>
							<option value="Macau">Macau</option>
							<option value="Macedonia">Macedonia, The Former Yugoslav Republic of</option>
							<option value="Madagascar">Madagascar</option>
							<option value="Malawi">Malawi</option>
							<option value="Malaysia">Malaysia</option>
							<option value="Maldives">Maldives</option>
							<option value="Mali">Mali</option>
							<option value="Malta">Malta</option>
							<option value="Marshall Islands">Marshall Islands</option>
							<option value="Martinique">Martinique</option>
							<option value="Mauritania">Mauritania</option>
							<option value="Mauritius">Mauritius</option>
							<option value="Mayotte">Mayotte</option>
							<option value="Mexico">Mexico</option>
							<option value="Micronesia">Micronesia, Federated States of</option>
							<option value="Moldova">Moldova, Republic of</option>
							<option value="Monaco">Monaco</option>
							<option value="Mongolia">Mongolia</option>
							<option value="Montserrat">Montserrat</option>
							<option value="Morocco">Morocco</option>
							<option value="Mozambique">Mozambique</option>
							<option value="Myanmar">Myanmar</option>
							<option value="Namibia">Namibia</option>
							<option value="Nauru">Nauru</option>
							<option value="Nepal">Nepal</option>
							<option value="Netherlands">Netherlands</option>
							<option value="Netherlands Antilles">Netherlands Antilles</option>
							<option value="New Caledonia">New Caledonia</option>
							<option value="New Zealand">New Zealand</option>
							<option value="Nicaragua">Nicaragua</option>
							<option value="Niger">Niger</option>
							<option value="Nigeria">Nigeria</option>
							<option value="Niue">Niue</option>
							<option value="Norfolk Island">Norfolk Island</option>
							<option value="Northern Mariana Islands">Northern Mariana Islands</option>
							<option value="Norway">Norway</option>
							<option value="Oman">Oman</option>
							<option value="Pakistan">Pakistan</option>
							<option value="Palau">Palau</option>
							<option value="Panama">Panama</option>
							<option value="Papua New Guinea">Papua New Guinea</option>
							<option value="Paraguay">Paraguay</option>
							<option value="Peru">Peru</option>
							<option value="Philippines">Philippines</option>
							<option value="Pitcairn">Pitcairn</option>
							<option value="Poland">Poland</option>
							<option value="Portugal">Portugal</option>
							<option value="Puerto Rico">Puerto Rico</option>
							<option value="Qatar">Qatar</option>
							<option value="Reunion">Reunion</option>
							<option value="Romania">Romania</option>
							<option value="Russia">Russian Federation</option>
							<option value="Rwanda">Rwanda</option>
							<option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option> 
							<option value="Saint LUCIA">Saint LUCIA</option>
							<option value="Saint Vincent">Saint Vincent and the Grenadines</option>
							<option value="Samoa">Samoa</option>
							<option value="San Marino">San Marino</option>
							<option value="Sao Tome and Principe">Sao Tome and Principe</option> 
							<option value="Saudi Arabia">Saudi Arabia</option>
							<option value="Senegal">Senegal</option>
							<option value="Seychelles">Seychelles</option>
							<option value="Sierra">Sierra Leone</option>
							<option value="Singapore">Singapore</option>
							<option value="Slovakia">Slovakia (Slovak Republic)</option>
							<option value="Slovenia">Slovenia</option>
							<option value="Solomon Islands">Solomon Islands</option>
							<option value="Somalia">Somalia</option>
							<option value="South Africa">South Africa</option>
							<option value="South Georgia">South Georgia and the South Sandwich Islands</option>
							<option value="Span">Spain</option>
							<option value="SriLanka">Sri Lanka</option>
							<option value="St. Helena">St. Helena</option>
							<option value="St. Pierre and Miguelon">St. Pierre and Miquelon</option>
							<option value="Sudan">Sudan</option>
							<option value="Suriname">Suriname</option>
							<option value="Svalbard">Svalbard and Jan Mayen Islands</option>
							<option value="Swaziland">Swaziland</option>
							<option value="Sweden">Sweden</option>
							<option value="Switzerland">Switzerland</option>
							<option value="Syria">Syrian Arab Republic</option>
							<option value="Taiwan">Taiwan, Province of China</option>
							<option value="Tajikistan">Tajikistan</option>
							<option value="Tanzania">Tanzania, United Republic of</option>
							<option value="Thailand">Thailand</option>
							<option value="Togo">Togo</option>
							<option value="Tokelau">Tokelau</option>
							<option value="Tonga">Tonga</option>
							<option value="Trinidad and Tobago">Trinidad and Tobago</option>
							<option value="Tunisia">Tunisia</option>
							<option value="Turkey">Turkey</option>
							<option value="Turkmenistan">Turkmenistan</option>
							<option value="Turks and Caicos">Turks and Caicos Islands</option>
							<option value="Tuvalu">Tuvalu</option>
							<option value="Uganda">Uganda</option>
							<option value="Ukraine">Ukraine</option>
							<option value="United Arab Emirates">United Arab Emirates</option>
							<option value="United Kingdom">United Kingdom</option>
							<option value="United States">United States</option>
							<option value="United States Minor Outlying Islands">United States Minor Outlying Islands</option>
							<option value="Uruguay">Uruguay</option>
							<option value="Uzbekistan">Uzbekistan</option>
							<option value="Vanuatu">Vanuatu</option>
							<option value="Venezuela">Venezuela</option>
							<option value="Vietnam">Viet Nam</option>
							<option value="Virgin Islands (British)">Virgin Islands (British)</option>
							<option value="Virgin Islands (U.S)">Virgin Islands (U.S.)</option>
							<option value="Wallis and Futana Islands">Wallis and Futuna Islands</option>
							<option value="Western Sahara">Western Sahara</option>
							<option value="Yemen">Yemen</option>
							<option value="Serbia">Serbia</option>
							<option value="Zambia">Zambia</option>
							<option value="Zimbabwe">Zimbabwe</option>
					    <%
					  		String nacionalidadSeleccionada = (String) request.getAttribute("nacionalidad");
					    	System.out.println("la nacionalidad: " + nacionalidadSeleccionada);
					  		if (nacionalidadSeleccionada != null) {
					  			System.out.println("llego la opcion seleccionada");
					  	%>
					  		<option selected><%=nacionalidadSeleccionada%></option>
					  	<%
					  		} else {
					  	%>
					  			<option selected>Uruguay</option>
					  	<% 
				  			} 
			  			%>
					  		
						</select>		  
					<br>
				<input class="btn btn-outline-dark" id="btnAceptarTur" onclick="return validarAltaUsuario(false)" name="btnAceptarTur" type="submit" value="Turista">
			</div>
		  
			  <div class="col-md-4">
					<h2 class="lbltxt">Proveedor</h2>
				  	<label class="lbltxt">Link</label><br>
				  	<input class="inputbox" type="url" id="linkProv" name="linkProv" <%if (request.getAttribute("linkProv") != null){String linkProv = (String)request.getAttribute("linkProv"); %> value='<%=linkProv%>' <%} %>><br>
					<label class="lbltxt">Descripción*</label><br>
					<textarea class="inputbox" rows = "5" cols ="38" id="descripcionProv" name ="descripcionProv" <%String descripcionProv = ""; if (request.getAttribute("descripcionProv") != null){descripcionProv = (String)request.getAttribute("descripcionProv");} %> ><%=descripcionProv%></textarea><br>		
				  <input class="btn btn-outline-dark" onclick="return validarAltaUsuario(true)" id="btnAceptarProv" name="btnAceptarProv" type="submit" value="Proveedor">
					  
			</div>
		</div>
		</form>
			
			<script>
				function cambiarTipoUsr(elem, usr) {
						document.getElementById("btnAceptar").disabled = false
					  var buttons;
					  var x = document.getElementById("js-description");
					  algunoApretado = true;
					  
					  if (usr == 'A'){
						  botonTurista=true;
						  document.getElementById("descripcionProv").value = "";
						buttons = document.getElementById("cat-Tur-content");
					  }else{
						  botonTurista=false;
						buttons = document.getElementById("cat-Prov-content");
					  }
					  
					  for (const child of x.children){
						  child.style.display = 'hidden';
						  console.log(child.id);
					  }
					  var description = elem.getAttribute('data-description');
					  x.appendChild(buttons.cloneNode(true));
					  var button = document.getElementsByClassName('js-button');
					  
					  for (var i = 0; i < button.length; i++) {
					    button[i].classList.remove('active-button');
					  }
					  
					  elem.classList.add('active-button');
				}
			</script>
			
			
	</section>
	

	
	<script src=resources/js/validarAltaUsuario.js type="text/javascript"></script>

</body>
</html>
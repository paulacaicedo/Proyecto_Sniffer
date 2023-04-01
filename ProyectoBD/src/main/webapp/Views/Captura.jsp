<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Informacion de la Captura</title>
</head>
<body>

 
	<c:set var="captura" value="${captura}"> </c:set>
	
		<table border= "1">
			<tr>
				<td>ID CAPTURA:</td>
				<td> <input type="text" name="id_captura" size="50" value="${captura.id_captura}"> </td>
			</tr>
		
			<tr>
				<td>DIRECCION FISICA:</td>
				<td> <input type="text" name="direccionFisica" size="50" value="${captura.direccionFisica}"> </td>
			</tr>
			
			<tr>
				<td>DIRECCION PROCEDENCIA:</td>
				<td> 
					 <label>
					 	<input type="text" name="direccion_source" size="50" value="${captura.direccionIp_source}">
					 </label> 
				 </td>
			</tr>
			
			<tr>
				<td>DIRECCION DESTINO:</td>
				<td> <input type="text" name="direccion_destino" size="50" value="${captura.direccionIp_destino}"> </td>
			</tr>
			
			<tr>
				<td>PUERTO DESTINO:</td>
				<td> <input type="text" name="puerto_destino" size="50" value="${captura.puertoDestino}"> </td>
			</tr>
			
			<tr>
				<td>ID SERVICIO:</td>
				<td> <input type="text" name="id_servicio" size="50" value="${captura.id_servicio}"> </td>
			</tr>
	
		</table>
		<br>
 
		<table border= "1">
			<tr>
				<td>SERVICIO HTPS:</td>
				<td> <a>Codigo 1 </a></td>
			</tr>
		
			<tr>
				<td>SERVICIO FTP:</td>
				<td> <a>Codigo  2 </a></td>
			</tr>
			
			<tr>
				<td>SERVICIO DNS:</td>
				<td> <a>Codigo 3</a></td>
			</tr>
			
			<tr>
				<td>SERVICIO SMTP:</td>
				<td> <a>Codigo 4 </a></td>
			</tr>
			
			<tr>
				<td>SERVICIO POP3:</td>
				<td> <a>Codigo 5 </a> </td>
			</tr>
			
			<tr>
				<td>SERVICIO DHCP:</td>
				<td> <a>Codigo 6 </a> </td>
			</tr>
			<tr>
				<td>SERVICIO HTTPS:</td>
				<td> <a>Codigo 7 </a> </td>
			</tr>
			<tr>
				<td>SERVICIO FTPS:</td>
				<td> <a>Codigo 8 </a> </td>
			</tr>
			<tr>
				<td>SERVICIO Otro:</td>
				<td> <a>Codigo 9 </a> </td>
			</tr>
	
		</table>
 
</body>
</html>
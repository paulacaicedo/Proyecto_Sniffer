<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Paquetes</title>
</head>
<body>

<h1>Lista de los Paquetes</h1>
	
	<table border="1" style="width:100%">
	
		<tr>
			<td>ID CONSULTA</td>
			<td>ID PAQUETE</td>
			<td>LONGITUD</td>
			<td>FECHA CAPTURA DEL PAQUETE</td>
			<td>ID CAPTURA</td>
			<td>SERVICIO</td>
			 
		</tr>
	
		<c:forEach var="paquete" items="${listapaquete}"> 
			<tr>
				<td><c:out value="${paquete.id_consulta}"></c:out></td>
				<td><c:out value="${paquete.id_paquete}"></c:out></td>
				<td><c:out value="${paquete.longitud}"></c:out></td>
				<td><c:out value="${paquete.fechaCaptura}"></c:out></td>
				<td><a href ="login?opcion=captura&id=<c:out value="${paquete.id_captura}"></c:out>"><c:out value="${paquete.id_captura}"></c:out> </a></td>
				<td><c:out value="${paquete.id_servicio}"></c:out></td>

			</tr>
		</c:forEach>
	
	</table>

</body>
</html>
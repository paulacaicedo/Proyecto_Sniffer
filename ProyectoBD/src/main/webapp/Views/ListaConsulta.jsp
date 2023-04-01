<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consultas Usuario</title>
</head>
<body>

<div style="text-align:center;">
	<h1>Listar Consulta</h1>
	
	<table border="1" style="width:100%">
	
		<tr>
			<td>ID CONSULTA</td>
			<td>ID FECHA</td>
			<td>TIEMPO CONSULTA</td>
			<td>ID USUARIO</td>
			<td>ACCION </td>
			 
		</tr>
	
		<c:forEach var="consulta" items="${listaConsulta}"> 
		<tr>
			<td><a href ="login?opcion=paquete&id=<c:out value="${consulta.id_consulta}"></c:out>"><c:out value="${consulta.id_consulta}"></c:out> </a></td>
			<td><c:out value="${consulta.fecha_inicio}"></c:out></td>
			<td><c:out value="${consulta.tiempoConsulta}"></c:out></td>
			<td><c:out value="${consulta.id_usuario}"></c:out></td>
			<td><a href ="login?opcion=eliminar&id=<c:out value="${consulta.id_consulta}"></c:out>"> Eliminar </a></td>
			 
		</tr>
	</c:forEach>
	
	</table>
</div> 
</body>
</html>
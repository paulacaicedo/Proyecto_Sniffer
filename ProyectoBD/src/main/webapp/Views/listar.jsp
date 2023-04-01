<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Usuarios</title>
</head>
<body>


<div style="text-align:center;">
	<h1>Gestionar Usuario</h1>
	
	<table border="1">
	
		<tr>
			<td>ID USUARIO</td>
			<td>CONTRSEÑA</td>
			<td>Accion</td>
		</tr>
	
	<c:forEach var="usuario" items="${lista}"> 
		<tr>
			<td> <a href ="usuario?opcion=meditar&id=<c:out value="${usuario.id_usuario}"></c:out>"> <c:out value="${usuario.id_usuario}"></c:out> </a></td>
			<td><c:out value="${usuario.contraseña}"></c:out></td>
			<td><a href ="usuario?opcion=eliminar&id=<c:out value="${usuario.id_usuario}"></c:out>"> Eliminar </a></td>
		</tr>
	</c:forEach>
	
	</table>

</div> 
</body>
</html>
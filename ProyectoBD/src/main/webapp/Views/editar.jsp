<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Usuario</title>
</head>
<body>

<h1>Editar Usuario</h1>

<form action="usuario" method="post">
<c:set var="usuario" value="${usuario}"> </c:set>
	<input type="hidden" name="opcion" value="editar">
	<input type="hidden" name="id_usuario" value="${usuario.id_usuario}">
	
		<table border= "1">
			<tr>
				<td>Contraseña:</td>
				<td> <input type="text" name="contraseña" size="50" value="${usuario.contraseña}"> </td>
			</tr>
	
		</table>
		<input type="submit" value="Guardar">
	</form>

</body>
</html>
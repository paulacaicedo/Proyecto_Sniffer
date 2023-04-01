<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Realizar Consulta</title>
</head>
<body>


	<h1> REALIZAR LA CONSULTA</h1>
	
	<form action="login" method="post">
	<input type="hidden" name="opcion" value="consulta">
	
		<table border= "1" style="width:100%">
			<tr>
				<td>Tiempo de la Consulta en segundos:</td>
				<td> <input type="text" name="tiempo_consulta" size="50"> </td>
			</tr>
	
		</table>
		<input type="submit" value="Realizar Consulta">
	</form>


</body>
</html>
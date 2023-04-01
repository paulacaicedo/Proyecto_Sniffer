<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
  .boton_personalizado{
    text-decoration: none;
    padding: 10px;
    font-weight: 600;
    font-size: 20px;
    color: #ffffff;
    background-color: #1883ba;
    border-radius: 6px;
    border: 2px solid #0016b0;
  }
  .boton_personalizado:hover{
    color: #1883ba;
    background-color: #ffffff;
  }
</style>
<meta charset="ISO-8859-1">
<title>Acciones de Usuario</title>
</head>
<body>
 
<div style="text-align:center;">
<h1> Acciones de Usuario</h1>
	<table border="1" style="width:100%">
			<tr>
			 <td> 
				 <form action="login" class="boton_personalizado">
			 	<a  style="color:black;" href="login?opcion=realizar">Realizar Consulta</a> 
				 </form>
			 </td>
			</tr>
			<tr>
				<td>
					<form action="login" class="boton_personalizado">
						<a style="color:black;" href="login?opcion=listar">Listar Consultas</a>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action="login" class="boton_personalizado">
						<a style="color: black;" href="login?opcion=consultasUsuarios">Listar Consultas de Otos Usuarios</a>
					</form>
				</td>
			</tr>
			
			<tr>
				<td>
					<form action="login" class="boton_personalizado">
						<a style="color: black;" href="login?opcion=estadistica">Analisis de la red </a>
					</form>
				</td>
			</tr>
	
	
		</table>
</div> 
</body>
</html>
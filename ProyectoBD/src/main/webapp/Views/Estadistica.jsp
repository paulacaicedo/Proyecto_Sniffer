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
<title>Estadisticas</title>
</head>
<body>
<div style="text-align:center;">
<h1>Graficos que puede analizar el Usuario</h1>
    <table border="1" style="width:100%">

            <tr style="width: 130%; height: 150%;">
                <td>
                    <form action="login" class="boton_personalizado">
                        <a style="color:black;" href="login?opcion=estadistica1">El Servicio que más se utiliza en la Red</a>
                    </form>
                </td>
            </tr>

            <tr style="width: 130%; height: 150%;">
                <td>
                    <form action="login" class="boton_personalizado">
                        <a style="color: black;" href="login?opcion=estadistica2">Dispositivo que consume más servicios</a>
                    </form>
                </td>
            </tr>

            <tr>
                <td>
                    <form action="login" class="boton_personalizado">
                        <a style="color: black;" href="login?opcion=estadistica3">Direccion IP de destino que llegan mas solicitudes</a> 
                    </form>
                </td>
            </tr>
            
            <tr>
                <td>
                    <form action="login" class="boton_personalizado">
                        <a style="color: black;" href="login?opcion=estadistica4">Consumo de ancho de banda en el tiempo</a> 
                    </form>
                </td>
            </tr>


        </table>
</div> 
</body>
</html>
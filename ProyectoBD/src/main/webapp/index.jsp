
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">

*{
	margin: 0px;
	padding: 0px;
}

#contenedor1
{
	background: #f9f7f7;
	width: 460px;
	height: 370px;
	margin: auto;
	margin-top: 100px;
}

#form1
{
	width: 100%;
	padding: 50px 20px 10px 20px;
	box-sizing: border-box;
}

#contenedor1 h1
{
	text-align: center;
	padding-top: 20px;
	color: #566573;
	font-size: 45px;
}

#form1 input[type="text"],
#form1 input[type="password"]
{
	border: none;
	background: none;
	width: 100%;
	height: 40px;
	font-size: 15px;
	font-weight: bold;
	text-align: center;
}

#form1 input[type="submit"]
{
	width: 100%;
	height: 60px;
	background: #1b4f72;
	color: #ffffff;
	cursor: pointer;
	border: 2px solid #ffffff;
	font-size: 25px;
	font-weight: 900;
}

#form1 hr
{
	margin-bottom: 40px;
	color: #aed6f1;
}

#contenedor2
{
	background: #f9f7f7;
	width: 460px;
	height: 140px;
	margin: auto;
	margin-top: 10px;
}

#form2
{
	width: 100%;
	padding: 23px 20px 0px 20px;
	box-sizing: border-box;
}

#form2 input[type="submit"]
{
	width: 100%;
	height: 60px;
	background: #ffffff;
	border: 2px solid #ffffff;
	cursor: pointer;
	font-size: 25px;
	color: #1b4f72;
}

#form2 input[type="submit"]:hover
{
	background: #3498db;
	color: #ffffff;
}


</style>

<meta charset="ISO-8859-1">
<title>Bienvenido</title>
</head>
<body>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Form Login</title>
		 
	</head>
	<body>
		<div id="contenedor1">
			<h1>Dashboard Para Analizar la Red</h1>
			<form id="form1" action="login" method="post">
			<input type="hidden" name="opcion" value="login">
				<input type="text" name="id_usuario" size="50" placeholder="Usuario"/>
				<hr>
				<input type="password" name="contraseña" size="50" placeholder="Contraseña"/>
				<hr>
				<input type="submit" value="Entrar"/>
			</form>
		</div>
		<div id="contenedor2">
			<form id="form2"  action="usuario">
				<input type="submit" name="opcion" value="Crear Usuario"/>
			</form>
		</div>
	</body>

</body>
</html>
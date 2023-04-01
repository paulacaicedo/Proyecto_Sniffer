package com.validacion;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Validacion implements IValidacion {
	
	public Validacion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isNumber(String N) {
	
		try {
			 Integer.parseInt(N);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean hasBlank(String s) {
		if(s.length()==0)return true;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == ' ')
				return true;

		return false;
	}

	@Override
	public void sendError(Errores error, PrintWriter out, HttpServletResponse response) {
		
		switch(error) {
		case ERROR_LOGIN: 
			response.setHeader("Refresh", "0; http://localhost:8080/ProyectoBD/index.jsp");
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Usuario o contraseña incorrectos.');");
			out.println("</script>");
			break;
		case ERROR_EXCEPCION_NUMERO:
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Valor Ingresado Incorrecto. Ingrese Uno Valido');");
			out.println("</script>");
			break;
		case ERROR_CARACTER_VACIO:
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No se es permitido espacios en blanco. Intente de nuevo.');");
			out.println("</script>");
			break;
		}
		 
		
	}

}

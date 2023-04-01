package com.validacion;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public interface IValidacion {
	
	
	public boolean isNumber(String N );
	
	public boolean hasBlank(String s);
	
	public void sendError(Errores r, PrintWriter out,  HttpServletResponse response);
	
	
}

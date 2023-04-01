package com.sniffer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Iterator;

import com.dao.CapturaDAO;
import com.dao.ConsultaDAO;
import com.model.Captura;
import com.model.Consulta;

public class prueba {
	
	 
	
	public static void main(String[] args) throws SQLException {
		
		
		long t = System.currentTimeMillis();
		Consulta con = new Consulta();
		ConsultaDAO c = new ConsultaDAO();
		
		try {
			java.sql.Date z = new java.sql.Date(t);
			
			con.setFecha_inicio(z);
			con.setTiempoConsulta(5 );
			con.setId_usuario(1);
			
			c.guardar(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	  
	 
		Sniffer s = new Sniffer();
		
		//CapturaDAO cp = new CapturaDAO();
	    //System.out.println(cp.getLastID());
		
		s.sniff(20000);
		
		
	} 
	
	
		
		
		
 

}

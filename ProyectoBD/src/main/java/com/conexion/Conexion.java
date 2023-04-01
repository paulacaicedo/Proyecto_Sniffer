package com.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Conexion {
	
	private static BasicDataSource dataSource = null;
	
	private static DataSource getDataSource() {
		
		if(dataSource == null) {
			
			//PARAMETROS DEL POOL DE CONEXIONES
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			dataSource.setUrl("jdbc:mysql://localhost:3306/proyectobd?serverTimezone=UTC");
			
			//CUANTAS CONEXIONES VAMOS A INICIAR EL POOL
			dataSource.setInitialSize(500);
			//CUANTAS CONEXIONES PUEDEN ESTAR ACTIVAS
			dataSource.setMaxIdle(100);
			//TOTAL DE CONEXIONES QUE PUEDEN HABER
			dataSource.setMaxTotal(120);
			//CUANTO DEBE DE ESPERAR SI LA CONEXION ESTA INACTIVA
			dataSource.setMaxWaitMillis(30000);
			dataSource.setRemoveAbandonedOnBorrow(true);
			dataSource.setRemoveAbandonedOnMaintenance(true);
			dataSource.setRemoveAbandonedTimeout(2);
			dataSource.setLogAbandoned(true);
			
			
		}
		
		return dataSource;
	}


	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
		
	}


}

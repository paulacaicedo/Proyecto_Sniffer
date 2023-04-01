package com.estadisticas;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.conexion.Conexion;

public class estadistica {
	
	
	public estadistica() {
		 
	}
	
	//OBTENER CONEXION
		private static Connection obtenerConexion() throws SQLException {
			return Conexion.getConnection();
			
		}
		
		
		public void graficoTorta(int id_usuario) {
			
			Connection connection;
			PreparedStatement statement;

			//response.setContentType("image/PNG");
			//OutputStream out = response.getOutputStream();
			
			int HTTP = 0;
			int FTP = 0;
			int DNS = 0;
			int SMTP = 0;
			int POP3 = 0;
			int DHCP = 0;
			int HTTPS = 0;
			int FTPS = 0;
			int Otro = 0;
			
			ResultSet resultSet = null;
			String sql = null;
			
			try {
				connection = obtenerConexion();
				sql = "SELECT USUARIO_id_usuario , p.id_consulta , p.id_servicio \r\n"
						+ "FROM paquete p ,consulta c , usuario u\r\n"
						+ "WHERE p.id_consulta = c.id_consulta\r\n"
						+ "AND USUARIO_id_usuario=?";
				
				statement = connection.prepareStatement(sql);
				statement.setInt(1, 1);
				
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					int servicio = resultSet.getInt(3);
					
					if(servicio==1) HTTP++; 
					if(servicio==2) FTP++;
					if(servicio==3) DNS++;
					if(servicio==4) SMTP++;
					if(servicio==5) POP3++;
					if(servicio==6) DHCP++;
					if(servicio==7) HTTPS++;
					if(servicio==8) FTPS++;
					if(servicio==9) Otro++;
					
				}
				
				
				
				statement.close();
				resultSet.close();
				connection.close();
				
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			

			DefaultPieDataset data = new DefaultPieDataset();
			data.setValue("HTTP: " + String.valueOf(HTTP), HTTP);
			data.setValue("FTP: " + String.valueOf(FTP), FTP);
			data.setValue("DNS: " + String.valueOf(DNS), DNS);
			data.setValue("SMTP: " + String.valueOf(SMTP), SMTP);
			data.setValue("POP3: " + String.valueOf(POP3), POP3);
			data.setValue("DHCP: " + String.valueOf(DHCP), DHCP);
			data.setValue("HTTPS: " + String.valueOf(HTTPS), HTTPS);
			data.setValue("FTPS: " + String.valueOf(FTPS), FTPS);
			data.setValue("Otro: " + String.valueOf(Otro), Otro);
			
			
			
			JFreeChart chart = ChartFactory.createPieChart3D("Servicio que mas consume", data, true,true,true);
			
			
			ChartPanel panel = new ChartPanel(chart);
			 
			
			JFrame ventana = new JFrame("");
			ventana.setVisible(true);
			ventana.setSize(800,600);
			
			ventana.add(panel);

	}
	 
			
	 
		
		
		

	 
	       
	public static void main(String[] args) {
		
	}
		
		  

}

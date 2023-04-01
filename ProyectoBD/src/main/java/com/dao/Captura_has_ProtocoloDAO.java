package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Captura_has_Protocolo;
import com.model.Servicio;
 
public class Captura_has_ProtocoloDAO {
	
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	//METODO GUARDAR
		public boolean guardar(Captura_has_Protocolo cp) throws SQLException {
				
				String sql = null;
				estadoOperacion = false;
				connection = obtenerConexion();
				
				try {
					connection.setAutoCommit(false);
					sql = "INSERT INTO captura_has_protocolo (CAPTURA_id_captura, CAPTURA_Servicio_id_servicio,PROTOCOLO_id_protocolo) VALUES(?,?,?)";
					statement = connection.prepareStatement(sql);
					
					statement.setInt(1, cp.getId_captura());
					statement.setInt(2, cp.getId_servicio());
					statement.setInt(3, cp.getId_protocolo());
					
					estadoOperacion = statement.executeUpdate() > 0;
					
					connection.commit();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					
					connection.rollback();
					e.printStackTrace();
				}
				
				
				return estadoOperacion;
				
			}
		
		//METODO LISTAR 
		public List<Captura_has_Protocolo> obtener() throws SQLException {
				
				ResultSet resultSet = null;
				List<Captura_has_Protocolo> listaCP = new ArrayList<>();
				
				
				String sql = null;
				estadoOperacion = false;
				connection = obtenerConexion();
				
				try {
					sql = "SELECT * FROM Captura_has_Protocolo";
					statement = connection.prepareStatement(sql);
					resultSet = statement.executeQuery(sql);
					
					while (resultSet.next()) {
						
						
						Captura_has_Protocolo cp = new Captura_has_Protocolo(
								resultSet.getInt(1),
								resultSet.getInt(2),
								resultSet.getInt(3));
						
						listaCP.add(cp);
						
						
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				
				return listaCP;
				
			}
	
		
		//OBTENER CONEXION
		private Connection obtenerConexion() throws SQLException {
			return Conexion.getConnection();
		}
		

}

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Paquete;
 

public class PaqueteDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	//METODO GUARDAR
	public boolean guardar(Paquete paquete) throws SQLException {
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				connection.setAutoCommit(false);
				sql = "INSERT INTO paquete (id_paquete, longitud,fechaCaptura,id_consulta,id_captura,id_servicio) VALUES(?,?,?,?,?,?)";
				statement = connection.prepareStatement(sql);
				
				statement.setString(1, null);
				statement.setInt(2, paquete.getLongitud());
				statement.setString(3, paquete.getFechaCaptura());
				statement.setInt(4, paquete.getId_consulta());
				statement.setInt(5, paquete.getId_captura());
				statement.setInt(6, paquete.getId_servicio());
				
				
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
	
	//METODO ELIMINAR
	public boolean eliminar(int id_paquete) throws SQLException {
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				
				connection.setAutoCommit(false);
				sql = "DELETE FROM paquete WHERE id_paquete=?";
				statement = connection.prepareStatement(sql);
				
				statement.setInt(1, id_paquete);
				
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
	public List<Paquete> obtener(int id_consulta) throws SQLException {
				
				ResultSet resultSet = null;
				List<Paquete> listaPaquete = new ArrayList<>();
				
				
				String sql = null;
				estadoOperacion = false;
				connection = obtenerConexion();
				
				try {
					sql = "SELECT * FROM paquete WHERE id_consulta=?";
					statement = connection.prepareStatement(sql);
					statement.setInt(1, id_consulta);
					
					resultSet = statement.executeQuery();
					
					
					while (resultSet.next()) {
						
						Paquete cap = new Paquete(resultSet.getInt(1),
								resultSet.getInt(2),resultSet.getString(3),
								resultSet.getInt(4),resultSet.getInt(5),
								resultSet.getInt(6));
						
						listaPaquete.add(cap);
						
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
		        }
				
				return listaPaquete;
	}
	
	//METODO OBTENER 
	public Paquete obtenerPaquete(int id_paquete) throws SQLException {
				
				ResultSet resultSet = null;
				Paquete paq = new Paquete();
			
				String sql = null;
				estadoOperacion = false;
				connection = obtenerConexion();
				
				try {
					
					sql = "SELECT * FROM paquete WHERE id_paquete=?";
					statement = connection.prepareStatement(sql);
					statement.setInt(1, id_paquete);
					
					resultSet = statement.executeQuery();
					
					if (resultSet.next()) {
						 
						paq.setId_paquete(resultSet.getInt(1));
						paq.setLongitud(resultSet.getInt(2));
						paq.setFechaCaptura(resultSet.getString(3));
						paq.setId_consulta(resultSet.getInt(4));
						paq.setId_captura(resultSet.getInt(5));
						paq.setId_servicio(resultSet.getInt(6));
						 
						 
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
				
				return paq;
				
			}
				
	
		
	//OBTENER CONEXION
	private Connection obtenerConexion() throws SQLException {
			return Conexion.getConnection();
			
	}
	

}

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Captura;
import com.model.Servicio;
 


public class ServicioDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	//METODO GUARDAR
	public boolean guardar(Servicio servicio) throws SQLException {
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				connection.setAutoCommit(false);
				sql = "INSERT INTO servicio (id_servicio, descripcion) VALUES(?,?)";
				statement = connection.prepareStatement(sql);
				
				statement.setString(1, null);
				statement.setString(2, servicio.getDescripcion());
				
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
	public List<Servicio> obtener() throws SQLException {
			
			ResultSet resultSet = null;
			List<Servicio> listaServicio = new ArrayList<>();
			
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				sql = "SELECT * FROM servicio";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					
					Servicio ser = new Servicio();
					ser.setId_servicio(resultSet.getInt(1));;
					ser.setDescripcion(resultSet.getString(2));;
					
					listaServicio.add(ser);
					
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			
			return listaServicio;
			
		}
	
	//METODO OBTENER 
	public Servicio obtenerServicio(String descripcion) throws SQLException {
				
				ResultSet resultSet = null;
				Servicio ser = new Servicio();
			
				String sql = null;
				estadoOperacion = false;
				connection = obtenerConexion();
				
				try {
					sql = "SELECT * FROM servicio WHERE descripcion=?";
					statement = connection.prepareStatement(sql);
					statement.setString(1, descripcion);
					
					resultSet = statement.executeQuery();
					
					if (resultSet.next()) {
						 
						ser.setId_servicio(resultSet.getInt(1));
						ser.setDescripcion(resultSet.getString(2));
						 
						 
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
				
				return ser;
				
			}

	//OBTENER CONEXION
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
	
	

}

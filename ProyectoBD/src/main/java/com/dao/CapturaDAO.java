package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Captura;

public class CapturaDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	//METODO GUARDAR 
	public boolean guardar(Captura captura) throws SQLException {
		
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			sql = "INSERT INTO captura (id_captura,direccionFisica,DireccionIp_source,direccionIp_destino,puertoDestino,id_servicio) VALUES (?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, null);
			statement.setString(2, captura.getDireccionFisica());
			statement.setString(3,captura.getDireccionIp_destino());
			statement.setString(4,captura.getDireccionIp_source());
			statement.setString(5,captura.getPuertoDestino());
			statement.setInt(6, captura.getId_servicio());
			
			estadoOperacion = statement.executeUpdate() > 0;
			
			connection.commit();
			statement.close();
			connection.close();

			
		} catch (Exception e) {
			
			connection.rollback();
			e.printStackTrace();
		}
			
		
		return estadoOperacion;
		
	}
	
	//METODO ELIMINAR
	public boolean eliminar(int id_captura) throws SQLException {
		
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			sql = "DELETE FROM captura WHERE id_captura=?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id_captura);
			
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
		public List<Captura> obtener() throws SQLException {
			
			ResultSet resultSet = null;
			List<Captura> listaCaptura = new ArrayList<>();
			
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				sql = "SELECT * FROM captura";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery(sql);
				
				
				while (resultSet.next()) {
					
					Captura cap = new Captura(resultSet.getInt(1),
							resultSet.getString(2),resultSet.getString(3),
							resultSet.getString(4),resultSet.getString(5),
							resultSet.getInt(6));
					
					listaCaptura.add(cap);
					
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			
			return listaCaptura;
			
		}
		
		public int getLastID() throws SQLException {
			
			ResultSet resultSet = null;
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			sql = "SELECT MAX(id_captura) FROM captura";
			statement = connection.prepareStatement(sql);
			
			resultSet = statement.executeQuery();
			
			return (resultSet.next()) ? resultSet.getInt(1) : 0;
		}
		
		//METODO OBTENER 
		public Captura obtenerCaptura(int id_captura) throws SQLException {
			
			ResultSet resultSet = null;
			Captura cap = new Captura();
		
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				sql = "SELECT * FROM captura WHERE id_captura=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, id_captura);
				
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					 
					cap.setId_captura(resultSet.getInt(1));
					cap.setDireccionFisica(resultSet.getString(2));
					cap.setDireccionIp_source(resultSet.getString(3));
					cap.setDireccionIp_destino(resultSet.getString(4));
					cap.setPuertoDestino(resultSet.getString(5));
					cap.setId_servicio(resultSet.getInt(6));
					 
					 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
			
			return cap;
			
		}
		
		
	
		
	
	
	//OBTENER CONEXION
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
		
	}
	
	
	

}

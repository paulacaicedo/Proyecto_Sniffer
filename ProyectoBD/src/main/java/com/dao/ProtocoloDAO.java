package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Protocolo;
import com.model.Servicio;

public class ProtocoloDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	//METODO GUARDAR
	public boolean guardar(Protocolo protocolo) throws SQLException {
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				connection.setAutoCommit(false);
				sql = "INSERT INTO protocolo (id_captura, descrpcion) VALUES(?,?)";
				statement = connection.prepareStatement(sql);
				
				statement.setString(1,null);
				statement.setString(2, protocolo.getDescripcion());
				
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
	public List<Protocolo> obtener() throws SQLException {
			
			ResultSet resultSet = null;
			List<Protocolo> listaProtocolo = new ArrayList<>();
			
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				sql = "SELECT * FROM protocolo";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					
					Protocolo pro = new Protocolo();
					pro.setId_protocolo(resultSet.getInt(1));;
					pro.setDescripcion(resultSet.getString(2));
					
					listaProtocolo.add(pro);
					
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			
			return listaProtocolo;
			
		}
		
	//METODO OBTENER 
	public Protocolo obtenerProtocolo(String descripcion) throws SQLException {
				
				ResultSet resultSet = null;
				Protocolo p = new Protocolo();
			
				String sql = null;
				estadoOperacion = false;
				connection = obtenerConexion();
				
				try {
					sql = "SELECT * FROM protocolo WHERE descripcion=?";
					statement = connection.prepareStatement(sql);
					statement.setString(1, descripcion);
					
					resultSet = statement.executeQuery();
					
					if (resultSet.next()) {
						 
						p.setId_protocolo(resultSet.getInt(1));
						p.setDescripcion(resultSet.getString(2));
						 
						 
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
				
				return p;
				
			}
		
	//OBTENER CONEXION
	private Connection obtenerConexion() throws SQLException {
			return Conexion.getConnection();
			
	}
	
	

}

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Consulta;
 

public class ConsultaDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	public int getLastID() throws SQLException {
		
		ResultSet resultSet = null;
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		sql = "SELECT MAX(id_consulta) FROM consulta";
		statement = connection.prepareStatement(sql);
		
		resultSet = statement.executeQuery();
		
		return (resultSet.next()) ? resultSet.getInt(1) : 0;
	}

	
	//METODO GUARDAR
	public boolean guardar(Consulta consulta) throws SQLException {
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				connection.setAutoCommit(false);
				sql = "INSERT INTO consulta (id_consulta, fecha_inicio,tiempoConsulta,USUARIO_id_usuario) VALUES(?,?,?,?)";
				statement = connection.prepareStatement(sql);
				
				statement.setString(1, null);
				statement.setDate(2,consulta.getFecha_inicio());
				statement.setInt(3, consulta.getTiempoConsulta());
				statement.setInt(4,consulta.getId_usuario());
				
				
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
	public boolean eliminar(int id_consulta) throws SQLException{
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				
				connection.setAutoCommit(false);
				sql = "DELETE FROM consulta WHERE id_consulta=?";
				statement = connection.prepareStatement(sql);
				
				statement.setInt(1, id_consulta);
				
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
		
	//METODO  LISTAR
	public List<Consulta> obtener(int id_usuario) throws SQLException {
			
			ResultSet resultSet = null;
			List<Consulta> listaConsulta = new ArrayList<>();
			
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				sql = "SELECT * FROM consulta WHERE USUARIO_id_usuario=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, id_usuario);
				
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					
					Consulta con = new Consulta();
					con.setId_consulta(resultSet.getInt(1));
					con.setFecha_inicio(resultSet.getDate(2));
					con.setTiempoConsulta(resultSet.getInt(3));
					con.setId_usuario(resultSet.getInt(4));
				 
					
					listaConsulta.add(con);
					
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return listaConsulta;
	}
	
	//METODO  LISTAR
	public List<Consulta> obtener() throws SQLException {
			
			ResultSet resultSet = null;
			List<Consulta> listaConsulta = new ArrayList<>();
			
			
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				sql = "SELECT * FROM consulta";
				statement = connection.prepareStatement(sql);
				
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					
					Consulta con = new Consulta();
					con.setId_consulta(resultSet.getInt(1));
					con.setFecha_inicio(resultSet.getDate(2));
					con.setTiempoConsulta(resultSet.getInt(3));
					con.setId_usuario(resultSet.getInt(4));
				 
					
					listaConsulta.add(con);
					
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return listaConsulta;
	}
	
	
	//METODO OBTENER 
	public Consulta obtenerConsulta(int id_consulta) throws SQLException {
			
			ResultSet resultSet = null;
			Consulta con = new Consulta();
		
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				sql = "SELECT * FROM consulta WHERE id_consulta=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, id_consulta);
				
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					con.setId_consulta(resultSet.getInt(1));
					con.setFecha_inicio(resultSet.getDate(2));
					con.setTiempoConsulta(resultSet.getInt(3));
				 	 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
			
			return con;
			
		}
		
		//OBTENER CONEXION
		private Connection obtenerConexion() throws SQLException {
			return Conexion.getConnection();
			
		}
		

}

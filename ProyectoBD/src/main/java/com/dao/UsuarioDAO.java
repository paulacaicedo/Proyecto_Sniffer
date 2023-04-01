package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Usuario;

public class UsuarioDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	
	//METODO LOGIN
	public boolean loginValidate(int user, String pass) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			
			sql = "select * from usuario where id_usuario=? and contraseña=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1,user);
			statement.setString(2, pass);
			
			ResultSet resultSet = statement.executeQuery();
			estadoOperacion = resultSet.next();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return estadoOperacion;
	}
	
	//METODO LOGIN TIPO
		public boolean loginTipo(Usuario user) throws SQLException {
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			
			try {
				
				sql = "select * from usuario where id_usuario=? and contraseña=? and TIPO_USUARIO_id_tipoUsuario=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1,user.getId_usuario());
				statement.setString(2,user.getContraseña());
				statement.setInt(3,user.getTipo_usuario());
				
				
				ResultSet resultSet = statement.executeQuery();
				estadoOperacion = resultSet.next();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			return estadoOperacion;
		}
	
	//METODO GUARDAR
	public boolean guardar(Usuario usuario) throws SQLException {
		
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO usuario (id_usuario, contraseña,TIPO_USUARIO_id_tipoUsuario) VALUES(?,?,?)";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, usuario.getId_usuario());
			statement.setString(2, usuario.getContraseña());
			statement.setInt(3, usuario.getTipo_usuario());
			
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
	
	//METODO EDITAR
	public boolean editar(int user, String pass) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE usuario SET contraseña=? WHERE id_usuario=?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, pass);
			statement.setInt(2, user);
			
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
	public boolean eliminar(int idusuario) throws SQLException{
		
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			sql = "DELETE FROM usuario WHERE id_usuario=?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idusuario);
			
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
	public List<Usuario> obtener() throws SQLException {
		
		ResultSet resultSet = null;
		List<Usuario> listaUsuarios = new ArrayList<>();
		
		
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			sql = "SELECT * FROM usuario";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				Usuario usu = new Usuario();
				usu.setId_usuario(resultSet.getInt(1));
				usu.setContraseña(resultSet.getString(2));
				usu.setTipo_usuario(resultSet.getInt(3));
				
				listaUsuarios.add(usu);
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		return listaUsuarios;
		
	}
	
	
	//METODO OBTENER 
	public Usuario obtenerUsuario(int idusuario) throws SQLException {
		
		ResultSet resultSet = null;
		Usuario usu = new Usuario();
	
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			sql = "SELECT * FROM usuario WHERE id_usuario=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idusuario);
			
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				 
				usu.setId_usuario(resultSet.getInt(1));
				usu.setContraseña(resultSet.getString(2));
				usu.setTipo_usuario(resultSet.getInt(3));
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
		return usu;
		
	}
	
	//OBTENER CONEXION
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
		
	}
	
	

}

package com.model;

public class Usuario {
	
	private int id_usuario;
	private String contraseña;
	private int tipo_usuario;
	
	
	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public Usuario(int id_usuario, String contraseña, int tipo_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.contraseña = contraseña;
		this.tipo_usuario = tipo_usuario;
	}
	
	public Usuario() {
		 
	}
	
	

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", contraseña=" + contraseña + "]";
	}
	
	
	
	

}

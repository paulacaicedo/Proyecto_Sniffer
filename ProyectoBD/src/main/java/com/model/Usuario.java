package com.model;

public class Usuario {
	
	private int id_usuario;
	private String contrase�a;
	private int tipo_usuario;
	
	
	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public Usuario(int id_usuario, String contrase�a, int tipo_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", contrase�a=" + contrase�a + "]";
	}
	
	
	
	

}

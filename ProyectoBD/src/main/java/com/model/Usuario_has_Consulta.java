package com.model;

public class Usuario_has_Consulta {
	
	private Usuario id_usuario;
	private Consulta id_consulta;
	
	public Usuario_has_Consulta() {
		// TODO Auto-generated constructor stub
	}

	public Usuario_has_Consulta(Usuario id_usuario, Consulta id_consulta) {
		super();
		this.id_usuario = id_usuario;
		this.id_consulta = id_consulta;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Consulta getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(Consulta id_consulta) {
		this.id_consulta = id_consulta;
	}

	@Override
	public String toString() {
		return "Usuario_has_Consulta [id_usuario=" + id_usuario + ", id_consulta=" + id_consulta + "]";
	}
	
	
	

}

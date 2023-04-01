package com.model;

import java.sql.Date;

public class Consulta {
	
	private int id_consulta;
	private Date fecha_inicio;
	private int tiempoConsulta;
	private int id_usuario;
	
	public Consulta() {
		 
	}
	
	public Consulta(int id_consulta, Date fecha_inicio, int tiempoConsulta,int id_usuario) {
		super();
		this.id_consulta = id_consulta;
		this.fecha_inicio = fecha_inicio;
		this.tiempoConsulta = tiempoConsulta;
		this.id_usuario = id_usuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public int getTiempoConsulta() {
		return tiempoConsulta;
	}

	public void setTiempoConsulta(int tiempoConsulta) {
		this.tiempoConsulta = tiempoConsulta;
	}


	@Override
	public String toString() {
		return "Consulta [id_consulta=" + id_consulta + ", fecha_inicio=" + fecha_inicio + ", tiempoConsulta="
				+ tiempoConsulta +  "]";
	}
	
	
	
	
	
	
	
	

}

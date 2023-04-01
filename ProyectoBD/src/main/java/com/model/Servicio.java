package com.model;

public class Servicio {
	
	private int id_servicio;
	private String descripcion;
	
	public Servicio() {
		// TODO Auto-generated constructor stub
	}

	public Servicio(int id_servicio, String descripcion) {
		super();
		this.id_servicio = id_servicio;
		this.descripcion = descripcion;
	}

	public int getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(int id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Servicio [id_servicio=" + id_servicio + ", descripcion=" + descripcion + "]";
	}
	
	
	
	

}

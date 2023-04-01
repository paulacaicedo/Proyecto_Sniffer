package com.model;

public class Protocolo {
	
	private int id_protocolo;
	private String descripcion;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}

	public Protocolo(int id_protocolo, String descripcion) {
		super();
		this.id_protocolo = id_protocolo;
		this.descripcion = descripcion;
	}

	public int getId_protocolo() {
		return id_protocolo;
	}

	public void setId_protocolo(int id_protocolo) {
		this.id_protocolo = id_protocolo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Protocolo [id_protocolo=" + id_protocolo + ", descripcion=" + descripcion + "]";
	}
	
	

}

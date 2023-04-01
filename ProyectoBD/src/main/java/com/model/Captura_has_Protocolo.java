package com.model;

public class Captura_has_Protocolo {
	
	private int id_captura;
	private int id_servicio;
	private int id_protocolo;
	
	public Captura_has_Protocolo() {
		// TODO Auto-generated constructor stub
	}

	public Captura_has_Protocolo(int id_captura, int id_servicio, int id_protocolo) {
		super();
		this.id_captura = id_captura;
		this.id_servicio = id_servicio;
		this.id_protocolo = id_protocolo;
	}

	public int getId_captura() {
		return id_captura;
	}

	public void setId_captura(int id_captura) {
		this.id_captura = id_captura;
	}

	public int getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(int id_servicio) {
		this.id_servicio = id_servicio;
	}

	public int getId_protocolo() {
		return id_protocolo;
	}

	public void setId_protocolo(int id_protocolo) {
		this.id_protocolo = id_protocolo;
	}

	@Override
	public String toString() {
		return "Captura_has_Protocolo [id_captura=" + id_captura + ", id_servicio=" + id_servicio + "]";
	}
	
	
	

}

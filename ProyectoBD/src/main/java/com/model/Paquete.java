package com.model;

import java.sql.Date;

public class Paquete {
	
	private int id_paquete;
	private int longitud;
	private String fechaCaptura;
	private int id_consulta;
	private int id_captura;
	private int id_servicio;
	
	
	public Paquete() {
		 
	}


	public Paquete(int id_paquete, int longitud, String fechaCaptura, int id_consulta, int id_captura,
			int id_servicio) {
		super();
		this.id_paquete = id_paquete;
		this.longitud = longitud;
		this.fechaCaptura = fechaCaptura;
		this.id_consulta = id_consulta;
		this.id_captura = id_captura;
		this.id_servicio = id_servicio;
	}


	public int getId_paquete() {
		return id_paquete;
	}


	public void setId_paquete(int id_paquete) {
		this.id_paquete = id_paquete;
	}


	public int getLongitud() {
		return longitud;
	}


	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}


	public String getFechaCaptura() {
		return fechaCaptura;
	}


	public void setFechaCaptura(String t) {
		this.fechaCaptura = t;
	}


	public int getId_consulta() {
		return id_consulta;
	}


	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
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


	@Override
	public String toString() {
		return "Paquete [id_paquete=" + id_paquete + ", longitud=" + longitud + ", fechaCaptura=" + fechaCaptura
				+ ", id_consulta=" + id_consulta + ", id_captura=" + id_captura + ", id_servicio=" + id_servicio + "]";
	}
	
	



}

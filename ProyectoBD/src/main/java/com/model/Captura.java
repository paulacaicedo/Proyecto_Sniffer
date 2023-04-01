package com.model;

public class Captura {
	
	private int id_captura;
	private String direccionFisica;
	private String direccionIp_source;
	private String direccionIp_destino;
	private String puertoDestino;
	private int id_servicio;

	
	
	public Captura() {
	}

	public Captura(int id_captura, String direccionFisica, String direccionIp_source, String direccionIp_destino,
			String puertoDestino, int id_servicio) {
		super();
		this.id_captura = id_captura;
		this.direccionFisica = direccionFisica;
		this.direccionIp_source = direccionIp_source;
		this.direccionIp_destino = direccionIp_destino;
		this.puertoDestino = puertoDestino;
		this.id_servicio = id_servicio;
	}

	public int getId_captura() {
		return id_captura;
	}

	public void setId_captura(int id_captura) {
		this.id_captura = id_captura;
	}

	public String getDireccionFisica() {
		return direccionFisica;
	}

	public void setDireccionFisica(String direccionFisica) {
		this.direccionFisica = direccionFisica;
	}

	public String getDireccionIp_source() {
		return direccionIp_source;
	}

	public void setDireccionIp_source(String direccionIp_source) {
		this.direccionIp_source = direccionIp_source;
	}

	public String getDireccionIp_destino() {
		return direccionIp_destino;
	}

	public void setDireccionIp_destino(String direccionIp_destino) {
		this.direccionIp_destino = direccionIp_destino;
	}

	public String getPuertoDestino() {
		return puertoDestino;
	}

	public void setPuertoDestino(String puertoDestino) {
		this.puertoDestino = puertoDestino;
	}

	public int getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(int id_servicio) {
		this.id_servicio = id_servicio;
	}

	@Override
	public String toString() {
		return "Captura [id_captura=" + id_captura + ", direccionFisica=" + direccionFisica + ", direccionIp_source="
				+ direccionIp_source + ", direccionIp_destino=" + direccionIp_destino + ", puertoDestino="
				+ puertoDestino + ", id_servicio=" + id_servicio + "]";
	}

	
	

}

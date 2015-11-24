package com.jetperu.remesa.bean;

import com.google.gson.Gson;

public class RemesaBean {

	private Integer Numero;
	private Integer PIN;

	private double Monto;
	private String Estado;
	private String Fecha;
	private String NombreAgencia;
	private String NombreMoneda;

	public Integer getPIN() {
		return PIN;
	}

	public void setPIN(Integer pIN) {
		PIN = pIN;
	}
	
	public Integer getNumero() {
		return Numero;
	}

	public void setNumero(Integer numero) {
		Numero = numero;
	}

	public double getMonto() {
		return Monto;
	}

	public void setMonto(double monto) {
		Monto = monto;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getNombreAgencia() {
		return NombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		NombreAgencia = nombreAgencia;
	}

	public String getNombreMoneda() {
		return NombreMoneda;
	}

	public void setNombreMoneda(String nombreMoneda) {
		NombreMoneda = nombreMoneda;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}

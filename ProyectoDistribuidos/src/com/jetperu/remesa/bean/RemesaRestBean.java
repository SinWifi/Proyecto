package com.jetperu.remesa.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemesaRestBean {
	
	private AgenciaBean Agencia;
	private String Estado;
	private String Fecha;
	private MonedaBean Moneda;
	private double Monto;
	private int Numero;
	private int PIN;
	private String NombreAgencia;
	private String NombreMoneda;
	
	public AgenciaBean getAgencia() {
		return Agencia;
	}
	public void setAgencia(AgenciaBean agencia) {
		Agencia = agencia;
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
	public MonedaBean getMoneda() {
		return Moneda;
	}
	public void setMoneda(MonedaBean moneda) {
		Moneda = moneda;
	}
	public double getMonto() {
		return Monto;
	}
	public void setMonto(double monto) {
		Monto = monto;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public int getPIN() {
		return PIN;
	}
	public void setPIN(int pIN) {
		PIN = pIN;
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

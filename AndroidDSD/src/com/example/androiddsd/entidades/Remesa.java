package com.example.androiddsd.entidades;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Remesa implements Serializable{
		
	private static final long serialVersionUID = -8107086530049997467L;
	
	public static final String NUMERO = "Numero";
	public static final String PIN = "PIN";
	public static final String FECHA = "Fecha";
	public static final String AGENCIA = "Agencia";
	public static final String MONEDA = "Moneda";
	public static final String MONTO = "Monto";
	public static final String ESTADO = "Estado";
		
	@Expose
	@SerializedName(NUMERO)
	private int numero;
	
	@Expose
	@SerializedName(PIN)
	private int pin;
	
	@Expose
	@SerializedName(FECHA)
	private String fecha;
	
	@Expose
	@SerializedName(AGENCIA)
	private Agencia agencia;
	
	@Expose
	@SerializedName(MONEDA)
	private Moneda moneda;
	
	@Expose
	@SerializedName(MONTO)
	private double monto;

	@Expose
	@SerializedName(ESTADO)
	private String estado;
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

package com.example.androiddsd.entidades;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Moneda implements Serializable{
		
	private static final long serialVersionUID = -8107086530049997467L;
	
	public static final String ID = "ID";
	public static final String CODIGO = "Codigo";
	public static final String NOMBRE = "Nombre";
	public static final String SIMBOLO = "Simbolo";
		
	@Expose
	@SerializedName(ID)
	private int id;
	
	@Expose
	@SerializedName(CODIGO)
	private String codigo;
	
	@Expose
	@SerializedName(NOMBRE)
	private String nombre;

	@Expose
	@SerializedName(SIMBOLO)
	private String simbolo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
}

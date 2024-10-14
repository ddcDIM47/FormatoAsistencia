package com.dim47.parte;

public class Profesor {
	private String nombreString;
	
	public  Profesor() {
		super();
	}
	
	public Profesor(String name) {
		super();
		this.nombreString = name;
	}

	public String getNombreString() {
		return nombreString;
	}

	public void setNombreString(String nombreString) {
		this.nombreString = nombreString;
	}
	
	
}

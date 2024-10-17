package com.dim47.parte;

public class Asignatura {
	private String nombre;
	private Profesor profesor;
	
	
	public Asignatura() {
		super();
	}
	
	public Asignatura(String name, Profesor profesor) {
		super();
		this.nombre = name;
		this.profesor = profesor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
	
}

package com.dim47.parte;

import java.util.List;

public class Dia {
	
	private String nombre;
	private String fNombre;
	public Hora[] horaArray;
	
	
	
	
	public Dia() {
		horaArray = new Hora[6];
		horaArray[0] = new Hora();
		horaArray[1] = new Hora();
		horaArray[2] = new Hora();
		horaArray[3] = new Hora();
		horaArray[4] = new Hora();
		horaArray[5] = new Hora();
	}
	
	public Dia(String nombre) {
		this();
		this.nombre = nombre;
	}
	
	private void formatearDia() {
		//TODO: cambiar de 2 de septiembre a 02SEP;
		this.setfNombre(this.getNombre());
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getfNombre() {
		return fNombre;
	}

	public void setfNombre(String fNombre) {
		this.fNombre = fNombre;
	}


	public Hora[] getHoras () {
		return horaArray;
	}
	
	public void setHoras (Hora[] array) {
		horaArray = array;
	}

//	public List<Asignatura> getListaAsignaturas() {
//		return listaAsignaturas;
//	}




//	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
//		this.listaAsignaturas = listaAsignaturas;
//	}

	
	
}

package com.dim47.parte;

import java.util.ArrayList;
import java.util.List;

public class Semana {
	private String textoString;
	private List<Asignatura> listaAsignatura;
	
	public void addAsignatura(Asignatura asignatura) {
		this.getListaAsignatura().add(asignatura);
	}
	
	public void removeAsignatura(String name) {
		for (Asignatura asignatura : listaAsignatura) {
			if (asignatura.getNombre() == name) {
				listaAsignatura.remove(asignatura);
				break;
			}
		}
	}
	
	public Semana() {
		super();
	}
	
	public Semana(String textoString) {
		super();
		this.textoString = textoString;
		this.listaAsignatura = new ArrayList<Asignatura>();
	}

	public String getTextoString() {
		return textoString;
	}

	public void setTextoString(String textoString) {
		this.textoString = textoString;
	}

	public List<Asignatura> getListaAsignatura() {
		return listaAsignatura;
	}

	public void setListaAsignatura(List<Asignatura> listaAsignatura) {
		this.listaAsignatura = listaAsignatura;
	}
	
	
	
}

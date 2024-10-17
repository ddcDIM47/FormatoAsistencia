package com.dim47.parte;



public class Semana {
	private String textoString;
	private Dia[] listaDias;
	//private List<Asignatura> listaAsignatura;
	
		
	public Semana() {
		super();
	}
	
	public Semana(String textoString) {
		super();
		this.textoString = textoString;
		this.setListaDias(new Dia[5]);
	}

	public String getTextoString() {
		return textoString;
	}

	public void setTextoString(String textoString) {
		this.textoString = textoString;
	}

	public Dia[] getListaDias() {
		return listaDias;
	}

	public void setListaDias(Dia[] listaDias) {
		this.listaDias = listaDias;
	}

	
}

package com.dim47.parte;



import java.util.List;

import com.dim47.excel.GeneradorExcel;
import com.dim47.gestionHtml.LeerHtml;

public class Inicio {
	public static void main(String[] args) {
		LeerHtml programaHtml = new LeerHtml();
		List<Semana> programaList = null;
		programaList = programaHtml.leerFromUrl("https://web.institutomilitar.com/semanal.html");
		GeneradorExcel generador = new GeneradorExcel();
		generador.crearHoja("Parte_Clase_XLVII_DIM", programaList);
	
	}
}

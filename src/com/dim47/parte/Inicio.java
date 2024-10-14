package com.dim47.parte;



import java.util.List;

import com.dim47.excel.GeneraExcel;
import com.dim47.gestionHtml.LeerHtml;

public class Inicio {
	public static void main(String[] args) {
		LeerHtml programaHtml = new LeerHtml();
		List<Semana> programaList = null;
		programaList = programaHtml.leerFromUrl("https://web.institutomilitar.com/semanal.html");
		GeneraExcel.crearHoja("", programaList);
	
	}
}

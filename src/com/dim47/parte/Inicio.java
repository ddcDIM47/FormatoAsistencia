package com.dim47.parte;



import java.util.List;

import com.dim47.excel.GeneradorExcel;
import com.dim47.gestionHtml.LeerHtml;

public class Inicio {
	public static void main(String[] args) {
		LeerHtml programaHtml = new LeerHtml();
		List<Semana> programas = programaHtml.leerFromUrl(args[0]);
		GeneradorExcel generador = new GeneradorExcel();
		generador.crearExcel("Parte_Clase_XLVII_DIM", programas);
	}
}

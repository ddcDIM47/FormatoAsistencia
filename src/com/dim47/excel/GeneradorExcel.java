package com.dim47.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.dim47.gestionHtml.Utils;
import com.dim47.parte.Asignatura;
import com.dim47.parte.Semana;

public class GeneradorExcel {

	private Workbook hoja;
	private final short TAMANOTITULO = 20;
	private final short TAMANOSUB = 14;
	
	private Font aplicarFuenteTitulo(Workbook hoja) {
		Font font = hoja.createFont();
		
		font.setFontHeightInPoints(TAMANOTITULO);
		font.setBold(true);
		font.setUnderline((byte) 1);
		return font;
	}
	
	private void aplicarBordesTabla(CellStyle estilo) {
		estilo.setBorderBottom(BorderStyle.THIN);
		estilo.setBorderTop(BorderStyle.THIN);
		estilo.setBorderRight(BorderStyle.THIN);
		estilo.setBorderLeft(BorderStyle.THIN);
	}
	
	private void aplicarEstiloTexto(CellStyle style) {
		Font font = hoja.createFont();
		font.setFontHeightInPoints(TAMANOSUB);
		font.setBold(true);
		style.setFont(font);
		
	}
	
	private void aplicarLineasTabla(Sheet sheet, CellStyle estilo, short filaI, short filaF, short columnaI, short ColumnaF) {
		for (short i = filaI; i < filaF; i++) {
			Row row = sheet.getRow(i);
//			filaFusionada(sheet, i, i, 9, ColumnaF);
				if(row == null) {
					row = createRow(sheet, i, estilo);
				}	
					
			for (int j = columnaI; j < ColumnaF; j++) {
				createCelda(row, estilo, j, " ");				
			}
		}
	}
	
	private void fusionarFilas(Sheet sheet, CellStyle estilo, short filaI, short filaF, short columnaI, short columnaF) {
		
		for (short i = filaI; i < filaF; i++) {
			
			filaFusionada(sheet, i, i, columnaI, columnaF);
		}
		
		
	}
	
	private Row creaFila(int indice) {
		return null;
	}
	
	private Row crearCelda() {
		return null;
	}
	
	private void filaFusionada(Sheet sheet, int inicioFila, int finFila, int inicioColumna, int finColumna) {
		
		sheet.addMergedRegion(new CellRangeAddress(inicioFila, finFila, inicioColumna, finColumna));
		
	}
	
	private void centrarTexto(CellStyle cellStyle) {
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
	}
	
	private short creaTitulos(Sheet sheet, short indice) {
		
		Row row = sheet.createRow(indice);
		row.setHeightInPoints(TAMANOTITULO+2);
		Cell celda = row.createCell(indice);
		filaFusionada(sheet, indice, indice, 1, 17);
		celda.setCellValue("DEPARTAMENTO DE SISTEMAS DE INFORMACIÓN Y CIBERDEFENSA DE LA ACING");
		CellStyle cellStyle = hoja.createCellStyle();
		cellStyle.setWrapText(true);
		centrarTexto(cellStyle);
		Font fontTitulo = aplicarFuenteTitulo(hoja);
		cellStyle.setFont(fontTitulo);
		celda.setCellStyle(cellStyle);
		
		indice += 2;
		
		filaFusionada(sheet, indice, indice, 1, 17);
		row = sheet.createRow(indice);
		row.setHeightInPoints(TAMANOTITULO+2);
		celda = row.createCell(1);
		celda.setCellValue("XLVII CURSO DIM");
		celda.setCellStyle(cellStyle);
		sheet.autoSizeColumn(1);
		
		return indice;
		
	}
	
	public Row createRow(Sheet sheet, short index, CellStyle style) {
		
		Row row = sheet.createRow(index);
		return row;
		
	}
	
	public Cell createCelda(Row row, CellStyle style, int index, String valor) {
		Cell celda = row.createCell(index);
		celda.setCellStyle(style);
		//style.setWrapText(true);
		celda.setCellValue(valor);
		return celda;
	}
	
	public short crearCabeceraAlumnos(Sheet sheet, short indice) {
		
		
		Font font1 = hoja.createFont();
		font1.setBold(false);
		font1.setFontHeightInPoints((short) 10);
		CellStyle cellStyle = hoja.createCellStyle();
		centrarTexto(cellStyle);
		cellStyle.setFont(font1);
		
		
		indice +=1;
		Row row = createRow(sheet, indice, cellStyle);
		
		
		filaFusionada(sheet, indice, indice+1, 0, 0);
		createCelda(row, cellStyle, 0, "EMPLEO");
		
		
		filaFusionada(sheet, indice, indice+1, 1, 1);
		createCelda(row, cellStyle, 1, "NOMBRE Y APELLIDOS");
		
		
		filaFusionada(sheet, indice, indice+1, 2, 2);
		createCelda(row, cellStyle, 2, "DÍA");
				
		filaFusionada(sheet,indice, indice, 3, 8);
		createCelda(row, cellStyle, 3, "HORAS");
				
		filaFusionada(sheet,indice, indice+1, 9, 17);
		createCelda(row, cellStyle, 9, "OBSERVACIONES");
				
		
		indice +=1;
		row = sheet.createRow(indice);
		for (int i = 0; i < 17; i++) {
			
			createCelda(row, cellStyle, i, i-2+"ª");
		}
		aplicarBordesTabla(cellStyle);	
		return indice;
	}
	
	
	private void nNombre(List<Asignatura> asignaturas) {
		
	}
	
	private short completarDatos(Sheet sheet, CellStyle cellStyle, Semana semana, short indice ) {
		int i = 0;
		while ( i < semana.getListaDias().length) {
			int index = 0;
			Row row = sheet.createRow(indice);
			createCelda(row, cellStyle, 0, semana.getListaDias()[i].getNombre());
			filaFusionada(sheet, indice, indice, 2, 11);
			
			Asignatura asigActual = semana.getListaDias()[i].horaArray[index].asignatura;
			createCelda(row, cellStyle, 1, asigActual.getNombre());
			
			for (Asignatura asig : semana.getListaAsignatura()) {
				if (Utils.matchString(asig.getNombre(),asigActual.getNombre())) {
					createCelda(row, cellStyle, 2, asig.getProfesor().getNombreString());
				}
			}
			
			aplicarBordesTabla(cellStyle);
			indice++;
			
			
			while((index < 6)) { 
				System.out.println("I: " + i + " index: " + index);
				aplicarBordesTabla(cellStyle);
				createCelda(row, cellStyle, index+12, " ");
				Asignatura as = semana.getListaDias()[i].horaArray[index].asignatura;
				//filaFusionada(sheet, indice+1, indice+1, 2, 11);
				if (as != null) {
					if (Utils.matchString(asigActual.getNombre(),as.getNombre())) {
						
						createCelda(row, cellStyle, index+12, "X");
					}else {
						asigActual = semana.getListaDias()[i].horaArray[index].asignatura;
						row = sheet.createRow(indice);
						createCelda(row, cellStyle, 0, semana.getListaDias()[i].getNombre());
						createCelda(row, cellStyle, 1, asigActual.getNombre());
						createCelda(row, cellStyle, index+12, "X");
						filaFusionada(sheet, indice, indice, 2, 11);
						for (Asignatura asig : semana.getListaAsignatura()) {
							if (Utils.matchString(asig.getNombre(),asigActual.getNombre())) {
								createCelda(row, cellStyle, 2, asig.getProfesor().getNombreString());
							}
						}
						
						indice++;
						
					}
				 }
					 
				 
				
				index++;
			}
			
			i++;
		}
		aplicarBordesTabla(cellStyle);
		return indice;
	}
	
	
	public void crearExcel(String nombre, List<Semana> programa) {
		hoja = new HSSFWorkbook();
		int i = 1;
		for (Semana semana : programa) {
			Sheet sheet = hoja.createSheet("semana" + i++);
			crearHoja("", semana, sheet);
		}
	}
	
	private  void crearHoja(String nombreHoja, Semana programa, Sheet sheet) {
	
		short indiceFila = 1;
				
		for (int i = 0; i <= 17; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.autoSizeColumn(10000);
		
		Row row = sheet.createRow(indiceFila);
		Cell celda = row.createCell(indiceFila);
		
		indiceFila = creaTitulos(sheet, indiceFila);
		CellStyle cellStyles = hoja.createCellStyle();
		centrarTexto(cellStyles);
		aplicarEstiloTexto(cellStyles);
		
		indiceFila +=3;
		filaFusionada(sheet, indiceFila, indiceFila, 1, 17);
		row = sheet.createRow(indiceFila);
		row.setHeightInPoints(TAMANOSUB+2);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		createCelda(row, cellStyles, 1, programa.getTextoString() + " de " + year);
						
		indiceFila +=1;
		filaFusionada(sheet, indiceFila, indiceFila, 1, 17);
		row = sheet.createRow(indiceFila);
		row.setHeightInPoints(TAMANOSUB+2);
		createCelda(row, cellStyles, 1, "FALTAS DE ASISTENCIA AL CURSO");
		
		
		/*------------------------CREAMOS TABLA-----------------------------------------*/
		
		/*-----------------------------Cabecera------*/
				
		indiceFila = crearCabeceraAlumnos(sheet, indiceFila);
		
		/*Poner nombres alumnos*/
		/*-------------------------------------------------------------------*/
		//font.setUnderline((byte) 0);
		//cellStyle.setFont(font);
		CellStyle cellStyle = hoja.createCellStyle();
//		centrarTexto(cellStyle);
		celda.setCellStyle(cellStyle);
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		createCelda(row, cellStyle, 0, "Cap.");
		createCelda(row, cellStyle, 1, "Daniel Sanchez López");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		createCelda(row, cellStyle, 0, "Cap.");
		createCelda(row, cellStyle, 1, "Manuel Ojeda González");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		createCelda(row, cellStyle, 0, "Cap.");
		createCelda(row, cellStyle, 1, "Jorge Ciprés Reula");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		createCelda(row, cellStyle, 0, "Cap.");
		createCelda(row, cellStyle, 1, "Antonio Requena Martínez");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		createCelda(row, cellStyle, 0, "Cap.");
		createCelda(row, cellStyle, 1, "Adolfo Soto Conde");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		createCelda(row, cellStyle, 0, "Cap.");
		createCelda(row, cellStyle, 1, "Manuel De Blas Pino");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		createCelda(row, cellStyle, 0, "Tte.");
		createCelda(row, cellStyle, 1, "Daniel Dominguez Cancela");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		filaFusionada(sheet, indiceFila, indiceFila, 0, 1);
		createCelda(row, cellStyle, 0, "OBSERVACIONES");
		
		
		indiceFila+=1;
		filaFusionada(sheet, indiceFila, indiceFila, 0, 17);
		aplicarBordesTabla(cellStyle);
		aplicarLineasTabla(sheet, cellStyle, (short)10, (short)18, (short)2, (short)18);
		fusionarFilas(sheet, cellStyle, (short)10, (short)17, (short)9, (short)17);
		fusionarFilas(sheet, cellStyle, (short)17, (short)18, (short)2, (short)17);
		
		
		/*------Tabla profesorado--------------------------------------------*/
		CellStyle styleDatos = hoja.createCellStyle();
		centrarTexto(styleDatos);
		
		indiceFila+=4;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		filaFusionada(sheet, indiceFila, indiceFila, 1, 17);
		celda.setCellValue("DISTRIBUCIÓN DEL PROFESORADO");
		celda.setCellStyle(cellStyles);
		indiceFila += 1;
		row = sheet.createRow(indiceFila);
		
		
		filaFusionada(sheet, indiceFila, indiceFila+1, 0, 0);
		createCelda(row, styleDatos, 0, "DÍA");
	
		filaFusionada(sheet, indiceFila, indiceFila+1, 1, 1);
		createCelda(row, styleDatos, 1, "ASIGNATURA");
		
		filaFusionada(sheet, indiceFila, indiceFila+1, 2, 11);
		createCelda(row, styleDatos, 2, "PROFESOR");
		
		filaFusionada(sheet, indiceFila, indiceFila, 12, 17);
		createCelda(row, styleDatos, 12, "HORAS");
		
		indiceFila +=1;
		row = sheet.createRow(indiceFila);
		for (int i = 0; i < 6; i++) {
			createCelda(row, styleDatos, 12+i, 1+i+"ª");
		}
		
		
		aplicarBordesTabla(styleDatos);
		/*Rellenamos los datos*/
		indiceFila++;
		indiceFila = completarDatos(sheet, cellStyle, programa, indiceFila);
		//
		//aplicarLineasTabla(sheet, cellStyle, indiceFila, (short)42, (short)0, (short)18);
		
		/*-------------------------------------------*/
		indiceFila += 15;
		row = sheet.createRow(indiceFila);
		filaFusionada(sheet, indiceFila, indiceFila, 0, 1);
		fusionarFilas(sheet, cellStyle, (short)42, (short)43, (short)2, (short)17);
		createCelda(row, cellStyle, 0, "OBSERVACIONES");
				
		indiceFila += 1;
		row = sheet.createRow(indiceFila);
		filaFusionada(sheet, indiceFila, indiceFila, 0, 17);
		/*-------------------------------------------------------------------*/
		
		sheet.setColumnWidth(1, 10000);

		
		try {
			
		    FileOutputStream out = new FileOutputStream(new File("excel.xlsx"));
		    hoja.write(out);
		    out.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	
}

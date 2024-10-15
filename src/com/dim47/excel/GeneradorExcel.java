package com.dim47.excel;



import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;


import com.dim47.parte.Semana;

public class GeneradorExcel {

	private Workbook hoja;
	private final short TAMANOTITULO = 20;
	
	private Font aplicarFuenteTitulo(Workbook hoja) {
		Font font = hoja.createFont();
		
		font.setFontHeightInPoints(TAMANOTITULO);
		font.setBold(true);
		//font.setUnderline((byte) 1);
		
		return font;
	}
	
	private void aplicarEstiloTexto() {
		
	}
	
	private void aplicarLineasTabla() {
		
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
		cellStyle.setWrapText(true);
		centrarTexto(cellStyle);
		Font fontTitulo = aplicarFuenteTitulo(hoja);
		cellStyle.setFont(fontTitulo);
		celda.setCellStyle(cellStyle);
		
		indice += 2;
		
		sheet.addMergedRegion(new CellRangeAddress(indice, indice, 1, 17));
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
		createCelda(row, cellStyle, 0, "Empleo");
		
		
		filaFusionada(sheet, indice, indice+1, 1, 1);
		createCelda(row, cellStyle, 1, "NOMBRE Y APELLIDOS");
		
		
		
		filaFusionada(sheet, indice, indice+1, 2, 2);
		createCelda(row, cellStyle, 2, "DÍA");
		
		filaFusionada(sheet,indice, indice, 3, 7);
		createCelda(row, cellStyle, 3, "HORA");
				
		filaFusionada(sheet,indice, indice+1, 9, 17);
		createCelda(row, cellStyle, 9, "OBSERVACIONES");
		
		
		indice +=1;
		row = sheet.createRow(indice);
		createCelda(row, cellStyle, 3, "1ª");
		
		createCelda(row, cellStyle, 4, "2ª");
		createCelda(row, cellStyle, 5, "3ª");
		createCelda(row, cellStyle, 6, "4ª");
		createCelda(row, cellStyle, 7, "5ª");
		createCelda(row, cellStyle, 8, "6ª");
		
		
		return indice;
	}
	
	
	public  void crearHoja(String nombreHoja, List<Semana> programa) {
		
		short indiceFila = 1;
		
		hoja = new HSSFWorkbook();
		Sheet sheet = hoja.createSheet("excel-sheet");
		Row row = sheet.createRow(indiceFila);
		Cell celda = row.createCell(indiceFila);
		
		indiceFila = creaTitulos(sheet, indiceFila);
		
		
		CellStyle cellStyle = hoja.createCellStyle();
		
				
		
		
		
		indiceFila +=3;
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 1, 17));
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		celda.setCellValue(programa.get(0).getTextoString() + " de " + year);
		//font.setFontHeightInPoints((short) 14);
		//cellStyle.setFont(font);
		celda.setCellStyle(cellStyle);
		
		
		
		indiceFila +=1;
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 1, 17));
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		celda.setCellValue("FALTAS DE ASISTENCIA AL CURSO");
		//font.setUnderline((byte) 0);
		//cellStyle.setFont(font);
		celda.setCellStyle(cellStyle);
		
		/*------------------------CREAMOS TABLA-----------------------------------------*/
		
		/*-----------------------------Cabecera------*/
				
		indiceFila = crearCabeceraAlumnos(sheet, indiceFila);
		/*Poner nombres alumnos*/
		/*-------------------------------------------------------------------*/
		//font.setUnderline((byte) 0);
		//cellStyle.setFont(font);
		celda.setCellStyle(cellStyle);
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("Cap.");
		celda = row.createCell(1);
		celda.setCellValue("Daniel Sanchez López");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("Cap.");
		celda = row.createCell(1);
		celda.setCellValue("Manuel Ojeda González");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("Cap.");
		celda = row.createCell(1);
		celda.setCellValue("Jorge Ciprés Reula");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("Cap.");
		celda = row.createCell(1);
		celda.setCellValue("Antonio Requena Martínez");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("Cap.");
		celda = row.createCell(1);
		celda.setCellValue("Adolfo Soto Conde");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("Cap.");
		celda = row.createCell(1);
		celda.setCellValue("Manuel De Blas Pino");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("Tte.");
		celda = row.createCell(1);
		celda.setCellValue("Daniel Dominguez Cancela");
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda = row.createCell(0);
		celda.setCellValue("Observaciones");
		
		indiceFila+=1;
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 0, 17));
		
		/*------Tabla profesorado--------------------------------------------*/
		indiceFila+=4;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 1, 17));
		celda.setCellValue("DISTRIBUCIÓN DEL PROFESORADO");
		
		indiceFila += 1;
		row = sheet.createRow(indiceFila);
		
		celda = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila+1, 0, 0));
		celda.setCellValue("DÍA");
		celda = row.createCell(1);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila+1, 1, 1));
		celda.setCellValue("ASIGNATURA");
		celda = row.createCell(2);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila+1, 2, 11));
		celda.setCellValue("PROFESOR");
		celda = row.createCell(12);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 12, 17));
		celda.setCellValue("HORAS");
		
		indiceFila +=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(12);
		celda.setCellValue("1ª");
		
		celda = row.createCell(13);
		celda.setCellValue("2ª");
		
		celda = row.createCell(14);
		celda.setCellValue("3ª");
		
		celda = row.createCell(15);
		celda.setCellValue("4ª");
		
		celda = row.createCell(16);
		celda.setCellValue("5ª");
		
		celda = row.createCell(17);
		celda.setCellValue("6ª");
		/*Rellenamos los datos*/
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		celda.setCellValue(programa.get(0).getListaAsignatura().get(0).getNombre());
		celda = row.createCell(2);
		celda.setCellValue(programa.get(0).getListaAsignatura().get(0).getProfesor().getNombreString());
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		celda.setCellValue(programa.get(0).getListaAsignatura().get(1).getNombre());
		celda = row.createCell(2);
		celda.setCellValue(programa.get(0).getListaAsignatura().get(1).getProfesor().getNombreString());
		
		indiceFila+=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		celda.setCellValue(programa.get(0).getListaAsignatura().get(2).getNombre());
		celda = row.createCell(2);
		celda.setCellValue(programa.get(0).getListaAsignatura().get(2).getProfesor().getNombreString());
		
		
		/*-------------------------------------------*/
		indiceFila += 15;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(0);
		celda.setCellValue("OBSERVACIONES");
		
		indiceFila += 1;
		row = sheet.createRow(indiceFila);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 0, 17));
		/*-------------------------------------------------------------------*/
		

		
		try {
			
		    FileOutputStream out = new FileOutputStream(new File("excel.xlsx"));
		    hoja.write(out);
		    out.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	
}

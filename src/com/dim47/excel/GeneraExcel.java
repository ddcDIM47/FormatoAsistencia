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

public class GeneraExcel {

	
	public static void crearHoja(String nombreHoja, List<Semana> programa) {
		
		short indiceFila = 1;
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("excel-sheet");
		
		Row row = sheet.createRow(1);
		Cell celda = row.createCell(1);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 1, 17));
		celda.setCellValue("DEPARTAMENTO DE SISTEMAS DE INFORMACIÓN Y CIBERDEFENSA DE LA ACING");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 20);
		font.setBold(true);
		font.setUnderline((byte) 1);
		cellStyle.setFont(font);
		celda.setCellStyle(cellStyle);
		
		indiceFila +=2;
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 1, 17));
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		celda.setCellValue("XLVII CURSO DIM");
		celda.setCellStyle(cellStyle);
		
		indiceFila +=3;
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 1, 17));
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		celda.setCellValue(programa.get(0).getTextoString() + " de " + year);
		font.setFontHeightInPoints((short) 14);
		cellStyle.setFont(font);
		celda.setCellStyle(cellStyle);
		
		
		
		indiceFila +=1;
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 1, 17));
		row = sheet.createRow(indiceFila);
		celda = row.createCell(1);
		celda.setCellValue("FALTAS DE ASISTENCIA AL CURSO");
		font.setUnderline((byte) 0);
		cellStyle.setFont(font);
		celda.setCellStyle(cellStyle);
		
		/*------------------------CREAMOS TABLA-----------------------------------------*/
		
		Font font1 = workbook.createFont();
		font1.setBold(false);
		font1.setFontHeightInPoints((short) 12);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setFont(font1);
		
		indiceFila +=1;
		row = sheet.createRow(indiceFila);
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila+1, 0, 0));
		celda = row.createCell(0);
		celda.setCellStyle(cellStyle);
		celda.setCellValue("EMPLEO");
		
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila+1, 1, 1));
		celda = row.createCell(1);
		celda.setCellValue("NOMBRE Y APELLIDOS");
		
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila+1, 2, 2));
		celda = row.createCell(2);
		celda.setCellValue("DÍA");
		
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila, 3, 7));
		celda = row.createCell(3);
		celda.setCellValue("HORA");
		
		sheet.addMergedRegion(new CellRangeAddress(indiceFila, indiceFila+1, 9, 17));
		celda = row.createCell(9);
		celda.setCellValue("OBSERVACIONES");
		
		indiceFila +=1;
		row = sheet.createRow(indiceFila);
		celda = row.createCell(3);
		celda.setCellValue("1ª");
		
		celda = row.createCell(4);
		celda.setCellValue("2ª");
		
		celda = row.createCell(5);
		celda.setCellValue("3ª");
		
		celda = row.createCell(6);
		celda.setCellValue("4ª");
		
		celda = row.createCell(7);
		celda.setCellValue("5ª");
		
		celda = row.createCell(8);
		celda.setCellValue("6ª");
		
		/*Poner nombres alumnos*/
		/*-------------------------------------------------------------------*/
		font.setUnderline((byte) 0);
		//cellStyle.setFont(font);
		celda.setCellStyle(cellStyle);
		
		
		/*------Tabla profesorado--------------------------------------------*/
		/*-------------------------------------------------------------------*/
		

		
		try {
			
		    FileOutputStream out = new FileOutputStream(new File("excel.xlsx"));
		    workbook.write(out);
		    out.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	
}

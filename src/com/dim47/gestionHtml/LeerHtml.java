package com.dim47.gestionHtml;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dim47.parte.Asignatura;
import com.dim47.parte.Profesor;
import com.dim47.parte.Semana;


//Como proceso contamos las semanas que salen, generamos objetos y añadimos profesores y asignaturas


public class LeerHtml {
	private final String ATRIBUTO = "colspan";
	private final String SEMANA_SPAN = "10";
	private final String ASIGNATURA_SPAN = "4";
	private final String PROFESOR_SPAN = "7";
	private List<Semana> programa;
	
	public LeerHtml() {
		this.programa = new ArrayList<Semana>();
	}
	
	
	
	public List<Semana> getPrograma() {
		return programa;
	}



	public void setPrograma(List<Semana> programa) {
		this.programa = programa;
	}



	public List<Semana> leerFromUrl(String url) {
		
		Document doc; 
		
		try {
			
			doc  = Jsoup.connect(url).get();
			
			programa = getSemanas(doc, this.programa);
			System.out.println("termina");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programa;
	}
	
	public void getProfesores(Document doc) throws IOException{
		 Elements link = doc.getElementsByAttributeValue(ATRIBUTO, PROFESOR_SPAN);
		    Element linElement = null;
		    String stringLink = null;
		   
		    for (int i = 0; i < link.size(); i++) 
		    {
		    	linElement = link.get(i);
		        
		        
		        if (linElement.childNode(0) != null) {
		        	stringLink = linElement.childNode(0).toString();
		        	if (!Utils.matchString("&", stringLink)) {
		        		System.out.println(stringLink);
					}
		        	
		        	
				}
		        
		     }
	}
	
	public  List<Asignatura> getAsignaturas(Document doc, int indice) throws IOException{
		 Elements link = doc.getElementsByAttributeValue(ATRIBUTO, ASIGNATURA_SPAN);
		    Element linElement = null;
		    String stringLink = null;
		    Asignatura asignatura = null;
		    Profesor profesor = null;
		    List<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();
		    int contador = 0;
		    for (int i = 0; i < link.size(); i++) 
		    {
		    	linElement = link.get(i);
		        
		        
		        if (linElement.childNode(0) != null) {
		        	stringLink = linElement.childNode(0).toString();
		        	if (Utils.matchString("asignatura", stringLink)) {
		        		contador++;
		        	
					}else {
						if (!Utils.matchString("<|&", stringLink) && contador==indice) {
		        			profesor = new Profesor(linElement.parentNode().childNode(3).childNode(0).toString());
		        			asignatura = new Asignatura(stringLink, profesor);
		        			listaAsignaturas.add(asignatura);
						} 
					}
		        	
		        	
				}
		        
		     }
		    return listaAsignaturas;
	}
	
	public  List<Semana> getSemanas(Document doc, List<Semana> lista) throws IOException {
		
	    Elements link = doc.getElementsByAttributeValue(ATRIBUTO, SEMANA_SPAN);
	    Element linElement = null;
	    String stringLink = null;
	    int indice =1;
	    System.out.println(link.size());
	    for (int i = 0; i < link.size(); i++) 
	    {
	    	linElement = link.get(i);
	        
	        
	        if (linElement.childNode(0) != null) {
	        	stringLink = linElement.childNode(0).toString();
	        	if (Utils.matchString("Semana del", stringLink)) {
	        		Semana semana = new Semana(stringLink);
	        		semana.setListaAsignatura(getAsignaturas(doc, indice));
	        		indice++;
	        		lista.add(semana);
	        		
				}
	        	
	        	
			}
	        
	     }
	    return lista;
	}
	
	
	
	
}

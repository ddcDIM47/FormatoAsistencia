package com.dim47.gestionHtml;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dim47.parte.Asignatura;
import com.dim47.parte.Dia;
import com.dim47.parte.Hora;
import com.dim47.parte.Profesor;
import com.dim47.parte.Semana;


//Como proceso contamos las semanas que salen, generamos objetos y añadimos profesores y asignaturas


public class LeerHtml {
	private final String ATRIBUTO = "colspan";
	private final String HEIGHT = "height";
	private final String SEMANA_SPAN = "10";
	private final String ASIGNATURA_SPAN = "4";
	private final String PROFESOR_SPAN = "7";
	private final String DIA_SPANS = "2";
	private final String SEMANA_HEIGHT = "25";
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
	
	
	private void crearDias() {
		
	}
	
	private void getProfesores(Document doc) throws IOException{
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
	
	private  List<Asignatura> getAsignaturas(Document doc, int indice) throws IOException{
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
	    Elements link2 = doc.select("td:is(.xl9831139,.xl9631139,.xl8631139,.xl10231139,[width=180],[colspan=2],[colspan=4],[colspan=7])"
	 			+ "td:not(.xl10031139,.xl8831139,.xl10031139,.xl9631139,.xl9931139,.xl8831139,.xl7131139,.xl7931139,.xl7531139,.xl10131139,.xl10031139,xl11231139,.xl9631139,.xl10631139,.xl10231139)");
//	 	
//	    Elements link2 = doc.select("td:is([width=180],[colspan=2],[colspan=4],[colspan=7])" 
//	 			+ "td:not(.xl10031139,.xl9631139,.xl9931139,.xl8831139,.xl7131139,.xl7931139,.xl7531139,.xl6931139,.xl10131139,.xl10031139,xl11231139,.xl9631139,.xl10631139,.xl10231139)");
	    Element linElement = link.get(0);
	    String stringLink = null;
	    int indice =1;
	    
	    for (int i = 0; i < link.size(); i++) 
	    {
	    	linElement = link.get(i);
	        
	        
	        if (linElement.childNode(0) != null) {
	        	stringLink = linElement.childNode(0).toString();
	        	if (Utils.matchString("Semana del", stringLink)) {
	        		Semana semana = new Semana(stringLink);
	        		semana.setListaDias(generarDias(link2,indice));
	        		List<Asignatura> listaA = getAsignaturas(doc, indice);
	        	    
	        		semana.setListaAsignatura(listaA);
	        		indice++;
	        		lista.add(semana);
	        		
				}
	        	
	        	
			}
	        
	     }
	    return lista;
	}



	private Dia[] generarDias(Elements link, int indice) {
		
		    Asignatura asignatura = null;
		    Profesor profesor = null;
		    Dia[] dias = new Dia[5];
		    Hora[] hour = null;
		    int contadorDia = 0;
		    int index = 0;
		    int indexHora = -1;
		    int indexDia = -1;
		    
		    while(link.size() > 0 && index<34) {
		    	
		    	if (Utils.matchString("SEMANA", link.get(0).text())) {
	        		
	        		//System.out.println(link.get(0));
	        		System.out.println("*---------------------------------------*");
	        		
	        		link.remove(0);
		        }else {
		        	while (Utils.matchSemana(link.get(0).text())) {
		        		
	        			//System.out.println(link.get(0).text());
	        			link.remove(0);
					}
	        		for (contadorDia = 0; contadorDia < 34 ; contadorDia++) {
	        			if (contadorDia<5) {
							//añado los dias
	        				//System.out.println(link.get(0).text());
	        				Dia dia = new Dia();
	        				dia.setNombre(link.get(0).text());
	        				dias[contadorDia] = dia;
						}else {
							
							indexDia++;
							
							if (contadorDia%5==0) {
								indexDia=0;
								indexHora++;
							}
	
							asignatura = new Asignatura();
							asignatura.setNombre(link.get(0).text());
							dias[indexDia].horaArray[indexHora].asignatura=asignatura;
							
						}
	        			index++;	        			
	        			link.remove(0);
	        			
	        		}
		        	
		        	link.remove(0);
		        }
		    	
		    	
		    	
		    }
		return dias;
	}
	
	
	
	
}

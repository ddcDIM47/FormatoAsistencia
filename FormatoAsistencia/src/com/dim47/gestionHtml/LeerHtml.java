package com.dim47.gestionHtml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LeerHtml {

	public static void leerFromUrl(String url) {
		URL urlObjeto;
		String codigo;
		Document doc; 
		try {
			urlObjeto = new URL(url);
			doc  = Jsoup.connect(url).get();
			InputStreamReader isr = new InputStreamReader(urlObjeto.openStream());
			BufferedReader br = new BufferedReader(isr);
//			while ((codigo = br.readLine())!=null) {
//				//System.out.println(codigo);
//				
//			}
			getTag(doc);
			br.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getTag(Document doc) throws IOException {
		

	    Elements link = doc.select("head");
	    String stringLink = null;
	    System.out.println(link.size());
	    for (int i = 0; i < link.size(); i++) 
	    {

	        stringLink = link.toString();
	        System.out.println(stringLink);
	     }
	}
	
}

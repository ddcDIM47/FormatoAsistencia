package com.dim47.gestionHtml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static boolean matchString(String patron, String busqueda) {
		
		Pattern pattern = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(busqueda);
		return matcher.find();
		
	}
}

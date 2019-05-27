package abl.libreria;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cadenas {

	public static void main(String[] args) {
		Cadenas c = new Cadenas();
		
		System.out.println(c.padRight("55",9,'*'));
		System.out.println(padDouble(4365.542,8,7));
		
		System.out.println(c.middleTrim(" alan   beel   laco"));
		System.out.println(capitalizar("alan bel   lacoma"));
	}
	
	public static int getNumeroPalabras(String texto) {
		return texto.split(" ").length;
	}
	
	public static String capitalizar(String texto) {
		texto = texto.toLowerCase();
		texto = Cadenas.middleTrim(texto);
		String [] partes = texto.split(" ");
		String campo = "";
		for( String n : partes) {
			if(n.trim().length() != 0)
				campo += n.toUpperCase().charAt(0) + n.substring(1) + " ";
		}
		return campo;
	}
	
	public static String middleTrim(String texto) {
		// elimina espacios en blanco extras entre un misma frase.
		String campo = "";
		String[] datos = texto.split(" ");	
		for(String n : datos)   {
			if(n.trim().length() != 0)
			campo += n + " ";
		}
		return campo.trim();
	}
	
	
	public static String padDouble(double numero , int digitos, int decimales) {
		String valorNumerico = String.valueOf(numero);
		String parteEntera = valorNumerico.substring(0, valorNumerico.indexOf(".")) ;    // 1234.33
		String parteDecimal = valorNumerico.substring(valorNumerico.indexOf(".")+1) ;  
		
		if(parteEntera.length() > digitos) return valorNumerico;
		
		if(parteDecimal.length() > decimales) return valorNumerico;
		parteEntera = padLeft (parteEntera, digitos, '0');
		parteDecimal = padRight (parteDecimal, decimales, '0');
		return parteEntera + "."+ parteDecimal;
	}
	
	
	public static String padLeft(int numero, int digitos) {
		return padLeft(String.valueOf(numero),digitos,'0');
		
        //String cadNum = Integer.toString(numero);
		//if(cadNum.length() > digitos) return cadNum;
		//int numeroCeros= digitos-cadNum.length();
		//for(int x=0 ; x < numeroCeros ; x++) cadNum = "0" + cadNum;
		//return cadNum;	
	}
	
	public static String padLeft(int numero, int digitos, char relleno) {
		return padLeft(String.valueOf(numero),digitos,relleno);
	}
	
	public static String padLeft(String cadenaTexto, int digitos) {
		return padLeft(cadenaTexto,digitos,' ');
	}
	
	
	public static String padLeft(String numero, int digitos, char car) {
			// rellena con el caracter que queramos a la izquierda de un digito.
	    if(numero.length() > digitos) return numero;
			
		   int numeroCeros= digitos-numero.length();
		   for(int x=0 ; x < numeroCeros ; x++) {
				numero = car + numero;
			}
			
			return numero;
		}
	
	public static String padRight(String numero, int digitos, char car) {
		// rellena con el caracter que queramos a la derecha de un digito.
    if(numero.length() > digitos) return numero;
		
	   int numeroCeros= digitos-numero.length();
	   for(int x=0 ; x < numeroCeros ; x++) {
			numero = numero + car;
		}
		
		return numero;
	}
	
	public static String padRight(int numero, int digitos) {
		return padRight(String.valueOf(numero),digitos,'0');
		
        //String cadNum = Integer.toString(numero);
		//if(cadNum.length() > digitos) return cadNum;
		//int numeroCeros= digitos-cadNum.length();
		//for(int x=0 ; x < numeroCeros ; x++) cadNum = "0" + cadNum;
		//return cadNum;	
	}
	
	public static String padRight(int numero, int digitos, char relleno) {
		return padRight(String.valueOf(numero),digitos,relleno);
	}
	
	public static String padRight(String cadenaTexto, int digitos) {
		return padRight(cadenaTexto,digitos,' ');
	}



	
	
	
	
	public static String mayusculas(String texto) {
		return texto.toUpperCase();
	} 

	
	public static boolean esVocal(char v) {
		boolean esvocal = false;
		switch(v) {
		case 'a':    case 'e':   case 'i':   case 'o':   case 'u':
		case 'á':
		case 'é':    case 'í':   case 'ó':   case 'ú':   case 'ü':
			esvocal = true;
		}
		return esvocal;
	}
	
	public static int contarVocales(String cadena) {
		int numVocales = 0;
		cadena= cadena.toLowerCase();
		
		for(int i=0 ; i< cadena.length() ; i++) {
			char caracter = cadena.charAt(i);
			if(esVocal(caracter) == true)
				numVocales++;
		}
		return numVocales;
		
	}
	
	public static int ocurrencias(String texto , String buscar) {
		texto = texto.replace("'" , "\"");
		int contador= 0;
		texto = texto.toLowerCase();
		buscar = buscar.toLowerCase();
		
		int posicion = texto.indexOf(buscar);  // devuelve -1 si no encuentra nada
		while (posicion != -1 ) {
			contador++;
			// hacemos una subcadena quitando la primera palabra encontrada.
			// Desde la posicion se le suma buscar.length que es el tamaño de la palabra, para
			// que nos coloquemos justo despues de la palabra buscada.
			texto = texto.substring(posicion + buscar.length()); 
			posicion = texto.indexOf(buscar);
		}
		return contador;
	}
	
	public static int contarLinks(String html) {
		int numero_enlaces = 0;
		html = html.toLowerCase();
		String base = "href=\"";
		
		int posicion = html.indexOf(base); 
		while (posicion != -1 ) {
			numero_enlaces++;
			
			html = html.substring(posicion + base.length());  // comenzamos el texto en la primera busqueda, vamos borrando texto.
			String enlace = html.substring(0,html.indexOf("\""));
			System.out.println(enlace);
			
			posicion = html.indexOf(base);
		}
		return numero_enlaces;
	}
	
	public static String getTextFile(String nombreArchivo) {
		String texto = "";
		try {
			BufferedReader archivo = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nombreArchivo))));
			
			String linea;
			while((linea = archivo.readLine()) != null ) {
				texto +=linea;
			}
			archivo.close();
			
		}catch(Exception e) {
			
		}
		return texto;
	}
	
	public static String[] getLinks(String html) {
		html = html.replace("'" , "\"");
		String[] enlaces = new String[ocurrencias(html,"href=\"")];
		int numero_enlaces = 0;
		html = html.toUpperCase();
		String buscar = "href=\"";
		int posicion = html.indexOf(buscar);
		while (posicion != -1 ) {
			html = html.substring(posicion + buscar.length());  
			String enlace = html.substring(0,html.indexOf("\""));
			enlaces[numero_enlaces++]= enlace;
			posicion = html.indexOf(buscar);
		}
		
		return enlaces;
	}


	public static String getFecha(String fecha) {
		fecha = fecha.replace("/", "-");
		String[] fechatroceada = fecha.split("-"); 
		int mes = new Integer(fechatroceada[1]).intValue();
		
		String[] meses = {"Enero", "Febrero" ,"Marzo", "Abril" ,"Mayo", "Junio" ,"Julio", "Agosto" 
				,"Septiembre", "Octubre" ,"Noviembre", "Diciembre"};
		
		String nueva = fechatroceada[0] + " de " + meses[mes-1] + " del " + fechatroceada[2] ;
		
		return nueva;
	}
	
	public static boolean esFecha(String fecha) {
		boolean esFecha = true;
		
		 if(!fecha.contains("/") && !fecha.contains("-") ) 
		    return false;
		
		fecha = fecha.replace("/", "-");
		int separadores = ocurrencias(fecha,"-");
		if (separadores != 2) {
			return false;
		}
		String[]datos = fecha.split("-");
		
		Matematicas m = new Matematicas();
		int dia = m.stringToInt(datos[0]);
		int mes = m.stringToInt(datos[1]);
		int anno = m.stringToInt(datos[2]);
		
		if(anno == 0)
			return false;
		
		if (mes <1 || mes >12 ) 
			return false;
		
		int[] diasMes = {31,28,31,30,31,30,31,31,30,31,30,31};
		int diaMes = diasMes[mes-1];
		
		if(dia<1 || dia > diaMes) 
			return false;
		
		
		
		return esFecha;
		
	}
	
	
	
	
	
	
	
}

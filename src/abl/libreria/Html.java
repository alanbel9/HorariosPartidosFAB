package abl.libreria;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Html {

	static final String ENLACE_HREF = "href=\"";
	static final String ENLACE_SRC = "src=\"";
	
	
	public static List<String> getLinks(String url) {
		return getEnlacesHTML(url, ENLACE_HREF);
	}
	
	public static List<String> getImages(String url) {
		return getEnlacesHTML(url, ENLACE_SRC);
	}
	
	public static List<String> getEnlacesHTML(String url, String buscar) {
		String html = getHTML(url);
		String urlBase = "";
		try {
			URL base = new URL(url);
			int puerto = base.getPort();
			if(puerto != -1){
				urlBase = base.getProtocol() + "://" + base.getHost() + ":" + base.getPort();
			} else urlBase = base.getProtocol() + "://" + base.getHost();
		} catch (Exception e) {}
		List<String> enlaces = new ArrayList<String>();
		html = html.replace("\'", "\"").replace(" ", "").toLowerCase();
		int posicionHref;
		while ((posicionHref= html.indexOf(buscar)) != -1) {
			String enlace = html.substring(posicionHref + buscar.length());
			enlace = enlace.substring(0, enlace.indexOf("\""));
			if (!enlace.startsWith("http")) {
				if (enlace.startsWith("/")) {
					enlace = urlBase + enlace;
				} else { 
					enlace = urlBase + "/" + enlace;
				}
			}
			if (!enlaces.contains(enlace)) enlaces.add(enlace);
			html = html.substring(posicionHref + buscar.length());
			}
		return enlaces;
	}	
	
	public static String getHTML(String urlToRead) {
		StringBuilder result = new StringBuilder();             //StringBuilder es una clase para tratamiento de texto(Existe tmb StringBuffer, que está sincronizada para trabajar con threads)
		try {
			URL url = new URL(urlToRead);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();              //Definimos conexión y casteamos
			conn.setRequestMethod("GET");													//Indicamos que tipo de petición hacemos.
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));          //Leo el contenido del recurso usando BufferedReader(con la ventaja de tener buffer). con inputstreamreader llamamos al metodo getinputstream. Leemos como si fuera un archivo de texto.
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (Exception e) {
			result = new StringBuilder();          //si hay algún problema, que me devuelva un string vacío.
		}
		return result.toString();
	}
	
	// descarga cualquier tipo de documento  HTTP
	public static void getDownloadFile( String url , String archivoDestino) {
		try {
			URL enlace= new URL(url);
			HttpURLConnection conexion = (HttpURLConnection) enlace.openConnection();
			conexion.setReadTimeout(10000);
			conexion.setRequestProperty("User-Agent", "Mi robot java"); // nombre del robot
			conexion.connect();
			InputStream documento = conexion.getInputStream();
			FileOutputStream guardar = new FileOutputStream(new File(archivoDestino));
			byte[] arrayBytes = new byte[65536];
			int bytesLeidos = 0;
			while ((bytesLeidos = documento.read(arrayBytes)) != -1 ) {
				guardar.write(arrayBytes , 0 , bytesLeidos);
				guardar.flush();
			}
			guardar.close();
			documento.close();
		}
		catch(Exception e) {}
	}
	
	// descarga cualquier tipo de documento  HTTP
		public static void getDownloadFileSSL( String url , String archivoDestino) {
			try {
				URL enlace= new URL(url);
				HttpsURLConnection conexion = (HttpsURLConnection) enlace.openConnection();
				conexion.setReadTimeout(10000);
				conexion.setRequestProperty("User-Agent", "Mi robot java"); // nombre del robot
				conexion.connect();
				InputStream documento = conexion.getInputStream();
				FileOutputStream guardar = new FileOutputStream(new File(archivoDestino));
				byte[] arrayBytes = new byte[65536];
				int bytesLeidos = 0;
				while ((bytesLeidos = documento.read(arrayBytes)) != -1 ) {
					guardar.write(arrayBytes , 0 , bytesLeidos);
					guardar.flush();
				}
				guardar.close();
				documento.close();
			}
			catch(Exception e) {}
		}
	

		public static boolean isHTML(String url) {
		String extensiones[] = { ".htm", ".html", ".php", ".jsp", ".asp", ".aspx"};
		for (String extension : extensiones) if (url.endsWith(extension)) return true;
		return false;
	}
		
		
		
		public static void saveHTML(String html , String archivoDestino) {
			try {
				PrintWriter salida = new PrintWriter(new FileWriter(archivoDestino));
				salida.write(html);
				salida.close();
			}
			catch(IOException e) {}
		}
		
	
	
	
	
}

package Principal;

import abl.libreria.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.ContentHandler;

public class Principal {

	public static final String URL_BASE="http://fabasket.com/horarios/";
	public static String RUTA_PDF;  
	public static List<String> lista_provincias; // 0=zaragoza , 1=huesca , 2=teruel
	public List<String> lineasPdf;
	public List<String> lineasBusqueda;
	
	
	public static void main (String args[]) {
		Principal principal = new Principal();
		
		//principal.descargarPdf();
		//String texto = principal.convertirPDFaTXT(RUTA_PDF);
		//principal.GuardarArchivoTxt(texto);
		
		
	}

	// busca el patron y devuelve cada linea si lo contiene.
	public List<String> buscarEnFichero(String ruta , String patron){
		List<String> busqueda = new ArrayList<String>();
		patron = patron.toUpperCase();
		String cadena;
		try {
			FileReader f = new FileReader(ruta);
			BufferedReader b = new BufferedReader(f);
			while( (cadena = b.readLine()) != null ) {
				cadena= cadena.toUpperCase();
				if( cadena.contains(patron ) ) busqueda.add(cadena);
			}
		}
		catch(Exception e) {}
		
		return busqueda;
	}

	
// --------------------------------------------
	
public void GuardarArchivoTxt(String texto) {
	try {
		String nombreArchivo = RUTA_PDF.replace(".pdf", ".txt");
		
		FileWriter salida = new FileWriter(nombreArchivo);
		salida.write(texto);
		salida.close();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}
}
	
	
	// guarda el ENLACE en la variable de clase enlacePdf . 
	public void obtenerEnlace() {
		lista_provincias = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect(URL_BASE).get();
			Elements enlaces = doc.select("a[href]");
			for (Element enlace : enlaces) {
				String txtEnlace = enlace.attr("href");
				// COGEMOS EL 1º ENLACE QUE APAREZCA, EL DE ZARAGOZA.
				if(txtEnlace.startsWith("http://fabasket.com/wp-content/upload")) { 
					lista_provincias.add(txtEnlace);
				}
			}
		}catch(IOException e) { e.printStackTrace();}

	}
	
	
	public void descargarPdf() {
		obtenerEnlace();
		Html.getDownloadFile(lista_provincias.get(0), RUTA_PDF);
		// get(0) devuelve ZARAGOZA.
	}
	
	public String convertirPDFaTXT(String ruta)  {
		InputStream is = null;
		String texto = "";
	    try {
	      is = new FileInputStream(ruta);
	      ContentHandler contenthandler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      PDFParser pdfparser = new PDFParser();
	      pdfparser.parse(is, contenthandler, metadata, new ParseContext());
	      //System.out.println(contenthandler.toString());
	      texto = contenthandler.toString();
	    }
	    catch (Exception e) {  e.printStackTrace(); }
	    finally {
	    	try {  if (is != null) is.close();} 
	    	catch(IOException ex) {}
	    }
		return texto;
	}
	
	public void GuardarArchivoTxt(List<String> lista) {
		try {
			String nombreArchivo = RUTA_PDF.replace(".pdf", ".txt");
			FileWriter salida = new FileWriter(nombreArchivo);
			for (String linea : lista) {
				salida.write(linea);
				salida.write("\n");
			}
			salida.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

 
  /*
   
   public String obtenerFechaEnlace(String enlace) {
		String resultado="";
		enlace = enlace.trim();
		String [] partes = enlace.split(" ");
		for(int i=0 ; i< partes.length ; i++) {
			if( partes[i].matches(".*\\d.*")  ) resultado= partes[i];
		}
		resultado = resultado.replace("/", "-");
		return resultado;
	}
 
 public List<String> obtenerListaHorarios(String html) {
	List<String> lista = new ArrayList<String>();
	Document doc = Jsoup.parse(html);
	Elements lineas = doc.select("p");  
	for (Element linea : lineas) {
		lista.add(linea.text());
	}
	return lista;
}
 
public void obtenerListaTextoFinal(String html) {
	lineasPdf = new ArrayList<String>();
	Document doc = Jsoup.parse(html);
	Elements lineas = doc.select("p");  
	for (Element enlace : lineas) {
		lineasPdf.add(enlace.text());
		// System.out.println(enlace.text());     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
	}
}



	public String  convertirPDFaHTML(String ruta)  {
		InputStream is = null;
		String html="";
	    try {
	      is = new FileInputStream(ruta);
	        PDDocument pdd = PDDocument.load(is); //This is the in-memory representation of the PDF document.
	        PDFText2HTML converter = new PDFText2HTML(); // the converter
	        html = converter.getText(pdd); // That's it!
	        pdd.close();
	        is.close();
	        
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	    return html; 
	}

   
   */
	
	
	
}

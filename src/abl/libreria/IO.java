package abl.libreria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IO {

	
	public static List<String> getFicheroLista(String archivo){
		return getFicheroLista(new File(archivo));
	}
	
	
	public static List<String> getFicheroLista(File archivo){
		List<String> lineasArchivo = new ArrayList<String>();
		BufferedReader archivoLectura=null;
		try {
			archivoLectura= new BufferedReader( new FileReader(archivo));
			String linea;
			while((linea = archivoLectura.readLine()) != null  ) {
				lineasArchivo.add(linea);
			}
	
		}catch(FileNotFoundException exce) {
			lineasArchivo = new ArrayList<String>();
		}
		catch(Exception e) {
			lineasArchivo = new ArrayList<String>();
		}finally {
			if(archivoLectura != null) {
				try {archivoLectura.close(); } 
				catch(IOException e) {}
			}
		}
		
		return lineasArchivo;
	}
	public static void copiar(String origen, String destino) {
		try {
			FileInputStream archivoOrigen = new FileInputStream(origen);
			FileOutputStream archivoDestino = new FileOutputStream(destino);
			int byteLeido;
			while ((byteLeido = archivoOrigen.read()) != -1) {
				archivoDestino.write(byteLeido);
			}
			archivoOrigen.close();
			archivoDestino.close();
		} catch (IOException ioe) {
			
		}
	}
	
	
}

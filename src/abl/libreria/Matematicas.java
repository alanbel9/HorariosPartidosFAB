package abl.libreria;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Matematicas {

	public static void main(String[] args) {
		Matematicas mates = new Matematicas();
		//System.out.println(eye(4,4));
		//System.out.println(Multiplicar(5,5));
		String numero="1234";
		mates.getEntero();
		//System.out.println(mates.stringToInt(numero));
		
		
		int[] numeros = {1,2,3,4,5,6,7,8,9,10};
		int[] nuevo = darVuelta(numeros);
		//int[] nuevo = arrayInvertido(numeros);
		imprimirArray(nuevo);
		
	}
	
	public static int[] darVuelta(int[] array) {
		int[] nuevo = new int[array.length];
		
		for(int i=0; i<array.length;i++)
		{
			nuevo[i] = array [array.length - i -1];	
		}
		return nuevo;
	}
	public static double[] darVuelta(double[] array) {
		double[] nuevo = new double[array.length];
		
		for(int i=0; i<array.length;i++)
		{
			nuevo[i] = array [array.length - i -1];	
		}
		return nuevo;
	}
	
	public static double[][] multArray(double[][]primera ,double[][]segunda ){
		if (comparaMatrices(primera,segunda) == false ) {
			return null;
		}
		double[][] multiplicacion = new double[primera.length][primera[0].length];
		
		for(int i=0 ; i< primera.length ; i++) {
			for(int j=0 ; j< primera[i].length ; j++) {
				multiplicacion[i][j] = primera[i][j] * segunda[i][j];
			}
		}
		return multiplicacion;
	}
	
	public static boolean comparaMatrices(double[][] primera , double[][] segunda) {
		boolean son_iguales = true;
		
		if( primera.length != segunda.length) {
			son_iguales = false;;
		}
		for(int i=0; i<primera.length ; i++) {
				if(primera[i].length != segunda[i].length)
			    {  
					son_iguales = false;
				       break;
			    }
		}
		
		return son_iguales;
	}
	
	
	public static int[] arrayInvertido(int[] array) {
		int[] nuevo = new int[array.length];
		int indice=0;
		for(int i=array.length -1; i>=0 ;i--)
		{
			nuevo[indice++] = array[i];	
		}
		return nuevo;
	}
	
	public static void imprimirArray( int[] array) {
		for(int i=0 ; i< array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
	}
	
	
	
	public static int suma (int a, int b)
	{
		return a+b;
		
	}
	
	
	
	
	public static int Multiplicar(int num1, int num2, int... y ) {
		int resultado =0;
		for(int i=1; i<=num2 ; i++)
			resultado += num1;
			
		return resultado;
		
	}
	
	
	public  static int sumatorioArray(int[]numeros) {
		int sumatorio = 0;
		for( int n : numeros)
			sumatorio += n;
		
		return sumatorio;
		
		
	}
	
	
	public static int[][] eye(int filas, int col)
	{
		int[][] matriz = new int[filas][col];
		
		for(int i=0; i<matriz.length ; i++) {
			for(int j=0; j<matriz[i].length ; j++) {
				
				matriz[i][j] = (i==j) ? 1 : 0;
				//if(i==j)
				//matriz[i][j] = 1;
				//else
				//	matriz[i][j] = 0;
			}
		}
		
		
		return matriz;
		
		
	}
	
	public static int[] sumaArrays(int[] arr1 , int[] arr2)
	{
	
		int[] nuevo = new int[ (arr1.length < arr2.length ? arr1.length :arr2.length  )];
		
		for(int i=0; i<nuevo.length ; i++)
		{
			nuevo[i]= arr1[i] + arr2[i];
		}	
		return nuevo;
	}
	

	public static int stringToInt(String texto) {
		int numero=0;
		try {
			numero = new Integer(texto).intValue();
		}catch(NumberFormatException e) {
			numero = 0;
			//System.out.println("Error");
		}
		
		return numero;
	}
	public static int stringToInt(String texto, int error) {
		int numero=error;
		try {
			numero = new Integer(texto).intValue();
		}catch(NumberFormatException e) {
			numero = error;
			//System.out.println("Error");
		}
		
		return numero;
	}
	
	public static long stringToLong(String texto) {
		long numero=0;
		try {
			numero = new Long(texto).longValue();
		}catch(NumberFormatException e) {
			numero = 0;
		}catch(Exception ae) {
			numero = -1;
		}
		
		return numero;
	}
	public static long stringToLong(String texto, long error) {
		long numero=error;
		try {
			numero = new Long(texto).longValue();
		}catch(NumberFormatException e) {
			numero = error;
		}catch(Exception ae) {
			numero = error;
		}
		
		return numero;
	}
	
	public static double stringToDouble(String texto) {
		 double numero = 0.0;
		 try {
			 numero = new Double(texto).doubleValue();
		 } catch(NumberFormatException e) {
			 
		 }
		 return numero;
	 }
	public static double stringToDouble(String texto, double error) {
		 double numero = error;
		 try {
			 numero = new Double(texto).doubleValue();
		 } catch(NumberFormatException e) {
			 
		 }
		 return numero;
	 }
	
	public static Date stringToDate(String fecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaNueva = null;
		try {
		fechaNueva = formatoDelTexto.parse(fecha);
		} catch (ParseException ex) {
		ex.printStackTrace();
		}
		return fechaNueva;
	}
	
	public static int stringToEntero(String cadena) {
		int campo=0;
		try {
   		 campo = new Integer(cadena).intValue(); 
   	 } catch(NumberFormatException e) {}
   	  return campo;
	}
	
	
	public static int getEntero() {
		int num=0;
		try {
			System.out.println("Introduce un entero: " );
			Scanner scan = new Scanner(System.in);
			num = scan.nextInt();
			
		}catch(InputMismatchException e) {
			num = 0;
		}
		return num;
	}
	

}

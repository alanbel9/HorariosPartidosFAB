package Principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.LineBorder;
import org.apache.tools.ant.Task;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;

public class AppGrafica  {

	private JFrame frmHorariosFab;
	private JTextField txtRuta;
	private JTextField txtBuscar;
	public Principal principal;
	
	//progressbar
	public int contador = 1;
	public Thread hilo = new Thread();
	Task task;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGrafica window = new AppGrafica();
					window.frmHorariosFab.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	/**
	 * Create the application.
	 */
	public AppGrafica() {
		initialize();
		principal = new Principal();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHorariosFab = new JFrame();
		frmHorariosFab.setTitle("Horarios FAB");
		frmHorariosFab.setBounds(100, 100, 847, 550);
		frmHorariosFab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHorariosFab.getContentPane().setLayout(null);
		
		JFileChooser destino = new JFileChooser();
		
		JTextArea txtAreaPrincipal = new JTextArea();
		txtAreaPrincipal.setBorder(new LineBorder(Color.ORANGE, 1, true));
		txtAreaPrincipal.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtAreaPrincipal.setBounds(33, 11, 648, 344);
		frmHorariosFab.getContentPane().add(txtAreaPrincipal);
		
		JScrollPane scroller = new JScrollPane(txtAreaPrincipal);
		scroller.setBounds(23, 93, 650, 373);
		JScrollBar bar = new JScrollBar();
		scroller.add(bar);
		frmHorariosFab.getContentPane().add(scroller);
		
		txtRuta = new JTextField();
		txtRuta.setEditable(false);
		txtRuta.setBounds(187, 22, 299, 20);
		frmHorariosFab.getContentPane().add(txtRuta);
		txtRuta.setColumns(10);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.CYAN);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBar.setForeground(Color.BLACK);
		progressBar.setBounds(187, 50, 299, 32);
		frmHorariosFab.getContentPane().add(progressBar);
		
		JButton btnSeleccionarDestino = new JButton("Seleccionar destino");
		btnSeleccionarDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = destino.showSaveDialog(frmHorariosFab);
				if(seleccion == JFileChooser.APPROVE_OPTION) { // si le damos a aceptar
					if( !destino.getSelectedFile().getName().endsWith(".pdf") ) {
						String nombre = destino.getSelectedFile().getName();
						destino.getSelectedFile().getName().concat(nombre + ".pdf");
					}
					File nombre = destino.getSelectedFile();
					txtRuta.setText(nombre.getAbsolutePath());
				}
				
				
			}
		});
		btnSeleccionarDestino.setBounds(23, 18, 154, 29);
		frmHorariosFab.getContentPane().add(btnSeleccionarDestino);
		
		JButton btnDescargarHorarios = new JButton("Descargar horarios");
		btnDescargarHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			//	int progress = task.hashCode();   // ???????????
				//progressBar.setValue(10);
				
						
				// si se ha introducido una ruta
				if(txtRuta.getText().trim().length() == 0  )  
					JOptionPane.showMessageDialog(null, "No has introducido ningún destino. ");
				else if( !txtRuta.getText().endsWith(".pdf")){
					txtRuta.setText(txtRuta.getText()+".pdf");
					principal.RUTA_PDF = txtRuta.getText();
					principal.descargarPdf();
					String texto = principal.convertirPDFaTXT(principal.RUTA_PDF);
					principal.GuardarArchivoTxt(texto);
					txtAreaPrincipal.setText(texto); 
				}
				else{
					principal.RUTA_PDF = txtRuta.getText();
					principal.descargarPdf();
					String texto = principal.convertirPDFaTXT(principal.RUTA_PDF);
					principal.GuardarArchivoTxt(texto);
					txtAreaPrincipal.setText(texto); 
				}
				
				
			}
		});
		btnDescargarHorarios.setBounds(509, 18, 164, 29);
		frmHorariosFab.getContentPane().add(btnDescargarHorarios);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> busqueda = new ArrayList<String>();
				String patron = txtBuscar.getText().trim();
				String rutaArchivo = principal.RUTA_PDF.replace(".pdf", ".txt");
				busqueda = principal.buscarEnFichero(rutaArchivo, patron);				
				
				try {
					FileWriter infoBusqueda = new FileWriter(principal.RUTA_PDF.replace(".pdf", "BUSQUEDA.txt"));
					busqueda.forEach(linea -> {
						try { 
							infoBusqueda.write(linea + "\n"); 
							} 
						catch(IOException e) {e.printStackTrace();}
						
						});
					infoBusqueda.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				

			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuscar.setBounds(683, 183, 138, 44);
		frmHorariosFab.getContentPane().add(btnBuscar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(683, 139, 138, 33);
		frmHorariosFab.getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		
		

		
		
		
		
	}
}

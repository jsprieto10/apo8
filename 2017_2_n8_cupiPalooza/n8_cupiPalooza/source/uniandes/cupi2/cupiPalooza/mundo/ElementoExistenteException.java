package uniandes.cupi2.cupiPalooza.mundo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ElementoExistenteException extends Exception {

	public final static String PATROCINADOR= "Patrocinador";
	public final static String BANDA= "Banda";
	String tipoRepetido;
	String nombreRepetido;
	public ElementoExistenteException(String pTipo, String pNombre) {
		// TODO Auto-generated constructor stub
		super();
		tipoRepetido=pTipo;
		nombreRepetido=pNombre;
		escribirLog();

	}
	public void escribirLog() {
		// TODO Auto-generated method stub

		Date fecha = new Date();
		PrintWriter escritor;
		try
		{
			try {
				escritor = new PrintWriter(new BufferedWriter(new FileWriter("./data/error.log", true)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				File archivo = new File("./data/error.log");
				escritor= new PrintWriter(archivo);
			}

			escritor.println( fecha.toString( ) + "- Elemento Repetido: " +  tipoRepetido + " " +nombreRepetido + " ya existe." );
			escritor.close( );
		}
		catch( FileNotFoundException e )
		{

			e.printStackTrace();
		}

	}

}

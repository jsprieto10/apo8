package uniandes.cupi2.cupiPalooza.mundo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class ElementoExisteException extends Exception {

	public final static String PATROCINADOR= "Patrocinador";
	public final static String BANDA= "Banda";
	String tipoRepetido;
	String nombreRepetido;
	public ElementoExisteException(String pTipo, String pNombre) {
		// TODO Auto-generated constructor stub
		super();
		tipoRepetido=pTipo;
		nombreRepetido=pNombre;
		escribirLog();
				
	}
	public void escribirLog() {
		// TODO Auto-generated method stub
		   File archivo = new File("./data/error.log");
	        
	        Date fecha = new Date();
	        PrintWriter escritor;
	        try
	        {
	            escritor = new PrintWriter(archivo);
	            
	            
	            escritor.println( fecha.toString( ) + "- Elemento Repetido: " +  tipoRepetido + " " +nombreRepetido + " ya existe" );
	            escritor.println("--------------------------------------------------------------------------------------" );
	            escritor.close( );
	        }
	        catch( FileNotFoundException e )
	        {

	            e.printStackTrace();
	        }
		
	}

}

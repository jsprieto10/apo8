package uniandes.cupi2.cupiPalooza.mundo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class PersistenciaException extends Exception{

	public PersistenciaException(String pCausa) {
		// TODO Auto-generated constructor stub
		super(pCausa);
		escribirLog(pCausa);
	}

	public void escribirLog(String pCausa) {
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

			escritor.println( fecha.toString( ) + pCausa );
			escritor.close( );
		}
		catch( FileNotFoundException e )
		{

			e.printStackTrace();
		}
	}

}

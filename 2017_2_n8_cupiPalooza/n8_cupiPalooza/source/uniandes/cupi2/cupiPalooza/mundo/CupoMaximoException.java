package uniandes.cupi2.cupiPalooza.mundo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CupoMaximoException extends Exception{

	public final static String TIPO_FESTIVAL="Festival";
	public final static String TIPO_ESCENARIO="Escenario";
	public final static String TIPO_PRESUPUESTO="Presupuesto";
	public final static String TIPO_CUPOS="Cupos";
	private String tipoElemento;
	private String tipoLimite;

	public CupoMaximoException(String pTipoElemento, String pTipoLimite) {
		// TODO Auto-generated constructor stub
		super();
		tipoElemento= pTipoElemento;
		tipoLimite= pTipoLimite;
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


			escritor.println( fecha.toString( ) + "- Cupo Maximo Excedido: " + tipoElemento + " " + tipoLimite );
			escritor.close( );
		}
		catch( FileNotFoundException e )
		{

			e.printStackTrace();
		}
	}

}

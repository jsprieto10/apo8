/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogotá - Colombia)
 * Departamento de  Ingeniería  de  Sistemas    y   Computación
 * Licenciado   bajo    el  esquema Academic Free License versión 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_festival
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPalooza.mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uniandes.cupi2.cupiPalooza.test.EscenarioTest;

/**
 * Festival de música. <br>
 * TODO Parte 1 Punto F: Documente la invariante de la clase.
 */

public class Festival implements Serializable
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -5516502844994245381L;

	/**
	 * Cantidad máxima de escenarios en el festival.
	 */
	public final static int CANTIDAD_MAXIMA_ESCENARIOS = 5;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Escenarios que componen el festival.
	 */
	private ArrayList escenarios;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * Construye el festival con su estado inicial a partir de un archivo serializado. <br>
	 * <b>post: </b> Se cargó el estado inicial del festival con la información del archivo dado por parámetro. <br>
	 * Si hay algún problema cargando el archivo, lanza excepción. <br>
	 * Si no existe el archivo seralizado, crea el arrayList vacío.
	 * @param pRuta Ruta del archivo del cual se cargará el estado del mundo.
	 * @throws PersistenciaException Se lanza una excepción si hay algún error cargando los datos del archivo.
	 */
	public Festival( String pRuta ) throws PersistenciaException
	{
		File file = new File(pRuta);
		if (file.exists())
		{
			escenarios = new ArrayList();
			cargar(pRuta);
		}
		else
			escenarios = new ArrayList();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna el escenario con el número recibido por parámetro.
	 * @param pNumero Número del escenario buscado. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
	 * @return El escenario con el número especificado.
	 */
	public Escenario darEscenario( int pNumero )
	{
		boolean termino = false;
		Escenario buscado = null;
		for( int i = 0; i < escenarios.size( ) && !termino; i++ )
		{
			Escenario actual = ( Escenario )escenarios.get( i );
			if( actual.darNumero( ) == pNumero )
			{
				buscado = actual;
				termino = true;
			}
		}
		return buscado;
	}

	/**
	 * Retorna el siguiente número disponible para un escenario.
	 * @return Número válido para asignar a un nuevo escenario. En caso de no haber más números disponibles retorna 0.
	 */
	public int darNumeroDisponible( )
	{
		int numero = 0;
		boolean termino = false;
		for( int i = 1; i <= CANTIDAD_MAXIMA_ESCENARIOS && !termino; i++ )
		{
			int provisional = i;
			for( int j = 0; j < escenarios.size( ); j++ )
			{
				Escenario actual = ( Escenario )escenarios.get( j );
				if( actual.darNumero( ) == provisional )
				{
					provisional++;
				}
			}

			if( provisional == i )
			{
				termino = true;
				numero = provisional;
			}
		}
		return numero;
	}

	/**
	 * Retorna la lista de escenarios en el festival.
	 * @return Lista de los escenarios.
	 */
	public ArrayList darEscenarios( )
	{
		return escenarios;
	}

	/**
	 * Crea un escenario con la información recibida por parámetro. <br>
	 * <b>post: </b> Se creó el escenario con el número especificado, el patrocinador y el presupuesto dados.
	 * @param pPatrocinador Nombre del patrocinador. pPatrocinador != null && pPatrocinador != "".
	 * @param pPresupuesto Presupuesto para el escenario. pPresupuesto > 0.
	 * @param pNumero Número del escenario. pNumero > 0 && pNumero <= 5.
	 * @throws ElementoExistenteException Si ya existe un escenario con este patrocinador.
	 * @throws CupoMaximoException Si se ha alcanzado el límite de escenarios en el festival.
	 */
	public void crearEscenario( String pPatrocinador, double pPresupuesto, int pNumero ) throws ElementoExistenteException, CupoMaximoException
	{
		boolean repetido = false;
		if( escenarios.size( ) >= CANTIDAD_MAXIMA_ESCENARIOS )
			throw new CupoMaximoException("Escenario", "Cantidad Maxima Escenarios");

		for( int i = 0; i < escenarios.size( ) && !repetido; i++ )
		{
			Escenario actual = ( Escenario )escenarios.get( i );
			if( actual.darPatrocinador( ).equals( pPatrocinador ) )
				repetido = true;
		}
		if( !repetido )
		{
			Escenario nuevo = new Escenario( pNumero, pPatrocinador, pPresupuesto );
			escenarios.add( nuevo );
		}
		else
			throw new ElementoExistenteException("Escenario", pPatrocinador);

	}

	/**
	 * Elimina un escenario. <br>
	 * <b>post: </b> El escenario fue eliminado del festival.
	 * @param pNumero Número del escenario a eliminar. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
	 * @return true si fue posible eliminar el escenario, false en caso de que el escenario no exista.
	 */
	public boolean eliminarEscenario( int pNumero )
	{
		boolean termino = false;
		for( int i = 0; i < escenarios.size( ) && !termino; i++ )
		{
			Escenario actual = ( Escenario )escenarios.get( i );
			if( actual.darNumero( ) == pNumero )
			{
				escenarios.remove( actual );
				termino = true;
			}
		}
		return termino;
	}

	/**
	 * Agrega una banda a un escenario. <br>
	 * <b>post: </b> Al escenario especificado se agregó una nueva banda en su repertorio.
	 * @param pEscenario Número del escenario al cual se agregará la banda. pNumero > 0 && pNumero <= 5.
	 * @param pNombre Nombre de la banda. pNombre != null && pNombre != "".
	 * @param pCantidadDeFans Cantidad de fans que tiene la banda. pCantidadDeFans > 0.
	 * @param pCantidadDeCanciones Cantidad de canciones que tocará la banda. pCantidadDeCanciones > 0.
	 * @param pCosto Costo de la banda por presentarse en un escenario. pCosto > 0.
	 * @param pRutaImagen Ruta de la imagen descriptiva de la banda. pRutaImagen != null && pRutaImagen != "".
	 * @throws ElementoExistenteException Si ya existe una banda con este nombre en este escenario.
	 * @throws CupoMaximoException Si el escenario al que se desea agregar la banda ha alcanzado su límite de bandas.
	 */
	public void agregarBandaAEscenario( int pEscenario, String pNombre, int pCantidadDeFans, int pCantidadDeCanciones, double pCosto, String pRutaImagen ) throws ElementoExistenteException, CupoMaximoException
	{		
		Escenario escenario = darEscenario( pEscenario );
		escenario.agregarBanda( pNombre, pCantidadDeFans, pCantidadDeCanciones, pCosto, pRutaImagen );

	}

	/**
	 * Elimina la banda con el nombre dado del escenario especificado. <br>
	 * <b>pre: </b> La banda con este nombre existe en el escenario. <br>
	 * <b>post: </b> Se eliminó la banda especificada del escenario.
	 * @param pEscenario Número del escenario al cual se agregará la banda. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
	 * @param pNombre Nombre de la banda. pNombre != null && pNombre != "". Existe una banda por este nombre.
	 * @throws Exception En caso de que se esté intentando eliminar la última banda restante en el escenario.
	 */
	public void eliminarBandaEscenario( int pEscenario, String pNombre ) throws Exception
	{
		Escenario escenario = darEscenario( pEscenario );
		escenario.eliminarBanda(pNombre);
	}

	/**
	 * Ordena ascendentemente las bandas de todos los escenarios por nombre. <br>
	 * <b> post: </b> Las bandas en los escenarios fueron ordenadas alfabéticamente por su nombre.
	 */
	public void ordenarPorNombre( )
	{
		for( int i = 0; i < escenarios.size( ); i++ )
		{
			Escenario actual = ( Escenario )escenarios.get( i );
			if( actual != null )
			{
				actual.ordenarPorNombre( );
			}
		}
	}

	/**
	 * Ordena descendentemente las bandas de todos los escenarios por su cantidad de fans. <br>
	 * <b> post: </b> Las bandas fueron ordenadas en los escenarios según su cantidad de fans.
	 */
	public void ordenarPorCantidadDeFans( )
	{
		for( int i = 0; i < escenarios.size( ); i++ )
		{
			Escenario actual = ( Escenario )escenarios.get( i );
			if( actual != null )
			{
				actual.ordenarPorCantidadDeFans( );
			}
		}
	}

	/**
	 * Ordena ascendentemente las bandas en los escenarios por su cantidad de canciones. <br>
	 * <b> post: </b> Las bandas fueron ordenadas en los escenarios según su cantidad de canciones.
	 */
	public void ordenarPorCantidadDeCanciones( )
	{
		for( int i = 0; i < escenarios.size( ); i++ )
		{
			Escenario actual = ( Escenario )escenarios.get( i );
			if( actual != null )
			{
				actual.ordenarPorCantidadDeCanciones( );
			}
		}
	}

	/**
	 * Guarda el estado del sistema en un archivo serializado. <br>
	 * <b>post: </b> Se guardó la lista de escenarios en el archivo dado. <br>
	 * @param pRuta Ruta del archivo donde se guarda el estado del sistema. pRuta != null && pRuta != "".
	 * @throws PersistenciaException Se lanza una excepción si hay algún error guardando los datos del archivo.
	 */
	public void guardar( String pRuta ) throws PersistenciaException
	{
		try
		{	ObjectOutputStream objeto;
		try {
			objeto = new ObjectOutputStream(new FileOutputStream(pRuta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			File archivo = new File(pRuta);
			objeto = new ObjectOutputStream(new FileOutputStream(archivo));

		}
		objeto.writeObject( escenarios );
		objeto.close( );
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new PersistenciaException( "Error al guardar:" + e.getMessage( ) );
		}
	}


	/**
	 * Carga el estado del sistemas de un archivo serializado. <br>
	 * <b>post: </b> Se inicializó la lista de escenarios a partir del archivo dado. <br>
	 * @param pArchivo Archivo con la información del sistema. pArchivo != null.
	 * @throws PersistenciaException Se lanza una excepción si hay algún error cargando los datos del archivo.
	 */
	public void cargar( String pArchivo ) throws PersistenciaException
	{
		try
		{
			ObjectInputStream objeto = new ObjectInputStream( new FileInputStream( pArchivo ) );
			escenarios = ( ArrayList)objeto.readObject( ) ;
			objeto.close( );
		}
		catch (Exception e)
		{
			throw new PersistenciaException( "error:" + e.getMessage( ) );
		}
	}


	/**
	 * Carga el estado del sistemas de un archivo de texto. <br>
	 * <b>post: </b> Se carga la información de los escenarios y las bandas que se encuentran en el archivo. <br>
	 * @param pNombreArchivo Archivo con la información del sistema. pNombreArchivo != null.
	 * @throws PersistenciaException Se lanza una excepción si hay algún error cargando los datos del archivo.
	 * @throws FormatoArchivoException Si el formato del archivo es erróneo y no puede ser leído.
	 */
	public void importarArchivoTexto( File pNombreArchivo ) throws PersistenciaException, FormatoArchivoException
	{
		try
		{
			File archivo = new File(pNombreArchivo.getAbsolutePath());
			FileReader reader = new FileReader(archivo);
			BufferedReader lector= new BufferedReader( reader );
			String linea = lector.readLine();
			linea = lector.readLine();
			int EscenarioActual = 0;
			while (linea != null)
			{
				String[] valores = linea.split(";");
				if (valores.length == 3)
				{
					String patrocinador = valores[0];
					int presupuesto = Integer.parseInt(valores[1]);
					EscenarioActual = darNumeroDisponible();
					crearEscenario(patrocinador, presupuesto, EscenarioActual);

				}
				else if (valores.length == 5)
				{
					String nombre = valores[0];
					int fans = Integer.parseInt(valores[1]);
					int canciones = Integer.parseInt(valores[2]);
					int costo = Integer.parseInt(valores[3]);
					String imagen = valores[4];
					agregarBandaAEscenario(EscenarioActual, nombre, fans, canciones, costo, imagen);
				}
				linea = lector.readLine();
			}
			lector.close();
			reader.close();
		}
		catch(IOException e)
		{
			throw new FormatoArchivoException("Error: "+ e.getMessage( ) );
		}
		catch(Exception e)
		{
			throw new FormatoArchivoException("Error: " + e.getMessage( ) );
		}


	}



	/**
	 * Genera un reporte con los costos de los escenarios en el festival. <br>
	 * <b>post: </b> El reporte de los costos del festival fue generado. <br>
	 * @param pRuta Ruta donde se desea guardar el archivo con el reporte. pRuta != null && pRutal != "".
	 * @throws PersistenciaException Se lanza una excepción si hay un error en la generación del reporte.
	 */
	public void generarReporte( String pRuta ) throws PersistenciaException
	{
		try
		{
			File archivo = new File (pRuta);
			if (!archivo.exists())
			{
				PrintWriter escritor = new PrintWriter( archivo );
				DateFormat formato= new SimpleDateFormat( "dd-MM-yyyy" );
				Date fechaA= new Date();
				formato.format( fechaA );
				String fecha = fechaA.toString( );

				escritor.println("Reporte costos escenarios");
				escritor.println("Fecha: "+fecha);
				escritor.println("Total escenarios: "+escenarios.size());
				for (int i = 0; i < escenarios.size(); i++)
				{
					escritor.println("=====================================================");
					Escenario actual = (Escenario) escenarios.get(i);
					escritor.println("Escenario "+ actual.darPatrocinador());
					escritor.println("Presupuesto "+ actual.darPresupuesto());
					escritor.println("Presupuesto en uso"+ actual.darCostoAcumulado());
					ArrayList bandas = actual.darBandas();
					escritor.println("Bandas "+ bandas.size());
					for (int j = 0; j < bandas.size(); j++)
					{
						Banda banda = (Banda) bandas.get(j);
						escritor.println(banda.darNombre()+" - Costo: $"+banda.darCosto() );
					}
				}
				escritor.close();
			}

		}
		catch (Exception e)
		{

		}
	}



	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	// TODO Parte 1 Punto G: Documente e implemente la invariante de la clase.
	// Si lo necesita, puede crear método privados adicionales.

	// -----------------------------------------------------------------
	// Puntos de Extensión
	// -----------------------------------------------------------------

	/**
	 * Extensión 1.
	 * @return respuesta1.
	 */
	public String metodo1( )
	{
		return "Respuesta 1";
	}

	/**
	 * Extensión 2.
	 * @return respuesta2.
	 */
	public String metodo2( )
	{
		return "Respuesta 2";
	}
}

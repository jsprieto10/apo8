/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_festival
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPalooza.mundo;

import java.io.File;
import java.util.ArrayList;

/**
 * Festival de m�sica. <br>
 * TODO Parte 1 Punto F: Documente la invariante de la clase.
 */

public class Festival 
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Cantidad m�xima de escenarios en el festival.
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
     * <b>post: </b> Se carg� el estado inicial del festival con la informaci�n del archivo dado por par�metro. <br>
     * Si hay alg�n problema cargando el archivo, lanza excepci�n. <br>
     * Si no existe el archivo seralizado, crea el arrayList vac�o.
     * @param pRuta Ruta del archivo del cual se cargar� el estado del mundo.
     * @throws PersistenciaException Se lanza una excepci�n si hay alg�n error cargando los datos del archivo.
 * @throws CupoMaximoException 
 * @throws ElementoExistenteException 
     */
    public Festival( String pRuta ) throws PersistenciaException, ElementoExistenteException, CupoMaximoException
    {
        escenarios = new ArrayList( );

        crearEscenario( "Movistar", 20000000, 1 );

        agregarBandaAEscenario( 1, "Seis Peatones", 56720, 5, 750000, "./data/imagenes/bandas/rock.png" );
        agregarBandaAEscenario( 1, "Zoey", 4820, 8, 850000, "./data/imagenes/bandas/pop.png" );
        agregarBandaAEscenario( 1, "Randy Travis", 12760, 3, 1200000, "./data/imagenes/bandas/country.png" );
        agregarBandaAEscenario( 1, "Batalla �pica", 34877, 4, 70000, "./data/imagenes/bandas/house.png" );

        crearEscenario( "Adidas", 50000000, 2 );
        agregarBandaAEscenario( 2, "Pl�sticos Adolescentes", 475312, 12, 480000, "./data/imagenes/bandas/pop.png" );
        agregarBandaAEscenario( 2, "Monos Sin Disfraz", 7945138, 6, 1050000, "./data/imagenes/bandas/rock.png" );
        agregarBandaAEscenario( 2, "Korean Kitchen", 687124, 7, 220000, "./data/imagenes/bandas/house.png" );
        agregarBandaAEscenario( 2, "Cultura Prof�tica", 77621, 3, 876000, "./data/imagenes/bandas/reggae.png" );
        agregarBandaAEscenario( 2, "John Waters", 64523, 8, 890000, "./data/imagenes/bandas/country.png" );

        crearEscenario( "Google", 15000000, 3 );
        agregarBandaAEscenario( 3, "Pixie Wings", 56720, 10, 564000, "./data/imagenes/bandas/pop.png" );
        agregarBandaAEscenario( 3, "Fiesta Flow", 587412, 5, 1300000, "./data/imagenes/bandas/reggae.png" );
        agregarBandaAEscenario( 3, "Los Melanc�licos", 22648, 8, 2400000, "./data/imagenes/bandas/rock.png" );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el escenario con el n�mero recibido por par�metro.
     * @param pNumero N�mero del escenario buscado. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
     * @return El escenario con el n�mero especificado.
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
     * Retorna el siguiente n�mero disponible para un escenario.
     * @return N�mero v�lido para asignar a un nuevo escenario. En caso de no haber m�s n�meros disponibles retorna 0.
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
     * Crea un escenario con la informaci�n recibida por par�metro. <br>
     * <b>post: </b> Se cre� el escenario con el n�mero especificado, el patrocinador y el presupuesto dados.
     * @param pPatrocinador Nombre del patrocinador. pPatrocinador != null && pPatrocinador != "".
     * @param pPresupuesto Presupuesto para el escenario. pPresupuesto > 0.
     * @param pNumero N�mero del escenario. pNumero > 0 && pNumero <= 5.
     * @throws ElementoExistenteException Si ya existe un escenario con este patrocinador.
     * @throws CupoMaximoException Si se ha alcanzado el l�mite de escenarios en el festival.
     */
    public void crearEscenario( String pPatrocinador, double pPresupuesto, int pNumero ) throws ElementoExistenteException, CupoMaximoException
   {
        boolean repetido = false;
        if( escenarios.size( ) > CANTIDAD_MAXIMA_ESCENARIOS )
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
     * @param pNumero N�mero del escenario a eliminar. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
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
     * <b>post: </b> Al escenario especificado se agreg� una nueva banda en su repertorio.
     * @param pEscenario N�mero del escenario al cual se agregar� la banda. pNumero > 0 && pNumero <= 5.
     * @param pNombre Nombre de la banda. pNombre != null && pNombre != "".
     * @param pCantidadDeFans Cantidad de fans que tiene la banda. pCantidadDeFans > 0.
     * @param pCantidadDeCanciones Cantidad de canciones que tocar� la banda. pCantidadDeCanciones > 0.
     * @param pCosto Costo de la banda por presentarse en un escenario. pCosto > 0.
     * @param pRutaImagen Ruta de la imagen descriptiva de la banda. pRutaImagen != null && pRutaImagen != "".
     * @throws ElementoExistenteException Si ya existe una banda con este nombre en este escenario.
     * @throws CupoMaximoException Si el escenario al que se desea agregar la banda ha alcanzado su l�mite de bandas.
     */
    public void agregarBandaAEscenario( int pEscenario, String pNombre, int pCantidadDeFans, int pCantidadDeCanciones, double pCosto, String pRutaImagen ) throws ElementoExistenteException, CupoMaximoException
    {
        Escenario escenario = darEscenario( pEscenario );
        boolean resultado = escenario.agregarBanda( pNombre, pCantidadDeFans, pCantidadDeCanciones, pCosto, pRutaImagen );
 
    }

    /**
     * Elimina la banda con el nombre dado del escenario especificado. <br>
     * <b>pre: </b> La banda con este nombre existe en el escenario. <br>
     * <b>post: </b> Se elimin� la banda especificada del escenario.
     * @param pEscenario N�mero del escenario al cual se agregar� la banda. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
     * @param pNombre Nombre de la banda. pNombre != null && pNombre != "". Existe una banda por este nombre.
     * @throws Exception En caso de que se est� intentando eliminar la �ltima banda restante en el escenario.
     */
    public void eliminarBandaEscenario( int pEscenario, String pNombre ) throws Exception
    {
        Escenario escenario = darEscenario( pEscenario );
    }

    /**
     * Ordena ascendentemente las bandas de todos los escenarios por nombre. <br>
     * <b> post: </b> Las bandas en los escenarios fueron ordenadas alfab�ticamente por su nombre.
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
     * <b> post: </b> Las bandas fueron ordenadas en los escenarios seg�n su cantidad de fans.
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
     * <b> post: </b> Las bandas fueron ordenadas en los escenarios seg�n su cantidad de canciones.
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
     * <b>post: </b> Se guard� la lista de escenarios en el archivo dado. <br>
     * @param pRuta Ruta del archivo donde se guarda el estado del sistema. pRuta != null && pRuta != "".
     * @throws PersistenciaException Se lanza una excepci�n si hay alg�n error guardando los datos del archivo.
     */
    public void guardar( String pRuta ) throws PersistenciaException
    {
    	
    }


/**
     * Carga el estado del sistemas de un archivo serializado. <br>
     * <b>post: </b> Se inicializ� la lista de escenarios a partir del archivo dado. <br>
     * @param pArchivo Archivo con la informaci�n del sistema. pArchivo != null.
     * @throws PersistenciaException Se lanza una excepci�n si hay alg�n error cargando los datos del archivo.
     */
    public void cargar( String pArchivo ) throws PersistenciaException
    {
    	
    }
    

/**
     * Carga el estado del sistemas de un archivo de texto. <br>
     * <b>post: </b> Se carga la informaci�n de los escenarios y las bandas que se encuentran en el archivo. <br>
     * @param pNombreArchivo Archivo con la informaci�n del sistema. pNombreArchivo != null.
     * @throws PersistenciaException Se lanza una excepci�n si hay alg�n error cargando los datos del archivo.
     * @throws FormatoArchivoException Si el formato del archivo es err�neo y no puede ser le�do.
     */
    public void importarArchivoTexto( File pNombreArchivo ) throws PersistenciaException, FormatoArchivoException
    {
    	
    }
    


/**
     * Genera un reporte con los costos de los escenarios en el festival. <br>
     * <b>post: </b> El reporte de los costos del festival fue generado. <br>
     * @param pRuta Ruta donde se desea guardar el archivo con el reporte. pRuta != null && pRutal != "".
     * @throws PersistenciaException Se lanza una excepci�n si hay un error en la generaci�n del reporte.
     */
    public void generarReporte( String pRuta ) throws PersistenciaException
    {
    	
    }

    

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    // TODO Parte 1 Punto G: Documente e implemente la invariante de la clase.
    // Si lo necesita, puede crear m�todo privados adicionales.

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Extensi�n 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Extensi�n 2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}

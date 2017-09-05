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

import java.io.Serializable;

/**
 * Banda que se presenta en el festival. <br>
 * TODO Parte 1 Punto A: Documente la invariante de la clase.
 * nombre != null&&nombre != ""
 *  cantidadDeCanciones>0
 *  cantidadDeFans > 0
 *  costo > 0
 *  rutaImagen != null
 */

public class Banda implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5676577076179727829L;

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
    /**
     * Nombre de la banda.
     */
    private String nombre;

    /**
     * Cantidad de fans de la banda.
     */
    private int cantidadDeFans;

    /**
     * Cantidad de canciones en su repertorio.
     */
    private int cantidadDeCanciones;

    /**
     * Valor que se paga a la banda por presentarse en el festival.
     */
    private double costo;

    /**
     * Ruta de la imagen que representa a la banda.
     */
    private String rutaImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una banda con la informaci�n recibida por par�metro. <br>
     * <b> post: </b> Inicializ� el nombre, la cantidad de fans, la cantidad de canciones, el costo y la ruta de la imagen con los valores recibidos por par�metro.
     * @param pNombre Nombre de la banda. pNombre != null y pNombre != "".
     * @param pCantidadDeFans Cantidad de fans que tiene la banda. pCantidadDeFans > 0.
     * @param pCantidadDeCanciones Cantidad de canciones que tocar� la banda. pCantidadDeCanciones > 0.
     * @param pCosto Costo de la banda por tocar en un escenario. pCosto > 0.
     * @param pRutaImagen Ruta de la imagen descriptiva de la banda. pRutaImagen != null y pRutaImagen !="".
     */
    public Banda( String pNombre, int pCantidadDeFans, int pCantidadDeCanciones, double pCosto, String pRutaImagen )
    {
        nombre = pNombre;
        cantidadDeFans = pCantidadDeFans;
        cantidadDeCanciones = pCantidadDeCanciones;
        costo = pCosto;
        rutaImagen = pRutaImagen;
        verificarInvariente();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la banda.
     * @return Nombre de la banda.
     */
    public String darNombre( )
    {
        return nombre;
        
    }

    /**
     * Retorna la cantidad de fans de la banda.
     * @return Cantidad de fans de la banda.
     */
    public int darCantidadDeFans( )
    {
        return cantidadDeFans;
    }

    /**
     * Retorna la cantidad de canciones de la banda.
     * @return Cantidad de canciones de la banda.
     */
    public int darCantidadDeCanciones( )
    {
        return cantidadDeCanciones;
    }

    /**
     * Retorna el valor que se paga a la banda por presentarse en el festival.
     * @return Costo de la banda.
     */
    public double darCosto( )
    {
        return costo;
    }

    /**
     * Retorna la imagen representativa de la banda.
     * @return Ruta de la imagen de la banda.
     */
    public String darRutaImagen( )
    {
        return rutaImagen;
    }

    /**
     * Compara la banda actual y la banda recibida por par�metro por su nombre.
     * @param pBanda Banda a comparar. pBanda != null && banda != "".
     * @return 0 si tienen el mismo nombre, 1 si la banda recibida por par�metro es menor, -1 si es mayor.
     */
    public int compararPorNombre( Banda pBanda )
    {
        // TODO Parte 2 Punto A: Completar el m�todo seg�n su documentaci�n.
    	if (nombre.compareToIgnoreCase( pBanda.nombre ) == 0 )
            return 0;
        else if (nombre.compareToIgnoreCase( pBanda.nombre  ) > 0)
            return 1;
        else
            return -1;
    }

    /**
     * Compara la banda actual y la banda recibida por par�metro por su cantidad de fans.
     * @param pBanda Banda a comparar. pBanda != null && banda != "".
     * @return 0 si tienen la misma cantidad de fans, 1 si la banda recibida por par�metro tiene una cantidad menor, -1 si tiene una cantidad mayor.
     */
    public int compararPorCantidadDeFans( Banda pBanda )
    {
        // TODO Parte 2 Punto B: Completar el m�todo seg�n su documentaci�n.
    	if (cantidadDeFans==pBanda.darCantidadDeFans() )
            return 0;
        else if (cantidadDeFans>pBanda.darCantidadDeFans())
            return 1;
        else
            return -1;
    }

    /**
     * Compara la banda actual y la banda recibida por par�metro por su cantidad de canciones.
     * @param pBanda Banda a comparar. pBanda != null && banda != "".
     * @return 0 si tienen la misma cantidad de canciones, 1 si la banda recibida por par�metro tiene una cantidad menor, -1 si tiene una cantidad mayor.
     */
    public int compararPorCantidadDeCanciones( Banda pBanda )
    {
        // TODO Parte 2 Punto C: Completar el m�todo seg�n su documentaci�n.
    	if (cantidadDeCanciones==pBanda.darCantidadDeCanciones() )
            return 0;
        else if (cantidadDeCanciones>pBanda.darCantidadDeCanciones())
            return 1;
        else
            return -1;
    }

    /**
     * Retorna la cadena que representa la banda.
     * @return Nombre de la banda.
     */
    public String toString( )
    {
        return nombre;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    // TODO Parte 1 Punto B: Implemente y documente el m�todo verificarInvariante.

 private void verificarInvariente() {
        
        assert ( nombre != null ) : "el nombre no puede ser null";
        
        assert (!nombre.equals( "" )) : "el nombre no puede estar vacio";
        
        assert ( cantidadDeFans >0) : "Debe haber la menos un fan";
       
       assert ( costo > 0 ) : "el costo debe ser mayor que 0";
        
        assert ( cantidadDeCanciones >0 ) : "Debe haber al menos una cancion";
        
        assert ( rutaImagen != null ) : "la ruta de la imagen no puede ser null";
 }
}

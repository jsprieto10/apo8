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

import java.util.ArrayList;



/**
 * Un escenario en el cual se presentar�n las bandas del festival. <br>
 * TODO Parte 1 Punto D: Documente la invariante de la clase.
 * 
* <b>inv: </b> <br>
* numero>0
*  presupuesto> 0
*  patrocinador != null
*  bandas != null
*  bandas.lenght<CANTIDAD_MAXIMA_BANDAS
*  
*/

public class Escenario
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Cantidad m�xima de bandas que puede tener un escenario.
     */
    public final static int CANTIDAD_MAXIMA_BANDAS = 10;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * N�mero representativo del escenario.
     */
    private int numero;

    /**
     * Patrocinador del escenario.
     */
    private String patrocinador;

    /**
     * Presupuesto disponible para las bandas.
     */
    private double presupuesto;

    /**
     * Lista de bandas programadas para el escenario.
     */
    private ArrayList<Banda> bandas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un escenario con la informaci�n recibida por par�metro.<br>
     * <b>post:</b> El escenario se inicializ� con el n�mero, el patrocinador y el presupuesto dados.<br>
     * Se inicializ� la lista de bandas.
     * @param pNumero N�mero del escenario. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_BANDAS.
     * @param pPatrocinador Nombre del patrocinador. pPatrocinador != null && pPatrocinador != "".
     * @param pPresupuesto Presupuesto para el escenario. pPresupuesto > 0.
     */
    public Escenario( int pNumero, String pPatrocinador, double pPresupuesto )
    {
        numero = pNumero;
        patrocinador = pPatrocinador;
        presupuesto = pPresupuesto;
        bandas = new ArrayList<Banda>( );
        verificarInvariante();

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el n�mero que identifica al escenario.
     * @return N�mero del escenario.
     */
    public int darNumero( )
    {
        return numero;
    }

    /**
     * Retorna el nombre del patrocinador del escenario.
     * @return Nombre del patrocinador del escenario.
     */
    public String darPatrocinador( )
    {
        return patrocinador;
    }

    /**
     * Retorna el presupuesto del escenario.
     * @return Presupuesto del escenario.
     */
    public double darPresupuesto( )
    {
        return presupuesto;
    }

    /**
     * Retorna la lista de bandas programadas para el escenario.
     * @return Bandas programadas para el escenario.
     */
    public ArrayList darBandas( )
    {
        return bandas;
    }

    /**
     * Calcula el costo total de todas las bandas que se presentan en el escenario.
     * @return Costo acumulado actual.
     */
    public double darCostoAcumulado( )
    {
        double costoActual = 0;
        for( int i = 0; i < bandas.size( ); i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            costoActual += actual.darCosto( );
        }
        return costoActual;
    }

    /**
     * Agrega una banda al escenario. <br>
     * <b>post:</b> Se agreg� a la lista de bandas una nueva banda con las caracter�sticas recibidas por par�metro. <br>
     * @param pNombre Nombre de la banda a agregar. pNombre != null && pNombre != "".
     * @param pCantidadDeFans Cantidad de fans que tiene la banda. pCantidadDeFans > 0.
     * @param pCantidadDeCanciones Cantidad de canciones que tocar� la banda. pCantidadDeCanciones > 0.
     * @param pCosto Costo de la banda por presentarse en un escenario. pCosto > 0.
     * @param pRutaImagen Ruta de la imagen descriptiva de la banda. pRutaImagen != null && pRutaImagen != "".
     * @return True si fue posible agregar la banda, false si ya exist�a una banda con ese nombre, si el costo de la banda excede el presupuesto disponible o si el escenario
     *         ya tiene todas las bandas permitidas.
     */
    public boolean agregarBanda( String pNombre, int pCantidadDeFans, int pCantidadDeCanciones, double pCosto, String pRutaImagen )
    {
        boolean resultado = false;
        if( buscarPorNombre( pNombre ) == null && darCostoAcumulado( ) + pCosto <= presupuesto && bandas.size( ) + 1 <= CANTIDAD_MAXIMA_BANDAS )
        {
            Banda banda = new Banda( pNombre, pCantidadDeFans, pCantidadDeCanciones, pCosto, pRutaImagen );
            bandas.add( banda );
            resultado = true;
        }
        verificarInvariante();
        return resultado;
    }

    /**
     * Elimina la banda con el nombre dado del escenario. <br>
     * <b>post:</b> Se elimin� la banda de la lista <br>
     * @param pNombre Nombre de la banda a eliminar. pNombre != null && pNombre != "".
     * @return True si fue posible eliminar la banda, false en caso de que la banda no exista.
     */
    public boolean eliminarBanda( String pNombre )
    {
        Banda banda = buscarPorNombre( pNombre );
        boolean resultado = false;
        if( banda != null )
        {
            bandas.remove( banda );
            resultado = true;
        }
        verificarInvariante();
        return resultado;
    }

    /**
     * Busca una banda por su nombre.
     * @param pNombre Nombre de la banda buscada. pNombre != null && pNombre != "".
     * @return Banda buscada, null si no hay ninguna banda con el nombre dado.
     */
    public Banda buscarPorNombre( String pNombre )
    {
        // TODO Parte 4 Punto A: Completar el m�todo seg�n su documentaci�n.
    	Banda rpta= null;
    	boolean encontrada= false;
    	for(int i=0; i<bandas.size();i++)
    	{
    		Banda actual= (Banda) bandas.get(i);
    		if (actual.darNombre().equals(pNombre))
    		{
    			encontrada=true;
    			rpta=actual;
    		}
    	}
    	return rpta;
    }

    /**
     * Encuentra la banda que tiene la cantidad de canciones especificada por par�metro, utilizando b�squeda binaria.
     * @param pCantidadCanciones Cantidad de canciones por la que se realizar� la b�squeda. pCantidadCanciones > 0.
     * @return La banda que tiene la cantidad de canciones especificada. Si ninguna banda satisface esta cantidad, retorna null.
     */
    public Banda buscarPorCantidadDeCanciones( int pCantidadCanciones )
    {
        // TODO Parte 4 Punto B: Completar el m�todo seg�n su documentaci�n.
    	ordenarPorCantidadDeCanciones();
    	Banda rpta=null;
    	int inicio=0;
    	int fin= bandas.size()-1;
    	boolean encontrada=false;
    	while(inicio<=fin&&encontrada==false)
    	{
    		int mitad= (inicio+fin)/2;
    		Banda actual= (Banda) bandas.get(mitad);
    		if(actual.darCantidadDeCanciones()==pCantidadCanciones)
    		{
    			encontrada=true;
    			rpta=actual;
    		}
    		else if(actual.darCantidadDeCanciones()<pCantidadCanciones)
    		{
    			inicio=mitad+1;
    		}
    		else
    		{
    			fin=mitad-1;
    		}
    	}
    	return rpta;
    }

    /**
     * Ordena ascendentemente las bandas por nombre, utilizando el algoritmo de selecci�n. <br>
     * <b> post: </b> Las bandas fueron ordenadas alfab�ticamente por su nombre.
     */
    public void ordenarPorNombre( )
    {

    	 for( int i = 0; i < bandas.size( ) ; i++ )
         {
             Banda menor = bandas.get( i );
             int menorPosicion= i;

             for( int j = i+1 ; j < bandas.size( ); j++ )
             {
                 Banda actual = bandas.get( j );
                 if( actual.compararPorNombre( menor ) < 0 )
                 {
                     menor = actual;
                     menorPosicion = j;
                 }
             }
             Banda temp = bandas.get( i );
             bandas.set( i, menor);
             bandas.set( menorPosicion, temp );
         }
    	// TODO Parte 3 Punto A: Completar el m�todo seg�n su documentaci�n.
    }

    /**
     * Ordena descendentemente las bandas por su cantidad de fans, utilizando el algoritmo de inserci�n. <br>
     * <b> post: </b> Las bandas fueron ordenadas seg�n su cantidad de fans.
     */
    public void ordenarPorCantidadDeFans( )
    {
    	for( int i = 1; i < bandas.size( ); i++ )
        {
            Banda porInsertar = ( Banda ) bandas.get( i );
            boolean termino = false;
            for( int j = i; j > 0 && !termino; j-- )
            {
                Banda actual = ( Banda ) bandas.get( j - 1 );
                if( actual.compararPorCantidadDeFans(porInsertar) < 0 )
                {
                    bandas.set( j, actual );
                    bandas.set( j - 1, porInsertar );
                }
                else
                    termino = true;
            }
        }
        // TODO Parte 3 Punto B: Completar el m�todo seg�n su documentaci�n.

    }

    /**
     * Ordena las bandas ascendendemente por su cantidad de canciones, utilizando el algoritmo de burbuja. <br>
     * <b> post: </b> Las bandas fueron ordenadas seg�n su cantidad de canciones.
     */
    public void ordenarPorCantidadDeCanciones( )
    {
    	  for( int i = bandas.size( ); i > 0; i-- )
          {
              for( int j = 0; j < i - 1; j++ )
              {
                  Banda a = ( Banda )bandas.get( j );
                  Banda b = ( Banda )bandas.get( j+1);
                  if( a.compararPorCantidadDeCanciones(b) >= 0  )
                  {
                      bandas.set( j, b );
                      bandas.set( j+1, a );
                  }
              }
          }
        // TODO Parte 3 Punto C: Completar el m�todo seg�n su documentaci�n.

    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    // TODO Parte 1 Punto E: Documente e implemente la invariante de la clase.
    // Si lo necesita, puede crear m�todo privados adicionales.
 private void verificarInvariante()
    
    {
        assert (numero >0):" el numero debe ser mayor a 0";
        assert (patrocinador != null):"el patrocinador no puede ser null";
        assert (!patrocinador.equals("")):" patrocinador invalido";
        assert ( bandas.size() <= CANTIDAD_MAXIMA_BANDAS ):" la capacidad de bandas es excedida";
        
        

    }
}

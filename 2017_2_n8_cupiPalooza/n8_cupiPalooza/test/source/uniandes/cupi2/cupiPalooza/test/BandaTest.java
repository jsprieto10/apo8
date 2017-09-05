/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPalooza
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPalooza.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiPalooza.mundo.Banda;

/**
 * Clase usada para verificar que los m�todos de la clase Banda est�n correctamente implementados.
 */
public class BandaTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Escenario del festival
     */
    private Banda banda;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase Banda. Este m�todo se ejecuta antes de cada m�todo de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        banda = new Banda("Caifanes", 56000, 5, 5000000, "./data/imagenes/bandas/rock.png");
    }

    // -----------------------------------------------------------------
    // M�todos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el m�todo Banda.<br>
     * <b>M�todos a probar:</b><br>
     * Banda<br>
     * darNombre<br>
     * darNumeroDeFans<br>
     * darNumeroDeCanciones<br>
     * darCosto<br>
     * darRutaImagen<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el correctamente, se comprueba cada uno de sus atributos.
     */
    @Test
    public void testBanda( )
    {
        assertTrue("El nombre no es correcto", banda.darNombre( ).equals( "Caifanes" ));
        assertEquals("El n�mero de fans no es correcto", banda.darCantidadDeFans( ), 56000);
        assertEquals("El n�mero de canciones no es correcto", banda.darCantidadDeCanciones( ), 5);
        assertTrue("El costo no es correcto", 5000000 == banda.darCosto( ));
        assertTrue("La ruta de la imagen no es correcta", banda.darRutaImagen( ).equals( "./data/imagenes/bandas/rock.png" ));
    }
    
    /**
     * Prueba 2: Verifica el m�todo compararPorNombre. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorNombre<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen el mismo nombre.<br>
     * 2) La banda contra la cual se compara tiene un nombre lexicogr�ficamente mayor. <br>
     * 3) La banda contra la cual se compara tiene un nombre lexicogr�ficamente menor. <br>
     */
    public void testCompararPorNombre( )
    {
        //Caso de prueba 1
        Banda banda1 = new Banda("Caifanes", 56000, 5, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorNombre( banda1 ), 0);
        
        //Caso de prueba 2
        Banda banda2 = new Banda("Sin Banderas", 56000, 5, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorNombre( banda2 ), -1);
        
        //Caso de prueba 3
        Banda banda3 = new Banda("Ava", 56000, 5, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorNombre( banda3 ), 1);
    }
    
    /**
     * Prueba 3: Verifica el m�todo compararPorCantidadDeFans. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorCantidadDeFans<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen la misma cantidad de fans.<br>
     * 2) La banda contra la cual se compara tiene mayor cantidad de fans. <br>
     * 3) La banda contra la cual se compara tiene menor cantidad de fans. <br>
     */
    public void testCompararPorCantidadDeFans( )
    {
        //Caso de prueba 1
        Banda banda1 = new Banda("Banda 1", 56000, 5, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeFans( banda1 ), 0);
        
        //Caso de prueba 2
        Banda banda2 = new Banda("Banda 2", 560, 5, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeFans( banda2 ), -1);
        
        //Caso de prueba 3
        Banda banda3 = new Banda("Banda 3", 72000, 5, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeFans( banda3 ), 1);
    }
    
    /**
     * Prueba 4: Verifica el m�todo compararPorCantidadDeCanciones. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorCantidadDeCanciones<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen la misma cantidad de canciones.<br>
     * 2) La banda contra la cual se compara tiene mayor cantidad de canciones. <br>
     * 3) La banda contra la cual se compara tiene menor cantidad de canciones. <br>
     */
    public void testCompararPorCantidadDeCanciones( )
    {
        //Caso de prueba 1
        Banda banda1 = new Banda("Banda 1", 56000, 5, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeCanciones( banda1 ), 0);
        
        //Caso de prueba 2
        Banda banda2 = new Banda("Banda 2", 56000, 3, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeCanciones( banda2 ), -1);
        
        //Caso de prueba 3
        Banda banda3 = new Banda("Banda 3", 56000, 7, 5000000, "./data/imagenes/bandas/rock.png");
        assertEquals("El resultado de la comparaci�n no es correcto", banda.compararPorCantidadDeCanciones( banda3 ), 1);
    }

}

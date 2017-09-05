/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogotá - Colombia)
 * Departamento de  Ingeniería  de  Sistemas    y   Computación
 * Licenciado   bajo    el  esquema Academic Free License versión 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPalooza
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPalooza.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiPalooza.mundo.Banda;
import uniandes.cupi2.cupiPalooza.mundo.CupoMaximoException;
import uniandes.cupi2.cupiPalooza.mundo.ElementoExistenteException;
import uniandes.cupi2.cupiPalooza.mundo.Escenario;

/**
 * Clase usada para verificar que los métodos de la clase Escenario estén correctamente implementados.
 */
public class EscenarioTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Escenario del festival
     */
    private Escenario escenario;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase escenario. Este método se ejecuta antes de cada método de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        escenario = new Escenario( 1, "Fujifilm", 3000000 );
    }

    /**
     * Agrega 3 bandas al escenario
     */
    public void setupEscenario2( )
    {
        try
        {
            escenario.agregarBanda( "Aventureros", 125000, 4, 750000, "./data/imagenes/bandas/pop.png" );
            escenario.agregarBanda( "Borbotones", 100000, 3, 850000, "./data/imagenes/bandas/rock.png" );
            escenario.agregarBanda( "Caifanes", 50000, 8, 1200000, "./data/imagenes/bandas/country.png" );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            fail( "No debería generarse excepción." );
        }
    }

    /**
     * Agrega 2 bandas al escenario para dejarlo al máximo de capacidad
     */
    public void setupEscenario3( )
    {
        setupEscenario2( );
        try
        {
            escenario.agregarBanda( "On Planets", 110000, 5, 20000, "./data/imagenes/bandas/pop.png" );
            escenario.agregarBanda( "Other", 80000, 7, 20000, "./data/imagenes/bandas/rock.png" );
        }
        catch( Exception e )
        {
            fail( "No debería generarse excepción." );
        }
    }

    // -----------------------------------------------------------------
    // Métodos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el método Escenario.<br>
     * <b>Métodos a probar:</b><br>
     * Festival<br>
     * darBandas<br>
     * darNumero<br>
     * darPatrocinador<br>
     * darPresupuesto<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el correctamente.<br>
     */
    @Test
    public void testEscenario( )
    {
        assertNotNull( "No se inicializó el arreglo de bandas correctamente.", escenario.darBandas( ) );
        assertEquals( "El arreglo de bandas no está vacío", escenario.darBandas( ).size( ), 0 );
        assertEquals( "El número del escenario no es correcto", 1, escenario.darNumero( ) );

        assertNotNull( "El patrocinador del escenario debería existir", escenario.darPatrocinador( ) );
        assertTrue( "El presupuesto actual debe ser mayor a 0", 0 < escenario.darPresupuesto( ) );

    }

    /**
     * <b>Prueba 3:</b> Verifica el método darCostoAcumulado.<br>
     * <b>Métodos a probar:</b><br>
     * darCostoAcumulado<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay ninguna banda en el escenario.<br>
     * 2. Hay cinco bandas en el escenario.
     */
    @Test
    public void testDarCostoAcumulado( )
    {
        // Caso de prueba 1
        assertTrue( "El costo acumulado no es correcto", 0 == escenario.darCostoAcumulado( ) );

        // Caso de prueba 2
        setupEscenario3( );
        assertTrue( "El costo acumulado no es correcto", 2840000 == escenario.darCostoAcumulado( ) );
    }

    /**
     * <b>Prueba 4:</b> Verifica el método agregarBanda.<br>
     * <b>Métodos a probar:</b><br>
     * agregarBanda<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe una banda con el nombre dado.<br>
     * 2. Existe una banda con el nombre dado y se puede agregar.<br>
     * 3. No hay presupuesto suficiente para agregar la banda.<br>
     * 4. El escenario ya tiene todas las bandas permitidas.
     */
    @Test
    public void testAgregarBanda( )
    {
        setupEscenario2( );
        // Caso de prueba 1
        try
        {
            escenario.agregarBanda( "Aventureros", 10, 10, 50000, "rutaImagen" );
            fail( "Debería generar excepción pues existe una banda por este nombre" );
        }
        catch( ElementoExistenteException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta" );
        }

        // Caso de prueba 2
        try
        {
            escenario.agregarBanda( "Otra banda", 10, 10, 100000, "rutaImagen" );
            assertNotNull( "El escenario debería tener la banda", escenario.buscarPorNombre( "Otra banda" ) );
        }
        catch( Exception e )
        {
            fail( "Debió agregar la banda exitosamente." );
        }

        // Caso de prueba 3
        try
        {
            escenario.agregarBanda( "Otra banda 2", 10, 10, 300000, "rutaImagen" );
            fail( "No debería haber presupuesto suficiente para agregar la banda" );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta" );
        }

        // Caso de prueba 4
        try
        {
            escenario.agregarBanda( "Otra banda 3", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 4", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 5", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 6", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 7", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 8", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 9", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 10", 10, 10, 100, "rutaImagen" );
            escenario.agregarBanda( "Otra banda 11", 10, 10, 300, "rutaImagen" );
            fail( "No hay más espacio en el escenario, no debió agregar la banda" );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepción
        }
        catch( Exception e )
        {
            fail( "No genera la excepción correcta" );
        }

    }

    /**
     * <b>Prueba 5:</b> Verifica el método eliminarBanda.<br>
     * <b>Métodos a probar:</b><br>
     * eliminarBanda<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda a eliminar existe en el escenario. <br>
     * 2. No permite eliminar la última banda del escenario.
     */
    @Test
    public void testEliminarBanda( )
    {
        setupEscenario2( );
        // Caso de prueba 1
        try
        {
            escenario.eliminarBanda( "Caifanes" );
            assertNull( "La bandan no debería existir en el escenario", escenario.buscarPorNombre( "On Planets" ) );
        }
        catch(Exception e)
        {
            fail( "No debería generar excepción" );
        }
        // Caso de prueba 2
        try
        {
            escenario.eliminarBanda( "Borbotones" );
            escenario.eliminarBanda( "Aventureros" );
            fail( "No debería eliminar la banda Aventureros pues es la última en el escenario" );
        }
        catch(Exception e)
        {
            //Debe generar excepción
        }

    }

    /**
     * <b>Prueba 6:</b> Verifica el método buscarPorNombre.<br>
     * <b>Métodos a probar:</b><br>
     * buscarPorNombre<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda buscada existe en el escenario.<br>
     * 2. La banda buscada no existe en el escenario.
     */
    @Test
    public void testBuscarPorNombre( )
    {
        setupEscenario2( );

        // Caso de prueba 1
        assertNotNull( "Debió encontrar la banda", escenario.buscarPorNombre( "Caifanes" ) );
        assertEquals( "La banda encontrada no es la correcta", escenario.buscarPorNombre( "Caifanes" ).darCantidadDeFans( ), 50000 );

        // Caso de prueba 2
        assertNull( "No debió encontrar la banda pues no existe", escenario.buscarPorNombre( "Los Inigualables" ) );
    }

    /**
     * <b>Prueba 7:</b> Verifica el método buscarPorCantidadDeCanciones.<br>
     * <b>Métodos a probar:</b><br>
     * buscarPorCantidadDeCanciones<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existe una banda con la cantidad de canciones buscada.<br>
     * 2. No existe una banca con la cantidad de canciones buscada.
     */
    @Test
    public void testBuscarPorCantidadDeCanciones( )
    {
        setupEscenario2( );

        // Caso de prueba 1
        assertNotNull( "Debió encontrar la banda", escenario.buscarPorCantidadDeCanciones( 8 ) );
        assertTrue( "La banda encontrada no es la correcta", escenario.buscarPorCantidadDeCanciones( 8 ).darNombre( ).equals( "Caifanes" ) );

        // Caso de prueba 2
        assertNull( "No debió encontrar la banda pues no existe", escenario.buscarPorCantidadDeCanciones( 15 ) );
    }

    /**
     * <b>Prueba 8:</b> Verifica el método ordenarPorNombre.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorNombre<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen 5 bandas en el escenario. <br>
     */
    @Test
    public void testOrdenarPorNombre( )
    {
        // Caso de prueba 1
        setupEscenario3( );
        escenario.ordenarPorNombre( );

        ArrayList bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda número " + i + " no está en la posición correcta", actual.compararPorNombre( siguiente ), -1 );
        }
    }

    /**
     * <b>Prueba 9:</b> Verifica el método ordenarPorCantidadDeFans.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorCantidadDeFans<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen 5 bandas en el escenario.
     */
    @Test
    public void testOrdenarPorCantidadDeFans( )
    {
        // Caso de prueba 1
        setupEscenario3( );
        escenario.ordenarPorCantidadDeFans( );

        ArrayList bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda número " + i + " no está en la posición correcta", actual.compararPorCantidadDeFans( siguiente ), 1 );
        }
    }

    /**
     * <b>Prueba 10:</b> Verifica el método ordenarPorCantidadDeCanciones.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorCantidadDeCanciones<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen 5 bandas en el escenario.
     */
    @Test
    public void testOrdenarPorCantidadDeCanciones( )
    {
        // Caso de prueba 1
        setupEscenario3( );
        escenario.ordenarPorCantidadDeCanciones( );

        ArrayList bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda número " + i + " no está en la posición correcta", actual.compararPorCantidadDeCanciones( siguiente ), -1 );
        }
    }

}

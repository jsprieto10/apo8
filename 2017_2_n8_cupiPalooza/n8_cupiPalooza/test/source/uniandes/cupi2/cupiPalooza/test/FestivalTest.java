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

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiPalooza.mundo.Banda;
import uniandes.cupi2.cupiPalooza.mundo.CupoMaximoException;
import uniandes.cupi2.cupiPalooza.mundo.ElementoExistenteException;
import uniandes.cupi2.cupiPalooza.mundo.Escenario;
import uniandes.cupi2.cupiPalooza.mundo.Festival;
import uniandes.cupi2.cupiPalooza.mundo.PersistenciaException;

/**
 * Clase usada para verificar que los m�todos de la clase Festival est�n correctamente implementados.
 */
public class FestivalTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Festival de m�sica con sus escenarios y bandas
     */
    private Festival festival;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase festival. Este m�todo se ejecuta antes de cada m�todo de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        try
        {
            festival = new Festival( "./test/data/archivoTest.data" );
            festival.importarArchivoTexto( new File( "./data/archivo1.txt" ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Crea un festival, asignando un patrocinador al cuarto escenario y tres bandas al mismo.
     */
    public void setupEscenario2( )
    {
        try
        {
            festival.crearEscenario( "Huawei", 20000000, 4 );
            festival.agregarBandaAEscenario( 4, "Aventureros", 125000, 4, 750000, "./data/imagenes/bandas/pop.png" );
            festival.agregarBandaAEscenario( 4, "Borbotones", 100000, 3, 850000, "./data/imagenes/bandas/rock.png" );
            festival.agregarBandaAEscenario( 4, "Caifanes", 50000, 8, 1200000, "./data/imagenes/bandas/country.png" );
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    /**
     * Crea un festival, asignando un patrocinador al quinto escenario y tres bandas al mismo.
     */
    public void setupEscenario3( )
    {
        try
        {
            festival.crearEscenario( "Sony", 600000, 5 );
            festival.agregarBandaAEscenario( 5, "On Planets", 125000, 4, 200000, "./data/imagenes/bandas/pop.png" );
            festival.agregarBandaAEscenario( 5, "Other", 100000, 3, 200000, "./data/imagenes/bandas/rock.png" );
            festival.agregarBandaAEscenario( 5, "Airia", 50000, 8, 100000, "./data/imagenes/bandas/country.png" );
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    // -----------------------------------------------------------------
    // M�todos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el m�todo Festival.<br>
     * <b>M�todos a probar:</b><br>
     * Festival<br>
     * darEscenario<br>
     * darEscenarios<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el festival.
     */
    @Test
    public void testFestival( )
    {
        setupEscenario1( );
        Escenario escenario1 = festival.darEscenario( 1 );
        Escenario escenario2 = festival.darEscenario( 2 );
        Escenario escenario3 = festival.darEscenario( 3 );
        Escenario escenario4 = festival.darEscenario( 4 );
        Escenario escenario5 = festival.darEscenario( 5 );

        assertNotNull( "No se inicializ� el escenario 1 correctamente.", escenario1 );
        assertNotNull( "No se inicializ� el escenario 2 correctamente.", escenario2 );
        assertNotNull( "No se inicializ� el escenario 3 correctamente.", escenario3 );
        assertNull( "No se inicializ� el escenario 4 correctamente.", escenario4 );
        assertNull( "No se inicializ� el escenario 5 correctamente.", escenario5 );

        assertEquals( "El n�mero de bandas en el escenario no es correcto", 3, festival.darEscenarios( ).size( ), 0 );

        assertEquals( "El n�mero del escenario 1 no es correcto", 1, escenario1.darNumero( ), 0 );
        assertEquals( "El n�mero del escenario 2 no es correcto", 2, escenario2.darNumero( ), 0 );
        assertEquals( "El n�mero del escenario 3 no es correcto", 3, escenario3.darNumero( ), 0 );

        assertTrue( "El patrocinador del escenario 1 no es correcto", escenario1.darPatrocinador( ).equals( "Movistar" ) );
        assertTrue( "El patrocinador del escenario 2 no es correcto", escenario2.darPatrocinador( ).equals( "Adidas" ) );
        assertTrue( "El patrocinador del escenario 3 no es correcto", escenario3.darPatrocinador( ).equals( "Google" ) );

        assertEquals( "El n�mero de bandas del escenario 1 no es correcto", 4, escenario1.darBandas( ).size( ), 0 );
        assertEquals( "El n�mero de bandas del escenario 2 no es correcto", 5, escenario2.darBandas( ).size( ), 0 );
        assertEquals( "El n�mero de bandas del escenario 1 no es correcto", 3, escenario3.darBandas( ).size( ), 0 );

    }

    /**
     * <b>Prueba 2:</b> Verifica el m�todo darNumeroDisponible.<br>
     * <b>M�todos a probar:</b><br>
     * darNumeroDisponible<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen los escenarios 1, 2 y 3.<br>
     * 2. Existen todos los escenarios.<br>
     * 3. Existen los escenarios 1, 3 y 5.<br>
     */
    @Test
    public void testDarNumeroDisponible( )
    {
        // Caso de prueba 1
        assertEquals( "El siguiente n�mero disponible es incorrecto.", 4, festival.darNumeroDisponible( ), 0 );

        // Caso de prueba 2
        setupEscenario2( );
        setupEscenario3( );
        assertEquals( "El siguiente n�mero disponible es incorrecto, no hay m�s escenarios", 0, festival.darNumeroDisponible( ), 0 );

        // Caso de prueba 3
        festival.eliminarEscenario( 4 );
        assertEquals( "El siguiente n�mero disponible es incorrecto.", 4, festival.darNumeroDisponible( ), 0 );
        festival.eliminarEscenario( 2 );
        assertEquals( "El siguiente n�mero disponible es incorrecto.", 2, festival.darNumeroDisponible( ), 0 );

    }

    /**
     * <b>Prueba 3:</b> Verifica el m�todo crearEscenario.<br>
     * <b>M�todos a probar:</b><br>
     * crearEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe un escenario con el patrocinador dado.<br>
     * 2. No existe un escenario con el patrocinador dado.<br>
     * 3. Ya existen todos los escenarios permitidos.<br>
     */
    @Test
    public void testCrearEscenario( )
    {
        setupEscenario2( );

        // Caso de prueba 1
        try
        {
            festival.crearEscenario( "Huawei", 50000, 5 );
            fail( "No debi� agregar el patrocinador pues est� repetido" );
        }
        catch( ElementoExistenteException e )
        {
            // Debe generar excepci�n
        }
        catch( Exception e )
        {
            fail( "No genera la excepci�n correcta" );
        }

        // Caso de prueba 2
        try
        {
            festival.crearEscenario( "Kinder Eggs", 50000, 5 );
        }
        catch( Exception e )
        {
            fail( "Debi� asignar el patrocinador" );
        }

        // Caso de prueba 3
        try
        {
            festival.crearEscenario( "Oracle", 50000, 7 );
            fail( "Se alcanz� el n�mero m�ximo de escenarios, no deber�a crear el escenario" );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepci�n
        }
        catch( Exception e )
        {
            fail( "No genera la excepci�n correcta" );
        }

    }

    /**
     * <b>Prueba 4:</b> Verifica el m�todo eliminarEscenario.<br>
     * <b>M�todos a probar:</b><br>
     * eliminarEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. El escenario a eliminar no existe.<br>
     * 3. El escenario a eliminar existe.<br>
     */
    @Test
    public void testEliminarEscenario( )
    {
        setupEscenario2( );
        // Caso de prueba 1
        boolean resultado = festival.eliminarEscenario( 6 );
        assertFalse( "No debi� eliminar el patrocinador pues el escenario no existe", resultado );

        // Caso de prueba 2
        resultado = festival.eliminarEscenario( 4 );
        assertTrue( "Debi� eliminar el escenario", resultado );

    }

    /**
     * <b>Prueba 5:</b> Verifica el m�todo agregarBandaAEscenario.<br>
     * <b>M�todos a probar:</b><br>
     * agregarBandaAEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe una banda con el mismo nombre en el escenario.<br>
     * 2. No existe una banda con ese nombre en el escenario.<br>
     * 3. El presupuesto no alcanza para agregar la banda.<br>
     * 4. Se alcanz� el l�mite de bandas en el escenario.
     */
    @Test
    public void testAgregarBandaAEscenario( )
    {
        setupEscenario3( );
        // Caso de prueba 1
        Escenario escenario = festival.darEscenario( 5 );
        try
        {
            festival.agregarBandaAEscenario( 5, "On Planets", 10, 10, 50000, "rutaImagen" );
            fail( "No debi� agregar la banda pues ya existe en este escenario" );
        }
        catch( ElementoExistenteException e )
        {
            // Debe generar excepci�n
        }
        catch( Exception e )
        {
            fail( "No genera la excepci�n correcta" );
        }

        // Caso de prueba 2
        try
        {
            festival.agregarBandaAEscenario( 5, "Otra banda", 10, 10, 50000, "rutaImagen" );
            assertNotNull( "El escenario deber�a tener la banda", escenario.buscarPorNombre( "Otra banda" ) );
        }
        catch( Exception e )
        {
            fail( "Debi� agregar la banda exitosamente" );
        }

        // Caso de prueba 3
        try
        {
            festival.agregarBandaAEscenario( 5, "Otra banda 2", 10, 10, 60000, "rutaImagen" );
            fail( "No deber�a haber presupuesto suficiente para agregar la banda" );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepci�n
        }
        catch( Exception e )
        {
            fail( "No genera la excepci�n correcta" );
        }

        // Caso de prueba 4
        try
        {
            festival.agregarBandaAEscenario( 5, "Otra banda 3", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 4", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 5", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 6", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 7", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 8", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 9", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 10", 10, 10, 600, "rutaImagen" );
            festival.agregarBandaAEscenario( 5, "Otra banda 11", 10, 10, 600, "rutaImagen" );
            fail( "No debe haber mas espacio en el escenario para agregar la banda" );
        }
        catch( CupoMaximoException e )
        {
            // Debe generar excepci�n
        }
        catch( Exception e )
        {
            fail( "No genera la excepci�n correcta" );
        }

    }

    /**
     * <b>Prueba 6:</b> Verifica el m�todo eliminarBandaEscenario.<br>
     * <b>M�todos a probar:</b><br>
     * eliminarBandaEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda a eliminar existe en el escenario.
     */
    @Test
    public void testEliminarBandaEscenario( )
    {
        setupEscenario3( );
        // Caso de prueba 1
        try
        {
            Escenario escenario = festival.darEscenario( 5 );
            festival.eliminarBandaEscenario( 5, "On Planets" );
            assertNull( "La bandan no deber�a existir en el escenario", escenario.buscarPorNombre( "On Planets" ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n" );
        }
        
        // Caso de prueba 2
        try
        {
            festival.eliminarBandaEscenario( 5, "Other" );
            festival.eliminarBandaEscenario( 5, "Airia" );
            fail( "No deber�a eliminar la banda Airia pues es la �ltima en el escenario" );
        }
        catch(Exception e)
        {
            //Debe generar excepci�n
        }
    }

    /**
     * <b>Prueba 7:</b> Verifica el m�todo ordenarPorNombre.<br>
     * <b>M�todos a probar:</b><br>
     * ordenarPorNombre<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se ordenan las bandas del escenario de prueba 1.<br>
     * 2. Se ordenan las bandas del escenario de prueba 2.<br>
     * 3. Se ordenan las bandas del escenario de prueba 3.
     */
    @Test
    public void testOrdenarPorNombre( )
    {
        // Caso de prueba 1
        festival.ordenarPorNombre( );
        Escenario escenario = festival.darEscenario( 1 );

        ArrayList bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorNombre( siguiente ), -1 );
        }

        // Caso de prueba 2
        setupEscenario2( );
        festival.ordenarPorNombre( );
        escenario = festival.darEscenario( 4 );
        bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorNombre( siguiente ), -1 );
        }

        // Caso de prueba 3
        setupEscenario3( );
        festival.ordenarPorNombre( );
        escenario = festival.darEscenario( 5 );
        bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorNombre( siguiente ), -1 );
        }
    }

    /**
     * <b>Prueba 8:</b> Verifica el m�todo ordenarPorCantidadDeFans.<br>
     * <b>M�todos a probar:</b><br>
     * ordenarPorCantidadDeFans<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se ordenan las bandas del escenario de prueba 1.<br>
     * 2. Se ordenan las bandas del escenario de prueba 2.<br>
     * 3. Se ordenan las bandas del escenario de prueba 3.
     */
    @Test
    public void testOrdenarPorCantidadDeFans( )
    {
        // Caso de prueba 1
        festival.ordenarPorCantidadDeFans( );
        Escenario escenario = festival.darEscenario( 1 );
        ArrayList bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorCantidadDeFans( siguiente ), 1 );
        }
        // Caso de prueba 2
        setupEscenario2( );
        festival.ordenarPorCantidadDeFans( );
        escenario = festival.darEscenario( 4 );
        bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorCantidadDeFans( siguiente ), 1 );
        }

        // Caso de prueba 3
        setupEscenario3( );
        festival.ordenarPorCantidadDeFans( );
        bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorCantidadDeFans( siguiente ), 1 );
        }
    }

    /**
     * <b>Prueba 9:</b> Verifica el m�todo ordenarPorCantidadDeCanciones.<br>
     * <b>M�todos a probar:</b><br>
     * ordenarPorCantidadDeCanciones<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se ordenan las bandas del escenario de prueba 1.<br>
     * 2. Se ordenan las bandas del escenario de prueba 2.<br>
     * 3. Se ordenan las bandas del escenario de prueba 3.
     */
    @Test
    public void testOrdenarPorCantidadDeCanciones( )
    {
        // Caso de prueba 1
        festival.ordenarPorCantidadDeCanciones( );
        Escenario escenario = festival.darEscenario( 1 );
        ArrayList bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorCantidadDeCanciones( siguiente ), -1 );
        }

        // Caso de prueba 2
        setupEscenario2( );
        festival.ordenarPorCantidadDeCanciones( );
        escenario = festival.darEscenario( 4 );
        bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorCantidadDeCanciones( siguiente ), -1 );
        }

        // Caso de prueba 3
        setupEscenario3( );
        festival.ordenarPorCantidadDeCanciones( );
        escenario = festival.darEscenario( 5 );
        bandas = escenario.darBandas( );

        for( int i = 0; i < bandas.size( ) - 1; i++ )
        {
            Banda actual = ( Banda )bandas.get( i );
            Banda siguiente = ( Banda )bandas.get( i + 1 );

            assertEquals( "La banda n�mero " + i + " no est� en la posici�n correcta", actual.compararPorCantidadDeCanciones( siguiente ), -1 );
        }
    }

    /**
     * <b>Prueba 9:</b> Verifica el m�todo cargar.<br>
     * <b>M�todos a probar:</b><br>
     * cargar<br>
     * <b>Casos de prueba:</b><br>
     * 1. No se carga el archivo porque no es v�lido.<br>
     * 2. Se carga el archivo.
     */
    @Test
    public void testCargar( )
    {
        // Caso de prueba 1
        try
        {
            festival.cargar( "./data/archivo1.txt" );
            fail( "No debi� cargar el estado pues el tipo del archivo no es v�lido." );
        }
        catch( PersistenciaException e )
        {
            // Debe generar excepci�n
        }

        // Caso de prueba 2
        try
        {
            festival.guardar( "./data/archivoTest.data" );
            festival.cargar( "./data/archivoTest.data" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debi� generar excepci�n pues este archivo es v�lido" );
        }
    }

    /**
     * <b>Prueba 10:</b> Verifica el m�todo guardar.<br>
     * <b>M�todos a probar:</b><br>
     * guardar<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se guarda el archivo.
     */
    @Test
    public void testGuardar( )
    {
        // Caso de prueba 1
        try
        {
            festival.guardar( "./data/archivoTest.data" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debi� generar excepci�n pues este archivo es v�lido" );
        }
    }
}

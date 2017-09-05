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

package uniandes.cupi2.cupiPalooza.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel con las opciones de la aplicaci�n.
 */
@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ordenar las bandas del escenario.
     */
    public final static String ORDENAR = "Ordenar las bandas";

    /**
     * Comando para buscar una banda.
     */
    public final static String BUSCAR = "Buscar una banda";

    /**
     * Comando para cargar el estado del festival.
     */
    public final static String CARGAR = "Cargar";

    /**
     * Comando para generar un reporte.
     */
    public final static String REPORTE = "Generar Reporte";

    /**
     * Comando para ordenar las bandas por nombre.
     */
    public final static String ORDENAR_NOMBRE = "Por Nombre";

    /**
     * Comando para ordenar las bandas por su cantidad de fans.
     */
    public final static String ORDENAR_FANS = "Por Cantidad de Fans";

    /**
     * Comando para ordenar las bandas por su cantidad de canciones.
     */
    public final static String ORDENAR_CANCIONES = "Por Cantidad de Canciones";

    /**
     * Comando para buscar una banda por su nombre.
     */
    public final static String BUSCAR_POR_NOMBRE = "Por Nombre";

    /**
     * Comando para buscar una banda por su cantidad de canciones.
     */
    public final static String BUSCAR_POR_CANCIONES = "Por Cantidad de Canciones";

    /**
     * Comando para la opci�n 1.
     */
    public final static String OPC_1 = "Opci�n 1";

    /**
     * Comando para la opci�n 2.
     */
    public final static String OPC_2 = "Opci�n 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia principal de la aplicaci�n.
     */
    private InterfazFestival interfaz;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Combo box con las opciones de ordenamiento.
     */
    // TODO : Declarar el atributo comboOrdenamiento

    /**
     * Combo box con las opciones de b�squeda.
     */
    // TODO : Declarar el atributo comboBusqueda 

    /**
     * Bot�n para ordenar las bandas.
     */
    private JButton btnOrdenar;

    /**
     * Bot�n para buscar una banda.
     */
    private JButton btnBuscar;

    /**
     * Bot�n para cargar.
     */
    private JButton btnCargar;

    /**
     * Bot�n para generar un reporte.
     */
    private JButton btnGenerarReporte;

    /**
     * Bot�n para llamar la opci�n 1.
     */
    private JButton btnOpc1;

    /**
     * Bot�n para llamar la opci�n 2.
     */
    private JButton btnOpc2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con los botones y sus respectivos comandos.
     * @param pPrincipal instancia principal de la aplicaci�n.
     */
    public PanelOpciones( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new BorderLayout( ) );
        JPanel acciones = new JPanel( );
        acciones.setLayout( new GridLayout( 2, 2 ) );
        acciones.setBorder( new TitledBorder( "Acciones" ) );
        add( acciones, BorderLayout.CENTER );
        JPanel opciones = new JPanel( );
        opciones.setLayout( new GridLayout( 2, 2 ) );
        opciones.setBorder( new TitledBorder( "Opciones" ) );
        add( opciones, BorderLayout.SOUTH );

        comboOrdenamiento = new JComboBox( );
        //TODO : A�adir los items de los tipos de ordenamiento a comboOrdenamiento
        acciones.add( comboOrdenamiento );

        btnOrdenar = new JButton( ORDENAR );
        btnOrdenar.addActionListener( this );
        btnOrdenar.setActionCommand( ORDENAR );
        acciones.add( btnOrdenar );

        comboBusqueda = new JComboBox( );
        //TODO : A�adir los items de los tipos de busqueda a comoBusqueda
        acciones.add( comboBusqueda );

        btnBuscar = new JButton( BUSCAR );
        btnBuscar.addActionListener( this );
        btnBuscar.setActionCommand( BUSCAR );
        acciones.add( btnBuscar );

        btnCargar = new JButton( CARGAR );
        btnCargar.addActionListener( this );
        btnCargar.setActionCommand( CARGAR );
        opciones.add( btnCargar );

        btnGenerarReporte = new JButton( REPORTE );
        btnGenerarReporte.addActionListener( this );
        btnGenerarReporte.setActionCommand( REPORTE );
        opciones.add( btnGenerarReporte );

        btnOpc1 = new JButton( OPC_1 );
        btnOpc1.addActionListener( this );
        btnOpc1.setActionCommand( OPC_1 );
        opciones.add( btnOpc1 );

        btnOpc2 = new JButton( OPC_2 );
        btnOpc2.addActionListener( this );
        btnOpc2.setActionCommand( OPC_2 );
        opciones.add( btnOpc2 );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( ORDENAR ) )
        {
            //TODO Crear la variable tipo String ordenamiento para obtener el tipo de ordenamiento seleccionado de comboOrdenamiento
            if( ordenamiento.equals( ORDENAR_NOMBRE ) )
            {
                interfaz.ordenarPorNombre( );
            }
            else if( ordenamiento.equals( ORDENAR_FANS ) )
            {
                interfaz.ordenarPorCantidadDeFans( );
            }
            else if( ordenamiento.equals( ORDENAR_CANCIONES ) )
            {
                interfaz.ordenarPorCantidadDeCanciones( );
            }
        }
        else if( comando.equals( BUSCAR ) )
        {
            //TODO Crear la variable tipo String busqueda para obtener el tipo de b�squeda seleccionado de comboBusqueda
			if( busqueda.equals( BUSCAR_POR_NOMBRE ) )
            {
                interfaz.buscarBandaPorNombre( );
            }
            else if( busqueda.equals( BUSCAR_POR_CANCIONES ) )
            {
                interfaz.buscarBandaPorCantidadDeCanciones( );
            }
        }
        else if( comando.equals( CARGAR ) )
        {
            interfaz.cargar( );
        }
        else if( comando.equals( REPORTE ) )
        {
            interfaz.generarReporte( );
        }
        else if( comando.equals( OPC_1 ) )
        {
            interfaz.reqFuncOpcion1( );
        }
        else if( comando.equals( OPC_2 ) )
        {
            interfaz.reqFuncOpcion2( );
        }
    }
}

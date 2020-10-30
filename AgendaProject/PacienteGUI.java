import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class PacienteGUI extends JFrame implements ActionListener
{
	public static LoginGUI login;
 	private JTextField tfNombre, tfCorreo, tfSintomas, tfTratamiento;
    private JButton    bCapturar, bConsultar, bConsultarFecha, bModificar, bCancelar, bActualizar, bRegresar, bSalir;
    
    private JComboBox comboTipoCita, comboHoraLlegada, comboPago, comboEstado, comboDia, comboMes, comboAno;
    private String opcionesDia[]={"SELECCIONAR","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private String opcionesMes[]={"SELECCIONAR","ENERO","FEBRERO","MARZO","ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE","OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    private String opcionesAno[]={"SELECCIONAR","2019", "2020", "2021"};
    private String opcionesHoraLlegada[]={"SELECCIONAR","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"};
    private String opcionesPago[] = {"SELECCIONAR","TARJETA", "EFECTIVO"};
    
    private JTextArea  taDatos;
    
	//  private CitaAD citaad = new CitaAD();
    private AgendaAD agendaad= new AgendaAD();
    private JPanel panel1, panel2;
	
    public PacienteGUI()
    {
    	super("Agenda de citas Dr.");
       	tfNombre = new JTextField();
        tfCorreo = new JTextField();
        
        bCapturar 				= new JButton("Capturar");
        bConsultar				= new JButton("Consulta General");
        bConsultarFecha			= new JButton("Consultar por fecha: ");
        bModificar				= new JButton("Modificar datos: ");
        bCancelar				= new JButton("Cancelar");
        bActualizar				= new JButton("Actualizar datos");
     
        bSalir   				= new JButton("Salir");
        bRegresar				= new JButton("Cerrar Sesion");
        // Adicionar addActionListener(this) a los JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarFecha.addActionListener(this);
        bModificar.addActionListener(this);
        bCancelar.addActionListener(this);
        bActualizar.addActionListener(this);     
        
        bSalir.addActionListener(this);
        bRegresar.addActionListener(this);
        
        bCancelar.setEnabled(false);
        bActualizar.setEnabled(false);
        
        comboHoraLlegada = new JComboBox(opcionesHoraLlegada);
        comboPago = new JComboBox(opcionesPago);
        comboDia= new JComboBox(opcionesDia);
        comboMes= new JComboBox (opcionesMes);
        comboAno= new JComboBox (opcionesAno);

        
        taDatos   = new JTextArea(10,30);
        
        panel1    = new JPanel();
        panel2   = new JPanel();
        
        // 2. Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(16,2));
        panel2.setLayout(new FlowLayout());
        
        // 3. Adicionar los objetos de los atributos a los JPanels
        panel1.add(new JLabel("Nombre: "));
        panel1.add(tfNombre);
        String nombre=agendaad.nombre;
        tfNombre.setText(nombre);
        tfNombre.setEditable(false);
        
        panel1.add(new JLabel("Correo: "));
        panel1.add(tfCorreo);
        String correo=agendaad.mail;
        tfCorreo.setText(correo);
        tfCorreo.setEditable(false);
        
        panel1.add(new JLabel("Dia: "));
        panel1.add(comboDia);
        panel1.add(new JLabel("Mes: "));
        panel1.add(comboMes);
        panel1.add(new JLabel("Ano: "));
        panel1.add(comboAno);
        panel1.add(new JLabel("Hora Llegada: "));
        panel1.add(comboHoraLlegada);
        panel1.add(new JLabel("Método de Pago: "));
        panel1.add(comboPago);
       
//     	bCapturar, bConsultar, bConsultarNoPaciente, bModificar, bCancelar, bActualizar, bSalir, bCCotizar, bCancelarCita;
        panel1.add(bCapturar);
        panel1.add(bConsultar);
//      panel1.add(bConsultarFecha); 
        panel1.add(bModificar);
        panel1.add(bCancelar);
        panel1.add(bActualizar);
  
        panel1.add(bSalir);
        panel1.add(bRegresar);
        
        // 4. Adicionar panel1 y taDatos al panel2
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        // 5. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(500,900);
        //setVisible(true);
    }
    
    private String obtenerDatos()
    {
    	/*
    	 *Lee los datos del GUI, de text fields y combos
    	 *Si las variables no son visibles para el usuario (tratamiento y sintomas), se inicializa a NINGUNO
    	 **/
        String datos = "";
		//Nombre, Correo, Fecha, Hora, Tipo de cita [antes, motivo], Método de pago
        String nombre 		= tfNombre.getText();
        String correo		=tfCorreo.getText();
        String dia 			= (String)comboDia.getSelectedItem();
        String mes 			= (String)comboMes.getSelectedItem();
        String ano 			= (String)comboAno.getSelectedItem();      
        String horaL		= (String)comboHoraLlegada.getSelectedItem();
        String pago  		= (String)comboPago.getSelectedItem();   
	
        if(nombre.isEmpty() || correo.isEmpty() || dia.equals("SELECCIONAR") || mes.equals("SELECCIONAR")  || ano.equals("SELECCIONAR")|| horaL.equals("SELECCIONAR") || pago.equals("SELECCIONAR"))
            datos = "VACIO";
        else
        {
        	if((dia.equals("31") && mes.equals("FEBRERO")) || (dia.equals("31") && mes.equals("ABRIL")) || (dia.equals("31") && mes.equals("JUNIO")) || (dia.equals("31") && mes.equals("SEPTIEMBRE")) ||
          	(dia.equals("31") && mes.equals("NOVIEMBRE")) || (dia.equals("30") && mes.equals("FEBRERO")) || (dia.equals("29") && mes.equals("FEBRERO") && !ano.equals("2020")))
        	datos="Fecha no valida";
        	else
        	{
	        	String fecha= dia+"/"+mes+"/"+ano;    	
	           	datos = nombre+"_"+correo+"_SELECCIONAR_"+fecha+"_"+horaL+"_SELECCIONAR_SIN REVISAR_"+pago+"_NO ATENDIDO_"+0;
        	}
        }
        return datos;
    }
    
    private void desplegarDatos(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        tfNombre.setText(st.nextToken());
        tfCorreo.setText(st.nextToken());
        String tipoCita=st.nextToken();
      	String date=st.nextToken();
        StringTokenizer fecha= new StringTokenizer(date, "/");
        comboDia.setSelectedItem(fecha.nextToken());
        comboMes.setSelectedItem(fecha.nextToken());
        comboAno.setSelectedItem(fecha.nextToken()); 
        	
        comboHoraLlegada.setSelectedItem(st.nextToken());
        String tratamiento= st.nextToken();
        String sintomas=st.nextToken();
        comboPago.setSelectedItem(st.nextToken());
//        tfCosto.setText((String)st.nextToken());
    }
    
    private void activarBotones()
    {
        bCancelar.setEnabled(true);        
        bActualizar.setEnabled(true);
        bCapturar.setEnabled(false);
        bConsultar.setEnabled(false);
        bConsultarFecha.setEnabled(false);
        bModificar.setEnabled(false);
    }
    
    private void inactivarBotones()
    {
        bCancelar.setEnabled(false);      
        bActualizar.setEnabled(false);        
        bCapturar.setEnabled(true);
        bConsultar.setEnabled(true);
        bConsultarFecha.setEnabled(true);
        bModificar.setEnabled(true);
    }
    
    public void actionPerformed(ActionEvent e)
    { 	
    	String datos="", respuesta="", resultado="";
        
        if(e.getSource() == bCapturar)
        {
            // 1. obtener datos de los TextFields
            datos = obtenerDatos();
            
            if(datos.equals("VACIO"))
            {
                respuesta="Algun campo esta vacio...";
            }
            else
            { 
            	respuesta = agendaad.capturar(datos);
   			}
            taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bConsultar)
        {
            String correo = tfCorreo.getText();
            datos = agendaad.consultarCorreoPaciente(correo);
			if(correo.isEmpty())
			{
				taDatos.setText("No se ha ingresado algun correo");
			}
			else
			{
                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("No hay un usuario asociado a ese correo");
                else
                {
                    taDatos.setText(datos);
                    desplegarDatos(datos);
                    
                }
        	}
        }
        
        if(e.getSource() == bConsultarFecha)
        {
            String dia = (String)comboDia.getSelectedItem();
	        String mes = (String)comboMes.getSelectedItem();
	        String ano = (String)comboAno.getSelectedItem();
	        
	        if(dia.isEmpty()||mes.isEmpty()||ano.isEmpty())
	        {
	        	taDatos.setText("Falta algun dato en fecha");
	        }
	        else
	        {
		        String fecha= dia+"/"+mes+"/"+ano;
	            datos = agendaad.consultarFecha(fecha);
	                if(datos.equals("NOT_FOUND"))
	                    taDatos.setText("No se encontraron citas para esa fecha...");
	                else
	                {
	                    taDatos.setText(datos);
	                    desplegarDatos(datos);
	                }
	        }
        }
        
        if(e.getSource() == bModificar)
        {
        	String correo = tfCorreo.getText();
            datos = agendaad.consultarCorreoPaciente(correo);
            
                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("Correo no localizado...");
                else
                {
                    taDatos.setText(datos);
                    desplegarDatos(datos);
                    activarBotones();
                }
        }
        
    	if(e.getSource()== bActualizar)
        {
        	
        	taDatos.setText("Actualizando");
        	datos=obtenerDatos();
        	respuesta=agendaad.actualizar(datos);
            taDatos.setText(respuesta);
            desplegarDatos(datos);
            inactivarBotones();
        }      
  
        
        if(e.getSource() == bCancelar)
        {
            inactivarBotones();
        }
        
        if(e.getSource()==bRegresar)
        {
        	int opcion;
				if(login==null)
				{
					opcion= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere cerrar sesion?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0)//ok
					{
						login=new LoginGUI();
						login.setVisible(true);
						login=null;
						this.dispose();
					}
				}
        }
        
       	if(e.getSource()==bSalir)
        {
           	int opcion;
				if(login==null)
				{
					opcion= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0)//ok
					{
						System.exit(0);
					}
				}
        }
    }

       
    public static void main(String args[])
    {
        new PacienteGUI();
    }
}
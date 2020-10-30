import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class CitaGUI2 extends JFrame implements ActionListener
{
 	private JTextField tfNombre, tfCorreo, tfCosto, tfSintomas, tfTratamiento;
    private JButton    bCapturar, bConsultar, bConsultarCorreo, bConsultarFecha, bModificar, bCancelar, bActualizar, bSalir, bAtendidos, bMonto;
    private JTextArea  taDatos;

    private AgendaAD agendaad = new AgendaAD();
    public static InterfaceDoc docInt;
    public static AsistenteGUI asistente;

    private JPanel panel1, panel2;
    private JComboBox comboTipoCita, comboHoraLlegada, comboPago, comboEstado, comboDia, comboMes, comboAno, comboTratamientos, comboPacientes;
    private String opcionesDia[]={"SELECCIONAR","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private String opcionesMes[]={"SELECCIONAR","ENERO","FEBRERO","MARZO","ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE","OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    private String opcionesAno[]={"SELECCIONAR","2019", "2020", "2021"};
    private String opcionesEstado[] = {"SELECCIONAR","ATENDIDO","NO ATENDIDO","NO SE PRESENTO"};
   // private String opcionesTipoCita[] = {"SELECCIONAR","CONSULTA GENERAL","SEGUIMIENTO","CURACION","INYECCION","CERTIFICADO MEDICO"};
   /*Se manda a llamar a la funcion obtenerCombo() que es donde se hace la conexion
    *con las bases de datos*/
    String combo = agendaad.obtenerCombo();
  	private String opcionesTipoCita[]=combo.split(","); //es para pasar de un String a un String []
	//ComboBox tratamiento
	String combo2 = agendaad.obtenerCombo2();
	private String opcionesTratamientos[]=combo2.split(",");
	String combo3 = agendaad.obtenerCombo3();
	private String opcionesPacientes[]=combo3.split(",");

    private String opcionesHoraLlegada[]={"SELECCIONAR","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"};
    private String opcionesPago[] = {"SELECCIONAR","TARJETA", "EFECTIVO"};

    public CitaGUI2()
    {
    	super("Agenda de citas Dr.");
       	tfNombre = new JTextField();
        tfCorreo = new JTextField();
        tfCosto= new JTextField();
        tfSintomas=new JTextField();
     //   tfTratamiento=new JTextField();

        bCapturar 				= new JButton("Capturar");
        bConsultar				= new JButton("Consulta General");
        bConsultarCorreo		= new JButton("Consultar correo: ");
        bConsultarFecha			= new JButton("Consultar por fecha: ");
        bModificar				= new JButton("Modificar datos: ");
        bCancelar				= new JButton("Cancelar");
        bActualizar				= new JButton("Actualizar datos");
        bAtendidos				= new JButton("Consultar pacientes atendidos");
     	  bMonto					= new JButton("Monto de la cita");
        bSalir   				= new JButton("Regresar");

        // Adicionar addActionListener(this) a los JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarCorreo.addActionListener(this);
        bConsultarFecha.addActionListener(this);
        bModificar.addActionListener(this);
        bCancelar.addActionListener(this);
        bActualizar.addActionListener(this);
        bAtendidos.addActionListener(this);
        bMonto.addActionListener(this);

        bSalir.addActionListener(this);

        bCancelar.setEnabled(false);
        bActualizar.setEnabled(false);

        comboEstado = new JComboBox(opcionesEstado);
        comboTipoCita = new JComboBox(opcionesTipoCita);
        comboTratamientos= new JComboBox(opcionesTratamientos);
        comboHoraLlegada = new JComboBox(opcionesHoraLlegada);
        comboPago = new JComboBox(opcionesPago);
        comboDia= new JComboBox(opcionesDia);
        comboMes= new JComboBox (opcionesMes);
        comboAno= new JComboBox (opcionesAno);
        comboPacientes = new JComboBox(opcionesPacientes);


        taDatos   = new JTextArea(11,30);

        panel1    = new JPanel();
        panel2   = new JPanel();

        // 2. Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(18,2));
        panel2.setLayout(new FlowLayout());

        // 3. Adicionar los objetos de los atributos a los JPanels

        panel1.add(new JLabel("Nombre: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("Correo: "));
        //panel1.add(tfCorreo);
        panel1.add(comboPacientes);
        panel1.add(new JLabel ("Tipo de cita: "));
        panel1.add(comboTipoCita);
        panel1.add(new JLabel("Dia: "));
        panel1.add(comboDia);
        panel1.add(new JLabel("Mes: "));
        panel1.add(comboMes);
        panel1.add(new JLabel("Ano: "));
        panel1.add(comboAno);
        panel1.add(new JLabel("Hora Llegada: "));
        panel1.add(comboHoraLlegada);
        panel1.add(new JLabel("Tratamiento: "));
        panel1.add(comboTratamientos);
        panel1.add(new JLabel("Sï¿½ntomas: "));
        panel1.add(tfSintomas);
        panel1.add(new JLabel("Mï¿½todo de Pago: "));
        panel1.add(comboPago);
        panel1.add(new JLabel("Estado de la cita: "));
        panel1.add(comboEstado);
        panel1.add(new JLabel("Costo: "));
        panel1.add(tfCosto);

        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarCorreo);
        panel1.add(bConsultarFecha);
        panel1.add(bModificar);
        panel1.add(bCancelar);
        panel1.add(bActualizar);
        panel1.add(bAtendidos);
  		panel1.add(bMonto);

        panel1.add(bSalir);

        // 4. Adicionar panel1 y taDatos al panel2
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        // 5. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(500,900);
//        setVisible(true);
    }

    private String obtenerDatos()
    {
    	/*
    	 *Lee los datos del GUI, de text fields y combos
    	 *Si las variables no son visibles para el usuario (tratamiento y sintomas), se inicializa a SELECCIONAR
    	 **/
        String datos = "";
		//Nombre, Correo, Fecha, Hora, Tipo de cita [antes, motivo], Mï¿½todo de pago
        String nombre 		= tfNombre.getText();
        String correo		=(String)comboPacientes.getSelectedItem();
        String tipoCita  	= (String)comboTipoCita.getSelectedItem();
        String dia 			= (String)comboDia.getSelectedItem();
        String mes 			= (String)comboMes.getSelectedItem();
        String ano 			= (String)comboAno.getSelectedItem();
        String horaL		= (String)comboHoraLlegada.getSelectedItem();
		    String tratamiento  = (String)comboTratamientos.getSelectedItem();
        String sintomas 	= tfSintomas.getText();
        String pago  		= (String)comboPago.getSelectedItem();
        String estado =(String)comboEstado.getSelectedItem();
		    String costo		= tfCosto.getText();

        boolean num= agendaad.containsDigit(nombre);

        if(num==true){
          datos="NUMERIC";
        }
        else{
          if(nombre.isEmpty() || correo.equals("SELECCIONAR") || dia.equals("SELECCIONAR") || mes.equals("SELECCIONAR")  || ano.equals("SELECCIONAR")|| horaL.equals("SELECCIONAR") || tipoCita.equals("SELECCIONAR") || sintomas.isEmpty() || pago.equals("SELECCIONAR") || estado.equals("SELECCIONAR")|| costo.equals(""))//|| tratamiento.equals("SELECCIONAR")  //lo quité porque no puede haber un tratamiento cuando estás agendando la cita.
              datos = "VACIO";
          else
          {
            if((dia.equals("31") && mes.equals("FEBRERO")) || (dia.equals("31") && mes.equals("ABRIL")) || (dia.equals("31") && mes.equals("JUNIO")) || (dia.equals("31") && mes.equals("SEPTIEMBRE")) ||
            (dia.equals("31") && mes.equals("NOVIEMBRE")) || (dia.equals("30") && mes.equals("FEBRERO")) || (dia.equals("29") && mes.equals("FEBRERO") && !ano.equals("2020")))
            datos="Fecha no valida";
            else
            {
              int cost=Integer.parseInt(costo);
              String fecha= dia+"/"+mes+"/"+ano;
                datos = nombre+"_"+correo+"_"+tipoCita+"_"+fecha+"_"+horaL+"_"+tratamiento+"_"+sintomas+"_"+pago+"_"+estado+"_"+cost;
            }
          }
        }
        return datos;
    }

    private void desplegarDatos(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        tfNombre.setText(st.nextToken());
       // tfCorreo.setText(st.nextToken());
        comboPacientes.setSelectedItem(st.nextToken());
        comboTipoCita.setSelectedItem(st.nextToken());
      	String date=st.nextToken();
        StringTokenizer fecha= new StringTokenizer(date, "/");
        comboDia.setSelectedItem(fecha.nextToken());
        comboMes.setSelectedItem(fecha.nextToken());
        comboAno.setSelectedItem(fecha.nextToken());

        comboHoraLlegada.setSelectedItem(st.nextToken());
        comboTratamientos.setSelectedItem(st.nextToken());
        tfSintomas.setText(st.nextToken());
        comboPago.setSelectedItem(st.nextToken());
        comboEstado.setSelectedItem(st.nextToken());
        tfCosto.setText(st.nextToken());
    }

    private void limpiar()
    {
        tfNombre.setText("");
        //tfCorreo.setText("");
        comboPacientes.setSelectedItem("SELECCIONAR");
        comboTipoCita.setSelectedItem("SELECCIONAR");
        comboDia.setSelectedItem("SELECCIONAR");
        comboMes.setSelectedItem("SELECCIONAR");
        comboAno.setSelectedItem("SELECCIONAR");

        comboHoraLlegada.setSelectedItem("SELECCIONAR");
        comboTratamientos.setSelectedItem("SELECCIONAR");
        tfSintomas.setText("");
        comboPago.setSelectedItem("SELECCIONAR");
        comboEstado.setSelectedItem("SELECCIONAR");
        tfCosto.setText("");
    }

    private void activarBotones()
    {
        bCancelar.setEnabled(true);
        bActualizar.setEnabled(true);
        bCapturar.setEnabled(false);
        bConsultar.setEnabled(false);
        bConsultarCorreo.setEnabled(false);
        bConsultarFecha.setEnabled(false);
        bModificar.setEnabled(false);
        bAtendidos.setEnabled(false);
    }

    private void inactivarBotones()
    {
        bCancelar.setEnabled(false);
        bActualizar.setEnabled(false);
        bCapturar.setEnabled(true);
        bConsultar.setEnabled(true);
        bConsultarCorreo.setEnabled(true);
        bConsultarFecha.setEnabled(true);
        bModificar.setEnabled(true);
        bAtendidos.setEnabled(true);
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
            else if(datos.equals("NUMERIC")){
              respuesta="No se aceptan numeros en nombre";
            }
            else
            {
            	respuesta = agendaad.capturar(datos);
   			}
            taDatos.setText(respuesta);
           // limpiar();
        }

        if(e.getSource() == bConsultar)
        {
        	limpiar();
            datos = agendaad.consultar();
            taDatos.setText(datos);
        }


        if(e.getSource() == bConsultarCorreo)
        {
            String correo = (String)comboPacientes.getSelectedItem();
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

	        if(dia.equals("SELECCIONAR") ||mes.equals("SELECCIONAR")||ano.equals("SELECCIONAR"))
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
        	String correo = (String)comboPacientes.getSelectedItem();
            datos = agendaad.consultarCorreoPaciente(correo);

                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("Correo no localizado...");
                else
                {
                    taDatos.setText(datos);
                    desplegarDatos(datos);
                    datos=agendaad.eliminar(datos);
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
  		if(e.getSource()==bMonto)
  		{
  			String tipoCita	= (String)comboTipoCita.getSelectedItem();
  			System.out.println(tipoCita);
  			if(tipoCita=="SELECCIONAR")
  			{
  				taDatos.setText("No ha seleccionado un tipo de cita");
  			}
  			else
  				datos=agendaad.Monto(tipoCita);
  				if(datos=="NOT_FOUND")
  				{
  					taDatos.setText("No existe este tipo de cita");
  				}
  				else
	  			taDatos.setText(datos);
  		}

        if(e.getSource() == bCancelar)
        {
            inactivarBotones();
        }

        if(e.getSource() == bAtendidos)
        {
        	taDatos.setText("Consultando");
            datos = agendaad.consultarAtendidos();
            	if(datos.equals("NOT_FOUND"))
            		taDatos.setText("No hay citas con el estatus de Atendido");
            	else
            	{
            		taDatos.setText(datos);
            	}
        }

       	if(e.getSource()==bSalir)
		{
			System.out.println("REGRESAR");
			String user=agendaad.usuario;
			System.out.println(user);
			if(user.equals("DOCTOR"))
			{
				docInt=new InterfaceDoc();
				docInt.setVisible(true);
				docInt=null;
				this.dispose();
			}
			else
			{
				if(user.equals("ASISTENTE"))
				{
					asistente=new AsistenteGUI();
					asistente.setVisible(true);
					asistente=null;
					this.dispose();
				}
			}
		}
    }


    public static void main(String args[])
    {
        new CitaGUI2();
    }
}

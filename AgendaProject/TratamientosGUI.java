import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*Tratamientos GUI
 *Maneja el registro de tratamientos. Guarda las opciones de tratamiento en una base de datos
 *Base de datos: agenda. Tabla: tratamientos
 *Estas opciones se desplegar�n en una lista en el GUI de cita que pueden ver tanto doctor como asistente.
 *Acceden Doctor y asistente
 **/

public class TratamientosGUI extends JFrame implements ActionListener
{
	private JTextField tfTratamiento;
	private JTextArea taDatos;
	private JButton bGuardar, bConsultar, bRegresar,bModificar;

	private JPanel panel1, panel2;

	public static AsistenteGUI asistente;
	public static InterfaceDoc docInt;
	public AgendaAD agendaad = new AgendaAD();

	public TratamientosGUI()
	{
		super("REGISTRO DE TRATAMIENTOS");
		tfTratamiento = new JTextField();

		bGuardar = new JButton("Guardar tratamiento");
		bConsultar = new JButton("Consultar tratamientos");
		bRegresar = new JButton("Regresar");
		bModificar = new JButton("Modificar");

		//ActionListener
		bGuardar.addActionListener(this);
		bConsultar.addActionListener(this);
		bRegresar.addActionListener(this);
		bModificar.addActionListener(this);

		taDatos = new JTextArea(10,20);

		panel1 = new JPanel();
		panel2 = new JPanel();

		//Layout
		panel1.setLayout(new GridLayout(3,2));
		panel2.setLayout(new FlowLayout());

		//Panel1
		panel1.add(new JLabel ("Tratamiento: "));
		panel1.add(tfTratamiento);
		panel1.add(bGuardar);
		panel1.add(bConsultar);
		panel1.add(bRegresar);
		panel1.add(bModificar);

		//panel2
		panel2.add(panel1);
		panel2.add(new JScrollPane(taDatos));

		//JFrame
		add(panel2);
		setSize(400,400);
//		setVisible(true);
	}

	private void borrarCampos()
	{
		tfTratamiento.setText("");
	}

	public void actionPerformed(ActionEvent event)
	{
		String datos="";
		if(event.getSource()==bGuardar)
		{
			String tratamiento=tfTratamiento.getText();

			if(tratamiento.isEmpty())
			{
				taDatos.setText("Campo vac�o...");
			}
			else
			{
				datos="GUARDANDO..."+tratamiento;
				datos=agendaad.guardar(tratamiento);
				taDatos.setText(datos);
				borrarCampos();
			}
		}
		if(event.getSource()==bConsultar)
		{
			datos=agendaad.consultarTratamiento();
			taDatos.setText(datos);
			borrarCampos();
		}

		    if(event.getSource() == bModificar)
        {
        	String mod = tfTratamiento.getText();
        	System.out.println(mod);
        	String nuevo, respuesta;
            datos = agendaad.consultarTratamiento2(mod);

                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("Este tratamiento no existe...");
                else
                {
                    nuevo = JOptionPane.showInputDialog(null,"Modificacion del tratamiento");
										if(nuevo!=null){
											respuesta =agendaad.modificarT(mod,nuevo);
											taDatos.setText(respuesta);
										}
										if(nuevo==null){
											JOptionPane.showMessageDialog(null,"Operacion cancelada");
										}
                }
        }
		if(event.getSource()==bRegresar)
		{
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
		new TratamientosGUI();
	}
}

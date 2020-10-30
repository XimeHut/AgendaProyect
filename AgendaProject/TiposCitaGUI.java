import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*TiposCitaGUI
 *Maneja el registro de tipos de cita, motivos para ir al doctor. Guarda las opciones de tipos de cita en una base de datos
 *Base de datos: agenda. Tabla: tipocita
 *Estas opciones se desplegar�n en una lista en el GUI de cita que pueden ver tanto doctor como asistente.
 *Accede s�lo el  Doctor
 **/

public class TiposCitaGUI extends JFrame implements ActionListener
{
	private JTextField tfTipoCita, tfCosto;
	private JTextArea taDatos;
	private JButton bGuardar, bConsultar, bRegresar , bModificar;

	private JPanel panel1, panel2;

	public static InterfaceDoc docInt;
	public static AsistenteGUI asistente;
	private AgendaAD agendaad = new AgendaAD();

	public TiposCitaGUI()
	{
		super("REGISTRO DE TIPO DE CITAS");
		tfTipoCita = new JTextField();
		tfCosto = new JTextField();

		bGuardar = new JButton("Guardar Tipo de Cita");
		bConsultar = new JButton("Consultar Tipos de Cita");
		bRegresar = new JButton("Regresar");
		bModificar = new JButton("Modificar");
		//ActionListener
		bGuardar.addActionListener(this);
		bConsultar.addActionListener(this);
		bRegresar.addActionListener(this);
		bModificar.addActionListener(this);

		taDatos = new JTextArea(10,30);

		panel1 = new JPanel();
		panel2 = new JPanel();

		//Layout
		panel1.setLayout(new GridLayout(4,2));
		panel2.setLayout(new FlowLayout());

		//Panel1
		panel1.add(new JLabel ("Tipo de cita: "));
		panel1.add(tfTipoCita);
		panel1.add(new JLabel ("Costo: "));
		panel1.add(tfCosto);
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
		//setVisible(true);
	}
	private String obtenerDatos()
	{
		String datos="";
		String tipoCita=tfTipoCita.getText();
		String strCosto= tfCosto.getText();

			if((tipoCita.isEmpty())||(strCosto.isEmpty()))
			{
				datos="VACIO";
			}
			else
			{
				try
	            {
	                int n = Integer.parseInt(strCosto);
	                datos = tipoCita+"_"+n;
	            }
	            catch(NumberFormatException nfe)
	            {
	                datos = "NO_NUMERICO";
	            }
			}
		return datos;
	}

		private void borrarCampos()
	{
		tfTipoCita.setText("");
		tfCosto.setText("");
	}

	public void actionPerformed(ActionEvent event)
	{
		String datos="";
		String tipoCita;
		int costo;
		if(event.getSource()==bGuardar)
		{
			datos=obtenerDatos();
			if(datos.equals("VACIO"))
			{
				taDatos.setText("Hay campos vacios...");
			}
			else
			{
				if(datos.equals("NO_NUMERICO"))
				{
					taDatos.setText("El costo no es num�rico...");
				}
				else
				{
					StringTokenizer st = new StringTokenizer(datos,"_");
					tipoCita=st.nextToken();
					costo=Integer.parseInt(st.nextToken());
					datos=agendaad.guardarTipoCita(tipoCita, costo);
					taDatos.setText(datos);
					borrarCampos();
				}
			}
		}
		if(event.getSource()==bConsultar)
		{
			datos=agendaad.consultarTipoCita();
			if(datos=="NOT_FOUND")
			{
				taDatos.setText("No hay tipos de cita registrados a�n.");
			}
			else
			taDatos.setText(datos);
			borrarCampos();
		}
			if(event.getSource() == bModificar)
        {
        	String mod = tfTipoCita.getText();
        	System.out.println(mod);
        	String nuevo, costo_nuevo, respuesta;
        	int cost;
            datos = agendaad.consultarTipo(mod);

                if(datos.equals("NOT_FOUND")){
                	 taDatos.setText("Este tipo de cita  no existe...");
                }

                else
                {

                    nuevo = JOptionPane.showInputDialog(null,"Modificacion del tipo");
										//if(nuevo==" "){nuevo=null;}
										if(nuevo!=null){
											costo_nuevo=JOptionPane.showInputDialog(null,"Modificacion del costo");
											if(costo_nuevo!=null){
												cost=Integer.parseInt(costo_nuevo);
												respuesta =agendaad.modificarTipo(mod,nuevo,cost);
												taDatos.setText(respuesta);
											}
											if(costo_nuevo==null){
													JOptionPane.showMessageDialog(null,"Operación cancelada");
											}
										}
										if(nuevo==null){
											JOptionPane.showMessageDialog(null,"Operación cancelada");
										}
                }
        }
		if(event.getSource()==bRegresar)
		{
			String user = agendaad.usuario;
			
			if (user.equals("DOCTOR"))
			{
				if(docInt==null)
				{
					docInt=new InterfaceDoc();
					docInt.setVisible(true);
					docInt=null;
					this.dispose();
				}
			}
			else
			{
				  if(asistente==null)
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
		new TiposCitaGUI();
	}
}

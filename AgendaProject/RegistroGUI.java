import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroGUI extends JFrame implements ActionListener{
	public static PacienteGUI paciente;
	public static LoginGUI login;

	private JTextField tfNombre, tfCorreo;
	private JPasswordField tfPassword, tfConfirmar;
	private JButton    bRegistrar, bSalir, bRegresar;

    private JTextArea  taDatos;

    private AgendaAD agendaad = new AgendaAD();

    private JPanel panel1, panel2;

    public RegistroGUI()
    {
       	super("Registro");
       	tfNombre = new JTextField();
       	tfCorreo = new JTextField();
        tfPassword = new JPasswordField();
		tfConfirmar= new JPasswordField();

        bRegistrar 				= new JButton("Registrar");
        bSalir   				= new JButton("Salir");
		bRegresar				= new JButton("Regresar");

        // Adicionar addActionListener(this) a los JButtons
        bRegistrar.addActionListener(this);
        bSalir.addActionListener(this);
		bRegresar.addActionListener(this);

		taDatos   = new JTextArea(5,40);

        panel1    = new JPanel();
        panel2   = new JPanel();

        // 2. Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(10,2));
        panel2.setLayout(new FlowLayout());

        // 3. Adicionar los objetos de los atributos a los JPanels
       	panel1.add(new JLabel(""));
       	panel1.add(new JLabel(""));
        panel1.add(new JLabel("Nombre: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("Correo: "));
        panel1.add(tfCorreo);
        panel1.add(new JLabel("Contrasena: "));
        panel1.add(tfPassword);
				panel1.add(new JLabel("Confirmar contrasena: "));
        panel1.add(tfConfirmar);
        panel1.add(new JLabel(""));
       	panel1.add(new JLabel(""));
        panel1.add(bRegistrar);
        panel1.add(bSalir);
		panel1.add(bRegresar);

        // 4. Adicionar panel1 y taDatos al panel2
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        // 5. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(500,600);
        //setVisible(true);

    }

   	private String obtenerDatos()
    {
        String datos = "";
        String nombre = tfNombre.getText();
        String correo  	= tfCorreo.getText();
        char[] contra 		= tfPassword.getPassword();
				char[] confi   = tfConfirmar.getPassword();

				String contrasena= String.valueOf(contra);
				String confirmar= String.valueOf(confi);

				boolean num= agendaad.containsDigit(nombre);

				if(num==true){
					datos="NUMERIC";
				}
				else{
					if(nombre.isEmpty()||correo.equals("") || contrasena.isEmpty() || confirmar.isEmpty())
	            datos = "VACIO";
					else if(!contrasena.equals(confirmar)){
						datos="NOT_SAME";
					}
					else{
	           	datos = "PACIENTE_"+nombre+"_"+correo+"_"+contrasena;
	        }
				}
        return datos;
    }

    public void actionPerformed(ActionEvent event)
    {
    	String datos="", respuesta="";

        if(event.getSource()==bRegistrar)
        {
        		String correo= tfCorreo.getText();
				datos=agendaad.consultarCorreoRegistro(correo);
				if(datos=="FOUND")
					{
        				respuesta="La cuenta de correo ya se encuentra registrada";
        			}
		        	else
		        		{
							if(datos=="NOT_FOUND")
							{
			        			datos=obtenerDatos();
			        			if(datos.equals("VACIO"))
			        			{
					            	respuesta = "Algun campo esta vacio...";
			        			}
								else if(datos=="NOT_SAME")
								{
									 respuesta="Las contasenas no coinciden";
								}
								else if(datos=="NUMERIC"){
									respuesta="No se aceptan numeros en nombre";
								}
				           		else
				           		{
									respuesta=agendaad.capturarRegistro(datos);
					             	if(respuesta=="CORRECTO")
					             	{
										respuesta="Registro correcto";
										if (paciente==null)
										{
											paciente= new PacienteGUI();
											paciente.setVisible(true);
											this.dispose();
										}
					             	}
				           		}
		        			}
						}
						taDatos.setText(respuesta);
      	}

			if(event.getSource()==bRegresar)
			{
				int opcion;
				opcion= JOptionPane.showConfirmDialog(null, "�Esta seguro que quiere regresar?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion==0)//ok
				{
					login=new LoginGUI();
					login.setVisible(true);
					login=null;
					this.dispose();
				}
			}

        	if(event.getSource()==bSalir)
			{
				int opcion;
				opcion= JOptionPane.showConfirmDialog(null, "�Esta seguro que desea salir?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion==0)//ok
				{
					System.exit(0);
				}
			}
   	}

    public static void main(String args[]){
        new RegistroGUI();
    }
}

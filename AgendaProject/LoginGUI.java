import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame implements ActionListener
{
	public static RegistroGUI frmReg;
	public static PacienteGUI paciente;
	public static PersonalGUI personal;
	public static InterfaceDoc doc;
	public static AsistenteGUI asistente;
	public static ContadorGUI contador;

	private JTextField tfCorreo;
	private JPasswordField tfPassword;
    private JButton    bRegistrar, bSalir, bIniciar;

    private JTextArea  taDatos;

	private AgendaAD agendaad = new AgendaAD();
    private JPanel panel1, panel2;

    public LoginGUI()
    {
       	super("INICIO DE SESION");
       	tfCorreo = new JTextField();
        tfPassword = new JPasswordField();

        bRegistrar 				= new JButton("Registrarme");
        bSalir   				= new JButton("Salir");
        bIniciar				= new JButton("Iniciar Sesion");

        // Adicionar addActionListener(this) a los JButtons
        bRegistrar.addActionListener(this);
        bSalir.addActionListener(this);
        bIniciar.addActionListener(this);

        taDatos   = new JTextArea(4,30);

        panel1    = new JPanel();
        panel2   = new JPanel();

        // 2. Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(7,2));
        panel2.setLayout(new FlowLayout());

        // 3. Adicionar los objetos de los atributos a los JPanels
       	panel1.add(new JLabel(""));
       	panel1.add(new JLabel(""));
        panel1.add(new JLabel("Correo: "));
        panel1.add(tfCorreo);
        panel1.add(new JLabel("Contrasena: "));
        panel1.add(tfPassword);
        panel1.add(new JLabel(""));
       	panel1.add(new JLabel(""));
   		panel1.add(new JLabel("¿No tiene cuenta?"));
   		panel1.add(new JLabel("Registrese aqui"));

        panel1.add(bIniciar);
        panel1.add(bRegistrar);
        panel1.add(bSalir);

        // 4. Adicionar panel1 y taDatos al panel2
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        // 5. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(400,600);
        setVisible(true);

    }


    public void actionPerformed(ActionEvent event)
    {
    	String datos="";
    	if (event.getSource()== bRegistrar)
			{
				if(frmReg==null)
				{
					frmReg=new RegistroGUI();
					frmReg.setVisible(true);
					frmReg=null;
					this.dispose();
				}
			}

			if(event.getSource()==bIniciar)
			{
				String correo = tfCorreo.getText();
				char[] password= tfPassword.getPassword();
				if(correo.isEmpty()||password.equals(""))
				{
					taDatos.setText("Algun campo esta vacio");
				}
				else
				{
		      		datos = agendaad.consultarCorreo(correo, password);
							System.out.println(datos);
					    if(datos.equals("NOT_FOUND"))
							taDatos.setText("Correo no localizado...");
					    else
				    	{
					        if(datos.equals("PACIENTE"))
					        {
							 	taDatos.setText("Inicio existoso");
							 	if (paciente==null)
								{
									paciente= new PacienteGUI();
									paciente.setVisible(true);
									paciente=null;
									this.dispose();
								}
							}
							if(datos.equals("DOCTOR"))
					        {
							 	taDatos.setText("Inicio existoso");
							 	if (doc==null)
								{
									doc= new InterfaceDoc();
									doc.setVisible(true);
									doc=null;
									this.dispose();
								}
							}
							if(datos.equals("PERSONAL"))
					        {
							 	taDatos.setText("Inicio existoso");
							 	if (personal==null)
								{
									personal= new PersonalGUI();
									personal.setVisible(true);
									personal=null;
									this.dispose();
								}
							}
							if(datos.equals("ASISTENTE"))
					        {
							 	taDatos.setText("Inicio existoso");
							 	if (asistente==null)
								{
									asistente= new AsistenteGUI();
									asistente.setVisible(true);
									asistente=null;
									this.dispose();
								}
							}
							if(datos.equals("CONTADOR"))
					        {
							 	taDatos.setText("Inicio existoso");
							 	if (contador==null)
								{
									contador= new ContadorGUI();
									contador.setVisible(true);
									contador=null;
									this.dispose();
								}
							}
							//falta poner ifs para doctor, asistente y contador/personal
							else if(datos.equals("NOT_CORRECT"))
							{
							 	taDatos.setText("Contrasena incorrecta");
							}
				     	}
				}
			}

        if(event.getSource()==bSalir)
        {
         	System.exit(0);
        }
   	}

    public static void main(String args[])
    {
        new LoginGUI();
    }
}

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AsistenteGUI extends JFrame implements ActionListener
{
	public static CitaGUI2 cita;
	public static PersonalGUI personal;
	public static TratamientosGUI tratamientos;
	public static LoginGUI login;
	public static PagoGUI contador;
	public static TiposCitaGUI tipo;
	
    private JButton    bPersonal, bCita, bTratamientos, bSalir, bRegresar, bConsultarMetodoPago;
    private LoginDP iniciodp;

    private JPanel panel1;

    public AsistenteGUI()
    {
       	super("Bienvenido(a)");
        bPersonal 				= new JButton("Consultar horario");
        bCita   				= new JButton("Agendar Cita");
        bTratamientos			= new JButton("Registrar tipo de citas");
        bConsultarMetodoPago   = new JButton("Consultar Metodo de Pago");
		bSalir					= new JButton("Salir");
		bRegresar				= new JButton("Cerrar Sesion");
        // Adicionar addActionListener(this) a los JButtons
        bPersonal.addActionListener(this);
        bCita.addActionListener(this);
        bTratamientos.addActionListener(this);
        bConsultarMetodoPago.addActionListener(this);
        bSalir.addActionListener(this);
        bRegresar.addActionListener(this);
        panel1    = new JPanel();

        // 2. Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(4,3));
        // 3. Adicionar los objetos de los atributos a los JPanels
        panel1.add(bPersonal);
        panel1.add(bCita);
        panel1.add(bTratamientos);
        panel1.add(bConsultarMetodoPago);
		panel1.add(bRegresar);
		panel1.add(bSalir);
        // 4. Adicionar panel1 y taDatos al panel2
        
        // 5. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel1);
        setSize(400,600);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
    	if (event.getSource()== bCita)
			{
				if(cita==null)
				{
					cita=new CitaGUI2();
					cita.setVisible(true);
					cita=null;
					this.dispose();
				}
			}
			if (event.getSource()== bPersonal)
			{
				if(personal==null)
				{
					personal=new PersonalGUI();
					personal.setVisible(true);
					personal=null;
					this.dispose();
				}
				
			}
			if(event.getSource()==bTratamientos)
			{
				if(tipo==null)
				{
					tipo=new TiposCitaGUI();
					tipo.setVisible(true);
					tipo=null;
					this.dispose();
				}
			}
			if(event.getSource()==bConsultarMetodoPago)
			{
				if (contador==null)
				{
					contador= new PagoGUI();
					contador.setVisible(true);
					contador=null;
					this.dispose();
				}
			}
			
			if(event.getSource()==bRegresar)
			{
				int opcion;
				opcion= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere cerrar sesion?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
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
				opcion= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion==0)//ok
				{
					System.exit(0);
				}
			}
    }
    
    public static void main(String args[])
    {
        new AsistenteGUI();
    }
}
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class InterfaceDoc extends JFrame implements ActionListener
{
	public static CitaGUI2 cita;
	public static UsuariosGUI usuarios;
	public static TratamientosGUI tratamientos;
	public static TiposCitaGUI tiposCita;
	public static ListaGUI lista;
	public static LoginGUI login;

    private JButton    bRegistrar, bCita, bTratamientos, bTiposCita, bLista, bLogOut, bSalir;
    private LoginDP iniciodp;

    private JPanel panel1;

    public InterfaceDoc()
    {
       	super("Bienvenido Doctor(a)");
        bRegistrar 				= new JButton("Registrar personal");
        bCita   				= new JButton("Agendar Cita");
        bTratamientos			= new JButton("Registrar tratamientos");
        bTiposCita				= new JButton("Tipos de cita");
				bLista 				= new JButton("Lista personal registrado");
				bLogOut					= new JButton("Cerrar Sesion");
				bSalir					= new JButton("Salir");
        // Adicionar addActionListener(this) a los JButtons
        bRegistrar.addActionListener(this);
        bCita.addActionListener(this);
        bTratamientos.addActionListener(this);
        bTiposCita.addActionListener(this);
				bLista.addActionListener(this);
        bLogOut.addActionListener(this);
        bSalir.addActionListener(this);
        panel1    = new JPanel();

        // 2. Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(3,3));
        // 3. Adicionar los objetos de los atributos a los JPanels
        panel1.add(bRegistrar);
        panel1.add(bCita);
        panel1.add(bTratamientos);
        panel1.add(bTiposCita);
				//panel1.add(bLista);
				panel1.add(bLogOut);
        panel1.add(bSalir);
        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel1);
        setSize(600,600);
  //    setVisible(true);
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
			if (event.getSource()== bRegistrar)
			{
				if(usuarios==null)
				{
					usuarios=new UsuariosGUI();
					usuarios.setVisible(true);
					usuarios=null;
					this.dispose();
				}
			}
			if(event.getSource()==bTratamientos)
			{
				if(tratamientos==null)
				{
					tratamientos=new TratamientosGUI();
					tratamientos.setVisible(true);
					tratamientos=null;
					this.dispose();
				}
			}

			if(event.getSource()==bTiposCita)
			{
				if(tiposCita==null)
				{
					tiposCita=new TiposCitaGUI();
					tiposCita.setVisible(true);
					tiposCita=null;
					this.dispose();
				}
			}

			if(event.getSource()==bLista){
				lista=new ListaGUI();
				lista.setVisible(true);
				lista=null;
				this.dispose();
			}

			if(event.getSource()==bLogOut)
			{
				int opcion;
				if(login==null)
				{
					opcion= JOptionPane.showConfirmDialog(null, "�Esta seguro que quiere cerrar sesion?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0)//ok
					{
						login=new LoginGUI();
						login.setVisible(true);
						login=null;
						this.dispose();
					}
				}
			}
			if(event.getSource()==bSalir)
			{
				int opcion;
				if(login==null)
				{
					opcion= JOptionPane.showConfirmDialog(null, "�Esta seguro que desea salir?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0)//ok
					{
						System.exit(0);
					}
				}
			}
    }

    public static void main(String args[])
    {
        new InterfaceDoc();
    }
}

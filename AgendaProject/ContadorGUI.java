import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ContadorGUI extends JFrame implements ActionListener
{

	public static PagoGUI contador;
	public static LoginGUI login;
	public static SelectContadorGUI select;
    private JButton bSeleccionar, bSalir, bRegresar, bConsultarMetodoPago;
    private LoginDP iniciodp;

    private JPanel panel1;

    public ContadorGUI()
    {
       	super("Bienvenido(a)");
        bSeleccionar 				= new JButton("Seleccionar datos");
        bConsultarMetodoPago   = new JButton("Consultar Metodo de Pago");
		bSalir					= new JButton("Salir");
		bRegresar				= new JButton("Cerrar Sesion");
        // Adicionar addActionListener(this) a los JButtons
        bSeleccionar.addActionListener(this);
        bConsultarMetodoPago.addActionListener(this);
        bSalir.addActionListener(this);
        bRegresar.addActionListener(this);
        panel1    = new JPanel();

        // 2. Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(4,3));
        // 3. Adicionar los objetos de los atributos a los JPanels
        panel1.add(bSeleccionar);
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
			
			if (event.getSource()== bSeleccionar)
			{
				if(select==null)
				{
					select=new SelectContadorGUI();
					select.setVisible(true);
					select=null;
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
        new ContadorGUI();
    }
}
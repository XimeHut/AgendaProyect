import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class PagoGUI extends JFrame implements ActionListener
{
 	private JButton bConsultarMetodoPago, bSalir;
	private JTextArea  taDatos;
    private JComboBox comboPago;
    private String opcionesPago[] = {"SELECCIONAR","TARJETA", "EFECTIVO"};
    private JPanel panel1, panel2;
    ///////OJO////
    public static AsistenteGUI asistente;
    public static ContadorGUI contador;
    
    private AgendaAD agendaad = new AgendaAD();
	
    public PagoGUI()
    {
    	super("Consultar metodos de pago");
        
        bConsultarMetodoPago   = new JButton("Consultar Metodo de Pago");
        bSalir   				= new JButton("Regresar");
        // Adicionar addActionListener(this) a los JButtons
        
        bConsultarMetodoPago.addActionListener(this);
        bSalir.addActionListener(this);
        
        comboPago = new JComboBox(opcionesPago);
        taDatos   = new JTextArea(20,40);
        
        panel1    = new JPanel();
        panel2   = new JPanel();
        // Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(3,2));
        panel2.setLayout(new FlowLayout());
        
        
        panel1.add(comboPago);
        panel1.add(bConsultarMetodoPago);
        panel1.add(new JLabel(""));
        panel1.add(bSalir);
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        add(panel2);
        setSize(500,500);
        //setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	String datos="";
    	if(e.getSource()==bConsultarMetodoPago)
        {
        	String metodo = (String)comboPago.getSelectedItem();
            datos = agendaad.consultarMetodo(metodo);
			if(datos.equals("Error"))
                taDatos.setText("Error");
            else
                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("No se encontraron citas pagadas con ese metodo de pago");
                else
                {
                    taDatos.setText(datos);
                   
                }
        }
        if(e.getSource()==bSalir)
        {
        	String user=agendaad.usuario;
			if(user.equals("CONTADOR"))
			{
				contador=new ContadorGUI();
				contador.setVisible(true);
				contador=null;
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
        new PagoGUI();
    }
}
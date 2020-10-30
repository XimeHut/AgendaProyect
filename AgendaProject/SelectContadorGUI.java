import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class SelectContadorGUI extends JFrame implements ActionListener
{
 	private JButton bConsultarMes, bConsultarCorreo, bSalir;
 	private JTextField tfCorreo;
	private JTextArea  taDatos;
    private JComboBox comboMes;
    private String opcionesMes[]={"SELECCIONAR","ENERO","FEBRERO","MARZO","ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE","OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    private JPanel panel1, panel2, panel3;
    ///////OJO////
    public static AsistenteGUI asistente;
    public static ContadorGUI contador;
    
    private AgendaAD agendaad = new AgendaAD();
	
    public SelectContadorGUI()
    {
    	super("Seleccionar datos para facturar");
        tfCorreo=new JTextField();
        bConsultarMes   = new JButton("Consultar Mes");
        bConsultarCorreo = new JButton("Consultar correo");
        bSalir   				= new JButton("Regresar");
        // Adicionar addActionListener(this) a los JButtons
        
        bConsultarMes.addActionListener(this);
        bConsultarCorreo.addActionListener(this);
        bSalir.addActionListener(this);
        
        comboMes = new JComboBox(opcionesMes);
        taDatos   = new JTextArea(20,40);
        
        panel1    = new JPanel();
        panel2   = new JPanel();
        panel3   = new JPanel();
        // Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(2,2));
        panel2.setLayout(new GridLayout(2,2));
        panel3.setLayout(new FlowLayout());
        
        panel1.add(new JLabel("Seleccionar citas del mes: "));
        panel1.add(comboMes);
        panel1.add(bConsultarMes);
        panel1.add(new JLabel(""));
        
        panel2.add(new JLabel("Buscar citas del correo: "));
        panel2.add(tfCorreo);
        panel2.add(bConsultarCorreo);
        panel2.add(bSalir);
        
        panel3.add(panel1);
        panel3.add(panel2);
        panel3.add(new JScrollPane(taDatos));
        
        add(panel3);
        setSize(500,500);
        //setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	String datos="";
    	
    	if(e.getSource()==bConsultarMes)
        {
        	String mes = (String)comboMes.getSelectedItem();
            datos = agendaad.consultarMes(mes);
			if(datos.equals("Error"))
                taDatos.setText("Error");
            else
            {
                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("No se encontraron citas ese mes");
                else
                {
                    taDatos.setText(datos);
                   
                }
            }    
        }
        
        if(e.getSource()==bConsultarCorreo)
        {
        	String correo = tfCorreo.getText();
        	
        	if(correo.isEmpty())
        	{
        		taDatos.setText("Escriba un correo para buscar");
        	}
        	else
        	{
	            datos = agendaad.consultarCorreoPacienteConta(correo);
	            //taDatos.setText("Buscando correo: "+correo);
				if(datos.equals("Error"))
	                taDatos.setText("Error");
	            else
	            {
	                if(datos.equals("NOT_FOUND"))
	                    taDatos.setText("No se encontraron citas con ese correo");
	                else
	                {
	                    taDatos.setText(datos);
	                   
	                }
	            }
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
        new SelectContadorGUI();
    }
}
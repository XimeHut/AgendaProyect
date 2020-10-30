/**
 * @(#)PersonalGUI.java
 *
 *
 * @author 
 * @version 1.00 2020/4/26
 */
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

	public class PersonalGUI extends JFrame implements ActionListener
	{
	private AgendaAD agendaad = new AgendaAD();
	public static AsistenteGUI asistente;
	public static LoginGUI login;
	
	private JTextField tfFecha;
	private JButton bConsultar , bSalir;
	private JComboBox comboDia , comboMes, comboAno;
	private String opcionesDia[]={"SELECCIONAR","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private String opcionesMes[]={"SELECCIONAR","ENERO","FEBRERO","MARZO","ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE",
    "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    private String opcionesAno[]={"SELECCIONAR","2019", "2020", "2021"};
	private JTextArea  taDatos;
	///////CAMBIO////

	
	private JPanel panel1, panel2;
	 
    public PersonalGUI() {
    	
    	super("CONSULTAR HORARIO");
    	
    	tfFecha = new JTextField();
    	bConsultar				= new JButton("Consultar Dia");
    	bSalir   				= new JButton("Regresar");
    	bConsultar.addActionListener(this);
    	bSalir.addActionListener(this);
    	
    	comboDia= new JComboBox(opcionesDia);
        comboMes= new JComboBox (opcionesMes);
        comboAno= new JComboBox (opcionesAno);
        
        taDatos   = new JTextArea(10,30);

        panel1    = new JPanel();
        panel2   = new JPanel();
        	
        panel1.setLayout(new GridLayout(5,2));
        panel2.setLayout(new FlowLayout());
        
        panel1.add(new JLabel("Seleccione la fecha a consultar "));
         panel1.add(new JLabel("    "));
        panel1.add(new JLabel("Dia: "));
        panel1.add(comboDia);
        panel1.add(new JLabel("Mes: "));
        panel1.add(comboMes);
        panel1.add(new JLabel("Ano: "));
        panel1.add(comboAno);
        
        panel1.add(bConsultar);
        panel1.add(bSalir);
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        add(panel2);
        setSize(400,600);
        //setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e){
    	
    	String datos="";
    	
    	if(e.getSource() == bConsultar)
        {
	       	String dia = (String)comboDia.getSelectedItem();
	        String mes = (String)comboMes.getSelectedItem();
	        String ano = (String)comboAno.getSelectedItem();
	        if(dia.equals("SELECCIONAR")||mes.equals("SELECCIONAR")||ano.equals("SELECCIONAR"))
	        {
	        	taDatos.setText("No ha seleccionado niguna fecha");
	        }
	        else
	        {
		        String fecha= dia+"/"+mes+"/"+ano;
		        
	            datos = agendaad.consultarPersonal(fecha);
	        	if(datos.equals("NOT_FOUND"))
	            taDatos.setText("No hay citas para este dia");
	            else
			        if(datos.equals("NOT_FOUND"))
			            taDatos.setText("No se encontraron citas para esa fecha...");
			        else
			        {
			            taDatos.setText(datos);
		
			        }
	        }
    	}
    
     	if(e.getSource()==bSalir)
	    {
	    	String user=agendaad.usuario;
	    	System.out.println("UP: "+user);
	    	if(user.equals("ASISTENTE"))
	    	{
			    asistente=new AsistenteGUI();
				asistente.setVisible(true);
				asistente=null;
				this.dispose();
	    	}
	    	else
	    	{
		    	if(user.equals("PERSONAL"))
		    	{
				    login=new LoginGUI();
					login.setVisible(true);
					login=null;
					this.dispose();
				    	
		    	}
	    	}
        }
    }
     public static void main(String args[])
    {
        PersonalGUI personal = new PersonalGUI();
    }
}
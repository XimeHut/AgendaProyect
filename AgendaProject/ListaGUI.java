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

	public class ListaGUI extends JFrame implements ActionListener {
	private AgendaAD agendaad = new AgendaAD();
	public static AsistenteGUI asistente;
	public static InterfaceDoc doc;

	private JButton bConsultar, bSalir;
	private JTextArea  taDatos;
	///////CAMBIO////


	private JPanel panel1, panel2;

    public ListaGUI() {

    	super("CONSULTAR PERSONAL");

    	 bConsultar				= new JButton("Consultar Personal");
    	 bSalir   				= new JButton("Regresar");
    	 bConsultar.addActionListener(this);
    	 bSalir.addActionListener(this);

        taDatos   = new JTextArea(10,30);

        panel1    = new JPanel();
        panel2   = new JPanel();

        panel1.setLayout(new GridLayout(3,2));
        panel2.setLayout(new FlowLayout());

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

    	if(e.getSource() == bConsultar){
          datos=agendaad.listaPersonal();
          taDatos.setText(datos);
    	}

     	if(e.getSource()==bSalir){
	    	String user=agendaad.usuario;
	    	System.out.println("UP: "+user);
	    	if(user.equals("ASISTENTE")){
			    asistente=new AsistenteGUI();
  				asistente.setVisible(true);
  				asistente=null;
  				this.dispose();
  	    }
	    	else{
		    	  if(user.equals("DOCTOR")){
  				    doc=new InterfaceDoc();
  					  doc.setVisible(true);
  					  doc=null;
  					  this.dispose();
		    	   }
	    	  }
        }
    }

     public static void main(String args[])
    {
        ListaGUI personal = new ListaGUI();
    }
}

/**
 * @(#)RegistroC.java
 *
 *
 * @author
 * @version 1.00 2020/4/23
 */
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class UsuariosGUI extends JFrame implements ActionListener
{

 	private JTextField 	tfTipoCuenta, tfNombre, tfCorreo, tfContrasena;
	private JButton    bCapturar, bConsultar, bCancelar, bSalir;
	private JComboBox comboUsuario;
	private String opcionesUsuario[] = {"SELECCIONAR","PACIENTE","ASISTENTE","CONTADOR","PERSONAL","DOCTOR"};
	private JTextArea  taDatos;

    private JPanel panel1, panel2;
    ///////OJO////
    public static InterfaceDoc docInt;
    private AgendaAD agendaad = new AgendaAD();

    public UsuariosGUI()
    {
    	super("Registro");

        tfNombre = new JTextField();
        tfCorreo = new JTextField();
        tfContrasena= new JTextField();

        bCapturar 				= new JButton("DAR DE ALTA");
        bConsultar				= new JButton("Consultar Usuarios");
        bCancelar				= new JButton("Cancelar");
        bSalir   				= new JButton("Regresar");
        // Adicionar addActionListener(this) a los JButtons

        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bCancelar.addActionListener(this);
        bSalir.addActionListener(this);

        comboUsuario = new JComboBox(opcionesUsuario);
        taDatos   = new JTextArea(20,40);

        panel1    = new JPanel();
        panel2   = new JPanel();
        // Definir el Layout los JPanels
        panel1.setLayout(new GridLayout(7,2));
        panel2.setLayout(new FlowLayout());


        panel1.add(new JLabel("Tipo de Cuenta: "));
        panel1.add(comboUsuario);
        panel1.add(new JLabel("Nombre: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("Correo: "));
        panel1.add(tfCorreo);
        panel1.add(new JLabel("Contrase�a: "));
        panel1.add(tfContrasena);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        panel1.add(bCapturar);
        panel1.add(bConsultar);
        //panel1.add(bCancelar);
        panel1.add(bSalir);

        add(panel2);
        setSize(500,500);
        //setVisible(true);
    }

    private String obtenerDatos()
    {
        String datos = "";

        String tipoCuenta  	= (String)comboUsuario.getSelectedItem();
        String nombre 		= tfNombre.getText();
        String correo 		= tfCorreo.getText();
        String contra		= tfContrasena.getText();


        if(nombre.equals("SELECCIONAR") ||nombre.isEmpty()|| correo.isEmpty() || contra.isEmpty() )
            datos = "VACIO";
        else
        {
           	datos = tipoCuenta+"_"+nombre+"_"+correo+"_"+contra;
        }
        return datos;
    }

    private void borrarCampos(){
      comboUsuario.setSelectedItem("SELECCIONAR");
      tfNombre.setText("");
      tfCorreo.setText("");
      tfContrasena.setText("");
    }
  	public void actionPerformed(ActionEvent e){

  		 String datos = "" , respuesta = "";

	    ///CAPTURAR USUARIOS
	        if(e.getSource()==bCapturar)
	        {

		         datos = obtenerDatos();

	            if(datos.equals("VACIO"))
	            {
	                respuesta="LLENAR TODOS LOS CAMPOS";
	            }
	            else
	            {
	            	respuesta = agendaad.capturarUsuarios(datos);
	            	borrarCampos();
	   			}
	            taDatos.setText(respuesta);
        	}

        //CONSULTAR USUARIOS

        	if(e.getSource()==bConsultar)
	        {

	          //respuesta = agendaad.consultarUsuarios();
						respuesta=agendaad.listaPersonal();
	          taDatos.setText(respuesta);
        	}

	       	if(e.getSource()==bSalir)
			{
				if(docInt==null)
					{
						docInt=new InterfaceDoc();
						docInt.setVisible(true);
						docInt=null;
						this.dispose();
					}
			}
    	}


    public static void main(String args[])
    {
        new UsuariosGUI();
    }
}

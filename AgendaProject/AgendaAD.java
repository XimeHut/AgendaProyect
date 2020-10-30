import java.io.*;
import java.util.StringTokenizer;
import java.sql.*;

public class AgendaAD
{
	//Atributos
	private Connection conexion;
	private Statement statement;

	private LoginDP iniciodp;
	private CitaDP actual;
	private TipoCitaDP tipodp;
	private TratamientoDP tradp;
	private UsuariosDP registrodp;
	public static String usuario, nombre, correo, mail;

    public AgendaAD()
    {
    	try
		{
			//Class.forName("com.mysql.jdbc.Driver").newInstance(); //a que base de datos me voy a conectar
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //a que base de datos me voy a conectar
			conexion =  DriverManager.getConnection("jdbc:mysql://localhost/Agenda?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"); //?pasar el parametro // hacer la conexion
			System.out.println("Conexion exitosa a la DB");
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Error 1: "+cnfe);
		}
		catch(InstantiationException ie)
		{
			System.out.println("Error 2: "+ie);
		}
		catch(IllegalAccessException iae)
		{
			System.out.println("Error 3: "+iae);
		}
			catch(SQLException se)
		{
			System.out.println("Error 4: "+se);
		}
    }
	//METODOS LOGIN
	///Metodos de gestion de lista
	public String consultarCorreo(String correo, char[] contrasena){
	 	String datos="";
    	String strInsert = "";
    	String strQuery;
    	ResultSet tr ;
		String pass="";
    	boolean encontrado = false;
		String password= String.valueOf(contrasena);

    	strQuery = "SELECT * FROM usuario WHERE correo='"+correo+"'";
    	iniciodp = new LoginDP();

    	try
    	{
	    	//1. Abrir el archivo
	    	statement = conexion.createStatement();

	    	//2. Procesoar datos
	    	tr = statement.executeQuery(strQuery);

	    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
	    	{
				iniciodp.setTipoCuenta(tr.getString(1)); //puedo poner el nombre o el numero de campo
	    		iniciodp.setNombre(tr.getString(2));
	    		iniciodp.setCorreo(tr.getString(3));
	    		iniciodp.setContrasena(tr.getString(4));
				pass= tr.getString(4);
				System.out.println(pass);

	    		//datos = "FOUND";
	    		encontrado = true;
	    	}
	    	//3.Cerrar el archivo
	    	//archivoIn.close();
	    	statement.close();
				if(encontrado==true){
					if(password.equals(pass)){
							datos=iniciodp.getTipoCuenta();
							usuario=datos;//mandar el tipo de cuenta a los demï¿½s mï¿½todos
							nombre=iniciodp.getNombre();
							correo=iniciodp.getCorreo();
							mail=correo;//Aquï¿½ la escribo dos veces porque hay un bug, si sï¿½lo mando "correo" del otro lado se recibe un null idk why
							//System.out.println("Mail: "+mail);
					}
					else{
						datos="NOT_CORRECT";
					}
				}
	    	if (!encontrado)
	    		datos="NOT_FOUND";

    		System.out.println(strInsert);
    	}
    	catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	return datos;
	 }

	public String consultarContrasena(String contrasena){
		 String respuesta="";
		 return respuesta;
	 }

	 public boolean containsDigit(String s) {
     boolean containsDigit = false;

     if (s != null && !s.isEmpty()) {
         for (char c : s.toCharArray()) {
             if (containsDigit = Character.isDigit(c)) {
							 	 containsDigit=true;
								 break;
             }
         }
     }

     return containsDigit;
 	}

	//METODOS PACIENTE
	public String capturar(String datos)
	{
		actual= new CitaDP();
		ResultSet tr ;
		String resultado="", respuesta="", strQuery="", nombreBD="", fechaBD="", horaBD="", sql="";
		int i=0;
		int coincide =0;
		boolean empalme=false, encontrado=true;
		StringTokenizer st= new StringTokenizer(datos, "_");
		String nombre = st.nextToken();
		String correo=st.nextToken();
        String tipoCita =st.nextToken();
        String fecha   = st.nextToken();
        String hora   = st.nextToken();
        String tratamiento   = st.nextToken();
        String sintomas   = st.nextToken();
        String pago   = st.nextToken();
        String estado=st.nextToken();
        int costo=Integer.parseInt(st.nextToken());

        sql="'"+nombre+"','"+correo+"','"+tipoCita+"','"+fecha+"','"+hora+"','"+tratamiento+"', '"+sintomas+"', '"+pago+"', '"+estado+"', "+costo;
        System.out.println(sql);
		//REVISAR QUE LA FECHA Y HORA A REGISTRAR SEAN DISTINTAS DE LAS DE EL RESTO DE LAS CITAS.
		//construir query
		strQuery="SELECT * FROM cita WHERE fecha='"+fecha+"'";
		System.out.println(strQuery);
		//Comparar con cada registro de bd de ese dï¿½a
		try
		{
    		//1. Abrir
    		statement = conexion.createStatement();

    		//2. Procesar datos
    		tr = statement.executeQuery(strQuery);

    		while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    		{
	    		nombreBD=tr.getString(1); //puedo poner el nombre o el numero de campo
	    		fechaBD=tr.getString(4);
	    		horaBD=tr.getString(5);
	    		//System.out.println("Hora datos" +hora+"\n");
	    		//System.out.println("Hora BD" +horaBD+"\n");
		 		//comparar hora de registro con hora de datos
		 		if(horaBD.equals(hora))
		 		{
		 			//empalme
		 			resultado="Ya hay una cita en ese horario";
					empalme=true;
					//System.out.println("Empalma");
		 		}
    		}
    		//3.Cerrar el archivo
    		statement.close();

    		//No hubo coincidencias, el horario estï¿½ libre
    		if(!empalme)
			{
				resultado=captura(sql);
			}
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
		return resultado;
	}

	public String captura(String datos)
	{
		String resultado="", respuesta="", strInsert="";
		//actual=new CitaDP(datos);
		strInsert="INSERT INTO cita Values("+datos+")";
		System.out.println(strInsert);
		try
	    {
	    	//1. Abrir
	    	statement =	conexion.createStatement();
	    	System.out.println("Statement");

	    	//2. Escribir o almacener los datos en el archivo O TABLA CORRESPONDIENTE
	    	statement.executeUpdate(strInsert);
	    	System.out.println("Execute");

	    	//3.Cerrar archivo
	    	//archivoOut.close();
	    	statement.close();
			System.out.println("Close");
			resultado = "Datos de la cita guardados";			
	    }
	    catch(SQLException ioe)
	    {
	    	resultado = "Error";
	    }
    	return resultado;
	}
	//Consulta general especï¿½fica del doctor TODOS LOS PACIENTES
	public String consultar()
	{
		String datos="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM cita";
    	actual = new CitaDP();

    	try
    	{
	    	//1. Abrir el archivo
	    	statement = conexion.createStatement();

	    	//2. Procesoar datos
	    	tr = statement.executeQuery(strQuery);

	    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
	    	{
	    		actual.setNombre(tr.getString(1)); //puedo poner el nombre o el numero de campo
	    		actual.setCorreo(tr.getString(2));
	    		actual.setTipoCita(tr.getString(3));
	    		actual.setFecha(tr.getString(4));
	    		actual.setHora(tr.getString(5));
	    		actual.setTratamiento(tr.getString(6));
	    		actual.setSintomas(tr.getString(7));
	    		actual.setPago(tr.getString(8));
	    		actual.setEstado(tr.getString(9));
	    		actual.setCosto(Integer.parseInt(tr.getString(10)));

	    		datos=datos+"\n"+actual.toString();
	    	}
	    	//3.Cerrar el archivo
	    	statement.close();
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	return datos;
	}
	//Solo despliega las citas ligadas al correo del paciente
	public String consultarCorreoPaciente(String correo)
	{
		String datos="", correoBD="", strQuery="";
		boolean encontrado=false;
		ResultSet tr ;

		//default: correo no encontrado
		datos="";
		//construir query
		strQuery="SELECT * FROM cita";
		System.out.println(strQuery);
		//Comparar con cada correo en el registro

		try
		{
    		//1. Abrir
    		statement = conexion.createStatement();

    		//2. Procesar datos
    		tr = statement.executeQuery(strQuery);
    		while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    		{
    			correoBD=tr.getString(2);
		 		//comparar correo de registro con correo de datos
		 		if(correoBD.equals(correo))
		 		{
		 			datos=datos+tr.getString(1)+"_"+tr.getString(2)+"_"+tr.getString(3)+"_"+tr.getString(4)+"_"+tr.getString(5)+"_"+tr.getString(6)+"_"+tr.getString(7)+"_"+tr.getString(8)+"_"+tr.getString(9)+"_"+tr.getString(10)+"\n";;
		 			encontrado=true;
		 		}
    		}
    		if(!encontrado)
	    	{
	    		datos="NOT_FOUND";
	    	}
	    	//3.Cerrar el archivo
	    	statement.close();
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
		return datos;
	}

	public String actualizar(String datos)
	{
		String respuesta="", resultado="";
		resultado=capturar(datos);

		if(resultado.equals("Ya hay una cita en ese horario"))
		{
			respuesta=resultado+"\n intente en otro horario";
			System.out.println(respuesta);
		}
		else
		{
			System.out.println(respuesta);
			/////BASE DE DATOS
	        StringTokenizer st= new StringTokenizer(datos, "_");
			String nombre = st.nextToken();
			String correo=st.nextToken();
	        String tipoCita =st.nextToken();
	        String fecha   = st.nextToken();
	        String hora   = st.nextToken();
	        String tratamiento   = st.nextToken();
	        String sintomas   = st.nextToken();
	        String pago   = st.nextToken();
	        String estado=st.nextToken();
	        int costo=Integer.parseInt(st.nextToken());


			String strUpdate="";
			strUpdate = "UPDATE cita SET nombre='"+nombre+"', correo='"+correo+"', tipocita='"+tipoCita+"',fecha='"+fecha+"', hora='"+hora+"', tratamiento='"+tratamiento+"',sintomas='"+sintomas+"' ,pago='"+pago+"',estado='"+estado+"' , costo="+costo+" WHERE fecha='"+fecha+"'AND correo='"+correo+"'";
			System.out.println(strUpdate);
			try
			{
				//1. Abrir el archivo
				statement = conexion.createStatement();

				//2. Escribir o al macenar los datos en el archivo
				statement.executeUpdate(strUpdate);

				//3. Cerrar archivo
				statement.close();
				//resultado="Datos actualizados: "+datos;
				System.out.println(strUpdate);
				respuesta=respuesta + "\nCita modificada";
			}
			catch(SQLException ioe)
			{
				respuesta="ERROR:IOE";
			}
		}
		return respuesta;
	}

	public String eliminar(String datos)
	{
		String respuesta="";
		/////BASE DE DATOS
        StringTokenizer st= new StringTokenizer(datos, "_");
		String nombre = st.nextToken();
		String correo= st.nextToken();
        String tipoCita =st.nextToken();

        String date=st.nextToken();
        StringTokenizer fecha= new StringTokenizer(date, "/");
        String dia= fecha.nextToken();
        String mes= fecha.nextToken();
        String ano= fecha.nextToken();
		String strUpdate="";
		strUpdate = "DELETE FROM cita WHERE correo='"+correo+"' AND fecha='"+date+"'";
		try
		{
			//1. Abrir el archivo
			statement = conexion.createStatement();

			//2. Escribir o al macenar los datos en el archivo
			statement.executeUpdate(strUpdate);

			//3. Cerrar archivo
			statement.close();
			//resultado="Datos actualizados: "+datos;
			System.out.println(strUpdate);
			respuesta=respuesta + "Cita eliminada\n";
		}
		catch(SQLException ioe)
		{
			respuesta="ERROR:IOE";
		}
		return respuesta;
	}

	public String consultarFecha(String fecha)
	{
		String respuesta="", strQuery="";
		ResultSet tr ;
		boolean encontrado=false;

		//REVISAR QUE LA FECHA Y HORA A REGISTRAR SEAN DISTINTAS DE LAS DE EL RESTO DE LAS CITAS.
		//construir query
		strQuery="SELECT * FROM cita WHERE fecha='"+fecha+"'";
		System.out.println(strQuery);
		//Comparar con cada registro de bd de ese dï¿½a
		try
		{
			//1. Abrir
	    	statement = conexion.createStatement();

	    	//2. Procesar datos
	    	tr = statement.executeQuery(strQuery);

	    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
	    	{
	    		encontrado=true;
		   		respuesta=respuesta+tr.getString(1)+"_"+tr.getString(2)+"_"+tr.getString(3)+"_"+tr.getString(4)+"_"+tr.getString(5)+"_"+tr.getString(6)+"_"+tr.getString(7)+"_"+tr.getString(8)+"_"+tr.getString(9)+"_"+tr.getString(10)+"\n";
		 		System.out.println(respuesta);
	    	}
			if(!encontrado)
			{
				respuesta="NOT_FOUND";
			}
		}
		catch(SQLException ioe)
	    {
	    	respuesta = "Error";
	    }
		return respuesta;
	}

	public String consultarAtendidos()
	{
		String datos="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM cita WHERE estado='ATENDIDO'";
    	actual = new CitaDP();

    	try
    	{
	    	//1. Abrir el archivo
	    	statement = conexion.createStatement();

	    	//2. Procesar datos
	    	tr = statement.executeQuery(strQuery);

	    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
	    	{
	    		actual.setNombre(tr.getString(1)); //puedo poner el nombre o el numero de campo
	    		actual.setCorreo(tr.getString(2));
	    		actual.setTipoCita(tr.getString(3));
	    		actual.setFecha(tr.getString(4));
	    		actual.setHora(tr.getString(5));
	    		actual.setTratamiento(tr.getString(6));
	    		actual.setSintomas(tr.getString(7));
	    		actual.setPago(tr.getString(8));
	    		actual.setEstado(tr.getString(9));
	    		actual.setCosto(Integer.parseInt(tr.getString(10)));

	    		datos=datos+actual.toString()+"\n";
		 		//System.out.println(datos);
	    	}
	    	//3.Cerrar el archivo
	    	statement.close();
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	//si no hay pacientes atendidos
    	if(datos.isEmpty())
    	{
    		datos="NOT_FOUND";
    	}
    	return datos;
	}

	public String obtenerCombo()
	{
		String datos="", str ="",combo="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM tipocita";
    	tipodp= new TipoCitaDP();

    	try{
    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);

    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		tipodp.setTipo(tr.getString(1)); //puedo poner el nombre o el numero de campo
    		datos=datos+tipodp.toComboBox()+",";
    	}
    		str = datos.substring(0, datos.length() - 1); //Esto es para no mandar la ultima "," para que se pude meter sin problema en el combo box
    		//	System.out.println(str);
        	combo = str;
	    	//3.Cerrar el archivo
	    	statement.close();
    	}

		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	return combo;
	}

	public String obtenerCombo2()
	{
		String datos="", str ="",combo="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM tratamiento";
    	tradp= new TratamientoDP();

    	try{
    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);

    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		tradp.setTratamiento(tr.getString(1)); //puedo poner el nombre o el numero de campo
    		//actual.setCosto(Integer.parseInt(tr.getString(10)));
    		datos=datos+tradp.toComboBox()+",";
    	}
    		str = datos.substring(0, datos.length() - 1);
    		System.out.println(str);
        	combo = str;
    		System.out.println(combo);
	    	//3.Cerrar el archivo
	    	statement.close();
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	return combo;
	}

	public String obtenerCombo3()
	{
		String datos="SELECCIONAR,", str ="",combo="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM usuario WHERE tipoCuenta = 'PACIENTE'";
    	registrodp= new UsuariosDP();

    	try{
    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);

    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		registrodp.setCorreo(tr.getString(3)); //puedo poner el nombre o el numero de campo
    		//actual.setCosto(Integer.parseInt(tr.getString(10)));
    		datos=datos+registrodp.toComboBox()+",";
    	}
    		str = datos.substring(0, datos.length() - 1);
    		System.out.println(str);
        	combo = str;
    		System.out.println(combo);
	    	//3.Cerrar el archivo
	    	statement.close();
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	return combo;
	}

	//Mï¿½TODOS REGISTRO
	public String capturarRegistro(String datos)
  	{
    	String resultado="";
    	String strInsert = "";
    	iniciodp=new LoginDP(datos);
    	strInsert = "INSERT INTO usuario VALUES("+iniciodp.toStringSql()+");";
    	//System.out.println(linea);

    	try{
    	//1. Abrir el archivo
    	statement =	conexion.createStatement();

    	//2. Escribir o almacener los datos en el archivo O TABLA CORRESPONDIENTE
    	statement.executeUpdate(strInsert);

    	//3.Cerrar archivo
    	//archivoOut.close();
    	statement.close();
    	resultado ="CORRECTO";
    	}
    	catch(SQLException ioe)
    	{
    		resultado = "Erroe: "+ioe;
    	}
    	return resultado;
    }

	public String consultarCorreoRegistro(String correo)
	 {
	 	String datos="";
    	String strInsert = "";
    	String strQuery;
    	ResultSet tr ;
    	boolean encontrado = false;

    	strQuery = "SELECT * FROM usuario WHERE correo='"+correo+"'";
      	iniciodp = new LoginDP();  //DP de registro e inicio son lo mismo

    	try
    	{
	    	//1. Abrir el archivo
	    	statement = conexion.createStatement();

	    	//2. Procesoar datos
	    	tr = statement.executeQuery(strQuery);

	    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
	    	{
          iniciodp.setTipoCuenta(tr.getString(1));
	    		iniciodp.setNombre(tr.getString(2));
	    		iniciodp.setCorreo(tr.getString(3));
	    		iniciodp.setContrasena(tr.getString(4));

	    		datos = "FOUND";
	    		encontrado = true;
	    	}
	    	//3.Cerrar el archivo
	    	//archivoIn.close();
	    	statement.close();
	    	if (!encontrado)
	    		datos="NOT_FOUND";

    		System.out.println(strInsert);
    	}
    	catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	return datos;
	 }

	//METODOS PERSONAL y USUARIOS
	public String consultarUsuarios()
    {
    	String datos = "";
    	String strInsert = "";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM usuario";
    	registrodp = new UsuariosDP();

    	try{
    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);

    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		registrodp.setTipoCuenta(tr.getString(1));
    		registrodp.setNombre(tr.getString(2));
    		registrodp.setCorreo(tr.getString(3));
    		registrodp.setContrasena(tr.getString(4));

    		datos = datos + registrodp.toString() + "\n";

      	}
    	//3.Cerrar el archivo
    	statement.close();
    	System.out.println(strQuery);
    	}
    	//catch(FileNotFoundException ioe)
    	catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	return datos;
    }

    public String consultarPersonal(String fecha)
    {
    	String datos="";
		String strQuery="";
		ResultSet tr;

		strQuery = "SELECT tipoCita, hora FROM cita WHERE estado = 'NO ATENDIDO' AND fecha = '"+fecha+"'";

		try
		{
			//abrir bd
			statement=conexion.createStatement();
			//procesar
			tr=statement.executeQuery(strQuery);

			//enviar
			while(tr.next())
			{
				datos=datos+tr.getString(1)+" HORA: "+tr.getString(2)+"\n";
				System.out.println(datos);
			}

			//cerrar bd
			statement.close();
			System.out.println(strQuery);
		}

		catch(SQLException sqlE)
		{
			datos="NOT_FOUND";
		}

		if(datos == "")
		{
			datos = "NOT_FOUND";
		}
		return datos;
    }

	//METODOS TRATAMIENTOS
	public String guardar(String tratamiento)
	{
		String datos="", strInsert="";

		//Verificar que el tipo de tratamiento no se haya registrado antes
		if(consultarTratamiento2(tratamiento).equals("NOT_FOUND"))
		{
		strInsert="INSERT INTO tratamiento VALUES ('"+tratamiento+"')";
		System.out.println(strInsert);

		try
		{
			//Abrir
			statement = conexion.createStatement();
			//Escribir
			statement.executeUpdate(strInsert);
			//Cerrar
			statement.close();

			datos="Tratamiento capturado: "+tratamiento;
		}

		catch(SQLException ioe)
	    {
	  		datos = "Error";
	  	}
	  	}
	  	else
	  	{
	  		datos="Ya se habï¿½a registrado ese tratamiento: \n"+consultarTratamiento2(tratamiento);
	  	}
	  	return datos;
	}

	public String consultarTratamiento()
	{
		String datos="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM tratamiento";

    	try{

    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);

    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		datos=datos+tr.getString(1)+"\n";
    	}

    	statement.close();
    	}

    	catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}

    	return datos;
    }


    /*consultarTratamiento2()
     *Busca un tipo de cita especï¿½fico, leï¿½do desde el tfTratamiento*/
     public String consultarTratamiento2(String tratamiento)
     {
     	String datos="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM tratamiento WHERE tratamiento= '"+tratamiento+"'";

    	try{

    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);
    	System.out.println(strQuery);

    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		datos=tr.getString(1)+"\n";
    	}

    	statement.close();
    	}

    	catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}


    	//regresar datos: si no hay otro tratamiento con ese nombre
    	if(datos.equals(""))
    	{
    		datos="NOT_FOUND";
    	}

    	return datos;
     }

     public String modificarT(String actual , String nuevo)
     {
     	String strUpdate="",strInsert="", respuesta="";
        strUpdate = "DELETE FROM tratamiento WHERE tratamiento='"+actual+"'";
        strInsert="INSERT INTO tratamiento VALUES ('"+nuevo+"')";
		try
		{
			//1. Abrir el archivo
			statement = conexion.createStatement();

			//2. Escribir o al macenar los datos en el archivo
			statement.executeUpdate(strUpdate);
			statement.executeUpdate(strInsert);


			//3. Cerrar archivo
			statement.close();
			//resultado="Datos actualizados: "+datos;
			System.out.println(strUpdate);
			System.out.println(strInsert);
			respuesta=respuesta + "Tratamiento modificado";
		}
		catch(SQLException ioe)
		{
			respuesta="ERROR:IOE";
		}
		return respuesta;
     }

    //METODOS TIPO CITA
    /*guardar(String tipocita)
	*Recibe el string de tratamiento del GUI.
	*Lo guarda en la tabla tipocita
	**/
	public String guardarTipoCita(String tipo, int costo)
	{
		String datos="", strInsert="";

		//verificar que el tipo de cita no haya sido registrado antes
		if(consultarTipo(tipo).equals("NOT_FOUND"))
		{
		strInsert="INSERT INTO tipocita VALUES ('"+tipo+"', "+costo+")";
		System.out.println(strInsert);

		try
		{
			//Abrir
			statement = conexion.createStatement();
			//Escribir
			statement.executeUpdate(strInsert);
			//Cerrar
			statement.close();

			datos="Tipo de cita capturado: "+tipo+"	$"+costo+"\n";
		}

		catch(SQLException ioe)
	    {
	  		datos = "Error";
	  	}
	  	}
	  	else
	  	{
	  		datos="Ya se habï¿½a registrado ese tipo de cita: \n"+consultarTipo(tipo);
	  	}
	  	return datos;
	}

	/*consultar()
	 *selecciona todos los TiposCita de la tabla TiposCita en la base de datos agenda
	 **/
	public String consultarTipoCita()
	{
		String datos="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM tipocita";

    	try
    	{
    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);

    	while(tr.next()) //mientras tenga registro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		datos=datos+tr.getString(1)+"	$"+tr.getString(2)+"\n";
    	}
    	statement.close();
    	}
    	catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
    	if(datos.isEmpty())
    	{
    		datos="NOT_FOUND";
    	}
    	return datos;
    }

    /*consultarTipo()
     *Busca un tipo de cita especï¿½fico, leï¿½do desde el tfTipoCita*/
     public String consultarTipo(String tipo)
     {
     	String datos="";
    	String strQuery;
    	ResultSet tr ;

    	strQuery = "SELECT * FROM tipocita WHERE tipo= '"+tipo+"'";

    	try{

    	//1. Abrir el archivo
    	statement = conexion.createStatement();

    	//2. Procesoar datos
    	tr = statement.executeQuery(strQuery);

    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    	{
    		datos=tr.getString(1)+"	$"+tr.getString(2)+"\n";
    	}

    	statement.close();
    	}

    	catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}


    	//regresar datos: si no hay otro tipo de cita con ese nombre
    	if(datos.equals(""))
    	{
    		datos="NOT_FOUND";
    	}

    	return datos;
     }

     public String modificarTipo(String actual , String nuevo , int costo)
     {
     	String strUpdate="",strInsert="", respuesta="";
        strUpdate = "DELETE FROM tipoCita WHERE tipo='"+actual+"'";

        strInsert=	strInsert="INSERT INTO tipocita VALUES ('"+nuevo+"', "+costo+")";
		try
		{
			//1. Abrir el archivo
			statement = conexion.createStatement();

			//2. Escribir o al macenar los datos en el archivo
			statement.executeUpdate(strUpdate);
			statement.executeUpdate(strInsert);


			//3. Cerrar archivo
			statement.close();
			//resultado="Datos actualizados: "+datos;
			System.out.println(strUpdate);
			System.out.println(strInsert);
			respuesta=respuesta + "Tipo de Cita modificado";
		}
		catch(SQLException ioe)
		{
			respuesta="ERROR:IOE";
		}
		return respuesta;
     }


    //METODOS USUARIOS
    public String capturarUsuarios(String datos)
    {

    	String resultado="";
    	String strInsert = "";
    	String linea="";

    	registrodp = new UsuariosDP(datos);
    	linea = registrodp.toStringSql();

    	strInsert = "INSERT INTO usuario Values("+linea+");";
    	System.out.println(strInsert);

    	try
    	{
	    	//1. Abrir el archivo
	    	statement =	conexion.createStatement();

	    	//2. Escribir o almacener los datos en el archivo O TABLA CORRESPONDIENTE
	    	statement.executeUpdate(strInsert);

	    	//3.Cerrar archivo
	    	statement.close();

	    	resultado ="Datos registrados correctamente";
    	}
    	catch(SQLException ioe)
    	{
    		resultado = "ERROR";
    	}
    	return resultado;
    }

    //METODOS CONSULTAR TIPO DE PAGO ASISTENTE/CONTADOR
    public String consultarMetodo(String metodo)
    {
		String respuesta="", strQuery="";
		ResultSet tr ;
		boolean encontrado=false;
		strQuery="SELECT * FROM cita WHERE pago='"+metodo+"'";
		System.out.println(strQuery);
		try
		{
			//1. Abrir
    		statement = conexion.createStatement();

    		//2. Procesar datos
    		tr = statement.executeQuery(strQuery);

    		while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    		{
    			encontrado=true;

    		//respuesta=respuesta+tr.getString(1)+" "+tr.getString(2)+" "+tr.getString(3)+" "+tr.getString(4)+" "+tr.getString(5)+" "+tr.getString(6)+" "+tr.getString(7)+" "+tr.getString(8)+" "+tr.getString(9)+" "+tr.getString(10)+"\n";
    		respuesta=respuesta+tr.getString(1)+" "+tr.getString(2)+" "+tr.getString(4)+" "+tr.getString(8)+" "+tr.getString(10)+"\n";
	 		System.out.println(respuesta);
    		}

				if(!encontrado)
				{
					respuesta="NOT_FOUND";
				}
		}
		catch(SQLException ioe)
	    {
	    	respuesta = "Error";
	    }
		return respuesta;
	}
	public String Monto(String tipoCita)
	{
		String datos="", tipo="", strQuery="";
		boolean encontrado=false;
		ResultSet tr ;
		//default: correo no encontrado
		datos="";
		//construir query
		strQuery="SELECT * FROM tipocita where tipo= '"+tipoCita+"'";
		System.out.println(strQuery);
		//Comparar con tipo de cita de la BD
		try
		{
    		//1. Abrir
    		statement = conexion.createStatement();

    		//2. Procesar datos
    		tr = statement.executeQuery(strQuery);
    		while(tr.next())
    		{
    			tipo=tr.getString(1);
		 		//comparar tipocita de GUI con BD
		 		if(tipo.equals(tipoCita))
		 		{
		 			datos=tr.getString(2);
		 			encontrado=true;
		 		}
		 		else
		 		{
		 			datos="NOT_FOUND";
		 		}
    		}
	    	//3.Cerrar el archivo
	    	statement.close();
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
		return datos;
	}

	public String listaPersonal(){
		String datos="", strQuery="";
		ResultSet tr ;

		strQuery="set @row=0";
		try{
    	statement = conexion.createStatement();
    	tr = statement.executeQuery(strQuery);
			strQuery="SELECT (@row:=@row+1) AS row, tipoCuenta, nombre, correo, contrasena FROM usuario ORDER BY tipoCuenta;";
			tr = statement.executeQuery(strQuery);
			while(tr.next()){
			datos=datos+tr.getString(1)+"-"+tr.getString(2)+"-"+tr.getString(3)+"-"+tr.getString(4)+"-"+tr.getString(5)+"\n";
			}
	    statement.close();
    }
		catch(SQLException sqlE){
    		datos = "Erroe: "+sqlE;
    }

		return datos;
	}
	
	//METODOS CONTADOR
	/*consultarCorreoPacienteConta(correo)
	 *Consulta las citas ya atendidas de un paciente usando su correo
	 */
	public String consultarCorreoPacienteConta(String correo)
	{
		String datos="", correoBD="", strQuery="";
		boolean encontrado=false;
		ResultSet tr ;

		//default: correo no encontrado
		datos="";
		//construir query
		strQuery="SELECT * FROM cita WHERE correo='"+correo+"' AND estado='ATENDIDO'";
		System.out.println(strQuery);
		//Comparar con cada correo en el registro

		try
		{
    		//1. Abrir
    		statement = conexion.createStatement();

    		//2. Procesar datos
    		tr = statement.executeQuery(strQuery);
    		while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
    		{
    			correoBD=tr.getString(2);
		 		//comparar correo de registro con correo de datos
		 		if(correoBD.equals(correo))
		 		{
		 			datos=datos+tr.getString(1)+"  "+tr.getString(10)+"  "+tr.getString(4)+"\n";//DESPLIEGA: nombre del paciente, costo y fecha de la cita
		 			encontrado=true;
		 		}
    		}
    		if(!encontrado)
	    	{
	    		datos="NOT_FOUND";
	    	}
	    	//3.Cerrar el archivo
	    	statement.close();
    	}
		catch(SQLException sqlE)
    	{
    		datos = "Erroe: "+sqlE;
    	}
		return datos;
	}
	/*consultar por mes
	 *Devuelve nombre, correo e importe de las citas ocurridas en el mes en curso
	 *
	 *ALERTA: minibug. 
	 *A futuro, cuando hubiera citas de más de un año, esto mostrará las citas del mismo mes en ambos años. 
	 *Por ejemplo, si fuera Enero 2020 y Enero 2021, entregaría los datos de ambos meses indistintamente. °O°
	 */
	public String consultarMes(String mes)
	{
		String respuesta="", strQuery="", strMes="", fecha="";
		ResultSet tr ;
		boolean encontrado=false;
		
		respuesta="Correo	Nombre	Importe \n";
		
		//construir query
		strQuery="SELECT * FROM cita WHERE estado='ATENDIDO'";
		System.out.println(strQuery);
		//Comparar con cada registro de bd de ese dï¿½a
		try
		{
			//1. Abrir
	    	statement = conexion.createStatement();

	    	//2. Procesar datos
	    	tr = statement.executeQuery(strQuery);

	    	while(tr.next()) //mienstras tenga refistro el next me va a a entregar true , voy a utiluzar lo mututors para darle los valores que obtengo de los campos
	    	{
	    		fecha=tr.getString(4); //obtiene la fecha (dd/mm/aa)
	    		//obtener mes
	    		StringTokenizer st= new StringTokenizer(fecha, "/");
	    		String dia=st.nextToken();
	    		strMes=st.nextToken();
	    		
	    		//comparar
	    		if(mes.equals(strMes))
	    		{
	    			encontrado=true;
	    			respuesta=respuesta+tr.getString(2)+"  "+tr.getString(1)+"  "+tr.getString(10)+"\n";
	    		}
	    	}
			if(!encontrado)
			{
				respuesta="NOT_FOUND";
			}
		}
		catch(SQLException ioe)
	    {
	    	respuesta = "Error";
	    }
		return respuesta;
	}
}

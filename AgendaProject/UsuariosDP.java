/**
 * @(#)RegistroDP.java
 *
 *
 * @author 
 * @version 1.00 2020/4/26
 */

import java.util.StringTokenizer;

public class UsuariosDP {
	
	private String tipoCuenta, nombre, correo, contra;

    public UsuariosDP() {
    		
	tipoCuenta = "";
	nombre = "";
	correo = "";
	contra= "";
    	
    }
    public UsuariosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		tipoCuenta= st.nextToken();
		nombre = st.nextToken();
		correo= st.nextToken();
	    contra = st.nextToken();	
	}
	
	public String getTipoCuenta()
	{
		return this.tipoCuenta;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	public String getCorreo()
	{
		return this.correo;
	}
	
	public String getContrasena()
	{
		return this.contra;
	}
	
	//Mutators
	
	public void setTipoCuenta(String tip)
	{
		this.tipoCuenta = tip;
	}
	
	public void setNombre(String name)
	{
		this.nombre = name;
	}
	
	public void setCorreo(String mail)
	{
		this.correo = mail;
	}
	
	public void setContrasena(String password)
	{
		this.contra = password;
	}
	
	public String toString()
	{
		return this.tipoCuenta+"_"+this.nombre+"_"+this.correo+"_"+this.contra;
	}
	public String toStringSql()
	{
		return "'"+this.tipoCuenta+"','"+this.nombre+"','"+this.correo+"','"+this.contra+"'";
	}
	 public String toComboBox()
	    {
	    //	return 	"\"" + this.tipo + "\"";
	    return this.correo;
	    }

}
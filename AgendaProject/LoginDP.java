
import java.util.StringTokenizer;

public class LoginDP
{
    // Atributos: Varaibles de Instancia o de Clase
    private String tipoCuenta, nombre, correo, contrasena;
	private LoginDP next;
    // Constructores
    public LoginDP()
    {
        this.tipoCuenta  = "";
        this.nombre = "";
        this.correo   = "";
        this.contrasena   = "";
    }

    public LoginDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        tipoCuenta  = st.nextToken();
        nombre = st.nextToken();
        correo   = st.nextToken();
        contrasena   = st.nextToken();
    }

    // Metodos: Accesors (geter's) y Mutators (seter's)
    // Accesors: obtienen el valor de la variable para enviarlo otro programa
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
        return this.contrasena;
    }

	public LoginDP getNext()
    {
    	return this.next;
    }
    // Mutators: cambian el valor de la variable por el dado desde otro programa
    public void setTipoCuenta(String tipo)
    {
    	this.tipoCuenta=tipo;
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
        this.contrasena = password;
    }

    public void setNext(LoginDP nodo)
    {
    	this.next=nodo;
    }

    public String toString()
    {
        return this.tipoCuenta+"_"+this.nombre+"_"+this.correo+"_"+this.contrasena;
    }

    public String toStringSql()
    {
        return "'"+this.tipoCuenta+"','"+this.nombre+"','"+this.correo+"','"+this.contrasena+"'";
    }
}

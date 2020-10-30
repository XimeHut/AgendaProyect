import java.util.StringTokenizer;

public class CitaDP
{
    // Atributos: Varaibles de Instancia o de Clase
    private String npaciente, nombre, correo,fecha, hora, tCita, tpago, tratamiento, sintomas, estado;
    private int costo;
	//private CitaDP next;
    // Constructores
    public CitaDP()
    {
        //this.npaciente  = "";
        this.nombre = "";
        this.correo="";
        this.tCita   = "";
        this.fecha   = "";
        this.hora   = "";
        this.tratamiento   = "";
        this.sintomas="";
		this.tpago   = "";//tipo de pago: tarjeta o efectivo        
        this.estado   = "";
        this.costo  = 0;
    }
    
    public CitaDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        nombre = st.nextToken();
        correo=st.nextToken();
        tCita   = st.nextToken();        
        fecha   = st.nextToken();
        hora   = st.nextToken();
        tratamiento   = st.nextToken();
        sintomas=st.nextToken();
        tpago   = st.nextToken();        
        estado   = st.nextToken();
        costo  = Integer.parseInt(st.nextToken()); 
    }
    
    // Metodos: Accesors (geter's) y Mutators (seter's)
    // Accesors: obtienen el valor de la variable para enviarlo otro programa
   /* public String getNoPaciente()
    {
        return this.npaciente;
    }*/
    
    public String getNombre()
    {
        return this.nombre;
    }

    public String getCorreo()
    {
        return this.correo;
    }
    
    public String getFecha()
    {
        return this.fecha;
    }
    
    public String getHora()
    {
        return this.hora;
    }
    
    public String getTipoCita()
    {
        return this.tCita;
    }
    
    public String getPago()
    {
        return this.tpago;
    }  
    
    public String getEstado()
    {
        return this.estado;
    }
    
    public String getTratamiento()
    {
        return this.tratamiento;
    }
    
  	public String getSintomas()
    {
        return this.sintomas;
    }
    
    public int getCosto()
    {
        return this.costo;
    }
     
    // Mutators: cambian el valor de la variable por el dado desde otro programa
   /* public void setNoPaciente(String num)
    {
        this.npaciente = num;
    }*/
    
    public void setNombre(String name)
    {
        this.nombre = name;
    }
    
    public void setCorreo(String mail)
    {
        this.correo = mail;
    }
    
   	public void setFecha(String date)
    {
        this.fecha = date;
    }    
    
    public void setHora(String hr)
    {
        this.hora = hr;
    }
    
    public void setTipoCita(String tipo)
    {
        this.tCita = tipo;
    }
    
    public void setPago(String tp)
    {
        this.tpago = tp;
    }
    
    public void setEstado(String est)
    {
        this.estado = est;
    }
    
    public void setTratamiento(String trat)
    {
        this.tratamiento = trat;
    }
    
    public void setSintomas(String sint)
    {
        this.sintomas = sint;
    }
        
    public void setCosto(int cantidad)
    {
        this.costo = cantidad;
    }
    
    
    public String toString()
    {
        return nombre+"_"+correo+"_"+tCita+"_"+fecha+"_"+hora+"_"+tratamiento+"_"+sintomas+"_"+tpago+"_"+estado+"_"+costo;
    }
    
    public String toStringSql()
    {
        return "'"+this.nombre+"','"+this.correo+"','"+this.tCita+"','"+this.fecha+"','"+this.hora+"','"+this.tratamiento+"','"+this.sintomas+"', '"+this.tpago+"', '"+this.estado+"', "+this.costo;
    }
}













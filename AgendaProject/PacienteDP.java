import java.util.StringTokenizer;

public class PacienteDP
{
    // Atributos: Varaibles de Instancia o de Clase
    private String npaciente, nombre, estado, tratamiento, fecha, hora, motivo, tpago;
    private double costo;
	private CitaDP next;
    // Constructores
    public PacienteDP()
    {
        this.npaciente  = "";
        this.nombre = "";
        this.estado   = "";
        this.tratamiento   = "";
        this.fecha   = "";
        this.hora   = "";
        this.motivo   = "";
        this.tpago   = "";//tipo de pago: tarjeta o efectivo
        this.costo  = 0;
    }
    
    public PacienteDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        npaciente  = st.nextToken();
        nombre = st.nextToken();
        estado   = st.nextToken();
        tratamiento   = st.nextToken();
        fecha   = st.nextToken();
        hora   = st.nextToken();
        motivo   = st.nextToken();
        tpago   = st.nextToken();
        costo  = Double.parseDouble(st.nextToken());
        
 
    }
    
    // Metodos: Accesors (geter's) y Mutators (seter's)
    // Accesors: obtienen el valor de la variable para enviarlo otro programa
    public String getNoPaciente()
    {
        return this.npaciente;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public String getEstado()
    {
        return this.estado;
    }
    
    public String getTratamiento()
    {
        return this.tratamiento;
    }
    
    public String getFecha()
    {
        return this.fecha;
    }
    
    public String getHora()
    {
        return this.hora;
    }
    
    public String getMotivo()
    {
        return this.motivo;
    }
    
    public String getPago()
    {
        return this.tpago;
    }    
    
    public double getCosto()
    {
        return this.costo;
    }
    
	public CitaDP getNext()
    {
    	return this.next;
    }	    
    // Mutators: cambian el valor de la variable por el dado desde otro programa
    public void setNoPaciente(String num)
    {
        this.npaciente = num;
    }
    
    public void setNombre(String name)
    {
        this.nombre = name;
    }
    
    public void setEstado(String est)
    {
        this.estado = est;
    }
    
    public void setTratamiento(String trat)
    {
        this.tratamiento = trat;
    }
    
    public void setFecha(String date)
    {
        this.fecha = date;
    }    
    
    public void setHora(String hr)
    {
        this.hora = hr;
    }
    
    public void setMotivo(String mot)
    {
        this.motivo = mot;
    }
    
    public void setPago(String tp)
    {
        this.tpago = tp;
    }
    
    public void setCosto(double cantidad)
    {
        this.costo = cantidad;
    }
    
    public void setNext(CitaDP nodo)
    {
    	this.next=nodo;
    }
    
    public String toString()
    {
        return this.npaciente+"_"+this.nombre+"_"+this.estado+"_"+this.tratamiento+"_"+this.fecha+"_"+this.hora+"_"+this.motivo+"_"+this.tpago+"_"+this.costo;
    }
    
    public String toStringSql()
    {
        return "'"+this.npaciente+"','"+this.nombre+"','"+this.estado+"','"+this.tratamiento+"','"+this.fecha+"','"+this.hora+"','"+this.motivo+"','"+this.tpago+"',"+this.costo+"";
    }
}













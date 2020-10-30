/**
 * @(#)TipoCitaDP.java
 *
 *
 * @author 
 * @version 1.00 2020/5/7
 */
import java.util.StringTokenizer;

public class TipoCitaDP {
	
	private String tipo;
	private int costo;

    public TipoCitaDP() {
    	
    	this.tipo = "";
    	this.costo = 0;
    }
    
    
    public TipoCitaDP(String datos)
    {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	tipo = st.nextToken();
    	costo = Integer.parseInt(st.nextToken()); 
    }
    
    public String getTipo()
    {
    	return this.tipo;
    }
    
     public Integer getCosto()
    {
    	return this.costo;
    }
    
     public void setTipo(String tip)
    {
        this.tipo =tip;
    }
      public void setCosto(Integer cos)
    {
        this.costo=cos;
    }
    
    	
    public String toComboBox()
    {
    //	return 	"\"" + this.tipo + "\"";
    return this.tipo;
    }
    
    public String toStringSql()
    {
    	return "'"+this.tipo+"', "+this.costo;
    
    }
    
    
}
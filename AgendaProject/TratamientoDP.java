/**
 * @(#)TratamientoDP.java
 *
 *
 * @author 
 * @version 1.00 2020/5/8
 */
import java.util.StringTokenizer;

public class TratamientoDP{

    private String tratamiento;


    public TratamientoDP() {
    	
    	this.tratamiento = "";
    
    }
    
	    
	    public TratamientoDP(String datos)
	    {
	    	StringTokenizer st = new StringTokenizer(datos,"_");
	    	tratamiento = st.nextToken();
	    
	    }
	    
	    public String getTratamiento()
	    {
	    	return this.tratamiento;
	    }
	    
	  
	     public void setTratamiento(String tip)
	    {
	        this.tratamiento =tip;
	    }
	  
	    	
	    public String toComboBox()
	    {
	    //	return 	"\"" + this.tipo + "\"";
	    return this.tratamiento;
	    }
	    
	    public String toStringSql()
	    {
	    	return "'"+this.tratamiento+"'";
	    
	    }
    
    
}
package classloader.classloader0;

public class Sample { 
    private Sample instance; 

    public void setSample(Object instance) { 
    	System.out.println(456);
        this.instance = (Sample) instance; 
    } 
 }

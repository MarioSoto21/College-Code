/* 
Mario Soto
Lab 8 creates class Arithmetic that implements interface Sequence */
public class Arithmetic implements Sequences {
    private float initialValue;
    private float element;
    private float difference;
    
  
  
    @Override
    public float getInitialValue(){
        return initialValue;
    }
    
    @Override
    public float getNext(){
      return initialValue +=difference;
           
    }
    
    @Override
    public void restart(){
        this.initialValue = initialValue-difference*(element);
        
    }
    
    @Override
    public void setStart(float value){
        this.initialValue = value;
    }
    @Override
    public float getNthElement(int element){ 
       if(element <=5){
        initialValue = initialValue+difference*(element-1);
        return initialValue;
       }
       else
           element = element/2;
           initialValue = initialValue+difference*(element-1);
        return initialValue;
    }
    //Constructor
      public Arithmetic(float initialValue, float difference){
        this.initialValue = initialValue;
        this.difference = difference;
       }
      }

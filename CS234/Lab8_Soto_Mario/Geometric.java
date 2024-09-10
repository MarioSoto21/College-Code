/* 
Mario Soto
Lab 8 create class Geometric that implement interface Sequence */
public class Geometric implements Sequences {
    private float initialValue;
    private float element;
    private float ratio;
    
       
    @Override
    public float getInitialValue(){
        return this.initialValue;
    }
    
    @Override
    public float getNext(){
        element = 0;
        element++;
        return initialValue= initialValue *(float)Math.pow(ratio,element);
    }
    
    @Override
    public void restart(){
        for(float i= 0; i<=5;i++){
        this.initialValue = initialValue/(float)Math.pow(ratio, element);
        }
        this.element=0;
        this.initialValue = initialValue;
    }
    
    @Override
    public void setStart(float value){
        this.initialValue = value;
    }
    @Override
    public float getNthElement(int element){ 
        if(element<=5){
            element = element -1;
       this.initialValue = initialValue*(float)Math.pow(ratio, element);
       element++;
        return initialValue;
        }
        else 
            element = element-4;
       element = element -1;
       this.initialValue = initialValue*(float)Math.pow(ratio, element);
        return initialValue;
    }
    //
    public Geometric(float initialValue, float ratio){
        this.initialValue = initialValue;
        this.ratio = ratio;
        }
    }


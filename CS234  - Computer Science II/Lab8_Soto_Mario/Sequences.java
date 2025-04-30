/* 
Mario Soto
Lab 8 create interface sequence*/
public interface Sequences {
    float getInitialValue();
    float getNext();
    void restart();
    void setStart(float value);
    float getNthElement(int element);
}

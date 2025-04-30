/*
Mario Soto
Lab 7 create class Payment
 */
public class Payment {
     //Declaring variables
    private double paymentAmount;
    
    //parameterized constructor
    public Payment(double paymentAmount) {
        super();
        this.paymentAmount = paymentAmount;
    }
    
    //Setters and getters
    public double getPaymentAmount() {
        return paymentAmount;
    }
    public void setPayementAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
   // display paymentDetails
    public void paymentDetails() {
        System.out.println( "Payment of $" + this.getPaymentAmount());
    }
}

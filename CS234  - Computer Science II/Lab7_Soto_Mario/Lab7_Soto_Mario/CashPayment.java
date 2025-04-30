/*
Mario Soto
Lab 7 create class cashPayment extends class Payment
 */
public class CashPayment extends Payment {
    private String clientName;

    //Parameterized constructor
    public CashPayment(double value, String clientName) {
        super(value);
        this.clientName = clientName;
    }
    public String getClientName(){
        return clientName;
    }
   // display paymentDetails
    @Override
   public void paymentDetails() {
        System.out.println( "Payment of $" + this.getPaymentAmount());
        System.out.println("        Client " + this.getClientName() + " paid in cash");
            System.out.println( "------------------------------------");

   }
}

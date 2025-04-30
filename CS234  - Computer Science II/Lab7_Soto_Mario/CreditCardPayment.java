/*
Mario Soto
Lab 7 create class CreditCardPayment extends class Payment
 */
public class CreditCardPayment extends Payment {
     //Declaring variables
    private String cardNumber;
    private String expirationDate;
    
    //parameterized constructor
    public CreditCardPayment(double value, String cardNumber, String expirationDate) {
        super(value);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }
    
    //Setters and Getters
   public String getCardNumber(){
       return cardNumber;
   }
   public String getCardExpiration(){
    return expirationDate;
}
    // display paymentDetails
    @Override
    public void paymentDetails() {
        System.out.println( "Using the card " + this.getCardNumber() + ", with expiration date " + 
                this.getCardExpiration());
            System.out.println( "------------------------------------");

    }
}

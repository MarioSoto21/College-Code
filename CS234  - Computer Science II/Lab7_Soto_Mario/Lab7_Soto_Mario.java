

/*Mario Soto
Lab7
 */
public class Lab7_Soto_Mario {

    public static void main(String[] args) {
        Payment pay1 = new Payment(13.95);
        
        Payment cash1 = new CashPayment(99.99, "Emma");
        CashPayment cash2 = new CashPayment(15.60, "John");
        
        CreditCardPayment credit1 = new CreditCardPayment(56.50, "********^678","06/22");
        Payment credit2 = new CreditCardPayment(19.65, "********123", "07/25");
        
        pay1.paymentDetails();
        cash1.paymentDetails();
        cash2.paymentDetails();
        credit1.paymentDetails();
        credit2.paymentDetails();
    }
}
    
 
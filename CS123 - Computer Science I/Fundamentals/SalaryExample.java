// Calculate and print the total pay given the weekly salary and the 
// number of weeks worked. Use string concatenation with the totalPay
// variable to prouce the output. Total Pay = $3000. Don't hardcode
// the number 3000 in your print statement.
public class SalaryExample {
	public static void main(String argv[]) {
		int weeklySalary = 500;
		int numWeeks = 6;
		int totalPay = weeklySalary * numWeeks;
		
		System.out.println("Total Pay = $" + totalPay);
	}
}
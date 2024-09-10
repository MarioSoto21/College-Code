// The PayrollCalculator class listed below calculates and print the weekly pay
// for two employees.
public class PayrollCalculator {
		
	
	public static void main(String[] ars ) {
		double hourlyRate, hoursWorked, weeklyPay;
		String employee;
		
		// Calculate weekly oat for Fred
		employee = "Fred";
		hourlyRate = 12.50;
		hoursWorked = 20;
		weeklyPay = hourlyRate * hoursWorked;
		System.out.println(employee +":" + weeklyPay);
		
		// Calculate weekly oat for Fred
		employee = "Amir";
		hourlyRate = 15.0;
		hoursWorked = 35;
		weeklyPay = hourlyRate * hoursWorked;
		System.out.println(employee +":" + weeklyPay);
	}
}
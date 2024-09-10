public class PayrollCalculator2 {
		
	
	public static void calculatePay(String employee, double hourlyRate, double hoursWorked) {
		double weeklyPay = hourlyRate * hoursWorked;
		System.out.println(employee + ":" + weeklyPay);
	}
		
		public static void main(String[] ars ) {
			calculatePay("Fred", 12.50, 20.0);
			calculatePay("Amir", 15.0, 35.0);
			calculatePay("James",18.0, 60.0);
			calculatePay("Chen", 15, 35);

	}
}
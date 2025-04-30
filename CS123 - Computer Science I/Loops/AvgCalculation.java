import java.util.Scanner;
public class AvgCalculation {
	public static void main(String[] args) {
		int number, sum = 0, count = 0;
		double average;
		Scanner  scan = new Scanner(System.in);
		
		// 1. initalize the loop variable (get the first number )
		System.out.println("Please enter a number to average in or -1 to stop: ");
		number = scan.nextInt();
		
		// 2. test the loop variable (against sentinel value)
		while (number != -1) {
			sum += number; // add number to sum
			count++; // count the number
		
		// 3. Change the loop variable (get a new number)
		System.out.print("Please enter a number to average in or -1 to stop: ");
		number = scan.nextInt();
		}
		System.out.println(count);
		// calculate average
		average = (double) sum/count;
		System.out.println("The average is " + average);
		
		scan.close();
	}
}
 
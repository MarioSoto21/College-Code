// This programi is supposed to figure out the total money value given
// the number of dimes, quarters and nickes. There is an error in the
// calculation of the total. Fix the error
public class MoneyCalculation {
	public static void main(String argv[]) {
		int numDimes = 7;
		int numQuarters = 3;
		int numNickels = 8;
		
		int total = numDimes * 10 + numQuarters * 25 + numNickels * 5;
		System.out.println("Total = " +total);
	}
}
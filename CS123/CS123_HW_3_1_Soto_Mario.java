import java.util.Scanner;

public class CS123_HW_3_1_Soto_Mario {
	
	public static void main(String argv[]) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter how heavy the letter is: ");
		double n = scan.nextDouble();
		double num = Math.ceil(n);
		double i = 30;
		if(num <= 1){
			System.out.println(i);
		}
		else {
		double total = (num-1)*25 + i;
		System.out.println(total + " cents");
		}	
	}
}
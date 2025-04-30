
import java.util.Scanner;
public class CS123_HW_4_Soto_Mario {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Integer: ");
		
		int n = in.nextInt();
		boolean isprime = true;
		for(int count = 1; count <= n; count++) {
					System.out.println("Hello World");
			
			}
			for(int i = 2; i<= n/2; i++) {
				if(n%i==0)
					isprime = false;
					break;
			}
			if(isprime)
				System.out.println(n+" is prime");
			else
				System.out.println(n+" is not a prime");
	}
}
 
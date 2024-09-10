import java.util.Scanner;
public class TestLoop {
	public static void main(String[] args) {
			/*int x = 5;
			while(x > 0) {
					System.out.println(x);
					x = x-1;
					*/
			/*for( int x = 5; x >0; x--) {
					System.out.println(x);
			}
			*/
			/*int x = 1;
			while(x <= 10) {
					System.out.println(x);
					x = x+1;
			}
			*/
			/*for( int x = 5; x <=15; x++){
					System.out.println(x);
			}
			*/
			/*int x = 10;
			while(x <=100) {
				System.out.println(x);
				x = x+10;
			}
			*/
			/* int x = 1;
			while(x<=10) {
					System.out.println(x);
				x = x+1;
			}
			*/
			/*for(int x = 10; x >= 5; x--) {
					System.out.println(x);
			}
			*/
			/*int x = 10;
			while(x>0) {
					System.out.println(x);
					x--;
			}
			*/
			/*for(int x = 100; x >= 0; x= x-10) {//x-=10
					System.out.println(x);
			}
			*/
			/*for(int x = 10; x >=1; x--) {
					
					if(x%2 == 0)
						System.out.println(x +" is even");
					else
						System.out.println(x +" is odd");
					
			}
			*/
			/*for(int x = 0; x<=10; x++) {
				int y = 10*x;
				System.out.println(y);
			}
			*/
// Write a Java program to reverse 3 digits integer as follow: If
// integer 123 is the input from user,for example, your program must 
// print 321 use / and %, + and other operators ifnecessary.
		Scanner in = new Scanner(System.in);
			System.out.println("Integer n: ");
			int n = in.nextInt();
			int a;
			while(n>0) {
				a = n%10;
				System.out.println(a);
				n = n/10;				
			}
	}
}

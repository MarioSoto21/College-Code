//Mario Soto
//P2.2
import java.util.Scanner;
public class Lab2_Soto_Mario_2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double Td, T, RH, a = 17.27, b = 237.7, f;
		System.out.print("Relative humidity(between 0 and 1): ");
		RH = input.nextDouble();
		System.out.print("Temperature (in degrees C: ");
		T = input.nextDouble();
		f = (a*T)/(b+T) + Math.log(RH);
		Td = b*f/(a-f);
		System.out.printf("Dew point: %.2f",Td);
	}
}
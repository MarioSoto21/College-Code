
import java.util.Scanner;


public class DewPointTester {
    public static void main(String[] args) {
         Scanner in=new Scanner(System.in);
         System.out.println("please enter the the relative humidity (between 0 and 1 : ");
         double RH=in.nextDouble();
         System.out.println("please enter the temperature (in degrees C) : ");
         double T=in.nextInt();
         DewPointTemperature Td=new DewPointTemperature(RH,T);
         System.out.printf(" The dew point value is %13.2f : ",Td.getDewPoint());
    }
   
}

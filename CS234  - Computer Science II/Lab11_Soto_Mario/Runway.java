/*
Mario Soto
Lab 11
*/
 import java.util.LinkedList;
import java.util.Queue;

public class Runway
{
   public Queue<String> takingOff;
   public Queue<String> landing;

   public Runway()
   {
       takingOff=new LinkedList<String>();
       landing=new LinkedList<String>();
       String flight = null;
   }
   public void addTakeOff(String flightNumber){
       takingOff.add(flightNumber);
   }
   public void addLanding(String flightNumber){
       landing.add(flightNumber);
   }
   public void handleFlights()
   {
      while (landing.size() > 0)
      {
         String flightNumber = landing.remove();
         System.out.println("Flight " + flightNumber + " is landing. ");
      }
      while(takingOff.size() > 0)
      {
         String flightNumber = takingOff.remove();
         System.out.println("Flight " + flightNumber + " is taking off.");
      }
      
      {
         System.out.println("There are no flights waiting to take off or land");
      }
   } 

   public static void main (String[] args)
   {
      Runway simulator = new Runway();
   }
}    


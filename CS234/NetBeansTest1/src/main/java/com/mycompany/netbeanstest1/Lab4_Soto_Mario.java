
import java.util.*;
public class Lab4_Soto_Mario
{
   static Scanner in = new Scanner(System.in);
   static int[][] seatingChart;

   public static void main(String[] args)
   {
       seatingChart = new int[][] {
          { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
          { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
          { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 },
          { 10, 10, 20, 20, 20, 20, 20, 20, 10, 10 },
          { 10, 10, 20, 20, 20, 20, 20, 20, 10, 10 },
          { 10, 10, 20, 20, 20, 20, 20, 20, 10, 10 },
          { 20, 20, 30, 30, 40, 40, 30, 30, 20, 20 },
          { 20, 30, 30, 40, 50, 50, 40, 30, 30, 20 },
          { 30, 40, 50, 50, 50, 50, 50, 50, 40, 30 },
       };
       System.out.println("Welcome to the Box Office Program.");                //Introduction Statements
       System.out.println("Please note that seats are arranged such that");
       System.out.println("row 1, column 1, is the bottom front row.");
       System.out.println("Also, a zero denotes the seat is already sold.");
       printSeats(seatingChart);
       char response = 'Y';
       while ((response == 'Y') || (response == 'y'))
       {
       System.out.print("Pick by Seat <s>, Price <p>, or Quit <q>: ");
       char choice = in.next().charAt(0);
           switch (choice)
            {
               case'S':case's':
               { sellSeatByNumber(seatingChart);
                   break; }
               case'P':case'p':
               { sellSeatByPrice(seatingChart);
                   break; }
               case'Q':case'q':
               { System.out.print("Thank you, come again!");
                   System.exit(0); }
               default:
               { System.out.println("Error: Invalid choice."); }
            }
           System.out.print("Would you like to reserve another seat (Y/N)?: ");
        response = in.next().charAt(0);
       }
       System.out.print("Thank you, come again!");
       }

   public static void printSeats(int seatingChart[][])
   {
       for(int i=0; i<seatingChart.length; i++)
       {
           for(int j=0; j<seatingChart[i].length; j++)
           {
               if (j>0)
                   System.out.print("");
                   System.out.print(seatingChart[i][j]);
           }
           System.out.println();
       }
   }


public static void sellSeatByPrice(int seatingChart[][])
{
   System.out.print("Please enter a price for the seat you would like: ");
   int price = in.nextInt();
   // boolean found = false;
   out: for (int i=0;i<9;i++)
     for (int j=0;j<10;j++)
        if (seatingChart[i][j]==price)
        { seatingChart[i][j]=0; break out; } // Notice this change

    printSeats(seatingChart);


}
   public static void sellSeatByNumber(int seatingChart[][])
   {
       System.out.println("Enter a row, then enter a seat number.");
       System.out.print("What row would you like to sit on?:");
       int row = in.nextInt();
       row = Math.abs(row-9);
       System.out.print("What seat of that row would you like to sit in?:");
       int col = in.nextInt();
       col -= 1;
       if (seatingChart[row][col]!=0)
       {
           seatingChart[row][col] = 0;
           printSeats(seatingChart);
           System.out.println("Your seat has been reserved and reflected with a 0 on the chart now.");
       }
       else { System.out.println("Sorry, that seat is already taken."); }
   }
}

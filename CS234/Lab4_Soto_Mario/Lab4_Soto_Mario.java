
import java.util.Scanner;
public class Lab4_Soto_Mario
{
	/*
	Prints the price of seats in a grid like pattern.
	@param seatingChart a 2D array of prices
	Marks a seat with the price given to 0.
	@param seats the array of seat prices
	@param price the price to mark to zero
	Marks a seat based on a given row and seat number from input.
	@param seats the array of seat prices
 */
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
       
       System.out.println("Please note that seats are arranged such that");
       System.out.println("row 1, column 1, is the bottom front row.");
       System.out.println("Also, a zero denotes the seat is already sold.");
       printSeats(seatingChart);
       char response = 'Y';
       while ((response == 'Y') || (response == 'y'))
       {
       System.out.print("Pick by <s>eat, <p>rice, or <q> to quit: ");
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
       }
       System.out.print("Thank you, come again!");
       }
	/*	Prints the price of seats in a grid like pattern.
		@param seats a 2D array of prices */
   public static void printSeats(int seatingChart[][])
   {
       for(int i=0; i<seatingChart.length; i++)
       {
           for(int j=0; j<seatingChart[i].length; j++)
           {
               if (j>0)
                   System.out.print("");
				   //System.out.print(seatingChart[i][j]);
				    System.out.printf("%2d ",seatingChart[i][j]);
           }
           System.out.println();
       }
   }

/* 	Marks a seat with the price given to 0.
	@param seats the array of seat prices
	@param price the price to mark to zero*/
public static void sellSeatByPrice(int seatingChart[][])
{
   System.out.println("Please enter a price for the seat you would like: ");
   int price = in.nextInt();

   out: for (int i=0;i<9;i++)
     for (int j=0;j<10;j++)
        if (seatingChart[i][j]==price)
        { seatingChart[i][j]=0; break out; }

    printSeats(seatingChart);
}
	/*
		Marks a seat based on a given row and seat number from input.
		@param seats the array of seat prices
	*/
   public static void sellSeatByNumber(int seatingChart[][])
   {
       System.out.println("Enter the row number you want:");
       int row = in.nextInt();
       row = Math.abs(row-9);
       System.out.println("Enter the seat number you want:");
       int col = in.nextInt();
       col -= 1;
       if (seatingChart[row][col]!=0)
       {
           seatingChart[row][col] = 0;
           printSeats(seatingChart);
       }
       else { System.out.println("Sorry, seat already occupied."); }
   }
}

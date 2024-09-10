/* Mario Soto
Lab3
*/

import java.util.Scanner;

public class Lab3_Soto_Mario {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
		
        while(true) {
            menu();
            int ch=sc.nextInt();
            System.out.println("You selected "+ch);
            if(ch==1) {
                option1();
            }
            else if(ch==2) {
                option2();
            }
            else if(ch==3) {
                option3();
            }
            else if(ch==4) {
                option4();
            }
            else if(ch==5) {
                System.out.println("Bye");
                break;
            }
            else {
                System.out.println("Invalid option");
            }
            System.out.println("-----------------------------------------------");
        }

    }
    public static void menu() {
        System.out.println("Select an option: "); 
		System.out.println("1. Print numbers ");
		System.out.println("2. Count Digits ");
		System.out.println("3. Interleave ");
		System.out.println("4. Countdown ");
		System.out.println("5. Quit");
    }
    //Option 1 
    public static void option1() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Write the staring number:");
        int start=sc.nextInt();
        System.out.println("Write the ending number:");
        int end=sc.nextInt();
        System.out.println("Write the power:");
        int power=sc.nextInt();
        printNumbers(start,end,power);
    }
  
    public static void printNumbers(int start,int end,int power) {
        for(int i=start;i<=end;i++) {
			 double pow = Math.pow(i,power);
            System.out.print(pow+" ");
        }
        System.out.println();
    }
    //Option 2 
        public static void option2() {
            Scanner sc=new Scanner(System.in);
            System.out.println("Write the string:");
            String str=sc.nextLine();
            System.out.println("There are: "+countDigits(str)+" digits");
        }
   
    public static int countDigits(String s) {
        int cnt=0;
        for(int i=0;i<s.length();i++) {
            if(Character.isDigit(s.charAt(i))) {
                cnt++;
            }
        }
        return cnt;
    }
    //Option 3 
    public static void option3() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String str=sc.nextLine();
        System.out.println("Enter character:");
        char c=sc.nextLine().charAt(0);
        System.out.println("Interleaved string = "+interleave(c,str));
    }
   
    public static String interleave(char c,String s) {
        String str="", nstr="";
		char ch;
	
	  for (int i=s.length()-1; i>=0; i--)
      {
        nstr= nstr+ s.charAt(i);
      }
      
        for( int j=0;j<nstr.length();j++) {
			str+=nstr.charAt(j);
            str+=c;
        }
        return str;
    }
	//Option 4 
	public static void option4() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the starting number:" );
        int n=sc.nextInt();
        countdown(n);
        System.out.println();
    }

       public static void countdown(int n) {
            if (n >= 0) {
                 System.out.print(n+" ");
                  countdown(n - 1);
            }
       }

}
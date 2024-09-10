
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeBook {
    // Create a new map object that stores student name and grade
    private Map<String, String> grades = new LinkedHashMap<>();
    // Default constructor
    public GradeBook() {
    }
    // Method menu
    public void menu() {
        // Create a scanner object
        Scanner scanner = new Scanner(System.in);
        // Create a vraiable choice
        char choice;
        // Infinite loop
        while (true) {
            // Print menu
            System.out.println("A)dd R)emove M)odify P)rint Q)uit");
            // Read user choice
            choice = scanner.next().charAt(0);
            scanner.nextLine();
            // If it is q then break loop
            if(Character.toLowerCase(choice) == 'q') {
                break;
            }
            else {
                // If it is a then add student name and grade to map
                // Take user student name and grade
                // Add to map using put
                if(Character.toLowerCase(choice) == 'a') {
                    String studentName, studentGrade;
                    System.out.print("Enter the student's name: ");
                    studentName = scanner.nextLine();
                    System.out.print("Enter the student's grade: ");
                    studentGrade = scanner.nextLine();
                    grades.put(studentName, studentGrade);
                }
                // If it is r, then remove student from map
                // Take user student name
                else if(Character.toLowerCase(choice) == 'r') {
                    String studentName;
                    System.out.print("Enter the student's name: ");
                    studentName = scanner.nextLine();
                    grades.remove(studentName);
                }
                // It it is m, then modify student grade
                else if(Character.toLowerCase(choice) == 'm') {
                    String studentName, studentGrade;
                    System.out.print("Enter the student's name: ");
                    studentName = scanner.nextLine();
                    System.out.print("Enter the student's grade: ");
                    studentGrade = scanner.nextLine();
                    grades.replace(studentName, studentGrade);
                }
                // If it is p, then print map
                else if(Character.toLowerCase(choice) == 'p') {
                    for( Map.Entry<String, String> entry : grades.entrySet() ){
                        System.out.println( entry.getKey() + ": " + entry.getValue() );
                    }
                }
                // Else the key is not valid
                else {
                    System.out.println("Not a valid option.");
                }
            }
        }
    }
}

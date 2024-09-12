package src.main.app;

import java.util.Scanner;
import src.main.mvc.Model;
import src.main.mvc.View;
import src.main.mvc.Controller;

public class Runner {
    public static void main(String[] args) {
        // Initialize input array
        int[] array = {5, 3, 7, 2, 8, 4, 1, 6};

        // Create instances of Model, View, and Controller
        Model model = new Model(array);
        View view = new View();
        Controller controller = new Controller(model, view);
	
        // Display original and sorted arrays
        controller.performSorting();

        // Input target value for binary search
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to search for: ");
        int target = scanner.nextInt();

        // Perform binary search and display the result
        controller.performSearch(target);
    }
}

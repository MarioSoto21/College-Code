package src.view;

import src.controller.Controller;
import java.util.Scanner;

interface UserInterface {
		void update(String message);
		void setController(Controller controller);
		void sendMessage();
}

public class View implements UserInterface{

		private String name;
		private Controller controller;

		public View(String name) {
				this.name = name;

		}

		@Override
		public void setController(Controller controller) {
				this.controller = controller;
		}

		@Override
		public void update(String message) {
				System.out.println(name + " : " + message);
		}

		@Override
		public void sendMessage() {
				Scanner in = new Scanner(System.in);
				
				System.out.println("Enter a string of digits and split them with comma: ");
				String data = in.nextLine();
				controller.saveData(data);
				
				System.out.println("Enter the number you're looking for: ");
				while (in.hasNextInt()) {
						int number = in.nextInt();
						System.out.println(controller.lookup(number));
						System.out.println("Enter the number you're looking for: ");
				}
		}
}
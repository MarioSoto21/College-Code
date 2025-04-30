package src.main.app;

import src.view.View;
import src.controller.Controller;
import src.model.Model;

public class Runner {

	public static void main(String[] args) {

		Controller controller;
		Model model = new Model();
		View view = new View("View 1");
		controller = new Controller(model, view);
		view.setController(controller);
		view.sendMessage();
	}
}
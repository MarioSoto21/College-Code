package src.controller;

import src.view.View;
import src.model.Model;

public class Controller {

		private Model model;
		private View view;

		public Controller(Model model, View view) {
				this.model = model;
				this.view = view;
		}

		public String lookup(int key) {
				return model.lookup(key);
		}

		public void saveData(String data) {
				if(model.saveData(data)) {
						updateView("Your data (" + data + ") has been successfully delivered.");
				}
		}

		public void updateView(String message) {
				view.update(message);
		}

}
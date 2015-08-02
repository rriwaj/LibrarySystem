package ls.controller.menu;

import javafx.fxml.FXML;
import ls.controller.utility.Utility;

public class BothController extends AdministratorController {

	@FXML
	private void searchByISBNClick() {
		Utility.loadSearchByISBNView(anchorPane);
	}

	@FXML
	private void checkoutBookClick() {
		Utility.loadCheckoutBookView(anchorPane);
	}
}

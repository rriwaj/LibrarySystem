package ls.controller.menu;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import ls.controller.Main;

public class BothController extends AdministratorController {
	final String rootUrl = "../view/checkout/";

	@FXML
	private void searchByISBNClick() {
		renderSearchByISBNView();
	}

	@FXML
	private void checkoutBookClick() {
		renderCheckoutBookView();
	}

	private void renderCheckoutBookView() {
		try {
			bodyLayout.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(rootUrl
					+ "checkoutBook.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			bodyLayout.getChildren().add(checkoutBook);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void renderSearchByISBNView() {
		try {
			bodyLayout.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(rootUrl
					+ "searchBookCheckoutRecordEntries.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			bodyLayout.getChildren().add(checkoutBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

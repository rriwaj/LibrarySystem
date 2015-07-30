package ls.controller.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import ls.controller.Main;

public class MemberController implements Initializable {
	@FXML
	private BorderPane rootLayout;
	@FXML
	private AnchorPane bodyLayout;
	private final String rootUrl = "../view/checkout/";

	@FXML
	private void searchByISBNClick() {
		renderSearchByISBNView();
	}

	@FXML
	private void checkoutBookClick() {
		renderCheckoutBookView();
	}

	// initialize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// load CheckoutBook Form initially
		renderCheckoutBookView();
	}

	// Helper Methods
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

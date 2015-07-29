package ls.controller.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import ls.controller.Main;

public class member implements Initializable {
	private AnchorPane rootLayout;

	public void mSearchByISBNClick() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/checkout/searchAllCheckouts.fxml"));
			AnchorPane checkoutBook;
			checkoutBook = (AnchorPane) loader.load();
			rootLayout.getChildren().clear();
			rootLayout.getChildren().setAll(checkoutBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// initilize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}

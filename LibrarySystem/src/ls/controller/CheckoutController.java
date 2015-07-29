package ls.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CheckoutController implements Initializable {
	@FXML
	private Button btnCheckout;
	@FXML
	private TextField txtMemberId;
	@FXML
	private TextField txtISBNNo;
	@FXML
	private Label lblMessage;
	FXMLLoader loader = new FXMLLoader();
	@FXML
	private AnchorPane rootLayout;

	public void btnCheckoutClick() {
		try {
			txtMemberId.setText("New ");
			//
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/BookCopyNotAvailable.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();

			rootLayout.getChildren().clear();
			rootLayout.getChildren().setAll(checkoutBook);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// initilize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

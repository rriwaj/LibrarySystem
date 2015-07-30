package ls.controller.checkout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import ls.controller.Main;

public class CheckoutBookController implements Initializable {
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
			if (txtMemberId.getText().length() > 0) {
				rootLayout.getChildren().clear();
				rootLayout.getChildren().add(lblMessage);
				lblMessage.setText("Thankyou your due date is 21/09/2015");
			} else {
				//
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class
						.getResource("../view/checkout/bookCopyNotAvailable.fxml"));
				AnchorPane checkoutBook = (AnchorPane) loader.load();
				rootLayout.getChildren().clear();
				rootLayout.getChildren().setAll(checkoutBook);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// initilize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

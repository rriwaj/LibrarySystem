package ls.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import ls.controller.utility.Utility;

public class MemberController implements Initializable {
	@FXML
	private BorderPane rootLayout;
	@FXML
	private AnchorPane bodyLayout;

	@FXML
	private void searchByISBNClick() {
		Utility.loadSearchByISBNView(bodyLayout);
	}

	@FXML
	private void checkoutBookClick() {
		Utility.loadCheckoutBookView(bodyLayout);
	}

	@FXML
	private void showMemberRecordClick() {
		Utility.loadPrintMemberRecordView(bodyLayout);
	}

	// initialize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// load CheckoutBook Form initially
		Utility.loadCheckoutBookView(bodyLayout);
	}

}

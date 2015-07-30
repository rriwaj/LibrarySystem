package ls.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import ls.controller.utility.Utility;

public class AdministratorController implements Initializable {
	@FXML
	protected BorderPane rootLayout;
	@FXML
	protected AnchorPane bodyLayout;
	@FXML
	public Label lblMsg;
	public static String holdMsg;

	@FXML
	protected void addEditMemberClick() {
		Utility.loadAddEditMemberView(bodyLayout);
	}

	@FXML
	protected void addBookCopyClick() {
		Utility.loadAddBookCopyView(bodyLayout);
	}

	@FXML
	protected void addBookClick() {
		Utility.loadAddBookView(bodyLayout);
	}

	// initialize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// load CheckoutBook Form initially
		Utility.loadAddEditMemberView(bodyLayout);
	}
}

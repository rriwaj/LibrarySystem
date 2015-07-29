package ls.controller.checkout;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class BookCopyNotAvailableController implements Initializable {
	@FXML
	private Label lblMessage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		lblMessage.setText("Book with ISBN not found");
	}
}
package ls.controller.book;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AddBookCopiesController implements Initializable {

	@FXML
	private TextField txtISBNNo;
	@FXML
	private Label lblMessage;
	@FXML
	private TextField txtNumberOfCopies;
	@FXML
	private Button btnAdd;
	FXMLLoader loader = new FXMLLoader();
	@FXML
	private AnchorPane rootLayout;

	public void btnBookCopyadded() {
//		System.out.println("jsdfbnkjs");
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
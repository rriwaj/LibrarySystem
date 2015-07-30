package ls.controller.book;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuthorController implements Initializable {
	@FXML
	private Button btnSave;
	@FXML
	private AnchorPane rootLayout;
	@FXML
	private TextField txtFirstName;

	public void btnSaveClick() {
		System.out.println("User Input:" + txtFirstName.getText());
		Stage stage = (Stage) btnSave.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void btnSaveAuthorClick() {
		Stage stage = (Stage) rootLayout.getScene().getWindow();
		System.out.println(stage);
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}

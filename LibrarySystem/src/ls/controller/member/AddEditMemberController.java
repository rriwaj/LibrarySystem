package ls.controller.member;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ls.controller.utility.Utility;

public class AddEditMemberController implements Initializable {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label lblMessage;

	@FXML
	private Button btnSave;
	@FXML
	private ComboBox<String> cmbState;
	@FXML
	private Label hdnText;

	@FXML
	public void applySaveMember() {

		Utility.showAlertBox("New Member Added");
		Utility.loadSearchMemberView(anchorPane);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmbState.getItems().addAll(Utility.getStates());
	}

	
}

package ls.controller.member;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ls.controller.utility.Utility;
import ls.dataaccess.DataAccess;
import ls.model.Address;
import ls.model.LibraryMember;

public class CopyOfAddEditMemberControlleroLD implements Initializable {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label lblMessage;

	@FXML
	private Button btnSave;
	@FXML
	private static TextField txtMemberId;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtZip;
	@FXML
	private TextField txtPhoneNo;
	@FXML
	private ComboBox<String> cmbState;
	@FXML
	private Label hdnText;

	private DataAccess dataAccess;

	public CopyOfAddEditMemberControlleroLD() {
		dataAccess = Utility.getDataAcessFacadeInstance();

	}

	@FXML
	public void applySaveMember() {
		Address memberAddress = new Address(txtStreet.getText(),
				txtCity.getText(), cmbState.getPromptText(), txtZip.getText());
		HashMap<String, LibraryMember> allMember = dataAccess.readMemberMap();
		if (allMember != null) {
			String memberId = Integer.toString(allMember.size() + 1);
			LibraryMember member = new LibraryMember(txtFirstName.getText(),
					txtLastName.getText(), txtPhoneNo.getText(), memberAddress,
					memberId);
			dataAccess.saveNewMember(member);
			//Utility.clearAllTextBoxFromAnchorPane(anchorPane);
			lblMessage.setText("Library Member with id " + memberId + " added");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
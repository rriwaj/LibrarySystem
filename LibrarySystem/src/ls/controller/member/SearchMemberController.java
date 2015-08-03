package ls.controller.member;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ls.controller.utility.Utility;
import ls.dataaccess.DataAccess;
import ls.model.Address;
import ls.model.LibraryMember;

public class SearchMemberController implements Initializable {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private AnchorPane anchorPaneAddEdit;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnAddNew;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnSave;
	@FXML
	private TextField txtMemberId;
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
	private TextField txtPhone;
	@FXML
	private TextField txtState;
	@FXML
	private Label hdnText;
	@FXML
	private TableView<Member> tblMember;
	@FXML
	private TableColumn<Member, String> colIdNumber;
	@FXML
	private TableColumn<Member, String> colName;
	@FXML
	private TableColumn<Member, String> colAddress;
	@FXML
	private TableColumn<Member, String> colPhoneNo;
	@FXML
	private TableColumn<Member, String> colEdit;
	@FXML
	private Label lblMessage;
	@FXML
	private Label lblMessageAddEdit;

	private DataAccess dataAccess;

	private static LibraryMember member;
	private static String memberId;

	public SearchMemberController() {
		dataAccess = Utility.getDataAcessFacadeInstance();
	}

	@FXML
	public void searchMembers() {
		lblMessage.setText("");
		if (!validateSearchFields()) {
			return;
		}

		LibraryMember mem = dataAccess.searchMember(txtMemberId.getText());
		List<Member> memberForTableView = new ArrayList<Member>();
		memberForTableView.add(new Member(mem.getMemberId(), mem.getFirstName()
				+ " " + mem.getLastName(), mem.getAddress().toString(), mem
				.getTelephone()));
		ObservableList<Member> data = FXCollections
				.observableArrayList(memberForTableView);
		colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
		tblMember.setItems(data);
	}

	private boolean validateSearchFields() {
		if (txtMemberId.getText().equals("")) {
			lblMessage.setText("Member ID: Value is required.");
			return false;
		}
		return true;
	}

	@FXML
	public void goToEditMember() {
		Member mem = (Member) tblMember.getSelectionModel().getSelectedItem();
		if (mem == null) {
			lblMessage.setText("Member selection is required.");
			return;
		}
		member = dataAccess.searchMember(mem.getIdNumber());
		Utility.loadAddEditMemberView(anchorPane);
	}

	@FXML
	public void goToAddMember() {
		Utility.loadAddEditMemberView(anchorPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (location.toString().contains("searchMembers.fxml")) {
			HashMap<String, LibraryMember> allMembers = dataAccess
					.readMemberMap();
			if (allMembers != null) {
				printAllMembersToTableView(allMembers.values());
			}
			if (memberId != null) {
				if (member == null) {
					lblMessage.setText("Library Member with id " + memberId
							+ " added.");
				} else {
					lblMessage.setText("Library Member with id " + memberId
							+ " updated.");
				}
			}
			memberId = null;
			member = null;
		} else {
			if (member != null) {
				PopulateEditView();
			}
		}
	}

	private void PopulateEditView() {
		txtFirstName.setText(member.getFirstName());
		txtLastName.setText(member.getLastName());
		txtStreet.setText(member.getAddress().getStreet());
		txtCity.setText(member.getAddress().getCity());
		txtState.setText(member.getAddress().getState());
		txtZip.setText(member.getAddress().getZip());
		txtPhone.setText(member.getTelephone());
	}

	@FXML
	public void applySaveMember() {
		if (!validateAddEditFields()) {
			return;
		}
		Address memberAddress = new Address(txtStreet.getText(),
				txtCity.getText(), txtState.getText(), txtZip.getText());
		HashMap<String, LibraryMember> allMember = dataAccess.readMemberMap();
		if (allMember != null) {
			if (member == null) {
				// add member
				memberId = Integer.toString(allMember.size() + 1);
				LibraryMember newMember = new LibraryMember(
						txtFirstName.getText(), txtLastName.getText(),
						txtPhone.getText(), memberAddress, memberId);
				dataAccess.saveNewMember(newMember);
			} else {
				// edit member
				memberId = member.getMemberId();
				LibraryMember editedMember = new LibraryMember(
						txtFirstName.getText(), txtLastName.getText(),
						txtPhone.getText(), memberAddress, memberId);
				dataAccess.saveNewMember(editedMember);
			}
			Utility.loadSearchMembersView(anchorPaneAddEdit);
		}
	}

	private boolean validateAddEditFields() {
		if (txtFirstName.getText().equals("")) {
			lblMessageAddEdit.setText("First Name: Value is required.");
			return false;
		}
		if (txtLastName.getText().equals("")) {
			lblMessageAddEdit.setText("Last Name: Value is required.");
			return false;
		}
		if (txtStreet.getText().equals("")) {
			lblMessageAddEdit.setText("Street: Value is required.");
			return false;
		}
		if (txtCity.getText().equals("")) {
			lblMessageAddEdit.setText("City: Value is required.");
			return false;
		}
		if (txtState.getText().equals("")) {
			lblMessageAddEdit.setText("State: Value is required.");
			return false;
		}
		if (txtZip.getText().equals("")) {
			lblMessageAddEdit.setText("Zip: Value is required.");
			return false;
		}
		if (txtPhone.getText().equals("")) {
			lblMessageAddEdit.setText("Phone: Value is required.");
			return false;
		}
		return true;
	}

	private void printAllMembersToTableView(Collection<LibraryMember> allMembers) {
		List<Member> memberForTableView = new ArrayList<Member>();
		for (LibraryMember mem : allMembers) {
			memberForTableView.add(new Member(mem.getMemberId(), mem
					.getFirstName() + " " + mem.getLastName(), mem.getAddress()
					.toString(), mem.getTelephone()));
		}
		ObservableList<Member> data = FXCollections
				.observableArrayList(memberForTableView);
		colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
		tblMember.setItems(data);
	}
}

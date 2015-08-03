package ls.controller.member;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	private TextField txtPhoneNo;
	@FXML
	private ComboBox<String> cmbState;
	@FXML
	private Label hdnText;

	@FXML
	private TableView<Member> tblMember;
	@FXML
	TableColumn<Member, String> colIdNumber;
	@FXML
	TableColumn<Member, String> colName;
	@FXML
	TableColumn<Member, String> colAddress;
	@FXML
	TableColumn<Member, String> colPhoneNo;
	@FXML
	TableColumn<Member, String> colEdit;

	private String selectedMemberIdToEdit;
	@FXML
	Label lblMessage;
	private DataAccess dataAccess;

	public SearchMemberController() {
		dataAccess = Utility.getDataAcessFacadeInstance();
	}

	@FXML
	public void searchMembers() {		
	
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

	@FXML
	public void goToEditMember() {
		try {
			Member mem = (Member) tblMember.getSelectionModel()
					.getSelectedItem();
			if (mem != null) {
				selectedMemberIdToEdit = mem.getIdNumber();
				Utility.loadAddEditMemberView(anchorPane);
			}
		} catch (NullPointerException ex) {
			lblMessage.setText("Please select the row to edit");
		}
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
		} else {
			if (selectedMemberIdToEdit != null) {
				PopulateEditView(dataAccess
						.searchMember(selectedMemberIdToEdit));
			}
		}
	}

	private void PopulateEditView(LibraryMember searchMember) {

		txtFirstName.setText(searchMember.getFirstName());
		txtLastName.setText(searchMember.getLastName());
		txtStreet.setText(searchMember.getAddress().getStreet());
		txtCity.setText(searchMember.getAddress().getCity());
		txtZip.setText(searchMember.getAddress().getZip());
		txtPhoneNo.setText(searchMember.getTelephone());
		txtMemberId.setText(searchMember.getMemberId());
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
			lblMessage.setText("Library Member with id " + memberId + " added");
		}
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

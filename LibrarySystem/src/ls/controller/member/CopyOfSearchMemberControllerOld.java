package ls.controller.member;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ls.controller.checkout.OverDueBook;
import ls.controller.utility.Utility;
import ls.dataaccess.DataAccess;
import ls.model.LibraryMember;

public class CopyOfSearchMemberControllerOld implements Initializable {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button btnSearch;
	@FXML
	private Button btnAddNew;

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

	private List<LibraryMember> allMembers;
	private DataAccess dataAccess;
	
	public CopyOfSearchMemberControllerOld() {
		dataAccess = Utility.getDataAcessFacadeInstance();
	}

	@FXML
	public void searchMembers() {
		System.out.println("searchMembers");
	}

	public void goToEditMember() {
		System.out.println("goToEditMember");
	}

	@FXML
	public void goToAddMember() {
		Utility.loadAddEditMemberView(anchorPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		Collection<LibraryMember> m = dataAccess.readMemberMap().values();
//		if (m != null) {
//			allMembers = Arrays.asList((LibraryMember[]) dataAccess
//					.readMemberMap().values().toArray());
//		}
//
//		if (allMembers != null) {
//			List<Member> memberForTableView = new ArrayList<Member>();
//
//			for (LibraryMember mem : allMembers) {
//				memberForTableView.add(new Member(mem.getMemberId(), mem
//						.getFirstName() + " " + mem.getLastName(), mem
//						.getAddress().toString(), mem.getTelephone()));
//			}
//			ObservableList<Member> data = FXCollections
//					.observableArrayList(memberForTableView);
//			colIdNumber.setCellValueFactory(new PropertyValueFactory<>(
//					"idNumber"));
//			colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//			colAddress
//					.setCellValueFactory(new PropertyValueFactory<>("address"));
//			colPhoneNo
//					.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
//			tblMember.setItems(data);
//		}
		// OverDueBook overDueBook = new OverDueBook(book.getIsbn(),
		// book.getTitle());
		// OverDueBook overDueBook = new OverDueBook("1212", "Test");
		// List<OverDueBook> list = new ArrayList<OverDueBook>();
		// list.add(overDueBook);
		// ObservableList<OverDueBook> data = FXCollections
		// .observableArrayList(list);
		// System.out.println(list);
		// colISBN.setCellValueFactory(new PropertyValueFactory<OverDueBook,
		// String>(
		// "ISBN"));
		// colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		// tblOverDueBook.setItems(data);

	}
}

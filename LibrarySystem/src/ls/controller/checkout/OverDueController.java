package ls.controller.checkout;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.layout.Pane;
import ls.controller.utility.Utility;
import ls.dataaccess.DataAccessFacade;
import ls.model.*;

public class OverDueController implements Initializable {
	@FXML
	private AnchorPane anchorPaneContent;
	@FXML
	private Label lblMessage;
	@FXML
	private TextField txtMemberId;
	@FXML
	private TextField txtBookISBN;
	@FXML
	private Button btnSearch;
	@FXML
	private Pane paneTableView;
	@FXML
	private Button btnPrint;

	@FXML
	TableView<OverDueBook> tblOverDueBook;

	@FXML
	TableColumn<OverDueBook, String> colISBN;
	@FXML
	TableColumn<OverDueBook, String> colTitle;
	@FXML
	TableColumn<OverDueBook, String> colCopyNo;
	@FXML
	TableColumn<OverDueBook, String> colMemberName;
	@FXML
	TableColumn<OverDueBook, String> colDueDate;
	@FXML
	TableColumn<OverDueBook, String> colStatus;

	@FXML
	TableView<OverDueBook> tblMemberRecords;

	DataAccessFacade dataAccessFacade;
	private Book book;

	public OverDueController() {
		dataAccessFacade = Utility.getDataAcessFacadeInstance();
	}

	@FXML
	public void checkBookCopyOverDue() {
		if (!validateOverDueFields()) {
			return;
		}
		paneTableView.setVisible(false);
		lblMessage.setText("");
		List<OverDueBook> overDueBookList = new ArrayList<OverDueBook>();
		// search books by ISBN
		book = dataAccessFacade.searchBook(txtBookISBN.getText());
		// if book exists
		if (book != null) {
			// get all library members as each library member has its own
			// checkout record
			HashMap<String, LibraryMember> members = dataAccessFacade
					.readMemberMap();

			// for each library members get their checkout records
			for (LibraryMember member : members.values()) {
				// get all checkout record entries from checkout records
				List<CheckoutRecordEntry> checkoutRecordEntries = member
						.getCheckoutRecord().getCheckoutRecordEntries();
				// get checkout record entry from checkout record
				for (CheckoutRecordEntry memberCheckoutRecordEntry : checkoutRecordEntries) {
					// get bookcopy from each checkout record entry
					BookCopy memberBookCopy = memberCheckoutRecordEntry
							.getBookCopy();
					if (memberBookCopy.getBook().getIsbn() // filter list of
															// book member
															// has with isbn
															// entered
							.equals(txtBookISBN.getText())) {
						// check if overdue or not
						if (isBookCopyOverDue(memberCheckoutRecordEntry
								.getDueDate())) {
							// overdueBookCopies.add(memberBookCopy);
							overDueBookList.add(new OverDueBook(memberBookCopy
									.getBook().getIsbn(), memberBookCopy
									.getBook().getTitle(), memberBookCopy
									.getCopyNum(), member.getFirstName() + " "
									+ member.getLastName(),
									memberCheckoutRecordEntry.getDueDate()
											.toString(), "Overdue"));
							// unAvailableBookCopies.remove(memberBookCopy);
						} else {
							overDueBookList.add(new OverDueBook(memberBookCopy
									.getBook().getIsbn(), memberBookCopy
									.getBook().getTitle(), memberBookCopy
									.getCopyNum(), member.getFirstName() + " "
									+ member.getLastName(),
									memberCheckoutRecordEntry.getDueDate()
											.toString(), "Unavailable"));
						}
					}
				}
			}
			printCheckoutRecordEntries(overDueBookList);
			paneTableView.setVisible(true);
		} else {
			lblMessage.setText("Book does not exist with ISBN "
					+ txtBookISBN.getText());
		}
	}

	private boolean validateOverDueFields() {
		if (txtBookISBN.getText().equals("")) {
			lblMessage.setText("Book ISBN: Value is required.");
			return false;
		}
		return true;
	}

	@FXML
	public void checkMemberRecord() {
		paneTableView.setVisible(false);
		lblMessage.setText("");

		String memberId = txtMemberId.getText();

		if (!validateFields()) {
			return;
		}

		List<OverDueBook> memberRecordsList = new ArrayList<OverDueBook>();
		// get all library members as each library member has its own
		// checkout record
		HashMap<String, LibraryMember> members = dataAccessFacade
				.readMemberMap();

		// for each library members get their checkout records

		LibraryMember existedLibraryMember = dataAccessFacade
				.searchMember(memberId);

		if (existedLibraryMember == null) {
			lblMessage.setText("Member does not exist with ID " + memberId);
			return;
		}

		for (LibraryMember member : members.values()) {
			if (member.getMemberId().equals(memberId)) {
				// get all checkout record entries from checkout records
				List<CheckoutRecordEntry> checkoutRecordEntries = member
						.getCheckoutRecord().getCheckoutRecordEntries();
				// get checkout record entry from checkout record
				for (CheckoutRecordEntry memberCheckoutRecordEntry : checkoutRecordEntries) {
					// get bookcopy from each checkout record entry
					BookCopy memberBookCopy = memberCheckoutRecordEntry
							.getBookCopy();
					memberRecordsList.add(new OverDueBook(memberBookCopy
							.getBook().getIsbn(), memberBookCopy.getBook()
							.getTitle(), memberBookCopy.getCopyNum(), "",
							memberCheckoutRecordEntry.getCheckoutDate()
									.toString(), memberCheckoutRecordEntry
									.getDueDate().toString()));
				}
			}

		}

		if (memberRecordsList.isEmpty()) {
			lblMessage.setText("No data found for member with ID " + memberId);
			return;
		}

		showCheckoutRecordEntries(memberRecordsList);
		paneTableView.setVisible(true);
	}

	private boolean validateFields() {
		if (txtMemberId.getText().equals("")) {
			lblMessage.setText("Member ID: Value is required.");
			return false;
		}
		return true;
	}

	private boolean isBookCopyOverDue(LocalDate dueDate) {

		return dueDate.isBefore(LocalDate.now());
	}

	private void printCheckoutRecordEntries(List<OverDueBook> list) {
		ObservableList<OverDueBook> data = FXCollections
				.observableArrayList(list);
		colISBN.setCellValueFactory(new PropertyValueFactory<OverDueBook, String>(
				"ISBN"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		colCopyNo.setCellValueFactory(new PropertyValueFactory<>("copyNo"));
		colMemberName.setCellValueFactory(new PropertyValueFactory<>(
				"memberName"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tblOverDueBook.setItems(data);
	}

	private void showCheckoutRecordEntries(List<OverDueBook> list) {
		ObservableList<OverDueBook> data = FXCollections
				.observableArrayList(list);
		colISBN.setCellValueFactory(new PropertyValueFactory<OverDueBook, String>(
				"ISBN"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		colCopyNo.setCellValueFactory(new PropertyValueFactory<>("copyNo"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tblMemberRecords.setItems(data);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}

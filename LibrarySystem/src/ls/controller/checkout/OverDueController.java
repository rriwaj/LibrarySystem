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

	DataAccessFacade dataAccessFacade;
	private Book book;

	public OverDueController() {
		dataAccessFacade = Utility.getDataAcessFacadeInstance();
	}

	@FXML
	public void checkBookCopyOverDue() {
		List<OverDueBook> overDueBookList = new ArrayList<OverDueBook>();
		if (txtBookISBN.getText().length() > 0) {
			// search books by ISBN
			book = dataAccessFacade.searchBook(txtBookISBN.getText());
			// if book exists
			if (book != null) {
				// get all book copies
				BookCopy[] bookCopies = book.getCopies();
				List<BookCopy> availableBookCopies = new ArrayList<BookCopy>();
				List<BookCopy> unAvailableBookCopies = new ArrayList<BookCopy>();
				// List<BookCopy> overdueBookCopies = new ArrayList<BookCopy>();
				for (int i = 0; i < bookCopies.length; i++) {
					if (bookCopies[i].isAvailable()) {
						availableBookCopies.add(bookCopies[i]);
					} else {
						unAvailableBookCopies.add(bookCopies[i]);
					}
				}

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
						if (memberBookCopy.getBook().getIsbn() //filter list of book member has with isbn entered
								.equals(txtBookISBN.getText())) {
							// check if overdue or not
							if (isBookCopyOverDue(memberCheckoutRecordEntry
									.getDueDate())) {
								// overdueBookCopies.add(memberBookCopy);
								overDueBookList.add(new OverDueBook(
										memberBookCopy.getBook().getIsbn(),
										memberBookCopy.getBook().getTitle(),
										memberBookCopy.getCopyNum(), member
												.getMemberId(),
										memberCheckoutRecordEntry.getDueDate()
												.toString(), "Overdue"));
								// unAvailableBookCopies.remove(memberBookCopy);
							} else {
								overDueBookList.add(new OverDueBook(
										memberBookCopy.getBook().getIsbn(),
										memberBookCopy.getBook().getTitle(),
										memberBookCopy.getCopyNum(), member
												.getMemberId(),
										memberCheckoutRecordEntry.getDueDate()
												.toString(), "Unavailable"));
							}
						}
					}
				}
				printCheckoutRecordEntries(overDueBookList);
			}
		} else {
			lblMessage.setText("Please enter ISBN no");
		}
	}

	private boolean isBookCopyOverDue(LocalDate dueDate) {

		return dueDate.isBefore(LocalDate.now());
	}

	public void searchCheckoutRecordsByMemberId() {
		paneTableView.setVisible(true);
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

	//
	// private void printCheckoutRecordEntriesHardCoded() {
	// // OverDueBook overDueBook = new OverDueBook(book.getIsbn(),
	// // book.getTitle());
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
	//
	// /*
	// * try { FXMLLoader loader = new FXMLLoader();
	// * loader.setLocation(Main.class
	// * .getResource("../view/checkout/searchBookCheckoutRecordEntries.fxml"
	// * )); AnchorPane checkoutBook = (AnchorPane) loader.load();
	// *
	// * anchorPaneContent.getChildren().add(checkoutBook); } catch
	// * (IOException e) { // TODO Auto-generated catch block
	// * e.printStackTrace(); }
	// */
	//
	// }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}

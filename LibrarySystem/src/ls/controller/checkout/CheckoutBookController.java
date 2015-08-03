package ls.controller.checkout;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ls.common.ConfigurationConstants;
import ls.controller.utility.Utility;
import ls.dataaccess.DataAccessFacade;
import ls.model.Address;
import ls.model.Book;
import ls.model.BookCopy;
import ls.model.LibraryMember;

public class CheckoutBookController implements Initializable {
	@FXML
	private Button btnCheckout;
	@FXML
	private TextField txtMemberId;
	@FXML
	private TextField txtISBNNo;
	@FXML
	private Label lblMessage;
	FXMLLoader loader = new FXMLLoader();
	@FXML
	private AnchorPane rootLayout;

	private DataAccessFacade dataAccessFacade;
	private LibraryMember existingLibraryMember;
	private Book existingBook;

	public CheckoutBookController() {
		dataAccessFacade = Utility.getDataAcessFacadeInstance();
	}

	public void btnCheckoutClick() {
		if (!validateFields()) {
			return;
		}

		String memberId = txtMemberId.getText();
		String bookISBNNo = txtISBNNo.getText();

		HashMap<String, LibraryMember> membersHashMap = new HashMap<String, LibraryMember>();
		membersHashMap = dataAccessFacade.readMemberMap();

		if (membersHashMap != null) {
			Collection<LibraryMember> libraryMembersCollection = membersHashMap
					.values();

			existingLibraryMember = libraryMemberExists(
					libraryMembersCollection, memberId);
			if (existingLibraryMember == null) {
				lblMessage.setText("Member does not exist with id " + memberId);
				return;
			}
		}

		HashMap<String, Book> booksHashMap = new HashMap<String, Book>();
		booksHashMap = dataAccessFacade.readBooksMap();

		if (booksHashMap != null) {
			Collection<Book> booksCollection = booksHashMap.values();

			existingBook = bookExists(booksCollection, bookISBNNo);
			if (existingBook == null) {
				lblMessage.setText("Book does not exist with ISBN "
						+ bookISBNNo);
				return;
			}
		}

		BookCopy nextAvailableBookCopy = existingBook.getNextAvailableCopy();

		if (nextAvailableBookCopy == null) {
			lblMessage.setText("No available copies from book with ISBN "
					+ bookISBNNo);
			return;
		}

		LocalDate today = LocalDate.now();
		LocalDate dueDate = LocalDate.now().plusDays(
				existingBook.getMaxCheckoutLength());
		existingLibraryMember.checkout(nextAvailableBookCopy, today, dueDate);

		dataAccessFacade.saveNewMember(existingLibraryMember);
		dataAccessFacade.saveNewBook(existingBook);

		lblMessage
				.setText(ConfigurationConstants.LabelMessages.OPERATION_DONE_SUCCESSFULLY
						+ " Your due date is " + dueDate.toString() + ".");

	}

	private boolean validateFields() {
		if (txtMemberId.getText().equals("")) {
			lblMessage.setText("Member ID: Value is required.");
			return false;
		}
		if (txtISBNNo.getText().equals("")) {
			lblMessage.setText("Book ISBN: Value is required.");
			return false;
		}
		return true;
	}

	private LibraryMember libraryMemberExists(
			Collection<LibraryMember> libraryMembersCollection, String memberId) {
		for (LibraryMember libraryMember : libraryMembersCollection) {
			if (libraryMember.getMemberId().equals(memberId)) {
				return libraryMember;
			}
		}
		return null;
	}

	private Book bookExists(Collection<Book> booksCollection, String bookISBNNo) {
		for (Book book : booksCollection) {
			if (book.getIsbn().equals(bookISBNNo)) {
				return book;
			}
		}
		return null;
	}

	// initilize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

package ls.controller.book;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.JOptionPane;

import ls.controller.utility.Utility;
import ls.dataaccess.DataAccessFacade;
import ls.model.Book;

public class AddBookCopiesController implements Initializable {
	@FXML
	protected AnchorPane anchorPane;
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

	private DataAccessFacade dataAccessFacade;

	public AddBookCopiesController() {
		dataAccessFacade = Utility.getDataAcessFacadeInstance();
	}

	public void btnBookCopyadded() {
		if (validateFields()) {
			Book lookedUpBook = dataAccessFacade
					.searchBook(txtISBNNo.getText());
			if (lookedUpBook == null) {
				int answer = JOptionPane
						.showConfirmDialog(
								null,
								"Book ISBN not found. Would you like to add a new book?",
								"Book Unavailable", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					Utility.loadAddBookView(anchorPane);
				} else {
					return;
				}
			}

			int numOfCopies = Integer.parseInt(txtNumberOfCopies.getText());
			for (int i = 0; i < numOfCopies; i++) {
				lookedUpBook.addCopy();
			}

			dataAccessFacade.saveNewBook(lookedUpBook);

			lblMessage.setText("Book Copy(ies) added successfully.");
		}

	}

	private boolean validateFields() {
		if (txtISBNNo.getText().equals("")) {
			lblMessage.setText("Book ISBN: Value is required.");
			return false;
		}
		if (txtNumberOfCopies.getText().equals("")) {
			lblMessage.setText("Number of Copies: Value is required.");
			return false;
		}
		if (!txtNumberOfCopies.getText().matches("[0-9]*")) {
			lblMessage.setText("Number of Copies: Numeric value is required.");
			return false;
		}
		return true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
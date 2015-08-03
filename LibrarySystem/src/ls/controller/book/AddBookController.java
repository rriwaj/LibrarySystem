package ls.controller.book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ls.common.ConfigurationConstants;
import ls.controller.Main;
import ls.controller.utility.Utility;
import ls.dataaccess.DataAccessFacade;
import ls.model.Address;
import ls.model.Author;
import ls.model.Book;

public class AddBookController implements Initializable {
	@FXML
	private AnchorPane bookAnchorPane;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnSaveAuthor;
	private Stage primaryStage;
	@FXML
	private Label lblMessage;
	@FXML
	private TextField txtISBN;
	@FXML
	private TextField txtTitle;
	@FXML
	private TextField txtMaxCheckOutLength;
	@FXML
	private TextField txtNumberOfCopies;

	DataAccessFacade dataAccessFacade;
	private static List<Author> authorsList;

	// Author data
	@FXML
	private AnchorPane authorAnchorPane;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtState;
	@FXML
	private TextField txtCredentials;
	@FXML
	private TextArea txtBio;
	@FXML
	private TextField txtZip;
	@FXML
	private TextField txtPhone;
	@FXML
	private Label lblMessageAuthor;

	public AddBookController() {
		authorsList = new ArrayList<Author>();
		// call dataaccessfacade from utility class ref login
		dataAccessFacade = Utility.getDataAcessFacadeInstance();
	}

	@FXML
	private void btnAuthorClick() {
		try {
			if (lblMessageAuthor != null) {
				lblMessageAuthor.setText("");
			}
			primaryStage = (Stage) bookAnchorPane.getScene().getWindow();
			Stage newStage = new Stage();
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/book/author.fxml"));
			AnchorPane comp = (AnchorPane) loader.load();
			newStage.setTitle("Add Author Details");
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.initOwner(primaryStage);
			Scene stageScene = new Scene(comp, 460.0, 625.0);
			newStage.setScene(stageScene);
			newStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void applySaveBook() {
		if (validateBookData()) {
			String isbn = txtISBN.getText();
			String title = txtTitle.getText();
			int maxCheckoutLength = Integer.parseInt(txtMaxCheckOutLength
					.getText());
			int numberOfCopies = Integer.parseInt(txtNumberOfCopies.getText());
			Book newBook = new Book(isbn, title, maxCheckoutLength, authorsList);
			if (numberOfCopies > 0) {
				for (int i = 0; i < numberOfCopies; i++) {
					newBook.addCopy();
				}
			}

			dataAccessFacade.saveNewBook(newBook);

			lblMessage
					.setText(ConfigurationConstants.LabelMessages.OPERATION_DONE_SUCCESSFULLY);
		}
	}

	private boolean validateBookData() {
		if (txtISBN.getText().equals("")) {
			lblMessage.setText("ISBN: Value is required.");
			return false;
		}
		if (txtTitle.getText().equals("")) {
			lblMessage.setText("Title: Value is required.");
			return false;
		}
		if (txtMaxCheckOutLength.getText().equals("")) {
			lblMessage.setText("Max Checkout Length: Value is required.");
			return false;
		}
		if (txtNumberOfCopies.getText().equals("")) {
			lblMessage.setText("Number of Copies: Value is required.");
			return false;
		}
		if (authorsList.isEmpty()) {
			lblMessage.setText("Authors: At least one author is required.");
			return false;
		}
		if (!txtMaxCheckOutLength.getText().matches("[0-9]*")) {
			lblMessage
					.setText("Max Checkout Length: Numeric value is required.");
			return false;
		}
		if (!txtNumberOfCopies.getText().matches("[0-9]*")) {
			lblMessage.setText("Number of Copies: Numeric value is required.");
			return false;
		}
		return true;
	}

	@FXML
	private void applySaveAuthor() {
		if (validateAuthorData()) {
			String credentials = txtCredentials.getText();
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String street = txtStreet.getText();
			String city = txtCity.getText();
			String state = txtState.getText();
			String bio = txtBio.getText();
			String zip = txtZip.getText();
			String phone = txtPhone.getText();
			Address address = new Address(street, city, state, zip);
			Author author = new Author(credentials, firstName, lastName, phone,
					address, bio);
			authorsList.add(author);
			Stage stage = (Stage) authorAnchorPane.getScene().getWindow();
			System.out.println(stage);
			stage.close();
		}
	}

	private boolean validateAuthorData() {
		if (txtFirstName.getText().equals("")) {
			lblMessageAuthor.setText("First Name: Value is required.");
			return false;
		}
		if (txtLastName.getText().equals("")) {
			lblMessageAuthor.setText("Last Name: Value is required.");
			return false;
		}
		if (txtStreet.getText().equals("")) {
			lblMessageAuthor.setText("Street: Value is required.");
			return false;
		}
		if (txtCity.getText().equals("")) {
			lblMessageAuthor.setText("City: Value is required.");
			return false;
		}
		if (txtState.getText().equals("")) {
			lblMessageAuthor.setText("State: Value is required.");
			return false;
		}
		if (txtZip.getText().equals("")) {
			lblMessageAuthor.setText("Zip: Value is required.");
			return false;
		}
		if (txtPhone.getText().equals("")) {
			lblMessageAuthor.setText("Phone: Value is required.");
			return false;
		}
		if (txtBio.getText().equals("")) {
			lblMessageAuthor.setText("Bio: Value is required.");
			return false;
		}

		return true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}

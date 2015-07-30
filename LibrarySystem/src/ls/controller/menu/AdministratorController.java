package ls.controller.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import ls.controller.Main;

public class AdministratorController implements Initializable {
	@FXML
	protected BorderPane rootLayout;
	@FXML
	protected AnchorPane bodyLayout;
	private final String rootUrl = "../view/member/";

	@FXML
	protected void addEditMemberClick() {
		renderAddEditMemberView();
	}

	@FXML
	protected void addBookCopyClick() {
		renderAddBookCopyView();
	}
	@FXML
	protected void addBookClick() {
		renderAddBookView();
	}

	// initialize view before load
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// load CheckoutBook Form initially
		renderAddEditMemberView();
	}

	// Helper Methods
	protected void renderAddEditMemberView() {
		try {
			bodyLayout.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(rootUrl
					+ "addEditMember.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			bodyLayout.getChildren().add(checkoutBook);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void renderAddBookCopyView() {
		try {
			bodyLayout.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/book/addBookCopies.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			bodyLayout.getChildren().add(checkoutBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void renderAddBookView() {
		try {
			bodyLayout.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/book/addBook.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			bodyLayout.getChildren().add(checkoutBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package ls.controller.member;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ls.controller.Main;

public class MemberController implements Initializable {
	@FXML
	private AnchorPane anchorPaneContent;
	@FXML
	private Label lblMessage;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnAddNew;
	@FXML
	private Button btnSave;

	public void searchMembers() {
		System.out.println("searchMembers");
	}

	public void goToAddMember() {
		System.out.println("goToAddMember");

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/member/addEditMember.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();

			anchorPaneContent.getChildren().add(checkoutBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goToEditMember() {
		System.out.println("goToEditMember");
	}

	public void applySaveMember() {
		System.out.println("applySaveMember");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
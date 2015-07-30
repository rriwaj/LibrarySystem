package ls.controller.member;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ls.controller.Main;
public class SearchMemberController implements Initializable {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button btnSearch;
	@FXML
	private Button btnAddNew;	

	public void searchMembers() {
		System.out.println("searchMembers");
	}

	public void goToEditMember() {
		System.out.println("goToEditMember");
	}

	public void goToAddMember() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/member/addEditMember.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();

			anchorPane.getChildren().add(checkoutBook);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}

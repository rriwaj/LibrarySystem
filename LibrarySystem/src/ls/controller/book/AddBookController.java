package ls.controller.book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ls.controller.Main;

public class AddBookController implements Initializable {
	@FXML
	Button btnSave;
	@FXML
	Button btnSaveAuthor;
	Stage primaryStage;
	@FXML
	Label lblMessage;
	@FXML
	TextField txtISBN;

	@FXML
	private void btnAuthorClick() {
		try {
			Stage newStage = new Stage();
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/book/author.fxml"));
			AnchorPane comp = (AnchorPane) loader.load();
			newStage.setTitle("Add Author Details");
			Scene stageScene = new Scene(comp, 600, 430);
			newStage.setScene(stageScene);
			primaryStage = newStage;
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void btnSaveClick() {
		lblMessage.setText("Saved Successfully");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}

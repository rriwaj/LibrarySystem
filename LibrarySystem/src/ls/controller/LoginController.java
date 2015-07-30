package ls.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ls.controller.utility.Utility;

public class LoginController {
	@FXML
	AnchorPane rootLayout;
	@FXML
	Label lblMessage;
	@FXML
	TextField txtUserId;
	@FXML
	PasswordField txtPassword;

	@FXML
	private void btnLoginClick() {
		try {
			if (txtUserId.getText().length() > 0) {
				Stage st = (Stage) rootLayout.getScene().getWindow();
				// Load root layout from fxml file.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class
						.getResource("../view/menu/member.fxml"));
				BorderPane root;
				root = (BorderPane) loader.load();
				// Show the scene containing the root layout.
				Scene scene = new Scene(root);
				st.setScene(scene);
				st.show();
			} else {
				lblMessage.setText("Invalid Username and password");
				Utility.clearAllTextBoxFromAnchorPane(rootLayout);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

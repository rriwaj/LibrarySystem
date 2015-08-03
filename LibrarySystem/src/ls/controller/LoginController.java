package ls.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ls.controller.utility.Utility;
import ls.dataaccess.Auth;

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
			if (txtUserId.getText().length() > 0
					&& txtPassword.getText().length() > 0) {

				Auth auth = Utility.getDataAcessFacadeInstance().login(
						txtUserId.getText(), txtPassword.getText());
				String menuUrl;
				if (auth != null) {
					switch (auth) {
					case ADMIN:
						menuUrl = "../view/menu/administrator.fxml";
						break;
					case BOTH:
						menuUrl = "../view/menu/both.fxml";
						break;
					default:
						menuUrl = "../view/menu/member.fxml";
					}

					Stage st = (Stage) rootLayout.getScene().getWindow();
					// Load root layout from fxml file.
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource(menuUrl));
					BorderPane root;
					root = (BorderPane) loader.load();
					// Show the scene containing the root layout.
					Scene scene = new Scene(root, 1000.0, 575.0);
					st.setScene(scene);
					st.setX(150);
					st.setY(50);
					st.show();
				} else {
					lblMessage
							.setText("Login Failed. Invalid UserName/Password");
				}

			} else {
				lblMessage.setText("Please enter Username/Password");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

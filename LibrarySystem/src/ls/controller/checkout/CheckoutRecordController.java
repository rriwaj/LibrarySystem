package ls.controller.checkout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ls.controller.Main;

public class CheckoutRecordController implements Initializable {
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

	public void searchCheckoutRecordsByBookISBN() {
		System.out.println("searchCheckoutRecordsByBookISBN");
	}

	public void searchCheckoutRecordsByMemberId() {
		paneTableView.setVisible(true);
		System.out.println("searchCheckoutRecordsByMemberId");
	}

	public void printCheckoutRecordEntries() {

		// Scene scene = new Scene(rootLayout);
		// primaryStage.setScene(scene);
		// primaryStage.show();

		System.out.println("printCheckoutRecordEntries");

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/checkout/searchBookCheckoutRecordEntries.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();

			anchorPaneContent.getChildren().add(checkoutBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// anotehr very lazy way to change content
		// BorderPane rootLayout = (BorderPane) lblMessage.getScene().getRoot();
		// rootLayout.setCenter(checkoutBook);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}

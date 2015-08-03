package ls.controller.utility;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import ls.controller.Main;
import ls.dataaccess.DataAccessFacade;

public class Utility {

	private static DataAccessFacade dataAccess;

	public static DataAccessFacade getDataAcessFacadeInstance() {
		if (dataAccess == null) {
			dataAccess = new DataAccessFacade();
		}
		return dataAccess;
	}

	public static String[] getStates() {
		return new String[] { "IA", "FL", "CA", "ID", "TX" };
	}

	public static void clearAllTextBoxFromAnchorPane(AnchorPane anchorPane) {
		for (Node node : anchorPane.getChildren()) {
			if (node instanceof TextField) {
				((TextField) node).setText("");
			}
		}
	}

	public static void showAlertBox(String message) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("My Title");
		a.setHeaderText(message);
		a.showAndWait();
	}

	public static void loadSearchMemberView(AnchorPane anchorPane) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/member/searchMembers.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().clear();
			anchorPane.getChildren().add(checkoutBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Helper Methods
	public static void loadAddEditMemberView(AnchorPane anchorPane) {
		try {
			anchorPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/member/addEditMember.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().add(checkoutBook);
			anchorPane.setLayoutX(0);
			anchorPane.setLayoutY(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Helper Methods
	public static void loadSearchMembersView(AnchorPane anchorPane) {
		try {
			anchorPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/member/searchMembers.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().add(checkoutBook);
			anchorPane.setLayoutX(0);
			anchorPane.setLayoutY(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadAddBookCopyView(AnchorPane anchorPane) {
		try {
			anchorPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/book/addBookCopies.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().add(checkoutBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadAddBookView(AnchorPane anchorPane) {
		try {
			anchorPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("../view/book/addBook.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().add(checkoutBook);
			anchorPane.setPrefSize(450.0, 400.0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadCheckoutBookView(AnchorPane anchorPane) {
		try {
			anchorPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/checkout/"
					+ "checkoutBook.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().add(checkoutBook);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadSearchByISBNView(AnchorPane anchorPane) {
		try {
			anchorPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/checkout/"
					+ "searchBookCheckoutRecordEntries.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().add(checkoutBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadPrintMemberRecordView(AnchorPane anchorPane) {
		try {
			anchorPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/checkout/"
					+ "searchMemberCheckoutRecordEntries.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();
			anchorPane.getChildren().add(checkoutBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

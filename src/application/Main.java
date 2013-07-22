package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Account;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane ap = new AnchorPane();

		Parent root = FXMLLoader.load(getClass().getResource(
				"/res/default.fxml"));
		Parent root2 = FXMLLoader.load(getClass().getResource(
				"/res/account.fxml"));
		Scene frame = new Scene(root);
		primaryStage.getIcons().add(new Image("/res/icon.png"));
		primaryStage.isResizable();
		primaryStage.setTitle("Bank Business created by @malcjohn");

		primaryStage.setScene(frame);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
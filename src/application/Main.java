package application;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(
				"/res/default.fxml"));
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
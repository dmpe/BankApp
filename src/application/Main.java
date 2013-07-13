package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/res/UI.fxml"));
		Scene frame = new Scene(root);
		primaryStage.isResizable();
		primaryStage.setTitle("Bank Business");
		primaryStage.setScene(frame);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/res/UI.fxml"));
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
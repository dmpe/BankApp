package application;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(
				Main.class.getResource("/res/default.fxml"));
		AnchorPane rootLayout = (AnchorPane) loader.load();

		Scene frame = new Scene(rootLayout);
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
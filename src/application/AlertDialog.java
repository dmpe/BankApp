package application;

import java.net.*;
import java.util.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class AlertDialog {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button ButtonClose;

	@FXML
	private TextArea TextArea;

	@FXML
	void initialize() {
		assert ButtonClose != null : "fx:id=\"ButtonCancel\" was not injected: check your FXML file 'alertdialog.fxml'.";
		assert TextArea != null : "fx:id=\"TextArea\" was not injected: check your FXML file 'alertdialog.fxml'.";

		ButtonCloseMethod();
	}

	/*
	 * http://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx
	 */
	@FXML
	void ButtonCloseMethod() {
		ButtonClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) ButtonClose.getScene().getWindow();
				stage.close();
			}
		});
	}

}

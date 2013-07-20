package application;

import java.net.*;
import java.util.*;
import exceptions.*;
import logic.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class AccountController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField AccountNumberField;

	@FXML
	private TextField CashField;

	@FXML
	private TextField OverdraftField;

	@FXML
	private PasswordField PinField;

	@FXML
	void AccountNumberMethod() {
	}

	@FXML
	void CashFieldMethod() {
	}

	@FXML
	void OverdraftFieldMethod() {
	}

	@FXML
	void PinFiledMethod() {
	}

	@FXML
	void initialize() {
		assert AccountNumberField != null : "fx:id=\"AccountNumberField\" was not injected: check your FXML file 'Account.fxml'.";
		assert CashField != null : "fx:id=\"CashField\" was not injected: check your FXML file 'Account.fxml'.";
		assert OverdraftField != null : "fx:id=\"OverdraftField\" was not injected: check your FXML file 'Account.fxml'.";
		assert PinField != null : "fx:id=\"PinField\" was not injected: check your FXML file 'Account.fxml'.";

	}

}

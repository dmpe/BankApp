package application;

import java.net.*;
import java.util.*;

import exceptions.*;
import logic.*;
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
	private Button ButtonAccountNumber;

	@FXML
	private Button ButtonCash;

	@FXML
	private Button ButtonOverdraft;

	@FXML
	private Button ButtonPin;

	@FXML
	private TextField AccountNumberField;

	@FXML
	private TextField CashField;

	@FXML
	private TextField OverdraftField;

	@FXML
	private PasswordField PinField;

	@FXML
	private TextArea TextArea;

	/*
	 * Object Account for inserting all the stuff there
	 */
	Account ac = new Account();

	@FXML
	void initialize() {
		assert AccountNumberField != null : "fx:id=\"AccountNumberField\" was not injected: check your FXML file 'Account.fxml'.";
		assert ButtonAccountNumber != null : "fx:id=\"ButtonAccountNumber\" was not injected: check your FXML file 'Account.fxml'.";
		assert ButtonCash != null : "fx:id=\"ButtonCash\" was not injected: check your FXML file 'Account.fxml'.";
		assert ButtonOverdraft != null : "fx:id=\"ButtonOverdraft\" was not injected: check your FXML file 'Account.fxml'.";
		assert ButtonPin != null : "fx:id=\"ButtonPin\" was not injected: check your FXML file 'Account.fxml'.";
		assert CashField != null : "fx:id=\"CashField\" was not injected: check your FXML file 'Account.fxml'.";
		assert OverdraftField != null : "fx:id=\"OverdraftField\" was not injected: check your FXML file 'Account.fxml'.";
		assert PinField != null : "fx:id=\"PinField\" was not injected: check your FXML file 'Account.fxml'.";
		assert TextArea != null : "fx:id=\"TextArea\" was not injected: check your FXML file 'Account.fxml'.";

		AccountNumberMethod();
		ButtonAccountNumberMethod();
		ButtonCashMethod();
		CashFieldMethod();
		PinFieldMethod();
		ButtonPinMethod();
		OverdraftFieldMethod();
		ButtonOverdraftMethod();

	}

	// account number - button
	@FXML
	void AccountNumberMethod() {
		AccountNumberField.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							int AccountNumber = Integer
									.parseInt(AccountNumberField.getText());
							ac.setAccountNumber(AccountNumber);
							TextArea.appendText("Everything allright: "
									+ AccountNumberField.getText());
						}
					}
				});
	}

	// account number - textfield
	@FXML
	void ButtonAccountNumberMethod() {
		ButtonAccountNumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int AccountNumber = Integer.parseInt(AccountNumberField
						.getText());
				ac.setAccountNumber(AccountNumber);
				TextArea.appendText("\nEverything allright: "
						+ AccountNumberField.getText());
			}
		});
	}

	// cash money - button
	@FXML
	void ButtonCashMethod() {
		ButtonCash.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				double zahl = Double.parseDouble(CashField.getText());
				ac.setBankDeposit(zahl);
				TextArea.appendText("\nEverything allright: "
						+ CashField.getText());
			}
		});
	}

	// cash money - field
	@FXML
	void CashFieldMethod() {
		CashField.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							double overdraft = Double.parseDouble(CashField
									.getText());
							ac.setBankDeposit(overdraft);
							TextArea.appendText("\nEverything allright: "
									+ CashField.getText());
						}
					}
				});
	}

	// overdraft money - button
	@FXML
	void ButtonOverdraftMethod() {
		ButtonOverdraft.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				double overdraft = Double.parseDouble(OverdraftField.getText());
				ac.setOverdraft(overdraft);
				TextArea.appendText("\nEverything allright: "
						+ OverdraftField.getText());
			}
		});
	}

	// overdraft money - field
	@FXML
	void OverdraftFieldMethod() {
		OverdraftField.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							double overdraft = Double
									.parseDouble(OverdraftField.getText());
							ac.setOverdraft(overdraft);
							TextArea.appendText("\nEverything allright: "
									+ OverdraftField.getText());
						}
					}
				});
	}

	// pin number - button
	@FXML
	void ButtonPinMethod() {
		ButtonPin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int pin = Integer.parseInt(PinField.getText());
				try {
					ac.setPin(pin);
					TextArea.appendText("\nEverything allright: "
							+ PinField.getText());
				} catch (WrongQuantityOfDigits e) {
					TextArea.appendText("\nOnly 4 number are allowed");
				}
			}
		});
	}

	// pin number field
	@FXML
	void PinFieldMethod() {
		PinField.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							int pin = Integer.parseInt(PinField.getText());
							try {
								ac.setPin(pin);
								TextArea.appendText("\n Everything allright: "
										+ PinField.getText());
							} catch (WrongQuantityOfDigits e) {
								TextArea.appendText("\nOnly 4 number are allowed");
							}
						}
					}
				});
	}

}
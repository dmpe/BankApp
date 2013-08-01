package application;

import java.net.*;
import java.util.*;

import exceptions.*;
import logic.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
	private Button printButton;

	@FXML
	private Button saveAccount;

	@FXML
	private TextField CashField;

	@FXML
	private TextField OverdraftField;

	@FXML
	private TextField AccountNumberField;

	@FXML
	private PasswordField PinField;

	@FXML
	private TextArea TextArea;

	@FXML
	private HBox hbox;

	Stage dialogStage;

	/*
	 * Object Account for inserting all the stuff there
	 */
	Account ac = new Account();
	CashMachine<Account> cm = new CashMachine<Account>();

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
		assert printButton != null : "fx:id=\"printButton\" was not injected: check your FXML file 'account.fxml'.";
		assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'account.fxml'.";

	}

	// account money button
	@FXML
	void ButtonAccountNumberMethod(ActionEvent event) {
		int AccountNumber = Integer.parseInt(AccountNumberField.getText());
		ac.setAccountNumber(AccountNumber);
		TextArea.appendText("\nEverything allright: "
				+ AccountNumberField.getText());
		AccountNumberField.setDisable(true);

	}

	// cash money - button
	@FXML
	void ButtonCashMethod(ActionEvent event) {
		double zahl = Double.parseDouble(CashField.getText());
		ac.setBankDeposit(zahl);
		TextArea.appendText("\nEverything allright: " + CashField.getText());
		CashField.setDisable(true);

	}

	// overdraft money - button
	@FXML
	void ButtonOverdraftMethod(ActionEvent event) {
		double overdraft = Double.parseDouble(OverdraftField.getText());
		ac.setOverdraft(overdraft);
		TextArea.appendText("\nEverything allright: "
				+ OverdraftField.getText());
		OverdraftField.setDisable(true);

	}

	// pin number - button
	@FXML
	void ButtonPinMethod(ActionEvent event) {
		int pin = Integer.parseInt(PinField.getText());
		try {
			ac.setPin(pin);
			TextArea.appendText("\nEverything allright: " + PinField.getText());
			PinField.setDisable(true);
		} catch (WrongQuantityOfDigits e) {
			TextArea.appendText("\nOnly 4 number are allowed");
		}
	}

	@FXML
	void printAllAccounts(ActionEvent event) {
		Iterator<Account> s = cm.iterator();
		TextArea.appendText("\nUnsaved accounts will NOT be printed");
		TextArea.appendText(" ");
		while (s.hasNext()) {
			TextArea.appendText("\n" + s.next());
		}
	}

	@FXML
	void Save(ActionEvent event) {
		int number = ac.getAccountNumber();
		double deposit = ac.getBankDeposit();
		double overdraft = ac.getOverdraft();
		int pin = ac.getPin();

		Account saveNewOne = new Account(number, overdraft, deposit, pin);
		cm.addNewAccount(saveNewOne);
		TextArea.appendText("\nYou have saved new account");
		dialogStage.close();
	}

	public void setNewAccount(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
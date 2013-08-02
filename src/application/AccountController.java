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
	private Button saveButton;

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
	 CashMachine<Account> maschine = new CashMachine<Account>();
	 Controller st = new Controller();

	@FXML
	void initialize() {
		assert AccountNumberField != null : "fx:id=\"AccountNumberField\" was not injected: check your FXML file 'account.fxml'.";
		assert CashField != null : "fx:id=\"CashField\" was not injected: check your FXML file 'account.fxml'.";
		assert OverdraftField != null : "fx:id=\"OverdraftField\" was not injected: check your FXML file 'account.fxml'.";
		assert PinField != null : "fx:id=\"PinField\" was not injected: check your FXML file 'account.fxml'.";
		assert TextArea != null : "fx:id=\"TextArea\" was not injected: check your FXML file 'account.fxml'.";
		assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'account.fxml'.";
		assert printButton != null : "fx:id=\"printButton\" was not injected: check your FXML file 'account.fxml'.";
		assert saveAccount != null : "fx:id=\"saveAccount\" was not injected: check your FXML file 'account.fxml'.";
		assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'account.fxml'.";

	}

	public void setNewAccount(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	void saveEverything(ActionEvent event) {
		int AccountNumber = Integer.parseInt(AccountNumberField.getText());
		ac.setAccountNumber(AccountNumber);

		int pin = Integer.parseInt(PinField.getText());
		try {
			ac.setPin(pin);
		} catch (WrongQuantityOfDigits e) {
			TextArea.appendText("\nOnly 4 number are allowed");
		}

		double cash = Double.parseDouble(CashField.getText());
		ac.setBankDeposit(cash);

		double overdraft = Double.parseDouble(OverdraftField.getText());
		ac.setOverdraft(overdraft);

		TextArea.appendText("\nEverything allright: "
				+ AccountNumberField.getText());
		TextArea.appendText("\nEverything allright: " + CashField.getText());
		TextArea.appendText("\nEverything allright: "
				+ OverdraftField.getText());
		TextArea.appendText("\nEverything allright: " + PinField.getText());

	}

	@FXML
	void printAllAccounts(ActionEvent event) {
		Iterator<Account> s = maschine.iterator();
		TextArea.appendText("\nUnsaved accounts will NOT be printed");
		while (s.hasNext()) {
			TextArea.appendText("\n" + s.next());
		}
		System.out.println(" ");
		st.sets();
	}

	/**
	 * https://github.com/stevenschwenke/SimFX/blob/master/src/main/java/de/
	 * stevenschwenke/java/javafx/simFX/ui/javaFX/JavaFxApplication.java#L295
	 */
	@FXML
	void Save(ActionEvent event) {
		int number = ac.getAccountNumber();
		double deposit = ac.getBankDeposit();
		double overdraft = ac.getOverdraft();
		int pin = ac.getPin();
		// st.sets();

		Account saveNewOne = new Account(number, overdraft, deposit, pin);
		maschine.addNewAccount(saveNewOne);
		TextArea.appendText("\nYou have saved new account");

		// TimerTask closeIt = new TimerTask() {
		// @Override
		// public void run() {
		// Platform.runLater(new Runnable() {
		// @Override
		// public void run() {
		// dialogStage.close();
		// }
		// });
		// }
		// };
		// new Timer().schedule(closeIt, 1500);

	}
}
package application;

import java.io.*;
import java.net.*;
import java.util.*;

import exceptions.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import jfxtras.labs.dialogs.*;
import jfxtras.labs.dialogs.MonologFX.*;
import logic.*;

public class Controller {

	@FXML
	public ResourceBundle resources;

	@FXML
	public URL location;

	@FXML
	public MenuItem About;

	@FXML
	public MenuItem NewAccount;

	@FXML
	public MenuBar MenuBar;

	@FXML
	public Menu MenuFile;

	@FXML
	public Menu MenuHelp;

	@FXML
	public RadioButton choice;

	@FXML
	public RadioButton free;

	@FXML
	public ComboBox<Integer> chooseYourMoney;

	@FXML
	public TextArea infoText;

	@FXML
	public TextField moneyField;

	@FXML
	public TextField accountField;

	@FXML
	public TextField pinField;

	@FXML
	public Button removeCard;

	@FXML
	public Button acceptAccount;

	@FXML
	public Button accountStatement;

	@FXML
	public Button acceptPin;

	@FXML
	public Button withdraw;
	/*
	 * http://docs.oracle.com/javafx/2/api/javafx/scene/control/RadioButton.html
	 * "Only one RadioButton can be selected when placed in a ToggleGroup.... A
	 * RadioButton that is not in a ToggleGroup can be selected and unselected."
	 */
	ToggleGroup group = new ToggleGroup();

	/*
	 * I have borrowed this idea from James_D (Co-Director, Marshall University
	 * Genomics and Bioinformatics Core Facility) here
	 * https://forums.oracle.com/message/10746865
	 * 
	 * You can either use the List-item-object or ObservableList-item2-object
	 * 
	 * List<Integer> items = Arrays.asList(new Integer[] { 100, 200, 500, 700,
	 * 1000, 1200 });
	 */
	ObservableList<Integer> items2 = FXCollections.observableArrayList(100,
			200, 500, 700, 1000, 1200);

	/*
	 * Object from CashMachine & CashCard
	 */
	CashMachine<Account> maschine = new CashMachine<Account>();
	CashCard cashCard = new CashCard();
	Stage primaryStage;

	@FXML
	void initialize() throws Exception {
		assert About != null : "fx:id=\"About\" was not injected: check your FXML file 'default.fxml'.";
		assert MenuBar != null : "fx:id=\"MenuBar\" was not injected: check your FXML file 'default.fxml'.";
		assert MenuFile != null : "fx:id=\"MenuFile\" was not injected: check your FXML file 'default.fxml'.";
		assert MenuHelp != null : "fx:id=\"MenuHelp\" was not injected: check your FXML file 'default.fxml'.";
		assert NewAccount != null : "fx:id=\"NewAccount\" was not injected: check your FXML file 'default.fxml'.";
		assert acceptAccount != null : "fx:id=\"acceptAccount\" was not injected: check your FXML file 'default.fxml'.";
		assert acceptPin != null : "fx:id=\"acceptPin\" was not injected: check your FXML file 'default.fxml'.";
		assert accountField != null : "fx:id=\"accountField\" was not injected: check your FXML file 'default.fxml'.";
		assert accountStatement != null : "fx:id=\"accountStatement\" was not injected: check your FXML file 'default.fxml'.";
		assert choice != null : "fx:id=\"choice\" was not injected: check your FXML file 'default.fxml'.";
		assert chooseYourMoney != null : "fx:id=\"chooseYourMoney\" was not injected: check your FXML file 'default.fxml'.";
		assert free != null : "fx:id=\"free\" was not injected: check your FXML file 'default.fxml'.";
		assert infoText != null : "fx:id=\"infoText\" was not injected: check your FXML file 'default.fxml'.";
		assert moneyField != null : "fx:id=\"moneyField\" was not injected: check your FXML file 'default.fxml'.";
		assert pinField != null : "fx:id=\"pinField\" was not injected: check your FXML file 'default.fxml'.";
		assert removeCard != null : "fx:id=\"removeCard\" was not injected: check your FXML file 'default.fxml'.";
		assert withdraw != null : "fx:id=\"withdraw\" was not injected: check your FXML file 'default.fxml'.";

		chooseYourMoney.setItems(items2);

		chooseYourMoney.setDisable(true);
		moneyField.setDisable(true);
		accountStatement.setDisable(true);

		free.setToggleGroup(group);
		choice.setToggleGroup(group);

		infoText.appendText("\nPlease, insert you card in ATM");
	}

	@FXML
	void acceptAccount(ActionEvent event) {
		try {
			System.out.println(maschine.getAllAccount());

			int cardNumber = Integer.parseInt(accountField.getText());
			cashCard.setAccountNumber(cardNumber);
			maschine.insertCashCard(cashCard);
			infoText.appendText("\nYou have inserted a card in ATM - we dont know whether right or wrong");
		} catch (CardInsertedException e) {
			infoText.appendText(e.getMessage());
		} catch (InvalidCardException e) {
			infoText.appendText(e.getMessage());
		} catch (NumberFormatException e) {
			infoText.appendText("\nOnly numbers are permitted");
			accountStatement.setDisable(true);
			accountField.setEditable(true);
		}
		event.consume();
	}

	@FXML
	void accountStatement(ActionEvent event) {
		infoText.appendText("\n" + maschine.accountStatementMethod());
		moneyField.setDisable(false);
		chooseYourMoney.setDisable(false);
		event.consume();
	}

	@FXML
	void acceptPin(ActionEvent event) {
		try {
			int pinNummer = Integer.parseInt(pinField.getText());
			maschine.pinInsert(pinNummer);
			infoText.appendText("\nYou habe inserted a pin number in ATM");
			accountStatement.setDisable(false);
			infoText.appendText("\nPin seems to be correct");
		} catch (PinNotCorectException e) {
			infoText.appendText("\nPin wrong, correct it! ");
		} catch (CardNotInsertedException e) {
			infoText.appendText(e.getMessage());
		} catch (InvalidCardException e) {
			infoText.appendText("\nThis card number doesn't exist in our database");
		} catch (NumberFormatException e) {
			infoText.appendText("\nOnly numbers are permitted");
			accountStatement.setDisable(true);
			pinField.setEditable(true);
		}
		event.consume();
	}

	/**
	 * for the right docs see JavaFX8 docs:
	 * xxx/api/javafx/scene/control/TextInputControl.html#deleteText(int,%20int)
	 * and see this bug {@link} https://javafx-jira.kenai.com/browse/RT-31802
	 */
	@FXML
	void removeCard(ActionEvent event) {
		try {
			int zahl = accountField.getLength();
			int zahl2 = pinField.getLength();
			int zahl3 = infoText.getLength();
			int zahl4 = moneyField.getLength();
			accountField.deleteText(0, zahl);
			accountField.setEditable(true);
			accountStatement.setDisable(true);
			pinField.deleteText(0, zahl2);
			pinField.setEditable(true);
			infoText.deleteText(0, zahl3);
			maschine.ejectCashCard();
			moneyField.deleteText(0, zahl4);
			infoText.appendText("Insert your card number ");
		} catch (CardNotInsertedException e) {
			System.out.println(e.getMessage());
		}
		event.consume();
	}

	@FXML
	void withdraw(ActionEvent event) {
		try {
			if (choice.isSelected()) {
				maschine.withdraw(chooseYourMoney.getValue());
				infoText.appendText("\n" + "--New--");
				infoText.appendText("\n" + maschine.accountStatementMethod());
			} else if (free.isSelected()) {
				double zahl2 = Double.parseDouble(moneyField.getText());
				maschine.withdraw(zahl2);
				infoText.appendText("\n" + "--New--");
				infoText.appendText("\n" + maschine.accountStatementMethod());
			} else {
				infoText.appendText("\nYou must choose the ammount to withdraw");
			}
		} catch (PinNotCorectException e) {
			infoText.appendText(e.getMessage());
		} catch (NotEnoughMoneyException e) {
			infoText.appendText(e.getMessage());
		} catch (NumberFormatException e) {
			infoText.appendText("\nYou can't do this");
		}
		event.consume();
	} // here end of the small method

	@FXML
	void About(ActionEvent event) throws IOException {
		MonologFX mf = new MonologFX(Type.INFO);
		mf.setMessage("Created by @Malcjohn  - cincenko@outlook.com" + "\n"
				+ "https://github.com/Johnmalc/BankApp/");
		mf.setTitleText("About this app");
		mf.centerOnScreen();
		mf.showDialog();
	}

	/**
	 * taken from here
	 * http://edu.makery.ch/blog/2012/11/27/javafx-tutorial-addressapp-5/
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void NewAccount(ActionEvent event) throws IOException {

		// Load the fxml file and create a new stage for the popup
		FXMLLoader loader = new FXMLLoader(
				Main.class.getResource("/res/account.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.getIcons().add(new Image("/res/account.png"));
		dialogStage.setTitle("Edit Account");
		// dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		// Set the person into the controller
		AccountController controller = loader.getController();
		controller.setNewAccount(dialogStage);

		dialogStage.show();
	}

	public String sets() {
		return maschine.getAllAccount();
	}

}

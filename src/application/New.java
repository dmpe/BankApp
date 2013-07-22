package application;

import java.io.IOException;
import java.net.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import exceptions.CardInsertedException;
import exceptions.CardNotInsertedException;
import exceptions.InvalidCardException;
import exceptions.NotEnoughMoneyException;
import exceptions.PinNotCorectException;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.Account;
import logic.CashCard;
import logic.CashMachine;

public class New {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private MenuItem About;

	@FXML
	private MenuItem NewAccount;

	@FXML
	private MenuBar MenuBar;

	@FXML
	private Menu MenuFile;

	@FXML
	private Menu MenuHelp;

	@FXML
	private Button acceptAccount;

	@FXML
	private Button acceptPin;

	@FXML
	private Button accountStatement;

	@FXML
	private Button removeCard;

	@FXML
	private Button withdraw;

	@FXML
	private ComboBox<Integer> chooseYourMoney;

	@FXML
	private RadioButton free;

	@FXML
	private RadioButton choice;

	@FXML
	private TextArea infoText;

	@FXML
	private TextField moneyField;

	@FXML
	private TextField pinField;

	@FXML
	private TextField accountField;

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

	@FXML
	void initialize() {
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

		free.setToggleGroup(group);
		choice.setToggleGroup(group);

		acceptAccount();
		acceptPin();
		accountField();
		accountStatement();
		withdraw();
		removeCard();
		pinField();

	}

	@FXML
	void acceptAccount() {
		acceptAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					int karteNummer = Integer.parseInt(accountField.getText());
					cashCard.setAccountNumber(karteNummer);
					maschine.insertCashCard(cashCard);
					infoText.appendText("\nYou have inserted a card in ATM");
				} catch (CardInsertedException e) {
					System.out.println(e.getMessage());
				} catch (InvalidCardException e) {
					System.out.println(e.getMessage());
				}
				accountField.setEditable(false);
				event.consume();
			}
		});
	}

	@FXML
	void acceptPin() {
		acceptPin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					int pinNummer = Integer.parseInt(pinField.getText());
					maschine.pinInsert(pinNummer);
					infoText.appendText("\nYou habe inserted a pin number in ATM");
				} catch (PinNotCorectException e) {
					System.out.println(e.getMessage());
				} catch (CardNotInsertedException e) {
					System.out.println(e.getMessage());
				} catch (InvalidCardException e) {
					System.out.println(e.getMessage());
				}
				pinField.setEditable(false);
				infoText.appendText("\n take your card out, then you can change the number again");
				event.consume();
			}
		});
	}

	@FXML
	void accountField() {
		accountField.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							try {
								int karteNummer = Integer.parseInt(accountField
										.getText());
								cashCard.setAccountNumber(karteNummer);
								maschine.insertCashCard(cashCard);
								infoText.appendText("\nYou have inserted a card in ATM");
							} catch (CardInsertedException e) {
								System.out.println(e.getMessage());
							} catch (InvalidCardException e) {
								System.out.println(e.getMessage());
							}
							accountField.setEditable(false);
						}
						event.consume();
					}
				});
	}

	@FXML
	void accountStatement() {
		accountStatement.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				infoText.appendText("\n" + maschine.accountStatementMethod());
				moneyField.setDisable(false);
				chooseYourMoney.setDisable(false);
				event.consume();
			}
		});
	}

	@FXML
	void pinField() {
		pinField.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							try {
								int pinNummer = Integer.parseInt(pinField
										.getText());
								maschine.pinInsert(pinNummer);
								infoText.appendText("\nYou habe inserted a pin in ATM");
							} catch (PinNotCorectException e) {
								System.out.println(e.getMessage());
							} catch (CardNotInsertedException e) {
								System.out.println(e.getMessage());
							} catch (InvalidCardException e) {
								System.out.println(e.getMessage());
							}
							pinField.setEditable(false);
						}
						event.consume();
					}
				});
	}

	/**
	 * for the right docs see JavaFX8 docs:
	 * xxx/api/javafx/scene/control/TextInputControl.html#deleteText(int,%20int)
	 * and see this bug {@link} https://javafx-jira.kenai.com/browse/RT-31802
	 */
	@FXML
	void removeCard() {
		removeCard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					maschine.ejectCashCard();
					int zahl = accountField.getLength();
					int zahl2 = pinField.getLength();
					int zahl3 = infoText.getLength();
					accountField.deleteText(0, zahl);
					accountField.setEditable(true);
					pinField.deleteText(0, zahl2);
					pinField.setEditable(true);
					infoText.deleteText(0, zahl3);
				} catch (CardNotInsertedException e) {
					System.out.println(e.getMessage());
				}
				event.consume();
			}
		});
	}

	@FXML
	void withdraw() {
		withdraw.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (choice.isSelected()) {
						maschine.withdraw(chooseYourMoney.getValue());
						infoText.appendText("\n--New--");
						infoText.appendText("\n"
								+ maschine.accountStatementMethod());

					} else if (free.isSelected()) {
						double zahl2 = Double.parseDouble(moneyField.getText());
						maschine.withdraw(zahl2);
						infoText.appendText("\n--New--");
						infoText.appendText("\n"
								+ maschine.accountStatementMethod());
					} else {
						infoText.appendText("\nYou must choose the ammount to withdraw");
					}
				} catch (PinNotCorectException e) {
					System.out.println(e.getMessage());
				} catch (NotEnoughMoneyException e) {
					System.out.println(e.getMessage());
				}
				event.consume();
			} // here end of the small method
		});
	}// here the end of the method

	@FXML
	void About() throws IOException {
		Parent root3 = FXMLLoader.load(getClass().getResource("/res/alertdialog.fxml"));
		
		

			
		
	}

	@FXML
	void NewAccount() {
	}

}

package application;

import java.net.*;
import java.util.*;

import exceptions.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import logic.*;

/*
 * Controller class
 * Model class is your UI.fxml
 * This class was generated in SceneBuilder. Great feature
 */

public class Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Integer> ComboBox;

	@FXML
	private RadioButton ChoiceMoney;

	@FXML
	private RadioButton FreeMoney;

	@FXML
	private TextField AmmountMoneyToWid;

	@FXML
	public TextField InfoTetx;

	@FXML
	private TextField InsertAccNumber;

	@FXML
	private TextField InsertPinNumber;

	@FXML
	private Button CardAccept;

	@FXML
	private Button CardOut;

	@FXML
	private Button MoneyToWith;

	@FXML
	private Button PinAccept;

	@FXML
	private Button StateOfAccount;

	/*
	 * Idea borrowed from James_D (Co-Director, Marshall University Genomics and
	 * Bioinformatics Core Facility) here
	 * https://forums.oracle.com/message/10746865
	 */
	List<Integer> items = Arrays.asList(new Integer[] { 100, 200, 500, 700,
			1000, 1200 });

	/*
	 * Object from CashMachine and CashCard
	 */
	CashMachine<Account> cm = new CashMachine<Account>();

	@FXML
	void initialize() {
		assert AmmountMoneyToWid != null : "fx:id=\"AmmountMoneyToWid\" was not injected: check your FXML file 'UI.fxml'.";
		assert CardAccept != null : "fx:id=\"CardAccept\" was not injected: check your FXML file 'UI.fxml'.";
		assert CardOut != null : "fx:id=\"CardOut\" was not injected: check your FXML file 'UI.fxml'.";
		assert ChoiceMoney != null : "fx:id=\"ChoiceMoney\" was not injected: check your FXML file 'UI.fxml'.";
		assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'UI.fxml'.";
		assert FreeMoney != null : "fx:id=\"FreeMoney\" was not injected: check your FXML file 'UI.fxml'.";
		assert InfoTetx != null : "fx:id=\"InfoTetx\" was not injected: check your FXML file 'UI.fxml'.";
		assert InsertAccNumber != null : "fx:id=\"InsertAccNumber\" was not injected: check your FXML file 'UI.fxml'.";
		assert InsertPinNumber != null : "fx:id=\"InsertPinNumber\" was not injected: check your FXML file 'UI.fxml'.";
		assert MoneyToWith != null : "fx:id=\"MoneyToWith\" was not injected: check your FXML file 'UI.fxml'.";
		assert PinAccept != null : "fx:id=\"PinAccept\" was not injected: check your FXML file 'UI.fxml'.";
		assert StateOfAccount != null : "fx:id=\"StateOfAccount\" was not injected: check your FXML file 'UI.fxml'.";

		ComboBox.getItems().clear();
		ComboBox.getItems().addAll(items);

		CardAcceptMethod();
		PinAcceptMethod();
		StateOfAccountMethod();
		CardOutMethod();
		MoneyToWithMethod();
	}

	@FXML
	void CardAcceptMethod() {
		CardAccept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int karteNummer = Integer.parseInt(InsertAccNumber.getText());
				CashCard cd = new CashCard(karteNummer);
				try {
					cm.insertCashCard(cd);
					InfoTetx.setText("You have inserted a card in ATM");
				} catch (CardInsertedException e) {
					System.out.println(e.getMessage());
				} catch (InvalidCardException e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	@FXML
	void PinAcceptMethod() {
		PinAccept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int pinNummer = Integer.parseInt(InsertPinNumber.getText());
				try {
					cm.pinEingeben(pinNummer);
					InfoTetx.setText("You habe inserted a pin number in ATM");
				} catch (PinNotCorectException e) {
					System.out.println(e.getMessage());
				} catch (CardNotInsertedException e) {
					System.out.println(e.getMessage());
				} catch (InvalidCardException e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	@FXML
	void StateOfAccountMethod() {
		StateOfAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					cm.accountStatement();
				} catch (CardNotInsertedException e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	@FXML
	void CardOutMethod() {
		CardOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					cm.ejectCashCard();
				} catch (CardNotInsertedException e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	@FXML
	void MoneyToWithMethod() {
		MoneyToWith.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					cm.withdraw(0);
				} catch (PinNotCorectException e) {
					System.out.println(e.getMessage());
				} catch (NotEnoughMoneyException e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}
}

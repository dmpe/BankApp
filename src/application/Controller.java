package application;

import java.net.*;
import java.util.*;

import exceptions.*;
import javafx.collections.*;
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
	public TextArea InfoTetx;

	@FXML
	private TextField AmmountMoneyToWid;

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

	// @FXML
	// private ScrollBar Scroll;

	/*
	 * Idea borrowed from James_D (Co-Director, Marshall University Genomics and
	 * Bioinformatics Core Facility) here
	 * https://forums.oracle.com/message/10746865 
	 * 
	 * You can either use the List-item-object or 
	 * ObservableList-item2-object 
	 * 
	 * List<Integer> items =
	 * Arrays.asList(new Integer[] { 100, 200, 500, 700, 1000, 1200 });
	 */
	ObservableList<Integer> items2 = FXCollections.observableArrayList(100,
			200, 500, 700, 1000, 1200);
	/*
	 * Object from CashMachine
	 */
	CashMachine<Account> maschine = new CashMachine<Account>();

	@FXML
	void initialize() {
		assert AmmountMoneyToWid != null : "fx:id=\"AmmountMoneyToWid\" was not injected: check your FXML file 'UI.fxml'.";
		assert CardAccept != null : "fx:id=\"CardAccept\" was not injected: check your FXML file 'UI.fxml'.";
		assert CardOut != null : "fx:id=\"CardOut\" was not injected: check your FXML file 'UI.fxml'.";
		assert ChoiceMoney != null : "fx:id=\"ChoiceMoney\" was not injected: check your FXML file 'UI.fxml'.";
		assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'UI.fxml'.";
		assert FreeMoney != null : "fx:id=\"FreeMoney\" was not injected: check your FXML file 'UI.fxml'.";
		assert InfoTetx != null : "fx:id=\"InfoTetx\" was not injected: check your FXML file 'UI.fxml'.";
		// assert Scroll != null :
		// "fx:id=\"Scroll\" was not injected: check your FXML file 'UI.fxml'.";
		assert InsertAccNumber != null : "fx:id=\"InsertAccNumber\" was not injected: check your FXML file 'UI.fxml'.";
		assert InsertPinNumber != null : "fx:id=\"InsertPinNumber\" was not injected: check your FXML file 'UI.fxml'.";
		assert MoneyToWith != null : "fx:id=\"MoneyToWith\" was not injected: check your FXML file 'UI.fxml'.";
		assert PinAccept != null : "fx:id=\"PinAccept\" was not injected: check your FXML file 'UI.fxml'.";
		assert StateOfAccount != null : "fx:id=\"StateOfAccount\" was not injected: check your FXML file 'UI.fxml'.";

		ComboBox.getItems().clear();
		ComboBox.getItems().addAll(items2); // here can be changed to items

		ComboBox.setDisable(true);
		AmmountMoneyToWid.setDisable(true);

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
					maschine.insertCashCard(cd);
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

					AmmountMoneyToWid.setDisable(false);
					ComboBox.setDisable(false);

					maschine.pinInsert(pinNummer);
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
				InfoTetx.setText(maschine.accountStatementMethod());
			}
		});
	}

	@FXML
	void CardOutMethod() {
		CardOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					maschine.ejectCashCard();
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
					
					if (ChoiceMoney.isSelected()) {
						maschine.withdraw(ComboBox.getValue());
						InfoTetx.setText(maschine.accountStatementMethod());
					} else if (FreeMoney.isSelected()) {
						int zahl2 = Integer.parseInt(AmmountMoneyToWid.getText());
						maschine.withdraw(zahl2);
						InfoTetx.setText(maschine.accountStatementMethod());
					} else {
						InfoTetx.setText("mistake");
					}
				
				} catch (PinNotCorectException e) {
					System.out.println(e.getMessage());
				} catch (NotEnoughMoneyException e) {
					System.out.println(e.getMessage());
				}
			} // here end of the small method
		});
	}// here the end of the method
}

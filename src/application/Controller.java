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

		/*
		 * This is another possibility how to use comboboxes
		 * 
		 * ComboBox.getItems().clear(); ComboBox.getItems().addAll(items2); //
		 * here can be changed to items
		 */
		ComboBox.setItems(items2);

		ComboBox.setDisable(true);
		AmmountMoneyToWid.setDisable(true);

		FreeMoney.setToggleGroup(group);
		ChoiceMoney.setToggleGroup(group);

		CardAcceptMethod();
		PinAcceptMethod();
		CardKeyPressedMethod();
		PinKeyPressedMethod();
		StateOfAccountMethod();
		CardOutMethod();
		MoneyToWithMethod();

	}

	@FXML
	void CardAcceptMethod() {
		CardAccept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					int karteNummer = Integer.parseInt(InsertAccNumber
							.getText());
					cashCard.setAccountNumber(karteNummer);
					maschine.insertCashCard(cashCard);
					InfoTetx.setText("You have inserted a card in ATM");
				} catch (CardInsertedException e) {
					System.out.println(e.getMessage());
				} catch (InvalidCardException e) {
					System.out.println(e.getMessage());
				}
				InsertAccNumber.setEditable(false);
			}
		});
	}

	/**
	 * Idea borrowed from
	 * 
	 * @author shakir.gusaroff {@link} https://forums.oracle.com/thread/2401060
	 */
	@FXML
	void CardKeyPressedMethod() {
		InsertAccNumber.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							try {
								int karteNummer = Integer
										.parseInt(InsertAccNumber.getText());
								cashCard.setAccountNumber(karteNummer);
								maschine.insertCashCard(cashCard);
								InfoTetx.setText("You have inserted a card in ATM");
							} catch (CardInsertedException e) {
								System.out.println(e.getMessage());
							} catch (InvalidCardException e) {
								System.out.println(e.getMessage());
							}
							InsertAccNumber.setEditable(false);
						}
						event.consume();
					}
				});
	}

	@FXML
	void PinKeyPressedMethod() {
		InsertPinNumber.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.ENTER) {
							try {
								int pinNummer = Integer
										.parseInt(InsertPinNumber.getText());
								maschine.pinInsert(pinNummer);
								InfoTetx.setText("You habe inserted a pin number in ATM");
							} catch (PinNotCorectException e) {
								System.out.println(e.getMessage());
							} catch (CardNotInsertedException e) {
								System.out.println(e.getMessage());
							} catch (InvalidCardException e) {
								System.out.println(e.getMessage());
							}
							InsertPinNumber.setEditable(false);
						}
						event.consume();
					}
				});
	}

	@FXML
	void PinAcceptMethod() {
		PinAccept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					int pinNummer = Integer.parseInt(InsertPinNumber.getText());
					maschine.pinInsert(pinNummer);
					InfoTetx.setText("You habe inserted a pin number in ATM");
				} catch (PinNotCorectException e) {
					System.out.println(e.getMessage());
				} catch (CardNotInsertedException e) {
					System.out.println(e.getMessage());
				} catch (InvalidCardException e) {
					System.out.println(e.getMessage());
				}
				InsertPinNumber.setEditable(false);
				InfoTetx.setText("take your card out, then you can change the number again");
			}
		});
	}

	@FXML
	void StateOfAccountMethod() {
		StateOfAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				InfoTetx.setText(maschine.accountStatementMethod());
				AmmountMoneyToWid.setDisable(false);
				ComboBox.setDisable(false);
			}
		});
	}

	/**
	 * for the right docs see JavaFX8 docs:
	 * xxx/api/javafx/scene/control/TextInputControl.html#deleteText(int,%20int)
	 * and see this bug {@link} https://javafx-jira.kenai.com/browse/RT-31802
	 */
	@FXML
	void CardOutMethod() {
		CardOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					maschine.ejectCashCard();
					int zahl = InsertAccNumber.getLength();
					int zahl2 = InsertPinNumber.getLength();
					int zahl3 = InfoTetx.getLength();
					InsertAccNumber.deleteText(0, zahl);
					InsertAccNumber.setEditable(true);
					InsertPinNumber.deleteText(0, zahl2);
					InsertPinNumber.setEditable(true);
					InfoTetx.deleteText(0, zahl3);
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
						double zahl2 = Double.parseDouble(AmmountMoneyToWid
								.getText());
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

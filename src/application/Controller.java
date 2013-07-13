package application;

import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField AmmountMoneyToWid;

	@FXML
	private Button CardAccept;

	@FXML
	private Button CardOut;

	@FXML
	private RadioButton ChoiceMoney;

	@FXML
	private ComboBox<Integer> ComboBox;

	@FXML
	private RadioButton FreeMoney;

	@FXML
	private TextField InfoTetx;

	@FXML
	private TextField InsertAccNumber;

	@FXML
	private TextField InsertPinNumber;

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
	List<Integer> items = Arrays.asList(new Integer[] { 10, 50, 90, 70 });

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
	}

}

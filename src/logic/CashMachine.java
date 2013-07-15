package logic;

import java.util.*;
import exceptions.*;

public class CashMachine<K> {

	public enum State {
		READY, CARD_INSERTED, PIN_CORRECT, PIN_WRONG
	}

	List<Account> accounts;
	private CashCard cashCard;
	private State state;
	private int index; // Number of account in the list

	public CashMachine() {
		index = 0;
		state = State.READY;

		// new predefined accounts
		accounts = new LinkedList<Account>();
		accounts.add(new Account(23456789, -100.0, 200, 1234));
		accounts.add(new Account(34567890, -200.0, 300, 1234));
		accounts.add(new Account(12345678, 0.0, 5000, 1234));
	}

	/**
	 * 
	 * Nur im Zustand READY konnen die Informationen der eingegebenen Cashcard
	 * im Attribute cashCard abgespeichert werden. Bei erfolgreicher Eingabe
	 * wechselt der Zustand von READY auf CARD_INSERTED. Der Status des
	 * Automaten soll auf der Konsole protokolliert werden.
	 * 
	 * @param cashCardX
	 * @throws InvalidCardException
	 * @throws CardInsertedException
	 */
	public void insertCashCard(CashCard cashCardX)
			throws CardInsertedException, InvalidCardException {
		switch (state) {
		case READY:
			cashCard = cashCardX;
			/*
			 * Sucht die passende Konto nach AccountNummer muss man andern wegen
			 * Iterable > alte for loop
			 */
			for (int i = 0; i < accounts.size(); i++) {
				if ((accounts.get(i).getAccountNumber()) == (cashCard
						.getAccountNumber())) {
					/*
					 * wenn account nummer und carten-account nummer entspricht
					 * > speichere index, damit man weiter mit dem richtigen
					 * (passenden) Account arbeiten kann
					 */
					this.index = i;
					state = State.CARD_INSERTED;
					break;
				} else {
					index++;
					if (index >= accounts.size()) {
						state = State.READY;
						throw new InvalidCardException();
					}
				}
			}
			break;
		default:
			throw new CardInsertedException();
		} // switch ends here
	}

	/**
	 * Die Methode gleicht die Eingabe des Pins mit dem Pin des gerade
	 * verwendeten Accounts ab. Bei korrekter Eingabe wechselt der Zustand des
	 * Geldautomaten in PIN_CORRECT. Bei Falscheingabe in PIN_WRONG. Der Status
	 * des Automaten soll auf der Konsole protokolliert werden Im Zustand
	 * PIN_WRONG, ist das Ausfuhren von weiteren Methoden nicht moglich.
	 * Beachten Sie, dass Geld nur noch abgehoben werden kann, wenn der Pin
	 * korrekt ist.
	 * 
	 * @param pinX
	 * @throws PinNotCorectException
	 * @throws CardNotInsertedException
	 * @throws InvalidCardException
	 */
	public void pinEingeben(int pinX) throws PinNotCorectException,
			CardNotInsertedException, InvalidCardException {
		switch (state) {
		case CARD_INSERTED:
			if (accounts.get(index).getPin() == pinX) {
				state = State.PIN_CORRECT;
				System.out.println("Sie haben den richtigen Pin eingegeben.");
				System.out.println("Automat ist auf Status " + state
						+ " gesetzt.");
			} else {
				throw new PinNotCorectException();
			} // end of if-else
			break;
		default:
			throw new CardNotInsertedException();
		} // end switch
	}

	/**
	 * 
	 * Das Abheben kann nur erfolgen wenn der Geldautomat im Zustand
	 * CARD_INSERTED ist. Der angegebene Betrag kann vom Konto abgehoben werden,
	 * solange es den Dispokredit nicht uberschreitet. Ausserdem muss das
	 * Kontoguthaben neu berechnet und auf der Konsole ausgegeben werden.
	 * 
	 * @param amount
	 * @throws PinNotCorectException
	 * @throws NotEnoughMoneyException
	 */
	public void withdraw(double amount) throws PinNotCorectException,
			NotEnoughMoneyException {
		switch (state) {
		case PIN_CORRECT:
			System.out.println("Ihr Kontoguthaben ist: "
					+ accounts.get(index).getBankDeposit() + " Euro.");
			if (accounts.get(index).getBankDeposit() - amount >= accounts.get(
					index).getOverdraft()) {
				accounts.get(index).setBankDeposit(
						accounts.get(index).getBankDeposit() - amount);
				System.out.println("Sie haben erfolgreich " + amount
						+ " Euro abgehoben.");
				System.out.println("Ihr Kontoguthaben ist: "
						+ accounts.get(index).getBankDeposit() + " Euro.");
			} else {
				throw new NotEnoughMoneyException();
			}
			break;
		default:
			throw new PinNotCorectException();
		} // switch State Ende
	}

	/**
	 * Ausgabe der aktuellen Kontoinformationen auf der Konsole, nur moglich im
	 * Zustand CARD_INSERTED. Die Methode accountStatement() kann auch im
	 * Zustand PIN_CORRECT ausgefuhrt werden.
	 * 
	 * @throws CardNotInsertedException
	 */
	public void accountStatement() throws CardNotInsertedException {
		// tests if states are correctly set
		if (state == State.CARD_INSERTED || state == State.PIN_CORRECT) {
			System.out.println("\n" + "Account Statement: " + "\n"
					+ "Account Nr.: " + accounts.get(index).getAccountNumber()
					+ "\n" + "Bank Deposit: "
					+ accounts.get(index).getBankDeposit() + "\n"
					+ "Overdraft: " + accounts.get(index).getOverdraft());
		} else {
			throw new CardNotInsertedException();
		}
	}

	/**
	 * 
	 * Der Geldautomat wird auf den Status READY gesetzt, das Attribute cashCard
	 * wird zur Nullreferenz. Das ist nur moglich im Zustand CARD_INSERTED. Der
	 * Status des Automaten soll auf der Konsole protokolliert werden. Die
	 * Methode ejectCashCard() kann auch im Zustand PIN_CORRECT ausgefuhrt
	 * werden.
	 * 
	 * @throws CardNotInsertedException
	 * 
	 */
	public void ejectCashCard() throws CardNotInsertedException {
		if (state == State.CARD_INSERTED || state == State.PIN_CORRECT) {
			cashCard = null;
			index = 0;
			state = State.READY;
			System.out.println("Ihr Karte ist entfernt!");
			System.out.println("Automat ist auf Status " + state + " gesetzt.");
		} else {
			throw new CardNotInsertedException();
		}
	}
}

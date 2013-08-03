package logic;

import java.util.*;
import exceptions.*;

public class CashMachine<K> implements Iterable<Account> {

	public enum State {
		READY, CARD_INSERTED, PIN_CORRECT, PIN_WRONG,
	}

	static LinkedList<Account> accounts;
	Account ac;
	CashCard cashCard;
	State state;

	Account a1 = new Account(23456789, -100.0, 2000, 1234);
	Account a2 = new Account(34567890, -200.0, 3000, 1234);
	Account a3 = new Account(12345678, 0.0, 50000, 1234);

	public CashMachine() {
		state = State.READY;
		// doesnt work yet
		// accounts.add(a1);
		// accounts.add(a2);
		// accounts.add(a3);
	}

	/**
	 * 
	 * Nur im Zustand READY konnen die Informationen der eingegebenen Cashcard
	 * im Attribute cashCard abgespeichert werden. Bei erfolgreicher Eingabe
	 * wechselt der Zustand von READY auf CARD_INSERTED. Der Status des
	 * Automaten soll auf der Konsole protokolliert werden.
	 * 
	 * @param cashCardX
	 * @throws CardInsertedException
	 * @throws InvalidCardException
	 */
	public void insertCashCard(CashCard cashCardX)
			throws CardInsertedException, InvalidCardException {
		switch (state) {
		case READY:
			this.cashCard = cashCardX;
			state = State.CARD_INSERTED;

			for (Account acc : accounts) {
				if (acc.getAccountNumber() == cashCard.getAccountNumber()) {
					this.ac = acc;
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
	public void pinInsert(int pinX) throws PinNotCorectException,
			CardNotInsertedException, InvalidCardException {
		switch (state) {
		case CARD_INSERTED:
			if ((ac.getAccountNumber()) == (cashCard.getAccountNumber())) {
				if (ac.getPin() == pinX) {
					state = State.PIN_CORRECT;
					System.out.println("Your pin is correct");
					System.out.println("ATM was set on status : " + state);
				} else {
					throw new PinNotCorectException();
				}
			} else {
				throw new InvalidCardException();
			}
			break;
		default:
			throw new CardNotInsertedException();
		}
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
			System.out.println("Your deposit before any action was: "
					+ ac.getBankDeposit() + " Euro.");
			if (ac.getBankDeposit() - amount >= ac.getOverdraft()) {
				ac.setBankDeposit(ac.getBankDeposit() - amount);
				System.out.println("You have withdrew " + amount + " Euro.");
				System.out.println("Your new deposit is: "
						+ ac.getBankDeposit() + " Euro.");
			} else {
				throw new NotEnoughMoneyException();
			}
			break;
		default:
			throw new PinNotCorectException();
		}
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
					+ "Account Nr.: " + ac.getAccountNumber() + "\n"
					+ "Bank Deposit: " + ac.getBankDeposit() + "\n"
					+ "Overdraft: " + ac.getOverdraft());
		} else {
			throw new CardNotInsertedException();
		}
	}

	/**
	 * 
	 * @return all the details from the account which is being used
	 */
	public String accountStatementMethod() {
		return "\n" + "Account Statement: " + "\n" + "Account Nr.: "
				+ ac.getAccountNumber() + "\n" + "Bank Deposit: "
				+ ac.getBankDeposit() + "\n" + "Overdraft: "
				+ ac.getOverdraft();
	}

	/**
	 * 
	 * Der Geldautomat wird auf den Status READY gesetzt, das Attribute cashCard
	 * wird zur Nullreferenz. Das ist nur moglich im Zustand CARD_INSERTED. Der
	 * Status des Automaten soll auf der Konsole protokolliert werden. Die
	 * Methode ejectCashCard() kann auch im Zustand PIN_CORRECT ausgefuhrt
	 * werden.
	 * 
	 * Reset of app
	 * 
	 * @throws CardNotInsertedException
	 * 
	 */
	public void ejectCashCard() throws CardNotInsertedException {
		if (state == State.CARD_INSERTED || state == State.PIN_CORRECT) {
			cashCard = null;
			ac = null;
			state = State.READY;
			System.out.println("\nThe card & account is out of the ATM");
			System.out.println("\nATM is set on " + state);
		} else {
			throw new CardNotInsertedException();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Account> iterator() {
		return accounts.iterator();
	}

	/**
	 * http://stackoverflow.com/questions/3717028/access-list-from-another-class
	 * Gets a new account and saves it in the list Method used by the
	 * AccountController class
	 */
	public void addNewAccount(Account no) {
		accounts.add(no);
		System.out.println("account size : " + accounts.size());
		System.out.println("\n");
		System.out.println(getAllAccount());
	}

	/**
	 * 
	 * @return all accounts (String)
	 */
	public String getAllAccount() {
		return "\n" + accounts.toString();
	}

	/**
	 * Static method for getting account from LL
	 * 
	 * @return all accounts (LinkedList<Account>)
	 */
	public static LinkedList<Account> getone() {
		accounts = new LinkedList<Account>();
		return accounts;
	}

	/**
	 * 
	 * @return size
	 */
	public int size() {
		return accounts.size();
	}

	// public void dej() {
	// accounts.add(a1);
	// accounts.add(a2);
	// accounts.add(a3);
	// }
}

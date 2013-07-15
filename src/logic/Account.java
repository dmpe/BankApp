package logic;

import exceptions.*;

public class Account {
	private int accountNumber;
	private double overdraft;
	private double bankDeposit;
	private int pin;

	/**
	 * This constructor allows a fast creation of accounts
	 * 
	 * @param accountNumber
	 * @param overdraft
	 * @param bankDeposit
	 * @param pin
	 */

	public Account(int accountNumber, double overdraft, double bankDeposit,
			int pin) {
		this.accountNumber = accountNumber;
		this.overdraft = overdraft;
		this.bankDeposit = bankDeposit;
		try {
			setPin(pin);
		} catch (WrongQuantityOfDigits e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Saves the account number
	 * 
	 * @param value
	 *            accountNumber
	 */
	public void setAccountNumber(int value) {
		this.accountNumber = value;
	}

	/**
	 * 
	 * @return accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Credit you could withdraw. You set a limit here
	 * 
	 * @param value2
	 *            overdraft
	 */
	public void setOverdraft(double value2) {
		this.overdraft = value2;
	}

	/**
	 * 
	 * @return overdraft
	 */
	public double getOverdraft() {
		return overdraft;
	}

	/**
	 * Your initial balance in the account
	 * 
	 * @param value3
	 *            bankDeposit
	 */
	public void setBankDeposit(double value3) {
		this.bankDeposit = value3;
	}

	/**
	 * @return bankDeposit
	 */
	public double getBankDeposit() {
		return bankDeposit;
	}

	/**
	 * Taken from:
	 * http://stackoverflow.com/questions/1306727/way-to-get-number-of
	 * -digits-in-an-int After teachers recommendation I have added an (another)
	 * Exception. He will like it !!!
	 * 
	 * @param value4
	 *            PIN
	 * @throws WrongQuantityOfDigits
	 */
	public void setPin(int value4) throws WrongQuantityOfDigits {
		// if the lenght of value4 is 4
		int length = String.valueOf(value4).length();
		if (length == 4) {
			this.pin = value4;
		} else {
			throw new WrongQuantityOfDigits();
		}
	}

	/**
	 * @return pin
	 */
	public int getPin() {
		return pin;
	}

	@Override
	public String toString() {
		return "Account : accountNumber=" + accountNumber + ", overdraft="
				+ overdraft + ", bankDeposit=" + bankDeposit + ", pin=" + pin;
	}

}

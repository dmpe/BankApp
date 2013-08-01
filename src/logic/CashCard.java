package logic;

public class CashCard {
	protected int accountNumber;
	
	// Constructor
	public CashCard() {
		
	}

	// Constructor
	public CashCard(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	// Methods for account (set and get)
	public void setAccountNumber(int value) {
		this.accountNumber = value;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
}

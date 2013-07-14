package exceptions;

public class InvalidCardException extends Exception {
	
	public InvalidCardException() {
		System.out.println("");
		System.out.println("Diese Karte ist keinem Konto zugeordnet. ");
	}
}

package exceptions;

public class CardNotInsertedException extends Exception {

	public CardNotInsertedException() {
		System.out.println("");
		System.out.println("Es gibt keine Karte im Automat.");
	}

}

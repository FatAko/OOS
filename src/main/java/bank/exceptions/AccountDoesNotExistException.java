package bank.exceptions;

/**
 * Exception, die Ausgelöst werden soll, wenn ein Account nicht existiert
 */
public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException(String ausgabe) {
        super(ausgabe);
    }
}

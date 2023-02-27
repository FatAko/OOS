package bank.exceptions;

/**
 * Exception, die Ausgelöst werden soll, wenn ein Account bereits existiert
 */
public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException(String ausgabe) {
        super(ausgabe);
    }
}

package bank.exceptions;

/**
 * Exception, die Ausgelöst werden soll, wenn eine Transaktion nicht existiert
 */
public class TransactionDoesNotExistException extends Exception {
    public TransactionDoesNotExistException(String ausgabe) {
        super(ausgabe);
    }
}

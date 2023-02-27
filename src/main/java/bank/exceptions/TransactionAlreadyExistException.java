package bank.exceptions;

/**
 * Exception, die Ausgelöst werden soll, wenn eine Transaktion bereits existiert
 */
public class TransactionAlreadyExistException extends Exception {
    public TransactionAlreadyExistException(String ausgabe) {
        super(ausgabe);
    }
}
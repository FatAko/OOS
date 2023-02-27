package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.util.*;

/**
 * Eine Bank-Klasse, die Konten und Transaktionen verwalten
 * und verarbeiten kann.
 */
public class PrivateBankAlt implements Bank {
    /**
     * Konstrukor
     * @param pName Name der Bank
     * @param pIncomingInterest Zinsen bei Einzahlung
     * @param pOutgoingInterest Zinsen bei Auszahlung
     */
    public PrivateBankAlt(String pName, double pIncomingInterest, double pOutgoingInterest) {
        this.name = pName;
        this.incomingInterest = pIncomingInterest;
        this.outgoingInterest = pOutgoingInterest;
    }
    /**
     * Copy Konstruktor
     * @param pOrig das zu Kopierende Objekt
     */
    public PrivateBankAlt(PrivateBankAlt pOrig) {
        this.name = pOrig.name;
        this.outgoingInterest = pOrig.outgoingInterest;
        this.incomingInterest = pOrig.incomingInterest;
        this.accountsToTransactions = pOrig.accountsToTransactions;
    }

    /**
     * Dieses Attribut soll den Namen der privaten Bank repraesentieren.
     */
    protected String name;
    /**
     * Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in
     * Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen. Dieses Attribut soll identisch zu dem
     * gleichnamigen Attribut der Klasse Payment sein.
     */
    protected double incomingInterest;
    /**
     * Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in
     * Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen. Dieses Attribut soll identisch zu
     * dem gleichnamigen Attribut der Klasse Payment sein.
     */
    protected double outgoingInterest;
    /**
     * Dieses Attribut soll Konten mit
     * Transaktionen verknüpfen, sodass jedem gespeicherten Konto 0 bis n Transaktionen zugeordnet
     * werden können. Der Schlüssel steht für den Namen des Kontos.
     */
    protected Map<String, List<Transaction>> accountsToTransactions = new HashMap<String, List<Transaction>>();

    /**
     * Setzt den Namen der privaten Bank.
     *
     * @param pName Name der privaten Bank
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Liefert den Namen der privaten Bank.
     *
     * @return Name der privaten Bank
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setzt die Zinsen bzw. den Zinssatz die bei einer Einzahlung anfallen fest.
     *
     * @param incomingInterest die Zinsen bzw. der Zinssatz
     */
    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    /**
     * Liefert die Zinsen bzw. den Zinssatz die bei einer Einzahlung anfallen.
     *
     * @return die Zinsen bzw. den Zinssatz
     */
    public double getIncomingInterest() {
        return incomingInterest;
    }

    /**
     * Setzt die Zinsen bzw. den Zinssatz die bei einer Auszahlung anfallen fest.
     *
     * @param outgoingInterest die Zinsen bzw. der Zinssatz
     */
    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }

    /**
     * Liefert die Zinsen bzw. den Zinssatz die bei einer Auszahlung anfallen.
     *
     * @return die Zinsen bzw. den Zinssatz
     */
    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    /**
     * Weist einer Bank Transaktionen zu.
     *
     * @param accountsToTransactions Banken und die zugehörigen Transaktionen
     */
    public void setAccountsToTransactions(Map<String, List<Transaction>> accountsToTransactions) {
        this.accountsToTransactions = accountsToTransactions;
    }

    /**
     * Liefert Transaktionen der jeweiligen Banken.
     */
    public Map<String, List<Transaction>> getAccountsToTransactions() {
        return accountsToTransactions;
    }

    /**
     * Liefert die Klassen attribute als String
     *
     * @return Klassenattribute als Zeichenkette
     */
    public String toString() {
        return ("Name: " + this.name + "\nIncomingInterest: " + this.incomingInterest + "\nOutgoingInterest: " + this.outgoingInterest + "\nAccountsToTransaction: \n" + accountsToTransactions.toString());
    }

    /**
     * Vergleicht, ob zwei Objekte des selben Typs die gleichen Attributwerte Aufweisen
     *
     * @param obj Das zu Vergleichende Objekt
     * @return true falls Objekte gleich sind, andernfalls false
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof PrivateBank)) {
            return false;
        } else {
            return (this.name.equals(((PrivateBank) obj).name) && this.outgoingInterest == ((PrivateBank) obj).outgoingInterest && this.incomingInterest == ((PrivateBank) obj).incomingInterest && this.accountsToTransactions.equals(((PrivateBank) obj).accountsToTransactions));
        }
    }

    /**
     * Adds an account to the bank. If the account already exists, an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    public void createAccount(String account) throws AccountAlreadyExistsException {
        if (accountsToTransactions.containsKey(account)) {
            throw new AccountAlreadyExistsException("Diesen Account gibt es schon!");
        } else {
            accountsToTransactions.put(account, new LinkedList<Transaction>());
        }
    }

    /**
     * Adds an account (with all specified transactions) to the bank. If the account already exists,
     * an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException {
        if (accountsToTransactions.containsKey(account)) {
            throw new AccountAlreadyExistsException("Diesen Account gibt es schon!");
        } else {
            accountsToTransactions.put(account, transactions);
        }
    }

    /**
     * Adds a transaction to an account. If the specified account does not exist, an exception is
     * thrown. If the transaction already exists, an exception is thrown.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which is added to the account
     * @throws TransactionAlreadyExistException if the transaction already exists
     */
    public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException {
        if (!accountsToTransactions.containsKey(account)) {
            throw new AccountDoesNotExistException("Diesen Account gibt es nicht!");
        } else {
            if (accountsToTransactions.get(account).contains(transaction)) {
                throw new TransactionAlreadyExistException("Diese Transaktion existiert bereits!");
            } else {
                if (transaction instanceof Payment) {
                    ((Payment) transaction).setIncomingInterest(incomingInterest);
                    ((Payment) transaction).setOutgoingInterest(outgoingInterest);
                    accountsToTransactions.get(account).add(transaction);
                } else if (transaction instanceof Transfer) {
                    accountsToTransactions.get(account).add(transaction);
                }
            }
        }
    }

    /**
     * Removes a transaction from an account. If the transaction does not exist, an exception is
     * thrown.
     *
     * @param account     the account from which the transaction is removed
     * @param transaction the transaction which is added to the account
     * @throws TransactionDoesNotExistException if the transaction cannot be found
     */
    public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException {
        if (!accountsToTransactions.get(account).contains(transaction)) {
            throw new TransactionDoesNotExistException("Diese Transaktion existiert nicht!");
        } else {
            accountsToTransactions.get(account).remove(transaction);
        }
    }

    /**
     * Checks whether the specified transaction for a given account exists.
     *
     * @param account     the account from which the transaction is checked
     * @param transaction the transaction which is added to the account
     */
    public boolean containsTransaction(String account, Transaction transaction) {
        return (accountsToTransactions.get(account).contains(transaction));
    }

    /**
     * Calculates and returns the current account balance.
     *
     * @param account the selected account
     * @return the current account balance
     */
    public double getAccountBalance(String account) {
        double balance = 0.0;
        for (int i = 0; i < accountsToTransactions.get(account).size(); i++) {
            if (accountsToTransactions.get(account).get(i) instanceof Transfer) {
                if (((Transfer) accountsToTransactions.get(account).get(i)).getSender() == account) {
                    balance += accountsToTransactions.get(account).get(i).getAmount() * -1;
                } else if (((Transfer) accountsToTransactions.get(account).get(i)).getRecipient() == account) {
                    balance += (accountsToTransactions.get(account).get(i).getAmount());
                }
            } else if (accountsToTransactions.get(account).get(i) instanceof Payment) {
                balance += accountsToTransactions.get(account).get(i).getAmount();
            }
        }
        return balance;
    }

    /**
     * Returns a list of transactions for an account.
     *
     * @param account the selected account
     * @return the list of transactions
     */
    public List<Transaction> getTransactions(String account) {
        return accountsToTransactions.get(account);
    }

    /**
     * Returns a sorted list (-> calculated amounts) of transactions for a specific account . Sorts the list either in ascending or descending order
     * (or empty).
     *
     * @param account the selected account
     * @param asc     selects if the transaction list is sorted ascending or descending
     * @return the list of transactions
     */
    public List<Transaction> getTransactionsSorted(String account, boolean asc) {
        List<Transaction> sorted = accountsToTransactions.get(account);
        if (asc) {
            sorted.sort(Comparator.comparing(Transaction::getAmount));
        } else {
            sorted.sort(Comparator.comparing(Transaction::getAmount).reversed());
        }
        return sorted;
    }

    /**
     * Returns a list of either positive or negative transactions (-> calculated amounts).
     *
     * @param account  the selected account
     * @param positive selects if positive  or negative transactions are listed
     * @return the list of transactions
     */
    public List<Transaction> getTransactionsByType(String account, boolean positive) {
        List<Transaction> byType = new LinkedList<Transaction>();
        for (Transaction transaction : accountsToTransactions.get(account)) {
            if (positive) {
                if (transaction.amount > 0) {
                    byType.add(transaction);
                }
            } else {
                if (transaction.amount < 0) {
                    byType.add(transaction);
                }
            }
        }
        return byType;
    }

}

package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Eine Bank-Klasse, die Konten und Transaktionen verwalten
 * und verarbeiten kann.
 */
public class PrivateBank implements Bank {

    /**
     * Konstrukor
     *
     * @param pName             Name der Bank
     * @param pIncomingInterest Zinsen bei Einzahlung
     * @param pOutgoingInterest Zinsen bei Auszahlung
     */
    public PrivateBank(String pName, double pIncomingInterest, double pOutgoingInterest, String pDirectoryName) {
        this.name = pName;
        this.incomingInterest = pIncomingInterest;
        this.outgoingInterest = pOutgoingInterest;
        this.directoryName = pDirectoryName;
    }

    /**
     * Copy Konstruktor
     *
     * @param pOrig das zu Kopierende Objekt
     */
    public PrivateBank(PrivateBank pOrig) {
        this.name = pOrig.name;
        this.outgoingInterest = pOrig.outgoingInterest;
        this.incomingInterest = pOrig.incomingInterest;
        this.accountsToTransactions = pOrig.accountsToTransactions;
        this.directoryName = pOrig.directoryName;
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
     * Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert  in
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
     * Der Speicherort (ein spezieller Ordner im Dateisystem, relativer oder absoluter Pfad) der Konten bzw. Transaktionen
     */
    protected String directoryName;

    /**
     * Setzt den Speicherort der Konten bzw. Transaktionen
     *
     * @param directoryName Speicherort der Konten bzw. Transaktionen
     */
    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    /**
     * Liefert den Speicherort der Konten bzw. Transaktionen
     *
     * @return Speicherort der Konten bzw. Transaktionen
     */
    public String getDirectoryName() {
        return directoryName;
    }

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
     * Schreibt den jeweiligen Account samt Attribute und Transaktionen in eine Json Datei
     *
     * @param Account Accountname
     */
    public void writeAccount(String Account) {
        try (FileWriter fileWriter = new FileWriter(directoryName + "/" + Account + ".json")) {

            fileWriter.write("[");
            for (Transaction transaction : getTransactions(Account)) {
                Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(transaction.getClass(), new CustomSerializerDeserializer()).setPrettyPrinting().create();
                fileWriter.write(gson.toJson(transaction));


                if (transaction != getTransactions(Account).get(getTransactions(Account).size() - 1)) {
                    fileWriter.write(",");
                }
            }
            fileWriter.write("]");
            /*
            Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Transaction.class, new CustomSerializerDeserializer()).setPrettyPrinting().create();
            fileWriter.write(gson.toJsonTree(getTransactions(Account)).toString());
            */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ließt die Vorhandenen Json Dateien ein und erstellt dementsprechend Objekte mit den bereits vorhandenen Attributen
     *
     * @throws IOException Fehler beim Lesen / Schreiben der Datei
     * @throws AccountAlreadyExistsException Falls ein Account bereits existiert
     */
    public void readAccount() throws IOException, AccountAlreadyExistsException {
        File f = new File(this.directoryName);
        File[] file = Objects.requireNonNull(f.listFiles());
        for (File data : file) {
            createAccount(data.getName().substring(0, data.getName().indexOf(".")));
            try {


                Reader reader = new FileReader(directoryName + "/" + data.getName());
                JsonArray parser = JsonParser.parseReader(reader).getAsJsonArray();

                for (JsonElement jsonElement : parser.getAsJsonArray()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    Gson gson = new GsonBuilder().registerTypeAdapter(Transaction.class, new CustomSerializerDeserializer()).create();
                    String tmp = gson.toJson(jsonObject.get("Instance"));

                    if (jsonObject.get("CLASSNAME").getAsString().equals("Payment")) {
                        Payment payment = gson.fromJson(tmp, Payment.class);
                        addTransaction(data.getName().replace(".json", ""), payment);
                    } else if (jsonObject.get("CLASSNAME").getAsString().equals("IncomingTransfer")) {
                        IncomingTransfer incomingTransfer = gson.fromJson(tmp, IncomingTransfer.class);
                        addTransaction(data.getName().replace(".json", ""), incomingTransfer);
                    } else if (jsonObject.get("CLASSNAME").getAsString().equals("OutgoingTransfer")) {
                        OutgoingTransfer outgoingTransfer = gson.fromJson(tmp, OutgoingTransfer.class);
                        addTransaction(data.getName().replace(".json", ""), outgoingTransfer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransactionAlreadyExistException e) {
                e.printStackTrace();
            } catch (AccountDoesNotExistException e) {
                e.printStackTrace();
            }
        }

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
     * Vergleicht ob zwei Objekte des selben Typs die gleichen Attributwere Aufweisen
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
        double balance = 0;
        for (int i = 0; i < accountsToTransactions.get(account).size(); i++) {
            balance += accountsToTransactions.get(account).get(i).calculate();
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
        List<Transaction> sorted = new LinkedList<Transaction>(accountsToTransactions.get(account));
        if (asc) {
            sorted.sort(Comparator.comparing(Transaction::calculate));
        } else {
            sorted.sort(Comparator.comparing(Transaction::calculate).reversed());
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
                if (transaction.calculate() >= 0) {
                    byType.add(transaction);
                }
            } else {
                if (transaction.calculate() < 0) {
                    byType.add(transaction);
                }
            }
        }
        return byType;
    }
}

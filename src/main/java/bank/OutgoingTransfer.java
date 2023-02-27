package bank;

public class OutgoingTransfer extends Transfer {
    /**
     * Konstruktor instanziiert Objekt mit den Attributen: Datum, Geldmenge und Beschreibung
     *
     * @param date        Datum der Ein /- Auszahlung
     * @param amount      die Geldmenge
     * @param description eine Beschreibung
     */
    public OutgoingTransfer(String date, double amount, String description) {
        super(date, amount, description);
    }

    /**
     * Konstruktor instanziiert Objekten mit allen Attributen und verwendet hierbei den ersten Konstruktor
     *
     * @param date        Datum der Ein /- Auszahlung
     * @param amount      die Geldmenge
     * @param description eine Beschreibung
     * @param sender      Sender des Geldes
     * @param recipient   Empfaenger des Geldes
     */
    public OutgoingTransfer(String date, double amount, String description, String sender, String recipient) {
        super(date, amount, description, sender, recipient);
    }

    /**
     * Copy Konstruktor instanziiert Objekt mit selben Attributen wie das Original, welches per Parameter uebergeben wird
     *
     * @param orig das zu Kopierende Objekt
     */
    public OutgoingTransfer(Transfer orig) {
        super(orig);
    }

    /**
     * Sollte der Betrag positiv sein und somit eine Einzahlung repraesentieren, soll der Wert des incomingInterest-
     * Attributes prozentual von der Einzahlung abgezogen und das Ergebnis zurückgegeben werden.
     * Sollte der Betrag negativ sein und somit eine Auszahlung repraesentieren, soll der Wert des outgoingInterest-
     * Attributes prozentual zu der Auszahlung hinzuaddiert und das Ergebnis zurueckgegeben werden.
     *
     * @return Berechnete Ein oder Auszahlung
     */
    public double calculate() {
        return (this.getAmount() * -1);
    }
}

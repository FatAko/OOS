package bank;

/**
 * @author fatih
 * Die Basisklasse Transfer soll im Kontext von Ueberweisungen verwendet werden
 */
public class Transfer extends Transaction implements CalculateBill {
    /**
     * Akteur, der die Geldmenge, die in amount angegeben wurde, ueberwiesen hat
     */
    private String sender;
    /**
     * Akteur, der die Geldmenge, die in amount angegeben wurde, ueberwiesen bekommen hat
     */
    private String recipient;

    /**
     * Konstruktor instanziiert Objekt mit den Attributen: Datum, Geldmenge und Beschreibung
     *
     * @param date Datum der Ein /- Auszahlung
     * @param amount die Geldmenge
     * @param description eine Beschreibung
     */
    public Transfer(String date, double amount, String description) {
        super(date, amount, description);
    }

    /**
     * Konstruktor instanziiert Objekten mit allen Attributen und verwendet hierbei den ersten Konstruktor
     *
     * @param date Datum der Ein /- Auszahlung
     * @param amount die Geldmenge
     * @param description eine Beschreibung
     * @param sender Sender des Geldes
     * @param recipient Empfaenger des Geldes
     */
    public Transfer(String date, double amount, String description, String sender, String recipient) {
        this(date, amount, description);
        //this.setSender(sender);
        this.sender = sender;
        //this.setRecipient(recipient);
        this.recipient = recipient;
    }

    /**
     * Copy Konstruktor instanziiert Objekt mit selben Attributen wie das Original, welches per Parameter uebergeben wird
     *
     * @param orig das zu Kopierende Objekt
     */
    public Transfer(Transfer orig) {
        this(orig.date, orig.amount, orig.description, orig.sender, orig.recipient);
    }

    /**
     * liefert den Akteur, der die Geldmenge ueberwiesen hat
     *
     * @return liefert den Akteur, der die Geldmenge ueberwiesen hat
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * liefert den Akteur, der die Geldmenge ueberwiesen bekommen hat
     *
     * @return liefert den Akteur, der die Geldmenge ueberwiesen bekommen hat
     */
    public String getSender() {
        return sender;
    }

    /**
     * setzt den Akteur, der die Geldmenge ueberwiesen hat
     *
     * @param sender Sender der Geldmenge
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * setzt den Akteur, der die Geldmenge ueberwiesen bekommen hat
     *
     * @param recipient Empfaenger der Geldmenge
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * liefert Geldmenge
     *
     * @return liefert Geldmenge
     */
    public double calculate() {
        return amount;
    }

    /**
     * liefert alle Attributwerte des aktuellen Objekts
     *
     * @return liefert alle Attributwerte des aktuellen Objekts
     */
    public String toString() {
        return super.toString() + "\n" +
                "Sender: " + this.sender + "\n" +
                "Recipient: " + this.recipient + "\n";
    }

    /**
     * vergleicht zwei Objekte der selben Klasse miteinander
     *
     * @param obj ein Vergleichsobjekt
     * @return liefert true, falls beide Objekte die selben Klassenattributwerte besitzen
     */
    public boolean equals(Transfer obj) {
        return (super.equals(obj) && this.sender.equals(obj.getSender()) && this.recipient.equals(obj.getRecipient()));
    }
}

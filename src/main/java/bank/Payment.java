package bank;

/**
 * Die Basisklasse Payment soll Ein- und Auszahlungen repraesentieren
 *
 * @author fatih
 */
public class Payment extends Transaction implements CalculateBill {
    /**
     * Zinsen, die bei einer Einzahlung anfallen (0 - 1 in %)
     */
    private double incomingInterest;
    /**
     * Zinsen, die bei einer Auszahlung anfallen (0 - 1 in %)
     */
    private double outgoingInterest;


    /**
     * Konstruktor instanziiert Objekt mit den Attributen: Datum, Geldmenge und Beschreibung
     *
     * @param date        Datum der Ein / Auszahlung
     * @param amount      Geldmenge
     * @param description eine Beschreibung
     */
    public Payment(String date, double amount, String description) {
        super(date, amount, description);
    }

    /**
     * Konstruktor instanziiert Objekten mit allen Attributen und verwendet hierbei den ersten Konstruktor
     *
     * @param date             Datum der Ein / Auszahlung
     * @param amount           Geldmenge
     * @param description      eine Beschreibung
     * @param incomingInterest Zinsen, die bei einer Einzahlung anfallen
     * @param outgoingInterest Zinsen, die bei einer Auszahlung anfallen
     */
    public Payment(String date, double amount, String description, double incomingInterest, double outgoingInterest) {
        this(date, amount, description);
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;
    }

    /**
     * Copy Konstruktor instanziiert Objekt mit selben Attributen wie das Original, welches per Parameter uebergeben wird
     *
     * @param orig das zu Kopierende Objekt
     */
    public Payment(Payment orig) {
        this(orig.date, orig.amount, orig.description, orig.incomingInterest, orig.outgoingInterest);
    }

    /**
     * liefert Zinsen, die bei einer Einzahlung anfallen
     *
     * @return liefert Zinsen, die bei einer Einzahlung anfallen
     */
    public double getIncomingInterest() {
        return incomingInterest;
    }

    /**
     * liefert Zinsen, die bei einer Auszahlung anfallen
     *
     * @return liefert Zinsen, die bei einer Auszahlung anfallen
     */
    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    /**
     * setzt Zinsen, die bei einer Einzahlung anfallen
     *
     * @param incomingInterest Zinsen, die bei einer Einzahlung anfallen
     */
    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    /**
     * setzt Zinsen, die bei einer Auszahlung ausfallen
     *
     * @param outgoingInterest Zinsen, die bei einer Auszahlung ausfallen
     */
    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
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
        if (Double.compare(amount, 0.0) < 0) { //Negativ = Auszahlung, Positiv = Einzahlung
            return amount + (outgoingInterest * amount);
        } else return amount - (incomingInterest * amount);
    }

    /**
     * liefert alle Attributwerte des aktuellen Objekts
     *
     * @return liefert alle Attributwerte des aktuellen Objekts
     */
    public String toString() {
        return super.toString() + "\n" +
                "incomingInterest: " + this.incomingInterest + "\n" +
                "outgoingInterest: " + this.outgoingInterest;
    }

    /**
     * vergleicht zwei Objekte der selben Klasse miteinander
     *
     * @param obj ein Vergleichsobjekt
     * @return liefert true, falls beide Objekte die selben Klassenattributwerte besitzen
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Payment)) {
            return false;
        } else {
            return (super.equals(obj) && this.outgoingInterest == ((Payment) obj).getOutgoingInterest() && this.incomingInterest == ((Payment) obj).getIncomingInterest());
        }
    }
}

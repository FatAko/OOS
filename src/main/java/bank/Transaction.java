package bank;

/**
 * Die Oberklasse Transaction liefert die Attribute Datum, Geldmenge und Beschreibung.
 *
 * @author fatih
 */
public abstract class Transaction implements CalculateBill {

    /**
     * Konstruktor der abstrakten Klasse Transaction
     * @param pDate Datum der Tansaktion
     * @param pAmount Geldmenge einer Ein- oder Auszahlung
     * @param pDescription zusaetzliche Beschreibung
     */
    public Transaction(String pDate, double pAmount, String pDescription) {
        this.date = pDate;
        this.amount = pAmount;
        this.description = pDescription;
    }

    /**
     * Zeitpunkt einer Ein- oder Auszahlung; Format: DD.MM.YYYY
     */
    protected String date;
    /**
     * Geldmenge einer Ein- oder Auszahlung
     */
    protected double amount;
    /**
     * Zusaetzliche Beschreibung
     */
    protected String description;

    /**
     * liefert Datum einer Ein- oder Auszahlung
     *
     * @return liefert Datum einer Ein- oder Auszahlung
     */
    public String getDate() {
        return date;
    }

    /**
     * liefert Geldmenge
     *
     * @return liefert Geldmenge
     */
    public double getAmount() {
        return amount;
    }

    /**
     * liefert zusaetzliche Beschreibung
     *
     * @return setzt den Zeitpunkt einer Ein- oder Auszahlung
     */
    public String getDescription() {
        return description;
    }

    /**
     * setzt den Zeitpunkt einer Ein- oder Auszahlung
     *
     * @param date Datum einer Ein- oder Auszahlung
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * setzt die Geldmenge
     *
     * @param amount die Gelgmenge bei einer Ein- Auszahlung
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * setzt die Beschreibung
     *
     * @param description eine Beschreibung zur Transaktion
     */
    public void setDescription(String description) {
        this.description = description;
    } //setzt die Beschreibung

    /**
     * liefert alle Attributwerte des aktuellen Objekts
     *
     * @return liefert alle Attributwerte des aktuellen Objekts
     */
    public String toString() {
        return "Datum: " + this.date + "\n" +
                "Beschreibung: " + this.description + "\n" +
                "Geldmenge: " + calculate();
    }

    /**
     * vergleicht zwei Objekte der selben Klasse miteinander
     *
     * @param obj ein Vergleichsobjekt
     * @return liefert true, falls beide Objekte die selben Klassenattributwerte besitzen
     */
    public boolean equals(Object obj) {
        return (this.description.equals(((Transaction)obj).getDescription()) && this.amount == ((Transaction)obj).getAmount() && this.date == ((Transaction)obj).getDate());
    }
}

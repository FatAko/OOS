package bank;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransferTest {

    Transfer testTransfer1, testTransfer2, copyTransfer1;

    /**
     * Vorbedingungen vor jedem Test Case
     */
    @BeforeEach
    void init() {
        testTransfer1 = new Transfer("Test", 1000, "TestIncoming", "TestSender", "TestEmpfänger");
        testTransfer2 = new Transfer("Test", 1000, "TestOutgoing", "TestSender", "TestEmpfänger");
        copyTransfer1 = new Transfer(testTransfer1);
    }

    /**
     * Test Case für den Copy-Konstruktor
     */
    @Test
    void copyConstructorTest() {
        assertEquals(testTransfer1, copyTransfer1);
    }

    /**
     * Test Case für die calculate() Methode
     */
    @Test
    void calculateTest() {
        IncomingTransfer incoming = new IncomingTransfer(testTransfer1);
        OutgoingTransfer outgoing = new OutgoingTransfer(testTransfer2);
        assertEquals(1000, incoming.calculate());
        assertEquals(-1000, outgoing.calculate());
    }

    /**
     * Test Case für die equals() Methode
     */
    @Test
    void equalsTest() {
        assertEquals(testTransfer1, copyTransfer1);
        assertNotEquals(testTransfer2, copyTransfer1);
    }

    /**
     * Test Case für die toString() Methode
     */
    @Test
    void toStringTest() {
        assertEquals("Datum: Test\nBeschreibung: TestIncoming\nGeldmenge: 1000.0\nSender: TestSender\nRecipient: TestEmpfänger\n", testTransfer1.toString());
    }
}

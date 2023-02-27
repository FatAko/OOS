package bank;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    Payment paymentTest1, paymentTest2, paymentCopyTest;

    /**
     * Vorbedingungen vor jedem Test Case
     */
    @BeforeEach
    void init() {
        paymentTest1 = new Payment("Test", 1000, "TestPaymentIncoming", 0.05, 0.1);
        paymentTest2 = new Payment("Test", -1000, "TestPaymentOutgoing", 0.05, 0.1);
        paymentCopyTest = new Payment(paymentTest1);
    }

    /**
     * Test Case für den Copy-Konstruktor
     */
    @Test
    void copyConstructorTest() {
        assertEquals(paymentTest1, paymentCopyTest);
    }

    /**
     * Test Case für die calculate() Methode
     */
    @Test
    void calculateTest() {
        assertEquals(950.0, paymentTest1.calculate()); //IncomingInterest
        assertEquals(-1100, paymentTest2.calculate()); //OutgoingInterest
    }

    /**
     * Test Case für die equals() Methode
     */
    @Test
    void equalsTest() {
        assertEquals(paymentTest1, paymentCopyTest);
        paymentCopyTest.setAmount(500);
        assertNotEquals(paymentTest1, paymentCopyTest);
    }

    /**
     * Test Case für die toString() Methode
     */
    @Test
    void toStringTest() {
        assertEquals("Datum: Test\nBeschreibung: TestPaymentIncoming\nGeldmenge: 950.0\nincomingInterest: 0.05\noutgoingInterest: 0.1", paymentTest1.toString());
    }
}

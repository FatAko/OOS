package bank;

import bank.exceptions.AccountAlreadyExistsException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse zum Testen der Funktionalitäten der impl. Methoden aus dem Interface Bank
 *
 * @author fatih
 */
public class PrivateBankTest {
    PrivateBank privateBankTest;
    IncomingTransfer incomingTransferTest;
    OutgoingTransfer outgoingTransferTest;
    Payment paymentTest;

    /**
     * Vorbedingen der Test Cases
     *
     * @throws AccountAlreadyExistsException Account existiert bereits
     */
    @BeforeEach
    void init() throws AccountAlreadyExistsException {
        privateBankTest = new PrivateBank("TestBank", 0.0, 0.0, "C:/Users/Benutzer01/IdeaProjects/OOS/src/test/java/bank/transactionsTest");
        incomingTransferTest = new IncomingTransfer("TestDatum", 1000, "Incoming", "TestSender", "TestEmpfänger");
        outgoingTransferTest = new OutgoingTransfer("TestDatum", 500, "Outgoing", "TestEmpfänger", "TestSender");
        paymentTest = new Payment("TestDatum", 50, "Payment");
        privateBankTest.createAccount("Peter");
    }

    /**
     * Löschen der Inhalte des transactionTest Ordners nach jedem Test Case
     */
    @AfterEach
    void reset() {
        File f = new File(privateBankTest.getDirectoryName());
        for (File file : f.listFiles()) {
            if (file != null)
                file.delete();
        }
    }

    /**
     * Test der getName() Methode
     */
    @Test
    void getNameTest() {
        assertEquals("TestBank", privateBankTest.getName());
    }

    /**
     * Test der setName() Methode
     */
    @Test
    void setNameTest() {
        privateBankTest.setName("NameTest");
        assertEquals("NameTest", privateBankTest.getName());
    }

    /**
     * Test der getIncoming() Methode
     */
    @Test
    void getIncomingTest() {
        assertEquals(0.0, privateBankTest.getIncomingInterest());
    }

    /**
     * Test der setIncoming() Methode
     */
    @Test
    void setIncomingTest() {
        privateBankTest.setIncomingInterest(0.05);
        assertEquals(0.05, privateBankTest.getIncomingInterest());
    }

    /**
     * Test der getOutgoing() Methode
     */
    @Test
    void getOutgoingTest() {
        assertEquals(0.0, privateBankTest.getOutgoingInterest());
    }

    /**
     * Test der setOutgoing() Methode
     */
    @Test
    void setOutgoingTest() {
        privateBankTest.setOutgoingInterest(0.01);
        assertEquals(0.01, privateBankTest.getOutgoingInterest());
    }

    /**
     * Test der createAccount() Methode
     */
    @Test
    void createAccountTest() {
        assertDoesNotThrow(() -> privateBankTest.createAccount("New"));
    }

    /**
     * Test der addTransaction() Methode
     */
    @Test
    void addTransactionTest() {
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", paymentTest));
        assertEquals(true, privateBankTest.containsTransaction("Peter", paymentTest));
    }

    /**
     * Test der removeTransaction() Methode
     */
    @Test
    void removeTransactionTest() {
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", incomingTransferTest));
        assertEquals(true, privateBankTest.containsTransaction("Peter", incomingTransferTest));
        assertDoesNotThrow(() -> privateBankTest.removeTransaction("Peter", incomingTransferTest));
        assertEquals(false, privateBankTest.containsTransaction("Peter", incomingTransferTest));
    }

    /**
     * Test der containsTransaction() Methode
     */
    @Test
    void containsTransactionTest() {
        assertEquals(false, privateBankTest.containsTransaction("Peter", incomingTransferTest));
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", incomingTransferTest));
        assertEquals(true, privateBankTest.containsTransaction("Peter", incomingTransferTest));
    }

    /**
     * Test der getAccountBalance() Methode
     */
    @Test
    void getAccountBalanceTest() {
        assertEquals(0.0, privateBankTest.getAccountBalance("Peter"));
    }

    /**
     * Test der getTransaction() Methode
     */
    @Test
    void getTransactionsTest() {
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", incomingTransferTest));
        assertEquals(List.of(incomingTransferTest), privateBankTest.getTransactions("Peter"));
    }

    /**
     * Test der getTransactionsSorted() Methode
     */
    @Test
    void getTransactionsSortedTest() {
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", incomingTransferTest));
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", outgoingTransferTest));
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", paymentTest));
        assertEquals(List.of(incomingTransferTest, paymentTest, outgoingTransferTest), privateBankTest.getTransactionsSorted("Peter", false));
    }

    /**
     * Test der getTransactionsByType() Methode
     */
    @Test
    void getTransactionsByType() {
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", incomingTransferTest));
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", outgoingTransferTest));
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", paymentTest));
        assertEquals(List.of(incomingTransferTest, paymentTest), privateBankTest.getTransactionsByType("Peter", true));
    }

    /**
     * Test zur Verfügbarkeit der Json Datei
     *
     * @param p Name der Datei
     */
    @ParameterizedTest
    @ValueSource(strings = {"Peter.json"})
    void containsJsonTest(String p) {
        assertDoesNotThrow(() -> privateBankTest.addTransaction("Peter", incomingTransferTest));
        privateBankTest.writeAccount("Peter");
        File f = new File(privateBankTest.getDirectoryName());
        assertTrue(f.listFiles()[0].getName().equals(p));
    }
}

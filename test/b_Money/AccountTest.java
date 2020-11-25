package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("TestPayment",1,1,new Money(1000,SEK),SweBank,"Alice");
		assertTrue(testAccount.timedPaymentExists("TestPayment"));
		testAccount.removeTimedPayment("TestPayment");
		assertFalse(testAccount.timedPaymentExists("TestPayment"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		assertEquals(10000000,(int) testAccount.getBalance().getAmount());
		testAccount.addTimedPayment("TestPayment",1,1,new Money(1000000,SEK),SweBank,"Alice");
		testAccount.tick();
		testAccount.tick();
		assertEquals(9000000,(int) testAccount.getBalance().getAmount());
		assertEquals(2000000,(int) SweBank.getBalance("Alice"));
	}

	@Test
	public void testAddWithdraw() {
		assertEquals(10000000,(int) testAccount.getBalance().getAmount());
		testAccount.withdraw(new Money(10000,SEK));
		assertNotEquals(10000000,(int) testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(10000000,(int) testAccount.getBalance().getAmount());
	}
}

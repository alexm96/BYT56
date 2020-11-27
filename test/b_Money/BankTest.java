package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals("SEK", SweBank.getCurrency().getName());
		assertEquals("DKK", DanskeBank.getCurrency().getName());
		assertEquals("SEK", Nordea.getCurrency().getName());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {

		SweBank.openAccount("toOpen");
		assertEquals(0 ,(int)SweBank.getBalance("toOpen"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(100000, SEK));
		assertEquals(100000, (int)SweBank.getBalance("Bob"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(100000, SEK));
		SweBank.withdraw("Bob", new Money(100000, SEK));
		assertEquals(0,(int) SweBank.getBalance("Bob"));
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		assertEquals(1000, (int)SweBank.getBalance("Bob"));
	}


	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		SweBank.transfer("Bob", "Ulrika",new Money(1000, SEK));
		assertEquals(0, (int)SweBank.getBalance("Bob"));
		assertEquals(1000, (int)SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		SweBank.addTimedPayment("Bob", "sample", 1, 1, new Money(100, SEK),Nordea, "Bob");
		SweBank.tick();

		assertEquals(900, (int)SweBank.getBalance("Bob"));
		assertEquals(100, (int)Nordea.getBalance("Bob"));
		SweBank.removeTimedPayment("Bob", "sample");
		SweBank.tick();

		assertEquals(Integer.valueOf(900), SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(100), Nordea.getBalance("Bob"));
	}
}

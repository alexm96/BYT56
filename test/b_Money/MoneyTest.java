package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {

		assertEquals(10000,(int)SEK100.getAmount());
	}

	@Test
	public void testGetCurrency() {

		assertEquals(SEK,SEK100.getCurrency());
	}

	@Test
	public void testToString() {
		String msg ="100.00 SEK";
		assertEquals(msg,SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(3000,(int)SEK200.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK200.equals(SEK200));
		assertTrue(SEK100.equals(EUR10));
	}

	@Test
	public void testAdd() {

		Money shouldBe3000 = SEK100.add(EUR10);
		assertEquals(3000,(int) shouldBe3000.universalValue());
	}

	@Test
	public void testSub() {
		Money shouldBe0 = SEK100.sub(EUR10);
		assertEquals(0,(int) shouldBe0.universalValue());
	}

	@Test
	public void testIsZero() {
		Money shouldBe0 = SEK100.sub(EUR10);
		assertTrue(shouldBe0.isZero());
	}

	@Test
	public void testNegate() {
		Money shouldBe3000 = SEK100.add(EUR10);
		Money shouldBeNeg3000=shouldBe3000.negate();
		assertEquals(-3000,(int)shouldBeNeg3000.universalValue());

	}

	@Test
	public void testCompareTo() {
		assertEquals(0,SEK200.compareTo(SEK200));
		assertEquals(1,SEK200.compareTo(SEK100));
		assertEquals(-1,SEK100.compareTo(SEK200));
		assertEquals(0,SEK100.compareTo(EUR10));
	}
}

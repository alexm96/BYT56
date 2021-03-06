package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK",SEK.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(.15),SEK.getRate());
	}
	
	@Test
	public void testSetRate() {
		assertEquals(Double.valueOf(.15),SEK.getRate());
		SEK.setRate(.16);
		assertNotEquals(.15, SEK.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(15,(int) SEK.universalValue(100));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(1000,(int)SEK.valueInThisCurrency(100,EUR));
	}

}

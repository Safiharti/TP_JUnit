package fr.emse.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyBagTest {

	private Money f12CHF;
	private Money f14CHF;
	private Money f7USD;
	private Money f21USD;
	private MoneyBag fMB1;
	private MoneyBag fMB2;
	@Before
	public void setUp() {
		f12CHF= new Money(12, "CHF");
		f14CHF= new Money(14, "CHF");
		f7USD= new Money( 7, "USD");
		f21USD= new Money(21, "USD");
		fMB1= new MoneyBag(f12CHF, f7USD);
		fMB2= new MoneyBag(f14CHF, f21USD);
	}
	@Test
	public void testBagEquals() {
		assertTrue(!fMB1.equals(null)); 
		assertEquals(fMB1, fMB1); 
		assertTrue(!fMB1.equals(f12CHF)); 
		assertTrue(!f12CHF.equals(fMB1)); 
		assertTrue(!fMB1.equals(fMB2));
	}
	@Test
	public void testMixedSimpleAdd() {
		// [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
		Money bag[] = { f12CHF, f7USD };
		MoneyBag expected = new MoneyBag(bag);
		assertEquals(expected, f12CHF.add(f7USD));
	}
	
	@Test
    public void testBagSimpleAdd() {
        MoneyBag bag = new MoneyBag(new Money[]{f12CHF});
        IMoney result = bag.add(f7USD);
        MoneyBag expected = new MoneyBag(new Money[]{f12CHF, f7USD});
        assertEquals(expected, result);
    }

    @Test
    public void testSimpleBagAdd() {
        MoneyBag bag = new MoneyBag(new Money[]{f7USD});
        IMoney result = bag.add(f7USD);
        MoneyBag expected = new MoneyBag(new Money[]{new Money(14, "USD")});
        assertEquals(expected, result);
    }

    @Test
    public void testBagBagAdd() {
        MoneyBag bag1 = new MoneyBag(new Money[]{f12CHF});
        MoneyBag bag2 = new MoneyBag(new Money[]{f7USD});
        MoneyBag expected = new MoneyBag(new Money[]{f12CHF, f7USD});
        assertEquals(expected, bag1.add(bag2));
    }
    @Test
    public void testSimplification() {
        MoneyBag moneyBag = new MoneyBag(new Money[]{new Money(10, "USD")});
        
        IMoney simplified = moneyBag.simplify();
        
        assertEquals(new Money(10, "USD"), simplified);
    }
    @Test
    public void testNoSimplification() {
        MoneyBag moneyBag = new MoneyBag(new Money[]{new Money(10, "USD"), new Money(5, "EUR")});
        
        IMoney simplified = moneyBag.simplify();
        
        assertEquals(moneyBag, simplified);
    }
}
